package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Ingrediente {
    private int id;
    private String nombre;
    private List<String>listaAlergenos = new ArrayList<>();

    private Pizza pizza;
    private Pasta pasta;
    
    
    public Ingrediente(int id, String nombre, List<String> listaAlergenos) {
        this.id = id;
        this.nombre = nombre;
        this.listaAlergenos = listaAlergenos;
    }

}