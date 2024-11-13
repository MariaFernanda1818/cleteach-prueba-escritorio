package com.gov.app.prueba.clteach.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Singleton para realizar solicitudes HTTP (GET y POST) y mapear respuestas JSON a objetos Java.
 */
@Data
@AllArgsConstructor
public class ApiClientDTO {

    // Instancia única del Singleton
    private static volatile ApiClientDTO instance;

    // ObjectMapper para convertir JSON a objetos Java
    private final ObjectMapper objectMapper;

    // Constructor privado para evitar la creación directa
    private ApiClientDTO() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Método para obtener la instancia única del Singleton.
     *
     * @return la instancia única de ApiClientDTO
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
     * Realiza una solicitud GET al endpoint especificado y convierte la respuesta a un objeto RespuestaGeneralDTO.
     *
     * @param endpoint URL del endpoint
     * @return RespuestaGeneralDTO convertido de la respuesta JSON
     * @throws Exception en caso de error de conexión o procesamiento
     */
    public RespuestaGeneralDTO sendGetRequest(String endpoint) throws Exception {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
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
            throw new RuntimeException("Error en la solicitud GET: Código HTTP " + responseCode);
        }
    }

    /**
     * Realiza una solicitud POST al endpoint especificado con un cuerpo JSON y convierte la respuesta a un objeto RespuestaGeneralDTO.
     *
     * @param endpoint URL del endpoint
     * @param jsonInput Cuerpo JSON de la solicitud
     * @return RespuestaGeneralDTO convertido de la respuesta JSON
     * @throws Exception en caso de error de conexión o procesamiento
     */
    public RespuestaGeneralDTO sendPostRequest(String endpoint, String jsonInput) throws Exception {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        // Enviar el JSON en el cuerpo de la solicitud
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
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
            throw new RuntimeException("Error en la solicitud POST: Código HTTP " + responseCode);
        }
    }
}
