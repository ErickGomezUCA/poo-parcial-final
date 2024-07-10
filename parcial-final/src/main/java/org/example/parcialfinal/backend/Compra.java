package org.example.parcialfinal.backend;

public class Compra {
    private int id; // 00090123 Identificador único de la compra
    private String fechaCompra; // 00090123 Fecha de la compra
    private double monto; // 00090123 Monto de la compra
    private String descripcion; // 00090123 Descripción de la compra
    private int idTajerta; // 00090123 ID de la tarjeta asociada a la compra

    public Compra() { // 00090123 Constructor por defecto de la clase Compra
        id = 0; // 00090123 Inicializa el ID de la compra como 0
        fechaCompra = ""; // 00090123 Inicializa la fecha de la compra como cadena vacía
        monto = 0; // 00090123 Inicializa el monto de la compra como 0
        descripcion = ""; // 00090123 Inicializa la descripción de la compra como cadena vacía
        idTajerta = 0; // 00090123 Inicializa el ID de la tarjeta asociada como 0
    }

    public Compra(int id, String fechaCompra, double monto, String descripcion, int idTajerta) { // 00090123 Constructor con parámetros de la clase Compra
        this.id = id; // 00090123 Asigna el ID de la compra con el valor proporcionado
        this.fechaCompra = fechaCompra; // 00090123 Asigna la fecha de la compra con el valor proporcionado
        this.monto = monto; // 00090123 Asigna el monto de la compra con el valor proporcionado
        this.descripcion = descripcion; // 00090123 Asigna la descripción de la compra con el valor proporcionado
        this.idTajerta = idTajerta; // 00090123 Asigna el ID de la tarjeta asociada con el valor proporcionado
    }
    public int getId() { // 00090123 Método para obtener el ID de la compra
        return id;
    }

    public void setId(int id) { // 00090123 Método para establecer el ID de la compra
        this.id = id;
    }

    public String getFechaCompra() { // 00090123 Método para obtener la fecha de la compra
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) { // 00090123 Método para establecer la fecha de la compra
        this.fechaCompra = fechaCompra;
    }

    public double getMonto() { // 00090123 Método para obtener el monto de la compra
        return monto;
    }

    public void setMonto(double monto) { // 00090123 Método para establecer el monto de la compra
        this.monto = monto;
    }

    public String getDescripcion() { // 00090123 Método para obtener la descripción de la compra
        return descripcion;
    }

    public void setDescripcion(String descripcion) { // 00090123 Método para establecer la descripción de la compra
        this.descripcion = descripcion;
    }

    public int getIdTajerta() { // 00090123 Método para obtener el ID de la tarjeta asociada a la compra
        return idTajerta;
    }

    public void setIdTajerta(int idTajerta) { // 00090123 Método para establecer el ID de la tarjeta asociada a la compra
        this.idTajerta = idTajerta;
    }
}
