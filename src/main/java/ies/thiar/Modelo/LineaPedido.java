package ies.thiar.Modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class LineaPedido {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    
    private int cantidad;

    @OneToOne
    private Producto product;
        
    @ManyToOne(cascade = CascadeType.ALL)
    private Pedido pedido;

    public LineaPedido(int ide, int cantidad) {
        //this.p=a;
        this.id=ide;
        this.cantidad = cantidad;
    }

    public void setProduct(Producto product) {
        this.product = product;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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