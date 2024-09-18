package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Pizza extends Producto{
    public enum SIZE {GRANDE,PEQUEÑA,MEDIANA}
    private SIZE tamanyo;
    private List<Ingrediente>listaIngredientesPizza = new ArrayList<>();

    public Pizza(int id, String nombre, double precio,SIZE tam) {
        super(id, nombre, precio);
        this.tamanyo=tam;
    }
}
