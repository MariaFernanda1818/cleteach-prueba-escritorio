package com.gov.app.prueba.clteach.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.gov.app.prueba.clteach.utils.constants.Constants.CONTENT_TYPE;
import static com.gov.app.prueba.clteach.utils.constants.Constants.CONTENT_TYPE_TWO;
import static com.gov.app.prueba.clteach.utils.constants.Constants.ERROR_POST;
import static com.gov.app.prueba.clteach.utils.constants.Constants.HTTP_POST;
import static com.gov.app.prueba.clteach.utils.constants.Constants.REQUEST_NAME;
import static com.gov.app.prueba.clteach.utils.constants.Constants.REQUEST_NAME_TWO;
import static com.gov.app.prueba.clteach.utils.constants.Constants.TYPE_CONTENT;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Singleton para realizar solicitudes HTTP (GET y POST) y mapear respuestas JSON a objetos Java.
 * <p>
 * Esta clase utiliza {@link HttpURLConnection} para realizar solicitudes HTTP y {@link ObjectMapper}
 * para convertir respuestas JSON en objetos de tipo {@link RespuestaGeneralDTO}.
 * </p>
 */
@Data
@AllArgsConstructor
public class ApiClientDTO {

    // Instancia única del Singleton
    private static volatile ApiClientDTO instance;

    // ObjectMapper para convertir JSON a objetos Java
    private final ObjectMapper objectMapper;

    /**
     * Constructor privado para evitar la creación directa de instancias.
     * <p>
     * Inicializa el {@link ObjectMapper} necesario para el procesamiento de JSON.
     * </p>
     */
    private ApiClientDTO() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Obtiene la instancia única del Singleton {@code ApiClientDTO}.
     * <p>
     * Implementa un patrón de Singleton doblemente bloqueado para garantizar que solo exista una instancia.
     * </p>
     *
     * @return la instancia única de {@code ApiClientDTO}
     */
    public static ApiClientDTO getInstance() {
        if (instance == null) {
            synchronized (ApiClientDTO.class) {
                if (instance == null) {
                    instance = new ApiClientDTO();
                }
            }
        }
        return instance;
    }

    /**
     * Realiza una solicitud HTTP POST al endpoint especificado con un cuerpo JSON y convierte la
     * respuesta en un objeto de tipo {@link RespuestaGeneralDTO}.
     * <p>
     * La solicitud incluye los encabezados necesarios para indicar el formato de entrada y salida como JSON.
     * </p>
     *
     * @param endpoint la URL del endpoint al que se realizará la solicitud POST.
     * @param jsonInput el cuerpo de la solicitud en formato JSON.
     * @return un objeto {@link RespuestaGeneralDTO} convertido de la respuesta JSON.
     * @throws Exception si ocurre un error de conexión o procesamiento de la respuesta.
     */
    public RespuestaGeneralDTO sendPostRequest(String endpoint, String jsonInput) throws Exception {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(HTTP_POST);
        connection.setRequestProperty(REQUEST_NAME, CONTENT_TYPE);
        connection.setRequestProperty(REQUEST_NAME_TWO, CONTENT_TYPE_TWO);
        connection.setDoOutput(true);

        // Enviar el JSON en el cuerpo de la solicitud
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInput.getBytes(TYPE_CONTENT);
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Convertir JSON a RespuestaGeneralDTO usando Jackson
            return objectMapper.readValue(response.toString(), RespuestaGeneralDTO.class);
        } else {
            throw new RuntimeException(ERROR_POST + responseCode);
        }
    }
}
