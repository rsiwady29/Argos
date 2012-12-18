package argos;


import Android.Principal;
import Semantics.Information.Program;
import Swing.RenderArgus;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author RichardSiwady
 */
public class DnD extends javax.swing.JFrame {

    private int n_window=0;
    private int n_button = 0;
    private int n_label =0;
    private int n_checkbox=0;
    private int n_radiobutton=0;
    private int n_textbox=0;
    private int n_combobox=0;
    private int n_listbox=0;
    private int n_picture=0;
    private String xmlFilename = null;
    private String listSelectedValue = "";
    
    /*::Events::*/
    
    private ArrayList<Event> eventTable = new ArrayList<Event>();
    
    private Tool tools[] =
    {
        new Tool("Button", "src/argos/button.png"),
        new Tool("Label", "src/argos/label.png"),
        new Tool("TextBox","src/argos/textfield.png"),
        new Tool("CheckBox","src/argos/checkbox.png"),
        new Tool("RadioButton","src/argos/radiobutton.png"),
        new Tool("Picture","src/argos/picture.png"),
        new Tool("ComboBox","src/argos/combobox.png"),
        new Tool("ListBox","src/argos/listbox.png")
    };
    
    DocumentBuilderFactory dbFactory = null;
    DocumentBuilder docBuilder = null;
    Document document = null;
    
    // <editor-fold defaultstate="collapsed" desc="Auxiliar Functions">
    private String getOpenFileName()
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
    
    public ArrayList<Event> getComponentEvents(String componentName)
    {
        ArrayList<Event> result = new ArrayList<Event>();
        for( Event event : eventTable)
        {
            if( event.componentName.equals(componentName))
            {
                result.add(event);
            }
        }
        return result;
    }
    
    private void eliminateWindow()
    {
        if(tabPaneWindows.getComponentCount()>0)
        {        
            tabPaneWindows.remove(tabPaneWindows.getSelectedComponent());
        }
    }
    
    private String getCleanPath(String path)
    {
        String p = "";
        String values[] = path.split("\\\\");
        for(int i=0; i<values.length; i++)
        {
            if( i != (values.length-1))
            {
                p += values[i] + "/";
            }
            else
            {
                p += values[i];
            }
        }
        return p;
    }
    
    private String loadImageFile()
    {
        String ruta = null;
        
        JFileChooser dialogo = new JFileChooser();
        dialogo.setDialogTitle("Choose an Image");
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG,PNG", "png","jpg");
        dialogo.setFileFilter(filter);
        dialogo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int resultado = dialogo.showOpenDialog(this);
        
        if( resultado == JFileChooser.APPROVE_OPTION)
        {
            ruta = dialogo.getSelectedFile().getAbsolutePath();
        }
        
        return ruta;
    }
    
    private File getSaveFileName()
    {
        File file = null;
        JFileChooser fchooser = new  JFileChooser();
        fchooser.showSaveDialog(rootPane);        
        file = fchooser.getSelectedFile();        
        return file;
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
    
    private String loadXmlFile()
    {
        String ruta = null;
        
        JFileChooser dialogo = new JFileChooser();
        dialogo.setDialogTitle("Choose an Xml file");
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
        dialogo.setFileFilter(filter);
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

    private void loadXML(java.awt.event.ActionEvent evt)
    {
        String file = loadXmlFile();
        xmlFilename = file;
        
    }
    
    private void android()
    {
        if( xmlFilename == null)
        {
            JOptionPane.showMessageDialog(rootPane, "Xml File not loaded");
        }
        else
        {
            Principal p = new Principal( getProgram(xmlFilename));

            java.net.URL url = ClassLoader.getSystemResource("Android/icon.png");
            Toolkit kit = Toolkit.getDefaultToolkit();
            Image img = kit.createImage(url);
            p.setIconImage(img);

            p.setVisible(true);
        }

    }
    
    private void gwt()
    {
        if( xmlFilename == null)
        {
            JOptionPane.showMessageDialog(rootPane, "Xml File not loaded");
        }
        else
        {
                new GWT.GwtConfig(getProgram(xmlFilename)).setVisible(true);
        }
    }
    
    private void swing()
    {
        if( xmlFilename == null)
        {
            JOptionPane.showMessageDialog(rootPane, "Xml File not loaded");
        }
        else
        {
            Program p = getProgram(xmlFilename);
            try {
                Swing.RenderArgus rd = new RenderArgus(p.getWindowsList(), p.getWhichWindow());
                rd.compile("no importa");
                rd.ejecutar();
                rd.generaraOtros(p.getWindowsList(),p.getWhichWindow());
                } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void swing2()
    {
        if( xmlFilename == null)
        {
            JOptionPane.showMessageDialog(rootPane, "Xml File not loaded");
        }
        else
        {
            Program p = getProgram(xmlFilename);
            try 
            {
                new swing2.SwingConfig(p).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // </editor-fold>
    
    public DnD() {
        initComponents();
        
        setSize(1250,730);
        DefaultListModel listm = new DefaultListModel();
        
        for( Tool tool : tools){
            listm.addElement(tool);
        }
        listConttrols.setModel(listm);
        listConttrols.setFixedCellHeight(68);

        listConttrols.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        listConttrols.setCellRenderer(new ToolCellRenderer());
        listConttrols.setOpaque(false);
        
        listConttrols.revalidate();
        listConttrols.repaint();
        listConttrols.addMouseListener(dragndropAdapter);
        tabPaneWindows.setVisible(false);
        tabPaneWindows.setSize(850,550);
        
        btnAddWindowActionPerformed(null);
        Component component  = getComponentAt(tabPaneWindows.getX(), tabPaneWindows.getY());
        
        // ]component List
        DefaultListModel model = new DefaultListModel();
        componentList.setModel(model);
     }
    
    // <editor-fold defaultstate="collapsed" desc="DnD">
    MouseAdapter dragndropAdapter = new MouseAdapter() 
    {

        @Override
        public void mousePressed(MouseEvent e) {
            ((Component)e.getSource()).setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            listSelectedValue = listConttrols.getSelectedValue().toString();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            ((Component)e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            int eventX = e.getX();
            int eventY = e.getY();
            int tabX   = tabPaneWindows.getX();
            int tabY   = tabPaneWindows.getY();
            int tabw   = tabPaneWindows.getWidth();
            int tabh   = tabPaneWindows.getHeight();
//            System.out.println("Ex: " + eventX + ",Ey: "+eventY + "\nTabX:" +tabX + ",TabY: "+tabY);
            if( eventX >= tabX && eventY >= tabY &&
                eventX <= (tabX+tabw) && eventY <= (tabY+tabh)    )
            {
                if( listSelectedValue != null )
                {
                    e.setSource(tabPaneWindows.getComponentAt(tabPaneWindows.getSelectedIndex()));
                    AddMouseEvent(e);
                }
            }
        }
    };// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="GenerateXml">    
    private void generateXmlFile(File file)
    {
        if( file == null)
        {
            JOptionPane.showConfirmDialog(rootPane, "A file must be selected");
            return;
        }
        try
        {
            String xml="";
            for(int i=0; i<tabPaneWindows.getTabCount(); i++)
            {
                dbFactory = DocumentBuilderFactory.newInstance();
                docBuilder = dbFactory.newDocumentBuilder();
                document = docBuilder.newDocument();

                JPanel panel = (JPanel) tabPaneWindows.getComponent(i);
                Element root;
                if( i==0)
                {
                    root = document.createElement("MAINWINDOW");
                }
                else
                {
                    root =  document.createElement("WINDOW");
                }                             
                /*Window Attributes*/
                root.setAttribute("ENABLED","true");
                root.setAttribute("VISIBLE", "true");
                root.setAttribute("X", "0");
                root.setAttribute("Y", "0");
                root.setAttribute("HEIGHT", ""+panel.getHeight());
                root.setAttribute("WIDTH", ""+panel.getWidth());

                root.setAttribute("NAME", panel.getName());
                root.setAttribute("TITLE", panel.getName());
                
                for(int j=0; j<panel.getComponentCount(); j++)
                {
                    Component component = panel.getComponent(j);
                    ArrayList<Event> events = getComponentEvents(component.getName());
                    if( !component.isVisible() ) 
                    {
                        continue; 
                    }
                    
                    if( component instanceof JButton )
                    {
                        Element button = document.createElement("BUTTON");
                        button.setAttribute("ENABLED","true");
                        button.setAttribute("VISIBLE", "true");
                        button.setAttribute("X", component.getX()+"");
                        button.setAttribute("Y", component.getY()+"");
                        button.setAttribute("NAME", component.getName());
                        button.setAttribute("WIDTH", component.getWidth()+"");
                        button.setAttribute("HEIGHT", component.getHeight()+"");                        
                        button.setAttribute("VALUE", ((JButton)component).getText());
                        
                        for( Event event : events )
                        {
                            button.setAttribute(event.eventName, event.fileName);
                        }                        
                        root.appendChild(button);
                    }
                    else if( component instanceof JLabel )
                    {
                        if( ((JLabel)component).getIcon() == null )
                        {
                            // Es Label :D
                            Element label = document.createElement("LABEL");
                            label.setAttribute("ENABLED","true");
                            label.setAttribute("VISIBLE", "true");
                            label.setAttribute("X", component.getX()+"");
                            label.setAttribute("Y", component.getY()+""); 
                            label.setAttribute("NAME", component.getName());
                            label.setAttribute("WIDTH", component.getWidth()+"");
                            label.setAttribute("HEIGHT", component.getHeight()+"");
                            label.setAttribute("VALUE", ((JLabel)component).getText());
                            root.appendChild(label);
                        }
                        else
                        {
                            // Es Picture :D
                            Element picture = document.createElement("IMAGEN");
                            picture.setAttribute("ENABLED","true");
                            picture.setAttribute("VISIBLE", "true");
                            picture.setAttribute("X", component.getX()+"");
                            picture.setAttribute("Y", component.getY()+""); 
                            picture.setAttribute("WIDTH", component.getWidth()+"");
                            picture.setAttribute("HEIGHT", component.getHeight()+"");
                            //TEST
                            picture.setAttribute("NAME", component.getName().split(",")[0]);
                            String path = component.getName().split(",")[1];
                            picture.setAttribute("PATH",getCleanPath(path) );

                            for( Event event : events )
                            {
                                picture.setAttribute(event.eventName, event.fileName);
                            }                            
                            root.appendChild(picture);
                        }
                    }
                    else if(component instanceof JCheckBox )
                    {
                        Element checkbox = document.createElement("CHECKBOX");
                        checkbox.setAttribute("ENABLED","true");
                        checkbox.setAttribute("VISIBLE", "true");
                        checkbox.setAttribute("X", component.getX()+"");
                        checkbox.setAttribute("Y", component.getY()+"");
                        checkbox.setAttribute("NAME", component.getName());
                        checkbox.setAttribute("WIDTH", component.getWidth()+"");
                        checkbox.setAttribute("HEIGHT", component.getHeight()+"");
                        checkbox.setAttribute("TEXT", ((JCheckBox)component).getText());
                        for( Event event : events )
                        {
                            checkbox.setAttribute(event.eventName, event.fileName);
                        }                        
                        root.appendChild(checkbox);
                    }
                    else if( component instanceof JRadioButton )
                    {
                        Element radiobutton = document.createElement("RADIOBUTTON");
                        radiobutton.setAttribute("ENABLED","true");
                        radiobutton.setAttribute("VISIBLE", "true");
                        radiobutton.setAttribute("X", component.getX()+"");
                        radiobutton.setAttribute("Y", component.getY()+"");
                        radiobutton.setAttribute("NAME", component.getName());
                        radiobutton.setAttribute("WIDTH", component.getWidth()+"");
                        radiobutton.setAttribute("HEIGHT", component.getHeight()+"");
                        radiobutton.setAttribute("TEXT", ((JRadioButton)component).getText());
                        for( Event event : events )
                        {
                            radiobutton.setAttribute(event.eventName, event.fileName);
                        }                        
                        root.appendChild(radiobutton);
                    }
                    else if( component instanceof  JTextField )
                    {
                        Element textfield = document.createElement("TEXTBOX");
                        textfield.setAttribute("ENABLED","true");
                        textfield.setAttribute("VISIBLE", "true");
                        textfield.setAttribute("X", component.getX()+"");
                        textfield.setAttribute("Y", component.getY()+"");
                        textfield.setAttribute("NAME", component.getName());
                        textfield.setAttribute("WIDTH", component.getWidth()+"");
                        textfield.setAttribute("HEIGHT", component.getHeight()+"");
                        textfield.setAttribute("VALUE", ((JTextField)component).getText() );                        
                        for( Event event : events )
                        {
                            textfield.setAttribute(event.eventName, event.fileName);
                        }                        
                        root.appendChild(textfield);
                    }
                    else if( component instanceof JComboBox)
                    {
                       Element combobox = document.createElement("COMBOBOX");
                       combobox.setAttribute("ENABLED","true");
                       combobox.setAttribute("VISIBLE", "true");
                       combobox.setAttribute("X", component.getX()+"");
                       combobox.setAttribute("Y", component.getY()+"");
                       combobox.setAttribute("NAME", component.getName());
                       combobox.setAttribute("WIDTH", component.getWidth()+"");
                       combobox.setAttribute("HEIGHT", component.getHeight()+"");
                       
                       for(int index = 0; index < ((JComboBox)component).getItemCount(); index++)
                       {
                           Element t = document.createElement("ITEM");
                           t.setAttribute("TEXT", ((JComboBox)component).getItemAt(index).toString());
                           t.setAttribute("VALUE", ((JComboBox)component).getItemAt(index).toString());                           
                           combobox.appendChild(t);
                       }
                        for( Event event : events )
                        {
                            combobox.setAttribute(event.eventName, event.fileName);
                        }                       
                       root.appendChild(combobox);
                    }
                    else if( component instanceof JList)
                    {
                        Element listbox = document.createElement("LISTBOX");
                        listbox.setAttribute("ENABLED","true");
                        listbox.setAttribute("VISIBLE", "true");
                        listbox.setAttribute("X", component.getX()+"");
                        listbox.setAttribute("Y", component.getY()+"");
                        listbox.setAttribute("NAME", component.getName());
                        listbox.setAttribute("WIDTH", component.getWidth()+"");
                        listbox.setAttribute("HEIGHT", component.getHeight()+"");
                        
                        DefaultListModel listmodel = (DefaultListModel) ((JList)component).getModel();
                        for(int index=0; index<listmodel.size(); index++)
                        {
                           Element t = document.createElement("ITEM");
                           t.setAttribute("TEXT", listmodel.get(index).toString());
                           t.setAttribute("VALUE", listmodel.get(index).toString());
                           listbox.appendChild(t);                            
                        }
                        for( Event event : events )
                        {
                            listbox.setAttribute(event.eventName, event.fileName);
                        }
                        root.appendChild(listbox);
                    }
                }                
                document.appendChild(root);
                // Archivo
                TransformerFactory transFac = TransformerFactory.newInstance();
                Transformer trans = transFac.newTransformer();
                trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                trans.setOutputProperty(OutputKeys.INDENT, "yes");

                StringWriter sw = new StringWriter();
                StreamResult result = new StreamResult(sw);
                DOMSource source = new DOMSource(document);
                trans.transform(source, result);
                xml += sw.toString();                
            }
            
            FileOutputStream outputStream = new FileOutputStream(file);
            
            outputStream.write(xml.getBytes());
            outputStream.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }// </editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listConttrols = new javax.swing.JList();
        tabPaneWindows = new javax.swing.JTabbedPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        componentList = new javax.swing.JList();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        btnOpenXml = new javax.swing.JMenuItem();
        btnSaveXml = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        btnAddWindow = new javax.swing.JMenuItem();
        btnDeleteWindow = new javax.swing.JMenuItem();
        btnClose = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnGenerateAndroid = new javax.swing.JMenuItem();
        btnGenerateGWT = new javax.swing.JMenuItem();
        btnGenerateSwing = new javax.swing.JMenuItem();
        btnSwing2 = new javax.swing.JMenuItem();
        btnAndroidXml = new javax.swing.JMenuItem();
        btnGwtXml = new javax.swing.JMenuItem();
        btnSwingXml = new javax.swing.JMenuItem();
        btnSwing2Xml = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnShowTable = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        listConttrols.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Label", "CheckBox", "RadioButton", "Button", "TextBox",
                "ComboBox","ListBox","Picture" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listConttrols.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(listConttrols);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 80, 120, 550);
        getContentPane().add(tabPaneWindows);
        tabPaneWindows.setBounds(160, 80, 720, 480);

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 36)); // NOI18N
        jLabel1.setText("Argos Multiplatform GUI Builder");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(190, 10, 720, 50);

        componentList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        componentList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                componentListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(componentList);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(1050, 110, 140, 460);

        jMenu4.setText("File");

        btnOpenXml.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        btnOpenXml.setText("Open XML");
        btnOpenXml.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpenXmlMouseClicked(evt);
            }
        });
        btnOpenXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenXmlActionPerformed(evt);
            }
        });
        jMenu4.add(btnOpenXml);

        btnSaveXml.setText("Save XML");
        btnSaveXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveXmlActionPerformed(evt);
            }
        });
        jMenu4.add(btnSaveXml);

        jMenuBar1.add(jMenu4);

        jMenu1.setText("Window");

        btnAddWindow.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        btnAddWindow.setText("Add Window");
        btnAddWindow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWindowActionPerformed(evt);
            }
        });
        jMenu1.add(btnAddWindow);

        btnDeleteWindow.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        btnDeleteWindow.setText("Delete Window");
        btnDeleteWindow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteWindowActionPerformed(evt);
            }
        });
        jMenu1.add(btnDeleteWindow);

        btnClose.setText("Close DnD");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        jMenu1.add(btnClose);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Build");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });

        btnGenerateAndroid.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        btnGenerateAndroid.setText("Android");
        btnGenerateAndroid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateAndroidActionPerformed(evt);
            }
        });
        jMenu2.add(btnGenerateAndroid);

        btnGenerateGWT.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        btnGenerateGWT.setText("GWT");
        btnGenerateGWT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateGWTActionPerformed(evt);
            }
        });
        jMenu2.add(btnGenerateGWT);

        btnGenerateSwing.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        btnGenerateSwing.setText("Swing");
        btnGenerateSwing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateSwingActionPerformed(evt);
            }
        });
        jMenu2.add(btnGenerateSwing);

        btnSwing2.setText("Swing2");
        btnSwing2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSwing2ActionPerformed(evt);
            }
        });
        jMenu2.add(btnSwing2);

        btnAndroidXml.setText("Android From Xml");
        btnAndroidXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAndroidXmlActionPerformed(evt);
            }
        });
        jMenu2.add(btnAndroidXml);

        btnGwtXml.setText("GWT From Xml");
        btnGwtXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGwtXmlActionPerformed(evt);
            }
        });
        jMenu2.add(btnGwtXml);

        btnSwingXml.setText("Swing From Xml");
        btnSwingXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSwingXmlActionPerformed(evt);
            }
        });
        jMenu2.add(btnSwingXml);

        btnSwing2Xml.setText("Swing2 From Xml");
        jMenu2.add(btnSwing2Xml);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Events");

        btnShowTable.setText("Show Table");
        btnShowTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowTableActionPerformed(evt);
            }
        });
        jMenu3.add(btnShowTable);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnAddWindowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWindowActionPerformed
        if( !tabPaneWindows.isVisible() )
        {
            tabPaneWindows.setVisible(true);
        }
        JPanel panel = new JPanel(null);
        panel.setName("window"+(n_window));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setSize(500, 500);
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddMouseEvent(evt);
            }
        });

        tabPaneWindows.addTab("Window"+(n_window), null, panel);
        ImageIcon icon = new ImageIcon("src/window.png");
        tabPaneWindows.setIconAt(n_window++, icon);
        tabPaneWindows.revalidate();
        tabPaneWindows.repaint();       
    }//GEN-LAST:event_btnAddWindowActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        
    }//GEN-LAST:event_jMenu2MouseClicked

    private void btnGenerateAndroidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateAndroidActionPerformed
        File file = getSaveFileName();
        generateXmlFile(file);
        if( file.getAbsolutePath().equals(""))
        {
            return;
        }
        Principal p = new Principal( getProgram(file.getAbsolutePath()));

        java.net.URL url = ClassLoader.getSystemResource("Android/icon.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        p.setIconImage(img);

        p.setVisible(true);    
    }//GEN-LAST:event_btnGenerateAndroidActionPerformed

    private void btnGenerateGWTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateGWTActionPerformed
        File file = getSaveFileName();
        generateXmlFile(file);
        
        new GWT.GwtConfig(getProgram(file.getAbsolutePath())).setVisible(true);
    }//GEN-LAST:event_btnGenerateGWTActionPerformed

    private void btnGenerateSwingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateSwingActionPerformed
        File file = getSaveFileName();
        generateXmlFile(file);
        
        new Swing.SwingConfig(getProgram(file.getAbsolutePath())).setVisible(true);
    }//GEN-LAST:event_btnGenerateSwingActionPerformed

    private void btnDeleteWindowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteWindowActionPerformed
        eliminateWindow();
    }//GEN-LAST:event_btnDeleteWindowActionPerformed

    private void btnShowTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowTableActionPerformed
        String string="";
        for(Event event: eventTable)
        {
            string += event.componentName +"= "+event.eventName+" __ "+event.fileName+"\n";
        }
        if( !string.equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, string);
        }
    }//GEN-LAST:event_btnShowTableActionPerformed

    private void btnAndroidXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAndroidXmlActionPerformed
        loadXML(evt);
        android();
    }//GEN-LAST:event_btnAndroidXmlActionPerformed

    private void btnGwtXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGwtXmlActionPerformed
        loadXML(evt);
        gwt();
    }//GEN-LAST:event_btnGwtXmlActionPerformed

    private void btnSwingXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwingXmlActionPerformed
        loadXML(evt);
        swing();
    }//GEN-LAST:event_btnSwingXmlActionPerformed

    private void btnOpenXmlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpenXmlMouseClicked

    }//GEN-LAST:event_btnOpenXmlMouseClicked

    private void btnOpenXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenXmlActionPerformed
        int result = JOptionPane.showConfirmDialog(rootPane, "Would you like to save your project?", "Save",
                                      JOptionPane.YES_NO_OPTION);
        
       if( result == JOptionPane.YES_OPTION )   
       {
           File file = getSaveFileName();
           generateXmlFile(file);  
           tabPaneWindows.removeAll();       
           eventTable.clear();
           String filename = getOpenFileName();
           openProject(filename);
       }    
    }//GEN-LAST:event_btnOpenXmlActionPerformed

    private void btnSaveXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveXmlActionPerformed
        File file = getSaveFileName();
        generateXmlFile(file);  
    }//GEN-LAST:event_btnSaveXmlActionPerformed

    private void componentListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_componentListMouseClicked
        if( evt.getClickCount() != 2 )
        {
            JPanel panel = (JPanel) tabPaneWindows.getSelectedComponent();
            for(int i=0; i<panel.getComponentCount(); i++)
            {
                Component component = panel.getComponent(i);
                if( component.getName().equals(componentList.getSelectedValue().toString()) )
                {
                    JOptionPane.showMessageDialog(null, "No pude hacer esto");
                    break;
                }
            }
        }
    }//GEN-LAST:event_componentListMouseClicked

    private void btnSwing2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwing2ActionPerformed
        File file = getSaveFileName();
        generateXmlFile(file);
                
        new swing2.SwingConfig(getProgram(file.getAbsolutePath())).setVisible(true);
    }//GEN-LAST:event_btnSwing2ActionPerformed
    
    // <editor-fold defaultstate="collapsed" desc="Open XML">
    private void openProject(String filename)
    {
        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="MouseEvent">
    private void addToComponentList(String item)
    {
        DefaultListModel model = (DefaultListModel) componentList.getModel();
        model.addElement(item);
        componentList.setModel(model);
    }
    
    private void AddMouseEvent(java.awt.event.MouseEvent evt)
    {
        if( evt.getClickCount() != 2 )
        {
            return;
        }
        
        JPanel panel = (JPanel) evt.getSource();
        if( listSelectedValue == null)
        {
            return;
        }
        String selected = listSelectedValue;
        
        //DragMouseAdapter mouseAdapter = new DragMouseAdapter();        
        MouseAdapter mouseAdapter = dragMouseAdapter;
        if( selected.equals("Label") )
        {
            JLabel lbl = new JLabel("label"+(n_label++));
            String value = JOptionPane.showInputDialog(rootPane, "Set Value: ", null);
            
            if( value != null )
            {
                lbl.setText(value);
            }
            
            lbl.setName("label"+(n_label));
            lbl.addMouseListener(mouseAdapter);
            lbl.addMouseMotionListener(mouseAdapter);
            lbl.setBounds(evt.getX(), evt.getY(), 100, 60);
            lbl.setOpaque(false);
                      
            addToComponentList("label"+(n_label));
            
            panel.add(lbl);
        }
        else if( selected.equals("Button"))
        {
            JButton btn = new JButton("button"+(n_button++));
            String value = JOptionPane.showInputDialog(rootPane, "Set Value: ", null);
            
            if( value != null )
            {
                btn.setText(value);
            }
            
            btn.setName("button"+(n_button));
            btn.addMouseListener(mouseAdapter);
            btn.addMouseMotionListener(mouseAdapter);
            btn.setBounds(evt.getX(),evt.getY(), 100,60);
            btn.setOpaque(false);
            
            addToComponentList("button"+(n_button));
            
            panel.add(btn);
        }
        else if( selected.equals("CheckBox"))
        {
            JCheckBox check = new JCheckBox("checkBox"+(n_checkbox++));
            String value = JOptionPane.showInputDialog(rootPane, "Set Value: ", null);
            if( value != null )
            {
                check.setText(value);
            }
            
            check.setName("checkBox"+(n_checkbox));            
            check.addMouseListener(mouseAdapter);
            check.addMouseMotionListener(mouseAdapter);
            check.setBounds(evt.getX(), evt.getY(), 100,60);
            check.setOpaque(false);
            
            addToComponentList("checkBox"+(n_checkbox));
            
            panel.add(check);
        }
        else if( selected.equals("RadioButton"))
        {
            JRadioButton radioButton = new JRadioButton("radioButton"+(n_radiobutton++));
            String value = JOptionPane.showInputDialog(rootPane, "Set Value: ", null);
            if( value != null )
            {
                radioButton.setText(value);
            }
            radioButton.setName("radioButton"+(n_radiobutton));            
            radioButton.addMouseListener(mouseAdapter);
            radioButton.addMouseMotionListener(mouseAdapter);
            radioButton.setBounds(evt.getX(), evt.getY(), 100,60);
            radioButton.setOpaque(false);
            
            addToComponentList("radioButton"+(n_radiobutton));
            
            panel.add(radioButton);
        }
        else if( selected.equals("TextBox"))
        {
            JTextField textField = new JTextField();
            String value = JOptionPane.showInputDialog(rootPane, "Set Value: ", null);
            if( value != null )
            {
                textField.setText(value);
            }
            textField.setName("textBox"+(n_textbox++));            
            textField.addMouseListener(mouseAdapter);
            textField.addMouseMotionListener(mouseAdapter);
            textField.setBounds(evt.getX(), evt.getY(), 100, 60);
            textField.setOpaque(false);
            
            addToComponentList("textBox"+(n_textbox++));
            
            panel.add(textField);
        }
        else if( selected.equals("ComboBox"))
        {
            JComboBox combo = new JComboBox();
            String value = JOptionPane.showInputDialog(rootPane, "List of Values separated by ',' : ", null);
            if( value != null )
            {
                String values [] = value.split(",");
                for(String value1 : values )
                {
                    combo.addItem(value1);
                }
                combo.setName("combobox"+(n_combobox++));
                combo.addMouseListener(mouseAdapter);
                combo.addMouseMotionListener(mouseAdapter);
                combo.setBounds(evt.getX(), evt.getY(), 100, 60);
                combo.setOpaque(false);
                
                addToComponentList("combobox"+(n_combobox++));
                
                panel.add(combo);
            }
        }
        else if( selected.equals("ListBox"))
        {
            JList list = new JList();
            String value = JOptionPane.showInputDialog(rootPane, "List of Values separated by ',' : ", null);
            if( value != null )
            {
                DefaultListModel listmodel = new DefaultListModel();
                String values [] = value.split(",");
                for(String value1 : values )
                {
                    listmodel.addElement(value1);
                }
                if( values.length != 0)
                {
                    list.setModel(listmodel);
                    list.setName("list"+(n_listbox++));
                    list.addMouseListener(mouseAdapter);
                    list.addMouseMotionListener(mouseAdapter);
                    list.setBounds(evt.getX(), evt.getY(), 100, 60);
                    list.setOpaque(false);
                    addToComponentList("list"+(n_listbox++));
                    panel.add(list);
                }
            }
        }
        else if( selected.equals("Picture"))
        {
            JLabel lbl = new JLabel("picture"+(n_picture++));
            String iconpath = loadImageFile();
            
            if( iconpath != null)
            {               
                lbl.setName("picture"+(n_picture)+","+iconpath);
                lbl.addMouseListener(mouseAdapter);
                lbl.addMouseMotionListener(mouseAdapter);
                lbl.setBounds(evt.getX(), evt.getY(), 100, 60);
                lbl.setOpaque(false);
                ImageIcon icon = new ImageIcon(iconpath);
                lbl.setIcon(new ImageIcon( icon.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT)));
                addToComponentList("picture"+(n_picture));
                panel.add(lbl);            
            }
            
        }        
        panel.revalidate();
        panel.repaint();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="DragMouseEvent And ContextMenu">
    MouseAdapter dragMouseAdapter = new MouseAdapter() 
    {
        private Point initialLoc=null;
        private Point initialLocOnScreen=null;

        @Override
        public void mousePressed(MouseEvent e) {
            final JPopupMenu menu = new JPopupMenu();
            if( SwingUtilities.isRightMouseButton(e) )
            {           
                menu.setLocation(e.getXOnScreen(), e.getYOnScreen());
                final Component c = e.getComponent();
                
                ArrayList<String> events = getControlEvents(c);
                
                for(final String event: events)
                {
                    JMenuItem item = new JMenuItem(event);
                    menu.add(item);
                    item.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            menu.setVisible(false);
                            EventCode ev = new EventCode(DnD.this,c.getName(), event);
                            ev.setVisible(true);
                        }
                    });
                }
                
                JMenuItem item = new JMenuItem("Delete");
                item.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Container container = c.getParent();                   
                        c.getParent().remove(c);        
                        menu.setVisible(false);
                        container.repaint();
                    }
                });
                menu.add(item);
                menu.setVisible(true);
            }
            else
            {
                menu.setVisible(false);
                Component comp = (Component)e.getSource();
                if( comp == null)
                {
                    return;
                }
                initialLoc = comp.getLocation();
                initialLocOnScreen = e.getLocationOnScreen();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
           Component comp = (Component)e.getSource();
           if( comp == null || initialLocOnScreen == null || initialLoc == null)
           {
               return;
           }
           else
           {
             Point locOnScreen = e.getLocationOnScreen();

             int x = locOnScreen.x - initialLocOnScreen.x + initialLoc.x;
             int y = locOnScreen.y - initialLocOnScreen.y + initialLoc.y;
             comp.setLocation(x, y);
           }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
           Component comp = (Component)e.getSource();
           if( comp == null)
           {
               return;
           }
           else
           {
             Point locOnScreen = e.getLocationOnScreen();

             int x = locOnScreen.x - initialLocOnScreen.x + initialLoc.x;
             int y = locOnScreen.y - initialLocOnScreen.y + initialLoc.y;
             comp.setLocation(x, y);
           }
        }
    };
    
    private ArrayList<String> getControlEvents(Component component)
    {
        ArrayList<String> events = new ArrayList<String>();
        
        if( component instanceof JButton) 
        {
            events.add("Click");
        }
        else if( component instanceof JLabel)
        {
            if( ((JLabel)component).getIcon() != null )
            {
                events.add("Click");
            }
        }
        else if( component instanceof JRadioButton)
        {
            events.add("OnValueChanged");
        }
        else if( component instanceof JCheckBox)
        {
            events.add("OnValueChanged");
        }
        else if( component instanceof JTextField)
        {
            events.add("OnTextChanged");
        }
        else if( component instanceof JComboBox)
        {
            events.add("ItemChanged");
        }
        else if( component instanceof JList)
        {
            events.add("ItemChanged");
        }
        
        return events;
    }
    
    public void addEventCode(String eventName, String componentName, String filename)
    {
        eventTable.add(new Event(eventName,componentName,filename));
    }    
    // </editor-fold>
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnAddWindow;
    private javax.swing.JMenuItem btnAndroidXml;
    private javax.swing.JMenuItem btnClose;
    private javax.swing.JMenuItem btnDeleteWindow;
    private javax.swing.JMenuItem btnGenerateAndroid;
    private javax.swing.JMenuItem btnGenerateGWT;
    private javax.swing.JMenuItem btnGenerateSwing;
    private javax.swing.JMenuItem btnGwtXml;
    private javax.swing.JMenuItem btnOpenXml;
    private javax.swing.JMenuItem btnSaveXml;
    private javax.swing.JMenuItem btnShowTable;
    private javax.swing.JMenuItem btnSwing2;
    private javax.swing.JMenuItem btnSwing2Xml;
    private javax.swing.JMenuItem btnSwingXml;
    private javax.swing.JList componentList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listConttrols;
    private javax.swing.JTabbedPane tabPaneWindows;
    // End of variables declaration//GEN-END:variables
}
