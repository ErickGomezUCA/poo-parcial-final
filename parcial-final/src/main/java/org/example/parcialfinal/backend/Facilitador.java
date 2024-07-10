package org.example.parcialfinal.backend;

public class Facilitador { // 00300723 Clase para guardar facilitador
    private int id; // 00090123 Identificador único del facilitador
    private String facilitador; // 00090123 Nombre del facilitador

    public Facilitador() { // 00090123 Constructor por defecto de la clase Facilitador
        id = 0; // 00090123 Inicializa el ID del facilitador como 0
        facilitador = ""; // 00090123 Inicializa el nombre del facilitador como cadena vacía
    }

    public Facilitador(int id, String facilitador) { // 00090123 Constructor con parámetros de la clase Facilitador
        this.id = id; // 00090123 Asigna el ID del facilitador con el valor proporcionado
        this.facilitador = facilitador; // 00090123 Asigna el nombre del facilitador con el valor proporcionado
    }

    public int getId() { // 00090123 Método para obtener el ID del facilitador
        return id; // 00300723 Retorna el id
    }

    public void setId(int id) { // 00090123 Método para establecer el ID del facilitador
        this.id = id; // 00300723 Setea el id
    }

    public String getFacilitador() { // 00090123 Método para obtener el nombre del facilitador
        return facilitador; // 00300723 Retorna el facilitador
    }

    public void setFacilitador(String facilitador) { // 00090123 Método para establecer el nombre del facilitador
        this.facilitador = facilitador; // 00300723 Setea el facilitador
    }

    @Override
    public String toString() { // 00090123 Método para obtener una representación en cadena del facilitador
        return facilitador; // 00300723 Retorna solo el nombre del facilitador
    }

}
