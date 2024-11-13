/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gov.app.prueba.clteach.services.impl;

import com.gov.app.prueba.clteach.dto.ExamenDTO;
import com.gov.app.prueba.clteach.dto.PacienteDTO;
import com.gov.app.prueba.clteach.services.IProcesarContenidoService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProcesarContenidoService implements IProcesarContenidoService{

    @Override
    public ExamenDTO procesarContenido(List<String> lineas) {
        ExamenDTO examen = new ExamenDTO();
        PacienteDTO paciente = new PacienteDTO();

        for (String segmento : lineas) {
            // Limpiar y dividir el segmento
            String texto = segmento.trim().endsWith("\\")
                    ? segmento.substring(0, segmento.length() - 1)
                    : segmento;
            String[] campos = texto.split("\\|");

            // Procesar el segmento
            switch (campos[0]) {
                case "PID" -> paciente.setNombrePaciente(campos[5]);
                case "OBR" -> paciente.setNumeroOrden(campos[3]);
                case "OBX" -> {
                    examen.setCodigoExamen(campos[3]);
                    examen.setResultadoExamen(campos[5]);
                }
                default -> log.info("Segmento desconocido: {}", segmento);
            }
        }

        examen.setPaciente(paciente);
        return examen;
    }
    
}
