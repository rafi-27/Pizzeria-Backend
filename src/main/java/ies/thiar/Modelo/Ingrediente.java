package ies.thiar.Modelo;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="cliente_seq", sequenceName="hibernate_sequence", allocationSize=1)
public class Ingrediente {

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "ingrediente_alergenos")
    private List<String> listaAlergenos;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(unique = true, nullable = false)
    private String nombre;

    public Ingrediente() {}

    public Ingrediente(String nombre, List<String> listaAlergenos) {
        this.nombre = nombre;
        this.listaAlergenos = listaAlergenos;
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

    public void setListaAlergenos(List<String> listaAlergenos) {
        this.listaAlergenos = listaAlergenos;
    }

    @Override
    public String toString() {
        return "Ingrediente [id=" + id + ", nombre=" + nombre + ", lista de alergenos " + listaAlergenos + "]";
    }
}