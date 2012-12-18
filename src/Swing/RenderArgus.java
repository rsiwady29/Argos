/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Swing;


import Semantics.Controls.*;
import Semantics.Information.EventCode;
import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;





public class RenderArgus {
   String decl="";
   String cod="";
    File f;
    FileWriter w;
    BufferedWriter bw;
    PrintWriter wr; 
    
   public RenderArgus(ArrayList<Window> R0, int padre) throws Exception
   {
         RenderWindow(R0.get(padre));
   
   } 
   public void generaraOtros(ArrayList<Window> R0,int main)
   {     
       for(int x=0;x<R0.size();x++)
       {
           if(x!=main){
               try {
                   RenderWindow(R0.get(x));
                   compile(R0.get(x).getName());
               } catch (IOException ex) {
                   Logger.getLogger(RenderArgus.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
                
       }
	 
      

   }
   
     public  void compile(String Clase) throws IOException 
   {
        
            /*JavaCompiler jc;
            jc= ToolProvider.getSystemJavaCompiler();
            String file = "src\\Generando\\"+Clase+".java";
            jc.run(null, null, null,file);
            
            ClassLoader cl = new JavaSourceClassLoader(
         this.getClass().getClassLoader(), new File[] { new File("src\\Generando\\"+Clase+".java") }, // optionalSourcePath
         (String) null);
            Object o = cl.loadClass("Generando."+Clase).newInstance();
          ((Runnable)o).run();
       */
       
         String comando = "javac " +f.getPath();        
         
            Runtime.getRuntime().exec(comando);
     
        
       }
   
    public void ejecutar() throws IOException
     {
       String comando2 = "java " +f.getName().substring(0,f.getName().length()-5);
              Runtime.getRuntime().exec(comando2);
     }
   
   private void RenderWindow (Window wd)
   {
       decl="";
       cod="";
         String NombreVentana = ((Window)wd).getName();
         f = new File(NombreVentana+".java");
         try{
             w = new FileWriter(f);
             bw = new BufferedWriter(w);
             wr = new PrintWriter(bw); 
             //wr.write("package Generando;");
             wr.append("\r\n");


             //wr.append("import Three.ListBoxItem;");
             wr.append("\r\n");
             wr.append("import java.util.ArrayList;\n" +
"import java.util.Date;\n import java.awt.event.ActionListener;\n" +
"import java.awt.event.ActionEvent;import java.awt.event.ItemListener;import java.awt.event.ItemEvent;"+
                     "import java.awt.*;\n import javax.swing.*;\n import javax.swing.event.ListSelectionEvent;\n" +
"import javax.swing.event.ListSelectionListener;\n import java.awt.event.MouseEvent;\n" +
"import java.awt.event.MouseListener;\n" +
"import java.awt.event.WindowEvent;\n" +
"import java.awt.event.WindowListener;\n" );
             //+
//"import java.util.Date;import com.toedter.calendar.JDateChooser;");
             wr.append("\r\n");
             wr.append("\r\n");
             wr.append("import java.io.Serializable;import java.awt.Color;import javax.swing.ImageIcon;import java.awt.event.ActionListener;\n" +
"import java.awt.event.ActionEvent;");
             wr.append("\r\n");
             wr.append("\r\n");
              wr.write(" class DatePicker { \n");
        wr.write("        int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH); \n");
        wr.write("        int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);; \n");
        wr.write("        JLabel l = new JLabel(\"\", JLabel.CENTER); \n");
        wr.write("     String day = \"\"; \n");
        wr.write("        JDialog d; \n");
        wr.write("        JButton[] button = new JButton[49]; \n");

        wr.write("        public DatePicker(JFrame parent) { \n");
        wr.write("                d = new JDialog(); \n");
        wr.write("                d.setModal(true); \n");
        wr.write("                String[] header = { \"Sun\", \"Mon\", \"Tue\", \"Wed\", \"Thur\", \"Fri\", \"Sat\" }; \n");
        wr.write("                JPanel p1 = new JPanel(new GridLayout(7, 7)); \n");
        wr.write("                p1.setPreferredSize(new Dimension(430, 120)); \n");

        wr.write("                for (int x = 0; x < button.length; x++) { \n");
        wr.write("                        final int selection = x; \n");
        wr.write("                        button[x] = new JButton(); \n");
        wr.write("                        button[x].setFocusPainted(false); \n");
        wr.write("                        button[x].setBackground(Color.white); \n");
        wr.write("                       if (x > 6) \n");
        wr.write("                                button[x].addActionListener(new ActionListener() { \n");
        wr.write("                                        public void actionPerformed(ActionEvent ae) { \n");
        wr.write("                                                day = button[selection].getActionCommand(); \n");
        wr.write("                                                d.dispose(); \n");
        wr.write("                                        } \n");
        wr.write("                                }); \n");
        wr.write("                        if (x < 7) { \n");
        wr.write("                                button[x].setText(header[x]); \n");
        wr.write("                                button[x].setForeground(Color.red); \n");
        wr.write("                        } \n");
        wr.write("                        p1.add(button[x]); \n");
        wr.write("                } \n");
        wr.write("                JPanel p2 = new JPanel(new GridLayout(1, 3)); \n");
        wr.write("                JButton previous = new JButton(\"<< Previous\"); \n");
        wr.write("                previous.addActionListener(new ActionListener() { \n");
        wr.write("                        public void actionPerformed(ActionEvent ae) { \n");
        wr.write("                                month--; \n");
        wr.write("                                displayDate(); \n");
        wr.write("                        } \n");
        wr.write("                }); \n");
        wr.write("                p2.add(previous); \n");
        wr.write("                p2.add(l); \n");
        wr.write("               JButton next = new JButton(\"Next >>\"); \n");
        wr.write("              next.addActionListener(new ActionListener() { \n");
        wr.write("                        public void actionPerformed(ActionEvent ae) { \n");
        wr.write("                                month++; \n");
        wr.write("                                displayDate(); \n");
        wr.write("                        } \n");
        wr.write("                }); \n");
        wr.write("                p2.add(next); \n");
        wr.write("                d.add(p1, BorderLayout.CENTER); \n");
        wr.write("                d.add(p2, BorderLayout.SOUTH); \n");
        wr.write("                d.pack(); \n");
        wr.write("                d.setLocationRelativeTo(parent); \n");
        wr.write("                displayDate(); \n");
        wr.write("                d.setVisible(true); \n");
        wr.write("        } \n");

        wr.write("        public void displayDate() { \n");
        wr.write("                for (int x = 7; x < button.length; x++) \n");
        wr.write("                        button[x].setText(\"\"); \n");
        wr.write("                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat( \n");
        wr.write("                           \"MMMM yyyy\"); \n");
        wr.write("                java.util.Calendar cal = java.util.Calendar.getInstance(); \n");
        wr.write("                cal.set(year, month, 1); \n");
        wr.write("                int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK); \n");
        wr.write("                int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); \n");
        wr.write("                for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) \n");
        wr.write("                        button[x].setText(\"\" + day); \n");
        wr.write("                l.setText(sdf.format(cal.getTime())); \n");
        wr.write("                d.setTitle(\"Date Picker\"); \n");
        wr.write("        } \n");

        wr.write("        public String setPickedDate(String format) { \n");
        wr.write("                if (day.equals(\"\")) \n");
        wr.write("                        return day; \n");
        wr.write("                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat( \n");
        wr.write("                                format); \n");
        wr.write("                java.util.Calendar cal = java.util.Calendar.getInstance(); \n");
        wr.write("                cal.set(year, month, Integer.parseInt(day)); \n");
        wr.write("                return sdf.format(cal.getTime()); \n");
        wr.write("        } \n");
        wr.write(" } \n");
             
             wr.append("public class "+ NombreVentana + " extends JFrame {");
             wr.append("\r\n");
             wr.append("\t");
             
                wr.append("public static void main(String args[]) {");
                wr.append("\r\n");
                wr.append("\t");
                wr.append("\t");
                wr.append("final JFrame "+NombreVentana+"Marco = new JFrame(\""+NombreVentana+"\");");
                wr.append("\r\n");
                wr.append("\t");
                wr.append("\t");
                wr.append("\t");
                 wr.write(NombreVentana+"Marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n");
            
                wr.append("\r\n");
                wr.append("\r\n");
                wr.append("\t");
                wr.append("\t");
                wr.append(NombreVentana+"Marco.setLayout(null);");
                wr.append("\r\n");
             wr.append("\t");
             wr.append("\t");
             wr.append(NombreVentana+"Marco.setTitle(\""+wd.getTitle()+"\");");
             wr.append("\r\n");
             wr.append("\t");
             wr.append("\t");
             wr.append(NombreVentana+"Marco.setSize("+wd.getWidth()+","+wd.getHeight()+");");
             wr.append("\r\n");
             wr.append("\r\n");
             wr.append("\t");
             wr.append("\t");
             if(wd.getChildren()!=null){
                RecorrerHijos(wd.getChildren(), wd.getName());
                
             }
             wr.append(decl);
                 wr.append(cod);
              wr.append(wd.getName()+"Marco.addWindowListener(new WindowListener() {\n");
             wr.append("                @Override\n");
             wr.append("public void windowOpened(WindowEvent e) {\n");
                if(wd.getLoad().getSymbolTable()!=null)
                {   
                   wr.append(GeneradorSentencia.GetCode_SymbolTable(wd.getLoad().getSymbolTable()));
                   int x=0;
                    wr.append(GeneradorSentencia.GenerarCodigo(wd.getLoad().getStatements()));
                }
             wr.append("//throw new UnsupportedOperationException(\"Not supported yet.\");\n}");
             wr.append("@Override\n");
             wr.append("               public void windowClosing(WindowEvent e) {\n");
             wr.append("                    //throw new UnsupportedOperationException(\"Not supported yet.\");\n}\n");
             wr.append("                @Override\n");
             wr.append("                public void windowClosed(WindowEvent e) {\n");
             wr.append("                    //throw new UnsupportedOperationException(\"Not supported yet.\");\n}\n");
             wr.append("               @Override\n");
             wr.append("  public void windowIconified(WindowEvent e) {\n");
             wr.append("                    //throw new UnsupportedOperationException(\"Not supported yet.\");\n}\n");
             wr.append("   @Override\n");
             wr.append(" public void windowDeiconified(WindowEvent e) {\n");
             wr.append("                    //throw new UnsupportedOperationException(\"Not supported yet.\");\n}\n");
             wr.append("  @Override\n");
             wr.append("                public void windowActivated(WindowEvent e) {\n");
             wr.append("                    //throw new UnsupportedOperationException(\"Not supported yet.\");\n}\n");
             wr.append("                @Override\n");
             wr.append("                public void windowDeactivated(WindowEvent e) {\n");
             wr.append("                    //throw new UnsupportedOperationException(\"Not supported yet.\");\n}\n");
             wr.append("});\n");
              
         
              wr.append("\r\n");
             wr.append("\t");
               wr.append(NombreVentana+"Marco.setResizable(false);");
              wr.append("\r\n");
             wr.append("\t");
              wr.append(NombreVentana+"Marco.setVisible(true);");
             wr.append("}"); 
             wr.append("\r\n");
             wr.append("\t");
//           
            
             wr.append("}");
             wr.append("\r\n");
      /*             wr.append("public static JPanel buildDatePanel(String label, Date value) { \n"
              
+"JPanel datePanel = new JPanel();\n" 
 + "JDateChooser dateChooser = new JDateChooser();\n"
 + "if (value != null) { \n"
+ "dateChooser.setDate(value); \n"
 +"}\n"
 +"for (Component comp : dateChooser.getComponents()) {\n" 
 +"    if (comp instanceof JTextField) {\n" 
 +"	((JTextField) comp).setColumns(50);\n" 
 +"	((JTextField) comp).setEditable(false);\n" 
 +"    }\n"
+ " }\n" 
+"\n" +"datePanel.add(dateChooser);\n" +
"\n" +"SpinnerModel model = new SpinnerDateModel();\n"
 +"JSpinner timeSpinner = new JSpinner(model);\n" 
              
              +"JComponent editor = new JSpinner.DateEditor(timeSpinner, \"HH:mm:ss\");\n" 
              +
"timeSpinner.setEditor(editor);\n" +
"if(value != null) {\n" +
"    timeSpinner.setValue(value);\n" +
"}\n" +
"\n" +
"datePanel.add(timeSpinner);\n" +
"\n" +
"return datePanel;\n" +"}");
             wr.append("}");
    */         
             // <editor-fold defaultstate="collapsed" desc="Clase Global">   
wr.append(    "class Global {\n");
wr.append("public static void Msgbox(String caption, String message)\n{\n");
 wr.append("       JOptionPane.showMessageDialog(null, message,caption,1);\n}\n");   
 wr.append("     // <editor-fold defaultstate=\"collapsed\" desc=\"Funcion build in\">    \n");   
 wr.append("    public static int GetDay()    \n{\n");   
 wr.append("         Date date=new Date();\n");   
 wr.append("         return date.getDay();}\n");  
 
 wr.append("    public static void OpenWindow(String window)    \n{\n");   
 wr.append("        try{ String code=\"java \"+window;\n");   
 wr.append("           Runtime.getRuntime().exec(code);"
                    +"}catch(Exception e ){}"
         + "}\n");  
 
 wr.append("     public static int GetMonth(){ \n");   
 wr.append("         Date date = new Date();\n");   
 wr.append("         return date.getMonth();}\n");   
 wr.append("     public static int GetYear(){" +
"\n"+"        Date date = new Date();"
+"\n" +
"        return date.getYear();    }\n");   
 wr.append("     public static Date GetDate(){"+
"\n "+"       return new Date();   }"+
"\n");   
 wr.append("     public static String BooleanToString(boolean b) {"+ 
"        if (b) {"+

"            return \"True\";\n" +
"        }\n" +
"        return \"False\";\n" +
"    }");   
 
 wr.append("    public static boolean StringToBoolean(String s){\n" +
"        if (s.equals(\"True\")) {\n" +
"            return true;}\n" +
"        return false;     }\n" +
" \n");   
 
 wr.append("   public static char StringToChar(String c)   {\n" +
"        char a = c.charAt(0);\n" +
"        return a;}\n" +
"    ");   
 wr.append("    public static Date StringToDate(String c)\n" +
"    {\n" +
"        Date fecha=new Date();\n" +
"        String dia = c.charAt(0)+\"\"+c.charAt(1);\n" +
"        String mes = c.charAt(2)+\"\"+c.charAt(3);\n" +
"        String anio = c.charAt(4)+\"\"+c.charAt(5)+\"\"+c.charAt(6)+\"\"+c.charAt(7);\n" +
"        int dian= StringToInt(dia);\n" +
"                int mesn= StringToInt(mes);\n" +
"                        int anion= StringToInt(anio);\n" +
"        fecha.setDate(dian);\n" +
"        fecha.setMonth(mesn);\n" +
"        fecha.setYear(anion);\n" +
"        \n" +
"                return fecha;\n" +
"    }    \n" +
"   \n" +
"   public static float StringToFloat(String valor)\n" +
"   {       \n" +
"       return Float.parseFloat(valor);       \n" +
"   }\n" +
"    \n" +
"   \n" +
"   public static int StringToInt(String valor)\n" +
"   {\n" +
"       return Integer.parseInt(valor);\n" +
"   }\n" +
"   \n" +
"    \n" +
"    public static int CharToInt(char c)\n" +
"    {\n" +
"        int n = c-48;\n" +
"        return n;\n" +
"    }\n" +
"    \n" +
"    \n" +
"    public static String CharToString(char c)\n" +
"    {\n" +
"        String n  = \"\"+c+\"\";\n" +
"        return n;        \n" +
"    }\n" +
"        \n" +
"    \n" +
"    public static String DateToString(Date d) \n" +
"    {\n" +
"        return d.toString();\n" +
"    }\n" +
"\n" +
"\n" +
"    public static int FloatToInt(float valor)\n" +
"    {\n" +
"        \n" +
"        return (int)Math.round(valor);\n" +
"    }    \n" +
"    \n" +
"    public static String FloatToString(float valor)\n" +
"    {\n" +
"        return String.valueOf(Math.round(valor));\n" +
"    }    \n" +
"    \n \n" +
"    public static float IntegerToFloat(int valor)\n" +
"    {\n" +
"        float v=(float) (valor*1.0);\n" +
"        return v;\n" +
"    }    \n" +
"    \n" +
"    \n" +
"    \n" +
"    public static String IntegerToString(int valor)\n" +
"    {\n" +
"        \n" +
"        return String.valueOf(valor);\n" +
"    }    \n" +
"    \n" +
"              \n" +
"    \n" +
"    public static boolean IntegerToBoolean(Integer i) \n" +
"    {\n" +
"        if (i == 0) {\n" +
"            return false;    \n" +
"        }\n" +
"        return true;\n" +
"    }    \n" +
"   \n" +
"    \n" +
"    \n" +
"    public static char IntegerToChar(int c)\n" +
"    {\n" +
"        char p=' ';\n" +
"       if (c >= 0 && c <= 9) {\n" +
"         p= Character.forDigit(c, 10);\n" +
"        \n" +
"        }\n" +
"       return p;\n" +
"    }    \n" +
"    \n" +
"           \n" +
"    //</editor-fold>\n" +
"    \n" +
"     // <editor-fold defaultstate=\"collapsed\" desc=\"Funcion build in de Control\">        \n" +
"    public Color GetBorderColor(JComponent c) {\n" +
"        \n" +
"        return c.getForeground();\n" +
"    \n" +
"    }\n" +
"    public void SetBorderColor(JComponent c,Color borderColor) {\n" +
"        c.setForeground(borderColor);\n" +
"    }\n" +
"\n" +
"    public boolean IsEnabled(JComponent c) {\n" +
"        return c.isEnabled();\n" +
"    }\n" +
"\n" +
"    public static void SetEnabled(JComponent c,boolean enabled) {\n" +
"        c.setEnabled(enabled);\n" +
"    }\n" +
"\n" +
"    public boolean IsVisible(JComponent c) {\n" +
"        return c.isVisible();\n" +
"    }\n" +
"\n" +
"    public static void SetVisible(JComponent c,boolean visible) {\n" +
"        c.setVisible(visible);\n" +
"    }\n" +
"\n" +
"    /*public ArrayList<Control> GetChildren(JComponent c) {\n" +
"        //TODO\n" +
"        \n" +
"        return null;\n" +
"    }*/\n" +
"\n" +
"    public static void SetChildren(JComponent c,ArrayList<JComponent> children) {\n" +
"        //TODO\n" +
"        //c.setChildren(children);\n" +
"       \n" +
"    }\n" +
"\n" +
"    public java.awt.Font GetFont(JComponent c) {\n" +
"        //TODO\n" +
"        return null;\n" +
"    }\n" +
"\n" +
"   /*public static void SetFont(Control c,java.awt.Font font) {\n" +
"        //TODO\n" +
"        //c.setFont(font);\n" +
"    }\n" +
"*/\n" +
"    public int GetHeight(JComponent c) {\n" +
"        return c.getHeight();\n" +
"    }\n" +
"\n" +
"    public static void SetHeight(JComponent c,int height) {\n" +
"        c.setBounds(c.getX(),c.getY(),c.getWidth(),height);\n" +
"       \n" +
"    }\n" +
"\n" +
"    public static String GetName(JComponent c) {\n" +
"        return c.getName();\n" +
"    }\n" +
"\n" +
"    public static void SetName(JComponent c,String name) {\n" +
"        c.setName(name);\n" +
"    }\n" +
"\n" +
"    //public JComponent getParent(JComponent c) {\n" +
"    //    return c.getParent();\n" +
"    //}\n" +
"\n" +
"    //public static void setParent(JComponent c,JComponent parent) {\n" +
"    //    c.setParent(parent);\n" +
"    //}\n" +
"\n" +
"    public int GetTabIndex(JComponent c) {\n" +
"        //TODO\n" +
"        return 0;\n" +
"    }\n" +
"\n" +
"    public static void SetTabIndex(JComponent c,int tabIndex) {\n" +
"        //TODO\n" +
"        //c.setTabIndex(tabIndex);\n" +
"    }\n" +
"\n" +
"    public int GetWidth(JComponent c) {\n" +
"        return c.getWidth();\n" +
"    }\n" +
"\n" +
"    public static void SetWidth(JComponent c,int width) {\n" +
"        c.setBounds(c.getX(),c.getY(),width,c.getHeight());\n" +
"    }\n" +
"\n" +
"    public int GetX(JComponent c) {\n" +
"         return c.getX();\n" +
"    }\n" +
"\n" +
"    public static void SetX(JComponent c,int x) {\n" +
"        c.setBounds(x,c.getY(),c.getWidth(),c.getHeight());\n" +
"    }\n" +
"\n" +
"    public int GetY(JComponent c) {\n" +
"        return c.getY();\n" +
"    }\n" +
"\n" +
"    public static void SetY(JComponent c,int y) {\n" +
"        c.setBounds(c.getX(),y,c.getWidth(),c.getHeight());}\n" +
"    \n" +
"\n" +
"        \n" +
"    // </editor-fold>     \n" +
"    \n" +
"     // <editor-fold defaultstate=\"collapsed\" desc=\"Funcion build in de Boton\">    \n" +
"\n" +
"    public static String GetValue(JButton btn) \n" +
"    {\n" +
"       return btn.getText(); \n" +
"    }\n" +
"\n" +
"    public static void SetValue(JButton btn,String value) {\n" +
"        btn.setText(value);\n" +
"    }           \n" +
"     // </editor-fold>    \n" +
"    \n" +
"\n" +
"    \n" +
"     // <editor-fold defaultstate=\"collapsed\" desc=\"Funcion build in de CheckBox\">         \n" +
"    \n" +
"   public int GetDirection(JCheckBox c) {\n" +
"        //to do\n" +
"        return c.getHorizontalTextPosition();\n" +
"    }\n" +
"\n" +
"    public static void SetDirection(JCheckBox c,int direction) { //0 es izquierdo y 1 es derecho\n" +
"        \n" +
"    }\n" +
"\n" +
"    public static String GetText(JCheckBox c) {\n" +
"        return c.getText();\n" +
"    }\n" +
"\n" +
"    public static void SetText(JCheckBox c,String text) {\n" +
"        c.setText(text);\n" +
"    }\n" +
"\n" +
"    public boolean IsChecked(JCheckBox c) {\n" +
"        if(c.isSelected())\n" +
"        {\n" +
"        return true;\n" +
"        }else{\n" +
"        \n" +
"        return false;\n" +
"        }\n" +
"        \n" +
"    }\n" +
"\n" +
"    public static void SetChecked(JCheckBox c,boolean checked) {\n" +
"        c.setSelected(checked);\n" +
"    }    \n" +
"    \n" +
"   // </editor-fold> \n" +
"    \n" +
"    \n" +
"     // <editor-fold defaultstate=\"collapsed\" desc=\"Funcion build in de Combobox\">        \n" +
"    public ArrayList<Object> GetItems(JComboBox cmb) \n" +
"    {\n" +
"        \n" +
"       ArrayList<Object> items=new ArrayList<Object>();\n" +
"       for ( int i = 0;  i < cmb.getItemCount(); i++) \n" +
"       {\n" +
"            items.add(cmb.getItemAt(i));\n" +
"       }           \n" +
"      return items;  \n" +
"    }\n" +
"\n" +
"    public static void SetItems(JComboBox cmb,ArrayList<Object> items) \n" +
"    {\n" +
"        for(int i=0;i<items.size();i++)\n" +
"        {\n" +
"            cmb.addItem(items.get(i));\n" +
"        }\n" +
"    }\n" +
"\n" +
"    public int GetSelectedIndex(JComboBox cmb,int n) \n" +
"    {\n" +
"       cmb.setSelectedIndex(n);\n" +
"       return n;               \n" +
"    }\n" +
"\n" +
"    public static void SetSelectedIndex(JComboBox cmb,int selectedIndex) \n" +
"    {        \n" +
"        cmb.setSelectedIndex(selectedIndex);\n" +
"    }\n" +
"\n" +
" \n" +
"    public static void SetSelectedItem(JComboBox cmb,Object selectedItem) \n" +
"    {\n" +
"        cmb.setSelectedItem(selectedItem);                \n" +
"    }\n" +
"    \n" +
"    public Object GetSelectedItem(JComboBox cmb,Object n) \n" +
"    {\n" +
"       cmb.setSelectedItem(n);\n" +
"       return n;     \n" +
"    }        \n" +
"    \n" +
"    \n" +
"    // </editor-fold>     \n" +
"    \n" +
"     // <editor-fold defaultstate=\"collapsed\" desc=\"Funcion build in de DatePicker\">            \n" +
"    public static String getDateFormat() {\n" +
"        return null;\n" +
"    }\n" +
"\n" +
"    public static void setDateFormat(String dateFormat) {\n" +
"        //TODO\n" +
"    }\n" +
"\n" +
"    public Date getMinValue() {\n" +
"        return null;\n" +
"    }\n" +
"\n" +
"    public static void setMinValue(Date minValue) {\n" +
"        //TODO\n" +
"    }\n" +
"\n" +
"    public Date getValue() {\n" +
"        return null;\n" +
"    }\n" +
"    \n" +
"// </editor-fold>    \n" +
"\n" +
"    \n" +
"     // <editor-fold defaultstate=\"collapsed\" desc=\"Funcion build in de Label\">            \n" +
"     public static String getValue(JLabel l) {\n" +
"        return l.getText();\n" +
"    }\n" +
"\n" +
"    public static void SetValue(JLabel l,String value) {\n" +
"        l.setText(value);\n" +
"    }    \n" +
"    \n" +
"// </editor-fold>    \n" +
"    \n" +
"    \n" +
"     // <editor-fold defaultstate=\"collapsed\" desc=\"Funcion build in de Picture\">                \n" +
"    public static String GetPath(JLabel l) {\n" +
"        return ((ImageIcon)l.getIcon()).getDescription() ;\n" +
"    }\n" +
"\n" +
"    public static void SetPath(JLabel l,String path) {\n" +
"        //TODO\n" +
"        l.setIcon(new ImageIcon(path,path));\n" +
"    }\n" +
"\n" +
"    public int GetSizeType() {\n" +
"        return 0;\n" +
"    }\n" +
"\n" +
"    public static void SetSizeType(int sizeType) {\n" +
"        //TODO\n" +
"    }  \n" +
"\n" +
"    \n" +
" // </editor-fold>   \n" +
"    \n" +
"    \n" +
"     // <editor-fold defaultstate=\"collapsed\" desc=\"Funcion build in de RadioButton\">                    \n" +
"  \n" +
"    public int GetDirection(JRadioButton r) {\n" +
"        return r.getHorizontalTextPosition();\n" +
"    }\n" +
"\n" +
"    public static void SetDirection(JRadioButton r,int direction) { //0 es izquierdo y 1 es derecho\n" +
"        r.setHorizontalTextPosition(direction);\n" +
"    }\n" +
"\n" +
"    public static String GetText(JRadioButton r) {\n" +
"        return r.getText();\n" +
"    }\n" +
"\n" +
"    public static void SetText(JRadioButton r,String text) {\n" +
"       r.setText(text);\n" +
"    }\n" +
"\n" +
"    public boolean IsChecked(JRadioButton r) {\n" +
"         if(r.isSelected())\n" +
"        {\n" +
"        return true;\n" +
"        }else{\n" +
"        \n" +
"        return false;\n" +
"        }\n" +
"    }\n" +"\n" +
"    public static void SetChecked(JRadioButton r,boolean checked) {\n" +
"      r.setSelected(checked);\n" +
"    }\n" +
"    \n" +
"    //</editor-fold>\n" +
"    \n" +
"    \n" +
"   \n" +
"     // <editor-fold defaultstate=\"collapsed\" desc=\"Funcion build in de Textbox\">                \n" +
"    public boolean IsReadOnly(JTextField t) {\n" +
"        return t.isEnabled();\n" +
"    }\n" +"\n" +
"    public static void SetReadOnly(JTextField t,boolean readOnly) {\n" +
"        t.setEnabled(readOnly);\n" +
"    }\n" +
" \n" +
"\n" +
"    public static String GetValue(JTextField t) {\n" +
"        return t.getText();\n" +
"    }\n" +
"\n" +
"    public static void SetValue(JTextField t,String value) {\n" +
"        t.setText(value);\n" +
"    }\n" +
"    \n" +
"    //</editor-fold>   \n" +
"    \n" +
"}\n" +
"\n" +
"      ");   
 //</editor-fold>





  
             wr.close();
             bw.close();
    //         RecorrerVentana(wd.getChildren());
             
             
             
             
         }catch(IOException e)
         {
             System.out.print (e.getMessage());
         }
          
          
          
     
            
       
   }
   
   private void RecorrerHijos(ArrayList<Control> childs, String papa)
   {
       for (int i=0; i<childs.size(); i++) {
           
           Control it = childs.get(i);
           
           if(it instanceof Button)
           {
               RenderButton((Button)it,papa);
               
           }
           else if(it instanceof Label )
           {
               
               RenderLabel((Label)it,papa);
               
               
           }else if(it instanceof CheckBox)
           {
               
               RenderCheckBox((CheckBox)it,papa);
               
                     
           }else if(it instanceof DatePicker)
           {
               
              RenderDatePicker((DatePicker)it,papa);
               
               
               
           }else if(it instanceof ListBox)
           {
               
              RenderListbox((ListBox)it,papa);
               
               
               
           }else if(it instanceof Panel)
           {
               
              RenderPanel((Panel)it,papa);
               
               
               
           }else if(it instanceof Picture)
           {
               
              RenderPicture((Picture)it,papa);
               
               
               
           }else if(it instanceof RadioButton)
           {
               
             RenderRadioButton((RadioButton)it,papa);
               
               
               
           }else if(it instanceof TextBox)
           {
               
             RenderTextBox((TextBox)it,papa);
               
               
               
           }else if(it instanceof ComboBox)
           {
               RenderComboBox((ComboBox)it,papa);
           }
       }
   }
   
   private void RecorrerVentana(ArrayList<Control> childs)
   {
      for (int i=0; i<childs.size(); i++) {
           
           Control it = childs.get(i);
           if(it instanceof Window)
           {
               RenderWindow((Window)it);
               
           }
           
           
           
           
      }
   }
        
   
   private void RenderLabel(Label lb,String Ventana)
   {
       //wr.append
       cod+=("//label" + lb.getName());
       //wr.append
       cod+=("\r\n"); 
//       wr.append
       cod+=("\t");
       cod+=("\t");
       //wr.append 
               cod+=("\t");
       decl+=" final JLabel "+ lb.getName() + "= new JLabel(\""+lb.getValue()+"\");\n";
//       wr.append("JLabel "+ lb.getName() + "= new JLabel(\""+lb.getValue()+"\");"  );
       //wr.append
       cod+=("\r\n");
       //wr.append
       cod+=("\t");
       //wr.append
       cod+=("\t");
       //wr.append
       cod+=(lb.getName()+".setBounds("+lb.getX()+","+lb.getY()+","+lb.getWidth()+","+lb.getHeight()+");");
       //wr.append
       cod+=("\r\n");
       //wr.append
       cod+=("\t");
       //wr.append
       cod+=("\t");
  //     java.awt.Font ft = lb.getFont().getFont();
       //wr.append
    //           cod+=(lb.getName()+".setFont((new java.awt.Font(\""+ ft.getFontName() +"\"" + "," + ft.getStyle()+","+ ft.getSize()+  ")));");
       //wr.append
               cod+=("\r\n");
       //wr.append
               cod+=("\t");
       //wr.append
               cod+=("\t");
    //   Color Cl = lb.getFont().getForeground();
       //wr.append
     //  cod+=("Color color= new Color (" + Cl.getRed() + "," + Cl.getGreen() + "," + Cl.getBlue()+ ");");
       //wr.append
       cod+=("\r\n");
       //wr.append
       cod+=("\t");
       //wr.append
       cod+=("\t");
       //wr.append
     //  cod+=(lb.getName()+".setForeground(color);");
       //wr.append
       cod+=("\r\n");
       //wr.append
       cod+=("\t");
       //wr.append
       cod+=("\t");
     //  Cl = lb.getFont().getBackground();
       //wr.append
      // cod+=("color= new Color (" + Cl.getRed() + "," + Cl.getGreen() + "," + Cl.getBlue()+ ");");
       //wr.append
       cod+=("\r\n");
       //wr.append
       cod+=("\t");
       //wr.append
       cod+=("\t");
       //wr.append
    //   cod+=(lb.getName()+".setBackground(color);");
       //wr.append
       cod+=("\r\n");
       //wr.append
       cod+=("\t");
       //wr.append
       cod+=("\t");
       //wr.append
       cod+=(Ventana+"Marco.add("+ lb.getName()+");");
       //wr.append
       cod+=("\r\n");
       //wr.append
       cod+=("\r\n");
       //wr.append
       cod+=("\r\n");
       //wr.append
       cod+=("\t");
       //wr.append
       cod+=("\t");
       
     
       
       
   }
   
  //REady
   private void RenderButton(Button bt,String Ventana)
   {
       
       cod+=("\r\n");
       cod+=("\r\n");
       //wr.append("\t");
       //wr.append("\t");
       cod+=("//JButton" + bt.getName());
       cod+=("\r\n");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       decl+=("final JButton "+ bt.getName() + "B = new JButton(\""+bt.getValue()+"\");\n");
       
       cod+=(bt.getName()+"B.setBounds("+bt.getX()+","+bt.getY()+","+bt.getWidth()+","+bt.getHeight()+");");
       cod+=("\r\n");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=("\t");
       cod+=(bt.getName()+"B.addActionListener(new ActionListener() { \n");
        cod+=("                        public void actionPerformed(ActionEvent ae) { \n");
  //     cod+=( "  Global.OpenWindow(\"vtn2\"); ") ;
       
       if(bt.getClick().getSymbolTable()!=null){

            cod+=(GeneradorSentencia.GetCode_SymbolTable(bt.getClick().getSymbolTable()));
            cod+=("\r\n");
            cod+=(GeneradorSentencia.GenerarCodigo(bt.getClick().getStatements()));
        }
            cod+=("\r\n");
        cod+=("                        } \n");
        cod+=("                }); \n");
       cod+=("\r\n");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=("\t");
        cod+=("//"+bt.getName()+"B");
       cod+=("\r\n");
        cod+=("\t");
       cod+=("\t");
       cod+=(Ventana+"Marco.add("+ bt.getName()+"B);");
       cod+=("\r\n");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       
       
       
       
   }
   
//REady
   private void RenderCheckBox(CheckBox ck, String papa)
   {
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=("//CheckBox" + ck.getName());
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       decl+=("final JCheckBox "+ ck.getName() + "= new JCheckBox();\n"  ); 
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=(ck.getName()+".setText(\""+ck.getText()+"\");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t"); 
       cod+=(ck.getName()+".setBounds("+ck.getX()+","+ck.getY()+","+ck.getWidth()+","+ck.getHeight()+");\n");
       cod+=("              "+ck.getName()+".addActionListener(new ActionListener() {\n"
               + "                @Override\n"

+"               public void actionPerformed(ActionEvent e)  {\n" +
 "              ");
       if(ck.getOnValueChanged()!=null){
       cod+=(ck.getOnValueChanged().GetCodigo_symbolTable());
 cod+=("\r\n");
       cod+=("\t");
       cod+=("\t"); 
  cod+= GeneradorSentencia.GenerarCodigo(ck.getOnValueChanged().getStatements());}
   cod+=(        
                ""+//en esta linea va el codigo

               "\n" +
"            }\n" +
"        });          ");
       


          
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t"); 
       cod+=(papa+"Marco.add("+ck.getName()+");");
   
   }
   
   //Ready
   private void RenderDatePicker(DatePicker dt,String Ventana)
   {
       decl += ("         final JFrame f" + dt.getName() + " = new JFrame(); \n");
     
      // decl += ("         final JLabel label" + dt.getName() + " = new JLabel(\" " + dt.getValue() + "\"); \n");
     //   cod += ("         label" + dt.getName() + ".setBounds(" + dt.getX() + ", " + dt.getY() + ", " + dt.getWidth() + ", " + dt.getHeight() + "); \n");
        decl += ("         final JComboBox text" + dt.getName() + " = new JComboBox(); \n");
        cod += ("         text" + dt.getName() + ".setBounds(" + dt.getX() + "+50, " + dt.getY() + ", 85, 30); \n");
//        decl += ("         final JButton b" + name + " = new JButton(\"...\"); \n");
    //    cod += ("         b" + name + ".setBounds(" + x + "+135, " + y + ", 30, 30); \n");
       // cod += ("         "+Ventana+"Marco.add(label" + dt.getName() + ");  \n");
   //     cod += ("         "+Ventana+"Marco.add(text" + dt.getName() + ");  \n");
        cod+=("\n\n //datepicker\n\n              text" + dt.getName() + ".removeAllItems();");
        cod += ("\n          text" + dt.getName() + ".addItem(\"Seleccionar Fecha\");\n");
        cod += ("\n                text" + dt.getName() + ".addActionListener(new ActionListener() { \n");
        cod += ("                        public void actionPerformed(ActionEvent ae) { \n");
                             cod+=("                            if(text"+ dt.getName()+".getSelectedItem().toString().equals(\"Seleccionar Fecha\")){\n" );
        cod+=("\n          text" + dt.getName() + ".removeAllItems();");
        cod+=("\n          text" + dt.getName() + ".addItem(\"Seleccionar Fecha\");");
        cod+=("\n          text" + dt.getName() + ".addItem((new DatePicker(f"+dt.getName()+").setPickedDate(\"ddMMyyyy\")));");
        cod+=("\n          text" + dt.getName() + ".setSelectedIndex(1);}");
        
//        cod += ("                                text" + dt.getName() + ".addItem((new DatePicker(f"+dt.getName()+").setPickedDate(\"" + dt.getDateFormat() + "\"))); \n");
        cod += ("                        } \n");
        cod += ("                }); \n");
       cod+=(Ventana+"Marco.add(text"+dt.getName()+");");
   }
   
   //Ready
   private void RenderComboBox(ComboBox cb,String Ventana)
   {
       cod+=("//ComboBox" + cb.getName());
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       decl+=("final JComboBox "+ cb.getName() + "= new JComboBox();");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=(cb.getName()+".setBounds("+cb.getX()+","+cb.getY()+","+cb.getWidth()+","+cb.getHeight()+");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
//       java.awt.Font fc = cb.getFont().getFont();
   //    cod+=(cb.getName()+".setFont((new java.awt.Font(\""+ fc.getFontName() +"\"" + "," + fc.getStyle()+","+ fc.getSize()+  ")));");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
 //      Color Ccb = cb.getFont().getForeground();
  //     cod+=("Color colorcb= new Color (" + Ccb.getRed() + "," + Ccb.getGreen() + "," + Ccb.getBlue()+ ");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
 //      cod+=(cb.getName()+".setBackground(color);");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
  //     cod+=(cb.getName()+".setEditable("+cb.isEnabled()+");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       for(int x=0;x<cb.getItems().size();x++)
       { 
           cod+=(cb.getName()+".addItem(\""+cb.getItems().get(x).getText() +"\");");
       } 
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t"); 
cod+=  (     cb.getName()+".addActionListener(new ActionListener()  {\n"+
        "@Override \n"+"             public void actionPerformed(ActionEvent e) {\n");
        if(cb.getItemChanged().getSymbolTable()!=null){
        cod+=cb.getItemChanged().getSymbolTable();
               cod+=("\r\n");
        cod+=GeneradorSentencia.GenerarCodigo(cb.getItemChanged().getStatements());
        }
    cod+=" }\n"; 
    cod+=    "});\n";
       
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t"); 
       cod+=(Ventana+"Marco.add("+ cb.getName()+");");
       cod+=("\r\n");
       cod+=("\r\n");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
   
   }
   
   private void RenderListbox(ListBox lb,String Ventana)//
   {
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=("//ListBox" + lb.getName());
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       decl+=("final JList "+ lb.getName() + "= new JList();\n");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=(lb.getName()+".setBounds("+lb.getX()+","+lb.getY()+","+lb.getWidth()+","+lb.getHeight()+");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
//       if(lb.getFont()!=null){
  //     java.awt.Font fl = lb.getFont().getFont();
      // cod+=(lb.getName()+".setFont((new java.awt.Font(\""+ fl.getFontName() +"\"" + "," + fl.getStyle()+","+ fl.getSize()+  ")));");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
    //   Color Clb = lb.getFont().getForeground();
       //cod+=("Color colorlb= new Color (" + Clb.getRed() + "," + Clb.getGreen() + "," + Clb.getBlue()+ ");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
    //   cod+=(lb.getName()+".setBackground(colorlb);");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
    //   cod+=(lb.getName()+".setEnabled("+lb.isEnabled()+");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       
       
       cod+=("String[] Alb = new String["+lb.getItems().size()+"];");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       for(int x=0;x<lb.getItems().size();x++)
       { 
//           cod+=("Alb["+x+"]=new ListBoxItem();");
  //                   cod+=("\r\n");
           cod+=("\t");
           cod+=("\t");
           cod+=("Alb["+x+"]=(\""+lb.getItems().get(x).getText() +"\");");
           cod+=("\r\n");
           cod+=("\t");
           cod+=("\t");
//           cod+=("Alb["+x+"].setValue(\""+lb.getItems().get(x).getValue() +"\");");
           cod+=("\r\n");
           cod+=("\t");
           cod+=("\t");
       }
          cod+=(lb.getName()+".setListData(Alb);");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
        decl+=("final JScrollPane src"+lb.getName()+"=new JScrollPane("+ lb.getName()+");\n"); 
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t"); 
        cod+=("src"+lb.getName()+".setBounds("+lb.getX()+","+lb.getY()+","+lb.getWidth()+","+lb.getHeight()+");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t"); 
      cod+=  (     lb.getName()+".addListSelectionListener(new  ListSelectionListener()  {\n"+
        "@Override \n"+"                            public void valueChanged(ListSelectionEvent e) {\n");
            cod+=( "  Global.OpenWindow(\"vtn2\"); ") ;
      if(lb.getItemChanged().getSymbolTable()!=null){
             
            cod+=GeneradorSentencia.GetCode_SymbolTable(lb.getItemChanged().getSymbolTable());
               cod+=("\r\n");
        cod+=GeneradorSentencia.GenerarCodigo(lb.getItemChanged().getStatements());
        }
        cod+=" }\n"; 
    cod+=    "});\n";
       cod+=(Ventana+"Marco.add(src"+lb.getName()+");");
       cod+=("\r\n");
       cod+=("\r\n");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
           
   }
   	



        
   private void RenderPanel(Panel pn,String Ventana)
   {
       cod+=("//JPanel" + pn.getName());
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       decl+=("final JPanel "+ pn.getName() + "= new JPanel();\n"  ); 
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=("final ButtonGroup "+ pn.getName()+"OP = new ButtonGroup();"  ); 
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=(pn.getName()+".setBounds("+pn.getX()+","+pn.getY()+","+pn.getWidth()+","+pn.getHeight()+");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=("\r\n");
       RecorrerHijos(pn.getChildren(), pn.getName());
       Color Cl = pn.getBorderColor();
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=("Color pncolor= new Color (" + Cl.getRed() + "," + Cl.getGreen() + "," + Cl.getBlue()+ ");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=(pn.getName()+".setBackground(pncolor);");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=(Ventana+"Marco.add("+ pn.getName()+");");
       cod+=("\r\n");
       cod+=("\r\n");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       
 
   }
   
   private void RenderPicture(Picture pc,String Ventana)//
   {
       String info="";
       if(pc.getClick()!=null)
       {
           info+=pc.getClick().GetCodigo_symbolTable();
           info+=GeneradorSentencia.GenerarCodigo(pc.getClick().getStatements());
       }
       
                decl+=("final ImageIcon img"+pc.getName()+" = new ImageIcon(\""+pc.getPath()+"\");\n");
                decl+=("final JLabel e"+pc.getName()+"= new JLabel(img"+pc.getName()+");\n");
                cod+=("e"+pc.getName()+".setBounds("+pc.getX()+","+pc.getY()+","+pc.getWidth()+","+pc.getHeight()+");");
                cod+=("  e"+pc.getName()+".addMouseListener(new MouseListener() {\n" +
"\n" +
"                @Override\n" +
"                public void mouseClicked(MouseEvent e) {\n" +
"                  "+info+"  ;\n" +
"                }\n" +
"\n" +
"                @Override\n" +
"                public void mousePressed(MouseEvent e) {\n" +
"                    "+info+"  ;\n" +
"                }\n" +
"\n" +
"                @Override\n" +
"                public void mouseReleased(MouseEvent e) {\n" +
"                    //throw new UnsupportedOperationException(\"Not supported yet.\");\n" +
"                }\n" +
"\n" +
"                @Override\n" +
"                public void mouseEntered(MouseEvent e) {\n" +
"                   // throw new UnsupportedOperationException(\"Not supported yet.\");\n" +
"                }\n" +
"\n" +
"                @Override\n" +
"                public void mouseExited(MouseEvent e) {\n" +
"                //    throw new UnsupportedOperationException(\"Not supported yet.\");\n" +
"                }\n" +
"            });");
                cod+=(Ventana+"Marco.add(e"+pc.getName()+");");
   }
   
   
   // codigo comentado
   private void RenderRadioButton(RadioButton rb,String panelName)//
   {
        cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
        cod+=("//OptionButton" + rb.getName());
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       decl+=("final JRadioButton "+ rb.getName() + "= new JRadioButton();\n"  );
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
        cod+=(rb.getName()+".setBounds("+rb.getX()+","+rb.getY()+","+rb.getWidth()+","+rb.getHeight()+");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
//       java.awt.Font frb = rb.getFont().getFont();
  //     cod+=(rb.getName()+".setFont((new java.awt.Font(\""+ frb.getFontName() +"\"" + "," + frb.getStyle()+","+ frb.getSize()+  ")));");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
  //     Color Crb = rb.getFont().getForeground();
   //    cod+=("Color color"+rb.getName()+"= new Color (" + Crb.getRed() + "," + Crb.getGreen() + "," + Crb.getBlue()+ ");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
   //    cod+=(rb.getName()+".setEnabled("+rb.isEnabled()+");");
        cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=(rb.getName()+".setText(\""+rb.getText()+"\");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
         cod+=("              "+rb.getName()+".addItemListener(new ItemListener() {\n"
               + "                @Override\n"

+"                public void itemStateChanged(ItemEvent e) {\n" +
 "                if("+rb.getName()+".isSelected()){\n"
               + rb.getName()+".setSelected(true);                                  ");
               //en esta linea va el codigo
                 if(rb.getOnValueChanged()!=null){
                 cod+=(rb.getOnValueChanged().GetCodigo_symbolTable());
                 cod+= GeneradorSentencia.GenerarCodigo(rb.getOnValueChanged().getStatements());}
cod+=(               "}\n" +
"            }\n" +
"        });          ");
     //  cod+=(panelName+"OP"+".add("+rb.getName()+");" );
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=(panelName+"Marco.add("+rb.getName()+");");
       cod+=("\r\n");
       cod+=("\r\n");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
   }
   //finish
   private void RenderTextBox(TextBox tb,String Ventana)
   {
       cod+=("//TextBox" + tb.getName());
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       decl+=("final JTextField "+ tb.getName() + "= new JTextField(\""+tb.getValue()+"\");\n"  );
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=(tb.getName()+".setBounds("+tb.getX()+","+tb.getY()+","+tb.getWidth()+","+tb.getHeight()+");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
//       java.awt.Font ft = tb.getFont().getFont();
  //     cod+=(tb.getName()+".setFont((new java.awt.Font(\""+ ft.getFontName() +"\"" + "," + ft.getStyle()+","+ ft.getSize()+  ")));");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
 //      Color Cl = tb.getFont().getForeground();
 //      cod+=("Color colortxt= new Color (" + Cl.getRed() + "," + Cl.getGreen() + "," + Cl.getBlue()+ ");");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
 //      cod+=(tb.getName()+".setBackground(color);");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
 //      cod+=(tb.getName()+".setBackground(color);");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
   //    cod+=(tb.getName()+".setEditable("+tb.isEnabled()+");");
        cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=  (     tb.getName()+".addActionListener(new ActionListener()  {\n"+
        "@Override \n"+"             public void actionPerformed(ActionEvent e) {\n");
        if(tb.getOnTextChanged().getSymbolTable()!=null){
        cod+=GeneradorSentencia.GetCode_SymbolTable(tb.getOnTextChanged().getSymbolTable());
               cod+=("\r\n");
        cod+=GeneradorSentencia.GenerarCodigo(tb.getOnTextChanged().getStatements());
        }
        
    cod+=" }\n"; 
    cod+=    "});\n";
       
       
       
        cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       cod+=(Ventana+"Marco.add("+ tb.getName()+");");
       cod+=("\r\n");
       cod+=("\r\n");
       cod+=("\r\n");
       cod+=("\t");
       cod+=("\t");
       
   }
   
  
    
    
    
}
