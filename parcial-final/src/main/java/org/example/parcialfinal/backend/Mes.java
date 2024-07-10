package org.example.parcialfinal.backend;

public enum Mes {
    ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIMEBRE, DICIEMBRE;

    @Override
    public String toString() {
        return capitalized(name());
    }

    public String capitalized(String value) {
        String primeraLetra = value.substring(0, 1).toUpperCase();
        String restoPalabra = value.substring(1).toLowerCase();
        return primeraLetra + restoPalabra;
    }

    public int getValue() {
        return this.ordinal() + 1;
    }
}
