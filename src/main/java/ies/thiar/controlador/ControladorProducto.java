package ies.thiar.controlador;

import java.sql.SQLException;
import java.util.List;

import ies.thiar.Modelo.Producto;
import ies.thiar.controlador.dao.ProductoDao;
import ies.thiar.controlador.dao.imp.JPAProductoDao;

public class ControladorProducto {
    ProductoDao productoJpa;

    public ControladorProducto() {
        productoJpa = new JPAProductoDao();
    }

    public void insertProducto(Producto producto) throws SQLException{
        productoJpa.insert(producto);
    }

    public void deleteProduct(int id) throws SQLException{
        productoJpa.delete(id);
    }

    public void updateProduct(Producto producto) throws SQLException{
        productoJpa.update(producto);
    }

    // public List<String> findAlergenoByIdIngredient(int idIngrediente) throws SQLException{
    //     return jProductoDao.listaAlergenosIngrediente(idIngrediente);
    // }

    // public List<Ingrediente> findIngredientesByProducto(int idProduct) throws SQLException{
    //     return jProductoDao.findIngredientesProducto(idProduct);
    // }

    public List<Producto>findAllProducts() throws SQLException{
        return productoJpa.findAll();
    }

    // public Producto findProductById(int idProducto) throws SQLException {
    //     return jProductoDao.findByID(idProducto);
    // }
    

    //-------------------------------------JDBC-------------------------------------
    // public void insertProducto(Producto producto) throws SQLException{
    //     jProductoDao.insert(producto);
    // }

    // public void deleteProduct(int id) throws SQLException{
    //     jProductoDao.delete(id);
    // }

    // public void updateProduct(Producto producto) throws SQLException{
    //     jProductoDao.update(producto);
    // }

    // public List<String> findAlergenoByIdIngredient(int idIngrediente) throws SQLException{
    //     return jProductoDao.listaAlergenosIngrediente(idIngrediente);
    // }

    // public List<Ingrediente> findIngredientesByProducto(int idProduct) throws SQLException{
    //     return jProductoDao.findIngredientesProducto(idProduct);
    // }

    // public List<Producto>findAllProducts() throws SQLException{
    //     return jProductoDao.findAll();
    // }

    // public Producto findProductById(int idProducto) throws SQLException {
    //     return jProductoDao.findByID(idProducto);
    // }


}
