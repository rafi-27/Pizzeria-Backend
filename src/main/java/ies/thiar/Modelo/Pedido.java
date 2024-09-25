package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private int id;
    private Date fecha;
    private double precioTotal;
    private EstadoPedido estado;
    private List<LineaPedido>lineaPedido = new ArrayList<>();
    private Pagable pago;
    private Cliente cliente;

    public Pedido() {
        this.fecha = new Date();
        this.precioTotal = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public void setPago(Pagable pago) {
        this.pago = pago;
    }

    public void anyadirCarrito(Producto p, int canti){
        lineaPedido.add(new LineaPedido(p, canti));
    }

    public double getPrecioTotal(){
        return this.precioTotal+=lineaPedido.stream().mapToDouble(LineaPedido::getPrecioSubtotal).sum();
    }
}
