package ies.thiar.controlador;

import java.sql.SQLException;

import ies.thiar.Modelo.Producto;
import ies.thiar.controlador.dao.ProductoDao;
import ies.thiar.controlador.dao.imp.JDBCProductoDao;

public class ControladorProducto {
    ProductoDao jProductoDao = new JDBCProductoDao();

    public void insertProducto(Producto producto) throws SQLException{
        jProductoDao.insert(producto);
    }

    



}
