package ies.thiar.ClasesEnDeshuso;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ies.thiar.Modelo.Cliente;

@XmlRootElement(name = "Clientes")
@XmlAccessorType(XmlAccessType.FIELD)

public class ClienteWrape {
    @XmlElement(name = "Cliente")
    private List<Cliente> listaPersonas = new ArrayList<>();

    public ClienteWrape() {}

    public ClienteWrape(List<Cliente> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public void setListaPersonas(List<Cliente> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public List<Cliente> getListaPersonas() {
        return listaPersonas;
    }
}