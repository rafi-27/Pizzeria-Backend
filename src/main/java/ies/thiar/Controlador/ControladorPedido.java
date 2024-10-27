package ies.thiar.Controlador;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.Pagable;
import ies.thiar.Modelo.Pedido;
import ies.thiar.Modelo.Producto;

public class ControladorPedido {
    private static Pedido pedidoActual;
    //si es null no lo creo si no lo creo
    //pedidoActual
    //Flujo estados: Pendiente, cancelar o finalizar si es finalizar entregado

    
    //agregarLineaPedido(Producto p)
    public static void agregarLineaPedido(Producto producto,int cantidad,Cliente clienteActual) throws IllegalAccessException{
        if(pedidoActual==null){
            pedidoActual = new Pedido(clienteActual);
            pedidoActual.setEstado(EstadoPedido.PENDIENTE);
            pedidoActual.anyadirCarrito(producto, cantidad);
            
        }
        
        if(pedidoActual.getCliente()!=clienteActual){
            throw new IllegalAccessException("No se puede hacer el pedido sin logearte correctamente.");
        }else{
            pedidoActual.anyadirCarrito(producto, cantidad);
            
        }
    }
    
    
    //finalizarPedido(Pagable metodoApaar) entregado y gestionar forma de pago.
    public static void finalizarPedido(Pagable metodoPagar){
        if (pedidoActual.getEstado()==EstadoPedido.ENTREGADO) {
            System.err.println("El pedido ya fue pagado bro");
        }else{
            metodoPagar.pagar(pedidoActual.getPrecioTotal());
            pedidoActual.setPago(metodoPagar);
            pedidoActual.setEstado(EstadoPedido.FINALIZADO);
            pedidoActual = null;
        }
    }

    //entregarPedido() para comprobar el estado.
    public void entregado(){
        pedidoActual.setEstado(EstadoPedido.ENTREGADO);
        pedidoActual = null;
    }

    //cancelarPedido() para cancelar 
    public void cancelarPedido(){
        pedidoActual.setEstado(EstadoPedido.CANCELADO);
        pedidoActual = null;
    }

    public static Pedido pedidoAct(){
        return pedidoActual;
    }
}