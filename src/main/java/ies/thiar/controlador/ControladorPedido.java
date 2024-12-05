package ies.thiar.controlador;

import java.sql.SQLException;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.LineaPedido;
import ies.thiar.Modelo.Pagable;
import ies.thiar.Modelo.PagarEfectivo;
import ies.thiar.Modelo.PagarTarjeta;
import ies.thiar.Modelo.Pedido;
import ies.thiar.Modelo.Producto;
import ies.thiar.Modelo.SIZE;
import ies.thiar.controlador.dao.ClienteDao;
import ies.thiar.controlador.dao.PedidoDao;
import ies.thiar.controlador.dao.imp.JDBCClienteDao;
import ies.thiar.controlador.dao.imp.JDBCPedido;

public class ControladorPedido {
    private PedidoDao jPedidoDao = new JDBCPedido();
    private ClienteDao jClienteDao = new JDBCClienteDao();

    private Pedido pedidoActual;
    public ControladorPedido(Pedido pedido) {
        this.pedidoActual=pedido;
    }

    public ControladorPedido() {}

    public void insertPedido(Pedido pedido) throws SQLException {
        jPedidoDao.insert(pedido);
    }

    public void deletePedido(int id) throws SQLException {
        jPedidoDao.delete(id);
    }

    public void updatePedido(Pedido pedido) throws SQLException {
        jPedidoDao.update(pedido);
    }

    public Pedido findPedidoById(int id) throws SQLException {
        return jPedidoDao.findByID(id);
    }

    // primero comprobar si el pedido existe
    public void anyadirCarrito(Producto producto, int cantidad, SIZE tamaño) throws SQLException, IllegalAccessException {
        Cliente cliente = jClienteDao.findByID(pedidoActual.getCliente());
        
        if (cliente == null) {
            throw new IllegalAccessException("Usuario incorrecto{anyadirCarrito}");
        }

        if(pedidoActual.getEstado()!=EstadoPedido.PENDIENTE){
            pedidoActual = new Pedido(pedidoActual.getCliente());
        }

        try {
            Pedido existente = jPedidoDao.findByID(pedidoActual.getId());
            if(existente == null){
                jPedidoDao.insert(existente);
            }else{
                pedidoActual.setLineaPedido(existente.getLineaPedido());
            }

            LineaPedido lineaPedido = new LineaPedido(cantidad, producto, existente);
            pedidoActual.getLineaPedido().add(lineaPedido);

            updatePedido(pedidoActual);



        } catch (Exception e) {
            // TODO: handle exception
        }
        

        pedidoActual.getLineaPedido().add(new LineaPedido(pedidoActual.getLineaPedido().size() + 1, cantidad, producto, pedidoActual));

        if (pedidoActual.getEstado() != EstadoPedido.PENDIENTE) {
            pedidoActual.setEstado(EstadoPedido.PENDIENTE);
        }
    }

    public void finalizarPedido(Pagable metodoPago) throws IllegalAccessException, SQLException {
        Cliente client = jClienteDao.findByID(pedidoActual.getCliente());
        if (client == null) {
            throw new IllegalAccessException("Usuario incorrecto{finalizarPedido}");
        }
        if (pedidoActual.getEstado() == EstadoPedido.PENDIENTE) {
            metodoPago.pagar(pedidoActual.getPrecioTotal());
            pedidoActual.setEstado(EstadoPedido.FINALIZADO);
            if (metodoPago.formaPago()==0) {
                pedidoActual.setPago(new PagarTarjeta());
            }else pedidoActual.setPago(new PagarEfectivo());
            updatePedido(pedidoActual);
            pedidoActual.getLineaPedido().clear();
        } else {
            System.out.println("Algo salio mal al finalizar.");
        }
    }

    public void cancelarPedido() throws IllegalAccessException, SQLException {
        Cliente client = jClienteDao.findByID(pedidoActual.getCliente());

        if (client == null) {
            throw new IllegalAccessException("Usuario incorrecto{cancelarPedido}");
        }
        if (pedidoActual.getEstado() != EstadoPedido.FINALIZADO || pedidoActual.getEstado() == EstadoPedido.ENTREGADO) {
            pedidoActual.setEstado(EstadoPedido.CANCELADO);
            updatePedido(pedidoActual);
            System.out.println("El pedido ha sido cancelado.");
        } else {
            System.out.println("Algo salio mal al cancelar el pedido.");
        }
    }

    public void entregarPedido(int idPedido) throws SQLException {
        if (pedidoActual.getId() == idPedido) {
            pedidoActual.setEstado(EstadoPedido.ENTREGADO);
            updatePedido(pedidoActual);
        } else {
            System.out.println("Id incorrecto");
        }
    }
}