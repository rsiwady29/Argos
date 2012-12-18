/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import Semantics.Controls.Button;
import Semantics.Controls.CheckBox;
import Semantics.Controls.ComboBox;
import Semantics.Controls.Control;
import Semantics.Controls.DatePicker;
import Semantics.Controls.Font;
import Semantics.Controls.Label;
import Semantics.Controls.ListBox;
import Semantics.Controls.ListBoxItem;
import Semantics.Controls.Panel;
import Semantics.Controls.Picture;
import Semantics.Controls.RadioButton;
import Semantics.Controls.TextBox;
import Semantics.Controls.Window;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import Semantics.Information.*;
import java.io.IOException;

/**
 *
 * @author josh
 */
public class renderVentanas {

    manageFile mf;
    ArrayList<manageFile> listado=new ArrayList<manageFile>();

    public ArrayList<manageFile> getListado() {
        return listado;
    }

    public void setListado(ArrayList<manageFile> listado) {
        this.listado = listado;
    }
    
    
    public manageFile getMf() {
        return mf;
    }

    public void setMf(manageFile mf) {
        this.mf = mf;
    }

    
    
    
    public renderVentanas(File archivo, Window wn, boolean isMain) throws IOException {
        String newPath=archivo.getPath();        
        File newFile = null;
        
  
                    
        mf = new manageFile(archivo);        
        dibujar(wn);
        mf.finish();
        mf.compile();
        listado.add(mf);
       /*
        mf.compile();
        if (isMain) {
            mf.run();
        }
        * 
        */


    }

    public void dibujar(Window wn) {
        mf.writeWIndow(wn.getName(), wn.getHeight(), wn.getWidth());
        ArrayList<Control> list = wn.getChildren();
        for (int i = 0; i < list.size(); i++) {


            if (list.get(i) instanceof Button) {

                Button btn = (Button) list.get(i);
                renderButton(btn);


            }
            if (list.get(i) instanceof CheckBox) {

                CheckBox chk = (CheckBox) list.get(i);
                renderCheckBox(chk);

            }

            if (list.get(i) instanceof DatePicker) {

                DatePicker dt = (DatePicker) list.get(i);
                renderDate(dt);


            }

            if (list.get(i) instanceof Label) {
                Label lbl = (Label) list.get(i);
                renderLabel(lbl);
            }
            if (list.get(i) instanceof ListBox) {

                ListBox lst = (ListBox) list.get(i);
                renderListBox(lst);

            }

            if (list.get(i) instanceof Panel) {
                Panel pnl = (Panel) list.get(i);
                renderPanel(pnl);


            }
            if (list.get(i) instanceof Picture) {

                Picture pct = (Picture) list.get(i);
                renderPicture(pct);
            }
            if (list.get(i) instanceof RadioButton) {

                RadioButton rdn = (RadioButton) list.get(i);
                renderRadioButton(rdn);

            }
            if (list.get(i) instanceof TextBox) {
                TextBox txt = (TextBox) list.get(i);
                renderTextBox(txt);

            }
             if (list.get(i) instanceof ComboBox) {
                ComboBox txt = (ComboBox) list.get(i);
                renderComboBox(txt);

            }




        }



    }

    public void renderGlobal() {
        mf.writeGlobal();

    }

    public void renderButton(Button btn) {
        //String n=GeneradorSentencia.GenerarCodigo(btn.getClick().getStatements());        
        btn.setFont(solveFont(btn));

        mf.writeButton(btn.getName(), btn.getValue(), btn.getHeight(), btn.getWidth(), btn.getX(), btn.getY(), btn.getFont().getFont(), btn.getFont().getBackground(), btn.isEnabled(), btn.isVisible(),
                btn.getClick(), btn.getParent());

    }

    public void renderLabel(Label lbl) {

        lbl.setFont(solveFont(lbl));

        mf.writeLabel(lbl.getName(), lbl.getValue(), lbl.getHeight(), lbl.getWidth(), lbl.getX(), lbl.getY(), lbl.getFont().getFont(), lbl.getBorderColor(), lbl.isEnabled(), lbl.isVisible(), lbl.getParent());
        int i = 0;
    }

    public void renderCheckBox(CheckBox chk) {


        chk.setFont(solveFont(chk));
        mf.writeCheckBox(chk.getName(), chk.getText(), chk.getDirection(), chk.getHeight(), chk.getWidth(), chk.getX(), chk.getY(), chk.getFont().getFont(), chk.getBorderColor(), chk.isEnabled(), chk.isVisible(), chk.isChecked(), chk.getOnValueChanged(), chk.getParent());

    }

    public void renderComboBox(ComboBox cmb) {
        cmb.setFont(solveFont(cmb));
        mf.writeComboBox(cmb.getName(), cmb.getHeight(), cmb.getWidth(), cmb.getX(), cmb.getY(), cmb.getItems(), cmb.getFont().getFont(), cmb.getBorderColor(), cmb.isEnabled(), cmb.isVisible(), cmb.getItemChanged(), cmb.getParent());

    }

    public void renderDate(DatePicker dtp) {
        dtp.setFont(solveFont(dtp));
        mf.writeDate(dtp.getName(), dtp.getDateFormat(), dtp.getDateFormat(), dtp.getHeight(), dtp.getWidth(), dtp.getX(), dtp.getY(), dtp.getFont().getFont(), Color.darkGray, dtp.isEnabled(), dtp.isVisible(), dtp.getParent());
    }

    public void renderListBox(ListBox lstBox) {
        lstBox.setFont(solveFont(lstBox));
        mf.writeListBox(lstBox, lstBox.getName(), lstBox.getHeight(), lstBox.getWidth(), lstBox.getX(), lstBox.getY(), lstBox.getItems(), lstBox.getFont().getFont(), lstBox.getBorderColor(), lstBox.isEnabled(), lstBox.isVisible(), lstBox.getItemChanged(), lstBox.getParent());
    }

    public void renderPanel(Panel pnl) {
    }

    public void renderPicture(Picture pct) {
        mf.writePicture(pct.getName(), pct.getPath(), pct.getHeight(), pct.getWidth(), pct.getX(), pct.getY(), pct.getBorderColor(), pct.isEnabled(), pct.isVisible(), pct.getClick(), pct.getParent());
    }

    public void renderRadioButton(RadioButton rdnB) {
        rdnB.setFont(solveFont(rdnB));
        mf.writeRadioBox(rdnB.getName(), rdnB.getText(), rdnB.getDirection(), rdnB.getHeight(), rdnB.getWidth(), rdnB.getX(), rdnB.getY(), rdnB.getFont().getFont(), rdnB.getBorderColor(), rdnB.isEnabled(), rdnB.isVisible(), rdnB.isChecked(), rdnB.getOnValueChanged(), rdnB.getParent());

    }

    public void renderTextBox(TextBox txt) {

        txt.setFont(solveFont(txt));
        mf.writeTextBox(txt.getName(), txt.getValue(), txt.getHeight(), txt.getWidth(), txt.getX(), txt.getY(), txt.getFont().getFont(), txt.getFont().getBackground(), txt.getBorderColor(), txt.isEnabled(), txt.isVisible(), txt.getOnTextChanged(), txt.getParent());
    }

    private Font solveFont(Control c) {

        Font m = new Font();
        java.awt.Font f1 = new java.awt.Font("monospaced", java.awt.Font.BOLD, 14);

      m.setBackground(Color.gray);
        m.setFont(f1);
       m.setForeground(Color.GRAY);
       
       
        if (c.getFont() == null) {


            return (m);
        } else {
            if (c.getFont().getFont().getStyle() == 0 || c.getFont().getFont().getSize() == 0) {

                return (m);
            }

        }
        return c.getFont();
    }
}
