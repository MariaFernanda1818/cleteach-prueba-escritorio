/**
 * DTO (Data Transfer Object) que representa la información básica de un paciente.
 * <p>
 * Esta clase se utiliza para encapsular datos clave del paciente, como su nombre y el número de orden
 * asociado a su solicitud o examen. Es utilizada para transferir información entre las diferentes 
 * capas del sistema.
 * </p>
 */
package com.gov.app.prueba.clteach.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que encapsula la información básica de un paciente.
 * <p>
 * Proporciona detalles esenciales como el nombre completo del paciente y el número de orden asociado.
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {

    /**
     * Nombre completo del paciente.
     * <p>
     * Representa el nombre completo del paciente registrado en el sistema.
     * </p>
     * Ejemplo: "María García".
     */
    private String nombrePaciente;

    /**
     * Número de orden asociado al paciente.
     * <p>
     * Este número identifica de forma única la solicitud o el examen relacionado con el paciente.
     * </p>
     * Ejemplo: "45678".
     */
    private String numeroOrden;
}
