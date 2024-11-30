package ies.thiar;

import java.util.ArrayList;
import java.util.List;

import ies.thiar.Modelo.Bebida;
import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.Pasta;
import ies.thiar.Modelo.Pizza;
import ies.thiar.Modelo.Producto;
import ies.thiar.Modelo.SIZE;
import ies.thiar.Utils.DatabaseConf;
import ies.thiar.controlador.ControladorCliente;
import ies.thiar.controlador.ControladorProducto;

public class MainDePruebas {
    public static void main(String[] args) {
        
        try {
            //Creacion de controladores y tablas.
            ControladorCliente controladorCliente = new ControladorCliente();
            DatabaseConf.dropAndCreateTables();
            

            //Creacion de usuarios:
            Cliente ruben = new Cliente("123456789Q","Ruben", "Garcia", "625478654", "ruben@gmail.com", "1234");
            Cliente juan = new Cliente("12345678P","Juan", "Ramos", "614875254", "juan@gmail.com", "1234");


            System.out.println("------------------------------------------------------------------------------------");

            //Pruebas con registro:
            controladorCliente.registrarCliente(ruben);
            controladorCliente.registrarCliente(juan);

            List<Cliente>listaClientesAll = controladorCliente.selectAll();
            System.out.println(listaClientesAll.size());
            listaClientesAll.forEach((cliente) -> System.out.println(cliente.toString()));
            
            System.out.println();

            System.out.println("------------------------------------------------------------------------------------");

            //pruebas con delete
            //controladorCliente.deleteClient(1);

            System.out.println(listaClientesAll.size());
            listaClientesAll.forEach((cliente) -> System.out.println(cliente.toString()));


            System.out.println("------------------------------------------------------------------------------------");
            //pruebas find by id:
            //Cliente ejemploFindById = controladorCliente.selectByID(2);
            //System.out.println("User: "+ejemploFindById.toString());


            System.out.println("------------------------------------------------------------------------------------");
            //pruebas con update:
            //ruben.setDni("uno random");
            //controladorCliente.updateCliente(ruben);
            //System.out.println(ruben.toString());

            System.out.println("------------------------------------------------------------------------------------");
            //Login
            Cliente clienteLogin = controladorCliente.clienteLogin("ruben@gmail.com", "1234");
            System.out.println(clienteLogin.toString());


            

            //------------------------------------------------------------------------------------//
            ControladorProducto controladorProducto = new ControladorProducto();
            //Creamos unos cuantos ingredientes:
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
            controladorProducto.insertProducto(pizzaPrueba);

            List<Ingrediente>listaIngredientes2 = new ArrayList<>(){
                {
                    add(new Ingrediente(1, "Harina de trigo", List.of("gluten")));
                    add(new Ingrediente(2, "Huevos frescos", List.of("huevo")));
                    add(new Ingrediente(3, "Queso parmesano", List.of("lácteos","huevo")));
                    add(new Ingrediente(4, "Nueces trituradas", List.of("frutos secos")));
                    add(new Ingrediente(5, "Camarones", List.of("mariscos")));
                    add(new Ingrediente(6, "Salsa de soya", List.of("soya")));
                }
            };

            Producto pastaPrueba = new Pasta(1,"Pasta carbonara", 15, listaIngredientes2);
            controladorProducto.insertProducto(pastaPrueba);

            Producto bebidaPrueba = new Bebida(1, "Coca Cola", 2.5, SIZE.MEDIANA);
            controladorProducto.insertProducto(bebidaPrueba);

            Producto bebidaPruebaDelete = new Bebida(1, "Fanta Taronja", 6, SIZE.GRANDE);
            controladorProducto.insertProducto(bebidaPruebaDelete);

            controladorProducto.deleteProduct(4);

            //Obtenemos los alergenos de un ingrediente:
            List<String>listaAlergenosDeUnIngrediente = controladorProducto.findAlergenoByIdIngredient(1);
            System.out.println("Los alergenos del ingrediente con id 1 son: ");
            listaAlergenosDeUnIngrediente.forEach(alergen -> System.out.print(alergen+", "));

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

            List<Ingrediente>listaIngredientesProducto1 = controladorProducto.findIngredientesByProducto(1);
            System.out.println("Los Ingredientes y alergenos de esos ingredientes del producto con id 1 son: ");
            listaIngredientesProducto1.forEach(ingrediente -> System.out.println(ingrediente+", "));
            
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("---IMPORTANTE------");
            List<Producto>listaProductosPrueba = controladorProducto.findAllProducts();
            System.out.println("Lista productos: "+listaProductosPrueba.size());
            listaProductosPrueba.forEach(productos -> System.out.println(productos+"\n"));

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            Producto pizzaRey = controladorProducto.findProductById(1);
            System.out.println("----------Producto 1 --------: "+pizzaRey);

            System.out.println();

            Producto pastaRey = controladorProducto.findProductById(2);
            System.out.println("----------Producto 2 --------: "+pastaRey);

            System.out.println();

            Producto bebidaRey = controladorProducto.findProductById(3);
            System.out.println("----------Producto 3 --------: "+bebidaRey);

            System.out.println("-------------------------------------------------------------Update-------------------------------------------------------------------");
            Producto productUpdatear = new Bebida(5, "Fanta Naranja", 12, SIZE.PEQUENYA);
            controladorProducto.insertProducto(productUpdatear);

            productUpdatear.setNombre("EjemploPrueba");
            
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
