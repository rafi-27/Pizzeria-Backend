package ies.thiar.controlador;

import java.sql.SQLException;
import java.util.List;

import ies.thiar.Modelo.Cliente;
import ies.thiar.controlador.dao.ClienteDao;
import ies.thiar.controlador.dao.imp.JDBCClienteDao;

public class ControladorCliente {
    ClienteDao jClienteDao = new JDBCClienteDao();

    public void registrarCliente(Cliente cliente) throws SQLException{
        Cliente client = jClienteDao.findByEmail(cliente.getEmail());
        System.out.println(client);
        if(client!=null){throw new IllegalArgumentException("Usuario ya registrado con anterioridad");}
        else {jClienteDao.insert(cliente);}
    }

    public List<Cliente> selectAll() throws SQLException{
        return jClienteDao.findAll();
    }









}