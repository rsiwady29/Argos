/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package argos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author richardsiwady
 */
public class EventCode extends javax.swing.JFrame {

    /**
     * Creates new form EventCode
     */
    String componentName = null;
    String eventName = null;
    DnD frame = null;
    
    public EventCode(DnD frame, String componentName, String eventName) {
        initComponents();
        txtCode.setTabSize(2);
        this.componentName = componentName;
        label.setText("Event Code for: " + componentName);
        btn.setText("Save Event Code");
        this.frame = frame;
        this.eventName = eventName;
        
        ArrayList<Event> events = frame.getComponentEvents(componentName);
        for(Event event : events)
        {
            if( event.componentName.equals(componentName) && event.eventName.equals(eventName))
            {
                try
                {
                    File file = new File(event.fileName);
                    FileReader fl = new FileReader(file);
                    BufferedReader reader = new BufferedReader(fl);
                    String code = "";
                    String line= "";
                    while( (line=reader.readLine())!=null )
                    {
                        code += line + "\n";
                    }
                    txtCode.setText(code);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    private File getSaveFileName()
    {
        File file = null;
        JFileChooser fchooser = new  JFileChooser();
        fchooser.showSaveDialog(rootPane);        
        file = fchooser.getSelectedFile();        
        return file;
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtCode = new javax.swing.JTextArea();
        label = new javax.swing.JLabel();
        btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtCode.setColumns(20);
        txtCode.setRows(5);
        txtCode.setText("begin\n\nend");
        jScrollPane1.setViewportView(txtCode);

        label.setText("jLabel1");

        btn.setText("Save Event");
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(175, 175, 175)
                .add(label)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                    .add(btn, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(label)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 264, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btn)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
        try
        {            
            File file = getSaveFileName();
            String code = txtCode.getText();
   
            FileOutputStream outputStream = new FileOutputStream(file);            
            outputStream.write(code.getBytes());
            outputStream.close();
            
            frame.addEventCode(eventName, componentName, file.getAbsolutePath().replace("\\", "/"));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        this.dispose();
    }//GEN-LAST:event_btnActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JTextArea txtCode;
    // End of variables declaration//GEN-END:variables
}
