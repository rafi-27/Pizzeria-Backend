package ies.thiar.Modelo;

import jakarta.persistence.Entity;

@Entity
public class Bebida extends Producto {

    public Bebida() {}

    public Bebida(String nombre, double precio, SIZE tamanyo) {
        super(nombre, precio, TipoProducto.BEBIDA, tamanyo);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}