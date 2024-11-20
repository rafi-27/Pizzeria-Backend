package ies.thiar.ClasesEnDeshuso;

import java.util.List;

import javax.xml.bind.JAXBException;

import ies.thiar.Modelo.Pizza;
import ies.thiar.controlador.ControladorProducto;

public class Pruebas2 {
    public static void main(String[] args) throws JAXBException {
        ControladorCliente controlCliente = new ControladorCliente();

        try {
            // /**
            // * Ejer 1
            // */
            // System.out.println("---------------------------------------------------------Primer
            // Ejer-------------------------------------------------------------------------");
            // List<Cliente> lista = controlCliente.importarAdministradorres();
            // lista.forEach(admins->System.out.println(admins));

            // System.out.println("---------------------------------------------------------Segundo
            // Ejer-------------------------------------------------------------------------");
            // /**
            // * Ejer 2
            // */

            // List<Cliente> listaClientes = new ArrayList<>();

            // // Metemos los clientes:
            // listaClientes.add(new Cliente(8, "78879645E", "Rodri", "calle hgola",
            // "87654345678", "null@gmail.com",
            // "Hola", false));
            // listaClientes.add(new Cliente(1, "65784903R", "Rafe", "calle jnondf",
            // "87654345678", "null@gmail.com",
            // "Hola", false));
            // listaClientes.add(
            // new Cliente(2, "34566786T", "Jose", "calle utueiu", "87654345678",
            // "null@gmail.com", "Hola", true));
            // listaClientes.add(
            // new Cliente(3, "76584903O", "Moha", "calle tuppq", "87654345678",
            // "null@gmail.com", "Hola", false));
            // listaClientes.add(new Cliente(2, "34566786T", "Jose", "calle utueiu",
            // "87654345678", "null@gmail.com",
            // "Hola", false));
            // listaClientes.add(
            // new Cliente(3, "76584903O", "Moha", "calle tuppq", "87654345678",
            // "null@gmail.com", "Hola", true));

            // System.out.println("Tamaño de lista de clientes: "+listaClientes.size());

            // controlCliente.exportarClientesAXML(listaClientes);

            // System.out.println("---------------------------------------------------------Tercer
            // Ejer-------------------------------------------------------------------------");
            // /**
            // * Ejer 2.1
            // */
            // List<Cliente>listaImportacionXML = controlCliente.importamosClientesXML();
            // listaImportacionXML.forEach(cliente -> System.out.println(cliente));

            // System.out.println("---------------------------------------------------------Cuarto
            // Ejer-------------------------------------------------------------------------");
            // /**
            // * Ejer 3 Exportacion
            // */
            ControladorProducto controladorProducto = new ControladorProducto();

            // List<Ingrediente>listaIngredientes = new ArrayList<>(){{
            // add(new Ingrediente(1,"null", List.of("uno","dos","tres")));
            // add(new Ingrediente(1,"nulle", List.of("Quesso","Patatas","Carne")));
            // add(new Ingrediente(1,"nullo", List.of("Lactosa","Lactose","Calamares")));
            // add(new Ingrediente(1,"nulla", List.of("Otro")));
            // }};

            // controladorProducto.exportarIngredienteDeProducto(listaIngredientes);
            // System.out.println("Tamaño de lista de ingredientes:
            // "+listaIngredientes.size());

            // System.out.println("---------------------------------------------------------Quinto
            // Ejer-------------------------------------------------------------------------");
            // /**
            // * Ejer 3.1 Importacion
            // */
            // controladorProducto.importarIngredienteDeProducto().forEach(ingre ->
            // System.out.println(ingre.toString()));;

            // List<Producto> listaProductos = new ArrayList<>() {
            //     {
            //         add(new Pizza(1, "Pizza Atun", 15.0, SIZE.GRANDE, List.of(new Ingrediente(1, "IngredientepizzaAtun",
            //                 List.of("AlergenoHola", "AlergenoComo", "AlergenoEstas")))));
            //         add(new Pizza(2, "Pizza Kebab", 15.0, SIZE.GRANDE,
            //                 List.of(new Ingrediente(1, "IngredientepizzaKebah", List.of("KebabHola", "KebajComo")),
            //                         new Ingrediente(1, "IngreSimple", List.of("Hola", "Como", "Estas")))));
            //         add(new Pasta(3, "Motzarela", 15.0,
            //                 List.of(new Ingrediente(1, "Ingrediente Pasta", List.of("Pasta", "Siuuu")))));
            //         add(new Bebida(4, "Coca cola", 15.0, SIZE.GRANDE));
            //         add(new Pizza(5, "Pizza", 15.0, SIZE.GRANDE, List.of()));
            //     }
            // };
            // controladorProducto.exportarProductosCSV(listaProductos);

            // List<Producto> listaProductosDos = controladorProducto.impoortarProductos();
            // listaProductosDos.forEach(producto ->
            // System.out.println(producto.toString()));

            //controladorProducto.exportarProductosXML(listaProductos);

            // List<Producto>listaProductosTres = controladorProducto.impoortarProductosXML();
            // listaProductosTres.forEach(producto -> System.out.println(producto.toString()));

            // List<Pizza>listaPizzas = new ArrayList<>(){{
            //     add(new Pizza(1, "Pizza Atun", 15.0, SIZE.GRANDE, List.of(new Ingrediente(23, "IngredientepizzaAtun",List.of("AlergenoHola", "AlergenoComo", "AlergenoEstas")))));
            //     add(new Pizza(2, "Pizza", 10.0, SIZE.MEDIANA, List.of(new Ingrediente(4555, "Ingredientepizza",List.of("AlergenoHola", "Alerge", "AEstas")))));
            //     add(new Pizza(3, "Pizzaffrr", 23.0, SIZE.PEQUEÑA, List.of(new Ingrediente(45, "Atun",List.of("Hola", "Como")))));
            // }};

            //controladorProducto.exportarPizzasCSV(listaPizzas);

            //List<Pizza>listaPizzasImport = controladorProducto.impoortarPizzasCSV();
            //listaPizzasImport.forEach(producto -> System.out.println(producto.toString()));

            //controladorProducto.exportarPizzasXML(listaPizzas);

            List<Pizza>listaPizzillas = controladorProducto.importarPizzasXML();
            listaPizzillas.forEach(producto -> System.out.println(producto.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
