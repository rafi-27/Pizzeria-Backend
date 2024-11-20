package ies.thiar.controlador.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Utils.DatabaseConf;
import ies.thiar.controlador.dao.ClienteDao;

public class JDBCClienteDao implements ClienteDao {
    // ---------------------------------Sentencias
    // SQL---------------------------------//
    final String INSERT_CLIENTE = "INSERT INTO clientes (dni, nombre, direccion, telefono, email, password) values (?,?,?,?,?,?)";
    final String SELECT_EMAIL = "select id, dni, nombre, direccion, telefono, email, password where email=?";
    // ---------------------------------Sentencias
    // SQL---------------------------------//

    @Override
    public void insert(Cliente client) throws SQLException {
        try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO,
                DatabaseConf.PASSWORD);
                PreparedStatement pstmtCliente = conexion.prepareStatement(INSERT_CLIENTE,
                        Statement.RETURN_GENERATED_KEYS);) {

            pstmtCliente.setString(1, client.getDni());
            pstmtCliente.setString(2, client.getNombre());
            pstmtCliente.setString(3, client.getDireccion());
            pstmtCliente.setString(4, client.getTelefono());
            pstmtCliente.setString(5, client.getEmail());
            pstmtCliente.setString(6, client.getPassword());

            pstmtCliente.executeUpdate();

            try (ResultSet generatedKeys = pstmtCliente.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getInt(1));
                }
            }

        } catch (Exception e) {
            System.out.println("Error al insertar");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Cliente client) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Cliente client) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Cliente findByID(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Cliente findByEmail(String email) throws SQLException {
        try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO,
                DatabaseConf.PASSWORD);
                PreparedStatement pstmtCliente = conexion.prepareStatement(SELECT_EMAIL);) {
            pstmtCliente.setString(6, email);
            try (ResultSet rs = pstmtCliente.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("email"),
                            rs.getString("password"));
                    return cliente;
                }
            }
            return null;
        }
    }

    @Override
    public List<Cliente> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
