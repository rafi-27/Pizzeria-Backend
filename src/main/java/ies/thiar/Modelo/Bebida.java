package ies.thiar.Modelo;

import javax.xml.bind.annotation.XmlRootElement;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@XmlRootElement(name = "bebida")
@Entity
public class Bebida extends Producto{
    
    //@CsvBindByName(column = "TamanyoB")
    @Enumerated(EnumType.STRING)
    private SIZE tamanyo;
    

    public Bebida(int id, String nombre, double precio,SIZE tam) {
        super(nombre, precio, TipoProducto.BEBIDA);
        this.tamanyo=tam;
    }

    public Bebida() {}

    public SIZE getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(SIZE tamanyo) {
        this.tamanyo = tamanyo;
    }
    
}
