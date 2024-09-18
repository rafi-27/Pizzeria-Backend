package ies.thiar.Modelo;

public class Bebida extends Producto{
    public enum SIZE {GRANDE,PEQUEÑA,MEDIANA}
    private SIZE tamanyo;

    public Bebida(int id, String nombre, double precio,SIZE tam) {
        super(id, nombre, precio);
        this.tamanyo=tam;
    }
}
