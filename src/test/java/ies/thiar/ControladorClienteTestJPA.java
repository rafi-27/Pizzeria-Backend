package ies.thiar;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ies.thiar.Modelo.Cliente;
import ies.thiar.controlador.ControladorCliente;

public class ControladorClienteTestJPA {
    ControladorCliente controladorCliente;

    @BeforeEach
    void setUp() {
        controladorCliente = new ControladorCliente();
    }

    @Test
    public void registrarClienteTest() {
        Cliente cliente = new Cliente("12345678O", "Rodolfo Tebas", "calle dos santos averia", "122312321", "email@gmail.com", "1234");
        try {
            controladorCliente.registrarCliente(cliente);
            assertEquals(1, cliente.getId());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void borrarCliente(){
        try {
            Cliente cliente = new Cliente("12345678O", "Rodolfo Tebas", "calle dos santos averia", "122312321", "email@gmail.com", "1234");
            controladorCliente.registrarCliente(cliente);
            System.out.println(cliente+"-------------------------------------------");
            controladorCliente.deleteClient(1);
            Cliente clienteEliminado = controladorCliente.selectByID(1);
            assertEquals(null, clienteEliminado);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatear() throws SQLException {
        String dni = "55017724F";
        String nombreApellido = "Manolo Rodriguez";
        String telefono = "564879845";

        Cliente cliente = new Cliente(dni, nombreApellido, "calle calle 3", telefono, "email2@gmail.com", "1234");

        controladorCliente.registrarCliente(cliente);

        cliente.setNombre("Rafe");
        controladorCliente.updateCliente(cliente);
        assertEquals("Rafe", cliente.getNombre());
    }

    @Test
    public void selectAll() throws SQLException {
        try {
            Cliente cliente = new Cliente("111111111", "Rodolfo Tebas", "calle cinco santos averia", "000000000", "email1@gmail.com", "1234");
            Cliente cliente2 = new Cliente("222222222", "Javier Tebas", "calle tres santos averia", "121212121", "email3@gmail.com", "1234");
            Cliente cliente3 = new Cliente("333333333", "Manolo Tebas", "calle cuatro santos averia", "34443334", "email4@gmail.com", "1234");
    
            controladorCliente.registrarCliente(cliente);
            controladorCliente.registrarCliente(cliente2);
            controladorCliente.registrarCliente(cliente3);
    
            ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) controladorCliente.selectAll();

            listaClientes.forEach(clienteS -> System.out.println("DEBUG------>" + clienteS));
            assertEquals(3, listaClientes.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testLogin()throws SQLException{
        Cliente cliente = new Cliente("878713231", "Login login", "calle seis santos averia", "999999999", "email6@gmail.com", "1234");
        controladorCliente.registrarCliente(cliente);

        Cliente clienteLogin = controladorCliente.clienteLogin("email6@gmail.com", "1234");

        assertEquals("Login login", clienteLogin.getNombre());
    }

}
