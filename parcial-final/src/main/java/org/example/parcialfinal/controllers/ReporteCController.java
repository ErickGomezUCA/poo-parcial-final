package org.example.parcialfinal.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.database.DBConnection;
import org.example.parcialfinal.backend.database.DatabaseUtils;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReporteCController implements Initializable {

    @FXML
    private ComboBox<Cliente> selectCliente;

    DBConnection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnection.getInstance();

        ObservableList<Cliente> clienteValues = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes());
        selectCliente.setItems(clienteValues);
    }

    @FXML
    void generarReporteC(ActionEvent event) {
        try {
            ResultSet rs = connection.getConnection().createStatement().executeQuery(
                    "SELECT t.num_tarjeta FROM Compras_Inteligentes ci " +
                        "INNER JOIN Tarjeta t ON ci.id_tarjeta_CI = t.id " +
                        "WHERE ci.id_cliente_CI = " + selectCliente.getValue().getId() + ";"
            );

            while(rs.next()) {
                System.out.println(censurarTarjeta(rs.getString(1)));
            }
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

