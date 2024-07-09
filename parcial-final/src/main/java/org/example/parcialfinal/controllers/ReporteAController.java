package org.example.parcialfinal.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.parcialfinal.backend.database.DBConnection;

import java.sql.*;

public class ReporteAController {
    DBConnection connection = DBConnection.getInstance();

    @FXML
    private TextField txtIdCliente;

    @FXML
    private TextField txtFechaDesde;

    @FXML
    private TextField txtFechaHasta;


    @FXML
    private TextArea txtMensajeReporteA;

    @FXML
    public void generarReporteA() {
        txtFechaDesde.clear();

        int idCliente = Integer.parseInt(txtIdCliente.getText());
        String fechaDesde = txtFechaDesde.getText();
        String fechaHasta = txtFechaHasta.getText();

        if (txtIdCliente.getText().equals("")) {
            txtIdCliente.setText("Este campo es obligatorio");
            return;
        }

        if (txtFechaDesde.getText().equals("")) {
            txtFechaDesde.setText("Este campo es obligatorio");
            return;
        }

        if (txtFechaHasta.getText().equals("")) {
            txtFechaHasta.setText("Este campo es obligatorio");
            return;
        }

        try {
            PreparedStatement st = connection.getConnection().prepareStatement("SELECT clnt.nombre_completo, cmpr.fecha_compra, cmpr.monto, cmpr.descripcion, t.num_tarjeta " +
                                                                                   "FROM Cliente clnt " +
                                                                                   "INNER JOIN Compras_Inteligentes ci " +
                                                                                   "ON clnt.id = ci.id_cliente_CI " +
                                                                                   "INNER JOIN Tarjeta t " +
                                                                                   "ON ci.id_tarjeta_CI = t.id " +
                                                                                   "INNER JOIN Compra cmpr " +
                                                                                   "ON t.id = cmpr.id_tarjeta_C " +
                                                                                   "WHERE clnt.id = ? " +
                                                                                   "AND cmpr.fecha_compra " +
                                                                                   "BETWEEN ? AND ? ");

            st.setInt(1, idCliente);
            st.setString(2, fechaDesde);
            st.setString(3, fechaHasta);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String nombreCompleto = rs.getString("nombre_completo");
                //todo cambiar a date
                String fechaCompra = rs.getString("fecha_compra");
                Double monto = rs.getDouble("monto");
                String descripcion = rs.getString("descripcion");
                String numTarjeta = rs.getString("num_tarjeta");

                txtMensajeReporteA.appendText("COMPRA(S) ENCONTRADA(S):\n" + nombreCompleto + ", " + fechaCompra + ", " + monto + ", " + descripcion + ", " + numTarjeta + "\n");
            }
            connection.closeConnection();
        } catch (SQLException e) {
            txtMensajeReporteA.setText("Error: " + e.getMessage());
        }
    }
}
