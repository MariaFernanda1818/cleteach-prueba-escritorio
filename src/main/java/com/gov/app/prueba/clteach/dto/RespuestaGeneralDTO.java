package com.gov.app.prueba.clteach.dto;

import com.gov.app.prueba.clteach.utils.enums.HttpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una respuesta genérica del sistema.
 * <p>
 * Proporciona un formato estándar para enviar respuestas en las operaciones de la aplicación, 
 * incluyendo detalles del estado, código, datos y un mensaje explicativo.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespuestaGeneralDTO {

    /**
     * Estado textual de la operación.
     * <p>
     * Representa el estado de la respuesta, generalmente asociado a un valor como "SUCCESS" o "ERROR".
     * </p>
     * Ejemplo: "SUCCESS".
     */
    private String status;

    /**
     * Código numérico que representa el resultado de la operación.
     * <p>
     * Usualmente se utiliza para devolver códigos HTTP estándar o personalizados.
     * </p>
     * Ejemplo: 200, 400, 500.
     */
    private int codigo;

    /**
     * Datos asociados a la respuesta.
     * <p>
     * Este campo puede contener cualquier tipo de objeto relevante para la operación.
     * </p>
     * Ejemplo: Un objeto, una lista o cualquier estructura de datos.
     */
    private Object data;

    /**
     * Mensaje descriptivo de la respuesta.
     * <p>
     * Proporciona información adicional sobre el resultado de la operación.
     * </p>
     * Ejemplo: "Operación realizada con éxito" o "Error en los datos enviados".
     */
    private String message;
}
