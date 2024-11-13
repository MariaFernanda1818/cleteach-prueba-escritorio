/**
 * DTO (Data Transfer Object) que representa la información necesaria para procesar una solicitud
 * relacionada con un paciente y su examen.
 * <p>
 * Esta clase encapsula los datos del paciente, como sus nombres, número de orden,
 * código del examen y resultado del examen.
 * </p>
 */
package com.gov.app.prueba.clteach.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequestDTO {

    /**
     * Nombres completos del paciente.
     * Ejemplo: "Juan Perez".
     */
    private String nombres;

    /**
     * Número de orden asociado al paciente.
     * Este número identifica la solicitud o examen del paciente.
     * Ejemplo: "12345".
     */
    private String numeroOrden;

    /**
     * Código único que identifica el examen del paciente.
     * Ejemplo: "A9".
     */
    private String codigoExamen;

    /**
     * Resultado del examen realizado al paciente.
     * Ejemplo: "Positivo" o "7.5".
     */
    private String resultadoExamen;    
}
