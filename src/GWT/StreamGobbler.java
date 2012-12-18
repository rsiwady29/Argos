/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GWT;

import java.io.*;
import javax.swing.JTextArea;

/**
 *
 * @author usuario
 */
public class StreamGobbler extends Thread
{
    InputStream is;
    String type;
    OutputStream os;
    JTextArea log;
    
    StreamGobbler(InputStream is, String type, JTextArea log)
    {
        this(is, type, null, log);
    }
    StreamGobbler(InputStream is, String type, OutputStream redirect, JTextArea log)
    {
        this.is = is;
        this.type = type;
        this.os = redirect;
        this.log = log;
    }
    
    public void run()
    {
        try
        {
            PrintWriter pw = null;
            if (os != null)
                pw = new PrintWriter(os);
                
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line=null;
            while ( (line = br.readLine()) != null)
            {
                if (pw != null)
                    pw.println(line);
                log.append(type + ">" + line+"\n");
            }
            if (pw != null)
                pw.flush();
        } catch (IOException ioe)
            {
            ioe.printStackTrace();  
            }
    }
}
