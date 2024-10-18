package ies.thiar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import ies.thiar.Control.GestionFicheros;
import ies.thiar.Modelo.Cliente;

import javax.xml.bind.JAXBException;

public class Pruebas2 {
    public static void main(String[] args) throws JAXBException {
        GestionFicheros gestor = new GestionFicheros();
        try {
            /**
             * Ejer 1
             */
            List<Cliente> lista = gestor.gestionBasicaDeFicheros();
            for (Cliente cliente : lista) {
                System.out.println(cliente.toString());
            }

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
            for (Cliente cliente : listaImportacionXML) {
                System.out.println(cliente.toString());
            }



            /**
             * Ejer 4
             */









             
            /**
             * Ejer 4
             */








             

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
