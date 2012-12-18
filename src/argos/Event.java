/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package argos;

/**
 *
 * @author richardsiwady
 */
public class Event {

    public Event(String eventName, String componentName, String fileName) {
        this.eventName = eventName;
        this.componentName = componentName;
        this.fileName = fileName;
    }
    public String componentName;
    public String fileName;
    public String eventName;
}
