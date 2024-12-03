package ies.thiar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.FormaPago;
import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.LineaPedido;
import ies.thiar.Modelo.Pasta;
import ies.thiar.Modelo.Pedido;
import ies.thiar.Modelo.Pizza;
import ies.thiar.Modelo.Producto;
import ies.thiar.Modelo.SIZE;
import ies.thiar.Utils.DatabaseConf;
import ies.thiar.controlador.ControladorCliente;
import ies.thiar.controlador.ControladorPedido;
import ies.thiar.controlador.ControladorProducto;

public class ControladorClienteTest {

    // Creando el controlador
    ControladorCliente controladorCliente = new ControladorCliente();
    ControladorProducto controladorProducto = new ControladorProducto();

    @BeforeEach
    void setUp() throws SQLException {
        // Configuración de la base de datos antes de cada test.
        DatabaseConf.createTables();
        controladorCliente = new ControladorCliente();
        controladorProducto = new ControladorProducto();
    }
    //--------------------------------------------------------------TestProductos---------------------------------------------------------

    @Test
    public void testInsertarProductosConIngredientes() throws SQLException {
        // Creamos algunos ingredientes
        List<Ingrediente> listaIngredientesPizza = new ArrayList<>() {
            {
                add(new Ingrediente(1, "Tomate", List.of("Leche", "Huevos", "Kiwi", "Cereales")));
                add(new Ingrediente(2, "Queso Mozzarella", List.of("Huevos", "Cipote")));
                add(new Ingrediente(3, "Pepperoni", List.of("Mani")));
                add(new Ingrediente(4, "Aceitunas negras", List.of("Mariscos")));
                add(new Ingrediente(5, "Albahaca fresca", List.of("Trigo")));
                add(new Ingrediente(6, "Champiñones", List.of("Soja")));
            }
        };

        // Crear el producto tipo Pizza
        Producto pizzaPrueba = new Pizza(1, "Pizza kebab", 10, SIZE.GRANDE, listaIngredientesPizza);

        // Insertar el producto en la base de datos
        controladorProducto.insertProducto(pizzaPrueba);

        // Verificamos que el producto haya sido insertado
        Producto productoRecuperadoPizza = controladorProducto.findProductById(1);
        assertNotNull(productoRecuperadoPizza, "El producto debería ser recuperado");
        assertEquals("Pizza kebab", productoRecuperadoPizza.getNombre(), "El nombre del producto debería ser el esperado");

        // Verificamos los ingredientes asociados al producto de pizza
        List<Ingrediente> ingredientesPizza = controladorProducto.findIngredientesByProducto(1);
        assertEquals(6, ingredientesPizza.size(), "El número de ingredientes debería ser 6 para la pizza");
        ingredientesPizza.forEach(ingrediente -> {
            assertNotNull(ingrediente.getListaAlergenos(), "Cada ingrediente debería tener alergenos");
            assertFalse(ingrediente.getListaAlergenos().isEmpty(), "El ingrediente no debería tener una lista vacía de alergenos");
        });

        // Creamos ingredientes para otro producto (pasta)
        List<Ingrediente> listaIngredientesPasta = new ArrayList<>() {
            {
                add(new Ingrediente(1, "Harina de trigo", List.of("gluten")));
                add(new Ingrediente(2, "Huevos frescos", List.of("huevo")));
                add(new Ingrediente(3, "Queso parmesano", List.of("lácteos", "huevo")));
                add(new Ingrediente(4, "Nueces trituradas", List.of("frutos secos")));
                add(new Ingrediente(5, "Camarones", List.of("mariscos")));
                add(new Ingrediente(6, "Salsa de soya", List.of("soya")));
            }
        };

        // Crear el producto tipo Pasta
        Producto pastaPrueba = new Pasta(2, "Pasta carbonara", 15, listaIngredientesPasta);

        // Insertar el producto en la base de datos
        controladorProducto.insertProducto(pastaPrueba);

        // Verificamos que el producto haya sido insertado
        Producto productoRecuperadoPasta = controladorProducto.findProductById(2);
        assertNotNull(productoRecuperadoPasta, "El producto debería ser recuperado");
        assertEquals("Pasta carbonara", productoRecuperadoPasta.getNombre(), "El nombre del producto debería ser el esperado");

        // Verificamos los ingredientes asociados al producto de pasta
        List<Ingrediente> ingredientesPasta = controladorProducto.findIngredientesByProducto(2);
        assertEquals(6, ingredientesPasta.size(), "El número de ingredientes debería ser 6 para la pasta");
        ingredientesPasta.forEach(ingrediente -> {
            assertNotNull(ingrediente.getListaAlergenos(), "Cada ingrediente debería tener alergenos");
            assertFalse(ingrediente.getListaAlergenos().isEmpty(), "El ingrediente no debería tener una lista vacía de alergenos");
        });
    }

    
    //-------------------------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void testSelectByIDCorrecto() {
        try {
            // Crear un cliente
            Cliente cliente = new Cliente("123456789Q", "Ruben Garcia", "Calle Ruben Garcia", "625478654",
                    "ruben@gmail.com", "1234");

            // Registrar al cliente
            controladorCliente.registrarCliente(cliente);

            // Probar la búsqueda por ID (suponiendo que el cliente tiene ID 1 después de la
            // inserción)
            Cliente clienteEncontrado = controladorCliente.selectByID(1);
            assertNotNull(clienteEncontrado); // Aseguramos que el cliente no sea null
            assertEquals(cliente.getEmail(), clienteEncontrado.getEmail()); // Validamos que los datos coincidan
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }
    }

    @Test
    public void testSelectByIDIncorrecto() {
        try {
            // Probar buscando un cliente que no existe
            Cliente clienteNoExistente = controladorCliente.selectByID(99);
            assertNull(clienteNoExistente); // Aseguramos que no se encuentre ningún cliente
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }
    }

    // Login---------------------------------------------------------------
    @Test
    public void testLoginCorrecto() {
        try {
            // Crear un cliente
            Cliente cliente = new Cliente("123456789Q", "Ruben Garcia", "Calle Ruben Garcia", "625478654",
                    "ruben@gmail.com", "1234");

            // Registrar al cliente
            controladorCliente.registrarCliente(cliente);

            // Realizar login con los datos correctos
            Cliente clienteLogin = controladorCliente.clienteLogin("ruben@gmail.com", "1234");
            assertNotNull(clienteLogin); // Aseguramos que el cliente se haya encontrado
            assertEquals(cliente.getEmail(), clienteLogin.getEmail()); // Comprobamos que los datos coinciden
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }
    }

    @Test
    public void testLoginIncorrecto() {
        try {
            // Intentamos hacer login con un email no registrado
            assertThrows(IllegalArgumentException.class,
                    () -> controladorCliente.clienteLogin("noexiste@gmail.com", "1234"));

            // Intentamos hacer login con un email correcto pero contraseña incorrecta
            Cliente cliente = new Cliente("123456789Q", "Ruben Garcia", "Calle Ruben Garcia", "625478654",
                    "ruben@gmail.com", "1234");
            controladorCliente.registrarCliente(cliente); // Registrar el cliente para la prueba
            assertThrows(IllegalArgumentException.class,
                    () -> controladorCliente.clienteLogin("ruben@gmail.com", "incorrecta"));
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }
    }

    @Test
    public void testCreacionYInsercionPedidos() throws SQLException{
        Cliente ruben = new Cliente("123456789Q", "Ruben Garcia", "Calle Ruben Garcia", "625478654", "ruben@gmail.com", "1234");
        System.out.println("-----------------------------------------------------Nos logeamos------------------------------------------------------");
            Cliente rubenLogin = controladorCliente.clienteLogin("ruben@gmail.com", "1234");

            Pedido pedido = new Pedido(1);
            
            List<Ingrediente>listaIngredientes = new ArrayList<>(){
                {
                    add(new Ingrediente(1, "Tomate", List.of("Leche","Huevos","Kiwi","Cereales")));
                    add(new Ingrediente(2, "Queso Mozzarella", List.of("Huevos", "Cipote")));
                    add(new Ingrediente(3, "Pepperoni", List.of("Mani")));
                    add(new Ingrediente(4, "Aceitunas negras", List.of("Mariscos")));
                    add(new Ingrediente(5, "Albahaca fresca", List.of("Trigo")));
                    add(new Ingrediente(6, "Champiñones", List.of("Soja")));
                }
            };
            Producto pizzaPrueba = new Pizza(1, "Pizza kebab", 10, SIZE.GRANDE, listaIngredientes);


            List<LineaPedido>listaLineaPedidos = new ArrayList<>(){{
                add(new LineaPedido(1, 3, pizzaPrueba, pedido));
                add(new LineaPedido(2, 2, pizzaPrueba, pedido));
            }};

            pedido.setLineaPedido(listaLineaPedidos);
            //pedido.setPago(FormaPago.EFECTIVO);

            ControladorPedido controladorPedido = new ControladorPedido();
            controladorPedido.insertPedido(pedido);
    }
}