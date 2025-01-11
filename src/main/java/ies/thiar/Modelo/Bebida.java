package ies.thiar.Modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Bebida extends Producto{
    // @Enumerated(EnumType.STRING)
    // private SIZE taman;
    

    public Bebida(int id, String nombre, double precio,SIZE tam) {
        super(nombre, precio, TipoProducto.BEBIDA,tam);
        //this.taman=tam;
    }

    public Bebida() {}

    // public SIZE getTamanyo() {
    //     return taman;
    // }

    // public void setTamanyo(SIZE tamanyo) {
    //     this.taman = tamanyo;
    // }
    
}
