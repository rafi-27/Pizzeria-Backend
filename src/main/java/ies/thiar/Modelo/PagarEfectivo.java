package ies.thiar.Modelo;

public class PagarEfectivo implements Pagable{
    @Override
    public Enum pagar(double cantidad) {
        System.out.println("Pagas en efectivo: "+cantidad);
        return FormaPago.EFECTIVO;
    }
}
