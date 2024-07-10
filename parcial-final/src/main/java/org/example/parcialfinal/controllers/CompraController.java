package org.example.parcialfinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.parcialfinal.LobbyApplication;
import org.example.parcialfinal.backend.Compra;
import org.example.parcialfinal.backend.Tarjeta;
import org.example.parcialfinal.backend.alertas.Alerta;
import org.example.parcialfinal.backend.database.DBConnection;

import javafx.event.ActionEvent;
import org.example.parcialfinal.backend.database.DatabaseUtils;

import java.io.IOException;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CompraController { // 00300723 Clase controladora para manejar las operaciones de compra
    DBConnection connection = DBConnection.getInstance(); // 00300723 Instancia de la conexión a la base de datos
    @FXML
    private VBox main; // 00300723 VBox principal del layout

    @FXML
    private DatePicker dtFechaAgregarCompra; // 00300723 Selector de fecha para agregar compra
    @FXML
    private Spinner<Double> spinnerMontoAgregarCompra; // 00300723 Selector de monto para agregar compra
    @FXML
    private ComboBox<Tarjeta> selectAgregarTarjeta; // 00300723 ComboBox para seleccionar tarjeta al agregar compra
    @FXML
    private TextArea txtDescripcionAgregarCompra; // 00300723 Campo de texto para la descripción al agregar compra

    @FXML
    private ComboBox<Compra> selectBuscarCompra; // 00300723 ComboBox para seleccionar compra al buscar

    @FXML
    private ComboBox<Compra> selectEliminarCompra; // 00300723 ComboBox para seleccionar compra al eliminar

    //cambiar a combobox todo
    @FXML
    private ComboBox<Compra> selectActualizarCompra; // 00300723 ComboBox para seleccionar compra al actualizar
    @FXML
    private DatePicker dtFechaActualizarCompra; // 00300723 Selector de fecha para actualizar compra
    @FXML
    private Spinner<Double> spinnerMontoActualizarCompra; // 00300723 Selector de monto para actualizar compra
    @FXML
    private ComboBox<Tarjeta> selectActualizarTarjeta; // 00300723 ComboBox para seleccionar tarjeta al actualizar compra
    @FXML
    private TextArea txtDescripcionActualizarCompra; // 00300723 Campo de texto para la descripción al actualizar compra

    @FXML
    private TableView<Compra> tableCompra; // 00300723 Tabla para mostrar las compras
    @FXML
    private TableColumn<Compra, Integer> colId; // 00300723 Columna para el ID de la compra
    @FXML
    private TableColumn<Compra, String> colFecha; // 00300723 Columna para la fecha de la compra
    @FXML
    private TableColumn<Compra, Double> colMonto; // 00300723 Columna para el monto de la compra
    @FXML
    private TableColumn<Compra, Integer> colTarjeta; // 00300723 Columna para el ID de la tarjeta
    @FXML
    private TableColumn<Compra, String> colDescripcion; // 00300723 Columna para la descripción de la compra

    private ArrayList<Compra> compras = new ArrayList<>(); // 00300723 Lista de compras
    Alerta alerta = new Alerta(); // 00300723 Instancia de la clase Alerta para mostrar mensajes

    @FXML
    public void initialize() { // 00300723 Método de inicialización
        cargarCompras(); // 00300723 Cargar todas las compras
        actualizarInputs(); // 00300723 Actualizar los inputs
        mostrarTodasCompras(); // 00300723 Mostrar todas las compras
    }

    private void actualizarInputs() { // 00300723 Método para actualizar los inputs
        ObservableList<Tarjeta> tarjetas = FXCollections.observableArrayList(DatabaseUtils.obtenerTarjetas()); // 00300723 Obtener lista de tarjetas
        ObservableList<Compra> compras = FXCollections.observableArrayList(DatabaseUtils.obtenerCompras()); // 00300723 Obtener lista de compras

        SpinnerValueFactory<Double> valueFactoryCrear = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10000.0, 0.0, 1); // 00300723 Value factory para crear
        SpinnerValueFactory<Double> valueFactoryActualizar = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10000.0, 0.0, 1); // 00300723 Value factory para actualizar
        spinnerMontoActualizarCompra.setValueFactory(valueFactoryActualizar); // 00300723 Setear value factory para actualizar

        prepararCrear(valueFactoryCrear, tarjetas); // 00300723 Preparar inputs para crear
        prepararBuscar(compras); // 00300723 Preparar inputs para buscar
        prepararActualizar(compras, valueFactoryActualizar, tarjetas); // 00300723 Preparar inputs para actualizar
        prepararEliminar(compras); // 00300723 Preparar inputs para eliminar
    }

    private void prepararCrear(SpinnerValueFactory<Double> valueFactoryCrear, ObservableList<Tarjeta> tarjetas) { // 00300723 Método para preparar inputs de creación
        spinnerMontoAgregarCompra.setValueFactory(valueFactoryCrear); // 00300723 Setear value factory para creación
        selectAgregarTarjeta.setItems(tarjetas); // 00300723 Setear items de tarjeta para creación
    }

    private void prepararBuscar(ObservableList<Compra> compras) { // 00300723 Método para preparar inputs de búsqueda
        selectBuscarCompra.setItems(compras); // 00300723 Setear items de compra para búsqueda
    }

    private void prepararActualizar(ObservableList<Compra> compras, SpinnerValueFactory<Double> valueFactoryActualizar, ObservableList<Tarjeta> tarjetas) { // 00300723 Método para preparar inputs de actualización
        selectActualizarCompra.setItems(compras); // 00300723 Setear items de compra para actualización
        spinnerMontoActualizarCompra.setValueFactory(valueFactoryActualizar); // 00300723 Setear value factory para actualización
        selectActualizarTarjeta.setItems(tarjetas); // 00300723 Setear items de tarjeta para actualización
    }

    private void prepararEliminar(ObservableList<Compra> compras) { // 00300723 Método para preparar inputs de eliminación
        selectEliminarCompra.setItems(compras); // 00300723 Setear items de compra para eliminación
    }

    private void mostrarTodasCompras() { // 00300723 Método para mostrar todas las compras
        ObservableList<Compra> compras = FXCollections.observableArrayList(DatabaseUtils.obtenerCompras()); // 00300723 Obtener todas las compras
        mostrarCompra(compras); // 00300723 Mostrar las compras
    }

    private void mostrarCompra(ObservableList<Compra> compras) { // 00300723 Método para mostrar compras en la tabla
        tableCompra.setItems(compras); // 00300723 Setear items de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id")); // 00300723 Setear factory para columna ID
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaCompra")); // 00300723 Setear factory para columna fecha
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto")); // 00300723 Setear factory para columna monto
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion")); // 00300723 Setear factory para columna descripción
        colTarjeta.setCellValueFactory(new PropertyValueFactory<>("idTajerta")); // 00300723 Setear factory para columna tarjeta
    }

    @FXML
    public void agregarCompra() { // 00300723 Método para agregar una compra
        String fecha = dtFechaAgregarCompra.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 00300723 Obtener fecha de compra
        Double monto = spinnerMontoAgregarCompra.getValue(); // 00300723 Obtener monto de compra
        String descripcion = txtDescripcionAgregarCompra.getText(); // 00300723 Obtener descripción de compra
        int idTarjeta = selectAgregarTarjeta.getValue().getId(); // 00300723 Obtener ID de tarjeta

        try { // 00300723 Consultar base
            PreparedStatement st = connection.getConnection().prepareStatement("INSERT INTO Compra(fecha_compra, monto, descripcion, id_tarjeta_C) VALUES (?, ?, ?, ?)"); // 00300723 Crear statement para inserción
            st.setString(1, fecha); // 00300723 Setear fecha
            st.setDouble(2, monto); // 00300723 Setear monto
            st.setString(3, descripcion); // 00300723 Setear descripción
            st.setInt(4, idTarjeta); // 00300723 Setear ID de tarjeta

            int filas = st.executeUpdate(); // 00300723 Ejecutar inserción
            if (filas > 0) { // 00300723 Si hay resultado
                mostrarTodasCompras(); // 00300723 Mostrar todas las compras
                actualizarInputs(); // 00300723 Actualizar inputs
                alerta.mostrarMensaje("Compras", "Compra agregada con exito"); // 00300723 Mostrar mensaje de éxito

                dtFechaAgregarCompra.setValue(null); // 00300723 Limpiar fecha
                spinnerMontoAgregarCompra.getValueFactory().setValue(0.0); // 00300723 Limpiar monto
                txtDescripcionAgregarCompra.clear(); // 00300723 Limpiar descripción

                cargarCompras(); // 00300723 Cargar compras
            }
            connection.closeConnection(); // 00300723 Cerrar conexión
        } catch (Exception e) { // 00300723 Si hay error
            System.out.println("Error: " + e.getMessage()); // 00300723 Manejar error
        }
    }

    @FXML
    public void buscarCompra() { // 00300723 Método para buscar una compra
        int id = (selectBuscarCompra.getValue().getId()); // 00300723 Obtener ID de compra
        ObservableList<Compra> compras = FXCollections.observableArrayList(); // 00300723 Crear lista observable de compras

        try {
            PreparedStatement st = connection.getConnection().prepareStatement("SELECT * FROM Compra WHERE id = ?"); // 00300723 Crear statement para búsqueda
            st.setInt(1, id); // 00300723 Setear ID de compra
            ResultSet rs = st.executeQuery(); // 00300723 Ejecutar búsqueda

            if (rs.next()) { // 00300723 Para cada resultado
                compras.add(new Compra(rs.getInt("id"), rs.getString("fecha_compra"), rs.getDouble("monto"), rs.getString("descripcion"), rs.getInt("id_tarjeta_C"))); // 00300723 Agregar compra encontrada a la lista
            } else { // 00300723 Si no tiene
                alerta.mostrarError("Compras error", "Compra no encontrado en base de datos", ""); // 00300723 Mostrar mensaje de error si no se encuentra la compra
            }

            mostrarCompra(compras); // 00300723 Mostrar compra encontrada
            alerta.mostrarMensaje("Compras", "Compra encontrada"); // 00300723 Mostrar mensaje de éxito

            connection.closeConnection(); // 00300723 Cerrar conexión
        } catch (Exception e) { // 00300723 Si hay error
            e.printStackTrace(); // 00300723 Manejar error
        }
    }

    @FXML
    public void eliminarCompra() { // 00300723 Método para eliminar una compra
        int id = selectEliminarCompra.getValue().getId(); // 00300723 Obtener ID de compra

        try { // 00300723 Consultar base
            PreparedStatement st = connection.getConnection().prepareStatement("DELETE FROM Compra WHERE id = ?"); // 00300723 Crear statement para eliminación
            st.setInt(1, id); // 00300723 Setear ID de compra

            int filas = st.executeUpdate(); // 00300723 Ejecutar eliminación
            if (filas > 0) { // 00300723 Si hay resultado
                cargarCompras(); // 00300723 Cargar compras
            } else { // 00300723 En caso que no
                alerta.mostrarError("Compra error", "Compra no encontrada en base de datos", ""); // 00300723 Mostrar mensaje de error si no se encuentra la compra
            }

            mostrarTodasCompras(); // 00300723 Mostrar todas las compras
            actualizarInputs(); // 00300723 Actualizar inputs
            alerta.mostrarMensaje("Compras", "Compra eliminada con éxito"); // 00300723 Mostrar mensaje de éxito
            connection.closeConnection(); // 00300723 Cerrar conexión
        } catch (Exception e) {
            e.printStackTrace(); // 00300723 Manejar error
        }
    }

    @FXML
    public void actualizarCompra() { // 00300723 Método para actualizar una compra
        int id = selectActualizarCompra.getValue().getId(); // 00300723 Obtener ID de compra
        String fecha = dtFechaActualizarCompra.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 00300723 Obtener fecha de compra
        Double monto = spinnerMontoAgregarCompra.getValue(); // 00300723 Obtener monto de compra
        String descripcion = txtDescripcionActualizarCompra.getText(); // 00300723 Obtener descripción de compra
        int idTarjeta = selectActualizarTarjeta.getValue().getId(); // 00300723 Obtener ID de tarjeta

        try { // 00300723 Manejar error
            PreparedStatement st = connection.getConnection().prepareStatement("UPDATE Compra SET fecha_compra = ?, monto = ?, descripcion = ?, id_tarjeta_C = ? WHERE id = ?"); // 00300723 Crear statement para actualización
            st.setString(1, fecha); // 00300723 Setear fecha de compra
            st.setDouble(2, monto); // 00300723 Setear monto de compra
            st.setString(3, descripcion); // 00300723 Setear descripción de compra
            st.setInt(4, idTarjeta); // 00300723 Setear ID de tarjeta
            st.setInt(5, id); // 00300723 Setear ID de compra

            int filas = st.executeUpdate(); // 00300723 Ejecutar actualización
            if (filas > 0) { // 00300723 Si hay resultado
                dtFechaActualizarCompra.setValue(null); // 00300723 Limpiar fecha de actualización
                spinnerMontoAgregarCompra.getValueFactory().setValue(0.0); // 00300723 Limpiar monto de actualización
                txtDescripcionActualizarCompra.clear(); // 00300723 Limpiar descripción de actualización

                cargarCompras(); // 00300723 Cargar compras
            } else { // 00300723 En caso que no
                alerta.mostrarError("Compra error", "Compra no encontrada en base de datos", ""); // 00300723 Mostrar mensaje de error si no se encuentra la compra
            }
            mostrarTodasCompras(); // 00300723 Mostrar todas las compras
            actualizarInputs(); // 00300723 Actualizar inputs
            alerta.mostrarMensaje("Compra", "Compra actualizada con éxito"); // 00300723 Mostrar mensaje de éxito
            connection.closeConnection(); // 00300723 Cerrar conexión
        } catch (Exception e) { // 00300723 Maneja error
            System.out.println("Error: " + e.getMessage()); // 00300723 Manejar error
        }
    }

    private void cargarCompras() { // 00300723 Método para cargar todas las compras
        compras = new ArrayList(); // 00300723 Inicializar lista de compras
        try { // 00300723  Abrir consulta
            Statement st = connection.getConnection().createStatement(); // 00300723 Crear statement
            ResultSet rs = st.executeQuery("SELECT * FROM Compra"); // 00300723 Ejecutar query para obtener todas las compras

            while (rs.next()) { // 00300723 Para cada resultado
                int idCompra = rs.getInt("id"); // 00300723 Obtener ID de compra
                String fecha = rs.getString("fecha_compra"); // 00300723 Obtener fecha de compra
                Double monto = rs.getDouble("monto"); // 00300723 Obtener monto de compra
                String descripcion = rs.getString("descripcion"); // 00300723 Obtener descripción de compra
                int idTarjeta = rs.getInt("id_tarjeta_C"); // 00300723 Obtener ID de tarjeta

                Compra compra = new Compra(idCompra, fecha, monto, descripcion, idTarjeta); // 00300723 Crear objeto compra
                compras.add(compra); // 00300723 Agregar compra a la lista
            }
            connection.closeConnection(); // 00300723 Cerrar conexión
        } catch (Exception e) { // 00300723 Manejar error
            System.out.println("Error: " + e.getMessage()); // 00300723 Manejar error
        }
    }

    @FXML
    public void clickRegresar(ActionEvent event) { // 00300723 Método para manejar el evento de regresar
        Stage stage = new Stage(); // 00300723 Crear nueva ventana
        LobbyApplication lobbyApp = new LobbyApplication(); // 00300723 Crear instancia de la aplicación principal
        try { // 00300723 Manejar error
            lobbyApp.start(stage); // 00300723 Iniciar aplicación principal
        } catch (IOException e) { // 00300723 Maneja error
            throw new RuntimeException(e); // 00300723 Manejar excepción
        }
        stage.show(); // 00300723 Mostrar nueva ventana
        cerrar(); // 00300723 Cerrar ventana actual
    }

    @FXML
    private void cerrar() { // 00300723 Método para cerrar la ventana actual
        ((Stage) main.getScene().getWindow()).close(); // 00300723 Cerrar ventana
    }
}
