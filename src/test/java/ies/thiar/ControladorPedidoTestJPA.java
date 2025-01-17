package ies.thiar;

import java.sql.SQLException;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.PagarTarjeta;
import ies.thiar.Modelo.Pedido;
import ies.thiar.controlador.ControladorPedido;

public class ControladorPedidoTestJPA {
    ControladorPedido controladorPedido;

    @BeforeEach
    void setUp() {
        controladorPedido = new ControladorPedido();
    }

    @Test
    public void registrarPedido() throws SQLException{
        Cliente cliente = new Cliente("12345678O", "Rodolfo Tebas", "calle dos santos averia", "122312321", "email@gmail.com", "1234");
        Pedido pedido = new Pedido(new Date(), 15, new PagarTarjeta(), cliente);

        controladorPedido.insertPedido(pedido);
    }




}
