package org.example.parcialfinal.backend.database;

import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.Tarjeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    static DBConnection connection = DBConnection.getInstance(); // 00090123 Instancia estática de DBConnection para manejar la conexión a la base de datos

    public static List<Facilitador> obtenerFacilitadores() { // 00090123 Método estático para obtener una lista de facilitadores desde la base de datos
        List<Facilitador> facilitadores = new ArrayList<>(); // 00090123 Crea una lista para almacenar los facilitadores obtenidos
        try {
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT * FROM Facilitador"); // 00090123 Ejecuta la consulta SQL para obtener todos los facilitadores
            while (rs.next()) { // 00090123 Itera sobre el resultado del conjunto de resultados (ResultSet)
                facilitadores.add(new Facilitador(rs.getInt("id"), rs.getString("facilitador"))); // 00090123 Crea y agrega un nuevo objeto Facilitador a la lista con los datos obtenidos del ResultSet
            }
            connection.closeConnection(); // 00090123 Cierra la conexión a la base de datos después de obtener los facilitadores
        } catch (SQLException e) {
            throw new RuntimeException(e); // 00090123 Lanza una excepción de tiempo de ejecución si ocurre un error al ejecutar la consulta SQL
        }

        return facilitadores; // 00090123 Devuelve la lista de facilitadores obtenidos desde la base de datos
    }

    public static List<Cliente> obtenerClientes() { // 00090123 Método estático para obtener una lista de clientes desde la base de datos
        List<Cliente> clientes = new ArrayList<>(); // 00090123 Crea una lista para almacenar los clientes obtenidos
        try {
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT * FROM Cliente"); // 00090123 Ejecuta la consulta SQL para obtener todos los clientes
            while (rs.next()) { // 00090123 Itera sobre el resultado del conjunto de resultados (ResultSet)
                clientes.add(new Cliente( // 00090123 Crea y agrega un nuevo objeto Cliente a la lista con los datos obtenidos del ResultSet
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("direccion"),
                        rs.getString("num_telefono")
                ));
            }
            connection.closeConnection(); // 00090123 Cierra la conexión a la base de datos después de obtener los clientes
        } catch (SQLException e) {
            throw new RuntimeException(e); // 00090123 Lanza una excepción de tiempo de ejecución si ocurre un error al ejecutar la consulta SQL
        }

        return clientes; // 00090123 Devuelve la lista de clientes obtenidos desde la base de datos
    }

    public static List<Tarjeta> obtenerTarjetas() { // 00090123 Método estático para obtener una lista de tarjetas desde la base de datos
        List<Tarjeta> tarjetas = new ArrayList<>(); // 00090123 Crea una lista para almacenar las tarjetas obtenidas
        try {
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT * FROM Tarjeta"); // 00090123 Ejecuta la consulta SQL para obtener todas las tarjetas
            while (rs.next()) { // 00090123 Itera sobre el resultado del conjunto de resultados (ResultSet)
                tarjetas.add(new Tarjeta( // 00090123 Crea y agrega un nuevo objeto Tarjeta a la lista con los datos obtenidos del ResultSet
                        rs.getInt("id"),
                        rs.getString("num_tarjeta"),
                        rs.getString("fecha_expiracion"),
                        rs.getString("tipo_tarjeta")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // 00090123 Lanza una excepción de tiempo de ejecución si ocurre un error al ejecutar la consulta SQL
        }

        return tarjetas; // 00090123 Devuelve la lista de tarjetas obtenidas desde la base de datos
    }

}
