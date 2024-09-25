package ies.thiar.Modelo;

import ies.thiar.Control.ControladorCliente;
import ies.thiar.Control.ControladorPedido;

public class Pruebas {
    public static void main(String[] args) {
        //Creamos un cliente:
        Cliente cliente1 = new Cliente(0,"22222222e", "Rafe","calle real madrid","632548220","null@gmail.com", "1234");

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
        
        controlCliente.agregarLineaPedido(pizzaGambas, 3);
        controlCliente.agregarLineaPedido(pizzaQueso, 6);

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
