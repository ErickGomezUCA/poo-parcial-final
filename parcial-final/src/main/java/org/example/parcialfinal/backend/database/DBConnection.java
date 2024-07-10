package org.example.parcialfinal.backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;
    private String user;
    private String password;
    private String url;
    private String driver;

    private DBConnection() { // 00300723 Para mejorar la escalabilidad y eficacia del proyecto, aplicar Singleton a esta clase
        connection = null; // 00090123 Inicializa la conexión a la base de datos como nula
        user = "pooUser"; // 00090123 Nombre de usuario para conectarse a la base de datos
        password = "pooUser"; // 00090123 Contraseña del usuario para autenticación en la base de datos
        url = "jdbc:mysql://localhost:3306/Banco"; // 00090123 URL de conexión JDBC para la base de datos MySQL llamada "Banco" en localhost
        driver = "com.mysql.jdbc.Driver"; // 00090123 Clase del controlador JDBC para MySQL

    }

    public static DBConnection getInstance() { // 00300723 Se devuelve la instancia del Singleton de la conexion a la base de datos
        if (instance == null) { // 00090123 Verifica si la instancia de DBConnection es nula
            instance = new DBConnection(); // 00090123 Crea una nueva instancia de DBConnection si es nula
        }
        return instance; // 00090123 Devuelve la instancia existente o recién creada de DBConnection
    }

    public Connection getConnection() { // 00300723 Crea una nueva conexion o devuelve una existente con las credenciales de usuario para la base de datos
        try {
            Class.forName(driver); // 00090123 Carga dinámicamente el controlador JDBC especificado para MySQL
            if (connection == null || connection.isClosed()) // 00300723 Si la conexion no fue inicializada antes, o fue cerrada, crear una nueva
                connection = DriverManager.getConnection(url, user, password);// 00090123 Establece la conexión a la base de datos MySQL utilizando la URL, usuario y contraseña proporcionados
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // 00090123 Imprime el rastreo de la excepción si no se encuentra la clase del controlador JDBC
        } catch (SQLException e) {
            e.printStackTrace(); // 00300723 No se puede crear la conexion
        }
        return connection; // 00300723 Devolver la nueva conexion, o la que se haya inicializado previamente
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close(); // 00300723 Cerrar la conexion existence
            } catch (SQLException e) {
                e.printStackTrace(); // 00300723 No existe una conexion para cerrarla
            }
        }
    }
}

