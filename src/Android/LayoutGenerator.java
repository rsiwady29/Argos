
package Android;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class LayoutGenerator 
{
    DocumentBuilderFactory dbFactory = null;
    DocumentBuilder docBuilder = null;
    Document document = null;
    Element root = null;
    Element scrollView = null;
    Element horizontalScrollView = null;
    static int correlative = 0;
    String ProjectPath;
    String fileName;
    
    public LayoutGenerator(String ProjectPath, String className, String packageName)
    {
        try
        {
            dbFactory = DocumentBuilderFactory.newInstance();
            docBuilder = dbFactory.newDocumentBuilder();
            document = docBuilder.newDocument();
            this.ProjectPath = ProjectPath;
            // Magic Fix
            this.fileName = className;
            //createMainClass(className, packageName);
            generateLayoutRoot();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void createMainClass(String className, String packageName)
    {
        fileName = ("test"+(correlative++));
        try 
        {
            String packageFolders[] = packageName.split("\\.");
            String folders = "";
            for(int i=0; i<packageFolders.length; i++)
            {
                folders += ApkGenerator.getFolderSeparator() + packageFolders[i];
            }
            String fn = ProjectPath+ApkGenerator.getFolderSeparator()+"src"+
                                 folders+ApkGenerator.getFolderSeparator()+
                                 className+".java";
            JOptionPane.showMessageDialog(null, fn);
            File file = new File(fn);
            
            FileOutputStream output = new FileOutputStream(file);
            String javaFile = "package " + packageName + ";\n";
            javaFile += "import android.os.Bundle;\nimport android.app.Activity;\n";
            javaFile += "public class " + className + " extends Activity\n{\n";
            javaFile += "@Override\npublic void onCreate(Bundle savedInstanceState) {\n";
            javaFile += "super.onCreate(savedInstanceState);\n";
            javaFile += "setContentView(R.layout."+fileName+");\n}\n}";
            
            output.write(javaFile.getBytes()); 
            output.close();
        } 
        catch (Exception ex) 
        {
            
        }
    }
    
    private void generateLayoutRoot()
    {
        scrollView = document.createElement("ScrollView");
        scrollView.setAttribute("xmlns:android", "http://schemas.android.com/apk/res/android");
        scrollView.setAttribute("android:layout_width", "match_parent");
        scrollView.setAttribute("android:layout_height", "match_parent");
        scrollView.setAttribute("android:background", "@android:color/darker_gray");
        
        horizontalScrollView = document.createElement("HorizontalScrollView");        
        horizontalScrollView.setAttribute("xmlns:android", "http://schemas.android.com/apk/res/android");
        horizontalScrollView.setAttribute("android:layout_width", "match_parent");
        horizontalScrollView.setAttribute("android:layout_height", "match_parent");
        horizontalScrollView.setAttribute("android:background", "@android:color/darker_gray");
        
        root = document.createElement("AbsoluteLayout");
        root.setAttribute("xmlns:android", "http://schemas.android.com/apk/res/android");
        root.setAttribute("xmlns:tools", "http://schemas.android.com/tools");
        root.setAttribute("android:layout_width", "match_parent");
        root.setAttribute("android:layout_height", "match_parent");
        root.setAttribute("android:background", "@android:color/darker_gray");

        scrollView.appendChild(horizontalScrollView);
        horizontalScrollView.appendChild(root);
        document.appendChild(scrollView);
    }
    
    public void generateLayoutButton(String idName, String text, int x, int y, 
                                    int width, int height,
                                    boolean visible, boolean enabled)
    {
        Element button = document.createElement("Button");
        button.setAttribute("android:id", "@+id/"+idName);
        button.setAttribute("android:text", text);
        button.setAttribute("android:layout_x", x+"dp");
        button.setAttribute("android:layout_y", y+"dp");
        button.setAttribute("android:layout_width", ""+width+"dp");
        button.setAttribute("android:layout_height", ""+height+"dp");
        button.setAttribute("android:visibility", visible?"visible":"invisible");        
        button.setAttribute("android:enabled", enabled?"true":"false");
        root.appendChild(button);
    }
    
    public void generateLayoutLabel(String idName, String text, int x, int y, int width, int height,
                                    boolean visible, boolean enabled)
    {
        Element label = document.createElement("TextView");
        label.setAttribute("android:id", "@+id/"+idName);
        label.setAttribute("android:text", text);
        label.setAttribute("android:layout_x", x+"dp");
        label.setAttribute("android:layout_y", y+"dp");
        label.setAttribute("android:layout_width", ""+width+"dp");
        label.setAttribute("android:layout_height", ""+height+"dp");
        label.setAttribute("android:visibility", visible?"visible":"invisible");        
        label.setAttribute("android:enabled", enabled?"true":"false");
        root.appendChild(label);
    }
    
    public void generateLayoutTextBox(String idName, String text, int x, int y, int width, int height,
                                    boolean visible, boolean enabled)
    {
        Element textbox = document.createElement("EditText");
        textbox.setAttribute("android:id", "@+id/"+idName);
        textbox.setAttribute("android:text", text);
        textbox.setAttribute("android:layout_x", x+"dp");
        textbox.setAttribute("android:layout_y", y+"dp");
        textbox.setAttribute("android:layout_width", ""+width+"dp");
        textbox.setAttribute("android:layout_height", ""+height+"dp");
        textbox.setAttribute("android:visibility", visible?"visible":"invisible");        
        textbox.setAttribute("android:enabled", enabled?"true":"false");
        root.appendChild(textbox);
    }
    
    public void generateLayoutCheckbox(String idName, String text, int x, int y, int width, int height,
                                    boolean visible, boolean enabled, boolean checked)
    {
        Element checkBox = document.createElement("CheckBox");
        checkBox.setAttribute("android:id", "@+id/"+idName);
        checkBox.setAttribute("android:text", text);
        checkBox.setAttribute("android:layout_x", x+"dp");
        checkBox.setAttribute("android:layout_y", y+"dp");
        checkBox.setAttribute("android:layout_width", ""+width+"dp");
        checkBox.setAttribute("android:layout_height", ""+height+"dp");
        checkBox.setAttribute("android:visibility", visible?"visible":"invisible");        
        checkBox.setAttribute("android:enabled", enabled?"true":"false");
        checkBox.setAttribute("android:checked", checked?"true":"false");
        
        root.appendChild(checkBox);
    }
    
    public void generateLayoutRadioButton(String idName, String text, int x, int y, int width, int height,
                                    boolean visible, boolean enabled, boolean checked)
    {
        Element radioButton = document.createElement("RadioButton");
        radioButton.setAttribute("android:id", "@+id/"+idName);
        radioButton.setAttribute("android:text", text);
        radioButton.setAttribute("android:layout_x", x+"dp");
        radioButton.setAttribute("android:layout_y", y+"dp");
        radioButton.setAttribute("android:layout_width", ""+width+"dp");
        radioButton.setAttribute("android:layout_height", ""+height+"dp");
        radioButton.setAttribute("android:visibility", visible?"visible":"invisible");        
        radioButton.setAttribute("android:enabled", enabled?"true":"false");
        radioButton.setAttribute("android:checked", checked?"true":"false");
        
        root.appendChild(radioButton);
    }
    
    public void generateLayoutDatePicker(String idName,int width, int height,
                                         int x, int y,
                                         boolean visible, boolean enabled)
    {
        Element datePicker = document.createElement("DatePicker");
        datePicker.setAttribute("android:id", "@+id/"+idName);
        datePicker.setAttribute("android:layout_x", x+"dp");
        datePicker.setAttribute("android:layout_y", y+"dp");
        datePicker.setAttribute("android:layout_width", ""+width+"dp");
        datePicker.setAttribute("android:layout_height", ""+height+"dp");
        datePicker.setAttribute("android:visibility", visible?"visible":"invisible");        
        datePicker.setAttribute("android:enabled", enabled?"true":"false");
        // Min values, Max values
        root.appendChild(datePicker);
    }
    
    public void generateLayoutPicture(String imagePath, String projectPath,String idName,
                                      int width, int height,
                                      int x, int y, boolean visible, boolean enabled)
    {
        // copy image to folder
        String imageName = copyFile(imagePath, projectPath).split("\\.")[0];
        Element imageView = document.createElement("ImageView");
        imageView.setAttribute("android:src", "@drawable/"+imageName); 
        imageView.setAttribute("android:id", "@+id/"+idName);
        imageView.setAttribute("android:layout_width", ""+width+"dp");
        imageView.setAttribute("android:layout_height", ""+height+"dp");
        imageView.setAttribute("android:layout_x", x+"dp");
        imageView.setAttribute("android:layout_y", y+"dp");
        imageView.setAttribute("android:visibility", visible?"visible":"invisible");        
        imageView.setAttribute("android:enabled", enabled?"true":"false");
        
        root.appendChild(imageView);
    }
    
    //Messagebox cant be added in xml resources
    
    // To Do's
    public void generateLayoutComboBox(String projectPath,String idName,
                                      int width, int height,
                                      int x, int y, boolean visible, boolean enabled,
                                      ArrayList<String> items)
    {
        Element spinner = document.createElement("Spinner");
        spinner.setAttribute("android:id", "@+id/"+idName);
        spinner.setAttribute("android:layout_x", x+"dp");
        spinner.setAttribute("android:layout_y", y+"dp");
        spinner.setAttribute("android:layout_width", ""+width+"dp");
        spinner.setAttribute("android:layout_height", ""+height+"dp");
        spinner.setAttribute("android:visibility", visible?"visible":"invisible");        
        spinner.setAttribute("android:enabled", enabled?"true":"false");
        // Entries ... or Adapter...

        String fileName="combobox_array_resource"+(correlative++)+".xml";
        String path = projectPath + ApkGenerator.getFolderSeparator() +
                      "res" + ApkGenerator.getFolderSeparator() + 
                      "values" + ApkGenerator.getFolderSeparator() +
                      fileName;
        ResourceGenerator resGen = new ResourceGenerator();
        String arrayName = idName + "Entries";
        resGen.generateStringArray( path , items, arrayName);
        spinner.setAttribute("android:entries", "@array/"+arrayName);

        root.appendChild(spinner);
    }
    
    public void generateListView(String projectPath,String idName,
                                 int width, int height,
                                 int x, int y, boolean visible, boolean enabled,
                                 ArrayList<String> items)
    {
        Element  listview = document.createElement("ListView");
        listview.setAttribute("android:id", "@+id/"+idName);
        listview.setAttribute("android:layout_x", x+"dp");
        listview.setAttribute("android:layout_y", y+"dp");
        listview.setAttribute("android:layout_width", ""+width+"dp");
        listview.setAttribute("android:layout_height", ""+height+"dp");
        listview.setAttribute("android:visibility", visible?"visible":"invisible");        
        listview.setAttribute("android:enabled", enabled?"true":"false");
        // Entries ... or Adapter...
        
        String fileName="listview_array_resource"+(correlative++)+".xml";
        String path = projectPath + ApkGenerator.getFolderSeparator() +
                      "res" + ApkGenerator.getFolderSeparator() + 
                      "values" + ApkGenerator.getFolderSeparator() +
                      fileName;
        ResourceGenerator resGen = new ResourceGenerator();

        String arrayName = idName + "Entries";
        resGen.generateStringArray( path , items, arrayName);
        listview.setAttribute("android:entries", "@array/"+arrayName);
        
        root.appendChild(listview);
    }
    
    public void generateLayoutLayout(String imagePath, String projectPath,String idName,
                                     int width, int height,
                                     int x, int y, boolean visible, boolean enabled, ArrayList<Element> elements)
    {
        Element layout = document.createElement("AbsoluteLayout");
        
        layout.setAttribute("android:id", "@+id/"+idName);
        layout.setAttribute("android:layout_x", x+"dp");
        layout.setAttribute("android:layout_y", y+"dp");
        layout.setAttribute("android:layout_width", ""+width+"dp");
        layout.setAttribute("android:layout_height", ""+height+"dp");
        layout.setAttribute("android:visibility", visible?"visible":"invisible");        
        layout.setAttribute("android:enabled", enabled?"true":"false");
        // append all childs to layout, if any
        if( elements != null)
        {
            for( Element element : elements)
            {
                layout.appendChild(element);
            }
        }
        
        root.appendChild(layout);
    }
    
    public void generateLayout()
    {
        try
        {
            String folderSep = ApkGenerator.getFolderSeparator();
            TransformerFactory transFac = TransformerFactory.newInstance();
            Transformer trans = transFac.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(document);
            trans.transform(source, result);
            String xml = sw.toString();
            
            JOptionPane.showMessageDialog(null, xml);
            
            File file = new File(ProjectPath + folderSep + "res" +
                                folderSep + "layout" +folderSep+
                                fileName+".xml");
            FileOutputStream outputStream = new FileOutputStream(file);
            
            outputStream.write(xml.getBytes());
            outputStream.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public String copyFile( String imagePath, String projectPath)
    {
        // Copy image to res/drawable in projectPath
        String folderSeparator = ApkGenerator.getFolderSeparator();
        File originImage = new File(imagePath);
        
        //String splits[] = imagePath.split(  folderSeparator.equals("/") ? "/" : "\\\\"  );
        String splits[] = imagePath.split("/");
        String destinyImageName = splits[splits.length-1];
        
        // ProjectPath->res->drawable folder
        File destinyImage = new File(projectPath + folderSeparator + 
                                    "res" + folderSeparator + "drawable"+ 
                                    folderSeparator + destinyImageName);
        
        try 
        {
            InputStream in = new FileInputStream(originImage);
            OutputStream out = new FileOutputStream(destinyImage);
            
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0)
            {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
        }
        return destinyImageName;
    }
}
