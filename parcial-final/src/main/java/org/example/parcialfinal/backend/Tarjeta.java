package org.example.parcialfinal.backend;

public class Tarjeta {
    private int id;
    private String numeroTarjeta;
    private String fechaExpiracion;
    private String tipo;

    public Tarjeta() {
        id = 0;
        numeroTarjeta = "";
        fechaExpiracion = "";
        tipo = "";
    }

    public Tarjeta(int id, String numeroTarjeta, String fechaExpiracion, String tipo) {
        this.id = id;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
