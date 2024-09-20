package ies.thiar.Control;

import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.Pedido;

public class ControladorPedido {
    private Pedido pedidoActual;
    //si es null no lo creo si no lo creo
    //pedidoActual

    //finalizarPedido(Pagable metodoApaar) entregado y gestionar forma de pago.
    
    
    
    //entregarPedido() para 

    //agregarLineaPedido(Producto p)




    //cancelarPedido() para cancelar 
    public void cancelarPedido(){
        pedidoActual.setEstado(EstadoPedido.CANCELADO);
        pedidoActual = null;
    }


}