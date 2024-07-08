package org.example.parcialfinal.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.database.DBConnection;
import org.example.parcialfinal.backend.database.DatabaseUtils;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TarjetaController implements Initializable {

    DBConnection connection = DBConnection.getInstance();

    @FXML
    private DatePicker dateFechaExp;

    @FXML
    private TextField txtTarjetaNum;

    @FXML
    private TextField txtTarjetaId;

    @FXML
    private ComboBox<String> selectTarjetaTipo;

    @FXML
    private ComboBox<Cliente> selectCliente;

    @FXML
    private ComboBox<Facilitador> selectFacilitador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> tipoTarjetaValues = FXCollections.observableArrayList("Credito", "Debito");
        selectTarjetaTipo.setItems(tipoTarjetaValues);

        ObservableList<Cliente> clienteValues = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes());
        selectCliente.setItems(clienteValues);

        ObservableList<Facilitador> facilitadoresValues = FXCollections.observableArrayList(DatabaseUtils.obtenerFacilitadores());
        selectFacilitador.setItems(facilitadoresValues);
    }

    @FXML
    void btnActualizarTarjetaClick(ActionEvent event) {
        try {
            PreparedStatement psTarjeta = connection.getConnection().prepareStatement("UPDATE Tarjeta SET num_tarjeta = ?, fecha_expiracion = ?, tipo_tarjeta = ? WHERE id = ?");
            psTarjeta.setString(1, txtTarjetaNum.getText());
            psTarjeta.setDate(2, Date.valueOf(dateFechaExp.getValue()));
            psTarjeta.setString(3, selectTarjetaTipo.getValue());
            psTarjeta.setInt(4, Integer.parseInt(txtTarjetaId.getText()));
            psTarjeta.executeUpdate();
            System.out.println("Registro de tarjeta actualizado");

            PreparedStatement psComprasInteligentes = connection.getConnection().prepareStatement("UPDATE Compras_Inteligentes SET id_cliente_CI = ?, id_facilitador_CI = ? WHERE id_tarjeta_CI = ?");
            psComprasInteligentes.setInt(1, selectCliente.getValue().getId());
            psComprasInteligentes.setInt(2, selectFacilitador.getValue().getId());
            psComprasInteligentes.setInt(3, Integer.parseInt(txtTarjetaId.getText()));
            psComprasInteligentes.executeUpdate();
            System.out.println("Registro de compras inteligentes actualizado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBuscarTarjetaClick(ActionEvent event) {
        try {
            Statement stmt = connection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Tarjeta WHERE id = " + txtTarjetaId.getText() + ";");

            while (rs.next()) {
                System.out.println("id: " + rs.getString("id")
                + "\nnumero de tarjeta: " + rs.getString("num_tarjeta")
                + "\nfecha de expiracion: " + rs.getString("fecha_expiracion")
                + "\ntipo de tarjeta: " + rs.getString("tipo_tarjeta") + "\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCrearTarjetaClick(ActionEvent event) {
        int lastIdInserted = 0;

        try {
            PreparedStatement psTarjeta = connection.getConnection().prepareStatement("INSERT INTO Tarjeta(num_tarjeta, fecha_expiracion, tipo_tarjeta) VALUES(?, ?, ?)");
            psTarjeta.setString(1 , txtTarjetaNum.getText());
            psTarjeta.setDate(2 , Date.valueOf(dateFechaExp.getValue()));
            psTarjeta.setString(3 , selectTarjetaTipo.getValue());
            psTarjeta.executeUpdate();
            System.out.println("Tarjeta creada en el sistema");

            // Obtener ultimo ID del autoincrement
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT LAST_INSERT_ID() FROM Tarjeta");
            while (rs.next()) {
                lastIdInserted = rs.getInt(1);
            }

            PreparedStatement psComprasInteligentes = connection.getConnection().prepareStatement("INSERT INTO Compras_Inteligentes(id_tarjeta_CI, id_cliente_CI, id_facilitador_CI) VALUES(?, ?, ?)");
            psComprasInteligentes.setInt(1, lastIdInserted);
            psComprasInteligentes.setInt(2, selectCliente.getValue().getId());
            psComprasInteligentes.setInt(3, selectFacilitador.getValue().getId());
            psComprasInteligentes.executeUpdate();
            System.out.println("Compras inteligentes registrada");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEliminarTarjetaClick(ActionEvent event) {
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement("DELETE FROM Tarjeta WHERE id = ?");
            ps.setInt(1, Integer.parseInt(txtTarjetaId.getText()));
            ps.executeUpdate();
            System.out.println("Tarjeta eliminada en el sistema");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
