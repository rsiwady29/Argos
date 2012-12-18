package argos;


import javax.swing.ImageIcon;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RichardSiwady
 */
public class Tool 
{
  private final String title;
  private final String imagePath;
  private ImageIcon image;

  public Tool(String title, String imagePath) 
  {
    this.title = title;
    this.imagePath = imagePath;
  }

  public String getTitle() 
  {
    return title;
  }

  public ImageIcon getImage() 
  {
    if (image == null) 
    {
      image = new ImageIcon(imagePath);
    }
    return image;
  }

  public String toString() 
  {
    return title;
  }
  
}
