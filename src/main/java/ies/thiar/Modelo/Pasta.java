package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Pasta extends Producto{
    public enum SIZE {GRANDE,PEQUEÑA,MEDIANA}
    private SIZE tamanyo;
    private List<Ingrediente>listaIngredientePasta = new ArrayList<>();

    public Pasta(int id, String nombre, double precio) {
        super(id, nombre, precio);
    }
}