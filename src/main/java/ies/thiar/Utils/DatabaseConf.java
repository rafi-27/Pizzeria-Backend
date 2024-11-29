package ies.thiar.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConf {
        /**
         * URL,User,Password,CreateTable
         */
        public static final String URL = "jdbc:mysql://localhost:3306/pizzeria";
        public static final String USUARIO = "root";
        public static final String PASSWORD = "admin";

        // Creamos tabla clientes:
        static final String CREATE_TABLE_CLIENTE = "CREATE TABLE IF NOT EXISTS CLIENTES( \r\n" + //
                        "    id int primary key Auto_Increment, \r\n" + //
                        "    dni VARCHAR(255) not null unique,   \r\n" + //
                        "    nombre VARCHAR(255) not null,     \r\n" + //
                        "    direccion VARCHAR(255) not null,     \r\n" + //
                        "    telefono VARCHAR(255) NULL UNIQUE,     \r\n" +
                        "    email VARCHAR(255) not NULL UNIQUE, \r\n" +
                        "    password VARCHAR(255) not NULL,\r\n" +
                        "    esAdministrador bool default false\r\n" +
                        ");";

        // Borramos tabla clientes:
        static final String DROP_TABLE_CLIENTE = "DROP TABLE IF EXISTS clientes";

        // Creamos tabla Producto
        static final String CREATE_TABLE_PRODUCT = "CREATE TABLE IF NOT EXISTS PRODUCTOS( \r\n" +
                        "    id int primary key Auto_Increment, \r\n" +
                        "    nombre VARCHAR(255) not null unique,   \r\n" +
                        "    precio double not null,     \r\n" +
                        "    tipo_Producto ENUM('PIZZA','PASTA','BEBIDA') not null,     \r\n" +
                        "    tamaño ENUM('GRANDE','MEDIANA','PEQUENYA') default null     \r\n" +
                        ");";

        // Borramos tabla producto:
        static final String DROP_TABLE_PRODUCT = "DROP TABLE IF EXISTS productos";

        // Creamos tabla Producto
        static final String CREATE_TABLE_ALERGENOS = "CREATE TABLE IF NOT EXISTS alergenos( \r\n" +
                        "    id int primary key Auto_Increment, \r\n" +
                        "    nombre VARCHAR(255) not null unique);";

        // Borramos tabla producto:
        static final String DROP_TABLE_ALERGENOS = "DROP TABLE IF EXISTS alergenos";

        // Creamos tabla Ingredientes
        static final String CREATE_TABLE_INGREDIENTE = "CREATE TABLE IF NOT EXISTS INGREDIENTES( \r\n" +
                        "    id int primary key Auto_Increment, \r\n" +
                        "    nombre VARCHAR(255) not null unique   \r\n" +
                        ");";

        // Borramos tabla producto:
        static final String DROP_TABLE_INGREDIENTE = "DROP TABLE IF EXISTS ingredientes";

        // tabla intermedia_Productos---Ingrediente
        static final String PRODUCTOS_INGREDIENTES = "CREATE TABLE IF NOT EXISTS PRODUCTOS_INGREDIENTES(id int primary key Auto_Increment, id_producto int, id_Ingrediente int, FOREIGN KEY(id_producto) references productos(id) on delete cascade on update cascade, Foreign key(id_Ingrediente) references ingredientes(id) on delete no action on update cascade);";
        // Borramos tabla producto:
        static final String DROP_PRODUCTOS_INGREDIENTES = "DROP TABLE IF EXISTS PRODUCTOS_INGREDIENTES";

        // tabla intermedia_Ingredientes---Alergenos
        static final String INGREDIENTES_ALERGENOS = "CREATE TABLE IF NOT EXISTS INGREDIENTES_ALERGENOS(id int primary key Auto_Increment, id_Ingrediente int, id_Alergenos int, FOREIGN KEY(id_Ingrediente) references ingredientes(id) on delete cascade on update cascade,Foreign key(id_Alergenos) references alergenos(id) on delete no action on update cascade);";
        // Borramos tabla producto:
        static final String DROP_INGREDIENTES_ALERGENOS = "DROP TABLE IF EXISTS INGREDIENTES_ALERGENOS";

        //Creamos la tabla de pedido
        static final String CREATE_TABLE_PEDIDO = "CREATE TABLE IF NOT EXISTS pedidos( \r\n" +
                        "    id int primary key Auto_Increment, \r\n" +
                        "    fecha Date not null,   \r\n" +
                        "    precio_total double not null,     \r\n" +
                        "    estado ENUM('PENDIENTE','FINALIZADO','ENTREGADO','CANCELADO') default 'PENDIENTE',     \r\n" +
                        "    forma_pago ENUM('TARJETA','EFECTIVO') null default null,     \r\n" +
                        "    id_cliente int not null,     \r\n" +
                        "    FOREIGN KEY(id_cliente) references clientes(id) on delete no action on update cascade" +
                        ");";

        //Borramos la tabla de pedido
        static final String DROP_TABLE_PEDIDO = "DROP TABLE IF EXISTS pedidos";

        //Creamos la tabla de linea pedido
        static final String CREATE_TABLE_LINEAPEDIDO = "CREATE TABLE IF NOT EXISTS linea_pedido( \r\n" +
                        "    id int primary key Auto_Increment, \r\n" +
                        "    cantidad int not null,   \r\n" +
                        "    id_producto int not null,     \r\n" +
                        "    id_pedido int not null,     \r\n" +
                        "    FOREIGN KEY(id_producto) references productos(id) on delete no action on update cascade,     \r\n" +
                        "    FOREIGN KEY(id_pedido) references pedidos(id) on delete no action on update cascade     \r\n" +
                        ");";

        //Borramos la tabla de linea pedido
        static final String DROP_TABLE_LINEAPEDIDO = "DROP TABLE IF EXISTS linea_pedido";

        public static void dropAndCreateTables() throws SQLException {
                try (Connection cn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                                Statement statement = cn.createStatement()) {
                        
                        //Borrados
                        statement.execute(DROP_TABLE_LINEAPEDIDO);
                        statement.execute(DROP_TABLE_PEDIDO);

                        statement.execute(DROP_PRODUCTOS_INGREDIENTES);
                        statement.execute(DROP_INGREDIENTES_ALERGENOS);

                        statement.execute(DROP_TABLE_INGREDIENTE);
                        statement.execute(DROP_TABLE_ALERGENOS);
                        statement.execute(DROP_TABLE_PRODUCT);
                        statement.execute(DROP_TABLE_CLIENTE);

                        //Creaciones
                        statement.execute(CREATE_TABLE_CLIENTE);
                        statement.execute(CREATE_TABLE_PRODUCT);
                        statement.execute(CREATE_TABLE_ALERGENOS);
                        statement.execute(CREATE_TABLE_INGREDIENTE);
                        
                        statement.execute(PRODUCTOS_INGREDIENTES);
                        statement.execute(INGREDIENTES_ALERGENOS);
                        
                        statement.execute(CREATE_TABLE_PEDIDO);
                        statement.execute(CREATE_TABLE_LINEAPEDIDO);
                        

                        System.out.println("Tablas borradas y creadas perfectamente.");
                }
        }
}