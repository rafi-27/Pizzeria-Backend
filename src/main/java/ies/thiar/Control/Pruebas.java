package ies.thiar.Control;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.PagarEfectivo;
import ies.thiar.Modelo.PagarTarjeta;
import ies.thiar.Modelo.Pedido;
import ies.thiar.Modelo.Pizza;
import ies.thiar.Modelo.Producto;
import ies.thiar.Modelo.SIZE;

public class Pruebas {
    public static void main(String[] args) {
        //Creamos un cliente:
        Cliente cliente1 = new Cliente(0,"22222222e", "Rafe","calle real madrid","632548220","null@gmail.com", "1234",false);
        Cliente cliente2 = new Cliente(0,"22222222e", "Rafe","calle real madrid","632548220","null@gmail.com", "1234",false);

        //Pantalla inicio
        ControladorCliente controlCliente = new ControladorCliente();
        controlCliente.registrarCliente(cliente1);

        if (controlCliente.loginCliente("null@gmail.com","1234")) {
            System.out.println("Existe");
        }else{
            System.err.println("No existe");
        }

        //Empezamos a crear pedido:
        
        Producto pizzaGambas = new Pizza(0, "Pizza de Gambas",15, SIZE.GRANDE);
        Producto pizzaKebab = new Pizza(1, "Pizza de Kebab",8.50, SIZE.MEDIANA);
        Producto pizzaAtun = new Pizza(8, "Pizza de Atun",9, SIZE.PEQUEÑA);
        Producto pizzaQueso = new Pizza(14, "Pizza de 4 quesos",7.50, SIZE.GRANDE);

        
        try {
            controlCliente.agregarLineaPedido(pizzaGambas, 3,cliente1);
            controlCliente.agregarLineaPedido(pizzaQueso, 6,cliente1);
            controlCliente.agregarLineaPedido(pizzaQueso, 1, cliente2);
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        Pedido pedido = ControladorPedido.pedidoAct();
        
        pedido.anyadirCarrito(pizzaKebab,2);
        pedido.anyadirCarrito(pizzaAtun,1);
        
        //Probamos finalizar pedido:
        PagarEfectivo pagarEfectivo = new PagarEfectivo();
        PagarTarjeta pagarTarjeta = new PagarTarjeta();

        ControladorPedido.finalizarPedido(pagarEfectivo);

        System.out.println(pedido);
        


        Pedido dos = ControladorPedido.pedidoAct();
        
    }
}
