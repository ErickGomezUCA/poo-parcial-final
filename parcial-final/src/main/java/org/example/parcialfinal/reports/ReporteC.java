package org.example.parcialfinal.reports;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Tarjeta;
import org.example.parcialfinal.backend.reportes.ReporteUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteC extends Reporte {
    public ReporteC(Control controlResultado) {
        super(controlResultado);
    }

    public void generarReporte(ComboBox<Cliente> selectCliente) {
        String contenidoReporte = "";
        List<String> tarjetasCredito = new ArrayList<>();
        List<String> tarjetasDebito = new ArrayList<>();

        try {
            ResultSet rs = connection.getConnection().createStatement().executeQuery(
                    "SELECT t.id, t.num_tarjeta, t.fecha_expiracion, t.tipo_tarjeta FROM Compras_Inteligentes ci " +
                            "INNER JOIN Tarjeta t ON ci.id_tarjeta_CI = t.id " +
                            "WHERE ci.id_cliente_CI = " + selectCliente.getValue().getId() + ";"
            );

            while(rs.next()) {
                Tarjeta tarjeta = new Tarjeta(rs.getInt("id"), rs.getString("num_tarjeta"), rs.getString("fecha_expiracion"), rs.getString("tipo_tarjeta"));

                switch (tarjeta.getTipo()) {
                    case "Credito":
                        tarjetasCredito.add(censurarTarjeta(tarjeta.getNumeroTarjeta()));
                        break;

                    case "Debito":
                        tarjetasDebito.add(censurarTarjeta(tarjeta.getNumeroTarjeta()));
                        break;

                    default:
                        System.out.println("Tipo no valido");
                }
            }

            contenidoReporte += "Tarjetas de crédito:\n";
            if (!tarjetasCredito.isEmpty()) {
                for (String cred : tarjetasCredito) {
                    contenidoReporte += "\t" + cred + "\n";
                }
            } else {
                contenidoReporte += "\tN/A\n";
            }

            contenidoReporte += "Tarjetas de débito:\n";
            if (!tarjetasDebito.isEmpty()) {
                for (String deb : tarjetasDebito) {
                    contenidoReporte += "\t" + deb + "\n";
                }
            } else {
                contenidoReporte += "\tN/A\n";
            }

            System.out.println(contenidoReporte);
            ReporteUtils.generarReporte('C', contenidoReporte);
            connection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String censurarTarjeta(String numeroTarjeta) {
        String censurado = "";
        char digito;

        for (int i = 0; i < numeroTarjeta.length(); i++) {
            digito = numeroTarjeta.charAt(i);

            if (Character.isDigit(digito) && i < numeroTarjeta.length() - 4) {
                censurado += 'X';
            } else {
                censurado += digito;
            }
        }

        return censurado;
    }
}
