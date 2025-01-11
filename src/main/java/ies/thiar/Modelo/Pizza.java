package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

//@XmlRootElement(name = "pizza")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Pizza extends Producto{
    //@CsvBindByName(column = "Tamanyo")
    // @Enumerated(EnumType.STRING)
    // private SIZE tamanyo;
    
    //@CsvBindAndSplitByName(column = "Ingredientes pizza", writeDelimiter = ",", elementType = String.class)
    @XmlElementWrapper(name="Ingredientes")
    @XmlElement(name = "ingrediente")
    @ManyToMany
    @JoinTable(name="Producto_Ingrediente", joinColumns=@JoinColumn(name = "producto_id"))
    private List<Ingrediente>listaIngredientesPizza = new ArrayList<>();

    //,List<Ingrediente>listaIngredientesPizzaParam
    public Pizza(int id, String nombre, double precio,SIZE tam, List<Ingrediente>lista) {
        super(nombre, precio, TipoProducto.PIZZA,tam);
        //this.tamanyo=tam;
        this.listaIngredientesPizza=lista;
    }

    //public Pizza(){}
    public Pizza(int id, String nombre, double precio,SIZE tam) {
        super(nombre, precio, TipoProducto.PIZZA);
        //this.tamanyo=tam;
    }

    // public SIZE getTamanyo() {
    //     return tamanyo;
    // }

    public List<Ingrediente> getListaIngredientesPizza() {
        return listaIngredientesPizza;
    }

    // public void setTamanyo(SIZE tamanyo) {
    //     this.tamanyo = tamanyo;
    // }

    public void setListaIngredientesPizza(List<Ingrediente> listaIngredientesPizza) {
        this.listaIngredientesPizza = listaIngredientesPizza;
    }

    @Override
    public String toString() {
        return super.toString()+" lista Ingredientes: "+getListaIngredientesPizza();
    }
}
