package Android;

import Semantics.Controls.*;
import Semantics.Expression.*;
import Semantics.Expression.Literals.*;
import Semantics.Expression.Operators.*;
import Semantics.Information.*;
import Semantics.Statements.*;
import Semantics.Types.*;
     
import java.util.ArrayList;
import java.util.*;
/**
 *
 * @author Moises
 */
    //TO DO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // - ListBox
    // - RadioButton (Revisar este: no se si utilizar RadioButton o RadioGroup)


public class ControlsCodeGenerator {
    
    Program program = null;
    
    public ControlsCodeGenerator(Program program)
    {
        this.program = program;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
    
    public ArrayList<WindowCode> getProgramCode()
    {
        ArrayList<WindowCode> windows = new ArrayList<WindowCode>();
        
        for(Window window: program.getWindowsList())
        {
            String codigo = getWindowCode(window);
            windows.add(new WindowCode(window.getName(), codigo, window));
        }
        
        return windows;
    }   
    
    private String getWindowCode(Window window)
    {
        String controls = "";
        
        //CODIGO SOLO CONTIENE LO QUE VAN DENTRO DE LA FUNCION ONCREATE
        StringBuilder codigo = new StringBuilder();
        
        //CODIGOFINAL ES LA COMBINACION DE VARIABLES LOCALES Y EL ONCREATE
        StringBuilder codigoFinal = new StringBuilder();
        Statement statements = window.getLoad().getStatements();
        Hashtable<String,Type> symbolsTable = window.getLoad().getSymbolTable();
        
        String codControl = "";
        
        for(Control c: window.getChildren())
        {
            
            if(c instanceof Button){
                Button b = (Button)c;
                
                String btnName = c.getName();
                codControl += "\tfinal Button " + b.getName() + " = (Button)this.findViewById(R.id." + btnName + ");\n";
                
                codigo.append(getButtonCode(b)).append("\n");
            }
            if(c instanceof Label){
                Label l = (Label)c;
                
                String lblName = c.getName();
                codControl += "\tfinal TextView " + l.getName() + " = (TextView)this.findViewById(R.id." + lblName + ");\n";
                
            }
            if(c instanceof TextBox){
                TextBox t = (TextBox)c;
                
                String txtName = t.getName();
                codControl += "\tfinal EditText " + t.getName() + " = (EditText)this.findViewById(R.id." + txtName + ");\n";
                
                
                codigo.append(getTextBoxCode(t)).append("\n");
            }
            if(c instanceof ComboBox){
                ComboBox cb = (ComboBox)c;
                
                String cmbBoxName = cb.getName();
                codControl += "\tfinal Spinner " + cb.getName() + " = (Spinner)this.findViewById(R.id." + cmbBoxName+ ");\n";
                
                codigo.append(getComboBoxCode(cb)).append("\n");
            }
            if(c instanceof ListBox){
                ListBox l = (ListBox)c;
                
                String listBoxName = l.getName();
                codControl += "\tfinal ListView " + l.getName() + " = (ListView)this.findViewById(R.id." + listBoxName+ ");\n";
                
                codigo.append(getListBoxCode(l)).append("\n");
            }
            if(c instanceof RadioButton){
                RadioButton rb = (RadioButton)c;
                
                String radioButton = rb.getName();
                codControl += "\tfinal RadioButton " + rb.getName() + " = (RadioButton)this.findViewById(R.id." + radioButton + ");\n";
                
                codigo.append(getRadioButton(rb)).append("\n");
            }
            if(c instanceof CheckBox){
                CheckBox cb = (CheckBox)c;
                
                String checkBoxName = cb.getName();
                codControl += "\tfinal CheckBox " + cb.getName() + " = (CheckBox)this.findViewById(R.id." + checkBoxName + ");\n";
                
                codigo.append(getCheckBoxCode(cb)).append("\n");
            }
            if(c instanceof Picture){
                Picture p = (Picture)c;
                
                String pictureName = p.getName();
                codControl += "\tfinal ImageView " + p.getName() + " = (ImageView)this.findViewById(R.id." + pictureName + ");\n";
                
                codigo.append(getPictureCode(p)).append("\n");
            }     
            if(c instanceof DatePicker){
                codControl += "\tfinal DatePicker " + c.getName() + " = (DatePicker)this.findViewById(R.id." + c.getName() + ");\n";
            }
            
        }
                
        //declaro la clase y le agrego los controles y variables locales
        codigoFinal.append("public class ").append(window.getName()).append(" extends Activity{\n");
        codigoFinal.append(controls);
        codigoFinal.append("\n");
        codigoFinal.append(GeneradorSentencia.GetCode_SymbolTable(symbolsTable)).append("\n");
        
        //declaro la funcion OnCreate y le agrego el codigo de los demas controles
        codigoFinal.append("\t@Override\n").append("\tpublic void onCreate(Bundle savedInstanceState) {");
        codigoFinal.append("\n\t\tsuper.onCreate(savedInstanceState);\n");
        codigoFinal.append("\t\tsetContentView(R.layout.").append(window.getName()).append(");\n");
        codigoFinal.append(codControl).append("\n\n");


        codigoFinal.append(GeneradorSentencia.GenerarCodigo(statements)).append("\n\n");

        codigoFinal.append(codigo.toString());
        
        codigoFinal.append("\n}\n}");
        
        return codigoFinal.toString();
    }
    
    private String getButtonCode(Button button)
    {
        if(button.getClick().getStatements() == null)
        {
            return "";
        }     
        String btnName = button.getName();
        StringBuilder codigo = new StringBuilder();

        codigo.append(btnName).append(".setOnClickListener(new OnClickListener() {\n\t\t");
        codigo.append("public void onClick(View v) {\n\n\t\t\t");
        
        Statement statements = button.getClick().getStatements();
        Hashtable<String,Type> symbolsTable = button.getClick().getSymbolTable();
        
        if(symbolsTable != null)
            codigo.append(GeneradorSentencia.GetCode_SymbolTable(symbolsTable)).append("\n");

        
        codigo.append(GeneradorSentencia.GenerarCodigo(statements)).append("\n");
        
        codigo.append("\t\t}\n;");
        codigo.append("\t});\n\n");
        
        return codigo.toString();
    }
    
    private String getTextBoxCode(TextBox textBox)
    {
        if(textBox.getOnTextChanged().getStatements() == null)
        {
            return "";
        }
        
        String txtName = textBox.getName();
        StringBuilder codigo = new StringBuilder();
                
        codigo.append(txtName).append(".setOnKeyListener(new OnKeyListener() {\n\t\t");
        codigo.append("public boolean onKey(View v, int keyCode, KeyEvent event) {\n\n\t\t\t");

        Statement statements = textBox.getOnTextChanged().getStatements();
        Hashtable<String,Type> symbolsTable = textBox.getOnTextChanged().getSymbolTable();
       
        if(symbolsTable != null)
            codigo.append(GeneradorSentencia.GetCode_SymbolTable(symbolsTable)).append("\n");
        codigo.append(GeneradorSentencia.GenerarCodigo(statements)).append("\n");
        
        codigo.append("\t\t}\n;");
        codigo.append("\t});\n\n");
        
        return codigo.toString();
    }
    
    
    private String getComboBoxCode(ComboBox comboBox)
    {
        if(comboBox.getItemChanged().getStatements() == null)
        {
            return "";
        }
        
        String cmbBoxName = comboBox.getName();
        StringBuilder codigo = new StringBuilder();
        
        codigo.append(cmbBoxName).append(".setOnItemSelectedListener(new OnItemSelectedListener() {\n\t\t");

        Statement statements = comboBox.getItemChanged().getStatements();
        Hashtable<String,Type> symbolsTable = comboBox.getItemChanged().getSymbolTable();
        
        if(symbolsTable != null)
            codigo.append(GeneradorSentencia.GetCode_SymbolTable(symbolsTable)).append("\n");
        codigo.append(GeneradorSentencia.GenerarCodigo(statements)).append("\n");
        
        codigo.append("\t});\n\n");
        
        return codigo.toString();
    }
    
    
    private String getListBoxCode(ListBox listBox)
    {
        if(listBox.getItemChanged().getStatements() == null)
        {
            return "";
        }
        
        String listBoxName = listBox.getName();
        StringBuilder codigo = new StringBuilder();
        
        codigo.append(listBoxName).append(".setOnItemSelectedListener(new OnItemSelectedListener() {\n\t\t");
        
        Statement statements = listBox.getItemChanged().getStatements();
        Hashtable<String,Type> symbolsTable = listBox.getItemChanged().getSymbolTable();

        if(symbolsTable != null)
            codigo.append(GeneradorSentencia.GetCode_SymbolTable(symbolsTable)).append("\n");
        codigo.append(GeneradorSentencia.GenerarCodigo(statements)).append("\n");
        
        codigo.append("\t});\n\n");
        
        return codigo.toString();
    }
    
    
    private String getRadioButton(RadioButton radioButton)
    {
        if(radioButton.getOnValueChanged().getStatements() == null)
        {
            return "";
        }
        
        String radioBtnName = radioButton.getName();
        StringBuilder codigo = new StringBuilder();
        
        codigo.append(radioBtnName).append(".setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {\n\t\t");
        codigo.append("public void onCheckedChanged(RadioGroup group, int checkedId) {\n\n\t\t\t");
        
        Statement statements = radioButton.getOnValueChanged().getStatements();
        Hashtable<String,Type> symbolsTable = radioButton.getOnValueChanged().getSymbolTable();

        if(symbolsTable != null)
            codigo.append(GeneradorSentencia.GetCode_SymbolTable(symbolsTable)).append("\n");
        codigo.append(GeneradorSentencia.GenerarCodigo(statements)).append("\n");
                
        codigo.append("\t\t}\n;");
        codigo.append("\t});\n\n");
        
        return codigo.toString();
    }
    
    
    private String getCheckBoxCode(CheckBox checkBox)
    {
        if(checkBox.getOnValueChanged().getStatements() == null)
        {
            return "";
        }
        
        String checkBoxName = checkBox.getName();
        StringBuilder codigo = new StringBuilder();
                
        codigo.append(checkBoxName).append(".setOnCheckedChangeListener(new OnCheckedChangeListener() {\n\t\t");
        codigo.append("public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {\n\n\t\t\t");
        
        Statement statements = checkBox.getOnValueChanged().getStatements();
        Hashtable<String,Type> symbolsTable = checkBox.getOnValueChanged().getSymbolTable();

        if(symbolsTable != null)
            codigo.append(GeneradorSentencia.GetCode_SymbolTable(symbolsTable)).append("\n");
        codigo.append(GeneradorSentencia.GenerarCodigo(statements)).append("\n");
        
        codigo.append("\t\t}\n;");
        codigo.append("\t});\n\n");
        
        return codigo.toString();
    }
    
    private String getPictureCode(Picture picture)
    {
        if(picture.getClick().getStatements() == null)
        {
            return "";
        }
        
        String pictureName = picture.getName();
        StringBuilder codigo = new StringBuilder();
        
        codigo.append(pictureName).append(".setOnClickListener(new OnClickListener() {\n\t\t");
        codigo.append("public void onClick(View v) {\n\n\t\t\t");

        Statement statements = picture.getClick().getStatements();
        Hashtable<String,Type> symbolsTable = picture.getClick().getSymbolTable();
        
        if(symbolsTable != null)
            codigo.append(GeneradorSentencia.GetCode_SymbolTable(symbolsTable)).append("\n");

        codigo.append(GeneradorSentencia.GenerarCodigo(statements)).append("\n");
        
        codigo.append("\t\t}\n;");
        codigo.append("\t});\n\n");
        
        return codigo.toString();
    }
    
    
}
