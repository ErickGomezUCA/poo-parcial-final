package org.example.parcialfinal.backend;

public class Cliente { // 00300723 Clase para guardar los registros de la tabla Cliente
    private int id; // 00300723 Contiene el id de los registros de los clientes
    private String nombreCompleto; // 00300723 Contiene el nombre completo de los registros de los clientes
    private String direccion; // 00300723 Contiene la direccion de los registros de los clientes
    private String numeroTelefono; // 00300723 Contiene el numero de telefono de los registros de los clientes

    public Cliente() { // 00090123 Constructor por defecto de la clase Cliente
        id = 0; // 00090123 Inicializa el ID del cliente como 0
        nombreCompleto = ""; // 00090123 Inicializa el nombre completo del cliente como cadena vacía
        direccion = ""; // 00090123 Inicializa la dirección del cliente como cadena vacía
        numeroTelefono = ""; // 00090123 Inicializa el número de teléfono del cliente como cadena vacía
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

    public String getNombreCompleto() { // 00300723 Metodo para obtener el nombre completo del cliente
        return nombreCompleto; // 00300723 Devuelve el valor del nombre completo del cliente
    }

    public void setNombreCompleto(String nombreCompleto) { // 00090123 Método para establecer el nombre completo del cliente
        this.nombreCompleto = nombreCompleto;// 00090123 Asigna el valor del nombre completo al campo correspondiente
    }

    public String getDireccion() { // 00090123 Método para obtener la dirección del cliente
        return direccion;// 00090123 Devuelve el valor de la dirección del cliente
    }

    public void setDireccion(String direccion) {// 00090123 Método para establecer la dirección del cliente
        this.direccion = direccion;// 00090123 Asigna el valor de la dirección al campo correspondiente
    }

    public String getNumeroTelefono() { // 00090123 Método para obtener el número de teléfono del cliente
        return numeroTelefono;// 00090123 Devuelve el valor del número de teléfono del cliente
    }

    public void setNumeroTelefono(String numeroTelefono) { // 00090123 Método para establecer el número de teléfono del cliente
        this.numeroTelefono = numeroTelefono;// 00090123 Asigna el valor del número de teléfono al campo correspondiente
    }

    @Override
    public String toString() {// 00090123 Método para obtener la representación en cadena del cliente
        return "ID: " + id + ", Nombre: " + nombreCompleto;// 00090123 Devuelve una cadena que representa el ID y el nombre completo del cliente
    }
}
