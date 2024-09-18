package ies.thiar.Modelo;

public class PagarTarjeta implements Pagable{

    @Override
    public void pagar(double cantidad) {
        System.out.println("Pagas en tarjeta: "+cantidad);
    }
    
}
