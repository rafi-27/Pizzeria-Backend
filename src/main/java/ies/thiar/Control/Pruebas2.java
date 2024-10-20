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
            List<Cliente> lista = gestor.gestionBasicaDeFicheros();
            lista.forEach(admins->System.out.println(admins));

            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

            
            /**
             * Ejer 3
             */
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

            System.out.println();
            gestor.convertimosAXml(listaClientes, "nuevoFichero.xml");

            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
            
            /**
             * Ejer 3
             */
            List<Cliente>listaImportacionXML = gestor.importacionXml();
            System.out.println(listaImportacionXML.size());
            listaImportacionXML.forEach(cliente -> System.out.println(cliente));

            /**
             * Ejer 4 Exportacion
             */
            List<Ingrediente>listaIngredientes = new ArrayList<>();
            List<String>listaAlergenosUno = new ArrayList<>();
            List<String>listaAlergenosDos = new ArrayList<>();
            List<String>listaAlergenosTres = new ArrayList<>();

            listaAlergenosUno.add("Lactosa");
            listaAlergenosUno.add("Lactose");
            listaAlergenosUno.add("Lactoso");

            listaAlergenosDos.add("Leche");
            listaAlergenosDos.add("Lechi");
            listaAlergenosDos.add("Lechu");

            listaAlergenosTres.add("Alergeno 1");

            listaIngredientes.add(new Ingrediente(1, "Gambas",listaAlergenosUno));
            listaIngredientes.add(new Ingrediente(2, "Carne kebab",listaAlergenosDos));
            listaIngredientes.add(new Ingrediente(3, "Queso",listaAlergenosTres));

            gestor.exportarClienteCSV("ArchivoCSV",listaIngredientes);
 
            /**
             * Ejer 4 Importacion
             */

             List<Ingrediente>listaIngredientes2 = gestor.leerClienteCSV("ArchivoCSV.csv");
             System.out.println(listaIngredientes2.size());
             listaIngredientes2.forEach(ingre->System.out.println(ingre.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
