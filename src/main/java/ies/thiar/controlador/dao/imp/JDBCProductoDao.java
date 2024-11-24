package ies.thiar.controlador.dao.imp;

import java.sql.SQLException;
import java.util.List;

import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.Producto;
import ies.thiar.controlador.dao.ProductoDao;

public class JDBCProductoDao implements ProductoDao{

    @Override
    public void insert(Producto producto) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void update(Producto producto) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Producto findByID(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByID'");
    }

    @Override
    public List<Producto> findAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<Ingrediente> findIngredientesProducto(int idProd) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findIngredientesProducto'");
    }

    @Override
    public List<String> listaAlergenosIngrediente(int idIngre) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listaAlergenosIngrediente'");
    }
    







    
}
