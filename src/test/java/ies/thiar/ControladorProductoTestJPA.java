package ies.thiar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ies.thiar.Modelo.Bebida;
import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.Pasta;
import ies.thiar.Modelo.Producto;
import ies.thiar.Modelo.SIZE;
import ies.thiar.controlador.ControladorProducto;

public class ControladorProductoTestJPA {
    ControladorProducto controladorProducto;

    @BeforeEach
    void setUp() {
        controladorProducto = new ControladorProducto();
    }

    @Test
    public void insertarUnProducto() throws SQLException {
        List<Ingrediente> listaIngredientes = List.of(
            new Ingrediente("Queso", List.of("Lactosa")),
            new Ingrediente("Tomate", List.of("")),
            new Ingrediente("Jamon", List.of("Lactosa", "Gluten"))
        );

        Producto producto = new Bebida("CocaCola", 2.50, SIZE.GRANDE);
        Producto pasta = new Pasta("Pasta", 5.50, listaIngredientes);

        controladorProducto.insertProducto(producto);
        controladorProducto.insertProducto(pasta);

        //
    }

    @Test
    public void borrarUnProducto() throws SQLException{
        List<Ingrediente> listaIngredientes = List.of(new Ingrediente("Queso",List.of("Lactosa")),new Ingrediente("Tomate",List.of("")),new Ingrediente("Jamon",List.of("Lactosa","Gluten")));


        Producto producto = new Bebida("CocaCola",2.50,SIZE.GRANDE);
        Producto pasta = new Pasta("Pasta", 5.50,listaIngredientes);

        controladorProducto.insertProducto(producto);
        controladorProducto.insertProducto(pasta);

        controladorProducto.deleteProduct(1);

        //assertEquals(null, controladorProducto.findAll().size());
    }

    @Test
    public void testUpdateProduct() throws SQLException{
        Producto producto = new Bebida("CocaCola",2.50,SIZE.GRANDE);

        controladorProducto.insertProducto(producto);

        producto.setNombre("Fanta");
        producto.setPrecio(2.75);
        controladorProducto.updateProduct(producto);
        assertEquals("Fanta", producto.getNombre());
    }


}