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
    int h; 
    
    public int NT_INSERT(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        h = i;
        if(Object.getElementAt(h).equals("INSERT")){
            h++;
            if(Object.getElementAt(h).equals("TOP")){
                h++;
                if(Object.getElementAt(h).equals("(")){
                    h++;
                    if(NT_EXPRESSION(Object, Line, TokenType)){
                        h++;
                        if(Object.getElementAt(h).equals(")")){
                            if(NT_INTO(Object, Line, TokenType)){
                                h++;
                                if(NT_FIN(Object, Line, TokenType)){
                                    h++;
                                    ls_Sentencia_Hija_Errores += ("INSERT Leido correctamente.\n");
                                    return h;
                                }
                            }
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                            return h;
                        }
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                    return h;
                }
            } else if(NT_INTO(Object, Line, TokenType)){
                h++;
                if(NT_FIN(Object, Line, TokenType)){
                    h++;
                    ls_Sentencia_Hija_Errores += ("INSERT Leido correctamente.\n");
                    return h;
                }
            }  else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'TOP OR OTHER'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'TOP OR OTHER'\n");
                return h;
            }
        }        
        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** INSERT no leido correctamente\n");
        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** INSERT no leido correctamente\n");
        return h;
    }
    
    public boolean NT_INTO(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("INTO")){
            h++;
            if(NT_INTO2(Object, Line, TokenType)){
                return true;
            }
        }else if(NT_INTO2(Object, Line, TokenType)){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'INTO'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'INTO'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_INTO2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_OBJECT(Object, Line, TokenType)){
            h++;
            if(NT_COLUMN_L(Object, Line, TokenType)){
                return true;
            }
        } else if(NT_RowsetFunc(Object, Line, TokenType)){
            h++;
            if(NT_COLUMN_L(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }
    
    public boolean NT_OBJECT(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            h++;
            if(Object.getElementAt(h).equals(".")){
                h++;
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
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'.'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'.'\n");
                        return false;
                    }
                }else if(Object.getElementAt(h).equals(".")){
                    h++;
                    if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID OR .'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID OR .'\n");
                    return false;
                }
            }else{h--; return true;}
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            return false;
        }
    }
    
    public boolean NT_RowsetFunc(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("OPENQUERY")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                    h++;
                    if(Object.getElementAt(h).equals(",")){
                        h++;
                        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                            h++;
                            if(Object.getElementAt(h).equals(")")){
                                return true;
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                                return false;
                            }
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                            return false;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t','\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t','\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                return false;
            }
        } else if(NT_oRowset(Object, Line, TokenType)){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'OPENQUERY'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'OPENQUERY'\n");
            return false;
        }
    }
    
    public boolean NT_oRowset(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("OPENROWSET")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(TokenType.getElementAt(h).equals("CADENA")){
                    h++;
                    if(Object.getElementAt(h).equals(",")){
                        h++;
                        if(TokenType.getElementAt(h).equals("CADENA")){
                            h++;
                            if(Object.getElementAt(h).equals(",")){
                                h++;
                                if(NT_oRowset2(Object, Line, TokenType)){
                                    h++;
                                    if(Object.getElementAt(h).equals(")")){
                                        return true;
                                    } else {
                                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                                        return false;
                                    }
                                }
                            }else if(Object.getElementAt(h).equals(";")){
                                h++;
                                if(TokenType.getElementAt(h).equals("CADENA")){
                                    h++;
                                    if(Object.getElementAt(h).equals(";")){
                                        h++;
                                        if(TokenType.getElementAt(h).equals("CADENA")){
                                            h++;
                                            if(Object.getElementAt(h).equals(",")){
                                                h++;
                                                if(NT_oRowset2(Object, Line, TokenType)){
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
                                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t','\n");
                                                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t','\n");
                                                return false;
                                            }
                                        } else {
                                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                                            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                                            return false;
                                        }
                                    } else {
                                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t';'\n");
                                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t';'\n");
                                        return false;
                                    }
                                } else {
                                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                                    return false;
                                }
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t', OR ;'\n");
                                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t', OR ;'\n");
                                return false;
                            }
                        } else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                            return false;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t','\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t','\n");
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
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'OPENROWSET'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'OPENROWSET'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_oRowset2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_OBJECT(Object, Line, TokenType)){
            return true;
        } else if(TokenType.getElementAt(h).equals("CADENA")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
            return false;
        }
    }
    
    public boolean NT_COLUMN_L(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("(")){
            h++;
            if(NT_COLUMN_L2(Object, Line, TokenType)){
                h++;
                if(Object.getElementAt(h).equals(")")){
                    h++;
                    if(NT_VALUES(Object, Line, TokenType)){
                        return true;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                    return false;
                }
            }
        }else if(NT_VALUES(Object, Line, TokenType)){
                        return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_COLUMN_L2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            h++;
            if(NT_CONCAT_ID(Object, Line, TokenType)){
                return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_CONCAT_ID(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                h++;
                if(NT_CONCAT_ID(Object, Line, TokenType)){
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
    
    public boolean NT_VALUES(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("VALUES")){
            h++;
            if(NT_VALUES2(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("DEFAULT")){
            h++;
            if(Object.getElementAt(h).equals("VALUES")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'VALUES'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'VALUES'\n");
                return false;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'VALUES OR DEFAULT'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'VALUES OR DEFAULT'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_VALUES2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("(")){
            h++;
            if(Object.getElementAt(h).equals("DEFAULT")){
                h++;
                if(NT_CONCAT(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        h++;
                        if(NT_VALUES3(Object, Line, TokenType)){
                            return true;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DEFAULT'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DEFAULT'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("(")){
            h++;
            if(Object.getElementAt(h).equals("NULL")){
                h++;
                if(NT_CONCAT(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        h++;
                        if(NT_VALUES3(Object, Line, TokenType)){
                            return true;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NULL'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NULL'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("(")){
            h++;
            if(NT_EXPRESSION(Object, Line, TokenType)){
                h++;
                if(NT_CONCAT(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        h++;
                        if(NT_VALUES3(Object, Line, TokenType)){
                            return true;
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
    
    public boolean NT_CONCAT(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(Object.getElementAt(h).equals("DEFAULT")){
                h++;
                if(NT_CONCAT(Object, Line, TokenType)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DEFAULT'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DEFAULT'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals(",")){
            h++;
            if(Object.getElementAt(h).equals("NULL")){
                h++;
                if(NT_CONCAT(Object, Line, TokenType)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NULL'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NULL'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals(",")){
            h++;
            if(NT_EXPRESSION(Object, Line, TokenType)){
                h++;
                if(NT_CONCAT(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }
    
    public boolean NT_VALUES3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("(")){
            h++;
            if(Object.getElementAt(h).equals("DEFAULT")){
                h++;
                if(NT_CONCAT(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        h++;
                        if(NT_VALUES3(Object, Line, TokenType)){
                            return true;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DEFAULT'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DEFAULT'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("(")){
            h++;
            if(Object.getElementAt(h).equals("NULL")){
                h++;
                if(NT_CONCAT(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        h++;
                        if(NT_VALUES3(Object, Line, TokenType)){
                            return true;
                        }
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NULL'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NULL'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("(")){
            h++;
            if(NT_EXPRESSION(Object, Line, TokenType)){
                h++;
                if(NT_CONCAT(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        h++;
                        if(NT_VALUES3(Object, Line, TokenType)){
                            return true;
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
    
    public boolean NT_EXPRESSION(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_CONSTANT(Object, Line, TokenType)){
            return true;
        } else if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            return true;
        } else if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
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
        } else if(Object.getElementAt(h).equals("@")){
            h++;
            if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("(")){
            h++;
            if(NT_EXPRESSION(Object, Line, TokenType)){
                h++;
                if(Object.getElementAt(h).equals(")")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                    return false;
                }
            }
        } else if(Object.getElementAt(h).equals("+")){
            h++;
            if(NT_EXPRESSION(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("-")){
            h++;
            if(NT_EXPRESSION(Object, Line, TokenType)){
                return true;
            }
        } else if(NT_EXPRESSION(Object, Line, TokenType)){
            h++;
            if(NT_OPERANDOR(Object, Line, TokenType)){
                h++;
                if(NT_EXPRESSION(Object, Line, TokenType)){
                    return true;
                }
            }
        } else if(NT_EXPRESSION(Object, Line, TokenType)){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'EXPRESSION'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'EXPRESSION'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_CONSTANT(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("CADENA")){
            return true;
        } else if(TokenType.getElementAt(h).equals("DECIMAL")){
            return true;
        } else if(TokenType.getElementAt(h).equals("ENTERO")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING OR NUMBER'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING OR NUMBER'\n");
            return false;
        }
    }
    
    public boolean NT_OPERANDOR(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("+")){
            return true;
        } else if(Object.getElementAt(h).equals("-")){
            return true;
        } else if(Object.getElementAt(h).equals("=")){
            return true;
        } else if(Object.getElementAt(h).equals("*")){
            return true;
        } else if(Object.getElementAt(h).equals("/")){
            return true;
        } else if(Object.getElementAt(h).equals("%")){
            return true;
        } else if(Object.getElementAt(h).equals("AND")){
            return true;
        } else if(Object.getElementAt(h).equals("OR")){
            return true;
        } else if(Object.getElementAt(h).equals("NOT")){
            return true;
        } else if(Object.getElementAt(h).equals(">")){
            return true;
        } else if(Object.getElementAt(h).equals("<")){
            return true;
        } else if(Object.getElementAt(h).equals(">=")){
            return true;
        } else if(Object.getElementAt(h).equals("<=")){
            return true;
        } else if(Object.getElementAt(h).equals("<>")){
            return true;
        } else if(Object.getElementAt(h).equals("!=")){
            return true;
        } else if(Object.getElementAt(h).equals("!<")){
            return true;
        } else if(Object.getElementAt(h).equals("!>")){
            return true;
        } else if(Object.getElementAt(h).equals("ALL")){
            return true;
        } else if(Object.getElementAt(h).equals("SOME")){
            return true;
        } else if(Object.getElementAt(h).equals("ANY")){
            return true;
        } else if(Object.getElementAt(h).equals("BETWEEN")){
            return true;
        } else if(Object.getElementAt(h).equals("EXISTS")){
            return true;
        } else if(Object.getElementAt(h).equals("IN")){
            return true;
        } else if(Object.getElementAt(h).equals("LIKE")){
            return true;
        } else if(Object.getElementAt(h).equals("SOME")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'OPERATOR'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'OPERATOR'\n");
            return false;
        }
    }
    
    public boolean NT_EXPRESSION2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("SUM")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NT_ALL_DIST(Object, Line, TokenType)){
                    h++;
                    if(NT_EXPRESSION(Object, Line, TokenType)){
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
        } else if(Object.getElementAt(h).equals("GETDATE")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(Object.getElementAt(h).equals(")")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                return false;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'SUM OR GETDATE'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'SUM OR GETDATE'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_ALL_DIST(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("ALL")){
            return true;
        } else if(Object.getElementAt(h).equals("DISTINCT")){
            return true;
        } else {h--; return true;}
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
