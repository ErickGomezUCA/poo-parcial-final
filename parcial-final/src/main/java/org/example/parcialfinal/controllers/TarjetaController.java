package org.example.parcialfinal.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.Tarjeta;
import org.example.parcialfinal.backend.database.DBConnection;
import org.example.parcialfinal.backend.database.DatabaseUtils;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TarjetaController implements Initializable {

    DBConnection connection = DBConnection.getInstance();

    @FXML
    private TextField txtTarjetaNum_Crear;
    @FXML
    private DatePicker dateFechaExp_Crear;
    @FXML
    private ComboBox<String> selectTarjetaTipo_Crear;
    @FXML
    private ComboBox<Cliente> selectCliente_Crear;
    @FXML
    private ComboBox<Facilitador> selectFacilitador_Crear;

    @FXML
    private ComboBox<Tarjeta> selectTarjeta_Buscar;

    @FXML
    private ComboBox<Tarjeta> selectTarjeta_Actualizar;
    @FXML
    private TextField txtTarjetaNum_Actualizar;
    @FXML
    private DatePicker dateFechaExp_Actualizar;
    @FXML
    private ComboBox<String> selectTarjetaTipo_Actualizar;
    @FXML
    private ComboBox<Cliente> selectCliente_Actualizar;
    @FXML
    private ComboBox<Facilitador> selectFacilitador_Actualizar;

    @FXML
    private ComboBox<Tarjeta> selectTarjeta_Eliminar;

    @FXML
    private TableView<Tarjeta> tableTarjeta;
    @FXML
    private TableColumn<Tarjeta, Integer> colId;
    @FXML
    private TableColumn<Tarjeta, String> colNumero;
    @FXML
    private TableColumn<Tarjeta, String> colTipo;
    @FXML
    private TableColumn<Tarjeta, String> colFecha;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> tipos = FXCollections.observableArrayList("Credito", "Debito");
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes());
        ObservableList<Facilitador> facilitadores = FXCollections.observableArrayList(DatabaseUtils.obtenerFacilitadores());
        ObservableList<Tarjeta> tarjetas = FXCollections.observableArrayList(DatabaseUtils.obtenerTarjetas());

        prepararCrear(tipos, clientes, facilitadores);
        prepararBuscar(tarjetas);
        prepararActualizar(tarjetas, tipos, clientes, facilitadores);
        prepararEliminar(tarjetas);

        mostrarTarjetas();
    }

    private void mostrarTarjetas() {
        ObservableList<Tarjeta> tarjetas = FXCollections.observableArrayList(DatabaseUtils.obtenerTarjetas());
        tableTarjeta.setItems(tarjetas);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }

    private void prepararCrear(ObservableList<String> tipos, ObservableList<Cliente> clientes, ObservableList<Facilitador> facilitadores) {
        selectTarjetaTipo_Crear.setItems(tipos);
        selectCliente_Crear.setItems(clientes);
        selectFacilitador_Crear.setItems(facilitadores);
    }

    private void prepararBuscar(ObservableList<Tarjeta> tarjetas) {
        selectTarjeta_Buscar.setItems(tarjetas);
    }

    private void prepararActualizar(ObservableList<Tarjeta> tarjetas, ObservableList<String> tipos, ObservableList<Cliente> clientes, ObservableList<Facilitador> facilitadores) {
        selectTarjeta_Actualizar.setItems(tarjetas);
        selectTarjetaTipo_Actualizar.setItems(tipos);
        selectCliente_Actualizar.setItems(clientes);
        selectFacilitador_Actualizar.setItems(facilitadores);
    }

    private void prepararEliminar(ObservableList<Tarjeta> tarjetas) {
        selectTarjeta_Eliminar.setItems(tarjetas);
    }

    @FXML
    void clickActualizarTarjeta(ActionEvent event) {
        try {
            PreparedStatement psTarjeta = connection.getConnection().prepareStatement("UPDATE Tarjeta SET num_tarjeta = ?, fecha_expiracion = ?, tipo_tarjeta = ? WHERE id = ?");
            psTarjeta.setString(1, txtTarjetaNum_Actualizar.getText());
            psTarjeta.setDate(2, Date.valueOf(dateFechaExp_Actualizar.getValue()));
            psTarjeta.setString(3, selectTarjetaTipo_Actualizar.getValue());
            psTarjeta.setInt(4, selectTarjeta_Actualizar.getValue().getId());
            psTarjeta.executeUpdate();
            System.out.println("Registro de tarjeta actualizado");
            connection.closeConnection();

            PreparedStatement psComprasInteligentes = connection.getConnection().prepareStatement("UPDATE Compras_Inteligentes SET id_cliente_CI = ?, id_facilitador_CI = ? WHERE id_tarjeta_CI = ?");
            psComprasInteligentes.setInt(1, selectCliente_Actualizar.getValue().getId());
            psComprasInteligentes.setInt(2, selectFacilitador_Actualizar.getValue().getId());
            psComprasInteligentes.setInt(3, selectTarjeta_Actualizar.getValue().getId());
            psComprasInteligentes.executeUpdate();
            System.out.println("Registro de compras inteligentes actualizado");
            connection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void clickBuscarTarjeta(ActionEvent event) {
        try {
            Statement stmt = connection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Tarjeta WHERE id = " + selectTarjeta_Buscar.getValue().getId() + ";");

            while (rs.next()) {
                System.out.println("id: " + rs.getString("id")
                + "\nnumero de tarjeta: " + rs.getString("num_tarjeta")
                + "\nfecha de expiracion: " + rs.getString("fecha_expiracion")
                + "\ntipo de tarjeta: " + rs.getString("tipo_tarjeta") + "\n");
            }
            connection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void clickCrearTarjeta(ActionEvent event) {
        int lastIdInserted = 0;

        try {
            PreparedStatement psTarjeta = connection.getConnection().prepareStatement("INSERT INTO Tarjeta(num_tarjeta, fecha_expiracion, tipo_tarjeta) VALUES(?, ?, ?)");
            psTarjeta.setString(1 , txtTarjetaNum_Crear.getText());
                psTarjeta.setDate(2 , Date.valueOf(dateFechaExp_Crear.getValue()));
            psTarjeta.setString(3 , selectTarjetaTipo_Crear.getValue());
            psTarjeta.executeUpdate();
            System.out.println("Tarjeta creada en el sistema");

            // Obtener ultimo ID del autoincrement
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT LAST_INSERT_ID() FROM Tarjeta");
            while (rs.next()) {
                lastIdInserted = rs.getInt(1);
            }

            PreparedStatement psComprasInteligentes = connection.getConnection().prepareStatement("INSERT INTO Compras_Inteligentes(id_tarjeta_CI, id_cliente_CI, id_facilitador_CI) VALUES(?, ?, ?)");
            psComprasInteligentes.setInt(1, lastIdInserted);
            psComprasInteligentes.setInt(2, selectCliente_Crear.getValue().getId());
            psComprasInteligentes.setInt(3, selectFacilitador_Crear.getValue().getId());
            psComprasInteligentes.executeUpdate();
            System.out.println("Compras inteligentes registrada");
            connection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void clickEliminarTarjeta(ActionEvent event) {
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement("DELETE FROM Tarjeta WHERE id = ?");
            ps.setInt(1, selectTarjeta_Eliminar.getValue().getId());
            ps.executeUpdate();
            System.out.println("Tarjeta eliminada en el sistema");
            connection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
