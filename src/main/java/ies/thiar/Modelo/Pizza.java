package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Pizza extends Producto {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "Producto_Ingrediente",
        joinColumns = @JoinColumn(name = "producto_id"),
        inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingrediente> listaIngredientesPizza = new ArrayList<>();

    public Pizza() {}

    public Pizza(String nombre, double precio, SIZE tam, List<Ingrediente> lista) {
        super(nombre, precio, TipoProducto.PIZZA, tam);
        this.listaIngredientesPizza = lista;
    }

    public Pizza(String nombre, double precio, SIZE tam) {
        super(nombre, precio, TipoProducto.PIZZA);
    }

    public List<Ingrediente> getListaIngredientesPizza() {
        return listaIngredientesPizza;
    }

    public void setListaIngredientesPizza(List<Ingrediente> listaIngredientesPizza) {
        this.listaIngredientesPizza = listaIngredientesPizza;
    }

    @Override
    public String toString() {
        return super.toString() + " lista Ingredientes: " + getListaIngredientesPizza();
    }
}