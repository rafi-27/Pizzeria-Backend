package ies.thiar;

import java.io.IOException;

import ies.thiar.Control.GestionFicheros;

public class MainPruebas {
    public static void main(String[] args) {
        GestionFicheros gestor = new GestionFicheros();
        try {
            gestor.gestionBasicaDeFicheros();
        } catch (IOException e) {
            e.printStackTrace();
        }

        


    }
}
