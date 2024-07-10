package org.example.parcialfinal.backend;

public enum Mes { // 00300723 Guarda todos los meses del año
    ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE; // 00090123 Enumeración de los meses del año

    @Override
    public String toString() { // 00090123 Devuelve el nombre del mes con la primera letra en mayúscula y el resto en minúscula
        return capitalized(name()); // 00300723 Cambia el formato del string
    }

    public String capitalized(String value) { // 00090123 Convierte la primera letra de una cadena a mayúscula y el resto a minúscula
        String primeraLetra = value.substring(0, 1).toUpperCase(); // 00300723 Obtiene la primera letra y la pasa a mayusculas
        String restoPalabra = value.substring(1).toLowerCase(); // 00300723 El resto del cuerpo en minuscula
        return primeraLetra + restoPalabra; // 00300723 Une ambas partes
    }

    public int getValue() { // 00090123 Devuelve el valor ordinal del mes incrementado en 1
        return this.ordinal() + 1; // 00300723 cada enum empieza a contar desde 0, entonces ahora cuenta desde 1
    }

}
