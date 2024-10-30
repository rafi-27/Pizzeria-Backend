package ies.thiar.Controlador;

import java.io.FileNotFoundException;
import java.io.IOException;


import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.Producto;

import java.util.*;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class ControladorProducto {
    GestionFicheros gestor;

    public ControladorProducto(){
        gestor = new GestionFicheros();
    }

    
    public List<Ingrediente> importarIngredienteDeProducto() throws FileNotFoundException, IOException{
        return gestor.leerIngredienteCSV();
    }

    public void exportarIngredienteDeProducto(List<Ingrediente>listaIngredientes) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, FileNotFoundException{
        gestor.exportarIngredienteCSV(listaIngredientes);
    }

    public void exportarProductosCSV(List<Producto>listaProductos) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException{
        gestor.exportarProductos(listaProductos);
    }

    public List<Producto> impoortarProductos() throws IOException{
        return gestor.importarProductos();
    }
}
