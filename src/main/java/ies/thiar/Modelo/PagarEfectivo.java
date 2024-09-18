package ies.thiar.Modelo;

public class PagarEfectivo implements Pagable{
    @Override
    public void pagar(double cantidad) {
        System.out.println("Has pagado en efectivo: "+cantidad);
    }
}
