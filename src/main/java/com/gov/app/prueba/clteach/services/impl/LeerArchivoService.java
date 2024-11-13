package com.gov.app.prueba.clteach.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gov.app.prueba.clteach.dto.ApiClientDTO;
import com.gov.app.prueba.clteach.dto.ExamenDTO;
import com.gov.app.prueba.clteach.dto.RespuestaGeneralDTO;
import com.gov.app.prueba.clteach.services.ILeerArchivoService;
import com.gov.app.prueba.clteach.utils.enums.HttpStatusEnum;
import com.gov.app.prueba.clteach.utils.helper.Utilities;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

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

    @Override
    public RespuestaGeneralDTO leerArchivoHl7(File archivo) {
        try {
            // Leer líneas del archivo
            List<String> lineas = Files.readAllLines(archivo.toPath());
            // Procesar el contenido del archivo
            ExamenDTO examen = procesarContenidoService.procesarContenido(lineas);
            // Enviar los datos procesados al endpoint
            return enviarDatos(examen);
        } catch (IOException e) {
            log.error("Error al leer el archivo HL7: {}", e.getMessage());
            return Utilities.construirError("Error en el procesamiento del archivo", HttpStatusEnum.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error inesperado: {}", e.getMessage());
            return Utilities.construirError("Error en el procesamiento de los datos",  HttpStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    private RespuestaGeneralDTO enviarDatos(ExamenDTO examen) throws Exception {
        String jsonExamen = objectMapper.writeValueAsString(examen);
        return apiClient.sendPostRequest("http://endpoint.ejemplo.com/examenes", jsonExamen);
    }


}
