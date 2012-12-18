
package Swing;
import Semantics.Controls.*;

import Semantics.Expression.CallFunction;
import Semantics.Expression.Expression;
import Semantics.Expression.Literals.*;
import Semantics.Expression.Operators.*;
import Semantics.Expression.Variable;
import Semantics.Information.EventCode;
import Semantics.Statements.*;
import java.awt.Color;
import java.util.ArrayList;



public class Argus {

    public static void main(String args[]) throws Exception {
        
        Window wd = new Window();
        wd.setName("principal");
        wd.setTitle("Test Ventana");
        wd.setHeight(700);
        wd.setWidth(500);
        Button bt = new Button();
        bt.setName("boton1");
       bt.setValue("este es el ke pruebo");
       
     
       bt.setX(0);
        bt.setY(200);
        bt.setHeight(90);
        bt.setWidth(90);
         ArrayList<Expression> _paramss = new ArrayList<Expression>();
            _paramss.add(new Variable("btn",null));
            ArrayList<Expression> paramss = new ArrayList<Expression>();
            paramss.add(new String_Lit("Hola"));
            paramss.add(new CallFunction("GetValue", _paramss, null));
            ArrayList<Expression> paramsss = new ArrayList<Expression>();
            paramsss.add(new String_Lit("main"));
        bt.setClick(new EventCode(new 
                If(new GreaterThan(new Integer_Lit(7), new Integer_Lit(4)),
                    new CallFunctionStatement("MsgBox",paramss,null),null,null),null,""));
        Label lb = new Label();
        lb.setName("Label1");
        lb.setValue(("Ya esta ccccvvvvvvvvv  fsfs   Listo el Label"));
        lb.setX(50);
        lb.setY(-10);
        lb.setHeight(400);
        lb.setWidth(400);
        Color rojo; 
        rojo = new Color(255,0,0);
        TextBox tb =new TextBox();
        tb.setName("txt1");
        tb.setValue("Cabeza");
        tb.setX(100);
        tb.setY(300);
        tb.setHeight(200);
        tb.setWidth(100);
        tb.setBorderColor(rojo);
        tb.setEnabled(true);
        Font F = new Font();
        F.setForeground(rojo);
        rojo = new Color(0,255,0);
        F.setBackground(rojo);
        F.setFont(new java.awt.Font("Tahoma", 1, 16));
        lb.setFont(F);
        tb.setFont(F);
        ArrayList<Control> cl = new ArrayList<Control>();
        ComboBox cb =new ComboBox();
        ListBox lib = new ListBox();
        ArrayList<ListBoxItem> itms = new ArrayList<ListBoxItem>();
        ListBoxItem l1= new ListBoxItem();
        l1.setText("hola1");
        l1.setValue("Leonel");
        
        ListBoxItem l2= new ListBoxItem();
        l2.setText("prueba");
        l2.setValue("Walter");
        itms.add(l1);
        itms.add(l2);
        cb.setItems(itms);
        cb.setFont(F);
        cb.setWidth(80);
        cb.setHeight(70);
        cb.setX(12);
        cb.setY(22);
        cb.setName("combito");
        
        
        lib.setItems(itms);
        lib.setFont(F);
        lib.setWidth(80);
        lib.setHeight(70);
        lib.setX(45);
        lib.setY(22);
        lib.setName("Listita");
        lib.setEnabled(true);
        
        //itms.add();
        Panel p = new Panel();
        p.setName("panelito");
        ArrayList<Control> hPanel= new ArrayList<Control>();
        CheckBox ck= new CheckBox();
        ck.setText("checkUno");
        ck.setName("cheke");
         CheckBox ck2= new CheckBox();
        ck2.setText("checkdos");
        ck2.setName("cheke2");
        RadioButton rb = new RadioButton();
        rb.setName("RB1");
        rb.setText("elija este");
        rb.setFont(F);
        rb.setX(45);
        rb.setY(250);
        rb.setWidth(300);
        rb.setHeight(50);
        rb.setEnabled(true);
        RadioButton rb2 = new RadioButton();
        rb2.setName("RB2");
        rb2.setText("radio___");
        rb2.setFont(F);
        rb2.setX(45);
        rb2.setEnabled(true);
        rb2.setY(200);
        rb2.setWidth(300);
        rb2.setHeight(50);
        hPanel.add(ck);
        hPanel.add(ck2);
        hPanel.add(rb);
        hPanel.add(rb2);
        p.setBorderColor(rojo);
        p.setChildren(hPanel);
        p.setHeight(190);
        p.setWidth(300);
        p.setX(30);
        p.setY(80);
        cl.add(p);
        DatePicker dp = new DatePicker();
        dp.setDateFormat("ddmmyyyy");
        dp.setX(150);
        dp.setY(300);
        dp.setWidth(180);
        dp.setHeight(180);
        
        Picture pic = new Picture();
        pic.setName("foto");
        
        pic.setPath("C:\\\\Users\\\\uno\\\\Desktop\\\\15 de wendy 053.JPG");
        pic.setX(150);
        pic.setY(400);
        pic.setWidth(800);
        pic.setHeight(1000);
        cl.add(pic);
        cl.add(dp);
        cl.add(bt);
        cl.add(lb);
        cl.add(tb);
        cl.add(cb);
        cl.add(lib);
        
        wd.setChildren(cl);
        ArrayList<Window> win= new  ArrayList<Window>();
        win.add(wd);
       
        
        Window w2 = new Window();
        w2.setName("ventana2");
        w2.setTitle("Ventana 2");
        w2.setWidth(200);
        w2.setHeight(300);
        //win.add(w2);
         RenderArgus rd = new RenderArgus(win,0);
        try{
         rd.compile(win.get(0).getName());
                  System.out.print("compilado");
                  
        }catch(Exception e){System.out.println(e.getMessage());
        }
        try{
         rd.ejecutar();
         System.out.print("Ejecutando");
        }catch(Exception s){System.out.println(s.getStackTrace());
        }
    }
    
}
