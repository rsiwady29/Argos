package argos;


import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RichardSiwady
 */
public class ToolCellRenderer extends JLabel implements ListCellRenderer{
  
  private static final Color HIGHLIGHT_COLOR = new Color(255, 226, 179);
  Color c;
  public ToolCellRenderer() {
    c = getBackground();
    setOpaque(true);
    setIconTextGap(12);
  }

  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
  {
        Tool entry = (Tool) value;
        setText(entry.getTitle());
        setIcon(entry.getImage());
        if (isSelected) 
        {
          setBackground(HIGHLIGHT_COLOR);
        }
        else 
        {
          setBackground(c);
        }
        return this;
  }
}
