package ies.thiar.Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "personas")
@XmlAccessorType(XmlAccessType.FIELD)

public class ClienteWrape {
    @XmlElement(name = "persona")
    private List<Cliente> listaPersonas = new ArrayList<>();

    public ClienteWrape() {}


    public ClienteWrape(List<Cliente> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public void setListaPersonas(List<Cliente> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }
}
