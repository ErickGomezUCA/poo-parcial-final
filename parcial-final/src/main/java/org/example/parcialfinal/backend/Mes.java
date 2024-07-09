package org.example.parcialfinal.backend;

public enum Mes {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;

    public int getValue() {
        return this.ordinal() + 1;
    }
}
