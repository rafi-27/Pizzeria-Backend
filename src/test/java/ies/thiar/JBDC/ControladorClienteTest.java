package ies.thiar.JBDC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.LineaPedido;
import ies.thiar.Modelo.Pedido;
import ies.thiar.Modelo.Pizza;
import ies.thiar.Modelo.Producto;
import ies.thiar.Modelo.SIZE;
import ies.thiar.Utils.DatabaseConf;
import ies.thiar.controlador.ControladorCliente;
import ies.thiar.controlador.ControladorPedido;

public class ControladorClienteTest {

    // Creando el controlador
    ControladorCliente controladorCliente = new ControladorCliente();

    @BeforeEach
    void setUp() throws SQLException {
        // Configuración de la base de datos antes de cada test.
        DatabaseConf.createTables();
        controladorCliente = new ControladorCliente();
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
            // Realizar login con los datos correctos
            Cliente clienteLogin = controladorCliente.clienteLogin("ruben@gmail.com", "1234");
            assertNotNull(clienteLogin); // Aseguramos que el cliente se haya encontrado
            assertEquals("ruben@gmail.com", clienteLogin.getEmail()); // Comprobamos que los datos coinciden
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
            Cliente cliente = new Cliente("1357908642E", "Jose manolo", "Calle Ruben Garcia", "625478654",
                    "jose@gmail.com", "1234");
            controladorCliente.registrarCliente(cliente); // Registrar el cliente para la prueba
            assertThrows(IllegalArgumentException.class,
                    () -> controladorCliente.clienteLogin("jose@gmail.com", "incorrecta"));
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown");
        }
    }

    @Test
    public void testCreacionYInsercionPedidos() throws SQLException{
        //Cliente ruben = new Cliente("123456789Q", "Ruben Garcia", "Calle Ruben Garcia", "625478654", "ruben@gmail.com", "1234");
        System.out.println("-----------------------------------------------------Nos logeamos------------------------------------------------------");
            //Cliente rubenLogin = controladorCliente.clienteLogin("ruben@gmail.com", "1234");

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