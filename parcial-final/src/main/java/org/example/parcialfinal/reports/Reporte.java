package org.example.parcialfinal.reports;

import javafx.scene.control.Control;
import org.example.parcialfinal.backend.database.DBConnection;

public class Reporte { //00167523 Clase general reporte de tipo factory
    protected DBConnection connection; //00167523 Campo protected para la conexion utilizada a BD
    protected Control controlResultado; //00167523 Campo protected para controlar los resultados

    public Reporte(Control controlResultado) { //00167523 Constructor de la clase reporte que tiene como parametro Control
        connection = DBConnection.getInstance(); //00167523 Inicializa la conexion con el singleton de DBConnection
        this.controlResultado = controlResultado; //00167523 Asigna el valor del parametro a controlresultado
    }
}
