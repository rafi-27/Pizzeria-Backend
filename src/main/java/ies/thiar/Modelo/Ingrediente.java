package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvIgnore;

public class Ingrediente {
    @CsvBindByName
    @CsvBindAndSplitByPosition(position = 0, writeDelimiter = ",", elementType = String.class)
    private List<String> listaAlergenos = new ArrayList<>();

    @CsvBindByName
    @CsvBindByPosition(position = 1)
    private int id;

    @CsvBindByName
    @CsvBindByPosition(position = 2)
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