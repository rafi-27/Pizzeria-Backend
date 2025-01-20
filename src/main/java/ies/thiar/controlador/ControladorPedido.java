package ies.thiar.controlador;

import java.sql.SQLException;
import java.util.List;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.LineaPedido;
import ies.thiar.Modelo.Pagable;
import ies.thiar.Modelo.PagarEfectivo;
import ies.thiar.Modelo.PagarTarjeta;
import ies.thiar.Modelo.Pedido;
import ies.thiar.Modelo.Producto;
import ies.thiar.controlador.dao.ClienteDao;
import ies.thiar.controlador.dao.PedidoDao;
import ies.thiar.controlador.dao.imp.JPAClienteDao;
import ies.thiar.controlador.dao.imp.JPAPedidoDao;

public class ControladorPedido {
    //private PedidoDao jpaPedidoDao = new JDBCPedido();
    private ClienteDao jClienteDao = new JPAClienteDao();
    private PedidoDao jpaPedidoDao;

   private Pedido pedidoActual;

    public ControladorPedido(Pedido pedido) {
        this.pedidoActual=pedido;
        this.jpaPedidoDao = new JPAPedidoDao();
    }

    public ControladorPedido() {
        this.jpaPedidoDao = new JPAPedidoDao();
    }

    public void insertPedido(Pedido pedido) throws SQLException {
        jpaPedidoDao.insert(pedido);
    }

    public void deletePedido(int id) throws SQLException {
        jpaPedidoDao.delete(id);
    }

    public void updatePedido(Pedido pedido) throws SQLException {
        jpaPedidoDao.update(pedido);
    }

    public Pedido findPedidoById(int id) throws SQLException {
        return jpaPedidoDao.findByID(id);
    }

    //Metodos complementarios:
    public List<LineaPedido> obtenerLineasPedidosByIdPedido(int idPedido) throws SQLException{
        return jpaPedidoDao.obtenerLineasPedidosByIdPedido(idPedido);
    }

    public List<Pedido> obtenerPedidosByState(EstadoPedido state) throws SQLException {
        return jpaPedidoDao.obtenerPedidosByState(state);
    }

    public List<Pedido> obtenerPedidosByIdClient(int idCliente) throws SQLException {
        return jpaPedidoDao.obtenerPedidosByIdClient(idCliente);
    }

    public void agregarLineaPedido(List<LineaPedido> listaLineaPedidos, int idPedido) throws SQLException {
        jpaPedidoDao.agregarLineaPedido(listaLineaPedidos, idPedido);
    }

    
    // primero comprobar si el pedido existe
    public void anyadirCarrito(Pedido pedido,Producto producto, int cantidad) throws SQLException, IllegalAccessException {
        Cliente cliente = jClienteDao.findByID(pedido.getCliente().getId());

        if (cliente == null) {
           // throw new IllegalAccessException("Usuario incorrecto{anyadirCarrito}");
            jClienteDao.insert(pedido.getCliente());
        }

        if(pedido.getEstado()!=EstadoPedido.PENDIENTE){
            pedido = new Pedido(pedido.getCliente());
        }

        try {
            Pedido existente = jpaPedidoDao.findByID(pedido.getId());

            if(existente == null){
                insertPedido(pedido);
            }else{
                pedido.setLineaPedido(existente.getLineaPedido());
            }

            LineaPedido lineaPedido = new LineaPedido(cantidad, producto, existente);
            pedido.getLineaPedido().add(lineaPedido);

            updatePedido(pedido);
        } catch (Exception e) {
            System.out.println("Algo salio mal al anyadir al carrito.");
        }

        if (pedido.getEstado() != EstadoPedido.PENDIENTE) {
            pedido.setEstado(EstadoPedido.PENDIENTE);
        }
    }


    public void finalizarPedido(Pedido pedido, Pagable metodoPago) throws IllegalAccessException, SQLException {
        Cliente client = jClienteDao.findByID(pedido.getCliente().getId());
        if (client == null) {
            throw new IllegalAccessException("Usuario incorrecto{finalizarPedido}");
        }
    
        if (pedido.getEstado() == EstadoPedido.PENDIENTE) {
            metodoPago.pagar(pedido.getPrecioTotal());
            pedido.setEstado(EstadoPedido.FINALIZADO);
    
            if (metodoPago.formaPago() == 0) {
                pedido.setPago(new PagarTarjeta());
            } else {
                pedido.setPago(new PagarEfectivo());
            }
    
            jpaPedidoDao.update(pedido);
    
            pedido.getLineaPedido().clear();
        } else {
            System.out.println("Algo salió mal al finalizar.");
        }
    }
    

    public void cancelarPedido(Pedido pedido) throws IllegalAccessException, SQLException {
        Cliente client = jClienteDao.findByID(pedido.getCliente().getId());

        if (client == null) {
            throw new IllegalAccessException("Usuario incorrecto{cancelarPedido}");
        }
        if (pedido.getEstado() != EstadoPedido.FINALIZADO || pedido.getEstado() == EstadoPedido.ENTREGADO) {
            pedido.setEstado(EstadoPedido.CANCELADO);
            updatePedido(pedido);
            System.out.println("El pedido ha sido cancelado.");
        } else {
            System.out.println("Algo salio mal al cancelar el pedido.");
        }
    }


    public void entregarPedido(Pedido pedido) throws SQLException {
        if (findPedidoById(pedido.getId()) != null) {
            pedido.setEstado(EstadoPedido.ENTREGADO);
            updatePedido(pedido);
        } else {
            System.out.println("Id incorrecto");
        }
    }
}