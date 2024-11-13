package com.gov.app.prueba.clteach.vistas;

import com.gov.app.prueba.clteach.dto.RespuestaGeneralDTO;
import com.gov.app.prueba.clteach.services.ILeerArchivoService;
import com.gov.app.prueba.clteach.utils.helper.Utilities;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * Ventana gráfica para la subida y envío de archivos HL7.
 * <p>
 * Esta clase permite a los usuarios seleccionar un archivo desde su sistema,
 * mostrar el nombre del archivo seleccionado y enviarlo para su procesamiento.
 * Utiliza {@link ILeerArchivoService} para procesar los archivos seleccionados.
 * </p>
 */
public class SubidaArchivosWindow extends javax.swing.JFrame {
 /**
     * Servicio para leer y procesar archivos HL7.
     */
    private final ILeerArchivoService iLeerArchivoService;

    /**
     * Archivo seleccionado por el usuario.
     */
    private File archivo;

    /**
     * Constructor de la ventana de subida de archivos.
     *
     * @param iLeerArchivoService el servicio encargado de leer y procesar el archivo HL7.
     */
    public SubidaArchivosWindow(ILeerArchivoService iLeerArchivoService) {
        this.iLeerArchivoService = iLeerArchivoService;
        setTitle("Subida de Archivos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    /**
     * Inicializa los componentes de la interfaz gráfica.
     * <p>
     * Este método configura el diseño, botones y etiquetas de la ventana.
     * Es generado automáticamente por el editor de formularios y no debe ser modificado manualmente.
     * </p>
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        seleccionarArchivoButton = new javax.swing.JButton();
        enviarButton = new javax.swing.JButton();
        nombreArchivoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Subida de archivos");
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 20, 0, 20, 0};
        layout.rowHeights = new int[] {0, 20, 0, 20, 0};
        getContentPane().setLayout(layout);

        seleccionarArchivoButton.setText("Seleccionar Archivo");
        seleccionarArchivoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarArchivoButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(seleccionarArchivoButton, gridBagConstraints);

        enviarButton.setText("Enviar");
        enviarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(enviarButton, gridBagConstraints);

        nombreArchivoLabel.setText("Archivo seleccionado: Ninguno");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(nombreArchivoLabel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Acción asociada al botón "Enviar".
     * <p>
     * Este método valida que un archivo haya sido seleccionado y confirma con el usuario
     * si desea enviarlo. En caso de éxito, utiliza {@link ILeerArchivoService} para procesar el archivo.
     * </p>
     *
     * @param evt el evento de acción asociado al botón.
     */
    private void enviarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarButtonActionPerformed
        if (archivo == null) {
            JOptionPane.showMessageDialog(this,
                "Por favor, selecciona un archivo antes de enviar.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Deseas enviar el archivo seleccionado?",
            "Confirmar Envío",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                RespuestaGeneralDTO respuesta = iLeerArchivoService.leerArchivoHl7(archivo);
                JOptionPane.showMessageDialog(this,
                    respuesta.getMessage(),
                    (Utilities.validacionRespuesta(respuesta.getStatus()) ? "Error" : "Éxito"),
                    (Utilities.validacionRespuesta(respuesta.getStatus()) ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Ocurrió un error al enviar el archivo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_enviarButtonActionPerformed
   /**
     * Acción asociada al botón "Seleccionar Archivo".
     * <p>
     * Este método abre un diálogo para que el usuario seleccione un archivo desde su sistema.
     * Valida que el archivo seleccionado sea un archivo de texto (.txt) y actualiza la etiqueta
     * con el nombre del archivo seleccionado.
     * </p>
     *
     * @param evt el evento de acción asociado al botón.
     */
    private void seleccionarArchivoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarArchivoButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Archivo");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            archivo = fileChooser.getSelectedFile();
            if (!archivo.getName().endsWith(".txt")) {
                JOptionPane.showMessageDialog(this,
                    "El archivo seleccionado no es válido. Solo se aceptan archivos .txt.",
                    "Error de Archivo",
                    JOptionPane.ERROR_MESSAGE);
                archivo = null; // Reiniciar selección si no es válido
                return;
            }
            nombreArchivoLabel.setText("Archivo seleccionado: " + archivo.getName());
        } else {
            JOptionPane.showMessageDialog(this,
                "No se seleccionó ningún archivo.",
                "Sin Archivo",
                JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_seleccionarArchivoButtonActionPerformed
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton enviarButton;
    private javax.swing.JLabel nombreArchivoLabel;
    private javax.swing.JButton seleccionarArchivoButton;
    // End of variables declaration//GEN-END:variables
}
