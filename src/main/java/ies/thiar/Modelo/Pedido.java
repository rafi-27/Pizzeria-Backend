package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private int id;
    private Date fecha;
    private double precioTotal;
    private EstadoPedido estado;
    private List<LineaPedido>lineaPedido;
    //private Pagable pago;
    private Pagable pago;
    private int cliente;

    public Pedido(int client) {
        this.fecha = new Date();
        this.precioTotal = 0;
        this.estado=EstadoPedido.PENDIENTE;
        this.cliente=client;
        this.lineaPedido = new ArrayList<>();
    }

    

    public Pedido(int id, Date fecha, double precioTotal, EstadoPedido estado, Pagable pago, int cliente) {
        this.id = id;
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.estado = estado;
        this.pago = pago;
        this.cliente = cliente;
        this.lineaPedido = new ArrayList<>();
    }

    public Pedido(Date fecha, double precioTotal, Pagable pago, int cliente) {
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.estado = EstadoPedido.PENDIENTE;
        this.pago = pago;
        this.cliente = cliente;
        this.lineaPedido = new ArrayList<>();
    }

    public Pedido(Date fecha, double precioTotal,EstadoPedido estadoPedido, Pagable pago, int cliente) {
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.estado = estadoPedido;
        this.pago = pago;
        this.cliente = cliente;
        this.lineaPedido = new ArrayList<>();
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

    public double getPrecioTotal(){
        return lineaPedido.stream().mapToDouble(LineaPedido::getPrecioSubtotal).sum();
    }

    public int getCliente(){
        return this.cliente;
    }

    @Override
    public String toString() {
        return "Pedido [id=" + id + ", fecha=" + fecha + ", precioTotal=" + precioTotal + ", estado=" + estado
                + ", pago=" + pago + ", cliente=" + cliente + "]";
    }

    public List<LineaPedido> getLineaPedido() {
        return lineaPedido;
    }

    public void setLineaPedido(List<LineaPedido> lineaPedido) {
        this.lineaPedido = lineaPedido;
    }

    public Pagable getPago() {
        return pago;
    }

    public int getMetodoPagoCeroOuno(){
        return pago.formaPago();
    }

    //metodo para finalizar
    public void finalizarPedido(){
        setEstado(EstadoPedido.FINALIZADO);
        
    }
}