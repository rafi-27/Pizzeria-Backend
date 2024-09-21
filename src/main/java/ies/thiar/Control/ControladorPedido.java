package ies.thiar.Control;

import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.Pedido;
import ies.thiar.Modelo.Producto;

public class ControladorPedido {
    private Pedido pedidoActual;
    //si es null no lo creo si no lo creo
    //pedidoActual

    
    //agregarLineaPedido(Producto p)
    public void agregarLineaPedido(Producto producto,int cantidad){
        if(pedidoActual==null){
            pedidoActual = new Pedido();
            pedidoActual.setEstado(EstadoPedido.PENDIENTE);
            pedidoActual.anyadirCarrito(producto, cantidad);
        }else{
            pedidoActual.anyadirCarrito(producto, cantidad);
            
        }
    }
    
    
    
    
    
    
    
    
    
    //finalizarPedido(Pagable metodoApaar) entregado y gestionar forma de pago.
    public void finalizarPedido(){
        pedidoActual.setEstado(EstadoPedido.FINALIZADO);
        pedidoActual = null;
    }
    
    //entregarPedido() para 
    public void entregado(){
        pedidoActual.setEstado(EstadoPedido.ENTREGADO);
        pedidoActual = null;
    }

    //cancelarPedido() para cancelar 
    public void cancelarPedido(){
        pedidoActual.setEstado(EstadoPedido.CANCELADO);
        pedidoActual = null;
    }


}