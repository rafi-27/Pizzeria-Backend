package ies.thiar.controlador.dao;

import java.sql.SQLException;
import java.util.List;
import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.LineaPedido;
import ies.thiar.Modelo.Pedido;

public interface PedidoDao {
    //CRUD
    public void insert(Pedido pedido) throws SQLException;
    public void delete(int id) throws SQLException;
    public void update(Pedido pedido) throws SQLException;
    public Pedido findByID(int id) throws SQLException;

    //Otros:
    public List<Pedido> obtenerPedidosByIdClient(int idCliente) throws SQLException;
    public List<Pedido> obtenerPedidosByState(EstadoPedido state) throws SQLException;
    public List<LineaPedido> obtenerLineasPedidosByIdPedido(int idPedido) throws SQLException;
    //En caso de no haberse creado ese pedido se crea
    public void agregarLineaPedido(List<LineaPedido> listaLineaPedidos, int idPedido) throws SQLException;
    public Pedido findPedidoByIdClient(int idCliente) throws SQLException;
}