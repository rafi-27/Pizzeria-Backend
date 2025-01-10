package ies.thiar.Modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Ingrediente {

    @ElementCollection()
    private List<String> listaAlergenos;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;

    @Column(unique=true, nullable=false)
    private String nombre;

    public Ingrediente(int id, String nombre, List<String> listaAlergenos) {
        this.id = id;
        this.nombre = nombre;
        this.listaAlergenos = listaAlergenos;
    }

    public Ingrediente(String nombre, List<String> listaAlergenos) {
        this.nombre=nombre;
        this.listaAlergenos=listaAlergenos;
    }

    public Ingrediente(int id, String nombre) {
        this.id=id;
        this.nombre=nombre;
    }
    
    public Ingrediente(String nombre) {
        this.nombre=nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getListaAlergenos() {
        return listaAlergenos;
    }

    public void setListaAlergenos(List<String> alergenos) {
        this.listaAlergenos = alergenos;
    }

    @Override
    public String toString() {
        return "Ingrediente [id=" + id + ", nombre=" + nombre + ", lista de alergenos " + listaAlergenos;
    }




    
}