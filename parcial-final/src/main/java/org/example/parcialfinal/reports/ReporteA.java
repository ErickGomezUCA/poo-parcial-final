package org.example.parcialfinal.reports;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.reportes.ReporteUtils;

import java.sql.*;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class ReporteA extends Reporte { //00167523 Clase ReporteA que se extiende de la clase Reporte
    public ReporteA(Control controlResultado) { //00167523 Constructor de ReporteA necesario por la clase Reporte
        super(controlResultado); //00167523 Pasa controlResultado al super de la clase Reporte
    }

    public void generarReporte(ComboBox<Cliente> selectCliente, DatePicker fechaInicio, DatePicker fechaFin) { //00167523 Metodo para generar el reporteA con parametros de javafx a utilizar
        String contenidoReporte = ""; //00167523 Crea una variable string contenidoReporte que es igual a una cadena de texto vacia
        try { //00167523 Comienzo del try utilizado para la funcion generarReporte
            int idCliente = selectCliente.getValue().getId(); //00167523 Crea una variable int idCliente que guarda el id que se selecciono en el combobox
            String primeraFecha = fechaInicio.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); //00167523 Crea una variable string primeraFecha que guarda la fecha ingresada en el datepicker
            String segundaFecha = fechaFin.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); //00167523 Crea una variable string segundaFecha que guarda la fecha ingresada en el datepicker


            PreparedStatement st = connection.getConnection().prepareStatement("SELECT clnt.nombre_completo, cmpr.fecha_compra, cmpr.monto, cmpr.descripcion, t.num_tarjeta " + //00167523 Comienzo consulta SQL, selecciona el nombre del cliente, la fecha de la compra, el monto de la compra, la descripcion de la compra, la tarjeta utilizada para la compra
                                                                                    "FROM Cliente clnt " + //00167523 Selecciona los datos de la tabla Cliente
                                                                                    "INNER JOIN Compras_Inteligentes ci " + //00167523 Se hace un inner join de la tabla Cliente con la tabla Compras_Inteligentes
                                                                                    "ON clnt.id = ci.id_cliente_CI " + //00167523 El inner join pasa cuando el id de la tabla Cliente y el id_cliente de la tabla Compras_Inteligentes son iguales
                                                                                    "INNER JOIN Tarjeta t " + //00167523 Se hace un inner join de la tabla Compras_Inteligentes con la tabla Tarjeta
                                                                                    "ON ci.id_tarjeta_CI = t.id " + //00167523 El inner join pasa cuando el id_tarjeta en la tabla Compras_Inteligentes es igual al id en la tabla Tarjeta
                                                                                    "INNER JOIN Compra cmpr " + //00167523 Se hace un inner join de la tabla Tarjeta con la tabla Compra
                                                                                    "ON t.id = cmpr.id_tarjeta_C " + //00167523 El inner join pasa cuando el id en la tabla Tarjeta es igual al id_tarjeta en la tabla Compra
                                                                                    "WHERE clnt.id =  ?" + //00167523 Se pone una condicion del id de la tabla Cliente que se quiere buscar con un ?
                                                                                    " AND cmpr.fecha_compra BETWEEN ? AND ?"); //00167523 Se pone una condicion donde la fecha de la compra realizada esta entre las fechas que se ingresan con un ?
            st.setInt(1, idCliente); //00167523 Asigna el idCliente al primer placeholder
            st.setString(2, primeraFecha); //00167523 Asigna primeraFecha al segundo placeholder
            st.setString(3, segundaFecha); //00167523 Asigna segundaFecha al tercer placeholder

            ResultSet rs = st.executeQuery(); //00167523 Ejecuta la consulta SQL y se obtiene el resultado

            while (rs.next()) { //00167523 Itera por cada fila que da de resulltado la consulta SQL
                String nombreCompleto = rs.getString("nombre_completo"); //00167523 Crea una variable string nombreCompleto a la que se le asigna el resultado de la columna en la tabla SQL nombre_completo
                Date fechaCompra = rs.getDate("fecha_compra"); //00167523 Crea una variable Date fechaCompra a la que se le asigna el resultado de la columna en la tabla SQL fecha_compra
                Double monto = rs.getDouble("monto"); //00167523 Crea una variable double monto a la que se le asigna el resultado de la columna en la tabla SQL monto
                String descripcion = rs.getString("descripcion"); //00167523 Crea una variable string descripcion a la que se le asigna el resultado de la columna en la tabla SQL descripcion
                String numTarjeta = rs.getString("num_tarjeta"); //00167523 Crea una variable string numTarjeta a la que se le asigna el resultado de la columna en la tabla SQL num_tarjeta

                contenidoReporte += ("nombre completo : " + nombreCompleto //00167523 Agrega el campo de nombreCompleto a el string contenidoReporte
                        + "\nfecha compra: " + fechaCompra //00167523 Agrega el campo de fechaCompra a el string contenidoReporte
                        + "\nmonto: " + monto //00167523 Agrega el campo de monto a el string contenidoReporte
                        + "\ndescripcion: " + descripcion //00167523 Agrega el campo de descripcion a el string contenidoReporte
                        + "\nnumero tarjeta: " + numTarjeta + "\n"); //00167523 Agrega el campo de numTarjeta a el string contenidoReporte
            }
            ((TextArea)controlResultado).setText(contenidoReporte); //00167523 Agrega contenidoReporte a un textarea
            ReporteUtils.generarReporte('A', contenidoReporte); //00167523 Por medio de reporteUtils genera el txt del reporte
            connection.closeConnection(); //00167523 Cierra la conexion a BD
        } catch (SQLException e) { //00167523 Maneja las excepciones de SQL
            System.out.println("Error: " + e.getMessage()); //00167523 Imprime en consola el mensaje de error
        }
    }
}
