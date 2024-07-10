package org.example.parcialfinal.backend;

public class Tarjeta { // 00090123 Declaración de la clase Tarjeta
    private int id; // 00090123 Campo privado para almacenar el ID de la tarjeta
    private String numeroTarjeta; // 00090123 Campo privado para almacenar el número de la tarjeta
    private String fechaExpiracion; // 00090123 Campo privado para almacenar la fecha de expiración de la tarjeta
    private String tipo; // 00090123 Campo privado para almacenar el tipo de la tarjeta

    public Tarjeta() {  // 00090123 Constructor por defecto de la clase Tarjeta
        id = 0; // 00090123 Inicializa el ID a 0
        numeroTarjeta = ""; // 00090123 Inicializa el número de tarjeta a una cadena vacía
        fechaExpiracion = ""; // 00090123 Inicializa la fecha de expiración a una cadena vacía
        tipo = ""; // 00090123 Inicializa el tipo a una cadena vacía
    }

    public Tarjeta(int id, String numeroTarjeta, String fechaExpiracion, String tipo) {// 00090123 Constructor con parámetros para la clase Tarjeta
        this.id = id; // 00090123 Asigna el valor del parámetro id al campo id
        this.numeroTarjeta = numeroTarjeta; // 00090123 Asigna el valor del parámetro numeroTarjeta al campo numeroTarjeta
        this.fechaExpiracion = fechaExpiracion; // 00090123 Asigna el valor del parámetro fechaExpiracion al campo fechaExpiracion
        this.tipo = tipo; // 00090123 Asigna el valor del parámetro tipo al campo tipo
    }

    public int getId() { // 00090123 Método para obtener el ID de la tarjeta
        return id; // 00090123 Devuelve el valor del ID de la tarjeta
    }

    public void setId(int id) { // 00090123 Método para establecer el ID de la tarjeta
        this.id = id; // 00090123 Asigna el valor del parámetro id al campo id
    }

    public String getNumeroTarjeta() { // 00090123 Método para obtener el número de la tarjeta
        return numeroTarjeta; // 00090123 Devuelve el valor del número de la tarjeta
    }

    public void setNumeroTarjeta(String numeroTarjeta) { // 00090123 Método para establecer el número de la tarjeta
        this.numeroTarjeta = numeroTarjeta; // 00090123 Asigna el valor del parámetro numeroTarjeta al campo numeroTarjeta
    }

    public String getFechaExpiracion() { // 00090123 Método para obtener la fecha de expiración de la tarjeta
        return fechaExpiracion; // 00090123 Devuelve el valor de la fecha de expiración de la tarjeta
    }

    public void setFechaExpiracion(String fechaExpiracion) { // 00090123 Método para establecer la fecha de expiración de la tarjeta
        this.fechaExpiracion = fechaExpiracion; // 00090123 Asigna el valor del parámetro fechaExpiracion al campo fechaExpiracion
    }

    public String getTipo() { // 00090123 Método para obtener el tipo de la tarjeta
        return tipo; // 00090123 Devuelve el valor del tipo de la tarjeta
    }

    public void setTipo(String tipo) { // 00090123 Método para establecer el tipo de la tarjeta
        this.tipo = tipo; // 00090123 Asigna el valor del parámetro tipo al campo tipo
    }

    @Override
    public String toString() { //00167523 Metodo para imprimir en una string las variables de la clase
        return "ID: " + id + ", Numero: " + numeroTarjeta; //00167523 Retorna como string concatenada el id y numero de tarjeta
    }
}
