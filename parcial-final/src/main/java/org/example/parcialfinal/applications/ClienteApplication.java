package org.example.parcialfinal.applications;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClienteApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException { // 00090123 Método start requerido por la clase Application para iniciar la aplicación
        FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("cliente.fxml")); // 00090123 Carga el archivo FXML para la interfaz de usuario del cliente
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720); // 00090123 Crea una escena con el contenido cargado desde el archivo FXML y establece el tamaño de la ventana
        stage.setTitle("Cliente"); // 00090123 Establece el título de la ventana
        stage.setScene(scene); // 00090123 Establece la escena en el escenario (Stage)
        stage.show(); // 00090123 Muestra la ventana
    }

    public static void main(String[] args) { // 00090123 Método principal para iniciar la aplicación
        launch(); // 00090123 Método estático de Application para lanzar la aplicación JavaFX
    }
}
