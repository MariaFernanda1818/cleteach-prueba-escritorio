package com.gov.app.prueba.clteach.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

/**
 * Enumeración que representa los códigos y mensajes de estado HTTP.
 * <p>
 * Esta enumeración incluye tanto las categorías principales de códigos de estado HTTP 
 * como respuestas informativas, exitosas, redirecciones, errores del cliente y errores del servidor.
 * Cada constante incluye un código numérico y una descripción legible.
 * </p>
 */
@Getter
public enum HttpStatusEnum {
    // Informational responses
    /**
     * Código 100: Continuar.
     */
    CONTINUE(100, "Continue"),
    /**
     * Código 101: Cambiando protocolos.
     */
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),
    
    // Successful responses
    /**
     * Código 200: OK.
     */
    OK(200, "OK"),
    /**
     * Código 201: Creado.
     */
    CREATED(201, "Created"),
    /**
     * Código 202: Aceptado.
     */
    ACCEPTED(202, "Accepted"),
    /**
     * Código 204: Sin contenido.
     */
    NO_CONTENT(204, "No Content"),
    
    // Redirection messages
    /**
     * Código 301: Movido permanentemente.
     */
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    /**
     * Código 302: Encontrado.
     */
    FOUND(302, "Found"),
    /**
     * Código 304: No modificado.
     */
    NOT_MODIFIED(304, "Not Modified"),
    
    // Client error responses
    /**
     * Código 400: Solicitud incorrecta.
     */
    BAD_REQUEST(400, "Bad Request"),
    /**
     * Código 401: No autorizado.
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * Código 403: Prohibido.
     */
    FORBIDDEN(403, "Forbidden"),
    /**
     * Código 404: No encontrado.
     */
    NOT_FOUND(404, "Not Found"),
    
    // Server error responses
    /**
     * Código 500: Error interno del servidor.
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    /**
     * Código 501: No implementado.
     */
    NOT_IMPLEMENTED(501, "Not Implemented"),
    /**
     * Código 502: Puerta de enlace incorrecta.
     */
    BAD_GATEWAY(502, "Bad Gateway"),
    /**
     * Código 503: Servicio no disponible.
     */
    SERVICE_UNAVAILABLE(503, "Service Unavailable");

    /**
     * Código numérico del estado HTTP.
     */
    private final int code;

    /**
     * Descripción legible del estado HTTP.
     */
    private final String reasonPhrase;

    /**
     * Constructor de la enumeración para asignar el código y la descripción.
     *
     * @param code el código numérico del estado HTTP.
     * @param reasonPhrase la descripción legible del estado HTTP.
     */
    HttpStatusEnum(int code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * Busca una constante de {@code HttpStatusEnum} basada en el código numérico.
     *
     * @param code el código numérico del estado HTTP.
     * @return la constante de {@code HttpStatusEnum} correspondiente al código.
     * @throws IllegalArgumentException si el código no es reconocido.
     */
    public static HttpStatusEnum valueOfCode(int code) {
        for (HttpStatusEnum status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de estado HTTP no reconocido: " + code);
    }

    /**
     * Busca una constante de {@code HttpStatusEnum} basada en la descripción.
     * <p>
     * Este método es compatible con Jackson para convertir cadenas en constantes de la enumeración.
     * </p>
     *
     * @param status la descripción del estado HTTP.
     * @return la constante de {@code HttpStatusEnum} correspondiente a la descripción.
     * @throws IllegalArgumentException si la descripción no es reconocida.
     */
    @JsonCreator
    public static HttpStatusEnum fromString(String status) {
        for (HttpStatusEnum httpStatusEnum : HttpStatusEnum.values()) {
            if (httpStatusEnum.getReasonPhrase().equalsIgnoreCase(status)) {
                return httpStatusEnum;
            }
        }
        throw new IllegalArgumentException("No enum constant for status: " + status);
    }
}
