package ies.thiar.Controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.Pizza;
import ies.thiar.Modelo.Producto;

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

    public void exportarProductosXML(List<Producto>listaProductos) throws JAXBException{
        gestor.exportarProductosXML(listaProductos);
    }

    public List<Producto> impoortarProductosXML() throws IOException, JAXBException{
        return gestor.importarProductosXML();
    }

    //----------Pizza CSV-----------//
    public void exportarPizzasCSV(List<Pizza>listaPizzas) throws FileNotFoundException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException{
        gestor.exportarPizzaCSV(listaPizzas);
    }

    public List<Pizza> impoortarPizzasCSV() throws IOException, JAXBException{
        return gestor.importarPizzasCSV();
    }
        //----------Pizza XML-----------//
    public void exportarPizzasXML(List<Pizza>listaPizzas) throws FileNotFoundException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, JAXBException{
        gestor.exportamosPizzasXML(listaPizzas);
    }
    
    public List<Pizza> importarPizzasXML() throws IOException, JAXBException{
        return gestor.importacionPizzasXml();
    }

    ///////////////////////////////////////////Examen///////////////////////////////////////////
    public List<Pizza> importarListaPizzasExamen() throws IOException{
        return gestor.importarPizzasSinLibreria();
    }
    ///////////////////////////////////////////Examen///////////////////////////////////////////


}
