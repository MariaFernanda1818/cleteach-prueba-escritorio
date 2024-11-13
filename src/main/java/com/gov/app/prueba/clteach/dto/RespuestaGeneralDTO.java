/**
 * DTO (Data Transfer Object) que encapsula una respuesta genérica para las operaciones de la aplicación.
 * <p>
 * Esta clase se utiliza para estructurar las respuestas de las operaciones realizadas en el sistema, 
 * proporcionando información como el estado, el código de respuesta, los datos asociados y un mensaje descriptivo.
 * </p>
 */
package com.gov.app.prueba.clteach.dto;

import com.gov.app.prueba.clteach.utils.enums.HttpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespuestaGeneralDTO {

    /**
     * Estado textual de la operación.
     * Representa el estado de la respuesta, generalmente asociado a un valor como "SUCCESS" o "ERROR".
     * Ejemplo: "SUCCESS".
     */
    private String status;

    /**
     * Código numérico que representa el resultado de la operación.
     * Usualmente se utiliza para devolver códigos HTTP estándar o personalizados.
     * Ejemplo: 200, 400, 500.
     */
    private int codigo;

    /**
     * Datos asociados a la respuesta.
     * Este campo puede contener cualquier tipo de objeto relevante para la operación.
     * Ejemplo: Un objeto, una lista, o cualquier estructura de datos.
     */
    private Object data;

    /**
     * Mensaje descriptivo de la respuesta.
     * Proporciona información adicional sobre el resultado de la operación.
     * Ejemplo: "Operación realizada con éxito" o "Error en los datos enviados".
     */
    private String message;
}
