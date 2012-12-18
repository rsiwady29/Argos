
package Android;

import Semantics.Controls.*;
import java.util.ArrayList;
import java.util.Random;


public class RandomControlGenerator 
{
    // Based on my Cell phone WxH 480x800
    private final static int width = 480;
    private final static int height = 800;
    private final static int controlWidth = 100;
    private final static int controlHeight = 50;
    
    // DontTouch
    private static int correlative = 0; 
    public static Random rand = new Random();
    
    public static Button generateButton()
    {
        String name = "btn";
        Button btn = new Button();
        
        btn.setX(rand.nextInt(width)); 
        btn.setY(rand.nextInt(height));
        btn.setName(name+(correlative++));
        btn.setWidth(rand.nextInt(controlWidth) + 60);
        btn.setHeight(rand.nextInt(controlHeight) + 60 );
        btn.setEnabled(rand.nextBoolean());
        btn.setVisible(true);
        
        btn.setValue(name+correlative);
        return btn;
    }
    
    public static Label generateLabel()
    {
        String name = "lbl";
        Label lbl = new Label();
        
        lbl.setX(rand.nextInt(width)); 
        lbl.setY(rand.nextInt(height));
        lbl.setName(name+(correlative++));
        lbl.setWidth(rand.nextInt(controlWidth)+ 60);
        lbl.setHeight(rand.nextInt(controlHeight)+ 60);
        lbl.setEnabled(rand.nextBoolean());
        lbl.setVisible(true);
        
        lbl.setValue(name+correlative);
        return lbl;
    }
    
    public static TextBox generateTextBox()
    {
        String name = "txt";
        TextBox txt = new TextBox();
        
        txt.setX(rand.nextInt(width)); 
        txt.setY(rand.nextInt(height));
        txt.setName(name+(correlative++));
        txt.setWidth(rand.nextInt(controlWidth)+ 60);
        txt.setHeight(rand.nextInt(controlHeight)+ 60);
        txt.setEnabled(rand.nextBoolean());
        txt.setVisible(true);
        
        txt.setValue(name+correlative);
        return txt;
    }
    
    public static CheckBox generateCheckBox()
    {
        String name = "chkBox";
        CheckBox chkBox = new CheckBox();

        chkBox.setX(rand.nextInt(width)); 
        chkBox.setY(rand.nextInt(height));
        chkBox.setName(name+(correlative++));
        chkBox.setWidth(rand.nextInt(controlWidth)+ 60);
        chkBox.setHeight(rand.nextInt(controlHeight)+ 60);
        chkBox.setEnabled(rand.nextBoolean());
        chkBox.setVisible(true);
        
        chkBox.setText(name+correlative);
        //chkBox.setDirection();
        chkBox.setChecked(rand.nextBoolean());
        return chkBox;
    }
    
    public static DatePicker generateDatePicker()
    {
        String name = "datePicker";
        DatePicker datePicker = new DatePicker();
        
        datePicker.setX(rand.nextInt(width)); 
        datePicker.setY(rand.nextInt(height));
        datePicker.setName(name+(correlative++));
        datePicker.setWidth(rand.nextInt(controlWidth)+ 60);
        datePicker.setHeight(rand.nextInt(controlHeight)+ 60);
        datePicker.setEnabled(rand.nextBoolean());
        datePicker.setVisible(true);
        
        return datePicker;
    }
    
    public static RadioButton generateRadioButton()
    {
        String name = "radioBtn";
        RadioButton radioBtn = new RadioButton();
        
        radioBtn.setX(rand.nextInt(width)); 
        radioBtn.setY(rand.nextInt(height));
        radioBtn.setName(name+(correlative++));
        radioBtn.setWidth(rand.nextInt(controlWidth)+ 60);
        radioBtn.setHeight(rand.nextInt(controlHeight)+ 60);
        radioBtn.setEnabled(rand.nextBoolean());
        radioBtn.setVisible(true);
        
        radioBtn.setChecked(rand.nextBoolean());
        radioBtn.setText(name+correlative);
        return radioBtn;
    }
    
    public static Picture generatePicture(String imagePath)
    {
        String name = "pic";
        Picture pic = new Picture();
        
        pic.setX(rand.nextInt(width)); 
        pic.setY(rand.nextInt(height));
        pic.setName(name+(correlative++));
        pic.setWidth(rand.nextInt(controlWidth)+ 60);
        pic.setHeight(rand.nextInt(controlHeight)+ 60);
        pic.setEnabled(rand.nextBoolean());
        pic.setVisible(true);
        
        pic.setPath(imagePath);        
        return pic;
    }
    
    public static ListBox generateListBox(String projectPath)
    {
        String name = "listbox";
        String idName = name+(correlative++);
        ListBox listbox = new ListBox();
        
        listbox.setX(rand.nextInt(width)); 
        listbox.setY(rand.nextInt(height));
        listbox.setName(idName);
        listbox.setWidth(rand.nextInt(controlWidth)+ 150);
        listbox.setHeight(rand.nextInt(controlHeight)+ 260);
        listbox.setEnabled(rand.nextBoolean());
        listbox.setVisible(true);
        
        ArrayList<ListBoxItem> items = getRandomItems();
        listbox.setItems(items);
        
        return listbox;
    }
    
    public static ComboBox generateComboBox()
    {
        String name = "combobox";
        ComboBox combobox = new ComboBox();
        
        combobox.setX(rand.nextInt(width)); 
        combobox.setY(rand.nextInt(height));
        combobox.setName(name+(correlative++));
        combobox.setWidth(rand.nextInt(controlWidth)+ 60);
        combobox.setHeight(rand.nextInt(controlHeight)+ 60);
        combobox.setEnabled(rand.nextBoolean());
        combobox.setVisible(true);
        
        ArrayList<ListBoxItem> items = getRandomItems();
        combobox.setItems(items);
        
        return combobox;
    }
    
    private static ArrayList<ListBoxItem> getRandomItems()
    {
        ArrayList<ListBoxItem> items = new ArrayList<ListBoxItem>();
        ListBoxItem item = new ListBoxItem();
        item.setText("jaja");
        item.setValue("jaja");
        items.add(item);
        
        item.setText("why");
        item.setValue("why");
        items.add(item);
        
        item.setText("lala");
        item.setValue("lala");
        items.add(item);
        
        return items;
    }
}
