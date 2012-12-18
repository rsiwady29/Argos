package Android;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//import proyectocompi2.GeneradorExpresion;
import Semantics.Information.BuiltInFunctions;
import Semantics.Statements.*;
import java.util.*;
import Semantics.Types.*;

/**
 *
 * @author Camilo
 */
public  class GeneradorSentencia {
    
    private static String GenerarSentenciaCodigo(Statement sentencia){
        String codigo = "";
        if(sentencia instanceof If){
            If iff = (If)sentencia;
            codigo += "if("+ GeneradorExpresion.GenerarExpresionCodigo(iff.getCondition())+"){";
            if(iff.getTrueStatement() != null)
            {
                codigo += GenerarCodigo(iff.getTrueStatement()) + "}";
            }
            else
            {
                codigo += "}";
            }
            
            if(iff.getFalseStatement()!= null){
                codigo += "else{"+GenerarCodigo(iff.getFalseStatement())+"}";
                
            }
            
        }
                
        if(sentencia instanceof Assign){
            Assign asg = (Assign)sentencia;
            codigo += GeneradorExpresion.GenerarExpresionCodigo(asg.getVariable()) + " = "+ GeneradorExpresion.GenerarExpresionCodigo(asg.getValue())+";\n";   
        }
        
        if(sentencia instanceof For){
            For forr = (For)sentencia;
            codigo+= "for("+forr.getVariable().getLexeme()+" = "+GeneradorExpresion.GenerarExpresionCodigo(forr.getBegin())+"; "+forr.getVariable().getLexeme()+" <= "+GeneradorExpresion.GenerarExpresionCodigo(forr.getEnd())+"; "+forr.getVariable().getLexeme()+" ++){\n";
            if(forr.getCode()!=null){
            codigo+= GenerarCodigo(forr.getCode());
            }
            codigo+= "\n}";
            
        }
        
        if(sentencia instanceof Repeat){
            Repeat repeat = (Repeat)sentencia;
            codigo += "do{\n";
            if(repeat.getCode() != null)
                codigo+= GenerarCodigo(repeat.getCode());
            codigo+="\n}while("+GeneradorExpresion.GenerarExpresionCodigo(repeat.getCondition())+");";
            
            
        }
        if(sentencia instanceof While){
            While while_ = (While)sentencia;
            codigo+="while("+GeneradorExpresion.GenerarExpresionCodigo(while_.getCondition())+"){\n";
            if(while_.getCode() != null)
                codigo+=GenerarCodigo(while_.getCode());
            codigo += "}\n";
        }
        if(sentencia instanceof CallFunctionStatement)
        {
            CallFunctionStatement call = (CallFunctionStatement)sentencia;
            if(BuiltInFunctions.getInstance().existsFunction(call.getId())!=null)
            {
                codigo += "Global.";
            }
            
            codigo+= call.getId() + "(";
            
            // ====================== AGREGADO =================================
            if(call.getId().equals("Msgbox") || call.getId().equals("SetItems")
                    || call.getId().equals("OpenWindow"))
            {
                if( codigo.contains("\""))
                {
                    codigo = codigo.substring(1,codigo.length()-1);
                }

                codigo += "getApplicationContext(), ";
            }
            // =================================================================
            for(int i=0; i<call.getParams().size(); i++)
            {
                codigo += GeneradorExpresion.GenerarExpresionCodigo(call.getParams().get(i));
                // ====================== AGREGADO =================================
                if(call.getId().equals("OpenWindow"))
                {
                    if( codigo.contains("\""))
                    {
                        codigo = codigo.replace("\"", "");
                    }
                    codigo += ".class";
                }
                // =================================================================
                if(i != (call.getParams().size() - 1))
                    codigo += ", ";
            }
            codigo += ");\n";
        }
        
        return codigo;
    }   
    
    public static String GenerarCodigo(Statement sentencia){
        String codigo = "";
        
        if( sentencia == null)
        {
            return codigo;
        }
        codigo += GenerarSentenciaCodigo(sentencia);        
        if(sentencia.getNext() != null){
            
            codigo += GenerarCodigo(sentencia.getNext());
        }
        
        return codigo;
    }
    
    
    //========================================================================
    // ========================== AGREGADO!!!!! ==============================
    //========================================================================
    public static String GetCode_SymbolTable(Hashtable<String,Type> hash){
        String codigo = "";
        
        if( hash == null)
        {
            return codigo;
        }
        
        Enumeration e = hash.keys();
        Object key;
        while( e.hasMoreElements() ){
            key = e.nextElement();
            String temp = (String)key;
            codigo += GetVariableCode(temp, hash.get(temp));
        }
        
        return codigo;
    }
    
    private static String GetVariableCode(String name, Type type)
    {
        String codigo = "";
        if(type instanceof Array_Type)
        {
            //TO DO:
            Array_Type arr_type = (Array_Type)type;
            String strType = "";
            
            if(arr_type.getContent() instanceof Boolean_Type)
                strType = "\tboolean";
            
            if(arr_type.getContent() instanceof Char_Type)
                strType = "\tchar";
            
            if(arr_type.getContent() instanceof Date_Type)
                //TO DO:
                strType = "\tDate";
            
            if(arr_type.getContent() instanceof Float_Type)
                strType = "\tfloat";
            
            if(arr_type.getContent() instanceof Integer_Type)
                strType = "\tint";
            
            if(arr_type.getContent() instanceof String_Type)
                strType = "\tString";
            
            
            ArrayList<Dimension> dimensions = arr_type.getDimensions();
            
            codigo = strType + " " + name;
            for(int i=0; i<dimensions.size(); i++)
                codigo += "[]";
            
            codigo += " = new " + strType;
            for(Dimension d: dimensions)
                codigo += "[" + d.getEnd() + "]";
            
            codigo += ";\n";
        }
        
        
        if(type instanceof Boolean_Type)
            codigo = "\tboolean " + name + ";\n";
        
        if(type instanceof Char_Type)
            codigo = "\tchar " + name + ";\n";
        
        if(type instanceof Date_Type)
            codigo = "\tDate " + name + " = new Date();\n";
        
        if(type instanceof Float_Type)
            codigo = "\tfloat " + name + " = 0;\n";
        
        
        if(type instanceof Integer_Type)
            codigo = "\tint " + name + " = 0;\n";
        
        if(type instanceof String_Type)
            codigo = "\tString " + name + " = \"\";\n";
        
        
        return codigo;
    }
}
