package org.example.parcialfinal.backend.database;

import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Compra;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.Tarjeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils { // 00300723 Clase para utilidades que facilitan en consultas de base de datos
    static DBConnection connection = DBConnection.getInstance(); // 00300723 Inicializa la conexión a la base de datos como una instancia singleton

    public static List<Facilitador> obtenerFacilitadores() { // 00300723 Obtiene todos los facilitadores de la base
        List<Facilitador> facilitadores = new ArrayList<>(); // 00300723 Crea una lista vacía para almacenar los facilitadores
        try { // 00300723 Consultar base de datos
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT * FROM Facilitador"); // 00300723 Ejecuta una consulta SQL para obtener todos los registros de la tabla Facilitador
            while (rs.next()) { // 00300723 para cada resultado
                facilitadores.add(new Facilitador(rs.getInt("id"), rs.getString("facilitador"))); // 00300723 Agrega cada facilitador a la lista
            }
            connection.closeConnection(); // 00300723 Cierra la conexión a la base de datos
        } catch (SQLException e) { // 00300723 En caso que no
            throw new RuntimeException(e); // 00300723 Lanza una excepción en caso de error
        }

        return facilitadores; // 00300723 Devuelve la lista de facilitadores
    }

    public static List<Cliente> obtenerClientes() { // 00300723 Consulta todos los clientes
        List<Cliente> clientes = new ArrayList<>(); // 00300723 Crea una lista vacía para almacenar los clientes
        try { // 00300723 Consultar base de datos
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT * FROM Cliente"); // 00300723 Ejecuta una consulta SQL para obtener todos los registros de la tabla Cliente
            while (rs.next()) { // 00300723 Para cada resultado
                clientes.add(new Cliente(rs.getInt("id"), rs.getString("nombre_completo"), rs.getString("direccion"), rs.getString("num_telefono"))); // 00300723 Agrega cada cliente a la lista
            }
            connection.closeConnection(); // 00300723 Cierra la conexión a la base de datos
        } catch (SQLException e) { // 00300723 En caso que no se conecte a la base
            throw new RuntimeException(e); // 00300723 Lanza una excepción en caso de error
        }

        return clientes; // 00300723 Devuelve la lista de clientes
    }

    public static List<Tarjeta> obtenerTarjetas() { // 00300723 Consultar todas las tarjetas
        List<Tarjeta> tarjetas = new ArrayList<>(); // 00300723 Crea una lista vacía para almacenar las tarjetas
        try { // 00300723 Consultar base de datos
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT * FROM Tarjeta"); // 00300723 Ejecuta una consulta SQL para obtener todos los registros de la tabla Tarjeta
            while (rs.next()) { // 00300723 Para cada resultado
                tarjetas.add(new Tarjeta(rs.getInt("id"), rs.getString("num_tarjeta"), rs.getString("fecha_expiracion"), rs.getString("tipo_tarjeta"))); // 00300723 Agrega cada tarjeta a la lista
            }
        } catch (SQLException e) { // 00300723 en caso que no
            throw new RuntimeException(e); // 00300723 Lanza una excepción en caso de error
        }

        return tarjetas; // 00300723 Devuelve la lista de tarjetas
    }

    public static List<Compra> obtenerCompras() { // 00300723 Consultar todas las compras
        List<Compra> compras = new ArrayList<>(); // 00300723 Crea una lista vacía para almacenar las compras
        try { // 00300723 Consultar la base de datos
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT * FROM Compra"); // 00300723 Ejecuta una consulta SQL para obtener todos los registros de la tabla Compra
            while (rs.next()) { // 00300723 Para cada resultado
                compras.add(new Compra(rs.getInt("id"), rs.getString("fecha_compra"), rs.getDouble("monto"), rs.getString("descripcion"), rs.getInt("id_tarjeta_C"))); // 00300723 Agrega cada compra a la lista
            }
        } catch (SQLException e) { // 00300723 En caso que no
            throw new RuntimeException(e); // 00300723 Lanza una excepción en caso de error
        }

        return compras; // 00300723 Devuelve la lista de compras
    }
}
