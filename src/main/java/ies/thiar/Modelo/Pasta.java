package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;

public class Pasta extends Producto{
        
    @CsvBindAndSplitByName(column = "Ingredientes pasta", writeDelimiter = ",", elementType = String.class)
    private List<Ingrediente>listaIngredientePasta = new ArrayList<>();

    public Pasta(int id, String nombre, double precio) {
        super(id, nombre, precio);
    }

    public List<Ingrediente> getListaIngredientePasta() {
        return listaIngredientePasta;
    }
    
}