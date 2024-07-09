package org.example.parcialfinal.reports;

import org.example.parcialfinal.backend.database.DBConnection;

public class Reporte {
    protected DBConnection connection;

    public Reporte() {
        connection = DBConnection.getInstance();
    }
}
