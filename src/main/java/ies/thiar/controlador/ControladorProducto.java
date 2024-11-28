package ies.thiar.controlador;

import java.sql.SQLException;
import java.util.List;

import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.Producto;
import ies.thiar.controlador.dao.ProductoDao;
import ies.thiar.controlador.dao.imp.JDBCProductoDao;

public class ControladorProducto {
    ProductoDao jProductoDao = new JDBCProductoDao();

    public void insertProducto(Producto producto) throws SQLException{
        jProductoDao.insert(producto);
    }

    public void deleteProduct(int id) throws SQLException{
        jProductoDao.delete(id);
    }

    public List<String> findAlergenoByIdIngredient(int idIngrediente) throws SQLException{
        return jProductoDao.listaAlergenosIngrediente(idIngrediente);
    }

    public List<Ingrediente> findIngredientesByProducto(int idProduct) throws SQLException{
        return jProductoDao.findIngredientesProducto(idProduct);
    }
    
}
