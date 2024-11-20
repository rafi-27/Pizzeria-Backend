package ies.thiar.controlador.dao;

import java.sql.SQLException;
import java.util.List;

import ies.thiar.Modelo.Cliente;

public interface ClienteDao {
    //CRUD
    public void insert(Cliente client) throws SQLException;
    public void delete(Cliente client) throws SQLException;
    public void update(Cliente client) throws SQLException;
    public Cliente findByID(int id) throws SQLException;
    public Cliente findByEmail(String email) throws SQLException;
    public List<Cliente> findAll() throws SQLException;
}
