// package ies.thiar.ClasesEnDeshuso;

// import java.util.ArrayList;
// import java.util.List;

// import ies.thiar.Modelo.Cliente;
// import ies.thiar.Modelo.LineaPedido;

// public class ExamenMain {
//     public static void main(String[] args) {
//         try {

//             ControladorCliente controladorCliente = new ControladorCliente();
//             ControladorProducto controladorProducto = new ControladorProducto();

//             //Ejer 1:
// //             List<Pizza>listaPizzas = controladorProducto.importarListaPizzasExamen();
// //             listaPizzas.forEach(pizzas->System.out.println(pizzas.toString()));
// // System.out.println();
            

//             //Ejer2:
//         //     List<Cliente> listaClientes = new ArrayList<>();
//         //     listaClientes.add(new Cliente(8, "78879645E", "Rodri", "calle hgola",
//         //             "87654345678", "null@gmail.com",
//         //             "Hola", false));
//         //     listaClientes.add(new Cliente(1, "65784903R", "Rafe", "calle jnondf",
//         //             "87654345678", "null@gmail.com",
//         //             "Hola", false));
//         //     listaClientes.add(
//         //             new Cliente(2, "34566786T", "Jose", "calle utueiu", "87654345678",
//         //                     "null@gmail.com", "Hola", true));
//         //     listaClientes.add(
//         //             new Cliente(3, "76584903O", "Moha", "calle tuppq", "87654345678",
//         //                     "null@gmail.com", "Hola", false));
//         //     listaClientes.add(new Cliente(2, "34566786T", "Jose", "calle utueiu",
//         //             "87654345678", "null@gmail.com",
//         //             "Hola", false));
//         //     listaClientes.add(
//         //             new Cliente(3, "76584903O", "Moha", "calle tuppq", "87654345678",
//         //                     "null@gmail.com", "Hola", true));
//         //     //Exportar
//            // controladorCliente.exportarClientesAXMLExam(listaClientes);

//             //Importar
//             List<Cliente>listaClientesImportar = controladorCliente.importarClientesExam();
//             listaClientesImportar.forEach(cliente->System.out.println(cliente.toString()));


            
//             //Ejercicio3
//             List<LineaPedido>listaLineaPedidos = new ArrayList<>(){{
//                 add(new LineaPedido(1, 6));
//                 add(new LineaPedido(2, 234));
//                 add(new LineaPedido(3, 555));
//                 add(new LineaPedido(4, 1323));
//                 add(new LineaPedido(5, 312));
//             }};

//             ControladorPedido controladorPedido = new ControladorPedido();
//             controladorPedido.exportarLineasDePedido(listaLineaPedidos);

// System.out.println();
//             List<LineaPedido> listaLineaPedidosImportar = controladorPedido.importarLineaPedidosExam();
//             listaLineaPedidosImportar.forEach(cliente->System.out.println(cliente.toString()));



//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }
