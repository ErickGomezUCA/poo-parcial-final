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
        connection = null;
        user = "pooUser";
        password = "pooUser";
        url = "jdbc:mysql://localhost:3306/Banco";
        driver = "com.mysql.jdbc.Driver";
    }

    public static DBConnection getInstance() { // 00300723 Se devuelve la instancia del Singleton de la conexion a la base de datos
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() { // 00300723 Crea una nueva conexion o devuelve una existente con las credenciales de usuario para la base de datos
        try {
            Class.forName(driver);
            if (connection == null || connection.isClosed()) // 00300723 Si la conexion no fue inicializada antes, o fue cerrada, crear una nueva
                connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

