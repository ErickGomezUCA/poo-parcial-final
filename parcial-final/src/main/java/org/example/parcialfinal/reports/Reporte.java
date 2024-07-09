package org.example.parcialfinal.reports;

import javafx.scene.control.Control;
import org.example.parcialfinal.backend.database.DBConnection;

public class Reporte {
    protected DBConnection connection;
    protected Control controlResultado;

    public Reporte(Control controlResultado) {
        connection = DBConnection.getInstance();
        this.controlResultado = controlResultado;
    }
}
