package ies.thiar;

import ies.thiar.controlador.ControladorCliente;
import ies.thiar.controlador.ControladorProducto;
import ies.thiar.Modelo.Producto;
import ies.thiar.Modelo.Bebida;
import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.SIZE;
import ies.thiar.Utils.DatabaseConf;
import ies.thiar.Modelo.Pizza;
import ies.thiar.Modelo.Pasta;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ControladorProductoTest {

    // --------------------------------------------------------------TestProductos---------------------------------------------------------

    ControladorProducto controladorProducto;

    @BeforeEach
    void setUp() throws SQLException {
        controladorProducto = new ControladorProducto();
    }

    @Test
    public void testInsertarProductosConIngredientes() throws SQLException {
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

        Producto pizzaPrueba = new Pizza(1, "Pizza kebab", 10, SIZE.GRANDE, listaIngredientesPizza);

        controladorProducto.insertProducto(pizzaPrueba);

        Producto productoRecuperadoPizza = controladorProducto.findProductById(1);
        assertNotNull(productoRecuperadoPizza, "El producto debería ser recuperado");
        assertEquals("Pizza kebab", productoRecuperadoPizza.getNombre(),
                "El nombre del producto debería ser el esperado");

        List<Ingrediente> ingredientesPizza = controladorProducto.findIngredientesByProducto(1);
        assertEquals(6, ingredientesPizza.size(), "El número de ingredientes debería ser 6 para la pizza");
        ingredientesPizza.forEach(ingrediente -> {
            assertNotNull(ingrediente.getListaAlergenos(), "Cada ingrediente debería tener alergenos");
            assertFalse(ingrediente.getListaAlergenos().isEmpty(),
                    "El ingrediente no debería tener una lista vacía de alergenos");
        });

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

        Producto pastaPrueba = new Pasta(2, "Pasta carbonara", 15, listaIngredientesPasta);

        controladorProducto.insertProducto(pastaPrueba);

        Producto productoRecuperadoPasta = controladorProducto.findProductById(2);
        assertNotNull(productoRecuperadoPasta, "El producto debería ser recuperado");
        assertEquals("Pasta carbonara", productoRecuperadoPasta.getNombre(),
                "El nombre del producto debería ser el esperado");

        List<Ingrediente> ingredientesPasta = controladorProducto.findIngredientesByProducto(2);
        assertEquals(6, ingredientesPasta.size(), "El número de ingredientes debería ser 6 para la pasta");
        ingredientesPasta.forEach(ingrediente -> {
            assertNotNull(ingrediente.getListaAlergenos(), "Cada ingrediente debería tener alergenos");
            assertFalse(ingrediente.getListaAlergenos().isEmpty(),
                    "El ingrediente no debería tener una lista vacía de alergenos");
        });
    }

}
