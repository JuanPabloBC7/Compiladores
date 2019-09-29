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
public class F2_DML_INSERT {
    DefaultListModel ll_Sentencia_Hija_Archivo = new DefaultListModel();
    String ls_Sentencia_Hija_Errores = "";
    
    public int NT_INSERT(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("INSERT")){
            i++;
            if(Object.getElementAt(i).equals("TOP")){
                i++;
                if(Object.getElementAt(i).equals("(")){
                    i++;
                    if(NT_EXPRESSION(Object, Line, TokenType, i)){
                        i++;
                        if(Object.getElementAt(i).equals(")")){
                            if(NT_INTO(Object, Line, TokenType, i)){
                                i++;
                                if(NT_FIN(Object, Line, TokenType, i)){
                                    ll_Sentencia_Hija_Archivo.addElement("INSERT Leido correctamente.");
                                    return i;
                                }
                            }
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                            return i;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                    return i;
                }
            } else if(NT_INTO(Object, Line, TokenType, i)){
                i++;
                if(NT_FIN(Object, Line, TokenType, i)){
                    ll_Sentencia_Hija_Archivo.addElement("INSERT Leido correctamente.");
                    return i;
                }
            }  else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'TOP OR OTHER'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'TOP OR OTHER'\n");
                return i;
            }
        }        
        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** INSERT no leido correctamente\n");
        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** INSERT no leido correctamente\n");
        return i;
    }
    
    public boolean NT_INTO(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("INTO")){
            i++;
            if(NT_INTO2(Object, Line, TokenType, i)){
                return true;
            }
        }else if(NT_INTO2(Object, Line, TokenType, i)){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'INTO'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'INTO'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_INTO2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_OBJECT(Object, Line, TokenType, i)){
            i++;
            if(NT_COLUMN_L(Object, Line, TokenType, i)){
                return true;
            }
        } else if(NT_RowsetFunc(Object, Line, TokenType, i)){
            i++;
            if(NT_COLUMN_L(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }
    
    public boolean NT_OBJECT(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            i++;
            if(Object.getElementAt(i).equals(".")){
                i++;
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
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'.'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'.'\n");
                        return false;
                    }
                }else if(Object.getElementAt(i).equals(".")){
                    i++;
                    if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID OR .'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID OR .'\n");
                    return false;
                }
            }else{ return true;}
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            return false;
        }
    }
    
    public boolean NT_RowsetFunc(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("OPENQUERY")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                    i++;
                    if(Object.getElementAt(i).equals(",")){
                        i++;
                        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                            i++;
                            if(Object.getElementAt(i).equals(")")){
                                return true;
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                                return false;
                            }
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                            return false;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t','\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t','\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                return false;
            }
        } else if(NT_oRowset(Object, Line, TokenType, i)){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'OPENQUERY'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'OPENQUERY'\n");
            return false;
        }
    }
    
    public boolean NT_oRowset(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("OPENROWSET")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(TokenType.getElementAt(i).equals("CADENA")){
                    i++;
                    if(Object.getElementAt(i).equals(",")){
                        i++;
                        if(TokenType.getElementAt(i).equals("CADENA")){
                            i++;
                            if(Object.getElementAt(i).equals(",")){
                                i++;
                                if(NT_oRowset2(Object, Line, TokenType, i)){
                                    i++;
                                    if(Object.getElementAt(i).equals(")")){
                                        return true;
                                    } else {
                                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                                        return false;
                                    }
                                }
                            }else if(Object.getElementAt(i).equals(";")){
                                i++;
                                if(TokenType.getElementAt(i).equals("CADENA")){
                                    i++;
                                    if(Object.getElementAt(i).equals(";")){
                                        i++;
                                        if(TokenType.getElementAt(i).equals("CADENA")){
                                            i++;
                                            if(Object.getElementAt(i).equals(",")){
                                                i++;
                                                if(NT_oRowset2(Object, Line, TokenType, i)){
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
                                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t','\n");
                                                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t','\n");
                                                return false;
                                            }
                                        } else {
                                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING'\n");
                                            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING'\n");
                                            return false;
                                        }
                                    } else {
                                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t';'\n");
                                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t';'\n");
                                        return false;
                                    }
                                } else {
                                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING'\n");
                                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING'\n");
                                    return false;
                                }
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t', OR ;'\n");
                                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t', OR ;'\n");
                                return false;
                            }
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING'\n");
                            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING'\n");
                            return false;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t','\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t','\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                return false;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'OPENROWSET'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'OPENROWSET'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_oRowset2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_OBJECT(Object, Line, TokenType, i)){
            return true;
        } else if(TokenType.getElementAt(i).equals("CADENA")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING'\n");
            return false;
        }
    }
    
    public boolean NT_COLUMN_L(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("(")){
            i++;
            if(NT_COLUMN_L2(Object, Line, TokenType, i)){
                i++;
                if(Object.getElementAt(i).equals(")")){
                    i++;
                    if(NT_VALUES(Object, Line, TokenType, i)){
                        return true;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                    return false;
                }
            }
        }else if(NT_VALUES(Object, Line, TokenType, i)){
                        return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_COLUMN_L2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            i++;
            if(NT_CONCAT_ID(Object, Line, TokenType, i)){
                return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_CONCAT_ID(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
            i++;
            if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                i++;
                if(NT_CONCAT_ID(Object, Line, TokenType, i)){
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
    
    public boolean NT_VALUES(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("VALUES")){
            i++;
            if(NT_VALUES2(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals("DEFAULT")){
            i++;
            if(Object.getElementAt(i).equals("VALUES")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'VALUES'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'VALUES'\n");
                return false;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'VALUES OR DEFAULT'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'VALUES OR DEFAULT'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_VALUES2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("(")){
            i++;
            if(Object.getElementAt(i).equals("DEFAULT")){
                i++;
                if(NT_CONCAT(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(")")){
                        i++;
                        if(NT_VALUES3(Object, Line, TokenType, i)){
                            return true;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        return false;
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DEFAULT'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DEFAULT'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("(")){
            i++;
            if(Object.getElementAt(i).equals("NULL")){
                i++;
                if(NT_CONCAT(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(")")){
                        i++;
                        if(NT_VALUES3(Object, Line, TokenType, i)){
                            return true;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        return false;
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NULL'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NULL'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("(")){
            i++;
            if(NT_EXPRESSION(Object, Line, TokenType, i)){
                i++;
                if(NT_CONCAT(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(")")){
                        i++;
                        if(NT_VALUES3(Object, Line, TokenType, i)){
                            return true;
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
    
    public boolean NT_CONCAT(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
            i++;
            if(Object.getElementAt(i).equals("DEFAULT")){
                i++;
                if(NT_CONCAT(Object, Line, TokenType, i)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DEFAULT'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DEFAULT'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals(",")){
            i++;
            if(Object.getElementAt(i).equals("NULL")){
                i++;
                if(NT_CONCAT(Object, Line, TokenType, i)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NULL'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NULL'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals(",")){
            i++;
            if(NT_EXPRESSION(Object, Line, TokenType, i)){
                i++;
                if(NT_CONCAT(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else { return true;}
        return false;
    }
    
    public boolean NT_VALUES3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("(")){
            i++;
            if(Object.getElementAt(i).equals("DEFAULT")){
                i++;
                if(NT_CONCAT(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(")")){
                        i++;
                        if(NT_VALUES3(Object, Line, TokenType, i)){
                            return true;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        return false;
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DEFAULT'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DEFAULT'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("(")){
            i++;
            if(Object.getElementAt(i).equals("NULL")){
                i++;
                if(NT_CONCAT(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(")")){
                        i++;
                        if(NT_VALUES3(Object, Line, TokenType, i)){
                            return true;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        return false;
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NULL'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NULL'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("(")){
            i++;
            if(NT_EXPRESSION(Object, Line, TokenType, i)){
                i++;
                if(NT_CONCAT(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(")")){
                        i++;
                        if(NT_VALUES3(Object, Line, TokenType, i)){
                            return true;
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
    
    public boolean NT_EXPRESSION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_CONSTANT(Object, Line, TokenType, i)){
            return true;
        } else if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            return true;
        } else if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
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
        } else if(Object.getElementAt(i).equals("@")){
            i++;
            if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("(")){
            i++;
            if(NT_EXPRESSION(Object, Line, TokenType, i)){
                i++;
                if(Object.getElementAt(i).equals(")")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                    return false;
                }
            }
        } else if(Object.getElementAt(i).equals("+")){
            i++;
            if(NT_EXPRESSION(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals("-")){
            i++;
            if(NT_EXPRESSION(Object, Line, TokenType, i)){
                return true;
            }
        } else if(NT_EXPRESSION(Object, Line, TokenType, i)){
            i++;
            if(NT_OPERANDOR(Object, Line, TokenType, i)){
                i++;
                if(NT_EXPRESSION(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else if(NT_EXPRESSION(Object, Line, TokenType, i)){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'EXPRESSION'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'EXPRESSION'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_CONSTANT(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("CADENA")){
            return true;
        } else if(TokenType.getElementAt(i).equals("DECIMAL")){
            return true;
        } else if(TokenType.getElementAt(i).equals("ENTERO")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING OR NUMBER'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING OR NUMBER'\n");
            return false;
        }
    }
    
    public boolean NT_OPERANDOR(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("+")){
            return true;
        } else if(Object.getElementAt(i).equals("-")){
            return true;
        } else if(Object.getElementAt(i).equals("=")){
            return true;
        } else if(Object.getElementAt(i).equals("*")){
            return true;
        } else if(Object.getElementAt(i).equals("/")){
            return true;
        } else if(Object.getElementAt(i).equals("%")){
            return true;
        } else if(Object.getElementAt(i).equals("AND")){
            return true;
        } else if(Object.getElementAt(i).equals("OR")){
            return true;
        } else if(Object.getElementAt(i).equals("NOT")){
            return true;
        } else if(Object.getElementAt(i).equals(">")){
            return true;
        } else if(Object.getElementAt(i).equals("<")){
            return true;
        } else if(Object.getElementAt(i).equals(">=")){
            return true;
        } else if(Object.getElementAt(i).equals("<=")){
            return true;
        } else if(Object.getElementAt(i).equals("<>")){
            return true;
        } else if(Object.getElementAt(i).equals("!=")){
            return true;
        } else if(Object.getElementAt(i).equals("!<")){
            return true;
        } else if(Object.getElementAt(i).equals("!>")){
            return true;
        } else if(Object.getElementAt(i).equals("ALL")){
            return true;
        } else if(Object.getElementAt(i).equals("SOME")){
            return true;
        } else if(Object.getElementAt(i).equals("ANY")){
            return true;
        } else if(Object.getElementAt(i).equals("BETWEEN")){
            return true;
        } else if(Object.getElementAt(i).equals("EXISTS")){
            return true;
        } else if(Object.getElementAt(i).equals("IN")){
            return true;
        } else if(Object.getElementAt(i).equals("LIKE")){
            return true;
        } else if(Object.getElementAt(i).equals("SOME")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'OPERATOR'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'OPERATOR'\n");
            return false;
        }
    }
    
    public boolean NT_EXPRESSION2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("SUM")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NT_ALL_DIST(Object, Line, TokenType, i)){
                    i++;
                    if(NT_EXPRESSION(Object, Line, TokenType, i)){
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
        } else if(Object.getElementAt(i).equals("GETDATE")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(Object.getElementAt(i).equals(")")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                return false;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'SUM OR GETDATE'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'SUM OR GETDATE'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_ALL_DIST(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("ALL")){
            return true;
        } else if(Object.getElementAt(i).equals("DISTINCT")){
            return true;
        } else {return true;}
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
