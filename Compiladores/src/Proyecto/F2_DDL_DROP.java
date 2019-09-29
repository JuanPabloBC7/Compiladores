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
    int h;
    
    public int NT_DROP(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        h = i;
        if(Object.getElementAt(h).equals("DROP")){
            h++;
            if(NT_TABLE(Object, Line, TokenType)){
                h++;
                if(NT_FIN(Object, Line, TokenType)){
                    h++;
                    ls_Sentencia_Hija_Errores += ("DROP Leido correctamente.\n");
                    return h;
                }
            }
        }
        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** DROP no leido correctamente\n");
        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** DROP no leido correctamente\n");
        return h;
    }
    
    public boolean NT_IF_EXISTS(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("IF")){
            h++;
            if(Object.getElementAt(h).equals("EXISTS")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'EXISTS'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'EXISTS'\n");
                return false;
            }
        } else {h--; return true;}
    }
    
    public boolean NT_TABLE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("TABLE")){
            h++;
            if(NT_IF_EXISTS(Object, Line, TokenType)){
                h++;
                if(NT_CONCAT(Object, Line, TokenType)){
                    return true;
                }
            }
        }else if(NT_INDEX(Object, Line, TokenType)){
            return true;
        }else if(NT_DATABASE(Object, Line, TokenType)){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'TABLE'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'TABLE'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_CONCAT(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_CONCAT_ID(Object, Line, TokenType)){
            h++;
            if(NT_CONCAT2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }
    
    public boolean NT_CONCAT2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(";")){
            h++;
            if(NT_CONCAT(Object, Line, TokenType)){
                return true;
            }
        }else{h--; return true;}
        return false;
    }
    
    public boolean NT_DATABASE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("DATABASE")){
            h++;
            if(NT_IF_EXISTS(Object, Line, TokenType)){
                h++;
                if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                    h++;
                    if(NT_DATABASE2(Object, Line, TokenType)){
                        return true;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    return false;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DATABASES'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DATABASES'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_DATABASE2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                h++;
                if(NT_DATABASE2(Object, Line, TokenType)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                return false;
            }
        }else {h--; return true;}
        return false;
    }
    
    public boolean NT_INDEX(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("INDEX")){
            h++;
            if(NT_IF_EXISTS(Object, Line, TokenType)){
                h++;
                if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                    h++;
                    if(NT_INDEX2(Object, Line, TokenType)){
                        return true;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    return false;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'INDEX'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'INDEX'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_INDEX2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_XML(Object, Line, TokenType)){
            return true;
        } else if(NT_BACKWARD(Object, Line, TokenType)){
            return true;
        }
        return false;
    }
    
    public boolean NT_CONCAT_ID(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            h++;
            if(NT_CONCAT_ID2(Object, Line, TokenType)){
                return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_CONCAT_ID2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(".")){
            h++;
            if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                h++;
                if(NT_CONCAT_ID3(Object, Line, TokenType)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                return false;
            }
        } else {h--; return true;}
        return false;
    }
    
    public boolean NT_CONCAT_ID3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(".")){
            h++;
            if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                return false;
            }
        } else {h--; return true;}
    }
    
    public boolean NT_XML(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("ON")){
            h++;
            if(NT_CONCAT_ID(Object, Line, TokenType)){
                h++;
                if(NT_XML2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ON'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ON'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_XML2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(NT_INDEX2(Object, Line, TokenType)){
                return true;
            }
        } else {h--; return true;}
        return false;
    }
    
    public boolean NT_BACKWARD(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_CONCAT_ID2(Object, Line, TokenType)){
            h++;
            if(NT_BACKWARD2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }
    
    public boolean NT_BACKWARD2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(NT_INDEX2(Object, Line, TokenType)){
                return true;
            }
        } else {h--; return true;}
        return false;
    }
    
    public boolean NT_FIN(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(";")){
            return true;
        } else if(Object.getElementAt(h).equals("GO")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'; OR GO'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'; OR GO'\n");
            return false;
        }
    }
}
