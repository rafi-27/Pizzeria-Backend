package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pasta")
public class Pasta extends Producto{

    public Pasta(){}
        
    //@CsvBindAndSplitByName(column = "Ingredientes pasta", writeDelimiter = ",", elementType = String.class)
    @XmlElement(name = "ingredientes")
    private List<Ingrediente>listaIngredientePasta = new ArrayList<>();

    public Pasta(int id, String nombre, double precio,List<Ingrediente>listaIngredientePastaParam) {
        super(nombre, precio, TipoProducto.PASTA);
        this.listaIngredientePasta=listaIngredientePastaParam;
    }

    public List<Ingrediente> getListaIngredientePasta() {
        return listaIngredientePasta;
    }

    @Override
    public String toString() {
        return super.toString()+" lista ingredientes pasta "+listaIngredientePasta;
    }

    
    
    
}