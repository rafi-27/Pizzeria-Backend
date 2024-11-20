package ies.thiar.ClasesEnDeshuso;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ies.thiar.Modelo.Pizza;

@XmlRootElement(name = "Pizzas")
@XmlAccessorType(XmlAccessType.FIELD)

public class PizzasWrapper {
    @XmlElement(name = "Pizza")
    private List<Pizza> listaPizzas = new ArrayList<>();

    public PizzasWrapper() {}

    public PizzasWrapper(List<Pizza> listaPizzas) {
        this.listaPizzas=listaPizzas;
    }

    public void setListaPizzas(List<Pizza> listaPizzas) {
        this.listaPizzas = listaPizzas;
    }

    public List<Pizza> getListaPizzas() {
        return listaPizzas;
    }
}