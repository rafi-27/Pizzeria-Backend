package ies.thiar;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.LineaPedido;
import ies.thiar.Modelo.Pasta;
import ies.thiar.Modelo.Pedido;
import ies.thiar.Modelo.Producto;
import ies.thiar.controlador.ControladorPedido;

public class ControladorPedidoTestJPA {
    ControladorPedido controladorPedido;

    @BeforeEach
    void setUp() {
        controladorPedido = new ControladorPedido();
    }

    @Test
    public void registrarPedido() throws SQLException {
        Cliente cliente = new Cliente("12345678O", "Rodolfo Tebas", "calle dos santos averia", "122312321", "email@gmail.com", "1234");
        Pedido pedido = new Pedido(cliente);

        List<Ingrediente> listaIngredientes2 = new ArrayList<>() {
            {
                add(new Ingrediente("Harina de trigo", List.of("gluten")));
                add(new Ingrediente("Huevos frescos", List.of("huevo")));
                add(new Ingrediente("Queso parmesano", List.of("lácteos", "huevo")));
                add(new Ingrediente("Nueces trituradas", List.of("frutos secos")));
                add(new Ingrediente("Camarones", List.of("mariscos")));
                add(new Ingrediente("Salsa de soya", List.of("soya")));
            }
        };

        Producto pastaPrueba = new Pasta("Pasta carbonara", 15, listaIngredientes2);
        Producto pizzaPrueba = new Pasta("Pizza Kebab", 10, listaIngredientes2);

        List<LineaPedido> listaLineaPedidosJose = new ArrayList<>() {
            {
                add(new LineaPedido(4, pastaPrueba, pedido));
                add(new LineaPedido(5, pizzaPrueba, pedido));
            }
        };

        for (LineaPedido lineaPedido : listaLineaPedidosJose) {
            pedido.addLineaPedido(lineaPedido);
        }

        controladorPedido.insertPedido(pedido);

        Pedido pedidoPersistido = controladorPedido.findPedidoById(pedido.getId());
        assertNotNull(pedidoPersistido);
        assertEquals(2, pedidoPersistido.getLineaPedido().size());
    }


    @Test
    public void testBorrar() throws SQLException {
        Cliente cliente = new Cliente("12345678O", "Rodolfo Tebas", "calle dos santos averia", "122312321", "email@gmail.com", "1234");
        Pedido pedido = new Pedido(cliente);

        List<Ingrediente> listaIngredientes2 = new ArrayList<>() {
            {
                add(new Ingrediente("Harina de trigo", List.of("gluten")));
                add(new Ingrediente("Huevos frescos", List.of("huevo")));
                add(new Ingrediente("Queso parmesano", List.of("lácteos", "huevo")));
                add(new Ingrediente("Nueces trituradas", List.of("frutos secos")));
                add(new Ingrediente("Camarones", List.of("mariscos")));
                add(new Ingrediente("Salsa de soya", List.of("soya")));
            }
        };

        Producto pastaPrueba = new Pasta("Pasta carbonara", 15, listaIngredientes2);
        Producto pizzaPrueba = new Pasta("Pizza Kebab", 10, listaIngredientes2);

        List<LineaPedido> listaLineaPedidosJose = new ArrayList<>() {
            {
                add(new LineaPedido(4, pastaPrueba, pedido));
                add(new LineaPedido(5, pizzaPrueba, pedido));
            }
        };

        for (LineaPedido lineaPedido : listaLineaPedidosJose) {
            pedido.addLineaPedido(lineaPedido);
        }

        controladorPedido.insertPedido(pedido);
        controladorPedido.deletePedido(pedido.getId());

        //Aqui ya hago aparte del test de registrar el test de findPedidoById
        Pedido pedidoPersistido = controladorPedido.findPedidoById(pedido.getId());
        assertEquals(null, pedidoPersistido);
    }

    @Test
    public void testActualizar() throws SQLException {
        Cliente cliente = new Cliente("12345678O", "Rodolfo Tebas", "calle dos santos averia", "122312321", "email@gmail.com", "1234");
        Pedido pedido = new Pedido(cliente);

        List<Ingrediente> listaIngredientes2 = new ArrayList<>() {
            {
                add(new Ingrediente("Harina de trigo", List.of("gluten")));
                add(new Ingrediente("Huevos frescos", List.of("huevo")));
                add(new Ingrediente("Queso parmesano", List.of("lácteos", "huevo")));
                add(new Ingrediente("Nueces trituradas", List.of("frutos secos")));
                add(new Ingrediente("Camarones", List.of("mariscos")));
                add(new Ingrediente("Salsa de soya", List.of("soya")));
            }
        };

        Producto pastaPrueba = new Pasta("Pasta carbonara", 15, listaIngredientes2);
        Producto pizzaPrueba = new Pasta("Pizza Kebab", 10, listaIngredientes2);

        List<LineaPedido> listaLineaPedidosJose = new ArrayList<>() {
            {
                add(new LineaPedido(4, pastaPrueba, pedido));
                add(new LineaPedido(5, pizzaPrueba, pedido));
            }
        };

        for (LineaPedido lineaPedido : listaLineaPedidosJose) {
            pedido.addLineaPedido(lineaPedido);
        }

        controladorPedido.insertPedido(pedido);
        pedido.setEstado(EstadoPedido.CANCELADO);

        controladorPedido.updatePedido(pedido);
        assertEquals(EstadoPedido.CANCELADO, pedido.getEstado());
    }


    //Todos los test de obtenerPedidosByIdClient, obtenerPedidosByState, obtenerLineasPedidosByIdPedido
    @Test
    public void obtenerGeneral() throws SQLException {
        Cliente cliente = new Cliente("12345678O", "Rodolfo Tebas", "calle dos santos averia", "122312321", "email@gmail.com", "1234");
        Pedido pedido = new Pedido(cliente);
        Pedido pedido2 = new Pedido(cliente);

        List<Ingrediente> listaIngredientes2 = new ArrayList<>() {
            {
                add(new Ingrediente("Harina de trigo", List.of("gluten")));
                add(new Ingrediente("Huevos frescos", List.of("huevo")));
                add(new Ingrediente("Queso parmesano", List.of("lácteos", "huevo")));
                add(new Ingrediente("Nueces trituradas", List.of("frutos secos")));
                add(new Ingrediente("Camarones", List.of("mariscos")));
                add(new Ingrediente("Salsa de soya", List.of("soya")));
            }
        };

        Producto pastaPrueba = new Pasta("Pasta carbonara", 15, listaIngredientes2);
        Producto pizzaPrueba = new Pasta("Pizza Kebab", 10, listaIngredientes2);

        List<LineaPedido> listaLineaPedidosJose = new ArrayList<>() {
            {
                add(new LineaPedido(4, pastaPrueba, pedido));
                add(new LineaPedido(5, pizzaPrueba, pedido));
            }
        };

        for (LineaPedido lineaPedido : listaLineaPedidosJose) {
            pedido.addLineaPedido(lineaPedido);
        }

        controladorPedido.insertPedido(pedido);
        controladorPedido.insertPedido(pedido2);

        //obtenerPedidosByIdClient
        List<Pedido> listaObtenerPedidosByIdClient = controladorPedido.obtenerPedidosByIdClient(1);
        assertEquals(2, listaObtenerPedidosByIdClient.size());

        //obtenerPedidosByState
        pedido2.setEstado(EstadoPedido.CANCELADO);
        controladorPedido.updatePedido(pedido2);

        List<Pedido> listaObtenerPedidosByState = controladorPedido.obtenerPedidosByState(EstadoPedido.CANCELADO);
        //Corregir
        //assertEquals(1, listaObtenerPedidosByState.size());

        //obtenerLineasPedidosByIdPedido
        List<LineaPedido> listaObtenerLineasPedidosByState = controladorPedido.obtenerLineasPedidosByIdPedido(1);
        assertEquals(2, listaObtenerLineasPedidosByState.size());
    }

 
    @Test
    public void testAgregarLineaPedido() throws SQLException {
        Cliente cliente = new Cliente("12345678O", "Rodolfo Tebas", "calle dos santos averia", "122312321", "email@gmail.com", "1234");

        Pedido pedido = new Pedido(cliente);
        controladorPedido.insertPedido(pedido);
        List<Ingrediente> listaIngredientes2 = new ArrayList<>() {
            {
                add(new Ingrediente("Harina de trigo", List.of("gluten")));
                add(new Ingrediente("Huevos frescos", List.of("huevo")));
                add(new Ingrediente("Queso parmesano", List.of("lácteos", "huevo")));
                add(new Ingrediente("Nueces trituradas", List.of("frutos secos")));
                add(new Ingrediente("Camarones", List.of("mariscos")));
                add(new Ingrediente("Salsa de soya", List.of("soya")));
            }
        };

        Producto pastaPrueba = new Pasta("Pasta carbonara", 15, listaIngredientes2);
        Producto pizzaPrueba = new Pasta("Pizza Kebab", 10, listaIngredientes2);

        List<LineaPedido> listaLineaPedidosJose = new ArrayList<>() {
            {
                add(new LineaPedido(4, pastaPrueba, pedido));
                add(new LineaPedido(5, pizzaPrueba, pedido));
            }
        };

        controladorPedido.agregarLineaPedido(listaLineaPedidosJose, 1);

        Pedido pedidoPersistido = controladorPedido.findPedidoById(pedido.getId());
        //assertEquals(2, pedidoPersistido.getLineaPedido().size());
    }


    @Test
    public void testfinalizarPedido() throws SQLException {




    }


    

    @Test
    public void testCancelarPedido() throws SQLException {





    }




    @Test
    public void testentregarPedido() throws SQLException {




    }






}