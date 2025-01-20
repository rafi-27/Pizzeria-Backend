package ies.thiar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ies.thiar.Modelo.Cliente;
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

        Pedido pedidoPersistido = controladorPedido.findPedidoById(pedido.getId());
        assertNotNull(pedidoPersistido);
        assertEquals(2, pedidoPersistido.getLineaPedido().size());
    }








}
