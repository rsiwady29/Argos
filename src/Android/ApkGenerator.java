package Android;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.swing.JOptionPane;


public class ApkGenerator {

	public String projectName = null;
	public String packageName = null;
        public String projectPath = null;
        
	public String JAVA_HOME = null;
	public String ANDROID_HOME = null;
	public String DEV_HOME = null;
        
        public String folderSeparator = null;
        OperatingSystem OS = null;
        
        public enum OperatingSystem{ Windows, UnixBased }
        
        public ApkGenerator(OperatingSystem os)
        {
            if( os == OperatingSystem.UnixBased )
            {
                folderSeparator = "/";
            }
            else if( os == OperatingSystem.Windows)
            {
                folderSeparator = "\\";
            }
            this.OS = os;
        }
	
        public static String getFolderSeparator()
        {
            String os = System.getProperty("os.name").toLowerCase();
            String foldSep = null;
        
            if( os.contains("win") )
            {
                foldSep = "\\";
            }
            else if( os.indexOf("nix") >= 0  || os.indexOf("nux") >= 0 || os.indexOf("mac")>=0   )
            {
                foldSep ="/";
            }
            return foldSep;
        } 
        
	public void createFolderStructure(String projectPath, String projectName, String packageName)
	{
            this.projectName = projectName;
            this.projectPath = projectPath;
            this.packageName = packageName;
            
            projectPath = projectPath + folderSeparator + projectName;

            JOptionPane.showMessageDialog(null, projectPath);

            String packageFolders[] = packageName.split("\\.");
            String folders = "";
            for(int i=0; i<packageFolders.length; i++)
            {
                folders += folderSeparator + packageFolders[i];
            }

            if(new File(projectPath).mkdir() &&
               new File(projectPath+folderSeparator+"src"+folders).mkdirs() &&
               new File(projectPath+folderSeparator+"res"+folderSeparator+"drawable").mkdirs() &&
               new File(projectPath+folderSeparator+"res"+folderSeparator+"layout").mkdir() &&
               new File(projectPath+folderSeparator+"res"+folderSeparator+"values").mkdir() &&
               new File(projectPath+folderSeparator+"obj").mkdir() && 
               new File(projectPath+folderSeparator+"lib").mkdir() &&
               new File(projectPath+folderSeparator+"bin").mkdir() &&
               new File(projectPath+folderSeparator+"docs").mkdir()
              )
            {
                JOptionPane.showMessageDialog(null,"Structure created succesfully");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Folder structure creation error");			
            }	
	}
	
	public void generateKeystore(String keyPath, String alias,
                                            String storepass, String keypass,
                                            String dname)
	{
            String command = getEscapedString(JAVA_HOME+ folderSeparator + "bin" + folderSeparator + "keytool", OS) +
                             " -genkey -keystore "+ getEscapedString(keyPath+ folderSeparator +"keystore",OS) +" -alias " + 
                             alias + " -storepass " + storepass + " -keypass " + keypass +
                             " -dname " + dname;// Check in Linux
            
            executeCommand(command);
	}
        
        public void generarR(String ProjectHome , String pathToAndroidJar)
        {
            String command = getEscapedString(ANDROID_HOME + folderSeparator + "platform-tools" + folderSeparator + "aapt", OS) +
                             " package -v -f -m -S " + getEscapedString(ProjectHome + getFolderSeparator() +"res",OS)+
                             " -J " + getEscapedString(ProjectHome + getFolderSeparator() + "src",OS) + " -M " + getEscapedString(ProjectHome+ getFolderSeparator() +"AndroidManifest.xml",OS) +
                             " -I " + getEscapedString(pathToAndroidJar, OS); 
            
            executeCommand(command);
        }
	
        public void compilarCodigo(String ProjectHome,String pathToAndroidJar, String packageName)
        {
            String packageFolders[] = packageName.split("\\.");
            String folders = "";
            for(int i=0; i<packageFolders.length; i++)
            {
                folders += getFolderSeparator() + packageFolders[i];
            }
            // LAST *.java cant be escaped in windows
            String command = getEscapedString(JAVA_HOME + folderSeparator + "bin" + folderSeparator + "javac" , OS) +
                            " -d " + getEscapedString(ProjectHome  + getFolderSeparator() +"obj",OS)+ " " +
                             "-classpath " + getEscapedString(pathToAndroidJar,OS) + (OS==OperatingSystem.Windows?";":":")+
                             getEscapedString(ProjectHome+ getFolderSeparator() +"obj",OS)+ " " +
                             "-sourcepath " + getEscapedString(ProjectHome+ getFolderSeparator() +"src",OS) + " " +
                             ProjectHome+ getFolderSeparator() + "src" + folders + getFolderSeparator() +"*.java";
            executeCommand2(command);
        }
        
        public void crearDEX(String ProjectHome)
        {
            String command =getEscapedString(ANDROID_HOME + folderSeparator + "platform-tools" + folderSeparator + "dx.bat", OS) +
                            " --dex --output="+
                            getEscapedString(ProjectHome+getFolderSeparator()+"bin"  + getFolderSeparator() +"classes.dex",OS)+ " "+
                            getEscapedString(ProjectHome+getFolderSeparator()+"obj",OS)+ " " +
                            getEscapedString(ProjectHome+getFolderSeparator()+"lib",OS);
            executeCommand(command);
            try{Thread.sleep(4000);}catch(Exception ex){}
        }
        
        public void crearAPKsinFirma(String ApkName, String ProjectHome, String pathToAndroidJar)
        {   
            String command = getEscapedString(ANDROID_HOME + folderSeparator + "platform-tools" + folderSeparator + "aapt", OS)+
                             " package -v -f -M " + 
                              getEscapedString(ProjectHome + getFolderSeparator() +"AndroidManifest.xml",OS)+
                              " -S " + getEscapedString( ProjectHome + getFolderSeparator() +"res",OS) + " " +
                              " -I " + getEscapedString(pathToAndroidJar,OS) + " -F " +
                              getEscapedString( ProjectHome + getFolderSeparator() +"bin"+getFolderSeparator()+ApkName+"Unsigned.apk",OS) + " "+
                              getEscapedString(ProjectHome + getFolderSeparator() +"bin",OS);
            executeCommand(command);
        }
        
        public void firmarApk(String apkName,String ProjectHome,String keyalias ,String storepass, String keypass)
        {
            String command = getEscapedString(JAVA_HOME + folderSeparator + "bin" + folderSeparator + "jarsigner", OS)+
                             " -keystore " + getEscapedString(ProjectHome+getFolderSeparator()+"keystore", OS) + " " +
                             " -storepass " + storepass + " -keypass " + keypass + " -signedjar " + 
                             getEscapedString(ProjectHome+getFolderSeparator()+"bin"+getFolderSeparator()+apkName+"Signed.apk", OS)+
                             " " + getEscapedString(ProjectHome+getFolderSeparator()+"bin"+getFolderSeparator()+apkName+"Unsigned.apk", OS)+
                             " " + keyalias;
            
            executeCommand2(command);
        }
        
        public void optimizeApk(String apkName, String ProjectHome)
        {
            String command = getEscapedString(ANDROID_HOME + folderSeparator + "tools" + folderSeparator + "zipalign", OS) + 
                             " -v -f 4 " + getEscapedString(ProjectHome + getFolderSeparator() + "bin" + getFolderSeparator() + apkName +"Signed.apk", OS)+
                             " " + getEscapedString(ProjectHome + getFolderSeparator() + "bin" + getFolderSeparator() + apkName +".apk", OS);
            
            executeCommand(command);
        }
        
        public static String getEscapedString(String string, OperatingSystem os)
        {
            if( os == OperatingSystem.Windows)
            {
                return "\"" + string + "\"";
            }
            
            // Else Unix Based
            String parts[] = string.split(" ");
            String escapedString = "";
            for( int index=0 ; index<parts.length; index++)
            {
                escapedString += index < (parts.length -1) ? parts[index] + "\\ " :
                                                             parts[index];
            }
            return escapedString;
        }
        
        
	public BufferedReader executeCommand(String command)
	{
            try 
            {
                ProcessBuilder builder = new ProcessBuilder(command);
                builder.redirectErrorStream(true);
                Process process = builder.start();
                Thread.sleep(1000);
                return new BufferedReader(new InputStreamReader(process.getInputStream()));
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return null;
	}

        public BufferedReader executeCommand2(String command)
	{
            // For Java Commands
            try 
            {
                //JOptionPane.showMessageDialog(null, command);
                Process process = Runtime.getRuntime().exec( command );
                Thread.sleep(1000);
                return new BufferedReader(new InputStreamReader(process.getInputStream()));
            }
            catch (Exception e)
            { 
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return null;
	}
        
        public void print(BufferedReader br)
        {
            if( br == null) {
                return;
            }
            String line;
            try 
            {
                line = br.readLine();
                while( line != null )
                {
                        System.out.println(line);
                        line = br.readLine(); 
                }
            } catch (IOException ex) 
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                //System.out.println(ex.getMessage());
            }	
        }
        
    public static String copyFile( String imagePath, String projectPath)
    {
        // Copy image to res/drawable in projectPath
        String folderSeparator = ApkGenerator.getFolderSeparator();
        File originImage = new File(imagePath);
        
        String splits[] = imagePath.split(  folderSeparator.equals("/") ? "/" : "\\\\"  );
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
