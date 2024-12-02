package ies.thiar.ClasesEnDeshuso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.LineaPedido;
import ies.thiar.Modelo.Pagable;
import ies.thiar.Modelo.Pedido;

public class ControladorPedido {
    private static Pedido pedidoActual;
    //si es null no lo creo si no lo creo
    //pedidoActual
    //Flujo estados: Pendiente, cancelar o finalizar si es finalizar entregado

    GestionFicheros gestor = new GestionFicheros();
    
    //agregarLineaPedido(Producto p)
    // public static void agregarLineaPedido(Producto producto,int cantidad,Cliente clienteActual) throws IllegalAccessException{
    //     if(pedidoActual==null){
    //         pedidoActual = new Pedido(clienteActual);
    //         pedidoActual.setEstado(EstadoPedido.PENDIENTE);
    //         pedidoActual.anyadirCarrito(producto, cantidad);
            
    //     }
        
    //     if(pedidoActual.getCliente()!=clienteActual){
    //         throw new IllegalAccessException("No se puede hacer el pedido sin logearte correctamente.");
    //     }else{
    //         pedidoActual.anyadirCarrito(producto, cantidad);
            
    //     }
    // }
    
    
    //finalizarPedido(Pagable metodoApaar) entregado y gestionar forma de pago.
    public static void finalizarPedido(Pagable metodoPagar){
        if (pedidoActual.getEstado()==EstadoPedido.ENTREGADO) {
            System.err.println("El pedido ya fue pagado bro");
        }else{
            metodoPagar.pagar(pedidoActual.getPrecioTotal());
            //pedidoActual.setPago(metodoPagar);
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

    //////////////////////////////////////////Examen//////////////////////////////////////////
    public void exportarLineasDePedido(List<LineaPedido>listaLineaPedido) throws FileNotFoundException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException{
        gestor.exportarLineaPedidoCSV(listaLineaPedido);
    }

     public List<LineaPedido> importarLineaPedidosExam() throws FileNotFoundException, IOException{
        return  gestor.importarLineaPedidoExam();
     }
    //////////////////////////////////////////Examen//////////////////////////////////////////
}