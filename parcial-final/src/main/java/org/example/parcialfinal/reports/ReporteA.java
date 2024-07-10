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

public class ReporteA extends Reporte {
    public ReporteA(Control controlResultado) {
        super(controlResultado);
    }

    public void generarReporte(ComboBox<Cliente> selectCliente, DatePicker fechaInicio, DatePicker fechaFin) {
        String contenidoReporte = "";
        try {
            int idCliente = selectCliente.getValue().getId();
            String primeraFecha = fechaInicio.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String segundaFecha = fechaFin.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


            PreparedStatement st = connection.getConnection().prepareStatement("SELECT clnt.nombre_completo, cmpr.fecha_compra, cmpr.monto, cmpr.descripcion, t.num_tarjeta " +
                                                                                    "FROM Cliente clnt " +
                                                                                    "INNER JOIN Compras_Inteligentes ci " +
                                                                                    "ON clnt.id = ci.id_cliente_CI " +
                                                                                    "INNER JOIN Tarjeta t " +
                                                                                    "ON ci.id_tarjeta_CI = t.id " +
                                                                                    "INNER JOIN Compra cmpr " +
                                                                                    "ON t.id = cmpr.id_tarjeta_C " +
                                                                                    "WHERE clnt.id =  " + idCliente +
                                                                                    " AND cmpr.fecha_compra BETWEEN " + primeraFecha + " AND " + segundaFecha);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String nombreCompleto = rs.getString("nombre_completo");
                Date fechaCompra = rs.getDate("fecha_compra");
                Double monto = rs.getDouble("monto");
                String descripcion = rs.getString("descripcion");
                String numTarjeta = rs.getString("num_tarjeta");

                contenidoReporte += ("nombre completo : " + nombreCompleto
                        + "\nfecha compra: " + fechaCompra
                        + "\nmonto: " + monto
                        + "\ndescripcion: " + descripcion
                        + "\nnumero tarjeta: " + numTarjeta + "\n");
            }
            ((TextArea)controlResultado).setText(contenidoReporte);
            ReporteUtils.generarReporte('A', contenidoReporte);
            connection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
