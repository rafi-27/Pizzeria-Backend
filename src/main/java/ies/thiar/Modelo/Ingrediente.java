package ies.thiar.Modelo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

@XmlAccessorType(XmlAccessType.FIELD)
public class Ingrediente {

    @CsvBindAndSplitByName(column = "ALERGENOS", writeDelimiter = ",", elementType = String.class)
    @XmlElementWrapper(name="alergenos")
    @XmlElement(name="alergeno")
    private List<String> listaAlergenos;

    @CsvBindByName(column = "ID")
    private int id;

    @CsvBindByName(column = "NOMBRE")
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