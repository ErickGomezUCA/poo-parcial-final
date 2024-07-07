package org.example.parcialfinal.backend.reportes;

import java.io.File;

public class ReporteUtils {
    static File carpertaReportes;

    public static void generarReporte(char LetraReporte, String contenido) { // 00300723 Funcion para generar cualquiera de los 4 reportes
        carpertaReportes = new File("reportes"); // 00300723 Establecer el nombre de la carpeta para guardar los reportes

        if (!carpertaReportes.exists()) { // 00300723 Verificar si la carpeta reportes no existe
            carpertaReportes.mkdir(); // 00300723 En caso que no exista, entonces craer una nueva en la ruta del proyecto
        }

        
    }
}
