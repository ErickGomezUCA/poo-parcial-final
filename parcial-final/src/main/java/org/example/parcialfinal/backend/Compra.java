package org.example.parcialfinal.backend;

public class Compra { // 00300723 Clase para guardar Compra
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
        return id; //00167523 retorna el id de la compra
    }

    public void setId(int id) { // 00090123 Método para establecer el ID de la compra
        this.id = id; //00167523 establece el id de la compra con id de parametro
    }

    public String getFechaCompra() { // 00090123 Método para obtener la fecha de la compra
        return fechaCompra; //00167523 retorna la fecha de la compra
    }

    public void setFechaCompra(String fechaCompra) { // 00090123 Método para establecer la fecha de la compra
        this.fechaCompra = fechaCompra; //00167523 establece la fecha de la compra con fechacompra de parametro
    }

    public double getMonto() { // 00090123 Método para obtener el monto de la compra
        return monto; //00167523 retorna monto de la compra
    }

    public void setMonto(double monto) { // 00090123 Método para establecer el monto de la compra
        this.monto = monto; //00167523 establece monto de la compra con el parametro monto
    }

    public String getDescripcion() { // 00090123 Método para obtener la descripción de la compra
        return descripcion; //00167523 retorna la descripcion de la compra
    }

    public void setDescripcion(String descripcion) { // 00090123 Método para establecer la descripción de la compra
        this.descripcion = descripcion; //00167523 establece la descripcion de la compra con la descripcion del parametor
    }

    public int getIdTajerta() { // 00090123 Método para obtener el ID de la tarjeta asociada a la compra
        return idTajerta; //00167523 retorna el idtarjeta asociada a la compra
    }

    public void setIdTajerta(int idTajerta) { // 00090123 Método para establecer el ID de la tarjeta asociada a la compra
        this.idTajerta = idTajerta; //00167523 establece idtarjeta a idtarjeta de parametro
    }

    @Override
    public String toString() { //00167523 metodo para hacer una cadena string de las variables de la clase
        return "ID: " + id + ", Fecha: " + fechaCompra + ", Monto: $" + monto; //00167523 retorna una cadena concatenada del id, fecha y monto de la compra
    }
}
