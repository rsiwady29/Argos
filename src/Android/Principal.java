
package Android;

import Semantics.Controls.*;
import Semantics.Information.Program;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {

    private String RutaJava = null;
    private String RutaAndroid = null;
    private String RutaProyecto = null;
    private ArrayList<String> activities = new ArrayList<String>();
    private Program program;
        
    private ApkGenerator genApk = null;
    ArrayList<Control> list = new ArrayList<Control>();
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("_apkGenerator/icon.png"));
        return retValue;
    }
   
    public Principal(Program program) {
        initComponents();
        this.program = program;
        txtIcon.setText("D:\\DropBox\\Argos\\src\\Android\\icon.png");
        String os = System.getProperty("os.name").toLowerCase();
        
        if( os.contains("win") )
        {
            genApk = new ApkGenerator(ApkGenerator.OperatingSystem.Windows);
        }
        else if( os.indexOf("nix") >= 0  || os.indexOf("nux") >= 0 || os.indexOf("mac")>=0   )
        {
            genApk = new ApkGenerator(ApkGenerator.OperatingSystem.UnixBased);
        }
        
        RutaJava = "C:\\Program Files\\Java\\jdk1.6.0_34";
        RutaAndroid = "C:\\Program Files (x86)\\Android\\android-sdk";
        RutaProyecto = "";
        
        genApk.JAVA_HOME = RutaJava;
        genApk.ANDROID_HOME = RutaAndroid;
        
        txtRutaAndroid.setText( RutaAndroid);
        txtRutaJDK.setText(RutaJava);
        txtAndroidJar.setText("C:\\Program Files (x86)\\Android\\android-sdk\\platforms\\android-10\\android.jar");
        
        txtAlias.setText("TestKey");
        txtStorepass.setText("1234567");
        txtKeypass.setText("1234567");
    }
    
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
    
    public ArrayList<String> transform(ArrayList<ListBoxItem> items)
    {
        ArrayList<String> array = new ArrayList<String>();
        
        for( ListBoxItem item : items )
        {
            array.add(item.getText());
        }
        
        return array;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtRutaJDK = new javax.swing.JTextField();
        txtRutaAndroid = new javax.swing.JTextField();
        txtRutaProyecto = new javax.swing.JTextField();
        btnCargarRutaJDK = new javax.swing.JButton();
        btnCargarRutaAndroid = new javax.swing.JButton();
        btnCargarRutaProyecto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombreProyecto = new javax.swing.JTextField();
        txtPaqueteProyecto = new javax.swing.JTextField();
        btnCrearKeystore = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtAlias = new javax.swing.JTextField();
        txtStorepass = new javax.swing.JTextField();
        txtKeypass = new javax.swing.JTextField();
        btnCrearR = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtAndroidJar = new javax.swing.JTextField();
        btnAndroidJar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnCrearProyecto = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtIcon = new javax.swing.JTextField();
        btnLoadIcon = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ruta del JDK");

        jLabel2.setText("Ruta de Android");

        jLabel3.setText("Ruta del Proyecto");

        btnCargarRutaJDK.setText("...");
        btnCargarRutaJDK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCargarRutaJDKMouseClicked(evt);
            }
        });

        btnCargarRutaAndroid.setText("...");
        btnCargarRutaAndroid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCargarRutaAndroidMouseClicked(evt);
            }
        });

        btnCargarRutaProyecto.setText("...");
        btnCargarRutaProyecto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCargarRutaProyectoMouseClicked(evt);
            }
        });

        jLabel4.setText("Nombre del Proyecto");

        jLabel5.setText("Paquete del Proyecto");

        btnCrearKeystore.setText("Crear Keystore");
        btnCrearKeystore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCrearKeystoreMouseClicked(evt);
            }
        });

        jLabel6.setText("Alias");

        jLabel7.setText("Storepass");

        jLabel8.setText("Keypass");

        btnCrearR.setText("Crear APK( Testing )");
        btnCrearR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCrearRMouseClicked(evt);
            }
        });

        jLabel11.setText("AndroidJar");

        btnAndroidJar.setText("...");
        btnAndroidJar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAndroidJarMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel10.setText("Generacion De Android");

        jLabel12.setText("Paso 4: Crear un KeyStore");

        jLabel13.setText("Paso 1:   Crear Estructura del Proyecto");

        btnCrearProyecto.setText("Crear Proyecto");
        btnCrearProyecto.setActionCommand("btnCrearProyecto");
        btnCrearProyecto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCrearProyectoMouseClicked(evt);
            }
        });

        jLabel9.setText("Icon");

        btnLoadIcon.setText("...");
        btnLoadIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoadIconMouseClicked(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Android/icon.png"))); // NOI18N

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
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
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnCrearProyecto)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCrearR, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtPaqueteProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtRutaProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnCargarRutaProyecto))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(btnLoadIcon)))))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtStorepass, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtKeypass, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnCrearKeystore, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnClose)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel11))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtRutaJDK)
                                    .addComponent(txtAndroidJar)
                                    .addComponent(txtRutaAndroid, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnCargarRutaJDK)
                                        .addComponent(btnCargarRutaAndroid))
                                    .addComponent(btnAndroidJar, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(27, 27, 27)))
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCargarRutaJDK)
                        .addGap(0, 0, 0)
                        .addComponent(btnCargarRutaAndroid))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtRutaJDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRutaAndroid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAndroidJar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(btnAndroidJar))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtRutaProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCargarRutaProyecto)
                            .addComponent(jLabel7)
                            .addComponent(txtStorepass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKeypass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtNombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLoadIcon)
                            .addComponent(jLabel6)
                            .addComponent(txtAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPaqueteProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrearProyecto)
                            .addComponent(btnCrearR)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCrearKeystore)
                        .addGap(18, 18, 18)
                        .addComponent(btnClose)))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarRutaJDKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCargarRutaJDKMouseClicked
        RutaJava = cargarFolder();
        txtRutaJDK.setText( RutaJava );
        genApk.JAVA_HOME = RutaJava;
    }//GEN-LAST:event_btnCargarRutaJDKMouseClicked

    private void btnCargarRutaAndroidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCargarRutaAndroidMouseClicked
        RutaAndroid = cargarFolder();
        txtRutaAndroid.setText( RutaAndroid );
        genApk.ANDROID_HOME = RutaAndroid;
    }//GEN-LAST:event_btnCargarRutaAndroidMouseClicked

    private void btnCargarRutaProyectoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCargarRutaProyectoMouseClicked
        RutaProyecto = cargarFolder();
        txtRutaProyecto.setText( RutaProyecto );
        genApk.DEV_HOME = RutaProyecto;
    }//GEN-LAST:event_btnCargarRutaProyectoMouseClicked

    private void btnCrearKeystoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearKeystoreMouseClicked
        
        String alias = txtAlias.getText();
        String storePass = txtStorepass.getText();
        String keypass = txtKeypass.getText();
        String dname = "\"CN=android, OU=android, O=android, L=android, S=Villanueva, C=Honduras\"";        
        
        if( genApk != null)
        {
            genApk.generateKeystore(RutaProyecto + genApk.folderSeparator + txtNombreProyecto.getText(),
                                    alias, storePass, keypass, dname);
        }
    }//GEN-LAST:event_btnCrearKeystoreMouseClicked

    private void btnCrearRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearRMouseClicked

        String androidJarPath = txtAndroidJar.getText();

        String projectHome = RutaProyecto+ApkGenerator.getFolderSeparator()+txtNombreProyecto.getText();

        JOptionPane.showMessageDialog(null, RutaProyecto+"\\"+txtNombreProyecto.getText());
        genApk.generarR(projectHome,androidJarPath);
        genApk.compilarCodigo(projectHome,androidJarPath,txtPaqueteProyecto.getText());

        genApk.crearDEX(projectHome);
        genApk.crearAPKsinFirma(txtNombreProyecto.getText(), projectHome, androidJarPath);
        genApk.firmarApk(txtNombreProyecto.getText(), projectHome, txtAlias.getText(), txtStorepass.getText(), txtKeypass.getText());
        genApk.optimizeApk(txtNombreProyecto.getText(), projectHome);       
    }//GEN-LAST:event_btnCrearRMouseClicked

    private void btnAndroidJarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAndroidJarMouseClicked
        txtAndroidJar.setText( cargarArchivo() );
    }//GEN-LAST:event_btnAndroidJarMouseClicked
    
    private void btnCrearProyectoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearProyectoMouseClicked
        /* Crear Estructura del Proyecto */
        if( txtNombreProyecto.getText().equals("") || txtPaqueteProyecto.getText().equals("") )
        {
            JOptionPane.showMessageDialog(null, "Both Project name and Package name are required");
            return;
        }

        genApk.createFolderStructure(txtRutaProyecto.getText(),
            txtNombreProyecto.getText(),
            txtPaqueteProyecto.getText());
        
       

        /* Crear Layout */
        String projectPath = RutaProyecto+ ApkGenerator.getFolderSeparator() +
                             txtNombreProyecto.getText() +
                              ApkGenerator.getFolderSeparator();

        ApkGenerator.copyFile(txtIcon.getText(), projectPath);
        
        CreateActivitiyFiles.CreateJavaFiles(projectPath, txtPaqueteProyecto.getText(), program);
        
        for(Window window: program.getWindowsList())
        {    
            LayoutGenerator layoutGenerator = new LayoutGenerator(projectPath, window.getName(),
                                                                txtPaqueteProyecto.getText());            
            activities.add(window.getName());

            ArrayList<Control> controls = window.getChildren();
            for(Control control : controls)
            {
                if( control instanceof Button)
                {
                    Button btn = (Button) control;
                    layoutGenerator.generateLayoutButton(btn.getName(), btn.getValue(), btn.getX(), 
                                                        btn.getY(),btn.getWidth(), btn.getHeight(), 
                                                        btn.isVisible(), btn.isEnabled());
                }
                else if( control instanceof Label)
                {
                    Label lbl = (Label) control;
                    layoutGenerator.generateLayoutLabel(lbl.getName(), lbl.getValue(), lbl.getX(), 
                                                        lbl.getY(), lbl.getWidth(), lbl.getHeight(), 
                                                        lbl.isVisible(), lbl.isEnabled());
                }
                else if( control instanceof CheckBox)
                {
                    CheckBox chk = (CheckBox) control;
                    layoutGenerator.generateLayoutCheckbox(chk.getName(), chk.getText(), chk.getX(), 
                                                        chk.getY(), chk.getWidth(), chk.getHeight(),
                                                        chk.isVisible(), chk.isEnabled(), chk.isChecked());
                }
                else if( control instanceof TextBox)
                {
                    TextBox txt = (TextBox) control;
                    layoutGenerator.generateLayoutTextBox(txt.getName(), txt.getValue(), txt.getX(), 
                                                        txt.getY(), txt.getWidth(), txt.getHeight(), 
                                                        txt.isVisible(), txt.isEnabled());
                }
                else if( control instanceof Picture)
                {
                    Picture pic = (Picture) control;
                    layoutGenerator.generateLayoutPicture(pic.getPath(), RutaProyecto+ApkGenerator.getFolderSeparator()+txtNombreProyecto.getText(),
                                                        pic.getName(),pic.getWidth(),pic.getHeight(),pic.getX(), pic.getY(), pic.isVisible(), pic.isEnabled());
                }
                else if( control instanceof ListBox)
                {
                    ListBox listbox = (ListBox) control;
                    layoutGenerator.generateListView(RutaProyecto+ApkGenerator.getFolderSeparator()+txtNombreProyecto.getText(),
                                                    listbox.getName(), listbox.getWidth(), listbox.getHeight(), listbox.getX(), listbox.getY(), 
                                                    listbox.isVisible(), listbox.isEnabled(),transform(listbox.getItems()));
                }
                else if( control instanceof ComboBox)
                {
                    ComboBox combobox = (ComboBox) control;
                    layoutGenerator.generateLayoutComboBox(RutaProyecto+ApkGenerator.getFolderSeparator()+txtNombreProyecto.getText(),
                                                            combobox.getName(), combobox.getWidth(), combobox.getHeight(), combobox.getX(),
                                                            combobox.getY(), combobox.isVisible(), combobox.isEnabled(),
                                                            transform(combobox.getItems()));
                }
                else if( control instanceof DatePicker)
                {
                    DatePicker datepicker = (DatePicker) control;
                    layoutGenerator.generateLayoutDatePicker(datepicker.getName(), datepicker.getWidth(), datepicker.getHeight(),
                                                            datepicker.getX(), datepicker.getY(), datepicker.isVisible(), datepicker.isEnabled());
                }
                else if( control instanceof RadioButton)
                {
                    RadioButton radioButton = (RadioButton) control;
                    layoutGenerator.generateLayoutRadioButton(radioButton.getName(), radioButton.getText(), radioButton.getX(), radioButton.getY(), 
                                                             radioButton.getWidth(), radioButton.getHeight(), radioButton.isVisible(), 
                                                             radioButton.isEnabled(), radioButton.isChecked());
                }
            }
            layoutGenerator.generateLayout();
        }
        
        /* Creacion del Manifest */
        ManifestGenerator mGen = new ManifestGenerator(txtPaqueteProyecto.getText(),"10");
        mGen.generateManifestApplication("MyApp", activities);
        mGen.generateManifestFile(RutaProyecto+ ApkGenerator.getFolderSeparator() +txtNombreProyecto.getText());
        
        /* Incluir Global.java*/
        CreateActivitiyFiles.CreateGlobalFile(projectPath, txtPaqueteProyecto.getText());
    }//GEN-LAST:event_btnCrearProyectoMouseClicked

    private void btnLoadIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadIconMouseClicked
        txtIcon.setText(cargarArchivo());
    }//GEN-LAST:event_btnLoadIconMouseClicked

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAndroidJar;
    private javax.swing.JButton btnCargarRutaAndroid;
    private javax.swing.JButton btnCargarRutaJDK;
    private javax.swing.JButton btnCargarRutaProyecto;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnCrearKeystore;
    private javax.swing.JButton btnCrearProyecto;
    private javax.swing.JButton btnCrearR;
    private javax.swing.JButton btnLoadIcon;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtAlias;
    private javax.swing.JTextField txtAndroidJar;
    private javax.swing.JTextField txtIcon;
    private javax.swing.JTextField txtKeypass;
    private javax.swing.JTextField txtNombreProyecto;
    private javax.swing.JTextField txtPaqueteProyecto;
    private javax.swing.JTextField txtRutaAndroid;
    private javax.swing.JTextField txtRutaJDK;
    private javax.swing.JTextField txtRutaProyecto;
    private javax.swing.JTextField txtStorepass;
    // End of variables declaration//GEN-END:variables
}
