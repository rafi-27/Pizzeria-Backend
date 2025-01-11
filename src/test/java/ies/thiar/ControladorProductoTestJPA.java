package ies.thiar;

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
    public void insertarUnProducto() throws SQLException{
        List<Ingrediente> listaIngredientes = List.of(new Ingrediente(1,"Queso",List.of("Lactosa")),new Ingrediente(2,"Tomate",List.of("")),new Ingrediente(3,"Jamon",List.of("Lactosa","Gluten")));


        Producto producto = new Bebida(1,"CocaCola",2.50,SIZE.GRANDE);
        Producto pasta = new Pasta(1,"Pasta", 5.50,listaIngredientes);

        controladorProducto.insertProducto(producto);
        controladorProducto.insertProducto(pasta);
    }
}
