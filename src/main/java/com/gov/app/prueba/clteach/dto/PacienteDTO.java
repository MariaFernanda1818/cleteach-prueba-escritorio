/**
 * DTO (Data Transfer Object) que representa la información básica de un paciente.
 * <p>
 * Esta clase se utiliza para encapsular datos clave del paciente, como su nombre y el número de orden
 * asociado a su solicitud o examen.
 * </p>
 */
package com.gov.app.prueba.clteach.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {

    /**
     * Nombre completo del paciente.
     * Ejemplo: "María García".
     */
    private String nombrePaciente;
    
    /**
     * Número de orden asociado al paciente.
     * Este número identifica la solicitud o examen relacionado con el paciente.
     * Ejemplo: "45678".
     */
    private String numeroOrden;
}
