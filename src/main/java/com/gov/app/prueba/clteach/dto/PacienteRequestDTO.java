/**
 * DTO (Data Transfer Object) que representa la información necesaria para procesar una solicitud
 * relacionada con un paciente y sus exámenes.
 * <p>
 * Esta clase encapsula los datos del paciente, como sus nombres, número de orden y una lista de exámenes
 * asociados. Se utiliza para transferir información entre las diferentes capas del sistema.
 * </p>
 */
package com.gov.app.prueba.clteach.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que encapsula los datos necesarios para procesar una solicitud de un paciente.
 * <p>
 * Proporciona información clave del paciente, incluyendo su nombre, número de orden y los exámenes asociados.
 * </p>
 */
@Data
@AllArgsConstructor
public class PacienteRequestDTO {

    /**
     * Constructor por defecto que inicializa la lista de exámenes como una lista vacía.
     */
    public PacienteRequestDTO() {
        this.examenes = new ArrayList<>();
    }

    /**
     * Nombres completos del paciente.
     * <p>
     * Representa el nombre completo del paciente registrado en la solicitud.
     * </p>
     * Ejemplo: "Juan Perez".
     */
    private String nombres;

    /**
     * Número de orden asociado al paciente.
     * <p>
     * Este número identifica de forma única la solicitud o el examen relacionado con el paciente.
     * </p>
     * Ejemplo: "12345".
     */
    private String numeroOrden;

    /**
     * Lista de exámenes asociados al paciente.
     * <p>
     * Contiene los detalles de los exámenes realizados al paciente, representados mediante objetos {@link ExamenDTO}.
     * </p>
     */
    private List<ExamenDTO> examenes;

    /**
     * Añade un examen a la lista de exámenes del paciente.
     *
     * @param examen el objeto {@link ExamenDTO} que representa el examen a añadir.
     */
    public void add(ExamenDTO examen) {
        examenes.add(examen);
    }
}
