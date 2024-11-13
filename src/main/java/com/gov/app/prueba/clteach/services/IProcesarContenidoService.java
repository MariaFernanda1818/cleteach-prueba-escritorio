/**
 * Interfaz para el servicio encargado de procesar el contenido de un archivo HL7.
 * <p>
 * Define el método necesario para procesar las líneas de un archivo HL7 y transformar su contenido
 * en un objeto {@link PacienteRequestDTO}.
 * </p>
 */
package com.gov.app.prueba.clteach.services;

import com.gov.app.prueba.clteach.dto.PacienteRequestDTO;

public interface IProcesarContenidoService {

    /**
     * Procesa el contenido de un archivo HL7 representado como un array de líneas.
     * <p>
     * Este método toma las líneas del archivo HL7, identifica y procesa los segmentos relevantes
     * (como PID, OBR, OBX) y convierte esta información en un objeto {@link PacienteRequestDTO}.
     * </p>
     *
     * @param lineas las líneas del archivo HL7 a procesar, divididas por saltos de línea.
     * @return un objeto {@link PacienteRequestDTO} que contiene la información procesada del paciente.
     */
    PacienteRequestDTO procesarContenido(String[] lineas);
}
