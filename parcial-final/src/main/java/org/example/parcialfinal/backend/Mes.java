package org.example.parcialfinal.backend;

public enum Mes {
    ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE; // 00090123 Enumeración de los meses del año

    @Override
    public String toString() { // 00090123 Devuelve el nombre del mes con la primera letra en mayúscula y el resto en minúscula
        return capitalized(name());
    }

    public String capitalized(String value) { // 00090123 Convierte la primera letra de una cadena a mayúscula y el resto a minúscula
        String primeraLetra = value.substring(0, 1).toUpperCase();
        String restoPalabra = value.substring(1).toLowerCase();
        return primeraLetra + restoPalabra;
    }

    public int getValue() { // 00090123 Devuelve el valor ordinal del mes incrementado en 1
        return this.ordinal() + 1;
    }

}
