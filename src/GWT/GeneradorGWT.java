/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GWT;
 
//import Semantics.Controls.Button;
//import Semantics.Controls.Control;
//import Semantics.Controls.Window;
import Semantics.Controls.Button;
import Semantics.Controls.CheckBox;
import Semantics.Controls.ComboBox;
import Semantics.Controls.Control;
import Semantics.Controls.DatePicker;
import Semantics.Controls.Label;
import Semantics.Controls.ListBox;
import Semantics.Controls.Panel;
import Semantics.Controls.Picture;
import Semantics.Controls.RadioButton;
import Semantics.Controls.TextBox;
import Semantics.Controls.Window;
import Semantics.Information.Program;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class GeneradorGWT {

    /**
     * @param args the command line arguments
     */
    public static String Path;
    public static String Name;
    public static String Package;
    public static String HtmlPath;
    
    private static void copyFile(File sourceFile, File destFile) throws IOException {
    if(!destFile.exists()) {
        destFile.createNewFile();
    }

    FileChannel source = null;
    FileChannel destination = null;
    try {
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();

        // previous code: destination.transferFrom(source, 0, source.size());
        // to avoid infinite loops, should be:
        long count = 0;
        long size = source.size();              
        while((count += destination.transferFrom(source, count, size-count))<size);
    }
    finally {
        if(source != null) {
            source.close();
        }
        if(destination != null) {
            destination.close();
        }
    }
}
    
    private static String generarVariableCodigo(Control crt)
    {
        String output = "";
        if(crt instanceof Button)
            output += "Button "+crt.getName()+"_"+crt.getParent().getName()+" = new Button();\n";
        if(crt instanceof CheckBox)
            output += "CheckBox "+crt.getName()+"_"+crt.getParent().getName()+" = new CheckBox();\n";
        if(crt instanceof ComboBox || crt instanceof ListBox)
            output += "ListBox "+crt.getName()+"_"+crt.getParent().getName()+" = new ListBox();\n";
        if(crt instanceof DatePicker)
            output += "DateBox "+crt.getName()+"_"+crt.getParent().getName()+" = new DateBox();\n";
        if(crt instanceof Label)
            output += "Label "+crt.getName()+"_"+crt.getParent().getName()+" = new Label();\n";
        if(crt instanceof Panel)
            output += "AbsolutePanel "+crt.getName()+"_"+crt.getParent().getName()+" = new AbsolutePanel();\n";
        if(crt instanceof Picture)
            output += "Image "+crt.getName()+"_"+crt.getParent().getName()+" = new Image();\n";
        if(crt instanceof RadioButton)
            output += "RadioButton "+crt.getName()+"_"+crt.getParent().getName()+" = new RadioButton(\""+crt.getParent().getName()+"\");\n";
        if(crt instanceof TextBox)
            output += "TextBox "+crt.getName()+"_"+crt.getParent().getName()+" = new TextBox();\n";
        return output;
    }
    
    private static String generarCodigo(Control crt) throws IOException
    {
        String output = "";        
        
        if(crt instanceof Window)
        {
            output += "private void "+crt.getName()+"_create() {\n";
            output += "\t\tfinal RootPanel rootPanel = RootPanel.get();\n";
            output += "\t\tRootPanel.get().clear();\n";
            output += "\t\tWindow.setTitle(\""+((Window)crt).getTitle()+"\");\n";
            for(int i=0; i<crt.getChildren().size(); i++)
            {
                output += "\t\t"+generarCodigo(crt.getChildren().get(i));
            }
            output += "\t\trootPanel.setStyleName(\""+crt.getName()+"_css\");\n";
            if(((Window) crt).getLoad() != null)
            {
                if(((Window) crt).getLoad().getFileName() != null)
                {
                    output += "\t\t"+GeneradorSentencia.GetCode_SymbolTable(((Window) crt).getLoad().getSymbolTable());
                    output += "\t\t"+GeneradorSentencia.GenerarCodigo(((Window) crt).getLoad().getStatements());
                }
            }
            output += "\t}\n";
        }
        else
        {
            String name = crt.getName() + "_" + crt.getParent().getName();
            
            if (crt instanceof Button) {
                output += name + ".setText(\"" + ((Button) crt).getValue() + "\");\n";
                if(((Button) crt).getClick() != null)
                {
                    if(((Button) crt).getClick().getFileName() != null)
                    {
                        output += name + ".addClickHandler(new ClickHandler(){\n";
                        output += "\t\tpublic void onClick(ClickEvent event) {\n";
                        output += "\t\t"+GeneradorSentencia.GetCode_SymbolTable(((Button) crt).getClick().getSymbolTable());
                        output += GeneradorSentencia.GenerarCodigo(((Button) crt).getClick().getStatements());
                        output += "\t\t}\n";
                        output += "\t});\n";
                    }
                }
            }
            if (crt instanceof CheckBox) {
                output += name + ".setText(\"" + ((CheckBox) crt).getText() + "\");\n";

                if (crt.getParent() instanceof Panel) {
                    output += "\t\t" + name + ".setName(\"" + crt.getParent().getName() + "\");\n";
                }

                if (((CheckBox) crt).isChecked()) {
                    output += "\t\t" + name + ".setValue(true);\n";
                } else {
                    output += "\t\t" + name + ".setValue(false);\n";
                }
                
                if(((CheckBox) crt).getOnValueChanged() != null)
                {
                    if(((CheckBox) crt).getOnValueChanged().getFileName() != null)
                {
                    output += name + ".addValueChangeHandler(new ValueChangeHandler<Boolean>(){\n";
                    output += "\t\tpublic void onValueChange(ValueChangeEvent<Boolean> event) {\n";
                    output += "\t\t"+GeneradorSentencia.GetCode_SymbolTable(((CheckBox) crt).getOnValueChanged().getSymbolTable());
                    output += GeneradorSentencia.GenerarCodigo(((CheckBox) crt).getOnValueChanged().getStatements());
                    output += "\t\t}\n";
                    output += "\t});\n";
                }
                }
            }
            if (crt instanceof ComboBox) {
                output += "\t\t" + name + ".setVisibleItemCount(1);\n";
                if (((ComboBox) crt).getItems() != null) {
                    for (int i = 0; i < ((ComboBox) crt).getItems().size(); i++) {
                        output += "\t\t" + name + ".addItem(\"" + ((ComboBox) crt).getItems().get(i).getText() + "\",\"" + ((ComboBox) crt).getItems().get(i).getValue() + "\");\n";
                    }
                }
                if(((ComboBox) crt).getItemChanged() != null)
                {
                    if(((ComboBox) crt).getItemChanged().getFileName() != null)
                    {
                        output += name + ".addChangeHandler(new ChangeHandler(){\n";
                        output += "\t\tpublic void onChange(ChangeEvent event) {\n";
                        output += "\t\t"+GeneradorSentencia.GetCode_SymbolTable(((ComboBox) crt).getItemChanged().getSymbolTable());
                        output += GeneradorSentencia.GenerarCodigo(((ComboBox) crt).getItemChanged().getStatements());
                        output += "\t\t}\n";
                        output += "\t});\n";
                    }
                }
            }
            if (crt instanceof ListBox) {
                output += "\t\t" + name + ".setVisibleItemCount(5);\n";
                if (((ListBox) crt).getItems() != null) {
                    for (int i = 0; i < ((ListBox) crt).getItems().size(); i++) {
                        output += "\t\t" + name + ".addItem(\"" + ((ListBox) crt).getItems().get(i).getText() + "\",\"" + ((ListBox) crt).getItems().get(i).getValue() + "\");\n";
                    }
                }
                if(((ListBox) crt).getItemChanged() != null)
                {
                    if(((ListBox) crt).getItemChanged().getFileName() != null)
                {
                    output += name + ".addChangeHandler(new ChangeHandler(){\n";
                    output += "\t\tpublic void onChange(ChangeEvent event) {\n";
                    output += "\t\t"+GeneradorSentencia.GetCode_SymbolTable(((ListBox) crt).getItemChanged().getSymbolTable());
                    output += GeneradorSentencia.GenerarCodigo(((ListBox) crt).getItemChanged().getStatements());
                    output += "\t\t}\n";
                    output += "\t});\n";
                }
                }
            }
            if (crt instanceof DatePicker) {
            }
            if (crt instanceof Label) {
                output += name + ".setText(\"" + ((Label) crt).getValue() + "\");\n";
            }
            if (crt instanceof Panel) {
                if (((Panel) crt).getChildren() != null) {
                    for (int i = 0; i < crt.getChildren().size(); i++) {
                        output += "\t\t" + generarVariableCodigo(crt.getChildren().get(i));
                        output += "\t\t" + generarCodigo(crt.getChildren().get(i));
                        output += "\t\t" + name + ".add(" + crt.getChildren().get(i).getName() + "_" + crt.getName() + "," + String.valueOf(crt.getChildren().get(i).getX() - crt.getX()) + "," + String.valueOf(crt.getChildren().get(i).getY() - crt.getY()) + ");\n";
                    }
                }
            }
            if (crt instanceof Picture) { 
                if (((Picture) crt).getPath() != null) {
                    File source = new File(((Picture) crt).getPath());   
                    String newFile = HtmlPath+"\\"+source.getName();
                    copyFile(source, new File(newFile));
                    output += "\t\t"+name+".setUrl(\"" + source.getName() + "\");\n";
                }
                if(((Picture) crt).getClick() != null)
                {
                    if(((Picture) crt).getClick().getFileName() != null)
                {
                    output += name + ".addClickHandler(new ClickHandler(){\n";
                    output += "\t\tpublic void onClick(ClickEvent event) {\n";
                    output += "\t\t"+GeneradorSentencia.GetCode_SymbolTable(((Picture) crt).getClick().getSymbolTable());
                    output += GeneradorSentencia.GenerarCodigo(((Picture) crt).getClick().getStatements());
                    output += "\t\t}\n";
                    output += "\t});\n";
                }
                }
            }
            if (crt instanceof RadioButton) {
                output += name + ".setText(\"" + ((RadioButton) crt).getText() + "\");\n";

                if (((RadioButton) crt).isChecked()) {
                    output += "\t\t" + name + ".setValue(true);\n";
                } else {
                    output += "\t\t" + name + ".setValue(false);\n";
                }
                
                if(((RadioButton) crt).getOnValueChanged() != null)
                {
                    if(((RadioButton) crt).getOnValueChanged().getFileName() != null)
                {
                    output += name + ".addValueChangeHandler(new ValueChangeHandler<Boolean>(){\n";
                    output += "\t\tpublic void onValueChange(ValueChangeEvent<Boolean> event) {\n";
                    output += "\t\t"+GeneradorSentencia.GetCode_SymbolTable(((RadioButton) crt).getOnValueChanged().getSymbolTable());
                    output += GeneradorSentencia.GenerarCodigo(((RadioButton) crt).getOnValueChanged().getStatements());
                    output += "\t\t}\n";
                    output += "\t});\n";
                }
                }
            }
            if (crt instanceof TextBox) {
                output += name + ".setText(\"" + ((TextBox) crt).getValue() + "\");\n";
                if (((TextBox) crt).isReadOnly()) {
                    output += "\t\t" + name + ".setReadOnly(true);\n";
                } else {
                    output += "\t\t" + name + ".setReadOnly(false);\n";
                }
                if(((TextBox) crt).getOnTextChanged() != null)
                {
                    if(((TextBox) crt).getOnTextChanged().getFileName() != null)
                    {
                        output += name + ".addKeyUpHandler(new KeyUpHandler(){\n";
                        output += "\t\tpublic void onKeyUp(KeyUpEvent event) {\n";
                        output += "\t\t"+GeneradorSentencia.GetCode_SymbolTable(((TextBox) crt).getOnTextChanged().getSymbolTable());
                        output += GeneradorSentencia.GenerarCodigo(((TextBox) crt).getOnTextChanged().getStatements());
                        output += "\t\t}\n";
                        output += "\t});\n";
                    }
                }
            }

            if (crt.getWidth() != 0 && crt.getHeight() != 0) {
                output += "\t\t" + name + ".setSize(\"" + String.valueOf(crt.getWidth()) + "px\",\"" + String.valueOf(crt.getHeight()) + "px\");\n";
            }
            
            if (crt.isVisible()) {
                output += "\t\t" + name + ".setVisible(true);\n";
            } else {
                output += "\t\t" + name + ".setVisible(false);\n";
            }
            if (!(crt instanceof Label) && !(crt instanceof Panel) && !(crt instanceof Picture)) {
                if (crt.isEnabled()) {
                    output += "\t\t" + name + ".setEnabled(true);\n";
                } else {
                    output += "\t\t" + name + ".setEnabled(false);\n";
                }
                if (crt.getTabIndex() != 0) {
                output += "\t\t" + name + ".setTabIndex(" + crt.getTabIndex() + ");\n";
            }
            }
            output += "\t\t" + name + ".setStyleName(\"" + name + "_css\");\n";

            if (!(crt.getParent() instanceof Panel)) {
                output += "\t\trootPanel.add(" + name + "," + String.valueOf(crt.getX()) + "," + String.valueOf(crt.getY()) + ");\n";
            }           
        }
        return output;
    }
    
    private static String generarEstiloCodigo(Control crt)
    {
        String output = "";
        /*
        if(!(crt instanceof Window))
        {            
            String name = crt.getName()+"_"+crt.getParent().getName();
            output += "."+name+"_css {\n";
        }
        else
            output += "."+crt.getName()+"_css {\n";  
            
        if(crt.getFont() != null)
        {            
            if(crt.getFont().getForeground() != null)
                output += "\tcolor: #"+Integer.toHexString(crt.getFont().getForeground().getRGB()).substring(2,8)+";\n";
            if(crt.getFont().getBackground() != null)
                output += "\tbackground-color: #"+Integer.toHexString(crt.getFont().getBackground().getRGB()).substring(2,8)+";\n";
        }
        if(crt.getFont() != null && crt.getFont().getFont() != null)
        {            
            output += "\tfont-size: "+crt.getFont().getFont().getSize()+"px;\n";
            String fontStyle = "normal";
            switch(crt.getFont().getFont().getStyle())
            {                
                case java.awt.Font.ITALIC | java.awt.Font.BOLD:
                    output += "\tfont-weight:bold;\n";
                    fontStyle = "italic";
                    break;
                case java.awt.Font.ITALIC:
                    fontStyle = "italic";
                    break;
                case java.awt.Font.BOLD:
                    output += "\tfont-weight:bold;\n";
                    break;
                default:
                    break;
            }
            output += "\tfont-style: "+fontStyle+";\n";
            output += "\tfont-family: \""+crt.getFont().getFont().getFamily()+"\";\n";
        }
        if(crt.getBorderColor() != null)
            output += "\tborder: 1px solid #"+Integer.toHexString(crt.getBorderColor().getRGB()).substring(2,8)+";\n";
        
        output += "}\n\n";
        
        if(crt instanceof Window)
        {
            for(int i=0; i<crt.getChildren().size(); i++)
            {
                output += generarEstiloCodigo(crt.getChildren().get(i));
            }    
        }
        else if(crt instanceof Panel)
        {
            if(crt.getChildren() != null)
            {
                for(int i=0; i<crt.getChildren().size(); i++)
                    output += generarEstiloCodigo(crt.getChildren().get(i));
            }
        }
        **/
        return output;
    }
    
    public static int generarCodigo(Program tree) throws FileNotFoundException, IOException
    {        
        try
        {     
            
            File global = new File("src\\GWT\\Global.source");
            
            char[] content=new char[(int)global.length()];
            
            
            FileReader fis = new FileReader("src\\GWT\\Global.source");
            FileWriter fos = new FileWriter(Path+"\\Global.java");
            
            fos.append("package "+Package+";\n\n");
            fis.read(content, 0, content.length);
            fis.close();
            fos.append(String.valueOf(content)); 
            fos.close();
            
            fos = new FileWriter(Path+"\\"+Name+".java");
            
            fos.append("package "+Package+";\n\n");
            fos.append("import com.google.gwt.core.client.EntryPoint;\n");
fos.append("import com.google.gwt.event.dom.client.ClickEvent;\n");
fos.append("import com.google.gwt.event.dom.client.ClickHandler;\n");
fos.append("import com.google.gwt.event.dom.client.KeyUpEvent;\n");
fos.append("import com.google.gwt.event.dom.client.KeyUpHandler;\n");
fos.append("import com.google.gwt.user.client.Window;\n");
fos.append("import com.google.gwt.user.client.ui.Button;\n");
fos.append("import com.google.gwt.user.client.ui.Label;\n");
fos.append("import com.google.gwt.user.client.ui.RootPanel;\n");
fos.append("import com.google.gwt.user.client.ui.TextBox;\n");
fos.append("import com.google.gwt.user.client.ui.ListBox;\n");
fos.append("import com.google.gwt.user.client.ui.AbsolutePanel;\n");
fos.append("import com.google.gwt.user.client.ui.CheckBox;\n");
fos.append("import com.google.gwt.dom.client.Style.Unit;\n");
fos.append("import com.google.gwt.user.client.ui.RadioButton;\n");
fos.append("import com.google.gwt.user.client.ui.Image;\n");
fos.append("import com.google.gwt.event.logical.shared.ValueChangeHandler;\n");
fos.append("import com.google.gwt.event.logical.shared.ValueChangeEvent;\n");
fos.append("import com.google.gwt.event.dom.client.ChangeHandler;\n");
fos.append("import com.google.gwt.event.dom.client.ChangeEvent;\n");
fos.append("import com.google.gwt.user.datepicker.client.DateBox;\n");
            
            fos.append("public class "+Name+" implements EntryPoint {\n\n");
            
            for(int j=0; j<tree.getWindowsList().size(); j++)
            {
                for(int i=0; i<tree.getWindowsList().get(j).getChildren().size(); i++)
                    fos.append("\t"+generarVariableCodigo(tree.getWindowsList().get(j).getChildren().get(i)));
                
                fos.append("\n");
                fos.flush();
            }           
            
            for(int i=0; i<tree.getWindowsList().size(); i++)
            {
                Globales.ventanaActual = tree.getWindowsList().get(i).getName();
                fos.append("\t"+generarCodigo(tree.getWindowsList().get(i))+"\n");
                fos.flush();
            }

            fos.append("\tpublic void onModuleLoad() {\n");
            fos.append("\t\t"+tree.getWindowsList().get(tree.getWhichWindow()).getName()+"_create();\n");
            fos.append("\t}\n\n}\n");
            fos.flush();
            fos.close();
            
            fos = new FileWriter(HtmlPath+"\\"+Name+".html");
            fos.append("<!doctype html>\n");
            fos.append("\n");
            fos.append("<html>\n");
            fos.append("  <head>\n");
            fos.append("    <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n");
            fos.append("\n");
            fos.append("    <link type=\"text/css\" rel=\"stylesheet\" href=\""+Name+".css\">\n");
            fos.append("\n");
            fos.append("    <title>"+tree.getWindowsList().get(tree.getWhichWindow()).getTitle()+"</title>\n");
            fos.append("\n");
            fos.append("    <script type=\"text/javascript\" language=\"javascript\" src=\""+Name+"/"+Name+".nocache.js\"></script>\n");
            fos.append("  </head>\n");
            fos.append("\n");
            fos.append("  <body>\n");
            fos.append("\n");
            fos.append("    <iframe src=\"javascript:''\" id=\"__gwt_historyFrame\" tabIndex='-1' style=\"position:absolute;width:0;height:0;border:0\"></iframe>\n");
            fos.append("\n");    
            fos.append("    <noscript>\n");
            fos.append("      <div style=\"width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif\">\n");
            fos.append("        Your web browser must have JavaScript enabled\n");
            fos.append("        in order for this application to display correctly.\n");
            fos.append("      </div>\n");
            fos.append("    </noscript>\n");
            fos.append("\n");
            fos.append("  </body>\n");
            fos.append("</html>\n");
            fos.close();
            
            fos = new FileWriter(HtmlPath+"\\"+Name+".css");
            
            String temp = "";
            for(int i=0; i<tree.getWindowsList().size(); i++)
            {
                temp = generarEstiloCodigo(tree.getWindowsList().get(i));
                fos.append(temp);                
                fos.flush();
            }
            
            fos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        return 0;
    }
}
