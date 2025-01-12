package ies.thiar.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="cliente_seq", sequenceName="hibernate_sequence", allocationSize=1)
public class PagarTarjeta extends  Pagable{

    @Override
    public void pagar(double cantidad) {
        System.out.println("Pagas en tarjeta: "+cantidad);
    }

    @Override
    public int formaPago() {
        return 0;
    }
}
