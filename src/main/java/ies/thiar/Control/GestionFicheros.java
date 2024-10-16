package ies.thiar.Control;
import java.io.*;
public class GestionFicheros {
    
    public void gestionBasicaDeFicheros() throws IOException{
        File file = new File("admin.txt"); 

        if (file.exists()) { 
            FileReader fr = new FileReader(file); 
            BufferedReader bf = new BufferedReader(fr); 
            String linea; 
            
            String[] primeraLinea = new String[0];
            String[] segundaLinea = new String[0];
            String[] terceraLinea = new String[0];

            while ((linea = bf.readLine()) != null ){ 
                System.out.println(linea.trim());
                
                







            } 
        }





    }







}
