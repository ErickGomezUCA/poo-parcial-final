package org.example.parcialfinal.reports;

import javafx.scene.control.*;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Mes;
import org.example.parcialfinal.backend.reportes.ReporteUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteB extends Reporte{
    public ReporteB(Control controlResultado) {
        super(controlResultado);
    }

    public void generarReporte(ComboBox<Cliente> selectCliente, ComboBox<Mes> selectMes, Spinner<Integer> anio) {
        String contenidoReporte = "";
        try {
            PreparedStatement st = connection.getConnection().prepareStatement("SELECT c.nombre_completo, SUM(com.monto) AS monto_total " +
                    "FROM Cliente c " +
                    "JOIN Compras_Inteligentes ci " +
                    "ON c.id = ci.id_cliente_CI " +
                    "JOIN Tarjeta t " +
                    "ON ci.id_tarjeta_CI = t.id " +
                    "JOIN Compra com " +
                    "ON t.id = com.id_tarjeta_C " +
                    "WHERE c.id = " + selectCliente.getValue().getId() + " " +
                    "AND YEAR(com.fecha_compra) = " + anio.getValue() + " " +
                    "AND MONTH(com.fecha_compra) = " + selectMes.getValue().getValue() + " " +
                    "GROUP BY c.nombre_completo");

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String nombreCompleto = rs.getString("nombre_completo");
                double montoTotal = rs.getDouble("monto_total");

                contenidoReporte += ("nombre completo : " + nombreCompleto
                        + "\nnombre completo: " + nombreCompleto
                        + "\nmonto total: " + montoTotal + "\n");
            }

            ((TextArea)controlResultado).setText(contenidoReporte);
            ReporteUtils.generarReporte('B', contenidoReporte);
            connection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
