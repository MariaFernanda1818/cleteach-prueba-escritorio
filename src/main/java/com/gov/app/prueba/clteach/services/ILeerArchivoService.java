/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gov.app.prueba.clteach.services;

import com.gov.app.prueba.clteach.dto.RespuestaGeneralDTO;
import java.io.File;

public interface ILeerArchivoService {
    
    RespuestaGeneralDTO leerArchivoHl7(File archivo);
    
}
