package org.example.parcialfinal.backend;

public class Cliente { // 00300723 Clase para guardar los registros de la tabla Cliente
    private int id; // 00300723 Contiene el id de los registros de los clientes
    private String nombreCompleto; // 00300723 Contiene el nombre completo de los registros de los clientes
    private String direccion; // 00300723 Contiene la direccion de los registros de los clientes
    private String numeroTelefono; // 00300723 Contiene el numero de telefono de los registros de los clientes

    public Cliente() {
        id = 0;
        nombreCompleto = "";
        direccion = "";
        numeroTelefono = "";
    }

    public Cliente(int id, String nombreCompleto, String direccion, String numeroTelefono) { // 00300723 Constructor para los objetos de Cliente
        this.id = id; // 00300723 Asigna el id del cliente
        this.nombreCompleto = nombreCompleto; // 00300723 Asigna el nombre completo del cliente
        this.direccion = direccion; // 00300723 Asigna la direccion del cliente
        this.numeroTelefono = numeroTelefono; // 00300723 Asigna el numero de telefono del cliente
    }

    public int getId() { // 00300723 Metodo para obtiener el id del cliente
        return id; // 00300723 Devuelve el valor del id del cliente
    }

    public void setId(int id) { // 00300723 Metodo para establecer el valor del id del cliente
        this.id = id; // 00300723 Establece el id del cliente
    }

    public String getNombreCompleto() { // 00300723 Meotod para obtener el nombre completo del cliente
        return nombreCompleto; // 00300723 Devuelve el valor del nombre completo del cliente
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombreCompleto;
    }
}
