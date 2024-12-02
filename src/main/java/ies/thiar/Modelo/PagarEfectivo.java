package ies.thiar.Modelo;

public class PagarEfectivo implements Pagable{
    @Override
    public void pagar(double cantidad) {
        System.out.println("Pagas en efectivo: "+cantidad);
    }

    @Override
    public int formaPago() {
        return 1;
    }
}
