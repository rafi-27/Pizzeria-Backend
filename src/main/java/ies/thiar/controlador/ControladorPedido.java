package ies.thiar.controlador;

import java.sql.SQLException;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.LineaPedido;
import ies.thiar.Modelo.Pagable;
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

    public ControladorPedido(Cliente cliente) {
        this.pedidoActual = new Pedido(cliente.getId());
    }

    public ControladorPedido() {}


    public void insertPedido(Pedido pedido) throws SQLException {
        jPedidoDao.insert(pedido);
    }

    public void deletePedido(int id) throws SQLException {
        jPedidoDao.delete(id);
    }

    public void updatePedido(Pedido pedido)throws SQLException {
        jPedidoDao.update(pedido);
    }

    public Pedido findPedidoById(int id) throws SQLException{
        return jPedidoDao.findByID(id);
    }














    //primero comprobar si el pedido existe
    public void anyadirCarrito(Producto producto, int cantidad, SIZE tamaño) throws SQLException, IllegalAccessException{
        Cliente cliente = jClienteDao.findByID(pedidoActual.getCliente());
        if (cliente == null) {
            throw new IllegalAccessException("Usuario incorrecto{anyadirCarrito}");
        }
        pedidoActual.getLineaPedido().add(new LineaPedido(pedidoActual.getLineaPedido().size() + 1, cantidad, producto, pedidoActual));
        
        if (pedidoActual.getEstado()!=EstadoPedido.PENDIENTE) {
            pedidoActual.setEstado(EstadoPedido.PENDIENTE);
        }
    }


    public void finalizarPedido(Pagable metodoPago) throws IllegalAccessException, SQLException{
        Cliente client = jClienteDao.findByID(pedidoActual.getCliente());
        if (client == null) {
            throw new IllegalAccessException("Usuario incorrecto{finalizarPedido}");
        }
        if (pedidoActual.getEstado() == EstadoPedido.PENDIENTE) {
            metodoPago.pagar(pedidoActual.getPrecioTotal());
            pedidoActual.setEstado(EstadoPedido.FINALIZADO);
            System.out.println("Precio total: "+pedidoActual.getPrecioTotal());
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
            System.out.println("El pedido ha sido cancelado.");
        }  else {
            System.out.println("Algo salio mal al cancelar el pedido.");
        }
    }

    public void entregarPedido(int idPedido) throws SQLException {
        System.out.println("ID del pedido: "+pedidoActual.getId()+" ID del que le paso: "+idPedido);
        if (pedidoActual.getId()==idPedido) {
            Pedido pedido = jPedidoDao.findByID(pedidoActual.getId());
            System.out.println(pedido.toString());
            pedidoActual.setEstado(EstadoPedido.ENTREGADO);
            jPedidoDao.update(pedido);
            System.out.println(pedido.toString());
        }else{
            System.out.println("Id incorrecto");
        } 
    }
}