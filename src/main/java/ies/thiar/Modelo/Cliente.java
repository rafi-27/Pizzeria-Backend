package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cliente {
    /**
     * Cliente. Representa un cliente que realiza un pedido. 
     */
    
     //Atributos:
    @XmlAttribute
    private int id;

    private String dni;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String password;
    private List<Pedido> listaPedidos = new ArrayList<>();
    private List<Cliente> listaClientes = new ArrayList<>();


    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    //Nuevo atributo
    private boolean esAdministrador;
    
    public Cliente(){}
    
    //Constructor:
    public Cliente(int id, String dni, String nombre, String direccion, String telefono, String email, String password, boolean admin) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.esAdministrador=admin;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono="
                + telefono + ", email=" + email + ", password=" + password + ", esAdministrador=" + esAdministrador + "]";
    }

   

}