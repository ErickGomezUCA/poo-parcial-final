package org.example.parcialfinal.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.example.parcialfinal.backend.database.DBConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TarjetaController {

    DBConnection connection = DBConnection.getInstance();

    @FXML
    private DatePicker txtTarjetaFechaExp;

    @FXML
    private TextField txtTarjetaNum;

    @FXML
    private TextField txtTarjetaId;

    @FXML
    private TextField txtTarjetaTipo;

    @FXML
    void btnActualizarTarjetaClick(ActionEvent event) {

    }

    @FXML
    void btnBuscarTarjetaClick(ActionEvent event) {

    }

    @FXML
    void btnCrearTarjetaClick(ActionEvent event) {
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement("INSERT INTO Tarjeta(num_tarjeta, fecha_expiracion, tipo_tarjeta) VALUES(?, ?, ?)");
            ps.setString(1 , txtTarjetaNum.getText());

            Date sqlDate = Date.valueOf(txtTarjetaFechaExp.getValue());
            ps.setDate(2 , sqlDate);

            ps.setString(3 , txtTarjetaTipo.getText());
            ps.executeUpdate();
            System.out.println("Tarjeta creada en el sistema");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEliminarTarjetaClick(ActionEvent event) {

    }

}
