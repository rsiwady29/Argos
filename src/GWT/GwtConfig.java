/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GWT;

import Semantics.Information.Program;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author RichardSiwady
 */
public class GwtConfig extends javax.swing.JFrame {

    /**
     * Creates new form GwtConfig
     */
    
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
    
    Program p = null;
    public GwtConfig(Program p) {
        initComponents();
        this.p = p;
        System.out.println("jajaj");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPath = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPackage = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnOpenFilePath = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        btnClose = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Path:");

        jLabel2.setText("Project Name:");

        jLabel3.setText("Project Package:");

        btnCreate.setText("Create");
        btnCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCreateMouseClicked(evt);
            }
        });

        btnOpenFilePath.setText("...");
        btnOpenFilePath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpenFilePathMouseClicked(evt);
            }
        });

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        btnClose.setText("Close");
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GWT/gwt.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Generacion");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("GWT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnClose)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtPackage, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel1))
                                            .addGap(31, 31, 31)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                                .addComponent(txtPath))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnOpenFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnCreate)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(50, 50, 50))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpenFilePath))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPackage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCreate)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateMouseClicked
        if(txtPath.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Please enter the project path!");
            return;
        }
        if(txtName.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Please enter the project name!");
            return;
        }
        if(txtPackage.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Please enter the project package!");
            return;
        }       
        
        String javaPath = txtPath.getText()+"\\"+txtName.getText()+"\\src\\";
        String htmlPath = txtPath.getText()+"\\"+txtName.getText()+"\\war";
        String[] list = txtPackage.getText().split("\\.");
        for(int i=0; i<list.length-1; i++)
        {
            javaPath += list[i] + "\\";
        }
        javaPath += "client";
        
        String _package = "";
        for(int i=0; i<list.length-1; i++)
        {
            _package += list[i] + ".";
        }
        _package += "client";
        
        String command = "conf.bat && cmd /c webAppCreator -out "+txtPath.getText()+"\\"+txtName.getText()+" "+txtPackage.getText();
        executeCommand(command, javaPath, list[list.length-1], _package, htmlPath);        
    }//GEN-LAST:event_btnCreateMouseClicked

    private void btnOpenFilePathMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpenFilePathMouseClicked
        txtPath.setText(cargarFolder());
    }//GEN-LAST:event_btnOpenFilePathMouseClicked

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        dispose();
    }//GEN-LAST:event_btnCloseMouseClicked

        private void executeCommand(String command, final String... params)
    {
        try {
            //String command = "cmd /c C:/Users/usuario/Documents/NetBeansProjects/ProyectoCompi2/Jose/war/jose.html";
            final Process proc = Runtime.getRuntime().exec(command);
            
            StreamGobbler errorGobbler = new 
                StreamGobbler(proc.getErrorStream(), "ERROR", txtLog);            
            
            // any output?
            StreamGobbler outputGobbler = new 
                StreamGobbler(proc.getInputStream(), "OUTPUT", txtLog);
                
            // kick them off
            errorGobbler.start();
            outputGobbler.start();
                                    
            // any error???
            Thread t = new Thread(){
                @Override
                public void run() {
                    int exitVal;
                    try {
                        exitVal = proc.waitFor();
                        //txtLog.append("\n");
                        if(exitVal == 0)
                        {
                            if(params.length > 0)
                            {
                                try {
                                    Program tree = p;
                                    GeneradorGWT.Path = params[0];
                                    GeneradorGWT.Name = params[1];
                                    GeneradorGWT.Package = params[2];
                                    GeneradorGWT.HtmlPath = params[3];
                                    if(GeneradorGWT.generarCodigo(tree)==0) 
                                    {
                                        String _command = "conf.bat && cmd /c echo %cd% && cd /d \""+txtPath.getText()+"\\"+txtName.getText()+"\" && echo %cd% && ant build && \""+params[3]+"\\"+params[1]+".html\"";
                                        executeCommand(_command);
                                    }
                                    else
                                        JOptionPane.showMessageDialog(rootPane, "Something went wrong!");
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            else
                                JOptionPane.showMessageDialog(rootPane, "Everything went better than expected!");
                        }
                        else
                            JOptionPane.showMessageDialog(rootPane, "Something went wrong!");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    super.run();
                }
            };
            t.start();            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnOpenFilePath;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPackage;
    private javax.swing.JTextField txtPath;
    // End of variables declaration//GEN-END:variables
}
