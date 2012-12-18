package Android;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class ResourceGenerator 
{
    DocumentBuilderFactory dbFactory = null;
    DocumentBuilder docBuilder = null;
    Document document = null;
    
    public ResourceGenerator()
    {
        try
        {
            dbFactory = DocumentBuilderFactory.newInstance();
            docBuilder = dbFactory.newDocumentBuilder();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void generateStringArray(String filePathAndName, ArrayList<String> stringList, String arrayName)
    {
        document = docBuilder.newDocument();
        Element root = document.createElement("resources");
        Element stringArray = document.createElement("string-array");
        stringArray.setAttribute("name", arrayName);
        Element itemElement;
        
        for( String item : stringList )
        {
            itemElement = document.createElement("item");
            itemElement.setTextContent(item);
            stringArray.appendChild(itemElement);
        }
        
        root.appendChild(stringArray);
        document.appendChild(root);
        
        // Generate Resource
        generateResource(filePathAndName);
    }
    
    private void generateResource(String filePathAndName)
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
            
            File file = new File(filePathAndName);
            FileOutputStream outputStream = new FileOutputStream(file);
            
            outputStream.write(xml.getBytes());
            outputStream.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
