package ies.thiar.Control;
import java.util.ArrayList;
import java.util.List;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.Producto;

public class ControladorCliente {
    //ciienteActual
    private Cliente clienteActual;
    private List<Cliente>listaClientes = new ArrayList<>();    
    //Illegal state exception

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
    public void agregarLineaPedido(Producto p,int Cantidad){
        ControladorPedido.agregarLineaPedido(p, Cantidad);
    }
}