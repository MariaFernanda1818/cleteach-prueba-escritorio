package com.gov.app.prueba.clteach.services.impl;

import com.gov.app.prueba.clteach.dto.ExamenDTO;
import com.gov.app.prueba.clteach.dto.PacienteRequestDTO;
import com.gov.app.prueba.clteach.services.IProcesarContenidoService;
import com.gov.app.prueba.clteach.utils.helper.Utilities;
import lombok.extern.slf4j.Slf4j;

import static com.gov.app.prueba.clteach.utils.constants.Constants.*;

/**
 * Servicio encargado de procesar el contenido de un archivo HL7.
 * <p>
 * Implementa la interfaz {@link IProcesarContenidoService} y proporciona la funcionalidad
 * para analizar y extraer información relevante de los segmentos HL7 ("PID", "OBR", "OBX").
 * Los datos procesados se almacenan en un objeto {@link PacienteRequestDTO}.
 * </p>
 */
@Slf4j
public class ProcesarContenidoService implements IProcesarContenidoService {

     /**
     * Procesa el contenido de un archivo HL7 representado como un array de líneas.
     *
     * <p>
     * Este método analiza cada línea del archivo HL7, identifica los segmentos válidos
     * ("PID", "OBR", "OBX"), y extrae información relevante como los datos del paciente,
     * número de orden y exámenes. Si el archivo no contiene segmentos válidos, lanza
     * una excepción.
     * </p>
     *
     * @param lineas las líneas del archivo HL7, separadas por saltos de línea.
     * @return un objeto {@link PacienteRequestDTO} que contiene los datos procesados del paciente.
     * @throws IllegalArgumentException si las líneas están vacías o son nulas.
     * @throws IllegalStateException    si el archivo HL7 no contiene segmentos válidos.
     */
    @Override
    public PacienteRequestDTO procesarContenido(String[] lineas) {
        if (lineas == null || lineas.length == 0) {
            throw new IllegalArgumentException(HL7_VACIO_NULO);
        }

        PacienteRequestDTO request = new PacienteRequestDTO();
        boolean formatoValido = false;

        for (String segmento : lineas) {
            if (segmento.isBlank()) {
                log.warn(SEGMENTO_VACIO_MAL_FORMATO);
                continue;
            }

            String textoLimpio = Utilities.limpiarLinea(segmento);
            String[] campos = textoLimpio.split(PATRON_CAMPOS);

            if (campos.length == 0) {
                log.warn(SEGMENTO_VACIO_MAL_FORMATO, segmento);
                continue;
            }

            formatoValido = procesarSegmento(campos, request);
        }

        if (!formatoValido) {
            throw new IllegalStateException(HL7_NO_VALIDO);
        }

        return request;
    }

  /**
     * Procesa un segmento HL7 según su tipo.
     *
     * <p>
     * Identifica el tipo del segmento HL7 (por ejemplo, "PID", "OBR", "OBX") y delega
     * el procesamiento al método correspondiente. Si el segmento no es reconocido,
     * se registra en los logs como desconocido.
     * </p>
     *
     * @param campos  los campos del segmento HL7, separados por el delimitador `|`.
     * @param request el objeto {@link PacienteRequestDTO} donde se almacenará la información procesada.
     * @return {@code true} si el segmento fue procesado correctamente; {@code false} en caso contrario.
     */
    private boolean procesarSegmento(String[] campos, PacienteRequestDTO request) {
        return switch (campos[0]) {
            case PID -> procesarPID(campos, request);
            case OBR -> procesarOBR(campos, request);
            case OBX -> procesarOBX(campos, request);
            default -> {
                log.info(SEGMENTOS_DESCONOCIDOS, String.join(PATRON_BARRA, campos));
                yield false;
            }
        };
    }

     /**
     * Procesa un segmento "PID" para extraer información del paciente.
     *
     * <p>
     * Este método analiza el segmento "PID" del archivo HL7 y extrae el nombre completo
     * del paciente. Si el segmento está incompleto, registra una advertencia en los logs.
     * </p>
     *
     * @param campos  los campos del segmento "PID", separados por el delimitador `|`.
     * @param request el objeto {@link PacienteRequestDTO} donde se almacenará la información procesada.
     * @return {@code true} si el segmento es válido y se procesó correctamente; {@code false} en caso contrario.
     */
    private boolean procesarPID(String[] campos, PacienteRequestDTO request) {
        if (campos.length > 5) {
            request.setNombres(Utilities.quitarPalabraFinal(campos[5], PATRON_P));
            return true;
        } else {
            log.warn(PID_INCOMPLETO, String.join(PATRON_BARRA, campos));
            return false;
        }
    }

     /**
     * Procesa un segmento "OBR" para extraer el número de orden del paciente.
     *
     * <p>
     * Este método analiza el segmento "OBR" del archivo HL7 y extrae el número de orden
     * asociado al paciente. Si el segmento está incompleto, registra una advertencia en los logs.
     * </p>
     *
     * @param campos  los campos del segmento "OBR", separados por el delimitador `|`.
     * @param request el objeto {@link PacienteRequestDTO} donde se almacenará la información procesada.
     * @return {@code true} si el segmento es válido y se procesó correctamente; {@code false} en caso contrario.
     */
    private boolean procesarOBR(String[] campos, PacienteRequestDTO request) {
        if (campos.length > 2) {
            request.setNumeroOrden(campos[2].strip());
            return true;
        } else {
            log.warn(OBR_INCOMPLETO, String.join(PATRON_BARRA, campos));
            return false;
        }
    }

     /**
     * Procesa un segmento "OBX" para extraer el código y el resultado del examen.
     *
     * <p>
     * Este método analiza el segmento "OBX" del archivo HL7 y extrae información
     * del examen, como el código y el resultado. Si el segmento está incompleto,
     * registra una advertencia en los logs.
     * </p>
     *
     * @param campos  los campos del segmento "OBX", separados por el delimitador `|`.
     * @param request el objeto {@link PacienteRequestDTO} donde se almacenará la información procesada.
     * @return {@code true} si el segmento es válido y se procesó correctamente; {@code false} en caso contrario.
     */
    private boolean procesarOBX(String[] campos, PacienteRequestDTO request) {
        if (campos.length > 5) {
            ExamenDTO examen = ExamenDTO.builder()
                    .codigoExamen(Utilities.quitarPalabraFinal(campos[3], PATRON_L))
                    .resultadoExamen(campos[5].strip())
                    .build();
            request.add(examen);
            return true;
        } else {
            log.warn(OBX_INCOMPLETO, String.join(PATRON_BARRA, campos));
            return false;
        }
    }
}
