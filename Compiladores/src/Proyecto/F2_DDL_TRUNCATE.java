/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import javax.swing.DefaultListModel;

/**
 *
 * @author jpbalan
 */
public class F2_DDL_TRUNCATE {
    String ls_Sentencia_Hija_Errores = "";
    int h; 
    
    public int NT_TRUNCATE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        h = i;
        if(Object.getElementAt(h).equals("TRUNCATE")){
            h++;
            if(Object.getElementAt(h).equals("TABLE")){
                h++;
                if(NT_ID(Object, Line, TokenType)){
                    h++;
                    if(NT_FIN(Object, Line, TokenType)){
                        h++;
                        ls_Sentencia_Hija_Errores = ("TRUNCATE Leido correctamente.\n");
                        return h;
                    }
                }
            } else {
                
                return h;
            }
        }
        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** TRUNCATE no leido correctamente\n");
        return h;
    }
    
    public boolean NT_ID(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            h++;
            if(NT_ID2(Object, Line, TokenType)){
                return true;   
            }
        }else{
            return false;
        }
        return false;
    }
    
    public boolean NT_ID2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(".")){
            h++;
            if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                h++;
                if(NT_ID3(Object, Line, TokenType)){
                    return true;
                }
            } else if(Object.getElementAt(h).equals(".")){
                h++;
                if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                    return true;
                } else {
                    return false;
                }
            }
        } else{h--; return true;}
        return false;
    }
    
    public boolean NT_ID3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(".")){
            h++;
            if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                return true;
            } else {
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                return false;
            }
        }else{h--; return true;}
    }
    
    public boolean NT_FIN(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(";")){
            return true;
        } else if(Object.getElementAt(h).equals("GO")){
            return true;
        } else {
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'; OR GO'\n");
            return false;
        }
    }
}
