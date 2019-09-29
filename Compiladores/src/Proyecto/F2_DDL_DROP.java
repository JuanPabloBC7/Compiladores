/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.io.IOException;
import javax.swing.DefaultListModel;

/**
 *
 * @author jpbalan
 */
public class F2_DDL_DROP {
    DefaultListModel ll_Sentencia_Hija_Archivo = new DefaultListModel();
    String ls_Sentencia_Hija_Errores = "";
    
    public int NT_DROP(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("DROP")){
            i++;
            if(NT_TABLE(Object, Line, TokenType, i)){
                i++;
                if(NT_FIN(Object, Line, TokenType, i)){
                    ll_Sentencia_Hija_Archivo.addElement("DROP Leido correctamente.");
                }
            }
        }
        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** DROP no leido correctamente\n");
        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** DROP no leido correctamente\n");
        return i;
    }
    
    public boolean NT_IF_EXISTS(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("IF")){
            i++;
            if(Object.getElementAt(i).equals("EXISTS")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'EXISTS'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'EXISTS'\n");
                return false;
            }
        } else { return true; }
    }
    
    public boolean NT_TABLE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("TABLE")){
            i++;
            if(NT_IF_EXISTS(Object, Line, TokenType, i)){
                i++;
                if(NT_CONCAT(Object, Line, TokenType, i)){
                    return true;
                }
            }
        }else if(NT_INDEX(Object, Line, TokenType, i)){
            return true;
        }else if(NT_DATABASE(Object, Line, TokenType, i)){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'TABLE'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'TABLE'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_CONCAT(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_CONCAT_ID(Object, Line, TokenType, i)){
            i++;
            if(NT_CONCAT2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }
    
    public boolean NT_CONCAT2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(";")){
            i++;
            if(NT_CONCAT(Object, Line, TokenType, i)){
                return true;
            }
        }else{ return true; }
        return false;
    }
    
    public boolean NT_DATABASE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("DATABASE")){
            i++;
            if(NT_IF_EXISTS(Object, Line, TokenType, i)){
                i++;
                if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                    i++;
                    if(NT_DATABASE2(Object, Line, TokenType, i)){
                        return true;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                    return false;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DATABASES'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DATABASES'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_DATABASE2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
            i++;
            if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                i++;
                if(NT_DATABASE2(Object, Line, TokenType, i)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                return false;
            }
        }else {return true;}
        return false;
    }
    
    public boolean NT_INDEX(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("INDEX")){
            i++;
            if(NT_IF_EXISTS(Object, Line, TokenType, i)){
                i++;
                if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                    i++;
                    if(NT_INDEX2(Object, Line, TokenType, i)){
                        return true;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                    return false;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'INDEX'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'INDEX'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_INDEX2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_XML(Object, Line, TokenType, i)){
            return true;
        } else if(NT_BACKWARD(Object, Line, TokenType, i)){
            return true;
        }
        return false;
    }
    
    public boolean NT_CONCAT_ID(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            i++;
            if(NT_CONCAT_ID2(Object, Line, TokenType, i)){
                return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_CONCAT_ID2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(".")){
            i++;
            if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                i++;
                if(NT_CONCAT_ID3(Object, Line, TokenType, i)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                return false;
            }
        } else { return true; }
        return false;
    }
    
    public boolean NT_CONCAT_ID3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(".")){
            i++;
            if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                return false;
            }
        } else { return true; }
    }
    
    public boolean NT_XML(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("ON")){
            i++;
            if(NT_CONCAT_ID(Object, Line, TokenType, i)){
                i++;
                if(NT_XML2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ON'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ON'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_XML2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
            i++;
            if(NT_INDEX2(Object, Line, TokenType, i)){
                return true;
            }
        } else { return true; }
        return false;
    }
    
    public boolean NT_BACKWARD(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_CONCAT_ID2(Object, Line, TokenType, i)){
            i++;
            if(NT_BACKWARD2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }
    
    public boolean NT_BACKWARD2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
            i++;
            if(NT_INDEX2(Object, Line, TokenType, i)){
                return true;
            }
        } else { return true; }
        return false;
    }
    
    public boolean NT_FIN(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(";")){
            return true;
        } else if(Object.getElementAt(i).equals("GO")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'; OR GO'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'; OR GO'\n");
            return false;
        }
    }
}
