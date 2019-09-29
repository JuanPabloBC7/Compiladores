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
    int h;
    
    public int S1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        h = i;
        if(Object.getElementAt(h).equals("SELECT")){
            h++;
            if(S2(Object, Line, TokenType)){
                if(NT_FIN(Object, Line, TokenType)){     
                    h++;
                    ls_Sentencia_Hija_Errores += ("SELECT Leido correctamente.\n");
                    return h;
                }
            }
        }
        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** SELECT no leido correctamente\n");
        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** SELECT no leido correctamente\n");
        return h;
    }
    public boolean S2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("TOP")){
            h++;
            if(NUMBER(Object, Line, TokenType)){
                h++;
                if(A1(Object, Line, TokenType)){
                    return true;
                }
            }
        } else if(Object.getElementAt(h).equals("1")){
            h++;
            if(A1(Object, Line, TokenType)){
                return true;
            }
        } else if(S6(Object, Line, TokenType)){
            return true;
        } 
        return false;
    }
    public boolean S4(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(SELECTEXPRESSION(Object, Line, TokenType)){
                h++;
                if(S4(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean S5(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("DISTINCT")){
            return true;
        } else if(Object.getElementAt(h).equals("ALL")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DISTINCT OR ALL'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DISTINCT OR ALL'\n");
            return false;
        }
    }
    public boolean S6(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(SELECTEXPRESSION(Object, Line, TokenType)){
            h++;
            if(S4(Object, Line, TokenType)){
                h++;
                if(A1(Object, Line, TokenType)){
                    return true;
                }
            }
        } else if(S5(Object, Line, TokenType)){
            h++;
            if(SELECTEXPRESSION(Object, Line, TokenType)){
                h++;
                if(S4(Object, Line, TokenType)){
                    h++;
                    if(A1(Object, Line, TokenType)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean A1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("FROM")){
            h++;
            if(TABLEEXPRESSION(Object, Line, TokenType)){
                h++;
                if(A2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'FROM'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'FROM'\n");
            return false;
        }
        return false;
    }
    public boolean A2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(D1(Object, Line, TokenType)){
            h++;
            if(B1(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("(")){
            h++;
            if(COLUMNDEF(Object, Line, TokenType)){
                h++;
                if(A3(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        h++;
                        if(D1(Object, Line, TokenType)){
                            h++;
                            if(B1(Object, Line, TokenType)){
                                return true;
                            }
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                }
            }
        } else if(A4(Object, Line, TokenType)){
            h++;
            if(D1(Object, Line, TokenType)){
                h++;
                if(B1(Object, Line, TokenType)){
                    return true;
                }
            }
        } else if(Object.getElementAt(h).equals("(")){
            h++;
            if(COLUMNDEF(Object, Line, TokenType)){
                h++;
                if(A3(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        h++;
                        if(A4(Object, Line, TokenType)){
                            h++;
                            if(D1(Object, Line, TokenType)){
                                h++;
                                if(B1(Object, Line, TokenType)){
                                    return true;
                                }
                            }
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
            return false;
        }
        return false;
    }
    public boolean A3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(COLUMNDEF(Object, Line, TokenType)){
                h++;
                if(A3(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean A4(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("WHERE")){
            h++;
            if(EXPRESSION(Object, Line, TokenType)){
                return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'WHERE'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'WHERE'\n");
            return false;
        }
        return false;
    }
    public boolean B1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("GROUP")){
            h++;
            if(Object.getElementAt(h).equals("BY")){
                h++;
                if(EXPRESSION(Object, Line, TokenType)){
                    h++;
                    if(B2(Object, Line, TokenType)){
                        h++;
                        if(B3(Object, Line, TokenType)){
                            h++;
                            if(C1(Object, Line, TokenType)){
                                return true;
                            }
                        }
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'BY'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'BY'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("HAVING")){
            h++;
            if(EXPRESSION(Object, Line, TokenType)){
                h++;
                if(C1(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'GROUP OR HAVING'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'GROUP OR HAVING'\n");
            return false;
        }
        return false;
    }
    public boolean B2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(EXPRESSION(Object, Line, TokenType)){
                h++;
                if(B2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean B3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("HAVING")){
            h++;
            if(EXPRESSION(Object, Line, TokenType)){
                return true;
            }
        } else {h--; return true;}
        return false;
    }
    public boolean C1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("ORDER")){
            h++;
            if(Object.getElementAt(h).equals("BY")){
                h++;
                if(ORDER(Object, Line, TokenType)){
                    h++;
                    if(C2(Object, Line, TokenType)){
                        h++;
                        if(C3(Object, Line, TokenType)){
                            return true;
                        }
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'BY'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'BY'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("LIMIT")){
            h++;
            if(BINDPARAMETER(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("LIMIT")){
            h++;
            if(NUMBER(Object, Line, TokenType)){
                return true;
            }
        } else {h--; return true;}
        return false;
    }
    public boolean C2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(ORDER(Object, Line, TokenType)){
                h++;
                if(C2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean C3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("LIMIT")){
            h++;
            if(BINDPARAMETER(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("LIMIT")){
            h++;
            if(NUMBER(Object, Line, TokenType)){
                return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'LIMIT'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'LIMIT'\n");
            return false;
        }
        return false;
    }
    public boolean D1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("INNER")){
            h++;
            if(Object.getElementAt(h).equals("JOIN")){
                h++;
                if(D2(Object, Line, TokenType)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'JOIN'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'JOIN'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("LEFT")){
            h++;
            if(Object.getElementAt(h).equals("JOIN")){
                h++;
                if(D2(Object, Line, TokenType)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'JOIN'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'JOIN'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("RIGTH")){
            h++;
            if(Object.getElementAt(h).equals("JOIN")){
                h++;
                if(D2(Object, Line, TokenType)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'JOIN'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'JOIN'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("JOIN")){
            h++;
            if(D2(Object, Line, TokenType)){
                return true;
            }
        }else {h--; return true;}
        return false;
    }
    public boolean D2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TABLEEXPRESSION(Object, Line, TokenType)){
            h++;
            if(Object.getElementAt(h).equals("ON")){
                h++;
                if(Object.getElementAt(h).equals("(")){
                    h++;
                    if(E1(Object, Line, TokenType)){
                        h++;
                        if(Object.getElementAt(h).equals(")")){
                            return true;
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                            return false;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ON'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ON'\n");
                return false;
            }
        }
        return false;
    }
    public boolean E1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            h++;
            if(Object.getElementAt(h).equals("=")){
                h++;
                if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                    h++;
                    if(E2(Object, Line, TokenType)){
                        return true;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    return false;
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
        return false;
    }
    public boolean E2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("AND")){
            h++;
            if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                h++;
                if(Object.getElementAt(h).equals("=")){
                    h++;
                    if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                        h++;
                        if(E2(Object, Line, TokenType)){
                            return true;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                        return false;
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
        } else {h--; return true;}
        return false;
    }
    public boolean SELECTEXPRESSION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("*")){
            return true;
        } else if(Object.getElementAt(h).equals("(")){
            h++;
            if(SELECTEXPRESSION2(Object, Line, TokenType)){
                return true;
            }
        } else if(TERM(Object, Line, TokenType)){
            h++;
            if(SELECTEXPRESSION3(Object, Line, TokenType)){
                return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'* OR ('\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'* OR ('\n");
            return false;
        }
        return false;
    }
    public boolean SELECTEXPRESSION2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            return true;
        } else if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            h++;
            if(Object.getElementAt(h).equals(",")){
                h++;
                if(SELECTEXPRESSION2(Object, Line, TokenType)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t','\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t','\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("*")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID OR *'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID OR *'\n");
            return false;
        }
        return false;
    }
    public boolean SELECTEXPRESSION3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("AS")){
            h++;
            if(COLUMNALIAS(Object, Line, TokenType)){
                return true;
            }
        } else if(COLUMNALIAS(Object, Line, TokenType)){
            return true;
        } else {h--; return true;}
        return false;
    }
    public boolean TERM(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(VALUE(Object, Line, TokenType)){
            return true;
        } else if(BINDPARAMETER(Object, Line, TokenType)){
            return true;
        } else if(FUNCTION(Object, Line, TokenType)){
            return true;
        } else if(CASE(Object, Line, TokenType)){
            return true;
        } else if(CASEWHEN(Object, Line, TokenType)){
            return true;
        } else if(Object.getElementAt(h).equals("(")){
            h++;
            if(OPERAND(Object, Line, TokenType)){
                h++;
                if(Object.getElementAt(h).equals(")")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                    return false;
                }
            }
        } else if(COLUMNREF(Object, Line, TokenType)){
            return true;
        } else if(TABLEALIAS(Object, Line, TokenType)){
            h++;
            if(COLUMNREF(Object, Line, TokenType)){
                return true;
            }
        } else if(ROWVALUECONSTRUCTOR(Object, Line, TokenType)){
            return true;
        } 
        return false;
    }
    public boolean VALUE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NUMBER(Object, Line, TokenType)){
            return true;
        } else if(Object.getElementAt(h).equals("NULL")){
            return true;
        } else {h--; return true;}
    }
    
    public boolean NUMBER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
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
    
    public boolean BINDPARAMETER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(":")){
            h++;
            if(NUMBER(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("?")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t': OR ?'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t': OR ?'\n");
            return false;
        }
        return false;
    }
    public boolean FUNCTION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("AVG")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(TokenType.getElementAt(h).equals("ENTERO")){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NUMBER'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NUMBER'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("MAX")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(TokenType.getElementAt(h).equals("ENTERO")){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NUMBER'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NUMBER'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("MIN")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(TokenType.getElementAt(h).equals("ENTERO")){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NUMBER'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NUMBER'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("SUM")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(TokenType.getElementAt(h).equals("ENTERO")){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NUMBER'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NUMBER'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("TRIM")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(TokenType.getElementAt(h).equals("CADENA")){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("UPPER")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(TokenType.getElementAt(h).equals("CADENA")){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("LOWER")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(TokenType.getElementAt(h).equals("CADENA")){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                return false;
            }
        }
        return false;
    }
    
    public boolean CASE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("CASE")){
            h++;
            if(TERM(Object, Line, TokenType)){
                h++;
                if(Object.getElementAt(h).equals("WHEN")){
                    h++;
                    if(EXPRESSION(Object, Line, TokenType)){
                        h++;
                        if(Object.getElementAt(h).equals("THEN")){
                            h++;
                            if(TERM(Object, Line, TokenType)){
                                h++;
                                if(CASE2(Object, Line, TokenType)){
                                    h++;
                                    if(CASE3(Object, Line, TokenType)){
                                        return true;
                                    }
                                }
                            }
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'THEN'\n");
                            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'THEN'\n");
                            return false;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'WHEN'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'WHEN'\n");
                    return false;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'CASE'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'CASE'\n");
            return false;
        }
        return false;
    }
    public boolean CASE2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(TERM(Object, Line, TokenType)){
                h++;
                if(CASE2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean CASE3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("ELSE")){
            h++;
            if(EXPRESSION(Object, Line, TokenType)){
                h++;
                if(Object.getElementAt(h).equals("END")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'END'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'END'\n");
                    return false;
                }
            }
        } else if(Object.getElementAt(h).equals("END")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ELSE OR END'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ELSE OR END'\n");
            return false;
        }
        return false;
    }
    public boolean CASEWHEN(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("CASE")){
            h++;
            if(Object.getElementAt(h).equals("WHEN")){
                h++;
                if(EXPRESSION(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals("THEN")){
                        h++;
                        if(TERM(Object, Line, TokenType)){
                            h++;
                            if(CASEWHEN2(Object, Line, TokenType)){
                                h++;
                                if(CASEWHEN3(Object, Line, TokenType)){
                                    return true;
                                }
                            }
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'THEN'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'THEN'\n");
                        return false;
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'WHEN'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'WHEN'\n");
                return false;
            }
        } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'CASE'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'CASE'\n");
                return false;
            }
        return false;
    }
    public boolean CASEWHEN2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(TERM(Object, Line, TokenType)){
                h++;
                if(CASEWHEN2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean CASEWHEN3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("ELSE")){
            h++;
            if(TERM(Object, Line, TokenType)){
                h++;
                if(Object.getElementAt(h).equals("END")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'END'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'END'\n");
                    return false;
                }
            }
        } else if(Object.getElementAt(h).equals("END")){
            return true;
        } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ELSE OR END'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ELSE OR END'\n");
                return false;
            }
        return false;
    }
    public boolean EXPRESSION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(ANDCONDITION(Object, Line, TokenType)){
            h++;
            if(EXPRESSION2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }
    public boolean EXPRESSION2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("OR")){
            h++;
            if(ANDCONDITION(Object, Line, TokenType)){
                h++;
                if(ANDCONDITION2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean ANDCONDITION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(CONDITION(Object, Line, TokenType)){
            h++;
            if(ANDCONDITION2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }
    public boolean ANDCONDITION2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("AND")){
           h++;
           if(CONDITION(Object, Line, TokenType)){
               h++;
               if(ANDCONDITION2(Object, Line, TokenType)){
                   return true;
               }
           }
       } else {h--; return true;}
        return false;
    }
    public boolean CONDITION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(OPERAND(Object, Line, TokenType)){
           h++;
           if(CONDITION2(Object, Line, TokenType)){
               return true;
           }
       } else if(Object.getElementAt(h).equals("NOT")){
           h++;
           if(EXPRESSION(Object, Line, TokenType)){
               return true;
           }
       } else if(Object.getElementAt(h).equals("(")){
           h++;
           if(CONDITION2(Object, Line, TokenType)){
               h++;
               if(Object.getElementAt(h).equals(")")){
                   return true;
               } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                    return false;
                }
           }
       } else {return false;}
        return false;
    }
    public boolean CONDITION2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(COMPARE(Object, Line, TokenType)){
            h++;
            if(OPERAND(Object, Line, TokenType)){
                return true;
            }
        } else if(CONDITION3(Object, Line, TokenType)){
            h++;
            if(Object.getElementAt(h).equals("IN")){
                h++;
                if(Object.getElementAt(h).equals("(")){
                    h++;
                    if(OPERAND(Object, Line, TokenType)){
                        h++;
                        if(CONDITION4(Object, Line, TokenType)){
                            h++;
                            if(Object.getElementAt(h).equals(")")){
                                return true;
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                                return false;
                            }
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IN'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IN'\n");
                return false;
            }
        } else if(CONDITION3(Object, Line, TokenType)){
            h++;
            if(Object.getElementAt(h).equals("LIKE")){
                h++;
                if(OPERAND(Object, Line, TokenType)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'LIKE'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'LIKE'\n");
                return false;
            }
        } else if(CONDITION3(Object, Line, TokenType)){
            h++;
            if(Object.getElementAt(h).equals("BETWEEN")){
                h++;
                if(OPERAND(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals("AND")){
                        h++;
                        if(OPERAND(Object, Line, TokenType)){
                            return true;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'AND'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'AND'\n");
                        return false;
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'BETWEEN'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'BETWEEN'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("IS")){
            h++;
            if(CONDITION3(Object, Line, TokenType)){
                h++;
                if(Object.getElementAt(h).equals("NULL")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NULL'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NULL'\n");
                    return false;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean CONDITION3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("NOT")){
            return true;
        } else {h--; return true;}
    }
    public boolean CONDITION4(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
           h++;
           if(OPERAND(Object, Line, TokenType)){
               h++;
               if(CONDITION4(Object, Line, TokenType)){
                   return true;
               }
           }
       } else {h--; return true;}
        return false;
    }
    public boolean COMPARE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("<>")){
            return true;
        } else if(Object.getElementAt(h).equals("<=")){
            return true;
        } else if(Object.getElementAt(h).equals(">=")){
            return true;
        } else if(Object.getElementAt(h).equals("=")){
            return true;
        } else if(Object.getElementAt(h).equals("<")){
            return true;
        } else if(Object.getElementAt(h).equals(">")){
            return true;
        } else if(Object.getElementAt(h).equals("!=")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'COMPARE'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'COMPARE'\n");
            return false;
        }
    }
    public boolean OPERAND(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(SUMMAND(Object, Line, TokenType)){
            h++;
            if(OPERAND3(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }
    public boolean OPERAND3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("||")){
            h++;
            if(SUMMAND(Object, Line, TokenType)){
                h++;
                if(OPERAND3(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean SUMMAND(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(FACTOR(Object, Line, TokenType)){
            h++;
            if(SUMMAND2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }
    public boolean SUMMAND2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("+")){
            h++;
            if(FACTOR(Object, Line, TokenType)){
                h++;
                if(SUMMAND2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else if(Object.getElementAt(h).equals("-")){
            h++;
            if(FACTOR(Object, Line, TokenType)){
                h++;
                if(SUMMAND2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean FACTOR(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TERM(Object, Line, TokenType)){
            h++;
            if(FACTOR2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }
    public boolean FACTOR2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("*")){
            h++;
            if(TERM(Object, Line, TokenType)){
                h++;
                if(FACTOR2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else if(Object.getElementAt(h).equals("/")){
            h++;
            if(TERM(Object, Line, TokenType)){
                h++;
                if(FACTOR2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean COLUMNREF(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
           h++;
            if(Object.getElementAt(h).equals(".")){
               h++;
               if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                   return true;
               } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    return false;
                }
           }
       } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            return false;
        }
        return false;
    }
    public boolean TABLEALIAS(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            return false;
        }
    }
    public boolean ROWVALUECONSTRUCTOR(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("(")){
            h++;
            if(TERM(Object, Line, TokenType)){
                h++;
                if(Object.getElementAt(h).equals(",")){
                    h++;
                    if(TERM(Object, Line, TokenType)){
                        h++;
                        if(ROWVALUECONSTRUCTOR2(Object, Line, TokenType)){
                            return true;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t','\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t','\n");
                    return false;
                }
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
            return false;
        }
        return false;
    }
    public boolean ROWVALUECONSTRUCTOR2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(TERM(Object, Line, TokenType)){
                h++;
                if(ROWVALUECONSTRUCTOR2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else if(Object.getElementAt(h).equals(")")){ 
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
            return false;
        }
        return false;
    }
    public boolean COLUMNALIAS(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            return false;
        }
    }
    public boolean TABLEEXPRESSION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TABLENAME(Object, Line, TokenType)){
            h++;
            if(TABLEEXPRESSION2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }
    public boolean TABLEEXPRESSION2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TABLEALIAS(Object, Line, TokenType)){
            return true;
        } else if(Object.getElementAt(h).equals("AS")){
            h++;
            if(TABLEALIAS(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals(".")){
            h++;
            if(TABLEALIAS(Object, Line, TokenType)){
                return true;
            }
        }
        else {h--; return true;}
        return false;
    }
    public boolean TABLENAME(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            return false;
        }
    }
    
    public boolean COLUMNDEF(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(COLUMNREF(Object, Line, TokenType)){
            h++;
            if(DATATYPE(Object, Line, TokenType)){
                h++;
                if(COLUMNDEF2(Object, Line, TokenType)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean COLUMNDEF2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(COLUMNDEF4(Object, Line, TokenType)){
            h++;
            if(Object.getElementAt(h).equals("NULL")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NULL'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NULL'\n");
                return false;
            }
        } else if(COLUMNDEF5(Object, Line, TokenType)){
            h++;
            if(Object.getElementAt(h).equals("PRIMARY")){
                h++;
                if(Object.getElementAt(h).equals("KEY")){
                    h++;
                    if(COLUMNDEF3(Object, Line, TokenType)){
                        return true;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'PRIMARY'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'PRIMARY'\n");
                    return false;
                }
            }
        }
        return false;
    }
    public boolean COLUMNDEF3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("ASC")){
            return true;
        } else if(Object.getElementAt(h).equals("DESC")){
            return true;
        } else {h--; return true;}
    }
    public boolean COLUMNDEF4(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("NOT")){
            return true;
        } else {h--; return true;}
    }
    public boolean COLUMNDEF5(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("NULL")){
            return true;
        } else if(Object.getElementAt(h).equals("NOT")){
            h++;
            if(Object.getElementAt(h).equals("NULL")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NULL'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NULL'\n");
                return false;
            }
        } else {h--; return true;}
    }
    public boolean DATATYPE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("CHAR")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NUMBER(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else if(Object.getElementAt(h).equals("-")){
                    h++;
                    if(NUMBER(Object, Line, TokenType)){
                        h++;
                        if(Object.getElementAt(h).equals(")")){
                            return true;
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                            return false;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'- OR NUMBER'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'- OR NUMBER'\n");
                    return false;
                }
            }
        } else if(Object.getElementAt(h).equals("VARCHAR")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NUMBER(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else if(Object.getElementAt(h).equals("-")){
                    h++;
                    if(NUMBER(Object, Line, TokenType)){
                        h++;
                        if(Object.getElementAt(h).equals(")")){
                            return true;
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                            return false;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'- OR NUMBER'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'- OR NUMBER'\n");
                    return false;
                }
            }
        } else if(Object.getElementAt(h).equals("BINARY")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NUMBER(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else if(Object.getElementAt(h).equals("-")){
                    h++;
                    if(NUMBER(Object, Line, TokenType)){
                        h++;
                        if(Object.getElementAt(h).equals(")")){
                            return true;
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                            return false;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'- OR NUMBER'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'- OR NUMBER'\n");
                    return false;
                }
            }
        } else if(Object.getElementAt(h).equals("DECIMAL")){
            return true;
        } else if(Object.getElementAt(h).equals("TINYINT")){
            return true;
        } else if(Object.getElementAt(h).equals("SMALLINT")){
            return true;
        } else if(Object.getElementAt(h).equals("INTEGER")){
            return true;
        } else if(Object.getElementAt(h).equals("BIGINT")){
            return true;
        } else if(Object.getElementAt(h).equals("FLOAT")){
            return true;
        } else if(Object.getElementAt(h).equals("DOUBLE")){
            return true;
        } else if(Object.getElementAt(h).equals("TIMESTAMP")){
            return true;
        } else if(Object.getElementAt(h).equals("DATE")){
            return true;
        } else if(Object.getElementAt(h).equals("TIME")){
            return true;
        } else if(Object.getElementAt(h).equals("VARBINARY")){
            return true;
        } else if(Object.getElementAt(h).equals("UNISIGNED_TINYINT")){
            return true;
        } else if(Object.getElementAt(h).equals("UNISIGNED_SMALLINT")){
            return true;
        } else if(Object.getElementAt(h).equals("UNISIGNED_INT")){
            return true;
        } else if(Object.getElementAt(h).equals("UNISIGNED_LONG")){
            return true;
        } else if(Object.getElementAt(h).equals("UNISIGNED_FLOAT")){
            return true;
        } else if(Object.getElementAt(h).equals("UNISIGNED_DOUBLE")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DATATYPE'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DATATYPE'\n");
            return false;
        }
        return false;
    }
    public boolean ORDER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(EXPRESSION(Object, Line, TokenType)){
            h++;
            if(ORDER2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }
    public boolean ORDER2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("ASC")){
            h++;
            if(ORDER3(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("DESC")){
            h++;
            if(ORDER3(Object, Line, TokenType)){
                return true;
            }
        } else {h--; return true;}
        return false;
    }
    public boolean ORDER3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("NULLS")){
            h++;
            if(ORDER4(Object, Line, TokenType)){
                return true;
            }
        } else {h--; return true;}
        return false;
    }
    public boolean ORDER4(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("FIRST")){
            return true;
        } else if(Object.getElementAt(h).equals("LAST")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'FIRST OR LAST'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'FIRST OR LAST'\n");
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
