package org.example.parcialfinal.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.parcialfinal.LobbyApplication;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.Tarjeta;
import org.example.parcialfinal.backend.alertas.Alerta;
import org.example.parcialfinal.backend.database.DBConnection;
import org.example.parcialfinal.backend.database.DatabaseUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TarjetaController implements Initializable { // 00300723 Define la clase controladora para las tarjetas

    DBConnection connection = DBConnection.getInstance(); // 00300723 Obtiene una instancia de la conexión a la base de datos
    Alerta alerta = new Alerta(); // 00300723 Crea una instancia de la clase Alerta para mostrar mensajes

    @FXML
    private VBox main; // 00300723 Declara el contenedor principal de la vista

    @FXML
    private TextField txtTarjetaNum_Crear; // 00300723 Campo de texto para el número de tarjeta en la creación
    @FXML
    private DatePicker dateFechaExp_Crear; // 00300723 Selector de fecha para la fecha de expiración en la creación
    @FXML
    private ComboBox<String> selectTarjetaTipo_Crear; // 00300723 ComboBox para seleccionar el tipo de tarjeta en la creación
    @FXML
    private ComboBox<Cliente> selectCliente_Crear; // 00300723 ComboBox para seleccionar el cliente en la creación
    @FXML
    private ComboBox<Facilitador> selectFacilitador_Crear; // 00300723 ComboBox para seleccionar el facilitador en la creación

    @FXML
    private ComboBox<Tarjeta> selectTarjeta_Buscar; // 00300723 ComboBox para seleccionar la tarjeta a buscar

    @FXML
    private ComboBox<Tarjeta> selectTarjeta_Actualizar; // 00300723 ComboBox para seleccionar la tarjeta a actualizar
    @FXML
    private TextField txtTarjetaNum_Actualizar; // 00300723 Campo de texto para el número de tarjeta en la actualización
    @FXML
    private DatePicker dateFechaExp_Actualizar; // 00300723 Selector de fecha para la fecha de expiración en la actualización
    @FXML
    private ComboBox<String> selectTarjetaTipo_Actualizar; // 00300723 ComboBox para seleccionar el tipo de tarjeta en la actualización
    @FXML
    private ComboBox<Cliente> selectCliente_Actualizar; // 00300723 ComboBox para seleccionar el cliente en la actualización
    @FXML
    private ComboBox<Facilitador> selectFacilitador_Actualizar; // 00300723 ComboBox para seleccionar el facilitador en la actualización

    @FXML
    private ComboBox<Tarjeta> selectTarjeta_Eliminar; // 00300723 ComboBox para seleccionar la tarjeta a eliminar

    @FXML
    private TableView<Tarjeta> tableTarjeta; // 00300723 Tabla para mostrar las tarjetas
    @FXML
    private TableColumn<Tarjeta, Integer> colId; // 00300723 Columna para el ID de la tarjeta
    @FXML
    private TableColumn<Tarjeta, String> colNumero; // 00300723 Columna para el número de la tarjeta
    @FXML
    private TableColumn<Tarjeta, String> colTipo; // 00300723 Columna para el tipo de la tarjeta
    @FXML
    private TableColumn<Tarjeta, String> colFecha; // 00300723 Columna para la fecha de expiración de la tarjeta

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // 00300723 Método de inicialización del controlador
        actualizarInputs(); // 00300723 Actualiza los inputs de la interfaz
        mostrarTarjetasTodas(); // 00300723 Muestra todas las tarjetas en la tabla
    }

    private void actualizarInputs() { // 00300723 Método para actualizar los inputs de la interfaz
        ObservableList<String> tipos = FXCollections.observableArrayList("Credito", "Debito"); // 00300723 Crea una lista de tipos de tarjeta
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes()); // 00300723 Obtiene la lista de clientes
        ObservableList<Facilitador> facilitadores = FXCollections.observableArrayList(DatabaseUtils.obtenerFacilitadores()); // 00300723 Obtiene la lista de facilitadores
        ObservableList<Tarjeta> tarjetas = FXCollections.observableArrayList(DatabaseUtils.obtenerTarjetas()); // 00300723 Obtiene la lista de tarjetas

        prepararCrear(tipos, clientes, facilitadores); // 00300723 Prepara los inputs para crear tarjeta
        prepararBuscar(tarjetas); // 00300723 Prepara los inputs para buscar tarjeta
        prepararActualizar(tarjetas, tipos, clientes, facilitadores); // 00300723 Prepara los inputs para actualizar tarjeta
        prepararEliminar(tarjetas); // 00300723 Prepara los inputs para eliminar tarjeta
    }

    private void mostrarTarjetasTodas() { // 00300723 Método para mostrar todas las tarjetas
        ObservableList<Tarjeta> tarjetas = FXCollections.observableArrayList(DatabaseUtils.obtenerTarjetas()); // 00300723 Obtiene todas las tarjetas
        mostrarTarjeta(tarjetas); // 00300723 Muestra las tarjetas en la tabla
    }

    private void mostrarTarjeta(ObservableList<Tarjeta> tarjetas) { // 00300723 Método para mostrar tarjetas en la tabla
        tableTarjeta.setItems(tarjetas); // 00300723 Establece las tarjetas en la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id")); // 00300723 Configura la columna de ID
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numeroTarjeta")); // 00300723 Configura la columna de número de tarjeta
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaExpiracion")); // 00300723 Configura la columna de fecha de expiración
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo")); // 00300723 Configura la columna de tipo de tarjeta
    }

    private void prepararCrear(ObservableList<String> tipos, ObservableList<Cliente> clientes, ObservableList<Facilitador> facilitadores) { // 00300723 Método para preparar los inputs de creación
        selectTarjetaTipo_Crear.setItems(tipos); // 00300723 Establece los tipos de tarjeta en el ComboBox
        selectCliente_Crear.setItems(clientes); // 00300723 Establece los clientes en el ComboBox
        selectFacilitador_Crear.setItems(facilitadores); // 00300723 Establece los facilitadores en el ComboBox
    }

    private void prepararBuscar(ObservableList<Tarjeta> tarjetas) { // 00300723 Método para preparar los inputs de búsqueda
        selectTarjeta_Buscar.setItems(tarjetas); // 00300723 Establece las tarjetas en el ComboBox de búsqueda
    }

    private void prepararActualizar(ObservableList<Tarjeta> tarjetas, ObservableList<String> tipos, ObservableList<Cliente> clientes, ObservableList<Facilitador> facilitadores) { // 00300723 Método para preparar los inputs de actualización
        selectTarjeta_Actualizar.setItems(tarjetas); // 00300723 Establece las tarjetas en el ComboBox de actualización
        selectTarjetaTipo_Actualizar.setItems(tipos); // 00300723 Establece los tipos de tarjeta en el ComboBox
        selectCliente_Actualizar.setItems(clientes); // 00300723 Establece los clientes en el ComboBox
        selectFacilitador_Actualizar.setItems(facilitadores); // 00300723 Establece los facilitadores en el ComboBox
    }

    private void prepararEliminar(ObservableList<Tarjeta> tarjetas) { // 00300723 Método para preparar los inputs de eliminación
        selectTarjeta_Eliminar.setItems(tarjetas); // 00300723 Establece las tarjetas en el ComboBox de eliminación
    }

    @FXML
    void clickCrearTarjeta(ActionEvent event) { // 00300723 Método para crear una nueva tarjeta
        int lastIdInserted = 0; // 00300723 Variable para almacenar el último ID insertado

        try {
            PreparedStatement psTarjeta = connection.getConnection().prepareStatement("INSERT INTO Tarjeta(num_tarjeta, fecha_expiracion, tipo_tarjeta) VALUES(?, ?, ?)"); // 00300723 Prepara la consulta SQL para insertar tarjeta
            psTarjeta.setString(1 , txtTarjetaNum_Crear.getText()); // 00300723 Establece el número de tarjeta
            psTarjeta.setDate(2 , Date.valueOf(dateFechaExp_Crear.getValue())); // 00300723 Establece la fecha de expiración
            psTarjeta.setString(3 , selectTarjetaTipo_Crear.getValue()); // 00300723 Establece el tipo de tarjeta
            psTarjeta.executeUpdate(); // 00300723 Ejecuta la inserción de la tarjeta

            // Obtener ultimo ID del autoincrement
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT LAST_INSERT_ID() FROM Tarjeta"); // 00300723 Obtiene el último ID insertado
            while (rs.next()) {
                lastIdInserted = rs.getInt(1); // 00300723 Almacena el último ID insertado
            }

            PreparedStatement psComprasInteligentes = connection.getConnection().prepareStatement("INSERT INTO Compras_Inteligentes(id_tarjeta_CI, id_cliente_CI, id_facilitador_CI) VALUES(?, ?, ?)"); // 00300723 Prepara la consulta SQL para insertar en Compras_Inteligentes
            psComprasInteligentes.setInt(1, lastIdInserted); // 00300723 Establece el ID de la tarjeta
            psComprasInteligentes.setInt(2, selectCliente_Crear.getValue().getId()); // 00300723 Establece el ID del cliente
            psComprasInteligentes.setInt(3, selectFacilitador_Crear.getValue().getId()); // 00300723 Establece el ID del facilitador
            psComprasInteligentes.executeUpdate(); // 00300723 Ejecuta la inserción en Compras_Inteligentes
            mostrarTarjetasTodas(); // 00300723 Actualiza la tabla de tarjetas
            actualizarInputs(); // 00300723 Actualiza los inputs
            alerta.mostrarMensaje("Tarjetas", "Tarjeta creada en el sistema"); // 00300723 Muestra un mensaje de éxito
            connection.closeConnection(); // 00300723 Cierra la conexión a la base de datos
        } catch (SQLException e) {
            throw new RuntimeException(e); // 00300723 Maneja cualquier error de SQL
        }
    }

    @FXML
    void clickBuscarTarjeta(ActionEvent event) { // 00300723 Método para buscar una tarjeta
        ObservableList<Tarjeta> tarjetas = FXCollections.observableArrayList(); // 00300723 Crea una lista para almacenar el resultado de la búsqueda
        try {
            Statement stmt = connection.getConnection().createStatement(); // 00300723 Crea un statement para ejecutar la consulta
            ResultSet rs = stmt.executeQuery("SELECT * FROM Tarjeta WHERE id = " + selectTarjeta_Buscar.getValue().getId() + ";"); // 00300723 Ejecuta la consulta de búsqueda

            while (rs.next()) {
                tarjetas.add(new Tarjeta(rs.getInt("id"), rs.getString("num_tarjeta"), rs.getString("fecha_expiracion"), rs.getString("tipo_tarjeta"))); // 00300723 Añade la tarjeta encontrada a la lista
            }
            mostrarTarjeta(tarjetas); // 00300723 Muestra la tarjeta encontrada en la tabla
            alerta.mostrarMensaje("Tarjetas", "Tarjeta encontrada en el sistema"); // 00300723 Muestra un mensaje de éxito
            connection.closeConnection(); // 00300723 Cierra la conexión a la base de datos
        } catch (SQLException e) {
            throw new RuntimeException(e); // 00300723 Maneja cualquier error de SQL
        }
    }

    @FXML
    void clickActualizarTarjeta(ActionEvent event) { // 00300723 Método para actualizar una tarjeta
        try {
            PreparedStatement psTarjeta = connection.getConnection().prepareStatement("UPDATE Tarjeta SET num_tarjeta = ?, fecha_expiracion = ?, tipo_tarjeta = ? WHERE id = ?"); // 00300723 Prepara la consulta SQL para actualizar la tarjeta
            psTarjeta.setString(1, txtTarjetaNum_Actualizar.getText()); // 00300723 Establece el nuevo número de tarjeta
            psTarjeta.setDate(2, Date.valueOf(dateFechaExp_Actualizar.getValue())); // 00300723 Establece la nueva fecha de expiración
            psTarjeta.setString(3, selectTarjetaTipo_Actualizar.getValue()); // 00300723 Establece el nuevo tipo de tarjeta
            psTarjeta.setInt(4, selectTarjeta_Actualizar.getValue().getId()); // 00300723 Establece el ID de la tarjeta a actualizar
            psTarjeta.executeUpdate(); // 00300723 Ejecuta la actualización de la tarjeta
            connection.closeConnection(); // 00300723 Cierra la conexión a la base de datos

            PreparedStatement psComprasInteligentes = connection.getConnection().prepareStatement("UPDATE Compras_Inteligentes SET id_cliente_CI = ?, id_facilitador_CI = ? WHERE id_tarjeta_CI = ?"); // 00300723 Prepara la consulta SQL para actualizar Compras_Inteligentes
            psComprasInteligentes.setInt(1, selectCliente_Actualizar.getValue().getId()); // 00300723 Establece el nuevo ID del cliente
            psComprasInteligentes.setInt(2, selectFacilitador_Actualizar.getValue().getId()); // 00300723 Establece el nuevo ID del facilitador
            psComprasInteligentes.setInt(3, selectTarjeta_Actualizar.getValue().getId()); // 00300723 Establece el ID de la tarjeta
            psComprasInteligentes.executeUpdate(); // 00300723 Ejecuta la actualización en Compras_Inteligentes
            mostrarTarjetasTodas(); // 00300723 Actualiza la tabla de tarjetas
            actualizarInputs();  // 00300723 Actualiza los campos de entrada
            alerta.mostrarMensaje("Tarjetas", "Tarjeta actualizada en el sistema");  // 00300723 Muestra un mensaje de éxito
            connection.closeConnection();  // 00300723 Cierra la conexión a la base de datos
        } catch (SQLException e) {  // 00300723 Captura excepciones de SQL
            throw new RuntimeException(e);  // 00300723 Lanza una excepción de tiempo de ejecución
        }
    }

    @FXML
    void clickEliminarTarjeta(ActionEvent event) {  // 00300723 Método para eliminar una tarjeta
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement("DELETE FROM Tarjeta WHERE id = ?");  // 00300723 Prepara la consulta SQL para eliminar
            ps.setInt(1, selectTarjeta_Eliminar.getValue().getId());  // 00300723 Establece el ID de la tarjeta a eliminar
            ps.executeUpdate();  // 00300723 Ejecuta la consulta de eliminación
            mostrarTarjetasTodas();  // 00300723 Actualiza la lista de tarjetas
            actualizarInputs();  // 00300723 Actualiza los campos de entrada
            alerta.mostrarMensaje("Tarjetas", "Tarjeta eliminada en el sistema");  // 00300723 Muestra un mensaje de éxito
            connection.closeConnection();  // 00300723 Cierra la conexión a la base de datos
        } catch (SQLException e) {  // 00300723 Captura excepciones de SQL
            throw new RuntimeException(e);  // 00300723 Lanza una excepción de tiempo de ejecución
        }
    }

    @FXML
    private void clickRegresar(ActionEvent event) {  // 00300723 Método para regresar a la pantalla principal
        Stage stage = new Stage();  // 00300723 Crea una nueva ventana
        LobbyApplication lobbyApp = new LobbyApplication();  // 00300723 Crea una instancia de la aplicación principal
        try {
            lobbyApp.start(stage);  // 00300723 Inicia la aplicación principal
        } catch (IOException e) {  // 00300723 Captura excepciones de entrada/salida
            throw new RuntimeException(e);  // 00300723 Lanza una excepción de tiempo de ejecución
        }
        stage.show();  // 00300723 Muestra la nueva ventana
        cerrar();  // 00300723 Cierra la ventana actual
    }

    @FXML
    private void cerrar() {  // 00300723 Método para cerrar la ventana actual
        ((Stage)main.getScene().getWindow()).close();  // 00300723 Cierra la ventana actual
    }
}