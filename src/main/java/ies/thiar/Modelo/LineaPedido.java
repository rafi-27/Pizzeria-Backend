package ies.thiar.Modelo;

import com.opencsv.bean.CsvBindByName;

public class LineaPedido {

    //@CsvBindAndSplitByName(column = "IDENTIFICATION", writeDelimiter = ",", elementType = Integer.class)
    @CsvBindByName(column = "IDENTIFICATION")
    private int id;
    
    @CsvBindByName(column = "QUANTITY")
    private int cantidad;

    private Producto p;
    private Pedido pedido;

    public LineaPedido(int ide, int cantidad) {
        //this.p=a;
        this.id=ide;
        this.cantidad = cantidad;
    }
    public LineaPedido(){}

    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioSubtotal(){
        return p.getPrecio()*cantidad;
    }

    @Override
    public String toString() {
        return "LineaPedido [id=" + id + ", cantidad=" + cantidad + "]";
    }
}