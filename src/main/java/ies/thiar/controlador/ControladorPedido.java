package ies.thiar.controlador;

import java.sql.SQLException;

import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.PagarEfectivo;
import ies.thiar.Modelo.PagarTarjeta;
import ies.thiar.Modelo.Pedido;
import ies.thiar.controlador.dao.PedidoDao;
import ies.thiar.controlador.dao.imp.JDBCPedido;

public class ControladorPedido {
    PedidoDao jPedidoDao = new JDBCPedido();

    //primero comprobar si el pedido existe
    public void insertPedido(Pedido pedido) throws SQLException {
        if(pedido.getEstado().equals(EstadoPedido.PENDIENTE)){
                pedido.setPago(null);
        }else if(pedido.getEstado().equals(EstadoPedido.FINALIZADO)){
            if(pedido.getPago().formaPago()==0){
                pedido.setPago(new PagarTarjeta());
            }else{
                pedido.setPago(new PagarEfectivo());
            }
        }
        jPedidoDao.insert(pedido);
    }

    










}