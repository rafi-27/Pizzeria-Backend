package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
