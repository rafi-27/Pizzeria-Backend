package ies.thiar.Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import ies.thiar.Modelo.Bebida;
import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.Pasta;
import ies.thiar.Modelo.Pizza;
import ies.thiar.Modelo.Producto;
import ies.thiar.Modelo.SIZE;

public class GestionFicheros {
    private final String archivoXML = "Clientes.xml";
    private final String archivoAdmin = "admin.txt";
    private final String archivoCSV = "Ingredientes.csv";
    private final String archivoCSVProductos = "productos.csv";
    private final String archivoXMLProductos = "productos.xml";
    private final String archivoPizzasCSV = "pizzas.csv";
    private final String archivoPizzasXML = "pizzas.xml";

    /**
     * (3 puntos) Actividad 1. Gestión básica de ficheros.
     * 
     * @gestionBasicaDeFicheros
     */
    public List<Cliente> leerArchivo() throws IOException {
        try (Stream<String> lineas = Files.lines(Path.of(archivoAdmin))) {
            return lineas.map(linea -> {
                List<String> cosas = List.of(linea.split("[,;|]"));
                cosas = cosas.stream().map(String::trim).toList();

                return new Cliente(Integer.parseInt(cosas.get(0)), cosas.get(1), cosas.get(2), cosas.get(3),
                        cosas.get(4),
                        cosas.get(5), cosas.get(6), true);
            }).toList();
        }
    }


    /**
     * (3 puntos) Actividad 2. JAXB
     * 
     * @param convertimosAXml
     * @param importacionXml
     * @throws JAXBException
     */
    public void convertimosAXml(List<Cliente> listaPerson) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Cliente.class, ClienteWrape.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        File f = new File(archivoXML);
        ClienteWrape p = new ClienteWrape(listaPerson);
        marshaller.marshal(p, f);
    }

    /*
     * Aqui directamente no le paso el parametro del nombre del fichero, tengo una
     * variable static
     * con el nombre del ficher
     */

    public List<Cliente> importacionXml() throws JAXBException, FileNotFoundException {
        List<Cliente> listaClientes = new ArrayList<>();

        JAXBContext context = JAXBContext.newInstance(ClienteWrape.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ClienteWrape clientes = (ClienteWrape) unmarshaller.unmarshal(new FileReader(archivoXML));

        listaClientes.addAll(clientes.getListaPersonas());

        return listaClientes;
    }

    /**
     * (4 puntos) Actividad 3. OpenCSV
     * Importacion
     * Exportacion
     * 
     * @throws IOException
     * @throws FileNotFoundException
     */

    public List<Ingrediente> leerIngredienteCSV() throws FileNotFoundException, IOException {
        List<Ingrediente> listaClientesDevolver = new ArrayList<>();
        try (FileReader fileReader = new FileReader(archivoCSV)) {
            CsvToBean<Ingrediente> csvToBean = new CsvToBeanBuilder<Ingrediente>(fileReader).withType(Ingrediente.class)
                    .withSeparator(';').withIgnoreLeadingWhiteSpace(true).build();

            listaClientesDevolver = csvToBean.parse();
        }
        return listaClientesDevolver;
    }

    public void exportarIngredienteCSV(List<Ingrediente> listaIngredientes)
            throws FileNotFoundException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        try (PrintWriter pw = new PrintWriter(archivoCSV);) {
            StatefulBeanToCsv<Ingrediente> beanToCsv = new StatefulBeanToCsvBuilder<Ingrediente>(pw).withSeparator(';')
                    .build();
            beanToCsv.write(listaIngredientes);
        }
    }

    // ------------------------------------------------------------------------------------PRUEBAS------------------------------------------------------------------------------------//
    // Probar importar y exportar productos xml y con csv y de forma brusca.
    /**
     * // try (PrintWriter pw = new PrintWriter(archivoCSVProductos);) {
        // StatefulBeanToCsv<Producto> beanToCsv = new
        // StatefulBeanToCsvBuilder<Producto>(pw).withSeparator(';').build();
        // beanToCsv.write(listaProductos);
        // }
     */
    public void exportarProductos(List<Producto> listaProductos) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoCSVProductos))) {
            pw.println("id;nombre;precio;tamanyo;ingredientes");

            for (Producto producto : listaProductos) {
                pw.print(producto.getId() + ";" + producto.getNombre() + ";" + producto.getPrecio() + ";");

                if (producto instanceof Pizza pizza) {
                    String ingredientesPizza = pizza.getListaIngredientesPizza().stream()
                            .map(Ingrediente::toString)
                            .collect(Collectors.joining(","));
                    pw.print(pizza.getTamanyo() + ";" + ingredientesPizza);
                } else if (producto instanceof Bebida bebida) {
                    pw.print(bebida.getTamanyo() + ";");
                } else if (producto instanceof Pasta pasta) {
                    String ingredientesPasta = pasta.getListaIngredientePasta().stream()
                            .map(Ingrediente::toString)
                            .collect(Collectors.joining(","));
                    pw.print(";" + ingredientesPasta);
                }
                pw.println();
            }
        }
    }


    public List<Producto> importarProductos() throws IOException {
        List<Producto> listaProductos = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSVProductos))) {
            String linea;
            // Leer la primera línea para omitir el encabezado
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";"); // Divide la línea por ";"
                // Asegúrate de que la longitud de datos sea suficiente
                if (datos.length < 6) {
                    System.out.println(linea);
                    continue; // Saltar esta línea si no tiene las columnas requeridas
                }
                // Obtener los datos básicos
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double precio = Double.parseDouble(datos[2]);
                String tamano = datos[3]; // Tamaño, puede ser vacío
                String ingredientesStr = datos[4]; // IDs de los ingredientes
                String alergenosStr = datos[5]; // Alérgenos, puede ser vacío
                // Crear producto dependiendo de los datos
                if (!tamano.isEmpty() && !ingredientesStr.isEmpty()) {
                    // Es una Pizza
                    List<Ingrediente> ingredientes = obtenerIngredientesPorIds(ingredientesStr, alergenosStr);
                    listaProductos.add(new Pizza(id, nombre, precio, SIZE.valueOf(tamano), ingredientes));
                } else if (!tamano.isEmpty()) {
                    // Es una Bebida
                    listaProductos.add(new Bebida(id, nombre, precio, SIZE.valueOf(tamano)));
                } else if (!ingredientesStr.isEmpty()) {
                    // Es una Pasta
                    List<Ingrediente> ingredientes = obtenerIngredientesPorIds(ingredientesStr, alergenosStr);
                    listaProductos.add(new Pasta(id, nombre, precio, ingredientes));
                }
            }
        }
        return listaProductos;
    }



    // Método para obtener los ingredientes por sus IDs
    private List<Ingrediente> obtenerIngredientesPorIds(String ingredientesStr, String alergenosStr) {
        List<Ingrediente> ingredientes = new ArrayList<>();
        String[] idsArray = ingredientesStr.split(","); // Divide por comas

        for (String idStr : idsArray) {
            int id = Integer.parseInt(idStr.trim()); // Obtener el ID del ingrediente

            // Crear un nuevo Ingrediente con ID, nombre y alérgenos
            Ingrediente ingrediente = new Ingrediente(id, "NombreDelIngrediente", List.of()); // Solo ejemplo
            ingredientes.add(ingrediente); // Añadir ingrediente
        }
        return ingredientes;
    }


    //-----------------------------------------------------------------XML-----------------------------------------------------------------//
    public void exportarProductosXML(List<Producto> listaProductos) throws JAXBException {
        // Crea el contexto de JAXB para las clases anotadas
        JAXBContext context = JAXBContext.newInstance(ProductosWrapper.class, Pizza.class, Bebida.class, Pasta.class);
    
        // Crea el wrapper y añade los productos
        ProductosWrapper wrapper = new ProductosWrapper();
        wrapper.setProductos(listaProductos);
    
        // Crea el marshaller para convertir el objeto a XML
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);  // Formato bonito del XML
    
        // Escribe el XML al archivo
        marshaller.marshal(wrapper, new File("productos.xml"));
    }



    public List<Producto> importarProductosXML() throws JAXBException {
        // Crear el contexto JAXB para ProductosWrapper y sus clases relacionadas
        JAXBContext context = JAXBContext.newInstance(ProductosWrapper.class, Pizza.class, Pasta.class, Bebida.class, Ingrediente.class);
    
        // Crear un Unmarshaller para convertir XML a objetos
        Unmarshaller unmarshaller = context.createUnmarshaller();
    
        // Deserializar el XML en un objeto ProductosWrapper
        ProductosWrapper wrapper = (ProductosWrapper) unmarshaller.unmarshal(new File(archivoXMLProductos));
    
        // Devolver la lista de productos
        return wrapper.getProductos();
    }

    //------------------------------------------------------------------------------Exportar e importar pizzas------------------------------------------------------------------------------//
    //CSV
    public void exportarPizzaCSV(List<Pizza> listaPizzas) throws FileNotFoundException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        try (PrintWriter pw = new PrintWriter(archivoPizzasCSV);) {
            StatefulBeanToCsv<Pizza> beanToCsv = new StatefulBeanToCsvBuilder<Pizza>(pw).withSeparator(';').build();
            beanToCsv.write(listaPizzas);
        }
    }
    //leer CSV
    public List<Pizza> importarPizzasCSV() throws FileNotFoundException, IOException {
        List<Pizza> listaPizzasDevolver = new ArrayList<>();
        try (FileReader fileReader = new FileReader(archivoPizzasCSV)) {
            CsvToBean<Pizza> csvToBean = new CsvToBeanBuilder<Pizza>(fileReader).withType(Pizza.class).withSeparator(';').withIgnoreLeadingWhiteSpace(true).build();

            listaPizzasDevolver = csvToBean.parse();
        }
        return listaPizzasDevolver;
    }

    //exportar XML 
    public void exportamosPizzasXML(List<Pizza> listaPizzas) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(PizzasWrapper.class,Pizza.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        File f = new File(archivoPizzasXML);
        PizzasWrapper p = new PizzasWrapper(listaPizzas);
        marshaller.marshal(p, f);
    }



    //importar XML
    public List<Pizza> importacionPizzasXml() throws JAXBException, FileNotFoundException {
        List<Pizza> listaPizzas = new ArrayList<>();

        JAXBContext context = JAXBContext.newInstance(PizzasWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        PizzasWrapper pizzas = (PizzasWrapper) unmarshaller.unmarshal(new FileReader(archivoPizzasXML));

        listaPizzas.addAll(pizzas.getListaPizzas());

        return listaPizzas;
    }



}