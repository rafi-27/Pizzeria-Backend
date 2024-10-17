package ies.thiar.Control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import ies.thiar.Modelo.Cliente;

import javax.xml.bind.JAXBException;

public class MainPruebas {
    public static void main(String[] args) throws JAXBException {
        GestionFicheros gestor = new GestionFicheros();
        try {
            gestor.gestionBasicaDeFicheros();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Cliente> listaClientes = new ArrayList<>();

        //Metemos los clientes: 
        listaClientes.add(new Cliente(8, "78879645E","Rodri","calle hgola", "87654345678", "null@gmail.com", "Hola",false));
        listaClientes.add(new Cliente(1, "65784903R","Rafe","calle jnondf", "87654345678", "null@gmail.com", "Hola", false));
        listaClientes.add(new Cliente(2, "34566786T","Jose","calle utueiu", "87654345678", "null@gmail.com", "Hola", true));
        listaClientes.add(new Cliente(3, "76584903O","Moha","calle tuppq", "87654345678", "null@gmail.com", "Hola", false));
        listaClientes.add(new Cliente(2, "34566786T","Jose","calle utueiu", "87654345678", "null@gmail.com", "Hola", false));
        listaClientes.add(new Cliente(3, "76584903O","Moha","calle tuppq", "87654345678", "null@gmail.com", "Hola", true));

        System.out.println();
        gestor.convertimosAXml(listaClientes,"nuevoFichero.xml");

        System.out.println();
        try {
            gestor.importacionXml();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
