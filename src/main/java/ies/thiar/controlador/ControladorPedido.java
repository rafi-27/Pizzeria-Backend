package ies.thiar.controlador;

import java.sql.SQLException;

import ies.thiar.Modelo.Pedido;
import ies.thiar.controlador.dao.PedidoDao;
import ies.thiar.controlador.dao.imp.JDBCPedido;

public class ControladorPedido {
    PedidoDao jPedidoDao = new JDBCPedido();

    public void insertPedido(Pedido pedido) throws SQLException {
        jPedidoDao.insert(pedido);
    }










}