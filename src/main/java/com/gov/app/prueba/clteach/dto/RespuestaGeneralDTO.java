/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gov.app.prueba.clteach.dto;

import com.gov.app.prueba.clteach.utils.enums.HttpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespuestaGeneralDTO {

   private HttpStatusEnum httpStatus; 
    
   private String object;
   
   private String message;
    
}
