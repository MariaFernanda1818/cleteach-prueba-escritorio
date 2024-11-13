/**
 * Interfaz para el servicio encargado de procesar archivos HL7.
 * <p>
 * Proporciona la definición del método necesario para leer y procesar un archivo HL7, 
 * devolviendo una respuesta estructurada en un objeto {@link RespuestaGeneralDTO}.
 * </p>
 */
package com.gov.app.prueba.clteach.services;

import com.gov.app.prueba.clteach.dto.RespuestaGeneralDTO;
import java.io.File;

public interface ILeerArchivoService {

    /**
     * Lee y procesa un archivo en formato HL7.
     * <p>
     * Este método toma un archivo HL7 como entrada, procesa su contenido y genera 
     * una respuesta con el resultado del procesamiento.
     * </p>
     *
     * @param archivo el archivo HL7 a procesar.
     * @return un objeto {@link RespuestaGeneralDTO} que contiene la información 
     * del resultado del procesamiento del archivo.
     */
    RespuestaGeneralDTO leerArchivoHl7(File archivo);
}
