package org.example.parcialfinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LobbyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException { // 00090123 Método start requerido por la clase Application para iniciar la aplicación
        FXMLLoader fxmlLoader = new FXMLLoader(LobbyApplication.class.getResource("lobby.fxml")); // 00090123 Carga el archivo FXML de la interfaz gráfica del lobby
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720); // 00090123 Crea una escena con las dimensiones especificadas y carga el contenido del FXML
        stage.setTitle("Banco Barcelo"); // 00090123 Establece el título de la ventana principal
        stage.setScene(scene); // 00090123 Establece la escena en el escenario (stage)
        stage.show(); // 00090123 Muestra la ventana principal
    }

    public static void main(String[] args) { // 00090123 Método principal para iniciar la aplicación
        launch(); // 00090123 Llama al método estático launch() de la clase Application para iniciar la aplicación JavaFX
    }
}