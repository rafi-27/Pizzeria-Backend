package ies.thiar.Control;

import java.io.*;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

// import com.opencsv.bean.CsvToBean;
// import com.opencsv.bean.CsvToBeanBuilder;
// import com.opencsv.bean.StatefulBeanToCsv;
// import com.opencsv.bean.StatefulBeanToCsvBuilder;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.ClienteWrape;;

public class GestionFicheros {
    private static final String archivoXML = "nuevoFichero.xml";
    private static final String archivoAdmin = "admin.txt";

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
    public void convertimosAXml(List<Cliente> listaPerson, String nombre) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Cliente.class, ClienteWrape.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        File f = new File(nombre);
        ClienteWrape p = new ClienteWrape(listaPerson);
        marshaller.marshal(p, f);
    }

    public List<Cliente> importacionXml() throws JAXBException, FileNotFoundException {
        List<Cliente> listaClientes = new ArrayList<>();

        JAXBContext context = JAXBContext.newInstance(ClienteWrape.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ClienteWrape clientes = (ClienteWrape) unmarshaller.unmarshal(new FileReader(archivoXML));

        listaClientes.addAll(clientes.getListaPersonas());

        // clientes.getListaPersonas().forEach(cliente -> System.out.println(cliente));
        return listaClientes;
    }

    /**
     * (4 puntos) Actividad 3. OpenCSV
     * 
     * @return
     * @throws JAXBException
     * @throws FileNotFoundException
     */

    // public void leerClienteCSV(String nombre){
    // try (FileReader fileReader = new FileReader(nombre)) {
    // CsvToBean<Cliente> csvToBean = new CsvToBeanBuilder<Cliente>(fileReader)
    // .withType(Cliente.class).build();

    // List<Cliente> listaClientes = csvToBean.parse();
    // System.out.println(listaClientes.size());

    // listaClientes.forEach(cliente -> System.out.println(cliente.toString()));
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }


    

    // public void exportarClienteCSV(String nombre,List<Cliente>listaClientes){
    // try (PrintWriter pw = new PrintWriter(nombre+".csv")) {
    // StatefulBeanToCsv<Cliente> beanToCsv = new
    // StatefulBeanToCsvBuilder<Cliente>(pw).build();
    // beanToCsv.write(listaClientes);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }

    // }

}