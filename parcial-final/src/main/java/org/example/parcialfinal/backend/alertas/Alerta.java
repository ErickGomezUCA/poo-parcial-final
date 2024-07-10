package org.example.parcialfinal.backend.alertas;

import javafx.scene.control.Alert;

public class Alerta { // 00300723 Clase para mostrar mensajes en pantalla
    public void mostrarMensaje(String titulo, String mensaje) { // 00090123 Método para mostrar un mensaje de información
        mostrarDialogo(Alert.AlertType.INFORMATION, titulo, mensaje); // 00090123 Invoca el método para mostrar un diálogo de tipo INFORMATION
    }

    public void mostrarError(String titulo, String mensaje, String mensajeError) { // 00090123 Método para mostrar un mensaje de error
        mostrarDialogo(Alert.AlertType.ERROR, titulo, mensaje + "\n" + mensajeError); // 00090123 Invoca el método para mostrar un diálogo de tipo ERROR con mensaje extendido
    }

    private void mostrarDialogo(Alert.AlertType type, String titulo, String text) { // 00090123 Método privado para mostrar un diálogo
        Alert dialogo = new Alert(type); // 00090123 Crea un nuevo diálogo de tipo especificado
        dialogo.setTitle(titulo); // 00090123 Establece el título del diálogo
        dialogo.setHeaderText(null); // 00090123 Configura el encabezado del diálogo como nulo
        dialogo.setContentText(text); // 00090123 Establece el texto del contenido del diálogo
        dialogo.showAndWait(); // 00090123 Muestra el diálogo y espera a que el usuario lo cierre
    }
}
