package org.example.parcialfinal.adapter;

import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Compra;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.Tarjeta;

public class ComprasFacilitadorAdapter implements ComprasFacilitadorValorTabla {
    private Cliente cliente;
    private Tarjeta tarjeta;
    private Facilitador facilitador;
    private Compra compra;



    @Override
    public int obtenerIdCliente() {
        return cliente.getId();
    }

    @Override
    public String obtenerNombre() {
        return "";
    }

    @Override
    public String obtenerNumTarjeta() {
        return "";
    }

    @Override
    public String obtenerFacilitador() {
        return "";
    }

    @Override
    public int obtenerCantidad() {
        return 0;
    }

    @Override
    public double obtenerMontoTotal() {
        return 0;
    }
}
