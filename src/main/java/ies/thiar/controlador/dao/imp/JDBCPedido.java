package ies.thiar.controlador.dao.imp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.LineaPedido;
import ies.thiar.Modelo.Pagable;
import ies.thiar.Modelo.PagarEfectivo;
import ies.thiar.Modelo.PagarTarjeta;
import ies.thiar.Modelo.Pedido;
import ies.thiar.Utils.DatabaseConf;
import ies.thiar.controlador.ControladorProducto;
import ies.thiar.controlador.dao.PedidoDao;

public class JDBCPedido implements PedidoDao {
    // ---------------------------------------------------------------------------------------------------------//
    final String INSERT_PEDIDO = "insert into pedidos (fecha, precio_total, estado, forma_pago, id_cliente) values (?,?,?,?,?)";
    final String SELECT_BY_ID = "select  id, fecha, precio_total, estado, forma_pago, id_cliente from pedidos where id=?";

    final String DELETE_PEDIDO = "delete from pedidos where id=?";
    final String UPDATE_PEDIDO = "UPDATE pedidos SET fecha=?, precio_total=?, estado=?, forma_pago=? WHERE id=?";
    final String SELECT_BY_ID_PEDIDO = "select cantidad, id_producto, id_pedido from linea_pedido where id_pedido=?";
    final String SELECT_BY_STATE = "select fecha, precio_total, forma_pago, id_cliente where estado=?";
    String FIND_PEDIDO_BY_ID_CLIENTE = "select  id, fecha, precio_total, estado, forma_pago, id_cliente from pedidos where id_cliente=?";
    // Hacemos el insert de la lineaDePedido:
    final String INSERT_LINEA_PEDIDO = "insert into linea_pedido (cantidad, id_producto, id_pedido) values (?,?,?)";

    // ---------------------------------------------------------------------------------------------------------//
    @Override
    public void insert(Pedido pedido) throws SQLException {
        try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO,
                DatabaseConf.PASSWORD);

                PreparedStatement pstmtPedido = conexion.prepareStatement(INSERT_PEDIDO,
                        Statement.RETURN_GENERATED_KEYS);) {
            // .getFecha().getTime())
            pstmtPedido.setDate(1, new Date(pedido.getFecha().getTime()));
            pstmtPedido.setDouble(2, pedido.getPrecioTotal());
            pstmtPedido.setString(3, pedido.getEstado().toString());
            
            if (pedido.getPago() == null) {
                pstmtPedido.setNull(4, 4);
            }else if (pedido.getMetodoPagoCeroOuno()==0) {
                pstmtPedido.setString(4, "TARJETA");
            }else{
                pstmtPedido.setString(4, "EFECTIVO");
            }
            pstmtPedido.setInt(5, pedido.getCliente());

            pstmtPedido.executeUpdate();

            try (ResultSet generatedKeys = pstmtPedido.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pedido.setId(generatedKeys.getInt(1));
                    System.out.println("SOUT: " + generatedKeys.getInt(1));
                }
            }
            agregarLineaPedido(conexion, pedido.getLineaPedido(), pedido.getId());
            System.out.println("echo");
        } catch (Exception e) {
            System.out.println("Error al insertar pedido");
            e.printStackTrace();
        }
    }

    
    @Override
    public void agregarLineaPedido(Connection conexion, List<LineaPedido> listaLineaPedidos, int idPedido)
            throws SQLException {
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
        try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO,
                DatabaseConf.PASSWORD);
                PreparedStatement pstmtCliente = conexion.prepareStatement(DELETE_PEDIDO);) {
            pstmtCliente.setInt(1, id);
            pstmtCliente.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al borrar");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Pedido pedido) throws SQLException {
        try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO,
                DatabaseConf.PASSWORD);
                PreparedStatement pstmtPedido = conexion.prepareStatement(UPDATE_PEDIDO);) {

                    pstmtPedido.setDate(1, new Date(pedido.getFecha().getTime()));
                    pstmtPedido.setDouble(2, pedido.getPrecioTotal());
                    pstmtPedido.setString(3, pedido.getEstado().toString());
            if (pedido.getPago() == null) {
                pstmtPedido.setNull(4, 4);
            }else if (pedido.getMetodoPagoCeroOuno()==0) {
                pstmtPedido.setString(4, "TARJETA");
            }else{
                pstmtPedido.setString(4, "EFECTIVO");
            }
            pstmtPedido.setInt(5, pedido.getId());

            pstmtPedido.executeUpdate();
            agregarLineaPedido(conexion, pedido.getLineaPedido(), pedido.getId());
        } catch (Exception e) {
            System.out.println("Error al hacer update del producto");
            e.printStackTrace();
        }
    }

    @Override
    public Pedido findByID(int id) throws SQLException {
        try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO,
                DatabaseConf.PASSWORD);
                PreparedStatement pstmtCliente = conexion.prepareStatement(SELECT_BY_ID);) {
            pstmtCliente.setInt(1, id);
            Pedido pedido;
            Pagable pago = null;
            try (ResultSet rs = pstmtCliente.executeQuery()) {
                if (rs.next()) {
                    String formaPago = rs.getString("forma_pago");
                    if (formaPago != null) {
                        if (formaPago.equals("0")) {
                            pago = new PagarTarjeta();
                        } else {
                            pago = new PagarEfectivo();
                        }
                    }
                    pedido = new Pedido(
                            rs.getInt("id"),
                            rs.getDate("fecha"),
                            rs.getDouble("precio_total"),
                            EstadoPedido.valueOf(rs.getString("estado")),
                            pago,
                            rs.getInt("id_cliente"));
                    return pedido;
                }
            }
            return null;
        }
    }

    @Override
    public List<Pedido> obtenerPedidosByIdClient(int idCliente) throws SQLException {
        List<Pedido>listaPedidosAdevolver = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO, DatabaseConf.PASSWORD);
            PreparedStatement pstmtAlergen = conexion.prepareStatement(SELECT_BY_STATE);) {
            pstmtAlergen.setInt(1, idCliente);

            Pagable pago = null;
            try (ResultSet rs = pstmtAlergen.executeQuery()) {
                while (rs.next()) {
                    String formaPago = rs.getString("forma_pago");
                    if (formaPago != null) {
                        if (formaPago.equals("0")) {
                            pago = new PagarTarjeta();
                        } else {
                            pago = new PagarEfectivo();
                        }
                    }
                    Pedido pedido = new Pedido(rs.getDate("fecha"),rs.getDouble("precio_total"),EstadoPedido.valueOf(rs.getString("estado")),pago,rs.getInt("id_cliente"));
                    listaPedidosAdevolver.add(pedido);
                }
            }
        } 
        return listaPedidosAdevolver;
    }

    @Override
    public List<Pedido> obtenerPedidosByState(EstadoPedido state) throws SQLException {
         List<Pedido>listaPedidosAdevolver = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO, DatabaseConf.PASSWORD);
            PreparedStatement pstmtAlergen = conexion.prepareStatement(SELECT_BY_STATE);) {
            pstmtAlergen.setString(1, state.toString());
            Pagable pago = null;
            try (ResultSet rs = pstmtAlergen.executeQuery()) {
                while (rs.next()) {
                    String formaPago = rs.getString("forma_pago");
                    if (formaPago != null) {
                        if (formaPago.equals("0")) {
                            pago = new PagarTarjeta();
                        } else {
                            pago = new PagarEfectivo();
                        }
                    }
                    Pedido pedido = new Pedido(rs.getDate("fecha"),rs.getDouble("precio_total"),EstadoPedido.valueOf(rs.getString("estado")),pago,rs.getInt("id_cliente"));
                    listaPedidosAdevolver.add(pedido);
                }
            }
        } 
        return listaPedidosAdevolver;
    }

    @Override
    public List<LineaPedido> obtenerLineasPedidosByIdPedido(int idPedido) throws SQLException {
        List<LineaPedido>listaLineaPedidosAdevolver = new ArrayList<>();
        ControladorProducto controladorProducto = new ControladorProducto();
        try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL,
                DatabaseConf.USUARIO,
                DatabaseConf.PASSWORD);
                PreparedStatement pstmtAlergen = conexion.prepareStatement(SELECT_BY_ID_PEDIDO);) {
            pstmtAlergen.setInt(1, idPedido);

            try (ResultSet rs = pstmtAlergen.executeQuery()) {
                while (rs.next()) {
                    LineaPedido lineaPedido = new LineaPedido(rs.getInt(1),controladorProducto.findProductById(rs.getInt(2)), findByID(rs.getInt(3)));
                    listaLineaPedidosAdevolver.add(lineaPedido);
                }
            }
        }
        return listaLineaPedidosAdevolver;
    }

    @Override
    public Pedido findPedidoByIdClient(int idCliente) throws SQLException{
        try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO,
                DatabaseConf.PASSWORD);
                PreparedStatement pstmtCliente = conexion.prepareStatement(FIND_PEDIDO_BY_ID_CLIENTE);) {
            pstmtCliente.setInt(1, idCliente);
            Pedido pedido;
            Pagable pago = null;
            try (ResultSet rs = pstmtCliente.executeQuery()) {
                if (rs.next()) {
                    String formaPago = rs.getString("forma_pago");
                    if (formaPago != null) {
                        if (formaPago.equals("0")) {
                            pago = new PagarTarjeta();
                        } else {
                            pago = new PagarEfectivo();
                        }
                    }
                    pedido = new Pedido(
                            rs.getInt("id"),
                            rs.getDate("fecha"),
                            rs.getDouble("precio_total"),
                            EstadoPedido.valueOf(rs.getString("estado")),
                            pago,
                            rs.getInt("id_cliente"));
                    return pedido;
                }
            }
            return null;
        }
    }
}
