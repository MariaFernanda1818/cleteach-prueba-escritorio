package com.gov.app.prueba.clteach.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.app.prueba.clteach.dto.ApiClientDTO;
import com.gov.app.prueba.clteach.dto.PacienteRequestDTO;
import com.gov.app.prueba.clteach.dto.RespuestaGeneralDTO;
import com.gov.app.prueba.clteach.services.ILeerArchivoService;
import static com.gov.app.prueba.clteach.utils.constants.Constants.ERROR_INESPERADO;
import static com.gov.app.prueba.clteach.utils.constants.Constants.ERROR_LEER_ARCHIVO_HL7;
import static com.gov.app.prueba.clteach.utils.constants.Constants.ERROR_PROCESAMIENTO_ARCHIVO;
import static com.gov.app.prueba.clteach.utils.constants.Constants.ERROR_PROCESAMIENTO_DATOS;
import static com.gov.app.prueba.clteach.utils.constants.Constants.URL_GUARDAR_PACIENTE;
import com.gov.app.prueba.clteach.utils.enums.HttpStatusEnum;
import com.gov.app.prueba.clteach.utils.helper.Utilities;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Servicio para procesar archivos HL7 y enviar datos al sistema.
 * <p>
 * Esta clase implementa {@link ILeerArchivoService} y proporciona la funcionalidad
 * para leer archivos en formato HL7, procesar su contenido y enviar los datos resultantes
 * a un sistema remoto a través de una solicitud HTTP.
 * </p>
 */
@Slf4j
public class LeerArchivoService implements ILeerArchivoService {

    private final ApiClientDTO apiClient;
    private final ObjectMapper objectMapper;
    private final ProcesarContenidoService procesarContenidoService;

    /**
     * Constructor que inicializa las dependencias necesarias.
     * <p>
     * Se utiliza el patrón Singleton para obtener la instancia de {@link ApiClientDTO},
     * y se inicializan un {@link ObjectMapper} y una instancia de {@link ProcesarContenidoService}.
     * </p>
     */
    public LeerArchivoService() {
        this.apiClient = ApiClientDTO.getInstance();
        this.objectMapper = new ObjectMapper();
        this.procesarContenidoService = new ProcesarContenidoService();
    }

    /**
     * Lee y procesa un archivo HL7.
     * <p>
     * Este método realiza las siguientes operaciones:
     * <ul>
     *   <li>Lee las líneas del archivo HL7.</li>
     *   <li>Procesa su contenido para extraer información del paciente mediante {@link ProcesarContenidoService}.</li>
     *   <li>Envía los datos procesados al sistema remoto.</li>
     * </ul>
     * </p>
     *
     * @param archivo el archivo HL7 a procesar.
     * @return un objeto {@link RespuestaGeneralDTO} que contiene el resultado del procesamiento.
     */
    @Override
    public RespuestaGeneralDTO leerArchivoHl7(File archivo) {
        try {
            // Leer líneas del archivo
            String[] lineas = Files.readAllLines(archivo.toPath()).get(0).split("n");
            // Procesar el contenido del archivo
            PacienteRequestDTO request = procesarContenidoService.procesarContenido(lineas);
            // Enviar los datos procesados al endpoint
            return enviarDatos(request);
        } catch (IOException e) {
            log.error(ERROR_LEER_ARCHIVO_HL7, e.getMessage());
            return Utilities.construirError(ERROR_PROCESAMIENTO_ARCHIVO, HttpStatusEnum.INTERNAL_SERVER_ERROR.getReasonPhrase());
        } catch (Exception e) {
            log.error(ERROR_INESPERADO, e.getMessage());
            return Utilities.construirError(ERROR_PROCESAMIENTO_DATOS, HttpStatusEnum.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    /**
     * Envía los datos procesados a un sistema remoto a través de una solicitud HTTP POST.
     *
     * @param request un objeto {@link PacienteRequestDTO} que contiene los datos procesados del archivo HL7.
     * @return un objeto {@link RespuestaGeneralDTO} que contiene la respuesta del sistema remoto.
     * @throws Exception si ocurre un error al realizar la solicitud HTTP o procesar la respuesta.
     */
    private RespuestaGeneralDTO enviarDatos(PacienteRequestDTO request) throws Exception {
        String jsonExamen = objectMapper.writeValueAsString(request);
        return apiClient.sendPostRequest(URL_GUARDAR_PACIENTE, jsonExamen);
    }
}
