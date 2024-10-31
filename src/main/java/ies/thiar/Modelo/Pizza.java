package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pizza")
public class Pizza extends Producto{
    //@CsvBindByName(column = "Tamanyo")
    private SIZE tamanyo;
    
    @XmlElement(name = "ingredientes")
    //@CsvBindAndSplitByName(column = "Ingredientes pizza", writeDelimiter = ",", elementType = String.class)
    private List<Ingrediente>listaIngredientesPizza = new ArrayList<>();

    public Pizza(int id, String nombre, double precio,SIZE tam,List<Ingrediente>listaIngredientesPizzaParam) {
        super(id, nombre, precio);
        this.tamanyo=tam;
        this.listaIngredientesPizza=listaIngredientesPizzaParam;
    }

    public Pizza(){}

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
