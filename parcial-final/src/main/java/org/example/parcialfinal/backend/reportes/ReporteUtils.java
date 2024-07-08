package org.example.parcialfinal.backend.reportes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReporteUtils { // 00300723 Clase para guardar algunas funciones utiles para generar reportes en archivos de formato .txt
    static File carpertaReportes; // 00300723 Variable estatica para guardar la carpeta de reportes

    public static void generarReporte(char letraReporte, String contenido) { // 00300723 Funcion para generar cualquiera de los 4 reportes
        carpertaReportes = new File("reportes"); // 00300723 Establecer el nombre de la carpeta para guardar los reportes

        if (!carpertaReportes.exists()) { // 00300723 Verificar si la carpeta reportes no existe
            carpertaReportes.mkdir(); // 00300723 En caso que no exista, entonces craer una nueva en la ruta del proyecto
        }

        String fechaHoraActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")); // 00300723 Obtener la fecha y hora cuando se creo el reporte

        try { // 00300723 Try catch para craer el archivo del reporte
            FileWriter fileWriter = new FileWriter(carpertaReportes.toString() + File.separator + "Reporte" + letraReporte + "-" + fechaHoraActual + ".txt"); // 00300723 Crea el archivo del reporte en la carpeta "reportes"
            fileWriter.write(contenido); // 00300723 Agregar el contenido del reporte al archivo txt
            fileWriter.close(); // 00300723 Cierra la creacion del reporte
            System.out.println("Reporte " + letraReporte + " creado."); // 00300723 Imprime que el reporte fue creado (reemplazar a mostrar un mensaje en el programa)
        } catch (IOException e) { // 00300723 En caso que exista un error en la creacion del reporte
            System.out.println("No se puede crear el reporte"); // 00300723 Informar que no se pudo crear el reporte
            e.printStackTrace(); // 00300723 Mostrar los errores en consola
        }
    }
}
