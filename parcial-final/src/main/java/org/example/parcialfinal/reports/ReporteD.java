package org.example.parcialfinal.reports;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.database.DBConnection;
import org.example.parcialfinal.backend.reportes.ReporteUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteD extends Reporte{
    public ReporteD(Control controlResultado) {
        super(controlResultado);
    }

    public void generarReporte(ComboBox<Facilitador> selectFacilitador) {
        String contenidoReporte = "";

        try {
            ResultSet rs = connection.getConnection().createStatement().executeQuery(
                "SELECT cl.id as 'id_cliente', cl.nombre_completo as 'nombre', t.num_tarjeta as 'num_tarjeta', f.facilitador as 'facilitador', COUNT(c.id_tarjeta_C) as 'cantidad', SUM(c.monto) as 'monto_total' " +
                    "FROM Compra c " +
                    "INNER JOIN Tarjeta t ON c.id_tarjeta_C = t.id " +
                    "INNER JOIN Compras_Inteligentes ci ON c.id_tarjeta_C = ci.id_tarjeta_CI " +
                    "INNER JOIN Cliente cl ON ci.id_cliente_CI = cl.id " +
                    "INNER JOIN Facilitador f ON ci.id_facilitador_CI = f.id " +
                    "WHERE f.id = " + selectFacilitador.getValue().getId() + " " +
                    "GROUP BY cl.id, cl.nombre_completo, t.num_tarjeta, f.facilitador;");

            while(rs.next()) {
                contenidoReporte += (
                            "ID Cliente : " + rs.getInt("id_cliente")
                        + "\nNombre: " + rs.getString("nombre")
                        + "\nNumero de tarjeta: " + rs.getString("num_tarjeta")
                        + "\nFacilitador: " + rs.getString("facilitador")
                        + "\nCantidad: " + rs.getInt("cantidad")
                        + "\nMonto total: $" + rs.getDouble("monto_total") + "\n\n");
            }

            System.out.println(contenidoReporte);
            ReporteUtils.generarReporte('D', contenidoReporte);
            connection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
