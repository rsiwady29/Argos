package GWT;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 
import Semantics.Expression.*;
import Semantics.Expression.Operators.*;
import Semantics.Expression.Literals.*;
import Semantics.Information.BuiltInFunctions;


/**
 *
 * @author Camilo
 */
public  class GeneradorExpresion {
    
     public static String GenerarExpresionCodigo(Expression expresion){
        String codigo = "";
        if(expresion instanceof Xor){      
            Xor xor = (Xor)expresion;
            codigo+= "("+GenerarExpresionCodigo(xor.getLeft()) + ") ^ (" + GenerarExpresionCodigo(xor.getRight())+")";          
        }
        if(expresion instanceof Mod){      
            Mod mod = (Mod)expresion;
            codigo+= "("+GenerarExpresionCodigo(mod.getLeft()) + ") % (" + GenerarExpresionCodigo(mod.getRight())+")";          
        }
        if(expresion instanceof Div){      
            Div div = (Div)expresion; 
            codigo+= "Math.floor(("+GenerarExpresionCodigo(div.getLeft()) + ") / (" + GenerarExpresionCodigo(div.getRight())+"))";          
        }
        if(expresion instanceof GreaterThan){      
            GreaterThan GT = (GreaterThan)expresion;
            codigo+= "("+GenerarExpresionCodigo(GT.getLeft()) + ") > (" + GenerarExpresionCodigo(GT.getRight())+")";          
        }
        if (expresion instanceof Add){
            Add add = (Add)expresion;
            codigo+= "("+GenerarExpresionCodigo(add.getLeft()) + ") + (" + GenerarExpresionCodigo(add.getRight())+")"; 
        }
        
        if (expresion instanceof And){
            And and = (And)expresion;
            codigo+= "("+GenerarExpresionCodigo(and.getLeft()) + ") && (" + GenerarExpresionCodigo(and.getRight())+")"; 
        }
        
        if (expresion instanceof Divide){
            Divide divide = (Divide)expresion;
            codigo+= "("+GenerarExpresionCodigo(divide.getLeft()) + ") / (" + GenerarExpresionCodigo(divide.getRight())+")"; 
        }
        
        if (expresion instanceof Equal){
            Equal equal = (Equal)expresion;
            codigo+= "("+GenerarExpresionCodigo(equal.getLeft()) + ") == (" + GenerarExpresionCodigo(equal.getRight())+")"; 
        }
        
        if (expresion instanceof GreaterEqualThan){
            GreaterEqualThan GTE = (GreaterEqualThan)expresion;
            codigo+= "("+GenerarExpresionCodigo(GTE.getLeft()) + ") >= (" + GenerarExpresionCodigo(GTE.getRight())+")"; 
        }
        
        if (expresion instanceof LessEqualThan){
            LessEqualThan LTE = (LessEqualThan)expresion;
            codigo+= "("+GenerarExpresionCodigo(LTE.getLeft()) + ") <= (" + GenerarExpresionCodigo(LTE.getRight())+")"; 
        }
        
        if (expresion instanceof LessThan){
            LessThan LT = (LessThan)expresion;
            codigo+= "("+GenerarExpresionCodigo(LT.getLeft()) + ") < (" + GenerarExpresionCodigo(LT.getRight())+")"; 
        }
        
        if (expresion instanceof Multiply){
            Multiply mult = (Multiply)expresion;
            codigo+= "("+GenerarExpresionCodigo(mult.getLeft()) + ") * (" + GenerarExpresionCodigo(mult.getRight())+")"; 
        }
        
        if (expresion instanceof NotEqual){
            NotEqual NE = (NotEqual)expresion;
           codigo+= "("+GenerarExpresionCodigo(NE.getLeft()) + ") != (" + GenerarExpresionCodigo(NE.getRight())+")"; 
        }
        
        if (expresion instanceof Or){
            Or or = (Or)expresion;
           codigo+= "("+GenerarExpresionCodigo(or.getLeft()) + ") || (" + GenerarExpresionCodigo(or.getRight())+")"; 
        }
        
        if (expresion instanceof Substract){
            Substract sub = (Substract)expresion;
           codigo+= "("+GenerarExpresionCodigo(sub.getLeft()) + ") - (" + GenerarExpresionCodigo(sub.getRight())+")"; 
        }
        
        if (expresion instanceof Not){
            Not not = (Not)expresion;
           codigo+= "! (" + GenerarExpresionCodigo(not.getRight())+")"; 
        }
               
                
        if (expresion instanceof CallFunction)
        {
            CallFunction call = (CallFunction)expresion;
            if(BuiltInFunctions.getInstance().existsFunction(call.getLexeme())!=null)
            {
                codigo += "Global.";
            }
            
                if(call.getLexeme() == "OpenWindow")
                {
                    codigo = "";
                }
                else
                {
            codigo+= call.getLexeme() + "(";
            for(int i=0; i<call.getParams().size(); i++)
            {
                codigo += GenerarExpresionCodigo(call.getParams().get(i));                
                if(i==0 && BuiltInFunctions.getInstance().existsFunction(call.getLexeme())!=null && call.getParams().get(0) instanceof Variable)
                {
                    codigo += "_"+Globales.ventanaActual;
                }
                if(i != (call.getParams().size() - 1))
                    codigo += ", ";
            }
            codigo += ")";
                }
        }
        else if (expresion instanceof ArrayVariable){
            ArrayVariable array = (ArrayVariable)expresion;
            codigo+= array.getLexeme();
            for(int i=0;i<=array.getIndexes().size();i++){
                codigo+= "["+GenerarExpresionCodigo(array.getIndexes().get(i))+"]";
            }
            codigo+= ";";
        }
        else if (expresion instanceof Variable){
            Variable var = (Variable)expresion;
            codigo+= var.getLexeme();
        }
        
       if(expresion instanceof Integer_Lit){
         Integer_Lit INT = (Integer_Lit)expresion;
         codigo+= INT.getValue();
     
            }
       
       if(expresion instanceof Boolean_Lit){
           Boolean_Lit bo = (Boolean_Lit)expresion;
           if(bo.isValue()){
               
               codigo+="true";
           }else{
               
               codigo+="false";
           }
       }
       
       if(expresion instanceof Char_Lit){
           Char_Lit ch = (Char_Lit)expresion;
           codigo+= "/'"+String.valueOf(ch.getValue())+"\'";
           
           
       }
       
       if(expresion instanceof Float_Lit){
           Float_Lit fl = (Float_Lit)expresion;
           codigo += String.valueOf(fl.getValue());
       }
       
       if(expresion instanceof String_Lit){
           String_Lit sl = (String_Lit)expresion;
           codigo += "\""+ sl.getValue()+"\"";
       }
       
       if(expresion instanceof Date_Lit){
           Date_Lit dl = (Date_Lit)expresion;
           
           codigo+= dl.getValue().toString();
       }
       
       
        
        
        return codigo;
    }
    
}
