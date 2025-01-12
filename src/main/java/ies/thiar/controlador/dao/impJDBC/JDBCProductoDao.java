// package ies.thiar.controlador.dao.impJDBC;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.ArrayList;
// import java.util.List;

// import ies.thiar.Modelo.Bebida;
// import ies.thiar.Modelo.Ingrediente;
// import ies.thiar.Modelo.Pasta;
// import ies.thiar.Modelo.Pizza;
// import ies.thiar.Modelo.Producto;
// import ies.thiar.Modelo.SIZE;
// import ies.thiar.Utils.DatabaseConf;
// import ies.thiar.controlador.dao.ProductoDao;

// public class JDBCProductoDao implements ProductoDao {
//     // Instrucciones
//     final String INSERT_PRODUCTO = "insert into productos (nombre, precio, tipo_Producto, tamaño) values (?,?,?,?)";
//     final String DELETE_PRODUCTO = "delete from productos where id=?";
//     final String UPDATE_PRODUCTO = "update productos set nombre=?, precio=?, tamaño=? where id=?";
//     final String SELECT_ALL_PRODUCTO = "select id, nombre, precio, tipo_Producto, tamaño from productos;";
//     final String SELECT_PRODUCTO = "select id, nombre, precio, tipo_Producto, tamaño from productos where id=?";


//     @Override
//     public void insert(Producto producto) throws SQLException {
//         Connection conexion = null;
//         try {
//             conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO, DatabaseConf.PASSWORD);
//             PreparedStatement pstmtProducto = conexion.prepareStatement(INSERT_PRODUCTO, Statement.RETURN_GENERATED_KEYS);
//             conexion.setAutoCommit(false);

//             pstmtProducto.setString(1, producto.getNombre());
//             pstmtProducto.setDouble(2, producto.getPrecio());
//             pstmtProducto.setString(3, producto.getTipoProducto().toString());
//             if (producto instanceof Pizza) {
//                 Pizza pizzita = (Pizza) producto;
//                 pstmtProducto.setString(4, pizzita.getTamanyo().toString());
//             } else if (producto instanceof Bebida) {
//                 Bebida bebidita = (Bebida) producto;
//                 pstmtProducto.setString(4, bebidita.getTamanyo().toString());
//             } else {
//                 pstmtProducto.setNull(4, 4);
//             }
//             pstmtProducto.executeUpdate();

//             try (ResultSet generatedKeys = pstmtProducto.getGeneratedKeys()) {
//                 if (generatedKeys.next()) {
//                     producto.setId(generatedKeys.getInt(1));
//                 }
//             }

//             if (producto instanceof Pizza) {
//                 Pizza pizzita = (Pizza) producto;
//                 saveIngrediente(conexion, pizzita.getListaIngredientesPizza(), producto.getId());
//             } else if (producto instanceof Pasta) {
//                 Pasta pastita = (Pasta) producto;
//                 saveIngrediente(conexion, pastita.getListaIngredientePasta(), producto.getId());
//             }

//             conexion.commit();
//         } catch (SQLException e) {
//             if (conexion != null){
//                 conexion.rollback();
//                 throw new SQLException("Error al guardar, "+e);
//             }
//             System.out.println("Error al insertar");
//         }finally{
//             if(conexion!=null){
//                 conexion.close();
//             }else{
//                 System.out.println("No se ha podido cerrar la conexion.");
//             }
//         }
//     }

//     @Override
//     public void delete(int id) throws SQLException {
//         try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO,
//                 DatabaseConf.PASSWORD);
//                 PreparedStatement pstmtProducto = conexion.prepareStatement(DELETE_PRODUCTO);) {
//             pstmtProducto.setInt(1, id);
//             pstmtProducto.executeUpdate();
//         } catch (Exception e) {
//             System.out.println("Error al borrar el producto con id: " + id);
//             e.printStackTrace();
//         }
//     }

//     @Override
//     public void update(Producto producto) throws SQLException {
//         try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO,
//                 DatabaseConf.PASSWORD);
//                 PreparedStatement pstmtCliente = conexion.prepareStatement(UPDATE_PRODUCTO);) {

//             pstmtCliente.setString(1, producto.getNombre());
//             pstmtCliente.setDouble(2, producto.getPrecio());
//             pstmtCliente.setString(3, producto.getTamanyo().toString());
//             pstmtCliente.setInt(4, producto.getId());
//             pstmtCliente.executeUpdate();
//         } catch (Exception e) {
//             System.out.println("Error al hacer update del producto");
//             e.printStackTrace();
//         }
//     }

//     @Override
//     public Producto findByID(int id) throws SQLException {
//         try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO, DatabaseConf.PASSWORD);
//                 PreparedStatement pstmtProducto = conexion.prepareStatement(SELECT_PRODUCTO);) {
//             pstmtProducto.setInt(1, id);
//             try (ResultSet rs = pstmtProducto.executeQuery()) {
//                 if (rs.next()) {
//                     if(rs.getString("tipo_Producto").equals("PIZZA")){
//                         Pizza pizzita = new Pizza(0, rs.getString("nombre"),rs.getDouble("precio"), SIZE.valueOf(rs.getString("tamaño")));
//                         pizzita.setListaIngredientesPizza(findIngredientesProducto(rs.getInt("id")));
//                         return pizzita;
//                     }else if(rs.getString("tipo_Producto").equals("PASTA")){
//                         Pasta pasta = new Pasta(0, rs.getString("nombre"),rs.getDouble("precio"));
//                         pasta.setListaIngredientePasta(findIngredientesProducto(rs.getInt("id")));
//                         return pasta;
//                     }else{
//                         Bebida bebida = new Bebida(0, rs.getString("nombre"),rs.getDouble("precio"), SIZE.valueOf(rs.getString("tamaño")));
//                         return bebida;
//                     }
//                 }
//             }
//         }
//         return null;
//     }

//     @Override
//     public List<Producto> findAll() throws SQLException {
//         List<Producto> listaDeProductosADevolver = new ArrayList<>();
//         try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO,
//                 DatabaseConf.PASSWORD);
//                 PreparedStatement pstmtProducto = conexion.prepareStatement(SELECT_ALL_PRODUCTO);) {
//             try (ResultSet rs = pstmtProducto.executeQuery()) {
//                 while (rs.next()) {
//                     if (rs.getString("tipo_Producto").equals("PIZZA")) {
//                         if (rs.getString("tamaño") != null) {
//                             Pizza pizza = new Pizza(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"),
//                                     SIZE.valueOf(rs.getString("tamaño")));
//                             pizza.setListaIngredientesPizza(findIngredientesProducto(rs.getInt("id")));
//                             listaDeProductosADevolver.add(pizza);
//                         }
//                     } else if (rs.getString("tipo_Producto").equals("PASTA")) {
//                         Pasta pasta = new Pasta(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
//                         pasta.setListaIngredientePasta(findIngredientesProducto(rs.getInt("id")));
//                         listaDeProductosADevolver.add(pasta);
//                     } else {
//                         if (rs.getString("tamaño") != null) {
//                             Bebida bebida = new Bebida(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"),
//                                     SIZE.valueOf(rs.getString("tamaño")));
//                             listaDeProductosADevolver.add(bebida);
//                         }
//                     }
//                 }
//             }
//         }
//         return listaDeProductosADevolver;
//     }

//     final String SELECT_INGREDIENTE_BY_PRODUCTO = "SELECT ingredientes.id, ingredientes.nombre, alergenos.nombre FROM ingredientes join productos_ingredientes on ingredientes.id=productos_ingredientes.id_Ingrediente join ingredientes_alergenos on ingredientes_alergenos.id_Ingrediente=productos_ingredientes.id_Ingrediente join alergenos on alergenos.id=ingredientes_alergenos.id_Alergenos where productos_ingredientes.id_producto=?;";

//     @Override
//     public List<Ingrediente> findIngredientesProducto(int idProd) throws SQLException {
//         List<Ingrediente> listaIngredientesADevolver = new ArrayList<>();
//         List<String> listaIngredientesYaProcesados = new ArrayList<>();

//         try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL,
//                 DatabaseConf.USUARIO,
//                 DatabaseConf.PASSWORD);
//                 PreparedStatement pstmtAlergen = conexion.prepareStatement(SELECT_INGREDIENTE_BY_PRODUCTO);) {
//             pstmtAlergen.setInt(1, idProd);

//             try (ResultSet rs = pstmtAlergen.executeQuery()) {
//                 while (rs.next()) {
//                     String nombre = rs.getString("nombre");
//                     if (!listaIngredientesYaProcesados.contains(nombre)) {
//                         Ingrediente ing = new Ingrediente(rs.getString("ingredientes.nombre"),
//                                 listaAlergenosIngrediente(rs.getInt("ingredientes.id")));
//                         listaIngredientesADevolver.add(ing);
//                         listaIngredientesYaProcesados.add(nombre);
//                     }
//                 }
//             }
//         }
//         return listaIngredientesADevolver;
//     }

//     final String SELECT_ALERGENOS_DE_INGREDIENT = "SELECT alergenos.nombre FROM alergenos join ingredientes_alergenos on alergenos.id=ingredientes_alergenos.id_Alergenos where ingredientes_alergenos.id_Ingrediente=?;";

//     @Override
//     public List<String> listaAlergenosIngrediente(int idIngre) throws SQLException {
//         List<String> listaAlergenosADevolver = new ArrayList<>();
//         try (Connection conexion = DriverManager.getConnection(DatabaseConf.URL, DatabaseConf.USUARIO,
//                 DatabaseConf.PASSWORD);
//                 PreparedStatement pstmtAlergen = conexion.prepareStatement(SELECT_ALERGENOS_DE_INGREDIENT);) {
//             pstmtAlergen.setInt(1, idIngre);
//             try (ResultSet rs = pstmtAlergen.executeQuery()) {
//                 while (rs.next()) {
//                     listaAlergenosADevolver.add(rs.getString("nombre"));
//                 }
//             }
//         }
//         return listaAlergenosADevolver;
//     }

//     // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
//     final String INSERT_INGREDIENTE = "insert into ingredientes (nombre) values (?)";
//     final String INSERT_INGREDIENTE_EXISTENTE_TABLAINTER = "insert into productos_ingredientes (id_producto, id_ingrediente) values (?,?)";

//     public void saveIngrediente(Connection conexion, List<Ingrediente> ingredientes, int id_producto)
//             throws SQLException {
//         PreparedStatement pstmtIngrediente = conexion.prepareStatement(INSERT_INGREDIENTE,
//                 Statement.RETURN_GENERATED_KEYS);

//         for (Ingrediente ingrediente : ingredientes) {
//             Ingrediente ingredienteAux = findByNameIngredient(conexion, ingrediente.getNombre());

//             if (ingredienteAux != null) {
//                 PreparedStatement pstmtIngrediente2 = conexion.prepareStatement(INSERT_INGREDIENTE_EXISTENTE_TABLAINTER,
//                         Statement.RETURN_GENERATED_KEYS);

//                 pstmtIngrediente2.setInt(1, id_producto);
//                 pstmtIngrediente2.setInt(2, ingredienteAux.getId());

//                 pstmtIngrediente2.executeUpdate();
//             } else {
//                 pstmtIngrediente.setString(1, ingrediente.getNombre());
//                 pstmtIngrediente.executeUpdate();

//                 try (ResultSet generatedKeys = pstmtIngrediente.getGeneratedKeys()) {
//                     if (generatedKeys.next()) {
//                         ingrediente.setId(generatedKeys.getInt(1));
//                     }
//                 }

//                 for (String alergen : ingrediente.getListaAlergenos()) {
//                     saveAlergeno(conexion, alergen, ingrediente.getId());
//                 }

//                 PreparedStatement pstmtIngrediente2 = conexion.prepareStatement(INSERT_INGREDIENTE_EXISTENTE_TABLAINTER,
//                         Statement.RETURN_GENERATED_KEYS);

//                 pstmtIngrediente2.setInt(1, id_producto);
//                 pstmtIngrediente2.setInt(2, ingrediente.getId());
//                 pstmtIngrediente2.executeUpdate();
//             }
//         }
//     }

//     final String SELECT_DEL_INGREDIENTE = "select ingredientes.id, ingredientes.nombre from ingredientes where ingredientes.nombre=?";

//     public Ingrediente findByNameIngredient(Connection conexion, String nombre) throws SQLException {
//         PreparedStatement pstmtIngrediente = conexion.prepareStatement(SELECT_DEL_INGREDIENTE,
//                 Statement.RETURN_GENERATED_KEYS);
//         pstmtIngrediente.setString(1, nombre);
//         try (ResultSet rs = pstmtIngrediente.executeQuery()) {
//             if (rs.next()) {
//                 Ingrediente ingrediente = new Ingrediente(
//                         rs.getInt("id"),
//                         rs.getString("nombre"));
//                 return ingrediente;
//             }
//         }
//         return null;
//     }

//     // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
//     final String SELECT_DEL_ALERGEN = "select alergenos.nombre from alergenos where alergenos.nombre=?";
//     final String INSERT_ALERGENO = "insert into alergenos (nombre) values (?)";
//     final String INSERT_ALERGEN_EXISTENTE = "insert into INGREDIENTES_ALERGENOS (id_Ingrediente, id_Alergenos) values (?,?)";

//     public void saveAlergeno(Connection conexion, String alergen, int id_ingrediente) throws SQLException {
//         String alergenAcomparar = findAlergen(conexion, alergen);
//         int id_Alergeno = -1;

//         if (alergenAcomparar == null) {
//             PreparedStatement pstmtIngrediente = conexion.prepareStatement(INSERT_ALERGENO,
//                     Statement.RETURN_GENERATED_KEYS);
//             pstmtIngrediente.setString(1, alergen);
//             pstmtIngrediente.executeUpdate();

//             try (ResultSet generatedKeys = pstmtIngrediente.getGeneratedKeys()) {
//                 if (generatedKeys.next()) {
//                     id_Alergeno = generatedKeys.getInt(1);
//                 }
//             }
//         } else {
//             PreparedStatement pstmtAlergeno = conexion.prepareStatement("SELECT id FROM alergenos WHERE nombre = ?");
//             pstmtAlergeno.setString(1, alergen);

//             try (ResultSet rs = pstmtAlergeno.executeQuery()) {
//                 if (rs.next()) {
//                     id_Alergeno = rs.getInt("id");
//                 }
//             }
//         }

//         if (id_Alergeno > 0) {
//             PreparedStatement pstmtIngredienteTablaIntermedia = conexion.prepareStatement(INSERT_ALERGEN_EXISTENTE);
//             pstmtIngredienteTablaIntermedia.setInt(1, id_ingrediente);
//             pstmtIngredienteTablaIntermedia.setInt(2, id_Alergeno);
//             pstmtIngredienteTablaIntermedia.executeUpdate();
//         }
//     }

//     public String findAlergen(Connection conexion, String alergen) throws SQLException {
//         String alergenADevolver;
//         PreparedStatement pstmtIngrediente = conexion.prepareStatement(SELECT_DEL_ALERGEN,
//                 Statement.RETURN_GENERATED_KEYS);
//         pstmtIngrediente.setString(1, alergen);

//         try (ResultSet rs = pstmtIngrediente.executeQuery()) {
//             if (rs.next()) {
//                 alergenADevolver = rs.getString("nombre");
//                 return alergenADevolver;
//             }
//         }
//         return null;
//     }
// }
