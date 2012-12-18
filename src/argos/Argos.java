/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package argos;

import Swing.RenderArgus;
import Android.Principal;
import Semantics.Information.Program;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Argos extends javax.swing.JFrame {

    private String cargarFolder()
    {
        String ruta = null;
        JFileChooser dialogo = new JFileChooser();
        dialogo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resultado = dialogo.showOpenDialog(this);        
        if( resultado == JFileChooser.APPROVE_OPTION)
        {
            ruta = dialogo.getSelectedFile().getAbsolutePath();
        }
        
        return ruta;
    }
    
    private String cargarArchivo()
    {
        String ruta = null;
        
        JFileChooser dialogo = new JFileChooser();
        dialogo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int resultado = dialogo.showOpenDialog(this);
        if( resultado == JFileChooser.APPROVE_OPTION)
        {
            ruta = dialogo.getSelectedFile().getAbsolutePath();
        }
        return ruta;
    }
    
    private Program getProgram(String file)
    {
        return ProgramGen.ProgramG.getProgram(file);
    }
    
    public Argos() {
        initComponents();
        txtXml.setText("C:\\Users\\Richard\\Desktop\\w.xml");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAndroid = new javax.swing.JButton();
        btnGwt = new javax.swing.JButton();
        btnSwing = new javax.swing.JButton();
        txtXml = new javax.swing.JTextField();
        btnOpenXml = new javax.swing.JButton();
        btnGuiBuilder = new javax.swing.JButton();
        btnSwing2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAndroid.setText("Android");
        btnAndroid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAndroidMouseClicked(evt);
            }
        });

        btnGwt.setText("GWT");
        btnGwt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGwtMouseClicked(evt);
            }
        });

        btnSwing.setText("Swing");
        btnSwing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSwingMouseClicked(evt);
            }
        });

        btnOpenXml.setText("Open XML");
        btnOpenXml.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpenXmlMouseClicked(evt);
            }
        });

        btnGuiBuilder.setText("GUI Builder");
        btnGuiBuilder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiBuilderActionPerformed(evt);
            }
        });

        btnSwing2.setText("Swing2");
        btnSwing2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSwing2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAndroid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGwt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSwing)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSwing2))
                    .addComponent(txtXml))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuiBuilder, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnOpenXml, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtXml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpenXml))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAndroid)
                    .addComponent(btnGwt)
                    .addComponent(btnSwing)
                    .addComponent(btnGuiBuilder)
                    .addComponent(btnSwing2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenXmlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpenXmlMouseClicked
        String archivo = cargarArchivo();
        txtXml.setText(archivo);
    }//GEN-LAST:event_btnOpenXmlMouseClicked

    private void btnGwtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGwtMouseClicked
        new GWT.GwtConfig(getProgram(txtXml.getText())).setVisible(true);
    }//GEN-LAST:event_btnGwtMouseClicked

    private void btnAndroidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAndroidMouseClicked
        if( txtXml.getText().equals(""))
        {
            return;
        }
        Principal p = new Principal( getProgram(txtXml.getText()));

        java.net.URL url = ClassLoader.getSystemResource("Android/icon.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        p.setIconImage(img);

        p.setVisible(true);
    }//GEN-LAST:event_btnAndroidMouseClicked

    private void btnSwingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSwingMouseClicked
        Program p = getProgram(txtXml.getText());
        new Swing.SwingConfig(p).setVisible(true);
    }//GEN-LAST:event_btnSwingMouseClicked

    private void btnGuiBuilderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiBuilderActionPerformed
        
        DnD guibuilder = new DnD();
        java.net.URL url = ClassLoader.getSystemResource("argos/logo.jpg");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        guibuilder.setIconImage(img);
        
        guibuilder.setVisible(true);
    }//GEN-LAST:event_btnGuiBuilderActionPerformed

    private void btnSwing2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSwing2MouseClicked
        new swing2.SwingConfig(getProgram(txtXml.getText())).setVisible(true);
    }//GEN-LAST:event_btnSwing2MouseClicked
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() 
            {
               Argos argos = new Argos();
               java.net.URL url = ClassLoader.getSystemResource("argos/logo.jpg");
               Toolkit kit = Toolkit.getDefaultToolkit();
               Image img = kit.createImage(url);
               argos.setIconImage(img); 
               
               argos.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAndroid;
    private javax.swing.JButton btnGuiBuilder;
    private javax.swing.JButton btnGwt;
    private javax.swing.JButton btnOpenXml;
    private javax.swing.JButton btnSwing;
    private javax.swing.JButton btnSwing2;
    private javax.swing.JTextField txtXml;
    // End of variables declaration//GEN-END:variables
}
