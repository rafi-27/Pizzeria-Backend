package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    /**
     * Cliente. Representa un cliente que realiza un pedido. 
     */
    
     //Atributos:
    private int id;
    private String dni;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String password;
    private List<Pedido> listaPedidos = new ArrayList<>();
    
    
    //Constructor:
    public Cliente(int id, String dni, String nombre, String direccion, String telefono, String email,
            String password) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }


    //Getters y setters de cada atributo:
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}