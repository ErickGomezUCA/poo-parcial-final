package org.example.parcialfinal.adapter;

import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Compra;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.Tarjeta;

public class ComprasFacilitadorAdapter implements ComprasFacilitadorValorTabla {
    private Cliente cliente;
    private Tarjeta tarjeta;
    private Facilitador facilitador;
    int cantidad;
    double montoTotal;

    public ComprasFacilitadorAdapter(int clienteId, String nombreCliente, String numTarjeta, String nombreFacilitador, int cantidad, double montoTotal) {
        cliente = new Cliente();
        tarjeta = new Tarjeta();
        facilitador = new Facilitador();

        cliente.setId(clienteId);
        cliente.setNombreCompleto(nombreCliente);

        tarjeta.setNumeroTarjeta(numTarjeta);
        facilitador.setFacilitador(nombreFacilitador);

        this.cantidad = cantidad;
        this.montoTotal = montoTotal;
    }

    @Override
    public int obtenerIdCliente() {
        return cliente.getId();
    }

    @Override
    public String obtenerNombre() {
        return cliente.getNombreCompleto();
    }

    @Override
    public String obtenerNumTarjeta() {
        return tarjeta.getNumeroTarjeta();
    }

    @Override
    public String obtenerFacilitador() {
        return facilitador.getFacilitador();
    }

    @Override
    public int obtenerCantidad() {
        return cantidad;
    }

    @Override
    public double obtenerMontoTotal() {
        return montoTotal;
    }
}
