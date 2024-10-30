package ies.thiar.Modelo;

import com.opencsv.bean.CsvBindByName;

public class Bebida extends Producto{
    
    @CsvBindByName(column = "TamanyoB")
    private SIZE tamanyo;

    public Bebida(int id, String nombre, double precio,SIZE tam) {
        super(id, nombre, precio);
        this.tamanyo=tam;
    }

    public SIZE getTamanyo() {
        return tamanyo;
    }
    
}
