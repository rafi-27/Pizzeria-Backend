package ies.thiar.controlador;

import java.sql.SQLException;
import java.util.List;

import ies.thiar.Modelo.Cliente;
import ies.thiar.controlador.dao.ClienteDao;
import ies.thiar.controlador.dao.imp.JDBCClienteDao;

public class ControladorCliente {
    ClienteDao jClienteDao = new JDBCClienteDao();

    public void registrarCliente(Cliente cliente) throws SQLException {
        Cliente client = jClienteDao.findByEmail(cliente.getEmail());

        if (client != null) throw new IllegalArgumentException("Usuario ya registrado con anterioridad");
        jClienteDao.insert(cliente);
    }

    public List<Cliente> selectAll() throws SQLException {
        return jClienteDao.findAll();
    }

    public void deleteClient(int id) throws SQLException {
        jClienteDao.delete(id);
    }

    public Cliente selectByID(int id) throws SQLException {
        return jClienteDao.findByID(id);
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        jClienteDao.update(cliente);
    }

    public Cliente clienteLogin(String email, String password) throws SQLException {
        Cliente client = jClienteDao.findByEmail(email);

        if (client == null) throw new IllegalArgumentException("Usuario no encontrado");

        if (client.getPassword().equals(password)) return client;
        throw new IllegalArgumentException("Contraseña incorrecta");
    }
}