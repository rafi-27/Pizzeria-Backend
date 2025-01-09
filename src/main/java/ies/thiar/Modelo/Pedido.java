package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date fecha;
    private double precioTotal;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @OneToMany(mappedBy="pedido", cascade = CascadeType.ALL)
    private List<LineaPedido>lineaPedido;
    //private Pagable pago;
    @Enumerated(EnumType.STRING)
    private FormaPago pago;

    //    private Pagable pago;
    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    public Pedido(Cliente client) {
        this.fecha = new Date();
        this.precioTotal = 0;
        this.estado=EstadoPedido.PENDIENTE;
        this.cliente=client;
        this.lineaPedido = new ArrayList<>();
    }

    public Pedido(int id, Date fecha, double precioTotal, EstadoPedido estado, FormaPago pago, Cliente cliente) {
        this.id = id;
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.estado = estado;
        this.pago = pago;
        this.cliente = cliente;
        this.lineaPedido = new ArrayList<>();
    }
    /**
     * public Pedido(int id, Date fecha, double precioTotal, EstadoPedido estado, Pagable pago, Cliente cliente) {
        this.id = id;
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.estado = estado;
        this.pago = pago;
        this.cliente = cliente;
        this.lineaPedido = new ArrayList<>();
    }
     * @param fecha
     * @param precioTotal
     * @param pago
     * @param cliente
     */

    public Pedido(Date fecha, double precioTotal, FormaPago pago, Cliente cliente) {
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.estado = EstadoPedido.PENDIENTE;
        this.pago = pago;
        this.cliente = cliente;
        this.lineaPedido = new ArrayList<>();
    }
    /**
     * public Pedido(Date fecha, double precioTotal, Pagable pago, Cliente cliente) {
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.estado = EstadoPedido.PENDIENTE;
        this.pago = pago;
        this.cliente = cliente;
        this.lineaPedido = new ArrayList<>();
    }
     * @param fecha
     * @param precioTotal
     * @param estadoPedido
     * @param pago
     * @param cliente
     */

    public Pedido(Date fecha, double precioTotal,EstadoPedido estadoPedido, FormaPago pago, Cliente cliente) {
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.estado = estadoPedido;
        this.pago = pago;
        this.cliente = cliente;
        this.lineaPedido = new ArrayList<>();
    }
    /**
     * public Pedido(Date fecha, double precioTotal,EstadoPedido estadoPedido, Pagable pago, Cliente cliente) {
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.estado = estadoPedido;
        this.pago = pago;
        this.cliente = cliente;
        this.lineaPedido = new ArrayList<>();
    }
     * @return
     */

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

    public void setPago(FormaPago pago) {
        this.pago = pago;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }



    public double getPrecioTotal(){
        return lineaPedido.stream().mapToDouble(LineaPedido::getPrecioSubtotal).sum();
    }

    public Cliente getCliente(){
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

    public FormaPago getPago() {
        return pago;
    }

    // public int getMetodoPagoCeroOuno(){
    //     return pago.formaPago();
    // }

    //metodo para finalizar
    public void finalizarPedido(){
        setEstado(EstadoPedido.FINALIZADO);
        
    }
}