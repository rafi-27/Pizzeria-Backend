package ies.thiar.controlador.dao.imp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.LineaPedido;
import ies.thiar.Modelo.Pedido;
import ies.thiar.Utils.DatabaseConf;
import ies.thiar.controlador.dao.PedidoDao;

public class JDBCPedido implements PedidoDao {
    // ---------------------------------------------------------------------------------------------------------//
    final String INSERT_PEDIDO = "insert into pedidos (fecha, precio_total, estado, forma_pago, id_cliente) values (?,?,?,?,?)";

    final String DELETE_PEDIDO = "";

    // ---------------------------------------------------------------------------------------------------------//
    @Override
    public void insert(Pedido pedido) throws SQLException {
        try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO, DatabaseConf.PASSWORD);
        
            PreparedStatement pstmtPedido = conexion.prepareStatement(INSERT_PEDIDO, Statement.RETURN_GENERATED_KEYS);) {
                //.getFecha().getTime())
            pstmtPedido.setDate(1, new Date(pedido.getFecha().getTime()));
            pstmtPedido.setDouble(2, pedido.getPrecioTotal());
            pstmtPedido.setString(3, pedido.getEstado().toString());
            pstmtPedido.setString(4, pedido.getPago().toString());
            pstmtPedido.setInt(5, pedido.getCliente().getId());

            pstmtPedido.executeUpdate();

            try (ResultSet generatedKeys = pstmtPedido.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pedido.setId(generatedKeys.getInt(1));
                    System.out.println("SOUT: "+generatedKeys.getInt(1));
                }
            }

            insertLineaPedido(conexion, pedido.getLineaPedido(), pedido.getId());

        } catch (Exception e) {
            System.out.println("Error al insertar pedido");
            e.printStackTrace();
        }
    }

    // Hacemos el insert de la lineaDePedido:
    final String INSERT_LINEA_PEDIDO = "insert into linea_pedido (cantidad, id_producto, id_pedido) values (?,?,?)";

    public void insertLineaPedido(Connection conexion, List<LineaPedido> listaLineaPedidos, int idPedido) throws SQLException {
        PreparedStatement pstmtLineaPedido = conexion.prepareStatement(INSERT_LINEA_PEDIDO, Statement.RETURN_GENERATED_KEYS);

        for (LineaPedido lineaPedido : listaLineaPedidos) {
            pstmtLineaPedido.setInt(1, lineaPedido.getCantidad());
            pstmtLineaPedido.setInt(2, lineaPedido.getProduct().getId());
            pstmtLineaPedido.setInt(3, idPedido);

            pstmtLineaPedido.executeUpdate();

            try (ResultSet generatedKeys = pstmtLineaPedido.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    lineaPedido.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Pedido pedido) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Pedido findByID(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Pedido> obtenerPedidosByIdClient(int idCliente) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Pedido> obtenerPedidosByState(EstadoPedido state) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Pedido> obtenerLineasPedidosByIdPedido(int idPedido) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void agregarLineaPedido(LineaPedido lineaPedido, Pedido pedidoAsociado) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
