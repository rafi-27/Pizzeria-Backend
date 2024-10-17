package ies.thiar.Control;
import java.io.*;
import java.util.*;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.ClienteWrape;;
public class GestionFicheros {
    
    public void gestionBasicaDeFicheros() throws IOException{
        File file = new File("admin.txt"); 
        List<String> lineas = new ArrayList();

        String primeraLinea="";
        String segundaLinea="";
        String terceraLinea="";
        
        if (file.exists()) { 
            FileReader fr = new FileReader(file); 
            BufferedReader bf = new BufferedReader(fr); 
            String linea;

            while ((linea = bf.readLine()) != null ){ 
                lineas.add(linea);
            }
            
            primeraLinea = lineas.get(0);
            segundaLinea = lineas.get(1);
            terceraLinea = lineas.get(2);
        }

        //hacemos los split aqui:
        String[] primera = primeraLinea.split(";");
        String[] segunda = segundaLinea.split(" \\| ");
        String[] tercera = terceraLinea.split(",");

        //vaciamos la lista para pasarle los datos correctamente.
        lineas.clear();
        
        //Corregimos
        for (int i = 0; i < primera.length; i++) {
            primera[i] = primera[i].trim();
        }

        for (int i = 0; i < segunda.length; i++) {
            segunda[i] = segunda[i].trim();
        }

        for (int i = 0; i < tercera.length; i++) {
            tercera[i] = tercera[i].trim();
        }

        //probamos mapearlos a clientes.
        List<Cliente>listaClientes = new ArrayList();

        //Añadimos los clientes que ya tenemos sus datos correctos.
        listaClientes.add(new Cliente(Integer.parseInt(primera[0]),String.valueOf(primera[1]), String.valueOf(primera[2]), String.valueOf(primera[3]), String.valueOf(primera[4]), String.valueOf(primera[5]), String.valueOf(primera[6]), true));
        listaClientes.add(new Cliente(Integer.parseInt(segunda[0]),String.valueOf(segunda[1]), String.valueOf(segunda[2]), String.valueOf(segunda[3]), String.valueOf(segunda[4]), String.valueOf(segunda[5]), String.valueOf(segunda[6]), true));
        listaClientes.add(new Cliente(Integer.parseInt(tercera[0]),String.valueOf(tercera[1]), String.valueOf(tercera[2]), String.valueOf(tercera[3]), String.valueOf(tercera[4]), String.valueOf(tercera[5]), String.valueOf(tercera[6]), true));


        System.out.println(listaClientes.size());
        for (Cliente client : listaClientes) {
            System.out.println(client.toString());
        }

    }

    //Exportamos en xml:
    public void exportarAXml(){

    }

    public void convertimosAXml(List<Cliente> listaPerson, String nombre) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Cliente.class, ClienteWrape.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

        File f = new File(nombre);
        ClienteWrape p = new ClienteWrape(listaPerson);
        marshaller.marshal(p,f);
    }











}