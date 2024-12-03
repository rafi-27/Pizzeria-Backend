package ies.thiar.controlador;

import java.sql.SQLException;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.Pagable;
import ies.thiar.Modelo.PagarEfectivo;
import ies.thiar.Modelo.PagarTarjeta;
import ies.thiar.Modelo.Pedido;
import ies.thiar.controlador.dao.ClienteDao;
import ies.thiar.controlador.dao.PedidoDao;
import ies.thiar.controlador.dao.imp.JDBCClienteDao;
import ies.thiar.controlador.dao.imp.JDBCPedido;

public class ControladorPedido {
    private PedidoDao jPedidoDao = new JDBCPedido();
    private ClienteDao jClienteDao = new JDBCClienteDao();

    private Pedido pedidoActual;

    public ControladorPedido(Pedido pedidoActual) {
        this.pedidoActual = pedidoActual;
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
    public void anyadirCarrito(){
        
    }

    public void finalizarPedido(Pagable metodoPago,Cliente cliente){

    }
    











}