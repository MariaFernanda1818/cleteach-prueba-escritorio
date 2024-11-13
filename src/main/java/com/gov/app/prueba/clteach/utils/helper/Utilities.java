/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gov.app.prueba.clteach.utils.helper;

import com.gov.app.prueba.clteach.dto.RespuestaGeneralDTO;
import com.gov.app.prueba.clteach.utils.enums.HttpStatusEnum;

public class Utilities {
    
    public static RespuestaGeneralDTO construirError(String mensaje, HttpStatusEnum status) {
        return RespuestaGeneralDTO.builder()
                .httpStatus(status)
                .message(mensaje)
                .build();
    }
    
}
