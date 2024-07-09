package org.example.parcialfinal.reports;

import javafx.scene.control.ComboBox;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.database.DBConnection;
import org.example.parcialfinal.backend.reportes.ReporteUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteD extends Reporte{
    public void generarReporte(ComboBox<Facilitador> selectFacilitador) {
        String contenidoReporte = "";

        try {
            ResultSet rs = connection.getConnection().createStatement().executeQuery(
                    "SELECT ci.id_tarjeta_CI, t.num_tarjeta, ci.id_cliente_CI, c.nombre_completo, ci.id_facilitador_CI, f.facilitador " +
                            "FROM Compras_Inteligentes ci " +
                            "INNER JOIN Tarjeta t ON ci.id_tarjeta_CI = t.id " +
                            "INNER JOIN Cliente c ON ci.id_cliente_ci = c.id " +
                            "INNER JOIN Facilitador f ON ci.id_facilitador_CI = f.id " +
                            "WHERE f.id = " + selectFacilitador.getValue().getId() + ";");

            while(rs.next()) {
                contenidoReporte += ("id_tarjeta_CI : " + rs.getString("id_tarjeta_CI")
                        + "\nnum_tarjeta: " + rs.getString("num_tarjeta")
                        + "\nid_cliente_CI: " + rs.getString("id_cliente_CI")
                        + "\nnombre_completo: " + rs.getString("nombre_completo")
                        + "\nid_facilitador_CI: " + rs.getString("id_facilitador_CI")
                        + "\nfacilitador: " + rs.getString("facilitador") + "\n");
            }

            System.out.println(contenidoReporte);
            ReporteUtils.generarReporte('D', contenidoReporte);
            connection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
