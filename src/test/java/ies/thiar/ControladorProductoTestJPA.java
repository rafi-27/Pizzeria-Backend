package ies.thiar;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ies.thiar.Modelo.Bebida;
import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.Pasta;
import ies.thiar.Modelo.Pizza;
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
            new Ingrediente("Queso", List.of("Lactosa", "Gluten")),
            new Ingrediente("Tomate", List.of("")),
            new Ingrediente("Jamon", List.of("Lactosa", "Gluten")),
            new Ingrediente("Jamones", List.of( "Gluten"))
        );

        List<Ingrediente> listaIngredientesPizza = List.of(
            new Ingrediente("Queso", List.of("Lactosa", "Gluten")),
            new Ingrediente("Kebab", List.of("Mayonesa","Pistachos"))
        );

        Producto producto = new Bebida("CocaCola", 2.50, SIZE.GRANDE);
        Producto pasta = new Pasta("Pasta", 5.50, listaIngredientes);
        Producto pizza = new Pizza("Pizza kebab", 8.50, SIZE.GRANDE, listaIngredientesPizza);

        controladorProducto.insertProducto(producto);
        controladorProducto.insertProducto(pasta);
        controladorProducto.insertProducto(pizza);

        List<Producto>listaProducto = controladorProducto.findAllProducts();
        assertEquals(3, listaProducto.size());
    }

    @Test
    public void borrarUnProducto() throws SQLException{
        List<Ingrediente> listaIngredientes = List.of(new Ingrediente("Queso",List.of("Lactosa")),new Ingrediente("Tomate",List.of("")),new Ingrediente("Jamon",List.of("Lactosa","Gluten")));


        Producto producto = new Bebida("CocaCola",2.50,SIZE.GRANDE);
        Producto pasta = new Pasta("Pasta", 5.50,listaIngredientes);

        controladorProducto.insertProducto(producto);
        controladorProducto.insertProducto(pasta);

        controladorProducto.deleteProduct(1);

        assertEquals(1, controladorProducto.findAllProducts().size());
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

    @Test
    public void testFindAlergenosByIngrediente() throws SQLException{
        List<Ingrediente> listaIngredientes = List.of(
            new Ingrediente("Queso", List.of("Lactosa", "Gluten")),
            new Ingrediente("Tomate", List.of("")),
            new Ingrediente("Jamon", List.of("Lactosa", "Gluten")),
            new Ingrediente("Jamones", List.of( "Gluten"))
        );

        List<Ingrediente> listaIngredientesPizza = List.of(
            new Ingrediente("Queso", List.of("Lactosa", "Gluten")),
            new Ingrediente("Kebab", List.of("Mayonesa","Pistachos"))
        );

        Producto producto = new Bebida("CocaCola", 2.50, SIZE.GRANDE);
        Producto pasta = new Pasta("Pasta", 5.50, listaIngredientes);
        Producto pizza = new Pizza("Pizza kebab", 8.50, SIZE.GRANDE, listaIngredientesPizza);

        controladorProducto.insertProducto(producto);
        controladorProducto.insertProducto(pasta);
        controladorProducto.insertProducto(pizza);

        List<String>listaAlergenos = controladorProducto.findAlergenoByIdIngredient(1);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        assertEquals(2, listaAlergenos.size());
        for (String string : listaAlergenos) {
            System.out.println(string);
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
    }

}