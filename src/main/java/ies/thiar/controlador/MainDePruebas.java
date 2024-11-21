package ies.thiar.controlador;

import java.util.List;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Utils.DatabaseConf;

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
            ruben.setDni("uno random");
            controladorCliente.updateCliente(ruben);
            System.out.println(ruben.toString());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
