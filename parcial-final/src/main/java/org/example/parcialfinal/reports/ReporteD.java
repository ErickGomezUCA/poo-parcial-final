package org.example.parcialfinal.reports;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.database.DBConnection;
import org.example.parcialfinal.backend.reportes.ReporteUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteD extends Reporte{ //00167523 clase reporteD que se extiende de reporte
    public ReporteD(Control controlResultado) { //00167523 constructor de reported necesario por la clase reporte
        super(controlResultado); //00167523 pasa controlresultado al super de la clase reporte
    }

    public void generarReporte(ComboBox<Facilitador> selectFacilitador) { //00167523 metodo para generar el reported con parametros de javafx a utilizar
        String contenidoReporte = ""; //00167523 crea variable contenidoreporte que es igual a una cadena de texto vacia

        try { //00167523 comienzo del try para funcion generarreporte
            ResultSet rs = connection.getConnection().createStatement().executeQuery( //00167523 ejecuta la consulta sql
                "SELECT cl.id as 'id_cliente', cl.nombre_completo as 'nombre', t.num_tarjeta as 'num_tarjeta', f.facilitador as 'facilitador', COUNT(c.id_tarjeta_C) as 'cantidad', SUM(c.monto) as 'monto_total' " + //00167523 comienzo de consulta sql que selecciona id cliente, nombre completo cliente, num tarjeta de tarjeta, facilitador de tarjeta, count de idtarjeta en compra, suma del monto totla en compra
                    "FROM Compra c " + //00167523 se selecciona de la tabla compra
                    "INNER JOIN Tarjeta t ON c.id_tarjeta_C = t.id " + //00167523 se hace un inner join de la tabla compra con la tabla tarjeta que compara id_tarjeta de compra con id de tarjeta
                    "INNER JOIN Compras_Inteligentes ci ON c.id_tarjeta_C = ci.id_tarjeta_CI " + //00167523 se hace un inner join de compra con comprainteligente que compara idtarjeta en compra con idtarjeta en comprainteligente
                    "INNER JOIN Cliente cl ON ci.id_cliente_CI = cl.id " + //00167523 hace inner join de cliente con compra inteligente que compara id_cliente en compra inteliente con id de cliente
                    "INNER JOIN Facilitador f ON ci.id_facilitador_CI = f.id " + //00167523 hace inner join de facilitador con compra inteligente que compara idfacilitador en comprainteligente con id en facilitador
                    "WHERE f.id = " + selectFacilitador.getValue().getId() + " " + //00167523 condicion de id sea igual a id en combobox
                    "GROUP BY cl.id, cl.nombre_completo, t.num_tarjeta, f.facilitador;"); //00167523 se agrupa por id cliente, nombrecompleto cliente, numtarjeta de tarjeta y facilitador

            while(rs.next()) { //00167523 itera por los resultados
                contenidoReporte += ( //00167523 se anaden a contenidoreporte
                            "ID Cliente : " + rs.getInt("id_cliente") //00167523 se anade idcliente de la columna idcliente a contenidoreporte
                        + "\nNombre: " + rs.getString("nombre") //00167523 se anade nombre de la columna nombre a contenidoreporte
                        + "\nNumero de tarjeta: " + rs.getString("num_tarjeta") //00167523 se anade numtarjetea de la columna numtarjeta a contenidoreporte
                        + "\nFacilitador: " + rs.getString("facilitador") //00167523 se anade facilitador de la columna facilitador a contenidoreporte
                        + "\nCantidad: " + rs.getInt("cantidad") //00167523 se anade cantidad de la columna cantidad a contenidoreporte
                        + "\nMonto total: $" + rs.getDouble("monto_total") + "\n\n"); //00167523 se anade monto de la columna montototal a contenidoreporte
            }

            ((TextArea)controlResultado).setText(contenidoReporte); //00167523 se anade el resultado a un textarea
            ReporteUtils.generarReporte('D', contenidoReporte); //00167523 por medio de reporteutils genera un reporte en el txt
            connection.closeConnection(); //00167523 cierra conexion a bd
        } catch (SQLException e) { //00167523 maneja excepciones de sql
            e.printStackTrace(); //00167523 imprime mensaje de error
        }
    }
}
