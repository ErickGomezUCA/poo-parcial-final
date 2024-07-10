package org.example.parcialfinal.backend.alertas;

import javafx.scene.control.Alert;

public class Alerta {
    public void mostrarMensaje(String titulo, String mensaje) {
        mostrarDialogo(Alert.AlertType.INFORMATION, titulo, mensaje);
    }

    public void mostrarError(String titulo, String mensaje, String mensajeError) {
        mostrarDialogo(Alert.AlertType.ERROR, titulo, mensaje + "\n" + mensajeError);
    }

    private void mostrarDialogo(Alert.AlertType type, String titulo, String text) {
        Alert dialogo = new Alert(type);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(text);
        dialogo.showAndWait();
    }
}
