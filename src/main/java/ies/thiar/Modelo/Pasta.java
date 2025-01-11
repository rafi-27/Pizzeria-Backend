package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Pasta extends Producto{

    public Pasta(){}
        
    @ManyToMany
    @JoinTable(name="Producto_Ingrediente", joinColumns=@JoinColumn(name = "producto_id"))
    private List<Ingrediente>listaIngredientePasta = new ArrayList<>();

    public Pasta(int id, String nombre, double precio,List<Ingrediente>listaIngredientePastaParam) {
        super(nombre, precio, TipoProducto.PASTA);
        this.listaIngredientePasta=listaIngredientePastaParam;
    }
    public Pasta(int id, String nombre, double precio) {
        super(nombre, precio, TipoProducto.PASTA);
    }
    public void setListaIngredientePasta(List<Ingrediente> listaIngredientePasta) {
        this.listaIngredientePasta = listaIngredientePasta;
    }

    public List<Ingrediente> getListaIngredientePasta() {
        return listaIngredientePasta;
    }

    @Override
    public String toString() {
        return super.toString()+" lista ingredientes pasta "+listaIngredientePasta;
    }
}