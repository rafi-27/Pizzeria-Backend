package ies.thiar.Modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bebida")
public class Bebida extends Producto{
    
    //@CsvBindByName(column = "TamanyoB")
    private SIZE tamanyo;
    

    public Bebida(int id, String nombre, double precio,SIZE tam) {
        super(nombre, precio);
        this.tamanyo=tam;
    }

    public Bebida() {}

    public SIZE getTamanyo() {
        return tamanyo;
    }
    
}
