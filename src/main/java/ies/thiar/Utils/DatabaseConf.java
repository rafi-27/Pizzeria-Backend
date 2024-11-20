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

    // Creamos tablas:
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
    static final String DROP_TABLE_CLIENTE = "DROP TABLE IF EXISTS clientes";


    public static void dropAndCreateTables() throws SQLException {
        try (Connection cn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                Statement statement = cn.createStatement()) {
                    statement.execute(DROP_TABLE_CLIENTE);
                    statement.execute(CREATE_TABLE_CLIENTE);
            System.out.println("Tabla de cliente borrada y creada perfe.");
        }
    }
}