package org.example.parcialfinal.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.parcialfinal.backend.database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteBController {
    DBConnection connection = DBConnection.getInstance();

    @FXML
    private TextField txtIdCliente;

    @FXML
    private TextField txtMesABuscar;

    @FXML
    private TextField txtAnioABuscar;

    @FXML
    private TextArea txtMensajeReporteB;

    @FXML
    public void generarReporteB() {
        int idCliente = Integer.parseInt(txtIdCliente.getText());
        int mesABuscar = Integer.parseInt(txtMesABuscar.getText());
        int anioABuscar = Integer.parseInt(txtAnioABuscar.getText());

        if (txtIdCliente.getText().equals("")) {
            txtIdCliente.setText("Este campo es obligatorio");
            return;
        }

        if (txtMesABuscar.getText().equals("")) {
            txtMesABuscar.setText("Este campo es obligatorio");
            return;
        }

        if (txtAnioABuscar.getText().equals("")) {
            txtAnioABuscar.setText("Este campo es obligatorio");
            return;
        }

        try {
            PreparedStatement st = connection.getConnection().prepareStatement("SELECT c.nombre_completo, SUM(com.monto) AS monto_total " +
                                                                                    "FROM Cliente c " +
                                                                                    "JOIN Compras_Inteligentes ci " +
                                                                                    "ON c.id = ci.id_cliente_CI " +
                                                                                    "JOIN Tarjeta t " +
                                                                                    "ON ci.id_tarjeta_CI = t.id " +
                                                                                    "JOIN Compra com " +
                                                                                    "ON t.id = com.id_tarjeta_C " +
                                                                                    "WHERE c.id = ? " +
                                                                                    "AND YEAR(com.fecha_compra) = ? " +
                                                                                    "AND MONTH(com.fecha_compra) = ? " +
                                                                                    "GROUP BY c.nombre_completo");

                    st.setInt(1, idCliente);
                    st.setInt(2, anioABuscar);
                    st.setInt(3, mesABuscar);

                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        String nombreCompleto = rs.getString("nombre_completo");
                        double montoTotal = rs.getDouble("monto_total");

                        txtMensajeReporteB.appendText("GASTO TOTAL DE CLIENTE:\n" + nombreCompleto + ", " + montoTotal + ".\n");
                    }
                    connection.closeConnection();
        } catch (SQLException e) {
            txtMensajeReporteB.setText("Error: " + e.getMessage());
        }
    }
}
