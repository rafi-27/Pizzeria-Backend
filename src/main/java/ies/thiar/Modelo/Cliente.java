package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente {
    /**
    * Cliente. Representa un cliente que realiza un pedido. 
    */
    
    //Atributos:
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;

    @Column(unique=true, nullable=false)
    private String dni;
    private String nombre;
    private String direccion;
    private String telefono;
    
    @Column(unique=true, nullable=false)
    private String email;

    @XmlTransient
    private String password;
    
    @OneToMany(mappedBy="cliente", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Pedido> listaPedidos = new ArrayList<>();


    //Nuevo atributo
    @XmlAttribute
    private boolean esAdministrador;
    
    public Cliente(){}
    
    //Constructor:
    public Cliente(String dni, String nombre, String direccion, String telefono, String email, String password) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }

    public Cliente(String dni, String nombre, String direccion, String telefono, String email, String password, boolean esadmin) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.esAdministrador=esadmin;
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
                + telefono + ", email=" + email + ", password=" + password + ", listaPedidos=" + listaPedidos
                + ", esAdministrador=" + esAdministrador + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public boolean isEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }
}