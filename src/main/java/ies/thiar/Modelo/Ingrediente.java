package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvIgnore;

public class Ingrediente {
    @CsvBindByPosition(position = 0)
    @CsvBindAndSplitByName(writeDelimiter = ",", elementType = String.class)
    private List<String> listaAlergenos = new ArrayList<>();

    @CsvBindByPosition(position = 1)
    @CsvBindByName
    private int id;
    @CsvBindByPosition(position = 2)
    @CsvBindByName
    private String nombre;
 
    @CsvIgnore
    private Pizza pizza;
    @CsvIgnore
    private Pasta pasta;

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

    public List<String> getAlergenos() {
        return listaAlergenos;
    }

    public void setAlergenos(List<String> alergenos) {
        this.listaAlergenos = alergenos;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nombre='" + nombre +
                ", alergenos=" + listaAlergenos + '\'' +
                '}';
    }
}