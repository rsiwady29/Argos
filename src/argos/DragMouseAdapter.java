package argos;


import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 *
 * @author RichardSiwady
 */
class DragMouseAdapter extends MouseAdapter {

   private Point initialLoc=null;
   private Point initialLocOnScreen=null;
   

   @Override
   public void mousePressed(MouseEvent e) {
       if( SwingUtilities.isRightMouseButton(e) )
       {           
           final JPopupMenu menu = new JPopupMenu();
           menu.setLocation(e.getXOnScreen(), e.getYOnScreen());
           final Component c = e.getComponent();
           JMenuItem item = new JMenuItem("delete");
           
           item.addActionListener(new ActionListener() {

               @Override
               public void actionPerformed(ActionEvent e) {
                   Container container = c.getParent();                   
                   c.getParent().remove(c);        
                   menu.setVisible(false);
                   container.repaint();
               }
           });
           menu.add(item);
           menu.setVisible(true);
           
       }
       else{
            Component comp = (Component)e.getSource();
            if( comp == null)
            {
                return;
            }
            initialLoc = comp.getLocation();
            initialLocOnScreen = e.getLocationOnScreen();
       }
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      Component comp = (Component)e.getSource();
      if( comp == null || initialLocOnScreen == null || initialLoc == null)
      {
          return;
      }
      else
      {
        Point locOnScreen = e.getLocationOnScreen();

        int x = locOnScreen.x - initialLocOnScreen.x + initialLoc.x;
        int y = locOnScreen.y - initialLocOnScreen.y + initialLoc.y;
        comp.setLocation(x, y);
      }
   }

   @Override
   public void mouseDragged(MouseEvent e) {
      Component comp = (Component)e.getSource();
      if( comp == null)
      {
          return;
      }
      else
      {
        Point locOnScreen = e.getLocationOnScreen();

        int x = locOnScreen.x - initialLocOnScreen.x + initialLoc.x;
        int y = locOnScreen.y - initialLocOnScreen.y + initialLoc.y;
        comp.setLocation(x, y);
      }
   }
}