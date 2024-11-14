package com.gov.app.prueba.clteach.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.app.prueba.clteach.dto.ApiClientDTO;
import com.gov.app.prueba.clteach.dto.PacienteRequestDTO;
import com.gov.app.prueba.clteach.dto.RespuestaGeneralDTO;
import com.gov.app.prueba.clteach.services.ILeerArchivoService;
import static com.gov.app.prueba.clteach.utils.constants.Constants.ERROR_INESPERADO_PORCESAR;
import static com.gov.app.prueba.clteach.utils.constants.Constants.ERROR_LECTURA_ARCHIVO;
import static com.gov.app.prueba.clteach.utils.constants.Constants.ERROR_LEER_ARCHIVO_HL7;
import static com.gov.app.prueba.clteach.utils.constants.Constants.ERROR_PROCESAMIENTO_DATOS;
import static com.gov.app.prueba.clteach.utils.constants.Constants.PATRON_LINEAS;
import static com.gov.app.prueba.clteach.utils.constants.Constants.SALTO_TEXTO;
import static com.gov.app.prueba.clteach.utils.constants.Constants.URL_GUARDAR_PACIENTE;
import com.gov.app.prueba.clteach.utils.enums.HttpStatusEnum;
import com.gov.app.prueba.clteach.utils.helper.Utilities;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Servicio para procesar archivos HL7 y enviar datos al sistema.
 */
@Slf4j
public class LeerArchivoService implements ILeerArchivoService {

    private final ApiClientDTO apiClient;
    private final ObjectMapper objectMapper;
    private final ProcesarContenidoService procesarContenidoService;

    public LeerArchivoService() {
        this.apiClient = ApiClientDTO.getInstance();
        this.objectMapper = new ObjectMapper();
        this.procesarContenidoService = new ProcesarContenidoService();
    }

    /**
     * Lee y procesa un archivo HL7.
     *
     * @param archivo Archivo HL7 a procesar.
     * @return Respuesta general del procesamiento.
     */
    @Override
    public RespuestaGeneralDTO leerArchivoHl7(File archivo) {
        try {
            String contenido = leerArchivo(archivo);
            String[] lineas = contenido.split(PATRON_LINEAS);
            PacienteRequestDTO request = procesarContenidoService.procesarContenido(lineas);
            return enviarDatos(request);
        } catch (IOException e) {
            return manejarError(ERROR_LEER_ARCHIVO_HL7, e, ERROR_LECTURA_ARCHIVO);
        } catch (Exception e) {
            return manejarError(ERROR_INESPERADO_PORCESAR, e, ERROR_PROCESAMIENTO_DATOS);
        }
    }

    /**
     * Lee el contenido de un archivo HL7.
     *
     * @param archivo Archivo HL7.
     * @return Contenido del archivo como cadena.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    private String leerArchivo(File archivo) throws IOException {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea.replace(PATRON_LINEAS, SALTO_TEXTO));
            }
        }
        return contenido.toString();
    }

    /**
     * Envía los datos procesados a un sistema remoto mediante HTTP POST.
     *
     * @param request Datos del paciente procesados.
     * @return Respuesta del sistema remoto.
     * @throws Exception Si ocurre un error en la solicitud HTTP.
     */
    private RespuestaGeneralDTO enviarDatos(PacienteRequestDTO request) throws Exception {
        String jsonExamen = objectMapper.writeValueAsString(request);
        return apiClient.sendPostRequest(URL_GUARDAR_PACIENTE, jsonExamen);
    }

    /**
     * Maneja los errores durante el procesamiento del archivo o datos.
     *
     * @param mensajeLog Mensaje para el log.
     * @param e          Excepción ocurrida.
     * @param mensajeUI  Mensaje de error para el usuario.
     * @return Respuesta con información del error.
     */
    private RespuestaGeneralDTO manejarError(String mensajeLog, Exception e, String mensajeUI) {
        log.error(mensajeLog, e);
        return Utilities.construirError(mensajeUI, HttpStatusEnum.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}
