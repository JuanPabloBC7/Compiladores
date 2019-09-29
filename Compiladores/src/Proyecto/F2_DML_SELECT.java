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
public class F2_DML_SELECT {
    DefaultListModel ll_Sentencia_Hija_Archivo = new DefaultListModel();
    String ls_Sentencia_Hija_Errores = "";

	public int S1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("SELECT")){
            i++;
            if(S2(Object, Line, TokenType, i)){
                ll_Sentencia_Hija_Archivo.addElement("SELECT Leido correctamente.");
                return i;
            }
        }
        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** SELECT no leido correctamente\n");
        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** SELECT no leido correctamente\n");
        return i;
    }
    public boolean S2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("TOP")){
            i++;
            if(NUMBER(Object, Line, TokenType, i)){
                i++;
                if(A1(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else if(Object.getElementAt(i).equals("1")){
            i++;
            if(A1(Object, Line, TokenType, i)){
                return true;
            }
        } else if(S6(Object, Line, TokenType, i)){
            return true;
        } 
        return false;
    }
    public boolean S4(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
            i++;
            if(SELECTEXPRESSION(Object, Line, TokenType, i)){
                i++;
                if(S4(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }
    public boolean S5(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("DISTINCT")){
            return true;
        } else if(Object.getElementAt(i).equals("ALL")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DISTINCT OR ALL'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DISTINCT OR ALL'\n");
            return false;
        }
    }
    public boolean S6(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(SELECTEXPRESSION(Object, Line, TokenType, i)){
            i++;
            if(S4(Object, Line, TokenType, i)){
                i++;
                if(A1(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else if(S5(Object, Line, TokenType, i)){
            i++;
            if(SELECTEXPRESSION(Object, Line, TokenType, i)){
                i++;
                if(S4(Object, Line, TokenType, i)){
                    i++;
                    if(A1(Object, Line, TokenType, i)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean A1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("FROM")){
            i++;
            if(TABLEEXPRESSION(Object, Line, TokenType, i)){
                i++;
                if(A2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'FROM'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'FROM'\n");
            return false;
        }
        return false;
    }
    public boolean A2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(D1(Object, Line, TokenType, i)){
            i++;
            if(B1(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals("(")){
            i++;
            if(COLUMNDEF(Object, Line, TokenType, i)){
                i++;
                if(A3(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(")")){
                        i++;
                        if(D1(Object, Line, TokenType, i)){
                            i++;
                            if(B1(Object, Line, TokenType, i)){
                                return true;
                            }
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        return false;
                    }
                }
            }
        } else if(A4(Object, Line, TokenType, i)){
            i++;
            if(D1(Object, Line, TokenType, i)){
                i++;
                if(B1(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else if(Object.getElementAt(i).equals("(")){
            i++;
            if(COLUMNDEF(Object, Line, TokenType, i)){
                i++;
                if(A3(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(")")){
                        i++;
                        if(A4(Object, Line, TokenType, i)){
                            i++;
                            if(D1(Object, Line, TokenType, i)){
                                i++;
                                if(B1(Object, Line, TokenType, i)){
                                    return true;
                                }
                            }
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        return false;
                    }
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
            return false;
        }
        return false;
    }
    public boolean A3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
            i++;
            if(COLUMNDEF(Object, Line, TokenType, i)){
                i++;
                if(A3(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }
    public boolean A4(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("WHERE")){
            i++;
            if(EXPRESSION(Object, Line, TokenType, i)){
                return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'WHERE'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'WHERE'\n");
            return false;
        }
        return false;
    }
    public boolean B1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("GROUP")){
            i++;
            if(Object.getElementAt(i).equals("BY")){
                i++;
                if(EXPRESSION(Object, Line, TokenType, i)){
                    i++;
                    if(B2(Object, Line, TokenType, i)){
                        i++;
                        if(B3(Object, Line, TokenType, i)){
                            i++;
                            if(C1(Object, Line, TokenType, i)){
                                return true;
                            }
                        }
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'BY'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'BY'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("HAVING")){
            i++;
            if(EXPRESSION(Object, Line, TokenType, i)){
                i++;
                if(C1(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'GROUP OR HAVING'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'GROUP OR HAVING'\n");
            return false;
        }
        return false;
    }
    public boolean B2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
            i++;
            if(EXPRESSION(Object, Line, TokenType, i)){
                i++;
                if(B2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }
    public boolean B3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("HAVING")){
            i++;
            if(EXPRESSION(Object, Line, TokenType, i)){
                return true;
            }
        } else {return true;}
        return false;
    }
    public boolean C1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("ORDER")){
            i++;
            if(Object.getElementAt(i).equals("BY")){
                i++;
                if(ORDER(Object, Line, TokenType, i)){
                    i++;
                    if(C2(Object, Line, TokenType, i)){
                        i++;
                        if(C3(Object, Line, TokenType, i)){
                            return true;
                        }
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'BY'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'BY'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("LIMIT")){
            i++;
            if(BINDPARAMETER(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals("LIMIT")){
            i++;
            if(NUMBER(Object, Line, TokenType, i)){
                return true;
            }
        } else {return true;}
        return false;
    }
    public boolean C2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
            i++;
            if(ORDER(Object, Line, TokenType, i)){
                i++;
                if(C2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }
    public boolean C3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("LIMIT")){
            i++;
            if(BINDPARAMETER(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals("LIMIT")){
            i++;
            if(NUMBER(Object, Line, TokenType, i)){
                return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'LIMIT'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'LIMIT'\n");
            return false;
        }
        return false;
    }
    public boolean D1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("INNER")){
            i++;
            if(Object.getElementAt(i).equals("JOIN")){
                i++;
                if(D2(Object, Line, TokenType, i)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'JOIN'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'JOIN'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("LEFT")){
            i++;
            if(Object.getElementAt(i).equals("JOIN")){
                i++;
                if(D2(Object, Line, TokenType, i)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'JOIN'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'JOIN'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("RIGTH")){
            i++;
            if(Object.getElementAt(i).equals("JOIN")){
                i++;
                if(D2(Object, Line, TokenType, i)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'JOIN'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'JOIN'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("JOIN")){
            i++;
            if(D2(Object, Line, TokenType, i)){
                return true;
            }
        }else {return true;}
        return false;
    }
    public boolean D2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TABLEEXPRESSION(Object, Line, TokenType, i)){
            i++;
            if(Object.getElementAt(i).equals("ON")){
                i++;
                if(Object.getElementAt(i).equals("(")){
                    i++;
                    if(E1(Object, Line, TokenType, i)){
                        i++;
                        if(Object.getElementAt(i).equals(")")){
                            return true;
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                            return false;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ON'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ON'\n");
                return false;
            }
        }
        return false;
    }
    public boolean E1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            i++;
            if(Object.getElementAt(i).equals("=")){
                i++;
                if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                    i++;
                    if(E2(Object, Line, TokenType, i)){
                        return true;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                    return false;
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
        return false;
    }
    public boolean E2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("AND")){
            i++;
            if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                i++;
                if(Object.getElementAt(i).equals("=")){
                    i++;
                    if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                        i++;
                        if(E2(Object, Line, TokenType, i)){
                            return true;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                        return false;
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
        } else {return true;}
        return false;
    }
    public boolean SELECTEXPRESSION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("*")){
            return true;
        } else if(Object.getElementAt(i).equals("(")){
            i++;
            if(SELECTEXPRESSION2(Object, Line, TokenType, i)){
                return true;
            }
        } else if(TERM(Object, Line, TokenType, i)){
            i++;
            if(SELECTEXPRESSION3(Object, Line, TokenType, i)){
                return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'* OR ('\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'* OR ('\n");
            return false;
        }
        return false;
    }
    public boolean SELECTEXPRESSION2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            return true;
        } else if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            i++;
            if(Object.getElementAt(i).equals(",")){
                i++;
                if(SELECTEXPRESSION2(Object, Line, TokenType, i)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t','\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t','\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("*")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID OR *'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID OR *'\n");
            return false;
        }
        return false;
    }
    public boolean SELECTEXPRESSION3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("AS")){
            i++;
            if(COLUMNALIAS(Object, Line, TokenType, i)){
                return true;
            }
        } else if(COLUMNALIAS(Object, Line, TokenType, i)){
            return true;
        } else {return true;}
        return false;
    }
    public boolean TERM(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(VALUE(Object, Line, TokenType, i)){
            return true;
        } else if(BINDPARAMETER(Object, Line, TokenType, i)){
            return true;
        } else if(FUNCTION(Object, Line, TokenType, i)){
            return true;
        } else if(CASE(Object, Line, TokenType, i)){
            return true;
        } else if(CASEWHEN(Object, Line, TokenType, i)){
            return true;
        } else if(Object.getElementAt(i).equals("(")){
            i++;
            if(OPERAND(Object, Line, TokenType, i)){
                i++;
                if(Object.getElementAt(i).equals(")")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                    return false;
                }
            }
        } else if(COLUMNREF(Object, Line, TokenType, i)){
            return true;
        } else if(TABLEALIAS(Object, Line, TokenType, i)){
            i++;
            if(COLUMNREF(Object, Line, TokenType, i)){
                return true;
            }
        } else if(ROWVALUECONSTRUCTOR(Object, Line, TokenType, i)){
            return true;
        } 
        return false;
    }
    public boolean VALUE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NUMBER(Object, Line, TokenType, i)){
            return true;
        } else if(Object.getElementAt(i).equals("NULL")){
            return true;
        } else {return true;}
    }
    
    public boolean NUMBER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
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
    
    public boolean BINDPARAMETER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(":")){
            i++;
            if(NUMBER(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals("?")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t': OR ?'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t': OR ?'\n");
            return false;
        }
        return false;
    }
    public boolean FUNCTION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        
        return false;
    }
    public boolean CASE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("CASE")){
            i++;
            if(TERM(Object, Line, TokenType, i)){
                i++;
                if(Object.getElementAt(i).equals("WHEN")){
                    i++;
                    if(EXPRESSION(Object, Line, TokenType, i)){
                        i++;
                        if(Object.getElementAt(i).equals("THEN")){
                            i++;
                            if(TERM(Object, Line, TokenType, i)){
                                i++;
                                if(CASE2(Object, Line, TokenType, i)){
                                    i++;
                                    if(CASE3(Object, Line, TokenType, i)){
                                        return true;
                                    }
                                }
                            }
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'THEN'\n");
                            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'THEN'\n");
                            return false;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'WHEN'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'WHEN'\n");
                    return false;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'CASE'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'CASE'\n");
            return false;
        }
        return false;
    }
    public boolean CASE2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
            i++;
            if(TERM(Object, Line, TokenType, i)){
                i++;
                if(CASE2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }
    public boolean CASE3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("ELSE")){
            i++;
            if(EXPRESSION(Object, Line, TokenType, i)){
                i++;
                if(Object.getElementAt(i).equals("END")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'END'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'END'\n");
                    return false;
                }
            }
        } else if(Object.getElementAt(i).equals("END")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ELSE OR END'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ELSE OR END'\n");
            return false;
        }
        return false;
    }
    public boolean CASEWHEN(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("CASE")){
            i++;
            if(Object.getElementAt(i).equals("WHEN")){
                i++;
                if(EXPRESSION(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals("THEN")){
                        i++;
                        if(TERM(Object, Line, TokenType, i)){
                            i++;
                            if(CASEWHEN2(Object, Line, TokenType, i)){
                                i++;
                                if(CASEWHEN3(Object, Line, TokenType, i)){
                                    return true;
                                }
                            }
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'THEN'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'THEN'\n");
                        return false;
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'WHEN'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'WHEN'\n");
                return false;
            }
        } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'CASE'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'CASE'\n");
                return false;
            }
        return false;
    }
    public boolean CASEWHEN2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
            i++;
            if(TERM(Object, Line, TokenType, i)){
                i++;
                if(CASEWHEN2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }
    public boolean CASEWHEN3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("ELSE")){
            i++;
            if(TERM(Object, Line, TokenType, i)){
                i++;
                if(Object.getElementAt(i).equals("END")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'END'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'END'\n");
                    return false;
                }
            }
        } else if(Object.getElementAt(i).equals("END")){
            return true;
        } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ELSE OR END'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ELSE OR END'\n");
                return false;
            }
        return false;
    }
    public boolean EXPRESSION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(ANDCONDITION(Object, Line, TokenType, i)){
            i++;
            if(EXPRESSION2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }
    public boolean EXPRESSION2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("OR")){
            i++;
            if(ANDCONDITION(Object, Line, TokenType, i)){
                i++;
                if(ANDCONDITION2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }
    public boolean ANDCONDITION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(CONDITION(Object, Line, TokenType, i)){
            i++;
            if(ANDCONDITION2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }
    public boolean ANDCONDITION2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("AND")){
           i++;
           if(CONDITION(Object, Line, TokenType, i)){
               i++;
               if(ANDCONDITION2(Object, Line, TokenType, i)){
                   return true;
               }
           }
       } else {return true;}
        return false;
    }
    public boolean CONDITION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(OPERAND(Object, Line, TokenType, i)){
           i++;
           if(CONDITION2(Object, Line, TokenType, i)){
               return true;
           }
       } else if(Object.getElementAt(i).equals("NOT")){
           i++;
           if(EXPRESSION(Object, Line, TokenType, i)){
               return true;
           }
       } else if(Object.getElementAt(i).equals("(")){
           i++;
           if(CONDITION2(Object, Line, TokenType, i)){
               i++;
               if(Object.getElementAt(i).equals(")")){
                   return true;
               } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                    return false;
                }
           }
       } else {return false;}
        return false;
    }
    public boolean CONDITION2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(COMPARE(Object, Line, TokenType, i)){
            i++;
            if(OPERAND(Object, Line, TokenType, i)){
                return true;
            }
        } else if(CONDITION3(Object, Line, TokenType, i)){
            i++;
            if(Object.getElementAt(i).equals("IN")){
                i++;
                if(Object.getElementAt(i).equals("(")){
                    i++;
                    if(OPERAND(Object, Line, TokenType, i)){
                        i++;
                        if(CONDITION4(Object, Line, TokenType, i)){
                            i++;
                            if(Object.getElementAt(i).equals(")")){
                                return true;
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                                return false;
                            }
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IN'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IN'\n");
                return false;
            }
        } else if(CONDITION3(Object, Line, TokenType, i)){
            i++;
            if(Object.getElementAt(i).equals("LIKE")){
                i++;
                if(OPERAND(Object, Line, TokenType, i)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'LIKE'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'LIKE'\n");
                return false;
            }
        } else if(CONDITION3(Object, Line, TokenType, i)){
            i++;
            if(Object.getElementAt(i).equals("BETWEEN")){
                i++;
                if(OPERAND(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals("AND")){
                        i++;
                        if(OPERAND(Object, Line, TokenType, i)){
                            return true;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'AND'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'AND'\n");
                        return false;
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'BETWEEN'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'BETWEEN'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("IS")){
            i++;
            if(CONDITION3(Object, Line, TokenType, i)){
                i++;
                if(Object.getElementAt(i).equals("NULL")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NULL'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NULL'\n");
                    return false;
                }
            }
        } else {return true;}
        return false;
    }
    public boolean CONDITION3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("NOT")){
            return true;
        } else {return true;}
    }
    public boolean CONDITION4(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
           i++;
           if(OPERAND(Object, Line, TokenType, i)){
               i++;
               if(CONDITION4(Object, Line, TokenType, i)){
                   return true;
               }
           }
       } else {return true;}
        return false;
    }
    public boolean COMPARE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("<>")){
            return true;
        } else if(Object.getElementAt(i).equals("<=")){
            return true;
        } else if(Object.getElementAt(i).equals(">=")){
            return true;
        } else if(Object.getElementAt(i).equals("=")){
            return true;
        } else if(Object.getElementAt(i).equals("<")){
            return true;
        } else if(Object.getElementAt(i).equals(">")){
            return true;
        } else if(Object.getElementAt(i).equals("!=")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'COMPARE'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'COMPARE'\n");
            return false;
        }
    }
    public boolean OPERAND(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(SUMMAND(Object, Line, TokenType, i)){
            i++;
            if(OPERAND3(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }
    public boolean OPERAND3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("||")){
            i++;
            if(SUMMAND(Object, Line, TokenType, i)){
                i++;
                if(OPERAND3(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }
    public boolean SUMMAND(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(FACTOR(Object, Line, TokenType, i)){
            i++;
            if(SUMMAND2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }
    public boolean SUMMAND2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("+")){
            i++;
            if(FACTOR(Object, Line, TokenType, i)){
                i++;
                if(SUMMAND2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else if(Object.getElementAt(i).equals("-")){
            i++;
            if(FACTOR(Object, Line, TokenType, i)){
                i++;
                if(SUMMAND2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }
    public boolean FACTOR(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TERM(Object, Line, TokenType, i)){
            i++;
            if(FACTOR2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }
    public boolean FACTOR2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("*")){
            i++;
            if(TERM(Object, Line, TokenType, i)){
                i++;
                if(FACTOR2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else if(Object.getElementAt(i).equals("/")){
            i++;
            if(TERM(Object, Line, TokenType, i)){
                i++;
                if(FACTOR2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }
    public boolean COLUMNREF(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
           i++;
            if(Object.getElementAt(i).equals(".")){
               i++;
               if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                   return true;
               } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                    return false;
                }
           }
       } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            return false;
        }
        return false;
    }
    public boolean TABLEALIAS(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            return false;
        }
    }
    public boolean ROWVALUECONSTRUCTOR(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("(")){
            i++;
            if(TERM(Object, Line, TokenType, i)){
                i++;
                if(Object.getElementAt(i).equals(",")){
                    i++;
                    if(TERM(Object, Line, TokenType, i)){
                        i++;
                        if(ROWVALUECONSTRUCTOR2(Object, Line, TokenType, i)){
                            return true;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t','\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t','\n");
                    return false;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
            return false;
        }
        return false;
    }
    public boolean ROWVALUECONSTRUCTOR2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
            i++;
            if(TERM(Object, Line, TokenType, i)){
                i++;
                if(ROWVALUECONSTRUCTOR2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else if(Object.getElementAt(i).equals(")")){ 
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
            return false;
        }
        return false;
    }
    public boolean COLUMNALIAS(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            return false;
        }
    }
    public boolean TABLEEXPRESSION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TABLENAME(Object, Line, TokenType, i)){
            i++;
            if(TABLEEXPRESSION2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }
    public boolean TABLEEXPRESSION2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TABLEALIAS(Object, Line, TokenType, i)){
            return true;
        } else if(Object.getElementAt(i).equals("AS")){
            i++;
            if(TABLEALIAS(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals(".")){
            i++;
            if(TABLEALIAS(Object, Line, TokenType, i)){
                return true;
            }
        }
        else {return true;}
        return false;
    }
    public boolean TABLENAME(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            return false;
        }
    }
    
    public boolean COLUMNDEF(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(COLUMNREF(Object, Line, TokenType, i)){
            i++;
            if(DATATYPE(Object, Line, TokenType, i)){
                i++;
                if(COLUMNDEF2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean COLUMNDEF2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(COLUMNDEF4(Object, Line, TokenType, i)){
            i++;
            if(Object.getElementAt(i).equals("NULL")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NULL'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NULL'\n");
                return false;
            }
        } else if(COLUMNDEF5(Object, Line, TokenType, i)){
            i++;
            if(Object.getElementAt(i).equals("PRIMARY")){
                i++;
                if(Object.getElementAt(i).equals("KEY")){
                    i++;
                    if(COLUMNDEF3(Object, Line, TokenType, i)){
                        return true;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'PRIMARY'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'PRIMARY'\n");
                    return false;
                }
            }
        }
        return false;
    }
    public boolean COLUMNDEF3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("ASC")){
            return true;
        } else if(Object.getElementAt(i).equals("DESC")){
            return true;
        } else {return true;}
    }
    public boolean COLUMNDEF4(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("NOT")){
            return true;
        } else {return true;}
    }
    public boolean COLUMNDEF5(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("NULL")){
            return true;
        } else if(Object.getElementAt(i).equals("NOT")){
            i++;
            if(Object.getElementAt(i).equals("NULL")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NULL'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NULL'\n");
                return false;
            }
        } else {return true;}
    }
    public boolean DATATYPE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("CHAR")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NUMBER(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else if(Object.getElementAt(i).equals("-")){
                    i++;
                    if(NUMBER(Object, Line, TokenType, i)){
                        i++;
                        if(Object.getElementAt(i).equals(")")){
                            return true;
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                            return false;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'- OR NUMBER'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'- OR NUMBER'\n");
                    return false;
                }
            }
        } else if(Object.getElementAt(i).equals("VARCHAR")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NUMBER(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else if(Object.getElementAt(i).equals("-")){
                    i++;
                    if(NUMBER(Object, Line, TokenType, i)){
                        i++;
                        if(Object.getElementAt(i).equals(")")){
                            return true;
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                            return false;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'- OR NUMBER'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'- OR NUMBER'\n");
                    return false;
                }
            }
        } else if(Object.getElementAt(i).equals("BINARY")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NUMBER(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else if(Object.getElementAt(i).equals("-")){
                    i++;
                    if(NUMBER(Object, Line, TokenType, i)){
                        i++;
                        if(Object.getElementAt(i).equals(")")){
                            return true;
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                            return false;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'- OR NUMBER'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'- OR NUMBER'\n");
                    return false;
                }
            }
        } else if(Object.getElementAt(i).equals("DECIMAL")){
            return true;
        } else if(Object.getElementAt(i).equals("TINYINT")){
            return true;
        } else if(Object.getElementAt(i).equals("SMALLINT")){
            return true;
        } else if(Object.getElementAt(i).equals("INTEGER")){
            return true;
        } else if(Object.getElementAt(i).equals("BIGINT")){
            return true;
        } else if(Object.getElementAt(i).equals("FLOAT")){
            return true;
        } else if(Object.getElementAt(i).equals("DOUBLE")){
            return true;
        } else if(Object.getElementAt(i).equals("TIMESTAMP")){
            return true;
        } else if(Object.getElementAt(i).equals("DATE")){
            return true;
        } else if(Object.getElementAt(i).equals("TIME")){
            return true;
        } else if(Object.getElementAt(i).equals("VARBINARY")){
            return true;
        } else if(Object.getElementAt(i).equals("UNISIGNED_TINYINT")){
            return true;
        } else if(Object.getElementAt(i).equals("UNISIGNED_SMALLINT")){
            return true;
        } else if(Object.getElementAt(i).equals("UNISIGNED_INT")){
            return true;
        } else if(Object.getElementAt(i).equals("UNISIGNED_LONG")){
            return true;
        } else if(Object.getElementAt(i).equals("UNISIGNED_FLOAT")){
            return true;
        } else if(Object.getElementAt(i).equals("UNISIGNED_DOUBLE")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DATATYPE'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DATATYPE'\n");
            return false;
        }
        return false;
    }
    public boolean ORDER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(EXPRESSION(Object, Line, TokenType, i)){
            i++;
            if(ORDER2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }
    public boolean ORDER2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("ASC")){
            i++;
            if(ORDER3(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals("DESC")){
            i++;
            if(ORDER3(Object, Line, TokenType, i)){
                return true;
            }
        } else {return true;}
        return false;
    }
    public boolean ORDER3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("NULLS")){
            i++;
            if(ORDER4(Object, Line, TokenType, i)){
                return true;
            }
        } else {return true;}
        return false;
    }
    public boolean ORDER4(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("FIRST")){
            return true;
        } else if(Object.getElementAt(i).equals("LAST")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'FIRST OR LAST'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'FIRST OR LAST'\n");
            return false;
        }
    }
}
