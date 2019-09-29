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
public class F2_DML_UPDATE {
    DefaultListModel ll_Sentencia_Hija_Archivo = new DefaultListModel();
    String ls_Sentencia_Hija_Errores = "";
    int h;
    
    public int NT_UPDATE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        h = i;
        if(Object.getElementAt(h).equals("UPDATE")){
            h++;
            if(NT_TOP(Object, Line, TokenType)){
                h++;
                if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                    h++;
                    if(Object.getElementAt(h).equals("SET")){
                        h++;
                        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                            h++;
                            if(Object.getElementAt(h).equals("=")){
                                h++;
                                if(NT_VALUE(Object, Line, TokenType)){
                                    h++;
                                    if(NT_MORE(Object, Line, TokenType)){
                                        h++;
                                        if(NT_WHERE(Object, Line, TokenType)){
                                            if(NT_FIN(Object, Line, TokenType)){
                                                h++;
                                                ls_Sentencia_Hija_Errores += ("UPDATE Leido correctamente.\n");
                                                return h;
                                            }
                                        }
                                    }
                                }
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'='\n");
                                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'='\n");
                                return h;
                            }
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                            return h;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'SET'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'SET'\n");
                        return h;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    return h;
                }
            }
        }
        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** UPDATE no leido correctamente\n");
        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** UPDATE no leido correctamente\n");
        return h;
    }
    public boolean NT_MORE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                h++;
                if(Object.getElementAt(h).equals("=")){
                    h++;
                    if(NT_VALUE(Object, Line, TokenType)){
                        h++;
                        if(NT_MORE(Object, Line, TokenType)){
                            return true;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'='\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'='\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                return false;
            }
        } else if(NT_SUMMAND(Object, Line, TokenType)){
            return true;
        } else {h--; return true;}
        return false;
    }
    public boolean NT_SUMMAND(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_FACTOR(Object, Line, TokenType)){
            h++;
            if(NT_SUMMAND2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }
    public boolean NT_SUMMAND2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("+")){
            h++;
            if(NT_FACTOR(Object, Line, TokenType)){
                h++;
                if(NT_SUMMAND2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else if(Object.getElementAt(h).equals("-")){
            h++;
            if(NT_FACTOR(Object, Line, TokenType)){
                h++;
                if(NT_SUMMAND2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean NT_FACTOR(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_VALUE(Object, Line, TokenType)){
            h++;
            if(NT_FACTOR2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }
    public boolean NT_FACTOR2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("*")){
            h++;
            if(NT_VALUE(Object, Line, TokenType)){
                h++;
                if(NT_FACTOR2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else if(Object.getElementAt(h).equals("/")){
            h++;
            if(NT_VALUE(Object, Line, TokenType)){
                h++;
                if(NT_FACTOR2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean NT_TOP(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("TOP")){
            h++;
            if(NT_EXPRESSION(Object, Line, TokenType)){
                h++;
                if(NT_PERCENT(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean NT_EXPRESSION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("(")){
            h++;
            if(NT_NUMBER(Object, Line, TokenType)){
                h++;
                if(Object.getElementAt(h).equals(")")){
                    return true;
                }
            }
        } else if(NT_NUMBER(Object, Line, TokenType)){
            return true;
        }
        return false;
    }
    public boolean NT_PERCENT(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("PERCENT")){
            return true;
        } else {h--; return true;}
    }
    public boolean NT_WHERE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("WHERE")){
            h++;
            if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                h++;
                if(NT_OP(Object, Line, TokenType)){
                    h++;
                    if(NT_VALUE(Object, Line, TokenType)){
                        h++;
                        if(NT_WHERE1(Object, Line, TokenType)){
                            return true;
                        }
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                return false;
            }
        } else {h--; return true;}
        return false;
    }
    public boolean NT_WHERE1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_LOGIC_OP(Object, Line, TokenType)){
            h++;
            if(NT_OTHER(Object, Line, TokenType)){
                return true;
            }
        } else {h--; return true;}
        return false;
    }
    public boolean NT_OTHER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            h++;
            if(NT_OP(Object, Line, TokenType)){
                h++;
                if(NT_VALUE(Object, Line, TokenType)){
                    h++;
                    if(NT_WHERE1(Object, Line, TokenType)){
                        return true;
                    }
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            return false;
        }
        return false;
    }
    public boolean NT_LOGIC_OP(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("AND")){
            return true;
        } else if(Object.getElementAt(h).equals("OR")){
            return true;
        } else if(Object.getElementAt(h).equals("&&")){
            return true;
        } else if(Object.getElementAt(h).equals("||")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'AND, OR, && OR ||'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'AND, OR, && OR ||'\n");
            return false;
        }
    }
    public boolean NT_VALUE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("CADENA")){
            return true;
        } else if(TokenType.getElementAt(h).equals("BOOLEANO")){
            return true;
        } else if(TokenType.getElementAt(h).equals("ENTERO")){
            return true;
        } else if(TokenType.getElementAt(h).equals("DECIMAL")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING, BOOLEAN OR NUMBER'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING, BOOLEAN OR NUMBER'\n");
            return false;
        }
    }
    public boolean NT_OP(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("<")){
            return true;
        } else if(Object.getElementAt(h).equals(">")){
            return true;
        } else if(Object.getElementAt(h).equals("<=")){
            return true;
        } else if(Object.getElementAt(h).equals(">=")){
            return true;
        } else if(Object.getElementAt(h).equals("!=")){
            return true;
        } else if(Object.getElementAt(h).equals("==")){
            return true;
        } else if(Object.getElementAt(h).equals("LIKE")){
            h++;
            if(TokenType.getElementAt(h).equals("CADENA")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("BETWEEN")){
            h++;
            if(NT_NUMBER(Object, Line, TokenType)){
                h++;
                if(Object.getElementAt(h).equals("AND")){
                    h++;
                    if(NT_NUMBER(Object, Line, TokenType)){
                        return true;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'AND'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'AND'\n");
                    return false;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'OPERATOR'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'OPERATOR'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_NUMBER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("CADENA")){
            return true;
        } else if(TokenType.getElementAt(h).equals("DECIMAL")){
            return true;
        } else if(TokenType.getElementAt(h).equals("ENTERO")){
            return true;
        } else if(TokenType.getElementAt(h).equals("BOOLEANO")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING OR NUMBER'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING OR NUMBER'\n");
            return false;
        }
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
