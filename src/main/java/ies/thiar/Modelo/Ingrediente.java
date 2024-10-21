package ies.thiar.Modelo;

import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;


public class Ingrediente {

    @CsvBindAndSplitByName(column = "ALERGENOS", writeDelimiter = ",", elementType = String.class)
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

    public Ingrediente() {
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
        return "Ingrediente [id=" + id + ", nombre=" + nombre + ", lista de ingredientes " + listaAlergenos;
    }




    
}