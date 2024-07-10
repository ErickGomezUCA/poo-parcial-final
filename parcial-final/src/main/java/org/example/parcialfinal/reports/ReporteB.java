package org.example.parcialfinal.reports;

import javafx.scene.control.*;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Mes;
import org.example.parcialfinal.backend.reportes.ReporteUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteB extends Reporte{ //00167523 Clase reporteB que se extiende de la clase Reporte
    public ReporteB(Control controlResultado) { //00167523 Constructor de ReporteB necesario por la clase Reporte
        super(controlResultado); //00167523 Pasa controlResultado al super de la clase Reporte
    }

    public void generarReporte(ComboBox<Cliente> selectCliente, ComboBox<Mes> selectMes, Spinner<Integer> anio) {  //00167523 Metodo para generar el reporteB con parametros de javafx a utilizar
        String contenidoReporte = ""; //00167523 Crea una variable string contenidoReporte que es igual a una cadena de texto vacia
        try { //00167523 Comienzo del try utilizado para la funcion generarReporte
            PreparedStatement st = connection.getConnection().prepareStatement("SELECT c.id, c.nombre_completo, SUM(com.monto) AS monto_total " + //00167523 Comienzo de consulta SQL, selecciona el id del cliente, el nombre completo del cliente, la suma total de las compras realizadas
                    "FROM Cliente c " + //00167523 Selecciona los datos de la tabla Cliente
                    "JOIN Compras_Inteligentes ci " + //00167523 Hace un join de la tabla Cliente con la tabla Compras_Inteligentes
                    "ON c.id = ci.id_cliente_CI " + //00167523 El join pasa cuando el id de la tabla Cliente es igual al id_cliente de la tabla Compras_Inteligentes
                    "JOIN Tarjeta t " + //00167523 Hace un join de la tabla Compras_Inteligentes con la tabla Tarjeta
                    "ON ci.id_tarjeta_CI = t.id " + //00167523 El join pasa cuando el id_tarjeta de la tabla Compras_Inteligentes es igual al id de la tabla Tarjetas
                    "JOIN Compra com " + //00167523 Hace un join de la tabla Compras con la tabla Tarjeta
                    "ON t.id = com.id_tarjeta_C " + //00167523 El join pasa cuando el id en tarjeta es igual al id_tarjeta en la tabla Compras
                    "WHERE c.id = " + selectCliente.getValue().getId() + " " + //00167523 Se pone una condicion del id de la tabla cliente que se busca con ?
                    "AND YEAR(com.fecha_compra) = " + anio.getValue() + " " + //00167523 Se pone una condicion del anio de la compra que se busca con ?
                    "AND MONTH(com.fecha_compra) = " + selectMes.getValue().getValue() + " " + //00167523 Se pone una condicion del mes de la compra que se busca con ?
                    "GROUP BY c.nombre_completo"); //00167523 Se agrupa por el nombre del cliente

            ResultSet rs = st.executeQuery(); //00167523 Ejecuta la consulta SQL y obtiene el resultado
            if (rs.next()) { //00167523 Comprueba si la fila es el resultado SQL
                int id = rs.getInt("id"); //00167523 Crea una variable int id a la que se le asigna la columna id
                String nombreCompleto = rs.getString("nombre_completo"); //00167523 Crea una variable String nombreComplreto a la que se le asigna la columna nombre_completo
                double montoTotal = rs.getDouble("monto_total"); //00167523 Crea una variable montoTotal a la que se le asigna la columna monto_totla

                contenidoReporte += (
                        "ID: " + id //00167523 Agrega el campo id a contenidoReporte
                        + "\nNombre completo: " + nombreCompleto //00167523 Agrega el campo nombreCompleto a contenidoReporte
                        + "\nMonto total: $" + montoTotal + "\n"); //00167523 Agrega el campo montoTotal a contenidoReporte
            }

            ((TextArea)controlResultado).setText(contenidoReporte); //00167523 Agrega contenidoReporte a un textarea
            ReporteUtils.generarReporte('B', contenidoReporte); //00167523 Por medio de reporteUtils genera el txt del reporte
            connection.closeConnection(); //00167523 Cierra la conexion a BD
        } catch (SQLException e) { //00167523 Maneja las excepciones de SQL
            System.out.println("Error: " + e.getMessage()); //00167523 Imprime a consola el mensaje de error
        }
    }
}
