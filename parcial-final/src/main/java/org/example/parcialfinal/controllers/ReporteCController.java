package org.example.parcialfinal.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Tarjeta;
import org.example.parcialfinal.backend.database.DBConnection;
import org.example.parcialfinal.backend.database.DatabaseUtils;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

            System.out.println("Tarjetas de credito: ");
            if (!tarjetasCredito.isEmpty()) {
                for (String cred : tarjetasCredito) {
                    System.out.println("\t" + cred);
                }
            } else {
                System.out.println("\tN/A");
            }

            System.out.println("Tarjetas de debito: ");
            if (!tarjetasDebito.isEmpty()) {
                for (String deb : tarjetasDebito) {
                    System.out.println("\t" + deb);
                }
            } else {
                System.out.println("\tN/A");
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

