package ies.thiar.Modelo;

import com.opencsv.bean.CsvBindByName;

public class LineaPedido {

    //@CsvBindAndSplitByName(column = "IDENTIFICATION", writeDelimiter = ",", elementType = Integer.class)
    @CsvBindByName(column = "IDENTIFICATION")
    private int id;
    
    @CsvBindByName(column = "QUANTITY")
    private int cantidad;

    private Producto product;
    private Pedido pedido;

    public LineaPedido(int ide, int cantidad) {
        //this.p=a;
        this.id=ide;
        this.cantidad = cantidad;
    }

    public LineaPedido(int ide, int cantidad, Producto producto, Pedido pedido) {
        this.id=ide;
        this.cantidad = cantidad;
        this.product=producto;
        this.pedido=pedido;
    }

    public LineaPedido(int cantidad, Producto producto, Pedido pedido) {
        this.cantidad = cantidad;
        this.product=producto;
        this.pedido=pedido;
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
        return product.getPrecio()*cantidad;
    }

    public Producto getProduct() {
        return product;
    }

    public Pedido getPedido() {
        return pedido;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LineaPedido{");
        sb.append("id=").append(id);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", product=").append(product);
        sb.append(", pedido=").append(pedido);
        sb.append('}');
        return sb.toString();
    }

    public void setId(int id) {
        this.id = id;
    }

}