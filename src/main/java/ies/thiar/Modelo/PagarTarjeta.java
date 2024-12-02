package ies.thiar.Modelo;

public class PagarTarjeta implements Pagable{

    @Override
    public Enum pagar(double cantidad) {
        System.out.println("Pagas en tarjeta: "+cantidad);
        return FormaPago.TARJETA;
    }
    
}
