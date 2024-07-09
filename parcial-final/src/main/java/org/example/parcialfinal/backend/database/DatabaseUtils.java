package org.example.parcialfinal.backend.database;

import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.Tarjeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
    static DBConnection connection = DBConnection.getInstance();

    public static List<Facilitador> obtenerFacilitadores() {
        List<Facilitador> facilitadores = new ArrayList<>();
        try {
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT * FROM Facilitador");
            while (rs.next()) {
                facilitadores.add(new Facilitador(rs.getInt("id"), rs.getString("facilitador")));
            }
            connection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return facilitadores;
    }

    public static List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT * FROM Cliente");
            while (rs.next()) {
                clientes.add(new Cliente(rs.getInt("id"), rs.getString("nombre_completo"), rs.getString("direccion"), rs.getString("num_telefono")));
            }
            connection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientes;
    }

    public static List<Tarjeta> obtenerTarjetas() {
        List<Tarjeta> tarjetas = new ArrayList<>();
        try {
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT * FROM Tarjera");
            while (rs.next()) {
                tarjetas.add(new Tarjeta(rs.getInt("id"), rs.getString("num_tarjeta"), rs.getString("fecha_expiracion"), rs.getString("tipo_tarjeta")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tarjetas;
    }
}
