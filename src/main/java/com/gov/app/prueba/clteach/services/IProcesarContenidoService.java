/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gov.app.prueba.clteach.services;

import com.gov.app.prueba.clteach.dto.ExamenDTO;
import java.util.List;

public interface IProcesarContenidoService {
    
    ExamenDTO procesarContenido(List<String> lineas);
    
}
