/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import Semantics.Controls.Control;
import Semantics.Controls.ListBox;

import Semantics.Controls.ListBoxItem;

import Semantics.Controls.Window;
import Semantics.Information.EventCode;


import java.awt.Color;
import java.awt.Font;
import java.io.*;


import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.Position;

/**
 *
 * @author jaime/joshua
 */
public class manageFile {

    File f;
    FileWriter w;
    BufferedWriter bw;
    PrintWriter wr;
    String declaraciones;
    String codigos;

    public manageFile(File f) {
        this.f = f;
        try {
            w = new FileWriter(f);
            
            bw = new BufferedWriter(w);
            
            wr = new PrintWriter(bw);
            declaraciones = "";
            codigos = "";
        } catch (Exception e) {
        }

    }

    private void writePicker() {
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

    }

    public void writeWIndow(String title, int height, int width) {
        {
            try {
                //String txt="";
                System.out.print(this.onlyname(f.getName()));
                wr.write("import java.awt.*;\n");
                wr.write("import javax.swing.*;\n");
                wr.write("import java.awt.event.ActionListener;\n");
                wr.write("import java.awt.event.ActionEvent;\n");
                wr.write("import java.util.Date; \n");
                wr.write("import java.util.ArrayList; \n");
                wr.write("import javax.swing.event.ListSelectionEvent; \n");
                wr.write("import javax.swing.event.ListSelectionListener; \n");                
                writeGlobal();
                writePicker();

                wr.write("    public class " + this.onlyname(f.getName()) + " extends JFrame{\n");


                wr.write("      public static void main(String args[]){\n");
                wr.write("final global Global=new global(); \n");
                wr.write("          final JFrame marco = new JFrame(\"" + title + "\");\n");
                wr.write("          marco.setLayout(null);\n");

                wr.write("          marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n");
                //codigos+=("    JPanel panel1 = new JPanel();\n");
                // codigos+=("    marco.getContentPane().add(panel1);\n");
                wr.write("          marco.setSize(" + height + "," + width + "); \n");
                wr.write("          marco.setLocation(200,200); \n");

            } catch (Exception e) {
                System.out.print("ERROR");
            };

        }


    }

    public void writeDate(String name, String value, String format, int height, int width, int x, int y, Font font, Color backgroundcolor, boolean enable, boolean visible,
            Control parent) {

        //PICKER
        declaraciones += ("         final JLabel label" + name + " = new JLabel(\" " + value + "\"); \n");
        codigos += ("         label" + name + ".setBounds(" + x + ", " + y + ", " + width + ", " + height + "); \n");
        declaraciones += ("         final JTextField text" + name + " = new JTextField(20); \n");
        codigos += ("         text" + name + ".setBounds(" + x + "+50, " + y + ", 85, 30); \n");
        declaraciones += ("         final JButton b" + name + " = new JButton(\"...\"); \n");
        codigos += ("         b" + name + ".setBounds(" + x + "+135, " + y + ", 30, 30); \n");
        codigos += ("         marco.add(label" + name + ");  \n");
        codigos += ("         marco.add(text" + name + ");  \n");

        if (parent instanceof Window) {
           codigos += ("         marco.add(b" + name + ");  \n");
        } else {
            codigos += ("         " +parent.getName()+".add(b" + name + ");  \n"); 
        }


        codigos += ("                b" + name + ".addActionListener(new ActionListener() { \n");
        codigos += ("                        public void actionPerformed(ActionEvent ae) { \n");
        codigos += ("                                text" + name + ".setText(new DatePicker(marco).setPickedDate(\"" + format + "\")); \n");
        codigos += ("                        } \n");
        codigos += ("                }); \n");

        //PICKER


    }

    public void writeButton(String name, String value, int height, int width, int x, int y, Font font, Color backgroundcolor, boolean enable, boolean visible, EventCode event,
            Control parent) {
        declaraciones += ("         final JButton " + name + " = new JButton(\"" + value + "\"); \n");
        codigos += ("         " + name + ".setBounds(" + x + ", " + y + ", " + width + ", " + height + "); \n");

        if (font != null) {
            String fname = "f" + name;
            codigos += ("         Font " + fname + "=new Font(\"" + font.getName() + "\"," + font.getStyle() + "," + font.getSize() + "); \n");

            codigos += ("         " + name + ".setFont( " + fname + " ); \n");


        }
        if (backgroundcolor != null) {
            String cname = "c" + name;
            codigos += ("         Color " + cname + " =new Color(" + backgroundcolor.getRGB() + "); \n");
            codigos += ("         " + name + ".setBackground(" + cname + "); \n");


        }
        if (enable) {
            codigos += ("         " + name + ".setEnabled(true); \n");
        } else {
            codigos += ("         " + name + ".setEnabled(false); \n");

        }
        if (visible) {
            codigos += ("         " + name + ".setVisible(true); \n");
        } else {
            codigos += ("         " + name + ".setVisible(false); \n");


        }

        if (parent instanceof Window) {
           codigos += ("         marco.add(" + name + ");  \n");
        } else {
            codigos += ("         " +parent.getName()+".add(" + name + ");  \n"); 
        }
        if (event.getStatements() != null) {

            codigos += (name + ".addMouseListener(new java.awt.event.MouseAdapter() { \n");
            codigos += ("public void mouseClicked(java.awt.event.MouseEvent evt) { \n");
            //codigo 

            codigos += (GeneradorSentencia.GetCode_SymbolTable(event.getSymbolTable()));

            String r = (GeneradorSentencia.GenerarCodigo(event.getStatements()));
            codigos += r;
            //
            codigos += ("} \n");
            codigos += ("}); \n");
            System.out.print(codigos);
        }
    }

    public void writeLabel(String name, String value, int height, int width, int x, int y, Font font, Color bordercolor, boolean enable, boolean visible,
            Control parent) {
        declaraciones += ("    final JLabel " + name + " = new JLabel(\"" + value + "\"); \n");
        codigos += ("         " + name + ".setBounds(" + x + ", " + y + ", " + width + ", " + height + "); \n");



        if (font != null) {
            String fname = "f" + name;
            codigos += ("         Font " + fname + "=new Font(\"" + font.getName() + "\"," + font.getStyle() + "," + font.getSize() + "); \n");

            codigos += ("         " + name + ".setFont( " + fname + " ); \n");


        }
        if (bordercolor != null) {
            String cname = "c" + name;
            codigos += ("         Color " + cname + " =new Color(" + bordercolor.getRGB() + "); \n");


            codigos += ("         " + name + ".setBorder(BorderFactory.createLineBorder(" + cname + ")); \n");



        }
        if (enable) {
            codigos += ("         " + name + ".setEnabled(true); \n");
        } else {
            codigos += ("         " + name + ".setEnabled(false); \n");

        }
        if (visible) {
            codigos += ("         " + name + ".setVisible(true); \n");
        } else {
            codigos += ("         " + name + ".setVisible(false); \n");

        }


        if (parent instanceof Window) {
           codigos += ("         marco.add(" + name + ");  \n");
        } else {
            codigos += ("         " +parent.getName()+".add(" + name + ");  \n"); 
        }

        /*
        codigos += (name+"addInputMethodListener(new java.awt.event.InputMethodListener() { \n");
        codigos+=("public void caretPositionChanged(java.awt.event.InputMethodEvent evt) { \n");
        codigos+=(" } \n");
        codigos+=("public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) { \n");
        //codigo         
        codigos+=(event.GetCodigo_symbolTable());
        codigos+=(GeneradorSentencia.GenerarCodigo(event.getStatements()));
        //
        codigos+=("} \n");        
        codigos+=("}); \n");        
         */

    }

    public void writeComboBox(String name, int height, int width, int x, int y, ArrayList<ListBoxItem> items, Font font, Color bordercolor, boolean enable, boolean visible, EventCode event, Control parent) {
        declaraciones += ("          final JComboBox " + name + " = new JComboBox(); \n");
        codigos += ("         " + name + ".setBounds(" + x + ", " + y + ", " + width + ", " + height + "); \n");

        if (font != null) {
            String fname = "f" + name;
            codigos += ("         Font " + fname + "=new Font(\"" + font.getName() + "\"," + font.getStyle() + "," + font.getSize() + "); \n");

            codigos += ("         " + name + ".setFont( " + fname + " ); \n");

        }
        if (bordercolor != null) {
            String cname = "c" + name;
            codigos += ("         Color " + cname + " =new Color(" + bordercolor.getRGB() + "); \n");


            codigos += ("         " + name + ".setBorder(BorderFactory.createLineBorder(" + cname + ")); \n");
        }
        if (enable) {
            codigos += ("         " + name + ".setEnabled(true); \n");
        } else {
            codigos += ("         " + name + ".setEnabled(false); \n");

        }
        if (visible) {
            codigos += ("         " + name + ".setVisible(true); \n");
        } else {
            codigos += ("         " + name + ".setVisible(false); \n");

        }

        codigos += ("         marco.add(" + name + "); \n");
        for (int i = 0; i < items.size(); i++) {
            codigos += ("         " + name + ".addItem(\"" + items.get(i).getText() + "\"); \n");

        }
        if (parent instanceof Window) {
           codigos += ("         marco.add(" + name + ");  \n");
        } else {
            codigos += ("         " +parent.getName()+".add(" + name + ");  \n"); 
        }
        if (event.getStatements() != null) {

            codigos += (name + ".addItemListener(new java.awt.event.ItemListener() { \n");
            codigos += ("public void itemStateChanged(java.awt.event.ItemEvent evt) { \n");
            //codigo         
            codigos += (GeneradorSentencia.GetCode_SymbolTable(event.getSymbolTable()));
            codigos += (GeneradorSentencia.GenerarCodigo(event.getStatements()));
            //
            codigos += ("} \n");
            codigos += ("}); \n");
        }
    }

    public void writeTextBox(String name, String value, int height, int width, int x, int y, Font font, Color backgroundcolor, Color bordercolor, boolean enable, boolean visible,
            EventCode event, Control parent) {

        declaraciones += ("          final JTextField " + name + " = new JTextField(\"" + value + "\"); \n");
        codigos += ("         " + name + ".setBounds(" + x + ", " + y + ", " + width + ", " + height + "); \n");




        if (font != null) {
            String fname = "f" + name;
            codigos += ("         Font " + fname + "=new Font(\"" + font.getName() + "\"," + font.getStyle() + "," + font.getSize() + "); \n");
            codigos += ("         " + name + ".setFont( " + fname + " ); \n");

        }
        if (bordercolor != null) {
            String cname = "c" + name;
            codigos += ("         Color " + cname + " =new Color(" + bordercolor.getRGB() + "); \n");


            codigos += ("         " + name + ".setBorder(BorderFactory.createLineBorder(" + cname + ")); \n");

        }
        if (backgroundcolor != null) {
            String cname = "c1" + name;
            codigos += ("         Color " + cname + " =new Color(" + backgroundcolor.getRGB() + "); \n");


            codigos += ("         " + name + ".setBackground(" + cname + "); \n");


        }
        if (enable) {
            codigos += ("         " + name + ".setEnabled(true); \n");
        } else {
            codigos += ("         " + name + ".setEnabled(false); \n");

        }
        if (visible) {
            codigos += ("         " + name + ".setVisible(true); \n");
        } else {
            codigos += ("         " + name + ".setVisible(false); \n");

        }
        if (parent instanceof Window) {
           codigos += ("         marco.add(" + name + ");  \n");
        } else {
            codigos += ("         " +parent.getName()+".add(" + name + ");  \n"); 
        }
        if (event.getStatements() != null) {
            codigos += (name + ".addInputMethodListener(new java.awt.event.InputMethodListener() { \n");
            codigos += ("public void caretPositionChanged(java.awt.event.InputMethodEvent evt) { \n");
            codigos += (" } \n");
            codigos += ("public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) { \n");
            //codigo         
            codigos += (GeneradorSentencia.GetCode_SymbolTable(event.getSymbolTable()));
            codigos += (GeneradorSentencia.GenerarCodigo(event.getStatements()));
            //
            codigos += ("} \n");
            codigos += ("}); \n");
        }

    }

    public void writePanel(String name, int height, int width, int x, int y, Font font, Color backgroundcolor, Color bordercolor, boolean enable, boolean visible,
            Control parent) {

        JPanel j = new JPanel();

        declaraciones += ("          final JPanel " + name + " = new JPanel(); \n");
        codigos += ("         " + name + ".setBounds(" + x + ", " + y + ", " + width + ", " + height + "); \n");




        if (font != null) {
            String fname = "f" + name;
            codigos += ("         Font " + fname + "=new Font(\"" + font.getName() + "\"," + font.getStyle() + "," + font.getSize() + "); \n");

            codigos += ("         " + name + ".setFont( " + fname + " ); \n");


        }

        if (bordercolor != null) {
            String cname = "c" + name;
            codigos += ("         Color " + cname + " =new Color(" + bordercolor.getRGB() + "); \n");


            codigos += ("         " + name + ".setBorder(BorderFactory.createLineBorder(" + cname + ")); \n");

        }
        if (backgroundcolor != null) {
            String cname = "c1" + name;
            codigos += ("         Color " + cname + " =new Color(" + backgroundcolor.getRGB() + "); \n");


            codigos += ("         " + name + ".setBackground(" + cname + "); \n");


        }

        if (enable) {
            codigos += ("         " + name + ".setEnabled(true); \n");
        } else {
            codigos += ("         " + name + ".setEnabled(false); \n");

        }
        if (visible) {
            codigos += ("         " + name + ".setVisible(true); \n");
        } else {
            codigos += ("         " + name + ".setVisible(false); \n");

        }

       if (parent instanceof Window) {
           codigos += ("         marco.add(" + name + ");  \n");
        } else {
            codigos += ("         " +parent.getName()+".add(" + name + ");  \n"); 
        }


    }

    public void writeListBox(ListBox lst, String name, int height, int width, int x, int y, ArrayList<ListBoxItem> items, Font font, Color bordercolor, boolean enable, boolean visible,
            EventCode event, Control parent) {
        JList J = new JList();
        String elementos = "String [] elementos" + name + " = {";
        for (int i = 0; i < items.size(); i++) {
            elementos += "\"" + items.get(i).getText() + "\"";
            if (i == items.size() - 1) {
                elementos += "};\n";
            } else {
                elementos += ",";
            }
        }
        declaraciones += ("         " + elementos);
        declaraciones += ("          final JList " + name + " = new JList(elementos" + name + "); \n");
        codigos += ("        final JScrollPane s" + name + " = new javax.swing.JScrollPane(" + name + ");  \n");


        codigos += ("         s" + name + ".setBounds(" + x + ", " + y + ", " + width + ", " + height + "); \n");
        if (font != null) {
            String fname = "f" + name;
            codigos += ("         Font " + fname + "=new Font(\"" + font.getName() + "\"," + font.getStyle() + "," + font.getSize() + "); \n");

            codigos += ("         " + name + ".setFont( " + fname + " ); \n");


        }
        if (bordercolor != null) {
            String cname = "c" + name;
            codigos += ("         Color " + cname + " =new Color(" + bordercolor.getRGB() + "); \n");

            codigos += ("         " + name + ".setBorder(BorderFactory.createLineBorder(" + cname + ")); \n");

        }
        if (enable) {
            codigos += ("         " + name + ".setEnabled(true); \n");
        } else {
            codigos += ("         " + name + ".setEnabled(false); \n");

        }
        if (visible) {
            codigos += ("         " + name + ".setVisible(true); \n");
        } else {
            codigos += ("         " + name + ".setVisible(false); \n");

        }
        
        if (parent instanceof Window) {
           codigos += ("         marco.add(s" + name + ");  \n");
        } else {
            codigos += ("         " +parent.getName()+".add(s" + name + ");  \n"); 
        }


        if (event.getStatements() != null) {
            codigos += (name + ".addListSelectionListener(new  ListSelectionListener()  {\n");
            codigos += ("  public void valueChanged(ListSelectionEvent e) {\n");
            if (lst.getItemChanged().getSymbolTable() != null) {
                //codigo         
                codigos += (GeneradorSentencia.GetCode_SymbolTable(event.getSymbolTable()));
                codigos += (GeneradorSentencia.GenerarCodigo(event.getStatements()));
                //
                codigos += ("} \n");
                codigos += ("}); \n");
            }
        }



    }

    public void writeCheckBox(String name, String text, int direction, int height, int width, int x, int y, Font font, Color bordercolor, boolean enable, boolean visible, boolean checked, EventCode event, Control parent) {

        declaraciones += ("          final JCheckBox " + name + " = new JCheckBox(\"" + text + "\"); \n");
        codigos += ("         " + name + ".setBounds(" + x + ", " + y + ", " + width + ", " + height + "); \n");
        //codigos+=("         " + name + ".setHorizontalTextPosition(1); \n");
        if (font != null) {
            String fname = "f" + name;
            codigos += ("         Font " + fname + "=new Font(\"" + font.getName() + "\"," + font.getStyle() + "," + font.getSize() + "); \n");

            codigos += ("         " + name + ".setFont( " + fname + " ); \n");

        }
        if (bordercolor != null) {
            String cname = "c" + name;
            codigos += ("         Color " + cname + " =new Color(" + bordercolor.getRGB() + "); \n");


            codigos += ("         " + name + ".setBorder(BorderFactory.createLineBorder(" + cname + ")); \n");
        }
        if (enable) {
            codigos += ("         " + name + ".setEnabled(true); \n");
        } else {
            codigos += ("         " + name + ".setEnabled(false); \n");

        }
        if (visible) {
            codigos += ("         " + name + ".setVisible(true); \n");
        } else {
            codigos += ("         " + name + ".setVisible(false); \n");

        }
        if (checked) {
            codigos += ("         " + name + ".setSelected(true); \n");
        } else {
            codigos += ("         " + name + ".setSelected(false); \n");

        }
        if (parent instanceof Window) {
           codigos += ("         marco.add(" + name + ");  \n");
        } else {
            codigos += ("         " +parent.getName()+".add(" + name + ");  \n"); 
        }
        if (event.getStatements() != null) {

            codigos += (name + ".addActionListener(new ActionListener() {\n");
            codigos += (" public void actionPerformed(ActionEvent e)  {\n");
            //codigo         
            codigos += (GeneradorSentencia.GetCode_SymbolTable(event.getSymbolTable()));
            codigos += (GeneradorSentencia.GenerarCodigo(event.getStatements()));
            //
            codigos += ("} \n");
            codigos += ("}); \n");
        }

    }

    public void writeRadioBox(String name, String text, int direction, int height, int width, int x, int y, Font font, Color bordercolor, boolean enable, boolean visible, boolean checked,
            EventCode event, Control parent) {

        declaraciones += ("          final JRadioButton " + name + " = new JRadioButton(\"" + text + "\"); \n");
        codigos += ("         " + name + ".setBounds(" + x + ", " + y + ", " + width + ", " + height + "); \n");
        //codigos+=("         " + name + ".setHorizontalTextPosition(1); \n");


        if (font != null) {
            String fname = "f" + name;
            codigos += ("         Font " + fname + "=new Font(\"" + font.getName() + "\"," + font.getStyle() + "," + font.getSize() + "); \n");

            codigos += ("         " + name + ".setFont( " + fname + " ); \n");

        }
        if (bordercolor != null) {
            String cname = "c" + name;
            codigos += ("         Color " + cname + " =new Color(" + bordercolor.getRGB() + "); \n");
            codigos += ("         " + name + ".setBorder(BorderFactory.createLineBorder(" + cname + ")); \n");
        }
        if (enable) {
            codigos += ("         " + name + ".setEnabled(true); \n");
        } else {
            codigos += ("         " + name + ".setEnabled(false); \n");
        }
        if (visible) {
            codigos += ("         " + name + ".setVisible(true); \n");
        } else {
            codigos += ("         " + name + ".setVisible(false); \n");

        }
        if (checked) {
            codigos += ("         " + name + ".setSelected(true); \n");
        } else {
            codigos += ("         " + name + ".setSelected(false); \n");
        }
        if (parent instanceof Window) {
           codigos += ("         marco.add(" + name + ");  \n");
        } else {
            codigos += ("         " +parent.getName()+".add(" + name + ");  \n"); 
        }
        if (event.getStatements() != null) {
            codigos += (name + ".addActionListener(new ActionListener() {\n");
            codigos += (" public void actionPerformed(ActionEvent e)  {\n");
            //codigo         
            codigos += (GeneradorSentencia.GetCode_SymbolTable(event.getSymbolTable()));
            codigos += (GeneradorSentencia.GenerarCodigo(event.getStatements()));
            //
            codigos += ("} \n");
            codigos += ("}); \n");
        }



    }

    public void writePicture(String name, String path, int height, int width, int x, int y, Color bordercolor, boolean enable, boolean visible, EventCode event, Control parent) {

        declaraciones += ("          final ImageIcon i" + name + " = new ImageIcon(\"" + path + "\"); \n");

        declaraciones += ("          final JLabel " + name + " = new JLabel(i" + name + "); \n");
        codigos += ("         " + name + ".setBounds(" + x + ", " + y + ", " + width + ", " + height + "); \n");
        //codigos+=("         " + name + ".setHorizontalTextPosition(1); \n");

        if (bordercolor != null) {
            String cname = "c" + name;
            codigos += ("         Color " + cname + " =new Color(" + bordercolor.getRGB() + "); \n");


            codigos += ("         " + name + ".setBorder(BorderFactory.createLineBorder(" + cname + ")); \n");
        }
        if (enable) {
            codigos += ("         " + name + ".setEnabled(true); \n");
        } else {
            codigos += ("         " + name + ".setEnabled(false); \n");

        }
        if (visible) {
            codigos += ("         " + name + ".setVisible(true); \n");
        } else {
            codigos += ("         " + name + ".setVisible(false); \n");

        }

        if (parent instanceof Window) {
           codigos += ("         marco.add(" + name + ");  \n");
        } else {
            codigos += ("         " +parent.getName()+".add(" + name + ");  \n"); 
        }
        if (event.getStatements() != null) {

            codigos += (name + "addMouseListener(new java.awt.event.MouseAdapter() { \n");
            codigos += ("public void mouseClicked(java.awt.event.MouseEvent evt) { \n");
            codigos += (GeneradorSentencia.GetCode_SymbolTable(event.getSymbolTable()));
            codigos += (GeneradorSentencia.GenerarCodigo(event.getStatements()));
            //
            codigos += ("} \n");
            codigos += ("}); \n");
        }


    }

    public void finish() {
        try {
            wr.write(declaraciones);
            wr.write(codigos);
            wr.write("          marco.setResizable(false); \n");
            wr.write("          marco.setVisible(true); \n");
            wr.write("      }\n");

            wr.write("  }\n");
            wr.close();

            bw.close();



        } catch (Exception e) {
        }

    }

    public void compile() throws IOException {


        String comando = "javac " + (this.f.getName());
        // Ejcutamos el comando
        Runtime.getRuntime().exec(comando);

    }

    public void run() throws IOException {
        String s = null;

        String comando = "java " + this.onlyname(this.f.getName());
        // Ejcutamos el comando
        Runtime.getRuntime().exec(comando);




    }

    public String onlyname(String name) {
        String r = "";
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '.') {
                break;
            }
            r += (name.charAt(i));

        }
        return r;

    }

    public void writeGlobal() {
        wr.write("class global {  \n");
        wr.write("    public static  void Msgbox(String caption, String message) \n");
        wr.write("  {\n");
        wr.write(" JOptionPane.showMessageDialog( null, message,    caption, JOptionPane.INFORMATION_MESSAGE ); ");
        wr.write("    } \n");

        wr.write("   public static void OpenWindow(String namewindow){  ");
        wr.write("      try {        ");
        wr.write("  Runtime.getRuntime().exec(\"java \" + namewindow);    \n");
        wr.write("      } catch (Exception e) {  \n");
        wr.write("       }       \n");
        wr.write("    }   \n");

        // <editor-fold defaultstate="collapsed" desc="Funcion build in">    

        wr.write("    public static int GetDay() \n");
        wr.write("   { \n");
        wr.write("        Date date = new Date(); \n");
        wr.write("        return date.getDay(); \n");
        wr.write("    } \n");
        wr.write("    public static int GetMonth() \n");
        wr.write("    { \n");
        wr.write("       Date date = new Date(); \n");
        wr.write("        return date.getMonth(); \n");
        wr.write("    } \n");
        wr.write("    public static int GetYear() \n");
        wr.write("   { \n");
        wr.write("        Date date = new Date(); \n");
        wr.write("        return date.getYear(); \n");
        wr.write("    } \n");
        wr.write("    public static Date GetDate() \n");
        wr.write("    { \n");
        wr.write("        return new Date(); \n");
        wr.write("    } \n");

        wr.write("    public static String BooleanToString(boolean b) \n");
        wr.write("    { \n");
        wr.write("        if (b) { \n");
        wr.write("            return \"True\"; \n");
        wr.write("        } \n");
        wr.write("        return \"False\"; \n");
        wr.write("    } \n");


        wr.write("    public static boolean StringToBoolean(String s) \n");
        wr.write("    { \n");
        wr.write("        if (s.equals(\"True\")) { \n");
        wr.write("            return true; \n");
        wr.write("        } \n");
        wr.write("        return false; \n");
        wr.write("     } \n");

        wr.write("  public static char StringToChar(String c) \n");
        wr.write("    { \n");
        wr.write("        char a = c.charAt(0); \n");
        wr.write("        return a; \n");
        wr.write("    } \n");

        wr.write("    public static Date StringToDate(String c) \n");
        wr.write("    { \n");
        wr.write("        Date fecha=new Date(); \n");
        wr.write("        String dia = c.charAt(0)+\"\"+c.charAt(1); \n");
        wr.write("        String mes = c.charAt(2)+\"\"+c.charAt(3); \n");
        wr.write("        String anio = c.charAt(4)+\"\"+c.charAt(5)+\"\"+c.charAt(6)+\"\"+c.charAt(7); \n");
        wr.write("        int dian= StringToInt(dia); \n");
        wr.write("                int mesn= StringToInt(mes); \n");
        wr.write("                        int anio2= StringToInt(anio); \n");
        wr.write("        fecha.setDate(dian); \n");
        wr.write("        fecha.setMonth(mesn); \n");
        wr.write("        fecha.setYear(anio2); \n");

        wr.write("                return fecha; \n");
        wr.write("    }     \n");

        wr.write("   public static float StringToFloat(String valor) \n");
        wr.write("   {        \n");
        wr.write("       return Float.parseFloat(valor);       \n");
        wr.write("   } \n");


        wr.write("   public static int StringToInt(String valor) \n");
        wr.write("   { \n");
        wr.write("       return Integer.parseInt(valor); \n");
        wr.write("   } \n");


        wr.write("    public static int CharToInt(char c) \n");
        wr.write("    { \n");
        wr.write("        int n = c-48; \n");
        wr.write("        return n; \n");
        wr.write("    } \n");


        wr.write("    public static String CharToString(char c)\n");
        wr.write("    { \n");
        wr.write("        String n  = \"\"+c+\"\"; \n");
        wr.write("        return n;         \n");
        wr.write("    } \n");


        wr.write("    public static String DateToString(Date d) \n");
        wr.write("    { \n");
        wr.write("        return d.toString(); \n");
        wr.write("    } \n");


        wr.write("    public static int FloatToInt(float valor) \n");
        wr.write("    { \n");

        wr.write("        return (int)Math.round(valor); \n");
        wr.write("    }     \n");

        wr.write("    public static String FloatToString(float valor) \n");
        wr.write("    { \n");
        wr.write("        return String.valueOf(Math.round(valor)); \n");
        wr.write("    }    \n");


        wr.write("    public static float IntegerToFloat(int valor) \n");
        wr.write("    {\n");
        wr.write("        float v=(float) (valor*1.0); \n");
        wr.write("        return v; \n");
        wr.write("    }     \n");



        wr.write("   public static String IntegerToString(int valor) \n");
        wr.write("    {\n");

        wr.write("        return String.valueOf(valor); \n");
        wr.write("    }     \n");



        wr.write("    public static boolean IntegerToBoolean(Integer i)  \n");
        wr.write("    { \n");
        wr.write("        if (i == 0) { \n");
        wr.write("            return false; \n");
        wr.write("        } \n");
        wr.write("        return true; \n");
        wr.write("    }    \n");



        wr.write("    public static char IntegerToChar(int c)\n");
        wr.write("    { \n");
        wr.write("        char p=' '; \n");
        wr.write("       if (c >= 0 && c <= 9) { \n");
        wr.write("         p= Character.forDigit(c, 10); \n");

        wr.write("        } \n");
        wr.write("       return p;\n");
        wr.write("    }     \n");


        //</editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Funcion build in de Control">        
        wr.write("    public Color GetBorderColor(JComponent c) {\n");

        wr.write("        return c.getForeground();\n");

        wr.write("    }\n");
        wr.write("    public void SetBorderColor(JComponent c,Color borderColor) {\n");
        wr.write("        c.setForeground(borderColor);\n");
        wr.write("    }\n");

        wr.write("    public boolean IsEnabled(JComponent c) {\n");
        wr.write("        return c.isEnabled();\n");
        wr.write("    }\n");

        wr.write("    public void SetEnabled(JComponent c,boolean enabled) {\n");
        wr.write("        c.setEnabled(enabled);\n");
        wr.write("    }\n");

        wr.write("    public boolean IsVisible(JComponent c) { \n");
        wr.write("        return c.isVisible(); \n");
        wr.write("    } \n");

        wr.write("    public void SetVisible(JComponent c,boolean visible) {\n");
        wr.write("        c.setVisible(visible);\n");
        wr.write("    }\n");

        // public ArrayList<Control> GetChildren(JComponent c) {
        //TODO

        //   return null;
        // }

        wr.write("    public void SetChildren(JComponent c,ArrayList<JComponent> children) { \n");
        //TODO
        //c.setChildren(children);

        wr.write("    } \n");

        wr.write("    public Font GetFont(JComponent c) {\n");
        //TODO
        wr.write("        return null;\n");
        wr.write("    }\n");

        //public void SetFont(Control c,Font font) {
        //TODO
        //c.setFont(font);
        // }

        wr.write("    public int GetHeight(JComponent c) { \n");
        wr.write("        return c.getHeight();\n");
        wr.write("    }\n");

        wr.write("    public void SetHeight(JComponent c,int height) {\n");
        wr.write("        c.setBounds(c.getX(),c.getY(),c.getWidth(),height); \n");

        wr.write("    }\n");

        wr.write("    public String GetName(JComponent c) { \n");
        wr.write("        return c.getName(); \n");
        wr.write("    } \n");

        wr.write("    public void SetName(JComponent c,String name) { \n");
        wr.write("        c.setName(name);\n");
        wr.write("    }\n");

        //public JComponent getParent(JComponent c) {
        //    return c.getParent();
        //}

        //public void setParent(JComponent c,JComponent parent) {
        //    c.setParent(parent);
        //}

        //public int GetTabIndex(JComponent c) {
        //TODO
        //  return 0;
        //}

        //public void SetTabIndex(JComponent c,int tabIndex) {
        //TODO
        //c.setTabIndex(tabIndex);
        //}

        wr.write("    public int GetWidth(JComponent c) { \n");
        wr.write("        return c.getWidth(); \n");
        wr.write("    } \n");

        wr.write("    public void SetWidth(JComponent c,int width) { \n");
        wr.write("        c.setBounds(c.getX(),c.getY(),width,c.getHeight());\n");
        wr.write("    } \n");

        wr.write("    public int GetX(JComponent c) { \n");
        wr.write("         return c.getX();\n");
        wr.write("    }\n");

        wr.write("    public void SetX(JComponent c,int x) {\n");
        wr.write("        c.setBounds(x,c.getY(),c.getWidth(),c.getHeight());\n");
        wr.write("   }\n");

        wr.write("    public int GetY(JComponent c) {\n");
        wr.write("       return c.getY();\n");
        wr.write("    }\n");

        wr.write("    public void SetY(JComponent c,int y) {\n");
        wr.write("        c.setBounds(c.getX(),y,c.getWidth(),c.getHeight());\n");
        wr.write("    }\n");


        // </editor-fold>     

        // <editor-fold defaultstate="collapsed" desc="Funcion build in de Boton">    

        wr.write("    public String GetValue(JButton btn) \n");
        wr.write("    {\n");
        wr.write("       return btn.getText(); \n");
        wr.write("    }\n");

        wr.write("    public void SetValue(JButton btn,String value) {\n");
        wr.write("        btn.setText(value);\n");
        wr.write("    }           \n");
        // </editor-fold>    



        // <editor-fold defaultstate="collapsed" desc="Funcion build in de Checwr.write("Box">         

        wr.write("   public int GetDirection(JCheckBox c) { \n");
        //to do
        wr.write("        return c.getHorizontalTextPosition();\n");
        wr.write("    }\n");

        //public void SetDirection(JChecwr.write("Box c,int direction) { //0 es izquierdo y 1 es derecho
        //   c.setDirection(direction);
        //}

        wr.write("    public String GetText(JCheckBox c) {\n");
        wr.write("        return c.getText();\n");
        wr.write("    }\n");

        wr.write("    public void SetText(JCheckBox c,String text) {\n");
        wr.write("        c.setText(text);\n");
        wr.write("    }\n");

        wr.write("    public boolean IsChecked(JCheckBox c) {\n");
        wr.write("        if(c.isSelected())\n");
        wr.write("        {\n");
        wr.write("        return true;\n");
        wr.write("        }else{\n");

        wr.write("       return false;\n");
        wr.write("       }\n");

        wr.write("    }\n");

        wr.write("    public void SetChecked(JCheckBox c,boolean checked) {\n");
        wr.write("        c.setSelected(checked);\n");
        wr.write("    }    \n");

        // </editor-fold> 


        // <editor-fold defaultstate="collapsed" desc="Funcion build in de Combobox">        
        wr.write("    public ArrayList<Object> GetItems(JComboBox cmb) \n");
        wr.write("    {\n");

        wr.write("      ArrayList<Object> items=new ArrayList<Object>();\n");
        wr.write("       for ( int i = 0;  i < cmb.getItemCount(); i++) \n");
        wr.write("       {\n");
        wr.write("            items.add(cmb.getItemAt(i));\n");
        wr.write("       }           \n");
        wr.write("      return items;  \n");
        wr.write("    }\n");

        wr.write("    public void SetItems(JComboBox cmb,ArrayList<Object> items) \n");
        wr.write("    {\n");
        wr.write("        for(int i=0;i<items.size();i++)\n");
        wr.write("        {\n");
        wr.write("            cmb.addItem(items.get(i));\n");
        wr.write("        }\n");
        wr.write("    }\n");

        wr.write("   public int GetSelectedIndex(JComboBox cmb,int n) \n");
        wr.write("    {\n");
        wr.write("       cmb.setSelectedIndex(n);\n");
        wr.write("       return n;               \n");
        wr.write("    }\n");

        wr.write("    public void SetSelectedIndex(JComboBox cmb,int selectedIndex) \n");
        wr.write("    {        \n");
        wr.write("        cmb.setSelectedIndex(selectedIndex);\n");
        wr.write("    }\n");


        wr.write("    public void SetSelectedItem(JComboBox cmb,Object selectedItem) \n");
        wr.write("    {\n");
        wr.write("        cmb.setSelectedItem(selectedItem);                \n");
        wr.write("    }\n");

        wr.write("    public Object GetSelectedItem(JComboBox cmb,Object n) \n");
        wr.write("    {\n");
        wr.write("       cmb.setSelectedItem(n);\n");
        wr.write("       return n;     \n");
        wr.write("    }        \n");


        // </editor-fold>     

        // <editor-fold defaultstate="collapsed" desc="Funcion build in de DatePicwr.write("er">            
        wr.write("    public String getDateFormat() {\n");
        wr.write("        return null;\n");
        wr.write("    }\n");

        wr.write("   public void setDateFormat(String dateFormat) {\n");
        wr.write("        //TODO\n");
        wr.write("    }\n");

        wr.write("   public Date getMinValue() {\n");
        wr.write("        return null;\n");
        wr.write("    }\n");

        wr.write("    public void setMinValue(Date minValue) {\n");
        wr.write("        //TODO\n");
        wr.write("    }\n");

        wr.write("    public Date getValue() {\n");
        wr.write("        return null;\n");
        wr.write("    }\n");

// </editor-fold>    


        // <editor-fold defaultstate="collapsed" desc="Funcion build in de Label">            
        wr.write("     public String GetValue(JLabel l) {\n");
        wr.write("        return l.getText();\n");
        wr.write("    }\n");

        wr.write("    public void SetValue(JLabel l,String value) {\n");
        wr.write("        l.setText(value);\n");
        wr.write("    }    \n");

// </editor-fold>    


        // <editor-fold defaultstate="collapsed" desc="Funcion build in de Picture">                
        wr.write("    public String GetPath(JLabel l) {\n");
        wr.write("        return ((ImageIcon)l.getIcon()).getDescription() ;\n");
        wr.write("    }\n");

        wr.write("    public void SetPath(JLabel l,String path) {\n");
        wr.write("        //TODO\n");
        wr.write("        l.setIcon(new ImageIcon(path,path));\n");
        wr.write("    }\n");

        wr.write("    public int GetSizeType() {\n");
        wr.write("        return 0;\n");
        wr.write("    }\n");

        wr.write("    public void SetSizeType(int sizeType) {\n");
        wr.write("        //TODO\n");
        wr.write("    }  \n");


        // </editor-fold>   


        // <editor-fold defaultstate="collapsed" desc="Funcion build in de RadioButton">                    

        wr.write("    public int GetDirection(JRadioButton r) {\n");
        wr.write("        return r.getHorizontalTextPosition();\n");
        wr.write("    }\n");

        wr.write("    public void SetDirection(JRadioButton r,int direction) { \n");
        //0 es izquierdo y 1 es derecho \n");
        wr.write("        r.setHorizontalTextPosition(direction);\n");
        wr.write("    }\n");

        wr.write("    public String GetText(JRadioButton r) {\n");
        wr.write("        return r.getText();\n");
        wr.write("    }\n");

        wr.write("    public void SetText(JRadioButton r,String text) {\n");
        wr.write("       r.setText(text);\n");
        wr.write("    }\n");

        wr.write("    public boolean IsChecked(JRadioButton r) {\n");
        wr.write("         if(r.isSelected())\n");
        wr.write("        {\n");
        wr.write("        return true;\n");
        wr.write("        }else{\n");

        wr.write("       return false;\n");
        wr.write("        }\n");
        wr.write("    }\n");

        wr.write("    public void SetChecked(JRadioButton r,boolean checked) { \n");
        wr.write("      r.setSelected(checked); \n");
        wr.write("    }\n");

        //</editor-fold>



        // <editor-fold defaultstate="collapsed" desc="Funcion build in de Textbox">                
        wr.write("    public boolean IsReadOnly(JTextField t) {\n");
        wr.write("        return t.isEnabled();\n");
        wr.write("    }\n");

        wr.write("   public void SetReadOnly(JTextField t,boolean readOnly) {\n");
        wr.write("        t.setEnabled(readOnly);\n");
        wr.write("   }\n");


        wr.write("    public String GetValue(JTextField t) {\n");
        wr.write("        return t.getText();\n");
        wr.write("    }\n");

        wr.write("    public void SetValue(JTextField t,String value) {\n");
        wr.write("        t.setText(value);\n");
        wr.write("    }\n");

        //</editor-fold>   

        wr.write("}\n");


    }
}
