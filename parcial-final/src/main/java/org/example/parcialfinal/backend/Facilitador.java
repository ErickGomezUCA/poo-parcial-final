package org.example.parcialfinal.backend;

public class Facilitador {
    private int id;
    private String facilitador;

    public Facilitador(int id, String facilitador) {
        this.id = id;
        this.facilitador = facilitador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacilitador() {
        return facilitador;
    }

    public void setFacilitador(String facilitador) {
        this.facilitador = facilitador;
    }
}
