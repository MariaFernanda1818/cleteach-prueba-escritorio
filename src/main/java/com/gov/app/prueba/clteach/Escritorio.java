/**
 * Clase principal que inicia la aplicación de escritorio para la subida de archivos HL7.
 * <p>
 * Esta clase crea una instancia del servicio {@link LeerArchivoService} y abre
 * la ventana de subida de archivos {@link SubidaArchivosWindow}.
 * Utiliza {@link SwingUtilities} para garantizar que la interfaz gráfica se inicialice
 * en el hilo de eventos de Swing.
 * </p>
 */
package com.gov.app.prueba.clteach;

import com.gov.app.prueba.clteach.services.impl.LeerArchivoService;
import com.gov.app.prueba.clteach.utils.constants.Constants;
import com.gov.app.prueba.clteach.vistas.SubidaArchivosWindow;
import javax.swing.SwingUtilities;

public class Escritorio {

    /**
     * Método principal que inicia la ejecución de la aplicación.
     * <p>
     * Este método inicializa el servicio {@link LeerArchivoService} y lanza
     * la ventana gráfica para la subida de archivos.
     * </p>
     *
     * @param args Argumentos de línea de comandos. Actualmente no se utilizan.
     */
    public static void main(String[] args) {
        // Crear la instancia del servicio para leer archivos HL7
        LeerArchivoService leerArchivosService = new LeerArchivoService();

        // Iniciar la interfaz gráfica en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            SubidaArchivosWindow subida = new SubidaArchivosWindow(leerArchivosService);
            subida.setVisible(true);
            subida.setSize(Constants.WIDTH, Constants.HEIGHT);
        });
    }
}
