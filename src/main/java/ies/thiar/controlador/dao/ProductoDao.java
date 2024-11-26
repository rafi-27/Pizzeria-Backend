package ies.thiar.controlador.dao;

import java.sql.SQLException;
import java.util.List;

import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.Producto;

public interface ProductoDao {
    public void insert(Producto producto) throws SQLException;
    public void delete(int id) throws SQLException;
    public void update(Producto producto) throws SQLException;
    public Producto findByID(int id) throws SQLException;

    public List<Producto> findAll() throws SQLException;
    public List<Ingrediente> findIngredientesProducto(int idProd) throws SQLException;
    public List<String> listaAlergenosIngrediente(int idIngre) throws SQLException;
    
}