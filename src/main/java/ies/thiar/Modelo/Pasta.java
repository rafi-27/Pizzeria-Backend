package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="cliente_seq", sequenceName="hibernate_sequence", allocationSize=1)
public class Pasta extends Producto {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "Producto_Ingrediente",
        joinColumns = @JoinColumn(name = "producto_id"),
        inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingrediente> listaIngredientePasta = new ArrayList<>();

    public Pasta() {}

    public Pasta(String nombre, double precio, List<Ingrediente> listaIngredientePastaParam) {
        super(nombre, precio, TipoProducto.PASTA);
        this.listaIngredientePasta = listaIngredientePastaParam;
    }

    public Pasta(String nombre, double precio) {
        super(nombre, precio, TipoProducto.PASTA);
    }

    public List<Ingrediente> getListaIngredientePasta() {
        return listaIngredientePasta;
    }

    public void setListaIngredientePasta(List<Ingrediente> listaIngredientePasta) {
        this.listaIngredientePasta = listaIngredientePasta;
    }

    @Override
    public String toString() {
        return super.toString() + " lista ingredientes pasta " + listaIngredientePasta;
    }
}