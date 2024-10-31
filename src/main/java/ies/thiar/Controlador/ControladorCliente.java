package ies.thiar.Controlador;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import ies.thiar.Modelo.Cliente;

public class ControladorCliente {
    //ciienteActual
    private Cliente clienteActual;
    private List<Cliente>listaClientes = new ArrayList<>();    
    //Illegal state exception

    //Gestion de ficheros:
    GestionFicheros gestor = new GestionFicheros();

        ////////////////////////////////////////Examen////////////////////////////////////////
        public void exportarClientesAXMLExam(List<Cliente> listaPerson) throws JAXBException{
            gestor.exportarClientesAXML(listaPerson);
        }
    
        public List<Cliente> importarClientesExam() throws FileNotFoundException, JAXBException{
            return gestor.importacionXmlClientesExam();
        }
        ////////////////////////////////////////Examen////////////////////////////////////////

    //registrarCliente(--------) para agregar un cliente, Crear objeto , meter en tabla
    public void registrarCliente(Cliente cliente){
        listaClientes.add(cliente);
    }

    //boolean loginCliente(correo, password) para logearse: buscar en tabla, check contrasenya si existe clienteActual = lo buscamos
    //despues de logearte usar clienteActual() va en login
    public boolean loginCliente(String correo,String password){
        for (Cliente cliente : listaClientes) {
            if (cliente.getEmail().equalsIgnoreCase(correo)&&cliente.getPassword().equalsIgnoreCase(password)) {
                this.clienteActual=cliente;
                return true;
            }
        } 
        return false;
    }
    
    //agregarLineaPedido(Producto p, Cantidad) ya despues del login. este llamo al controlador pedido hariamos un controladoPedido.agregarlineaPedido. esta 
    // public void agregarLineaPedido(Producto p,int Cantidad,Cliente cliente) throws IllegalAccessException{
    //     ControladorPedido.agregarLineaPedido(p, Cantidad,cliente);
    // }

    public List<Cliente> importarAdministradorres() throws IOException{
        return gestor.leerArchivo();
    }


    //Hay un comentario en mi clase Gestion de ficheros que explico porque no le paso un parametro a esta funcion
    public List<Cliente> importamosClientesXML() throws FileNotFoundException, JAXBException{
        return gestor.importacionXml();
    }

    public void exportarClientesAXML(List<Cliente>listaClintes) throws JAXBException{
        gestor.convertimosAXml(listaClintes);
    }
}