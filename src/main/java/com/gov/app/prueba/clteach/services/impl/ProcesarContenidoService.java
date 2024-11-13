package com.gov.app.prueba.clteach.services.impl;

import com.gov.app.prueba.clteach.dto.PacienteRequestDTO;
import com.gov.app.prueba.clteach.services.IProcesarContenidoService;
import static com.gov.app.prueba.clteach.utils.constants.Constants.HL7_NO_VALIDO;
import static com.gov.app.prueba.clteach.utils.constants.Constants.OBR;
import static com.gov.app.prueba.clteach.utils.constants.Constants.OBR_INCOMPLETO;
import static com.gov.app.prueba.clteach.utils.constants.Constants.OBX;
import static com.gov.app.prueba.clteach.utils.constants.Constants.OBX_INCOMPLETO;
import static com.gov.app.prueba.clteach.utils.constants.Constants.PATRON_BARRA;
import static com.gov.app.prueba.clteach.utils.constants.Constants.PATRON_L;
import static com.gov.app.prueba.clteach.utils.constants.Constants.PID;
import static com.gov.app.prueba.clteach.utils.constants.Constants.PID_INCOMPLETO;
import static com.gov.app.prueba.clteach.utils.constants.Constants.SEGMENTOS_DESCONOCIDOS;
import static com.gov.app.prueba.clteach.utils.constants.Constants.SEGMENTO_VACIO_MAL_FORMATO;
import com.gov.app.prueba.clteach.utils.helper.Utilities;
import lombok.extern.slf4j.Slf4j;

/**
 * Servicio encargado de procesar el contenido de archivos HL7.
 * <p>
 * Implementa la interfaz {@link IProcesarContenidoService} para extraer información relevante
 * de los segmentos HL7, como "PID", "OBR" y "OBX", y convertirla en un objeto {@link PacienteRequestDTO}.
 * </p>
 */
@Slf4j
public class ProcesarContenidoService implements IProcesarContenidoService {

    /**
     * Procesa el contenido de un archivo HL7 representado como un array de líneas.
     * <p>
     * Este método analiza cada segmento del archivo HL7, valida su formato y extrae
     * información relevante, como los datos del paciente, número de orden, código del examen
     * y resultado del examen. Si el archivo no contiene segmentos válidos, lanza una excepción.
     * </p>
     *
     * @param lineas las líneas del archivo HL7, separadas por saltos de línea.
     * @return un objeto {@link PacienteRequestDTO} que contiene la información procesada del paciente.
     * @throws IllegalArgumentException si las líneas del archivo están vacías o son nulas.
     * @throws IllegalStateException si el archivo HL7 no contiene segmentos válidos.
     */
    @Override
    public PacienteRequestDTO procesarContenido(String[] lineas) {
        if (lineas == null || lineas.length == 0) {
            throw new IllegalArgumentException("El contenido del archivo HL7 está vacío o es nulo");
        }

        PacienteRequestDTO request = new PacienteRequestDTO();
        boolean formatoValido = false;

        for (String segmento : lineas) {
            // Limpiar y dividir el segmento
            String texto = Utilities.limpiarLinea(segmento);
            String[] campos = texto.split("\\|");

            if (campos.length == 0) {
                log.warn(SEGMENTO_VACIO_MAL_FORMATO, segmento);
                continue;
            }

            // Procesar el segmento
            switch (campos[0]) {
                case PID -> formatoValido = procesarPID(campos, request);
                case OBR -> formatoValido = procesarOBR(campos, request);
                case OBX -> formatoValido = procesarOBX(campos, request);
                default -> log.info(SEGMENTOS_DESCONOCIDOS, segmento);
            }
        }

        if (!formatoValido) {
            throw new IllegalStateException(HL7_NO_VALIDO);
        }

        return request;
    }

    /**
     * Procesa el segmento "PID" del archivo HL7 para extraer información del paciente.
     *
     * @param campos los campos del segmento "PID", separados por el delimitador `|`.
     * @param request el objeto {@link PacienteRequestDTO} donde se almacenará la información procesada.
     * @return {@code true} si el segmento es válido y se procesó correctamente; {@code false} en caso contrario.
     */
    private boolean procesarPID(String[] campos, PacienteRequestDTO request) {
        if (campos.length > 2) {
            request.setNombres(campos[5]);
            return true;
        } else {
            log.warn(PID_INCOMPLETO, String.join(PATRON_BARRA, campos));
            return false;
        }
    }

    /**
     * Procesa el segmento "OBR" del archivo HL7 para extraer el número de orden del paciente.
     *
     * @param campos los campos del segmento "OBR", separados por el delimitador `|`.
     * @param request el objeto {@link PacienteRequestDTO} donde se almacenará la información procesada.
     * @return {@code true} si el segmento es válido y se procesó correctamente; {@code false} en caso contrario.
     */
    private boolean procesarOBR(String[] campos, PacienteRequestDTO request) {
        if (campos.length > 2) {
            request.setNumeroOrden(campos[3]);
            return true;
        } else {
            log.warn(OBR_INCOMPLETO, String.join(PATRON_BARRA, campos));
            return false;
        }
    }

    /**
     * Procesa el segmento "OBX" del archivo HL7 para extraer el código y el resultado del examen.
     *
     * @param campos los campos del segmento "OBX", separados por el delimitador `|`.
     * @param request el objeto {@link PacienteRequestDTO} donde se almacenará la información procesada.
     * @return {@code true} si el segmento es válido y se procesó correctamente; {@code false} en caso contrario.
     */
    private boolean procesarOBX(String[] campos, PacienteRequestDTO request) {
        if (campos.length > 2) {
            request.setCodigoExamen(Utilities.quitarPalabraFinal(campos[3], PATRON_L));
            request.setResultadoExamen(campos[5]);
            return true;
        } else {
            log.warn(OBX_INCOMPLETO, String.join(PATRON_BARRA, campos));
            return false;
        }
    }
}
