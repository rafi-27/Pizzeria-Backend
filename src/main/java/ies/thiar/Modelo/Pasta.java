package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Pasta extends Producto{
    private List<Ingrediente>listaIngredientePasta = new ArrayList<>();

    public Pasta(int id, String nombre, double precio) {
        super(id, nombre, precio);
    }
}