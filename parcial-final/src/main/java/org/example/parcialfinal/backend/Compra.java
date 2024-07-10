package org.example.parcialfinal.backend;

public class Compra {
    private int id;
    private String fechaCompra;
    private double monto;
    private String descripcion;
    private int idTajerta;

    public Compra() {
        id = 0;
        fechaCompra = "";
        monto = 0;
        descripcion = "";
        idTajerta = 0;
    }

    public Compra(int id, String fechaCompra, double monto, String descripcion, int idTajerta) {
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.monto = monto;
        this.descripcion = descripcion;
        this.idTajerta = idTajerta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTajerta() {
        return idTajerta;
    }

    public void setIdTajerta(int idTajerta) {
        this.idTajerta = idTajerta;
    }
}
