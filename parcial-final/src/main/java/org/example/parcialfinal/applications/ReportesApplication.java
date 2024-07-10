package org.example.parcialfinal.applications;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportesApplication extends Application { // 00300723 Clase para mostrar los reportes
    @Override
    public void start(Stage stage) throws IOException { // 00300723 Inicia los reportes
        FXMLLoader fxmlLoader = new FXMLLoader(ReportesApplication.class.getResource("reportes.fxml")); // 000300723 Carga el archivo FXML para la ventana principal
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720); // 000300723 Crea la escena a partir del archivo FXML cargado
        stage.setTitle("Reportes"); // 000300723 Establece el título de la ventana
        stage.setScene(scene); // 000300723 Muestra la escena en la ventana
        stage.show(); // 000300723 Muestra la ventana
    }

    public static void main(String[] args) { // 000300723 Inicia el programa
        launch(); // 000300723 Punto de entrada principal de la aplicación
    }
}
