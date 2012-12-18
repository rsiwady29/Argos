/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Android;
      
import Semantics.Controls.*;
import Semantics.Information.*;
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Moises
 */
public class CreateActivitiyFiles {
    public static void CreateJavaFiles(String ProjectPath, String packageName, Program program){
        
        ControlsCodeGenerator ccg = new ControlsCodeGenerator(program);
        ArrayList<WindowCode> windowsCode = ccg.getProgramCode();
        
        for(WindowCode wc: windowsCode)
        {
             /*Package folders*/
            String packageFolders[] = packageName.split("\\.");
            String folders = "";
            for(int i=0; i<packageFolders.length; i++)
            {
                folders += ApkGenerator.getFolderSeparator() + packageFolders[i];
            }
            
            String fileName = ProjectPath+"src"+folders+
                              ApkGenerator.getFolderSeparator()+
                              wc.getWindow().getName() + ".java";
            
            String imports = 
                      "import java.util.*;\n"
                    + "import android.os.Bundle;\n"
                    + "import android.app.*;\n"
                    + "import android.content.*;\n"
                    + "import android.graphics.*;\n"
                    + "import android.view.*;\n"
                    + "import android.view.ViewGroup.LayoutParams;\n"
                    + "import android.view.View.*;\n"
                    + "import android.widget.AdapterView.*;\n"
                    + "import android.widget.CompoundButton.*;\n"
                    + "import android.widget.*;\n\n";
            
            String codigo = wc.getWindowCode();
            
            String codigoFinal ="package " + packageName + ";\n\n" + imports + codigo;
            
            try
            {
                FileWriter fw = new FileWriter(fileName);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(codigoFinal);
                bw.close();
            }
            catch(Exception e)
            {
            
            }    
        }
    }
    
    
    public static void CreateGlobalFile(String projectPath, String packageName)
    {
        try
        {
             /*Package folders*/
            String packageFolders[] = packageName.split("\\.");
            String folders = "";
            for(int i=0; i<packageFolders.length; i++)
            {
                folders += ApkGenerator.getFolderSeparator() + packageFolders[i];
            }
            
            List<String> lines = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader("src/Android/Global.java.copy"));
            
            String line = br.readLine();
            while (line != null)
            {
                lines.add(line);
                line = br.readLine();
            }
            br.close();
            
            lines.add(0, "package " + packageName + ";\n\n");
            String n = projectPath+"src"+folders+ApkGenerator.getFolderSeparator()
                                + "Global.java";
 
            String codigo = "";
            for (String s : lines)
            {
                codigo += s+"\n";
            }
            
            File file = new File(n);
            file.createNewFile();
            
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(codigo.getBytes());
            stream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
