/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Android;
import Semantics.Controls.Window;

/**
 *
 * @author Moises
 */
         
public class WindowCode {
    String windowName;
    String windowCode;
    Window window;

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public WindowCode(String windowName, String windowCode, Window window) {
        this.windowName = windowName;
        this.windowCode = windowCode;
        this.window = window;
    }

    public String getWindowCode() {
        return windowCode;
    }

    public void setWindowCode(String windowCode) {
        this.windowCode = windowCode;
    }

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }
    
    
}
