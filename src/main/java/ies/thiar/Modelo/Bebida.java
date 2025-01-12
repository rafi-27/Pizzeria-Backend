package ies.thiar.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="cliente_seq", sequenceName="hibernate_sequence", allocationSize=1)
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