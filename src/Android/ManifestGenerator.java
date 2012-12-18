package Android;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class ManifestGenerator 
{
    DocumentBuilderFactory dbFactory = null;
    DocumentBuilder docBuilder = null;
    Document document = null;
    Element root = null;
    
    public ManifestGenerator(String packageName, String minimumAndroidApiLevel)
    {
        try
        {
            dbFactory = DocumentBuilderFactory.newInstance();
            docBuilder = dbFactory.newDocumentBuilder();
            document = docBuilder.newDocument();
            
            generateManifestHeader(packageName);
            generateManifestUses(minimumAndroidApiLevel);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void generateManifestHeader(String packageName)
    {
        root = document.createElement("manifest");
        root.setAttribute("xmlns:android", "http://schemas.android.com/apk/res/android");
        root.setAttribute("android:versionCode", "1");
        root.setAttribute("android:versionName", "1.0");
        root.setAttribute("package", packageName);
        document.appendChild(root);
    }
    
    private void generateManifestUses(String minimumAndroidApiLevel)
    {
        Element usesSdk = document.createElement("uses-sdk");
        usesSdk.setAttribute("android:minSdkVersion", minimumAndroidApiLevel);
        usesSdk.setAttribute("android:targetSdkVersion", minimumAndroidApiLevel);
        root.appendChild(usesSdk);
    }
    
    public void generateManifestApplication(String applicationName, ArrayList<String> activities)
    {
        // SetDefaultIcon
        Element application = document.createElement("application");
        application.setAttribute("android:label", applicationName);
        // Change icon dynamic
        application.setAttribute("android:icon", "@drawable/icon");
        
        for( String activityName : activities)
        {
            application = generateManifestActivity(application, activityName);
        }
        root.appendChild(application);
    }
    
    public Element generateManifestActivity(Element application, String activityName)
    {       
        // Add intent-filter ??
        Element activity = document.createElement("activity");
        activity.setAttribute("android:name", "."+activityName);
        
        // We have to choose Main class
        Element intentFilter = document.createElement("intent-filter");
        
        Element action = document.createElement("action");
        action.setAttribute("android:name", "android.intent.action.MAIN");
        
        Element category = document.createElement("category");
        category.setAttribute("android:name","android.intent.category.LAUNCHER");
        
        intentFilter.appendChild(action);
        intentFilter.appendChild(category);
        
        activity.appendChild(intentFilter);
        
        application.appendChild(activity);
        return application;
    }
    
    public void generateManifestFile(String projectPath)
    {
        try
        {
            TransformerFactory transFac = TransformerFactory.newInstance();
            Transformer trans = transFac.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(document);
            trans.transform(source, result);
            String xml = sw.toString();
            
            File file = new File(projectPath+ApkGenerator.getFolderSeparator()+"AndroidManifest.xml");
            FileOutputStream outputStream = new FileOutputStream(file);
            
            outputStream.write(xml.getBytes());
            outputStream.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            //System.out.println(e.getMessage());
        }
    }
}
