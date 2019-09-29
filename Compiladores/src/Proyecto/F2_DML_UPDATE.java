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

    public int NT_UPDATE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("UPDATE")){
            i++;
            if(NT_TOP(Object, Line, TokenType, i)){
                i++;
                if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                    i++;
                    if(Object.getElementAt(i).equals("SET")){
                        i++;
                        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                            i++;
                            if(Object.getElementAt(i).equals("=")){
                                i++;
                                if(NT_VALUE(Object, Line, TokenType, i)){
                                    i++;
                                    if(NT_MORE(Object, Line, TokenType, i)){
                                        i++;
                                        if(NT_WHERE(Object, Line, TokenType, i)){
                                            return i;
                                        }
                                    }
                                }
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'='\n");
                                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'='\n");
                                return i;
                            }
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                            return 1;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'SET'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'SET'\n");
                        return 1;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                    return 1;
                }
            }
        }
        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** UPDATE no leido correctamente\n");
        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** UPDATE no leido correctamente\n");
        return i;
    }
    public boolean NT_MORE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
            i++;
            if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                i++;
                if(Object.getElementAt(i).equals("=")){
                    i++;
                    if(NT_VALUE(Object, Line, TokenType, i)){
                        i++;
                        if(NT_MORE(Object, Line, TokenType, i)){
                            return true;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'='\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'='\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                return false;
            }
        } else if(NT_SUMMAND(Object, Line, TokenType, i)){
            return true;
        } else {return true;}
        return false;
    }
    public boolean NT_SUMMAND(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_FACTOR(Object, Line, TokenType, i)){
            i++;
            if(NT_SUMMAND2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }
    public boolean NT_SUMMAND2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("+")){
            i++;
            if(NT_FACTOR(Object, Line, TokenType, i)){
                i++;
                if(NT_SUMMAND2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else if(Object.getElementAt(i).equals("-")){
            i++;
            if(NT_FACTOR(Object, Line, TokenType, i)){
                i++;
                if(NT_SUMMAND2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }
    public boolean NT_FACTOR(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_VALUE(Object, Line, TokenType, i)){
            i++;
            if(NT_FACTOR2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }
    public boolean NT_FACTOR2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("*")){
            i++;
            if(NT_VALUE(Object, Line, TokenType, i)){
                i++;
                if(NT_FACTOR2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else if(Object.getElementAt(i).equals("/")){
            i++;
            if(NT_VALUE(Object, Line, TokenType, i)){
                i++;
                if(NT_FACTOR2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }
    public boolean NT_TOP(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("TOP")){
            i++;
            if(NT_EXPRESSION(Object, Line, TokenType, i)){
                i++;
                if(NT_PERCENT(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }
    public boolean NT_EXPRESSION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("(")){
            i++;
            if(NT_NUMBER(Object, Line, TokenType, i)){
                i++;
                if(Object.getElementAt(i).equals(")")){
                    return true;
                }
            }
        } else if(NT_NUMBER(Object, Line, TokenType, i)){
            return true;
        }
        return false;
    }
    public boolean NT_PERCENT(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("PERCENT")){
            return true;
        } else {return true;}
    }
    public boolean NT_WHERE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("WHERE")){
            i++;
            if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                i++;
                if(NT_OP(Object, Line, TokenType, i)){
                    i++;
                    if(NT_VALUE(Object, Line, TokenType, i)){
                        i++;
                        if(NT_WHERE1(Object, Line, TokenType, i)){
                            return true;
                        }
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                return false;
            }
        } else {return true;}
        return false;
    }
    public boolean NT_WHERE1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_LOGIC_OP(Object, Line, TokenType, i)){
            i++;
            if(NT_OTHER(Object, Line, TokenType, i)){
                return true;
            }
        } else {return true;}
        return false;
    }
    public boolean NT_OTHER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            i++;
            if(NT_OP(Object, Line, TokenType, i)){
                i++;
                if(NT_VALUE(Object, Line, TokenType, i)){
                    i++;
                    if(NT_WHERE1(Object, Line, TokenType, i)){
                        return true;
                    }
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            return false;
        }
        return false;
    }
    public boolean NT_LOGIC_OP(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("AND")){
            return true;
        } else if(Object.getElementAt(i).equals("OR")){
            return true;
        } else if(Object.getElementAt(i).equals("&&")){
            return true;
        } else if(Object.getElementAt(i).equals("||")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'AND, OR, && OR ||'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'AND, OR, && OR ||'\n");
            return false;
        }
    }
    public boolean NT_VALUE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("CADENA")){
            return true;
        } else if(TokenType.getElementAt(i).equals("BOOLEANO")){
            return true;
        } else if(TokenType.getElementAt(i).equals("ENTERO")){
            return true;
        } else if(TokenType.getElementAt(i).equals("DECIMAL")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING, BOOLEAN OR NUMBER'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING, BOOLEAN OR NUMBER'\n");
            return false;
        }
    }
    public boolean NT_OP(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("<")){
            return true;
        } else if(Object.getElementAt(i).equals(">")){
            return true;
        } else if(Object.getElementAt(i).equals("<=")){
            return true;
        } else if(Object.getElementAt(i).equals(">=")){
            return true;
        } else if(Object.getElementAt(i).equals("!=")){
            return true;
        } else if(Object.getElementAt(i).equals("==")){
            return true;
        } else if(Object.getElementAt(i).equals("LIKE")){
            i++;
            if(TokenType.getElementAt(i).equals("CADENA")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("BETWEEN")){
            i++;
            if(NT_NUMBER(Object, Line, TokenType, i)){
                i++;
                if(Object.getElementAt(i).equals("AND")){
                    i++;
                    if(NT_NUMBER(Object, Line, TokenType, i)){
                        return true;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'AND'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'AND'\n");
                    return false;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'OPERATOR'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'OPERATOR'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_NUMBER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("CADENA")){
            return true;
        } else if(TokenType.getElementAt(i).equals("DECIMAL")){
            return true;
        } else if(TokenType.getElementAt(i).equals("ENTERO")){
            return true;
        } else if(TokenType.getElementAt(i).equals("BOOLEANO")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING OR NUMBER'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING OR NUMBER'\n");
            return false;
        }
    }
}
