package ies.thiar.controlador;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Utils.DatabaseConf;

public class MainDePruebas {
    public static void main(String[] args) {
        
        try {
            //Creacion de controladores y tablas.
            ControladorCliente controladorCliente = new ControladorCliente();
            DatabaseConf.dropAndCreateTables();
            
            //Creacion de usuarios:
            Cliente ruben = new Cliente("12345678O","Ruben", "Garcia", "625478654", "ruben@gmail.com", "1234");


            //Pruebas con registro:
            controladorCliente.registrarCliente(ruben);


        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
