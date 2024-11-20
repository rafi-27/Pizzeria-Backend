package ies.thiar.ClasesEnDeshuso;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ies.thiar.Modelo.Producto;

@XmlRootElement(name = "productos")
public class ProductosWrapper {
    private List<Producto> productos;

    @XmlElement(name = "producto")
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}

