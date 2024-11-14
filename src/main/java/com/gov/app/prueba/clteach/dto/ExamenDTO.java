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

/**
 * Clase que encapsula los datos relacionados con un examen.
 * <p>
 * Proporciona detalles como el código del examen, su resultado y la información del paciente al que está asociado.
 * Se utiliza como parte del modelo de datos para transferir información entre diferentes capas del sistema.
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamenDTO {

    /**
     * Código único que identifica el examen.
     * <p>
     * Este código es un identificador único para cada tipo de examen realizado.
     * </p>
     * Ejemplo: "A9".
     */
    private String codigoExamen;

    /**
     * Resultado del examen.
     * <p>
     * Representa el valor o estado obtenido tras realizar el examen.
     * </p>
     * Ejemplo: "7.4", "Negativo".
     */
    private String resultadoExamen;

    /**
     * Información del paciente asociado al examen.
     * <p>
     * Este atributo contiene los datos del paciente al que pertenece el examen, representados mediante
     * un objeto {@link PacienteDTO}.
     * </p>
     */
    private PacienteDTO paciente;
}
