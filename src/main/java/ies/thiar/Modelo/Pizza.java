package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

public class Pizza extends Producto{
    @CsvBindByName(column = "Tamanyo")
    private SIZE tamanyo;
    
    @CsvBindAndSplitByName(column = "Ingredientes pizza", writeDelimiter = ",", elementType = String.class)
    private List<Ingrediente>listaIngredientesPizza = new ArrayList<>();

    public Pizza(int id, String nombre, double precio,SIZE tam) {
        super(id, nombre, precio);
        this.tamanyo=tam;
    }

    public SIZE getTamanyo() {
        return tamanyo;
    }

    public List<Ingrediente> getListaIngredientesPizza() {
        return listaIngredientesPizza;
    }

    @Override
    public String toString() {
        return "Pizza [tamanyo=" + tamanyo + ", listaIngredientesPizza=" + listaIngredientesPizza + "]";
    }
}
