
package main;

import Clases.Cancion;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class JdAgregarCanciones extends javax.swing.JDialog {

    private boolean continuar;
    private Cancion aDevolver;

    
    public JdAgregarCanciones(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Añadir canciones");
        continuar = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfNombre = new javax.swing.JTextField();
        jbContinuar = new javax.swing.JButton();
        jtfRuta = new javax.swing.JTextField();
        jtfDuracion = new javax.swing.JTextField();
        jbCancelar = new javax.swing.JButton();
        jbBuscar = new javax.swing.JButton();
        jlNombre = new javax.swing.JLabel();
        jlRuta = new javax.swing.JLabel();
        jlDuracion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtfNombre.setToolTipText("<html>Introduzca el nombre de la cancion</html>");
        jtfNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNombreActionPerformed(evt);
            }
        });

        jbContinuar.setText("Continuar");
        jbContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbContinuarActionPerformed(evt);
            }
        });

        jtfRuta.setToolTipText("Introduzca la ruta de la canción");
        jtfRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfRutaActionPerformed(evt);
            }
        });

        jtfDuracion.setToolTipText("Introduzca la duración de la canción");
        jtfDuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDuracionActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jbBuscar.setText("Buscar...");
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });

        jlNombre.setText("Nombre");

        jlRuta.setText("Ruta");

        jlDuracion.setText("Duración");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlRuta, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(jlDuracion, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jbContinuar)
                        .addGap(48, 48, 48)
                        .addComponent(jbCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jbBuscar)))
                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfRuta))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlDuracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNombreActionPerformed

    /**
     * En caso de pulsar el botón continuar del JDialog comprueba los campos y 
     * sus contenidos.
     * ----Errores----
     * ·En caso de estar vacío saldrá un error
     * ·En caso de que la duración tenga letras saldrá un error
     * 
     * 
     * @param evt 
     */
    private void jbContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbContinuarActionPerformed
        if(jtfNombre.getText().isBlank() || jtfRuta.getText().isBlank() || jtfDuracion.getText().isBlank()){
            JOptionPane.showMessageDialog(rootPane, "Uno de los campos está vacio, vuelva a intentarlo", "Error", JOptionPane.INFORMATION_MESSAGE);
        }else if(tieneLetras(jtfDuracion.getText())){
            JOptionPane.showMessageDialog(rootPane, "La duración no puede contener letras", "Error", JOptionPane.INFORMATION_MESSAGE);
        }else{
            continuar = true;
            aDevolver = new Cancion(jtfNombre.getText(), jtfRuta.getText(), Integer.parseInt(jtfDuracion.getText()));
            this.setVisible(false); 
        }
    }//GEN-LAST:event_jbContinuarActionPerformed

    private void jtfRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfRutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfRutaActionPerformed

    private void jtfDuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDuracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfDuracionActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbCancelarActionPerformed

    /**
     * Acción del botón de buscar. Abre un file chooser en el que poder elegir
     * la canción a añadir, una vez realizada la selección los datos de la canción
     * se meterán en los campos a falta de la duración.
     * @param evt 
     */
    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        JFileChooser dialogoAbrirFichero = new JFileChooser();
        
        int seleccion = dialogoAbrirFichero.showOpenDialog(null);
        
        if(seleccion == JFileChooser.APPROVE_OPTION){
            File archivo = dialogoAbrirFichero.getSelectedFile();
            jtfNombre.setText(archivo.getName());
            jtfRuta.setText(archivo.getAbsolutePath());
            if(archivo.getName().endsWith(".wav")){
                try{
                    AudioInputStream stream = AudioSystem.getAudioInputStream(archivo);
                    Clip clip = AudioSystem.getClip();
                    clip.open(stream);
                    long duracion =  Math.round(clip.getMicrosecondLength() * Math.pow(10, -6) / 60);
                    jtfDuracion.setText(""+duracion);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jbBuscarActionPerformed

    public boolean isContinuar() {
        return continuar;
    }

    public Cancion getaDevolver() {    
        return aDevolver;
    }
    
    /**
     * Función que comprueba si una string contiene letras. 
     * @param cadena -> String
     * @return Boolean 
     */
    public boolean tieneLetras(String cadena){
        char[] cadenaCaracteres = cadena.toCharArray();
        boolean tieneLetras = false;
        for(char c:cadenaCaracteres){
            if(!Character.isDigit(c)){
                tieneLetras = true;
            }
        }
        return tieneLetras;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbBuscar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbContinuar;
    private javax.swing.JLabel jlDuracion;
    private javax.swing.JLabel jlNombre;
    private javax.swing.JLabel jlRuta;
    private javax.swing.JTextField jtfDuracion;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfRuta;
    // End of variables declaration//GEN-END:variables
}
