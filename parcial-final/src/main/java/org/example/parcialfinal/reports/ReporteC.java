package org.example.parcialfinal.reports;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Tarjeta;
import org.example.parcialfinal.backend.reportes.ReporteUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteC extends Reporte { //00167523 clase reportec que se extiende de reporte
    public ReporteC(Control controlResultado) { //00167523 constructor de reportec necesario por la clase reporte
        super(controlResultado); //00167523 pasa controlresultado al super de la clase reporte
    }

    public void generarReporte(ComboBox<Cliente> selectCliente) { //00167523 metodo para generarreporte con parametros de javafx a utilizar
        String contenidoReporte = ""; //00167523 variable string que es igual a una cadena de texto vacia
        List<String> tarjetasCredito = new ArrayList<>(); //00167523 lista de tipo string de las tarjetascredito
        List<String> tarjetasDebito = new ArrayList<>(); //00167523 lista tipo strinf de las tarjetasdebito

        try { //00167523 comienzo del try utilizado
            ResultSet rs = connection.getConnection().createStatement().executeQuery( //00167523 ejecuta una consulta sql
                    "SELECT t.id, t.num_tarjeta, t.fecha_expiracion, t.tipo_tarjeta FROM Compras_Inteligentes ci " + //00167523 comienzo de consulta que selecciona el id de tarjeta, num tarjeta, fecha expiracion tarjeta y el tipo de tarjeta
                            "INNER JOIN Tarjeta t ON ci.id_tarjeta_CI = t.id " + //00167523 se hace un inner join de tarjeta con comprasinteligentes que sucede cuando el id_tarjeta en la tabla compras inteligentes es igual al id en tabla tarjeta
                            "WHERE ci.id_cliente_CI = " + selectCliente.getValue().getId() + ";" //00167523 se pone una condicion que mira el combobox selectcliente que sea igual al id_cliente en comprasinteligentes
            );

            while(rs.next()) { //00167523 itera por cada fila de resultado
                Tarjeta tarjeta = new Tarjeta(rs.getInt("id"), rs.getString("num_tarjeta"), rs.getString("fecha_expiracion"), rs.getString("tipo_tarjeta")); //00167523 se agrega la tarjeta a una lista por medio de columnas de sql por el resultado

                switch (tarjeta.getTipo()) { //00167523 switch que toma como parametro el tipo de tarjeta
                    case "Credito": //00167523 en caso de tipo credito
                        tarjetasCredito.add(censurarTarjeta(tarjeta.getNumeroTarjeta())); //00167523 se anade la tarjeta y se censura
                        break; //00167523 hace un break si pasa el caso credito

                    case "Debito": //00167523 en caso de tipo debito
                        tarjetasDebito.add(censurarTarjeta(tarjeta.getNumeroTarjeta())); //00167523 se anade a la lista y se censura
                        break; //00167523 hace un break su pasa el caso debito

                    default: //00167523 caso default
                        System.out.println("Tipo no valido"); //00167523 se imprime que no es un tipo de tarjeta valido
                }
            }

            contenidoReporte += "Tarjetas de crédito:\n"; //00167523 se anade al contenido reporte
            if (!tarjetasCredito.isEmpty()) { //00167523 mira si la lista tarjetascredito no esta vacia
                for (String cred : tarjetasCredito) { //00167523 recorre la lista
                    contenidoReporte += "\t" + cred + "\n"; //00167523 se anaden las tarjetas a contenidoreporte
                }
            } else { //00167523  si esta vacia la lista
                contenidoReporte += "\tN/A\n"; //00167523 se pone N/A en contenidoreporte
            }

            contenidoReporte += "Tarjetas de débito:\n"; //00167523 se anade al contenidoreporte
            if (!tarjetasDebito.isEmpty()) { //00167523 mira si la lista no esta vacia
                for (String deb : tarjetasDebito) { //00167523 recorre la lista
                    contenidoReporte += "\t" + deb + "\n"; //00167523 se anaden las tarjetas a contenidoreporte
                }
            } else { //00167523 si la lista esta vacia
                contenidoReporte += "\tN/A\n"; //00167523 se pone N/A en contenidoreporte
            }

            ((TextArea)controlResultado).setText(contenidoReporte); //00167523 agrega contenidoreporte a un textarea
            ReporteUtils.generarReporte('C', contenidoReporte); //00167523 por medio de reporteutils genera el txt del reporte
            connection.closeConnection(); //00167523 se cierra la conexion a bd
        } catch (SQLException e) { //00167523 maneja las excepciones de sql
            throw new RuntimeException(e); //00167523 tira una exceocion en caso de que haya
        }
    }

    private String censurarTarjeta(String numeroTarjeta) {
        String censurado = "";
        char digito;

        for (int i = 0; i < numeroTarjeta.length(); i++) {
            digito = numeroTarjeta.charAt(i);

            if (Character.isDigit(digito) && i < numeroTarjeta.length() - 4) {
                censurado += 'X';
            } else {
                censurado += digito;
            }
        }

        return censurado;
    }
}
