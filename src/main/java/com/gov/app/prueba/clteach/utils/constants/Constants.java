/**
 * Clase que define constantes utilizadas en toda la aplicación.
 * <p>
 * Esta clase contiene valores constantes relacionados con patrones, tipos de contenido,
 * mensajes de error y configuraciones generales que se utilizan en múltiples lugares
 * del sistema.
 * </p>
 */
package com.gov.app.prueba.clteach.utils.constants;

public class Constants {

    // HTTP Constants
    /** Método HTTP POST. */
    public static final String HTTP_POST = "POST";

    /** Nombre del encabezado HTTP para el tipo de contenido. */
    public static final String REQUEST_NAME = "Content-Type";

    /** Nombre del encabezado HTTP para la aceptación de contenido. */
    public static final String REQUEST_NAME_TWO = "Accept";

    /** Tipo de contenido para solicitudes HTTP en formato JSON con codificación UTF-8. */
    public static final String CONTENT_TYPE = "application/json; utf-8";

    /** Tipo de contenido para solicitudes HTTP en formato JSON. */
    public static final String CONTENT_TYPE_TWO = "application/json";

    /** Codificación de contenido UTF-8. */
    public static final String TYPE_CONTENT = "utf-8";

    /** Mensaje de error para solicitudes HTTP POST con código HTTP no exitoso. */
    public static final String ERROR_POST = "Error en la solicitud POST: Código HTTP ";

    // Dimensiones Generales
    /** Ancho predeterminado de componentes visuales. */
    public static final int WIDTH = 300;

    /** Alto predeterminado de componentes visuales. */
    public static final int HEIGHT = 250;

    // HL7 Segment Constants
    /** Identificador del segmento PID en HL7. */
    public static final String PID = "PID";

    /** Identificador del segmento OBR en HL7. */
    public static final String OBR = "OBR";

    /** Identificador del segmento OBX en HL7. */
    public static final String OBX = "OBX";

    // Patrones y Delimitadores
    /** Delimitador de campos en HL7. */
    public static final String PATRON_BARRA = "|";

    /** Patrón para identificar subcadenas con "L". */
    public static final String PATRON_L = "L";

    /** Patrón para identificar subcadenas con "P". */
    public static final String PATRON_P = "P";

    /** Patrón para separar campos en un segmento HL7. */
    public static final String PATRON_CAMPOS = "\\|";

    /** Patrón para separar líneas en un archivo HL7. */
    public static final String PATRON_LINEAS = "\\n";

    /** Carácter de salto de línea en texto. */
    public static final String SALTO_TEXTO = "\n";

    // Mensajes de Error
    /** Mensaje para segmentos PID incompletos. */
    public static final String PID_INCOMPLETO = "Segmento PID incompleto: {}";

    /** Mensaje para segmentos OBR incompletos. */
    public static final String OBR_INCOMPLETO = "Segmento OBR incompleto: {}";

    /** Mensaje para segmentos OBX incompletos. */
    public static final String OBX_INCOMPLETO = "Segmento OBX incompleto: {}";

    /** Mensaje de error cuando el formato HL7 es inválido. */
    public static final String HL7_NO_VALIDO = "El formato del HL7 es inválido o no contiene los segmentos requeridos.";

    /** Mensaje para segmentos desconocidos en HL7. */
    public static final String SEGMENTOS_DESCONOCIDOS = "Segmento desconocido: {}";

    /** Mensaje para segmentos vacíos o mal formados. */
    public static final String SEGMENTO_VACIO_MAL_FORMATO = "Segmento vacío o mal formado: {}";

    /** Mensaje de error al procesar el archivo HL7. */
    public static final String ERROR_PROCESAMIENTO_ARCHIVO = "Error en el procesamiento del archivo.";

    /** Mensaje de error al procesar los datos del archivo HL7. */
    public static final String ERROR_PROCESAMIENTO_DATOS = "Error en el procesamiento de los datos.";

    /** Mensaje de error al leer el archivo HL7. */
    public static final String ERROR_LEER_ARCHIVO_HL7 = "Error al leer el archivo HL7: {}";

    /** Mensaje de error en la lectura de un archivo. */
    public static final String ERROR_LECTURA_ARCHIVO = "Error en la lectura del archivo.";

    /** Mensaje de error inesperado durante el procesamiento. */
    public static final String ERROR_INESPERADO = "Error inesperado: {}";

    /** Mensaje de error cuando el archivo HL7 está vacío o es nulo. */
    public static final String HL7_VACIO_NULO = "El archivo HL7 está vacío o es nulo.";

    /** Mensaje de error inesperado al procesar datos. */
    public static final String ERROR_INESPERADO_PORCESAR = "Error inesperado al procesar datos.";

    // URL
    /** URL para guardar la información del paciente. */
    public static final String URL_GUARDAR_PACIENTE = "http://localhost:8001/api/v1/pacientes/guardar";
}
