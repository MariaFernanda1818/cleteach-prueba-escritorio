/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.gov.app.prueba.clteach;

import com.gov.app.prueba.clteach.services.impl.LeerArchivoService;
import com.gov.app.prueba.clteach.vistas.SubidaArchivosWindow;
import javax.swing.SwingUtilities;

public class Escritorio {

    public static void main(String[] args) {
        LeerArchivoService leerArchivosService = new LeerArchivoService();
        SwingUtilities.invokeLater(() ->{
            SubidaArchivosWindow subida = new SubidaArchivosWindow(leerArchivosService);
            subida.setVisible(true);
            subida.setSize(300, 250);
        });
           
    }
}
