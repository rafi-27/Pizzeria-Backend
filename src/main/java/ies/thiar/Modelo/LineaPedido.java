package ies.thiar.Modelo;

public class LineaPedido {
    private int id;
    private int cantidad;
    private Producto p;

    private Pedido pedido;

    public LineaPedido(Producto a, int cantidad) {
        this.p=a;
        this.cantidad = cantidad;
    }

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

}