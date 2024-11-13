/**
 * DTO (Data Transfer Object) que representa un examen realizado a un paciente.
 * <p>
 * Esta clase se utiliza para transferir información relacionada con un examen, 
 * incluyendo el código del examen, el resultado y los datos del paciente asociado.
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
public class ExamenDTO {

    /**
     * Código único que identifica el examen.
     * Ejemplo: "A9".
     */
    private String codigoExamen;
    
    /**
     * Resultado del examen.
     * Ejemplo: "7.4" o "Negativo".
     */
    private String resultadoExamen;

    /**
     * Información del paciente asociado al examen.
     * Representada mediante el objeto {@link PacienteDTO}.
     */
    private PacienteDTO paciente;
}
