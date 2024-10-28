package ies.thiar.Controlador;

import java.io.*;
import java.util.*;

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


// import com.opencsv.bean.CsvToBean;
// import com.opencsv.bean.CsvToBeanBuilder;
// import com.opencsv.bean.StatefulBeanToCsv;
// import com.opencsv.bean.StatefulBeanToCsvBuilder;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.Ingrediente;

import java.util.stream.Stream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


public class GestionFicheros {
    private final String archivoXML = "Clientes.xml";
    private final String archivoAdmin = "admin.txt";
    private final String archivoCSV = "Ingredientes.csv";

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
     * (3 puntos) Actividad 1. Gestión básica de ficheros.
     * 
     * @return gestionBasicaDeFicheros
     * @throws IOException
     */
    public List<Cliente> gestionBasicaDeFicheros() throws IOException {
        File file = new File(archivoAdmin);
        List<String> lineas = new ArrayList();

        String primeraLinea = "";
        String segundaLinea = "";
        String terceraLinea = "";

        if (file.exists()) {
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            String linea;

            while ((linea = bf.readLine()) != null) {
                lineas.add(linea);
            }

            primeraLinea = lineas.get(0);
            segundaLinea = lineas.get(1);
            terceraLinea = lineas.get(2);
        }

        // hacemos los split aqui:
        String[] primera = primeraLinea.split(";");
        String[] segunda = segundaLinea.split(" \\| ");
        String[] tercera = terceraLinea.split(",");

        // vaciamos la lista para pasarle los datos correctamente.
        lineas.clear();

        // Corregimos
        for (int i = 0; i < primera.length; i++) {
            primera[i] = primera[i].trim();
        }

        for (int i = 0; i < segunda.length; i++) {
            segunda[i] = segunda[i].trim();
        }

        for (int i = 0; i < tercera.length; i++) {
            tercera[i] = tercera[i].trim();
        }

        // probamos mapearlos a clientes.
        List<Cliente> listaClientes = new ArrayList();

        // Añadimos los clientes que ya tenemos sus datos correctos.
        listaClientes.add(new Cliente(Integer.parseInt(primera[0]), String.valueOf(primera[1]),
                String.valueOf(primera[2]), String.valueOf(primera[3]), String.valueOf(primera[4]),
                String.valueOf(primera[5]), String.valueOf(primera[6]), true));
        listaClientes.add(new Cliente(Integer.parseInt(segunda[0]), String.valueOf(segunda[1]),
                String.valueOf(segunda[2]), String.valueOf(segunda[3]), String.valueOf(segunda[4]),
                String.valueOf(segunda[5]), String.valueOf(segunda[6]), true));
        listaClientes.add(new Cliente(Integer.parseInt(tercera[0]), String.valueOf(tercera[1]),
                String.valueOf(tercera[2]), String.valueOf(tercera[3]), String.valueOf(tercera[4]),
                String.valueOf(tercera[5]), String.valueOf(tercera[6]), true));

        return listaClientes;

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

    /*Aqui directamente no le paso el parametro del nombre del fichero, tengo una variable static
    con el nombre del ficher*/

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
        List<Ingrediente>listaClientesDevolver = new ArrayList<>();
        try (FileReader fileReader = new FileReader(archivoCSV)) {
            CsvToBean<Ingrediente> csvToBean = new CsvToBeanBuilder<Ingrediente>(fileReader).withType(Ingrediente.class).withSeparator(';').withIgnoreLeadingWhiteSpace(true).build();

            listaClientesDevolver = csvToBean.parse();
        }
        return listaClientesDevolver;
    }
    
    public void exportarIngredienteCSV(List<Ingrediente> listaIngredientes)
            throws FileNotFoundException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        try (PrintWriter pw = new PrintWriter(archivoCSV);) {
            StatefulBeanToCsv<Ingrediente> beanToCsv = new StatefulBeanToCsvBuilder<Ingrediente>(pw).withSeparator(';').build();
            beanToCsv.write(listaIngredientes);
        }
    }
}