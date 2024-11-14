package com.gov.app.prueba.clteach.utils.helper;

import com.gov.app.prueba.clteach.dto.RespuestaGeneralDTO;
import com.gov.app.prueba.clteach.utils.enums.HttpStatusEnum;

/**
 * Clase de utilidades que proporciona métodos estáticos para validaciones, procesamiento de cadenas
 * y construcción de objetos relacionados con la aplicación.
 */
public class Utilities {

    /**
     * Valida si el estado de respuesta coincide con el estado esperado de "CREATED".
     *
     * @param statusRespuesta el estado de respuesta recibido.
     * @return {@code true} si el estado es nulo o no coincide con "CREATED"; {@code false} en caso contrario.
     */
    public static boolean validacionRespuesta(String statusRespuesta) {
        return (statusRespuesta == null ? HttpStatusEnum.CREATED.getReasonPhrase() != null : 
                !statusRespuesta.equals(HttpStatusEnum.CREATED.getReasonPhrase().toUpperCase()));
    }

    /**
     * Elimina una palabra específica del final de una cadena de texto si coincide con un patrón.
     *
     * @param texto el texto original.
     * @param patronQuitar el patrón que se desea eliminar del final del texto.
     * @return el texto sin el patrón al final; si no coincide, retorna el texto original.
     */
    public static String quitarPalabraFinal(String texto, String patronQuitar) {
        return texto.endsWith(patronQuitar) ? texto.substring(0, texto.length() - patronQuitar.length()).stripTrailing() : texto;
    }

    /**
     * Limpia una línea de texto eliminando caracteres no deseados y espacios.
     * <p>
     * Este método realiza las siguientes acciones:
     * <ul>
     *     <li>Elimina el carácter de escape final si existe ("\\").</li>
     *     <li>Reemplaza todos los caracteres "^" por espacios.</li>
     * </ul>
     * </p>
     *
     * @param linea la línea de texto a limpiar.
     * @return la línea de texto limpia.
     */
    public static String limpiarLinea(String linea) {
        if (linea == null || linea.isEmpty()) {
            return linea;
        }

        linea = linea.trim().endsWith("\\") 
                ? linea.substring(0, linea.length() - 1) 
                : linea;

        // Expresión regular para eliminar ^L y ^ caracteres
        return linea.replaceAll("\\^", " "); 
    }

    /**
     * Construye un objeto {@link RespuestaGeneralDTO} con un mensaje de error y un estado.
     *
     * @param mensaje el mensaje descriptivo del error.
     * @param status el estado asociado al error.
     * @return un objeto {@link RespuestaGeneralDTO} con la información del error.
     */
    public static RespuestaGeneralDTO construirError(String mensaje, String status) {
        return RespuestaGeneralDTO.builder()
                .status(status)
                .message(mensaje)
                .build();
    }
}
