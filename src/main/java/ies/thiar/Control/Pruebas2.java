package ies.thiar.Control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.Ingrediente;

import javax.xml.bind.JAXBException;

public class Pruebas2 {
    public static void main(String[] args) throws JAXBException {
        GestionFicheros gestor = new GestionFicheros();

        try {
            /**
             * Ejer 1
             */
            System.out.println("---------------------------------------------------------Primer Ejer-------------------------------------------------------------------------");
            List<Cliente> lista = gestor.gestionBasicaDeFicheros();
            lista.forEach(admins->System.out.println(admins));

            

            System.out.println("---------------------------------------------------------Segundo Ejer-------------------------------------------------------------------------");
            /**
             * Ejer 2
             */
            ControladorCliente controlCliente = new ControladorCliente();

            List<Cliente> listaClientes = new ArrayList<>();

            // Metemos los clientes:
            listaClientes.add(new Cliente(8, "78879645E", "Rodri", "calle hgola", "87654345678", "null@gmail.com",
                    "Hola", false));
            listaClientes.add(new Cliente(1, "65784903R", "Rafe", "calle jnondf", "87654345678", "null@gmail.com",
                    "Hola", false));
            listaClientes.add(
                    new Cliente(2, "34566786T", "Jose", "calle utueiu", "87654345678", "null@gmail.com", "Hola", true));
            listaClientes.add(
                    new Cliente(3, "76584903O", "Moha", "calle tuppq", "87654345678", "null@gmail.com", "Hola", false));
            listaClientes.add(new Cliente(2, "34566786T", "Jose", "calle utueiu", "87654345678", "null@gmail.com",
                    "Hola", false));
            listaClientes.add(
                    new Cliente(3, "76584903O", "Moha", "calle tuppq", "87654345678", "null@gmail.com", "Hola", true));

            System.out.println("Tamaño de lista de clientes: "+listaClientes.size());
            
            controlCliente.exportarClientesAXML(listaClientes);


            System.out.println("---------------------------------------------------------Tercer Ejer-------------------------------------------------------------------------");
            /**
             * Ejer 2.1
             */
            List<Cliente>listaImportacionXML = controlCliente.importamosClientesXML();
            listaImportacionXML.forEach(cliente -> System.out.println(cliente));


            System.out.println("---------------------------------------------------------Cuarto Ejer-------------------------------------------------------------------------");
            /**
             * Ejer 3 Exportacion
             */
            ControladorProducto controladorProducto = new ControladorProducto();

            List<Ingrediente>listaIngredientes = new ArrayList<>(){{
                add(new Ingrediente(1,"null", List.of("uno","dos","tres")));
                add(new Ingrediente(1,"nulle", List.of("Quesso","Patatas","Carne")));
                add(new Ingrediente(1,"nullo", List.of("Lactosa","Lactose","Calamares")));
                add(new Ingrediente(1,"nulla", List.of("Otro")));
            }};

            controladorProducto.exportarIngredienteDeProducto(listaIngredientes);
            System.out.println("Tamaño de lista de ingredientes: "+listaIngredientes.size());
            

            System.out.println("---------------------------------------------------------Quinto Ejer-------------------------------------------------------------------------");
            /**
             * Ejer 3.1 Importacion
             */
             controladorProducto.importarIngredienteDeProducto().forEach(ingre -> System.out.println(ingre.toString()));;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
