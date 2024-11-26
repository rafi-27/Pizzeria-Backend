package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

//@XmlRootElement(name = "pizza")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pizza extends Producto{
    //@CsvBindByName(column = "Tamanyo")
    private SIZE tamanyo;
    
    //@CsvBindAndSplitByName(column = "Ingredientes pizza", writeDelimiter = ",", elementType = String.class)
    @XmlElementWrapper(name="Ingredientes")
    @XmlElement(name = "ingrediente")
    private List<Ingrediente>listaIngredientesPizza = new ArrayList<>();

    //,List<Ingrediente>listaIngredientesPizzaParam
    public Pizza(int id, String nombre, double precio,SIZE tam, List<Ingrediente>lista) {
        super(nombre, precio);
        this.tamanyo=tam;
        this.listaIngredientesPizza=lista;
    }

    //public Pizza(){}

    public SIZE getTamanyo() {
        return tamanyo;
    }

    public List<Ingrediente> getListaIngredientesPizza() {
        return listaIngredientesPizza;
    }

    @Override
    public String toString() {
        return super.toString()+" tamanyo=" + tamanyo;
    }
}
