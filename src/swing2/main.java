/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import Semantics.Controls.*;
import Semantics.Information.EventCode;
import Semantics.Statements.If;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import Semantics.Expression.*;
import Semantics.Expression.Literals.*;
import Semantics.Expression.Operators.GreaterThan;
import Semantics.Statements.CallFunctionStatement;

/**
 *
 * @author josh
 */
public class main {
    
    public static void main(String[] args) throws IOException 
    {
                            
        /*
        System.out.println("Hello Josh");
        Window wn = new Window();
        File f = new File("jaime.java");
        manageFile mf = new manageFile(f);
        
        mf.writeWIndow("hola soy ventana", 400, 600);
        
        
        java.awt.Font f1 = new java.awt.Font("serif", java.awt.Font.ITALIC, 14);
        mf.writeButton("jaime1", "Agregar", 20, 100, 20, 100, f1, Color.red, true, true);
        
        java.awt.Font f2 = new java.awt.Font("serif", java.awt.Font.BOLD, 16);
        mf.writeLabel("label1", "hOLA COM ESTAS?", 20, 300, 20, 120, f2, Color.yellow, true, true);
        
        java.awt.Font f11 = new java.awt.Font("serif", java.awt.Font.BOLD, 16);
        //Alto, ancho, x,y
        mf.writeTextBox("texto1", "texto jj", 20, 300, 60, 160, f11, Color.white, Color.blue, true, true);
         mf.writePicture("imagen1", "bb.jpg", 50, 50, 260, 260, Color.red, true, true);
        
        ArrayList<ListBoxItem> m = new ArrayList<ListBoxItem>();
        
        ListBoxItem t = new ListBoxItem();
        t.setText("OP1");
        
        m.add(t);
        t = new ListBoxItem();
        t.setText("OP2");
        m.add(t);
        
        t = new ListBoxItem();
        t.setText("OP33");
        m.add(t);
        //Alto, ancho, x,y
        //ComboBox c =new ComboBox();
        //c.getFont();
        java.awt.Font f3 = new java.awt.Font("monospaced", java.awt.Font.BOLD, 22);
        mf.writeComboBox("combo9", 20, 80, 10, 10, m, f3, Color.blue, true, true);
        
        mf.writeListBox("Lista", 40, 80, 50, 200, m, f3, Color.yellow, true, true);
        
        mf.writeCheckBox("jjjj", "male", 1,40, 100, 150,300, f2, Color.orange, true, true, true);
        
        mf.writeRadioBox("j1", "radio111", 1,40, 100, 180,350, f3, Color.orange, true, true, true);
        
        mf.writePanel("jpan", 20, 20, 100, 380, f3, Color.darkGray, Color.yellow, true, true);
        mf.writeDate("midate", "fech","dd/MM/yyyy", 30, 100, 50, 450, null, null, true, true);
        
        mf.finish();
        
        
        System.out.print("Escrito");
        try {
            mf.compile();
            System.out.print("Compilado");
        } catch (IOException e) {
            System.out.print(e.getStackTrace());
            
        }
        
         try {
           mf.run();
            System.out.print("Ejecutado");
        } catch (IOException e) {
            System.out.print(e.getStackTrace());
            
        }
        */
                        Font font = new Font();
            font.setFont(new java.awt.Font("Monospaced",java.awt.Font.BOLD,18));
            Window window = new Window();            
            window.setName("main");
            window.setTitle("Hola Example");
            window.setFont(font);
            window.getFont().setBackground(Color.DARK_GRAY);
            window.setWidth(300);
            window.setHeight(200);
            ArrayList<Control> list = new ArrayList<Control>();
            Button btn = new Button();
            btn.setParent(window);
            btn.setName("btn");
            btn.setValue("Click ME!");
            btn.setWidth(100);
            btn.setHeight(50);
            btn.setX(200);
            btn.setY(275);
            btn.setFont(font);
            
            btn.setVisible(true);
            btn.setEnabled(true);
            ArrayList<Expression> _paramss = new ArrayList<Expression>();
            _paramss.add(new Variable("btn",null));
            ArrayList<Expression> paramss = new ArrayList<Expression>();
            paramss.add(new String_Lit("Hola"));
            paramss.add(new CallFunction("GetValue", _paramss, null));
            ArrayList<Expression> paramsss = new ArrayList<Expression>();
            paramsss.add(new String_Lit("main"));
            btn.setClick(new EventCode(new If(new GreaterThan(new Integer_Lit(7), new Integer_Lit(4)),new CallFunctionStatement("OpenWindow", paramsss, new CallFunctionStatement("MsgBox", paramss, null)),null,null),null,""));
            list.add(btn);
            TextBox txt1 = new TextBox();
            txt1.setParent(window);
            txt1.setName("txt1");
            txt1.setValue("");
            txt1.setWidth(200);
            txt1.setHeight(50);
            txt1.setX(200);
            txt1.setY(50);
            txt1.setFont(font);
            txt1.setVisible(true);
            txt1.setEnabled(true);
            list.add(txt1);
            TextBox txt2 = new TextBox();
            txt2.setParent(window);
            txt2.setName("txt2");
            txt2.setValue("");
            txt2.setWidth(200);
            txt2.setHeight(50);
            txt2.setX(100);
            txt2.setY(125);
            txt2.setFont(font);
            txt2.setVisible(true);
            txt2.setEnabled(true);
            list.add(txt2);
            TextBox txt3 = new TextBox();
            txt3.setParent(window);
            txt3.setName("txt3");
            txt3.setValue("");
            txt3.setWidth(200);
            txt3.setHeight(50);
            txt3.setX(100);
            txt3.setY(200);
            txt3.setVisible(true);
            txt3.setEnabled(true);
            txt3.setFont(font);
            list.add(txt3);
            Label lbl = new Label();
            lbl.setParent(window);
            lbl.setName("lbl");
            lbl.setValue("A: ");
            lbl.setWidth(75);
            lbl.setHeight(50);
            lbl.setX(100);
            lbl.setY(50);            
            Font lblFont = new Font();
            lblFont.setFont(new java.awt.Font("Monospaced",java.awt.Font.BOLD,18));
            lblFont.setForeground(Color.BLUE);
            lbl.setBorderColor(Color.GREEN);
            lbl.setFont(lblFont);
            lbl.setVisible(true);
            lbl.setEnabled(true);
            list.add(lbl);
            /*
            Panel pnl = new Panel();
            pnl.setParent(window);
            pnl.setName("pnl");
            pnl.setWidth(400);
            pnl.setHeight(200);
            pnl.setX(450);
            pnl.setY(50);
            Font pnlFont = new Font();
            pnlFont.setFont(new java.awt.Font("Monospaced",java.awt.Font.BOLD,18));
            pnl.setFont(pnlFont);
            pnl.getFont().setBackground(Color.RED);
            pnl.setVisible(true);
            */ 
            ArrayList<Control> listRadios = new ArrayList<Control>();
            RadioButton rd1 = new RadioButton();
            rd1.setParent(window);
            rd1.setName("rd1");
            rd1.setText("MASCULINO");
            rd1.setWidth(25);
            rd1.setHeight(25);
            rd1.setX(475);
            rd1.setY(60);
            rd1.setVisible(true);
            rd1.setEnabled(true);
            rd1.setFont(font);
            listRadios.add(rd1);
            RadioButton rd2 = new RadioButton();
            rd2.setParent(window);
            rd2.setName("rd2");
            rd2.setText("FEMENINO");
            rd2.setWidth(25);
            rd2.setHeight(25);
            rd2.setX(475);
            rd2.setY(110);
            rd2.setVisible(true);
            rd2.setEnabled(true);
            rd2.setFont(font);
            listRadios.add(rd2);
            RadioButton rd3 = new RadioButton();
            rd3.setParent(window);
            rd3.setName("rd3");
            rd3.setText("NONE");
            rd3.setWidth(25);
            rd3.setHeight(25);
            rd3.setX(475);
            rd3.setY(160);
            rd3.setVisible(true);
            rd3.setEnabled(true);
            rd3.setFont(font);
            listRadios.add(rd3);
            //pnl.setChildren(listRadios);
            //list.add(pnl);
            list.add(rd3);
            list.add(rd2);
            list.add(rd1);
            window.setChildren(list);
            ArrayList<Window> listaVentanas = new ArrayList<Window>();
            listaVentanas.add(window);
            //Program tree = new Program(listaVentanas, 0);
           // renderWindows(listaVentanas, 0);
        
    }
    public static void renderWindows(ArrayList<Window> ventanas,int indice) throws IOException 
    {        
        boolean isMain=false;
        
        for(int i=0;i<ventanas.size();i++)
        {
            File f=new File(ventanas.get(i).getName()+".java");
            if(i==indice)
            {
                
            }
            renderVentanas ren=new renderVentanas(f,ventanas.get(i),isMain);                        
        }     
        for(int i=0;i<ventanas.size();i++)
        {
            File f=new File(ventanas.get(i).getName()+".java");
            renderVentanas ren=new renderVentanas(f,ventanas.get(i),isMain);                        
            if(i==indice)
            {
                manageFile man=ren.getMf();
                man.run();
            }
            
            
        }     

        
        
    }     
    
}