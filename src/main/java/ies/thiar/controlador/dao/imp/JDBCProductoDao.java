package ies.thiar.controlador.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.Pasta;
import ies.thiar.Modelo.Pizza;
import ies.thiar.Modelo.Producto;
import ies.thiar.Utils.DatabaseConf;
import ies.thiar.controlador.dao.ProductoDao;

public class JDBCProductoDao implements ProductoDao {
    // Instrucciones
    final String INSERT_PRODUCTO = "insert into productos (nombre, precio, tipo_Producto) values (?,?,?)";

    @Override
    public void insert(Producto producto) throws SQLException {
        try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO,
                DatabaseConf.PASSWORD);
                PreparedStatement pstmtCliente = conexion.prepareStatement(INSERT_PRODUCTO,
                        Statement.RETURN_GENERATED_KEYS);) {

            pstmtCliente.setString(1, producto.getNombre());
            pstmtCliente.setDouble(2, producto.getPrecio());
            pstmtCliente.setString(3, producto.getTipoProducto().toString());

            if (producto instanceof Pizza) {
                Pizza pizzita = (Pizza) producto;
                saveIngrediente(conexion, pizzita.getListaIngredientesPizza(), producto.getId());
            } else if (producto instanceof Pasta) {
                Pasta pastita = (Pasta) producto;
                saveIngrediente(conexion, pastita.getListaIngredientePasta(), producto.getId());
            }
            // else{

            // }

            pstmtCliente.executeUpdate();

            try (ResultSet generatedKeys = pstmtCliente.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    producto.setId(generatedKeys.getInt(1));
                }
            }

        } catch (Exception e) {
            System.out.println("Error al insertar");
            e.printStackTrace();
        }
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

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    final String INSERT_INGREDIENTE = "insert into ingredientes (nombre) values (?)";
    final String INSERT_INGREDIENTE_EXISTENTE_TABLAINTER = "insert into productos_ingredientes (id_producto, id_ingrediente) values (?,?)";

    public void saveIngrediente(Connection conexion, List<Ingrediente> ingredientes, int id_producto)
            throws SQLException {
        PreparedStatement pstmtIngrediente = conexion.prepareStatement(INSERT_INGREDIENTE,
                Statement.RETURN_GENERATED_KEYS);

        for (Ingrediente ingrediente : ingredientes) {
            Ingrediente ingredienteAux = findByNameIngredient(conexion, ingrediente.getNombre());

            if (ingredienteAux != null) {
                PreparedStatement pstmtIngrediente2 = conexion.prepareStatement(INSERT_INGREDIENTE_EXISTENTE_TABLAINTER,
                        Statement.RETURN_GENERATED_KEYS);

                pstmtIngrediente2.setInt(1, ingrediente.getId());
                pstmtIngrediente2.setInt(2, id_producto);

                pstmtIngrediente2.executeUpdate();
                continue;

            } else {
                pstmtIngrediente.setString(1, ingrediente.getNombre());
                for (String alergen : ingrediente.getListaAlergenos()) {
                    saveAlergeno(conexion, alergen, ingrediente.getId());
                }
            }
        }

        try (ResultSet generatedKeys = pstmtIngrediente.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                for (Ingrediente ingrediente : ingredientes) {
                    ingrediente.setId(generatedKeys.getInt(1));
                }
                // ingredientes.setId(generatedKeys.getInt(1));
            }
        }
    }

    final String SELECT_DEL_INGREDIENTE = "select ingredientes.nombre from ingredientes where ingredientes.nombre=?";

    public Ingrediente findByNameIngredient(Connection conexion, String nombre) throws SQLException {
        PreparedStatement pstmtIngrediente = conexion.prepareStatement(SELECT_DEL_INGREDIENTE,
                Statement.RETURN_GENERATED_KEYS);
        pstmtIngrediente.setString(1, nombre);
        try (ResultSet rs = pstmtIngrediente.executeQuery()) {
            if (rs.next()) {
                Ingrediente ingrediente = new Ingrediente(
                        rs.getInt("id"),
                        rs.getString("nombre"));
                return ingrediente;
            }
        }
        return null;
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    final String SELECT_DEL_ALERGEN = "select alergenos.nombre from alergenos where alergenos.nombre=?";

    final String INSERT_ALERGENO = "insert into alergenos (nombre) values (?)";

    final String INSERT_ALERGEN_EXISTENTE = "insert into INGREDIENTES_ALERGENOS (id_Ingrediente, id_Alergenos) values (?,?)";

    public void saveAlergeno(Connection conexion, String alergen, int id_ingrediente) throws SQLException {
        String alergenAcomparar = findAlergen(conexion, alergen);
        PreparedStatement pstmtIngrediente = conexion.prepareStatement(INSERT_ALERGENO, Statement.RETURN_GENERATED_KEYS);

        if (alergenAcomparar == null) {
            pstmtIngrediente.setString(1, alergen);
            pstmtIngrediente.executeUpdate();
        }

        int id_Alergeno = -1;
        try (ResultSet generatedKeys = pstmtIngrediente.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id_Alergeno = generatedKeys.getInt(1);
            }
        }

        System.out.println("Alergeno ya creado.");
        System.out.println("ID: " + id_ingrediente);
        PreparedStatement pstmtIngredienteTablaIntermedia = conexion.prepareStatement(INSERT_ALERGEN_EXISTENTE, Statement.RETURN_GENERATED_KEYS);

        pstmtIngredienteTablaIntermedia.setInt(1, id_ingrediente);
        pstmtIngredienteTablaIntermedia.setInt(2, id_Alergeno);
        pstmtIngredienteTablaIntermedia.executeUpdate();

    }

    public String findAlergen(Connection conexion, String alergen) throws SQLException {
        String alergenADevolver;
        PreparedStatement pstmtIngrediente = conexion.prepareStatement(SELECT_DEL_ALERGEN,
                Statement.RETURN_GENERATED_KEYS);
        pstmtIngrediente.setString(1, alergen);

        try (ResultSet rs = pstmtIngrediente.executeQuery()) {
            if (rs.next()) {
                alergenADevolver = rs.getString("nombre");
                return alergenADevolver;
            }
        }
        return null;
    }
}
