package ies.thiar.Modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public abstract class Producto {
    @XmlAttribute
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    private String nombre;
    private double precio;

    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;
    
    @Enumerated(EnumType.STRING)
    private SIZE tamanyo;
    
    public Producto(String nombre, double precio, TipoProducto tipe,SIZE tamany) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipe;
        this.tamanyo=tamany;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public SIZE getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(SIZE tamanyo) {
        this.tamanyo = tamanyo;
    }

    public Producto(String nombre, double precio, TipoProducto tipe) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipe;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public Producto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
    }
}