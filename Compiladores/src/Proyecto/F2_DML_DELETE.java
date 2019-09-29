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
public class F2_DML_DELETE {
    DefaultListModel ll_Sentencia_Hija_Archivo = new DefaultListModel();
    String ls_Sentencia_Hija_Errores = "";
    int h;
    
    public int S1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        h=i;
        if(Object.getElementAt(h).equals("DELETE")){
            h++;
            if(Object.getElementAt(h).equals("FROM")){
                h++;
                if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                    h++;
                    if(S2(Object, Line, TokenType)){
                        h++;
                        if(NT_FIN(Object, Line, TokenType)){
                            h++;
                            ls_Sentencia_Hija_Errores = ("DROP Leido correctamente.\n");
                            return h;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                }
            } else {
            }
        }
        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** DELETE no leido correctamente\n");
        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** DELETE no leido correctamente\n");
        return h;
    }
    public boolean S2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(A1(Object, Line, TokenType)){
            return true;
        } else if(Object.getElementAt(h).equals("WHERE")){
            h++;
            if(EXPRESSION(Object, Line, TokenType)){
                h++;
                if(A1(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }
    public boolean A1(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("ORDER")){
            h++;
            if(Object.getElementAt(h).equals("BY")){
                h++;
                if(ORDER(Object, Line, TokenType)){
                    h++;
                    if(A2(Object, Line, TokenType)){
                        h++;
                        if(A3(Object, Line, TokenType)){
                            return true;
                        }
                    }
                }
            } else {
                return false;
            }
        } else if(A3(Object, Line, TokenType)){
            return true;
        } else {h--; return true;}
        return false;
    }
    public boolean A2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(ORDER(Object, Line, TokenType)){
                h++;
                if(A2(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    public boolean A3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("LIMIT")){
            h++;
            if(A4(Object, Line, TokenType)){
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
    public boolean A4(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(BINDPARAMETER(Object, Line, TokenType)){
            return true;
        } else if(NUMBER(Object, Line, TokenType)){
            return true;
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
       }
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
                    if(CONSTANTOPERAND(Object, Line, TokenType)){
                        h++;
                        if(CONDITION4(Object, Line, TokenType)){
                            h++;
                            if(Object.getElementAt(h).equals(")")){
                                return true;
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                                return false;
                            }
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                    return false;
                }
            } else {
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
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'AND'\n");
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if(Object.getElementAt(h).equals("IS")){
            h++;
            if(CONDITION3(Object, Line, TokenType)){
                h++;
                if(Object.getElementAt(h).equals("NULL")){
                    return true;
                } else {
                    return false;
                }
            }
        } else if(Object.getElementAt(h).equals("NOT")){
            h++;
            if(EXPRESSION(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("(")){
            h++;
            if(EXPRESSION(Object, Line, TokenType)){
                h++;
                if(Object.getElementAt(h).equals(")")){
                    return true;
                }  else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                    return false;
                }
            }
        } else {
            return false;
        }
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
           if(CONSTANTOPERAND(Object, Line, TokenType)){
               h++;
               if(CONDITION4(Object, Line, TokenType)){
                   return true;
               }
           }
       } else {h--; return true;}
        return false;
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
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
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
        }  else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING OR NUMBER'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING OR NUMBER'\n");
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
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
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
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
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
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
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
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
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
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                }
            } else {
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
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
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
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
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
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
        } else {
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
                    return false;
                }
            }
        } else if(Object.getElementAt(h).equals("END")){
            return true;
        } else {
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
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
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
                    return false;
                }
            }
        } else if(Object.getElementAt(h).equals("END")){
            return true;
        } else {
            return false;
        }
        return false;
    }
    public boolean COLUMNREF(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
       if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
           return true;
       } else if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
           h++;
           if(Object.getElementAt(h).equals(".")){
               h++;
               if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                   return true;
               } else {
                    return false;
                }
           } else {
                return false;
            }
       } else {
            return false;
        }
    }
    public boolean TABLEALIAS(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            return true;
        }  else {
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
                    return false;
                }
            }
        } else {
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
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'COMPARE'\n");
            return false;
        }
    }
    public boolean CONSTANTOPERAND(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(OPERAND(Object, Line, TokenType)){
            return true;
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
        } else {
            return false;
        }
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
        }  else {
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
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'; OR GO'\n");
            return false;
        }
    }
}
