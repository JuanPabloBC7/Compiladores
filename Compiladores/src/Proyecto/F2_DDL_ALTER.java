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
public class F2_DDL_ALTER {
    DefaultListModel ll_Sentencia_Hija_Archivo = new DefaultListModel();
    String ls_Sentencia_Hija_Errores = "";

    public int NT_ALTER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("ALTER")){
            i++;
            if(NT_OPTIONS(Object, Line, TokenType, i)){
                i++;
                if(NT_FIN(Object, Line, TokenType, i)){
                    ll_Sentencia_Hija_Archivo.addElement("ALTER Leido correctamente.");
                    return i;
                }
            }
        }
        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** ALTER no leido correctamente\n");
        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** ALTER no leido correctamente\n");
        return i;
    }

    public boolean NT_OPTIONS(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("DATABASE")){
            i++;
            if(NT_DATABASE(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals("INDEX")){
            i++;
            if(NT_INDEX(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals("TABLE")){
            i++;
            if(NT_TABLE(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals("USER")){
            i++;
            if(NT_USER(Object, Line, TokenType, i)){
                return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DATABASE, INDEX, TALBE OR USER'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DATABASE, INDEX, TALBE OR USER'\n");
            return false;
        }
        return false;
    }

    public boolean NT_DATABASE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_ID(Object, Line, TokenType, i)){
            i++;
            if(NT_ALT_BASE(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_ID(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            return true;
        } else if(Object.getElementAt(i).equals("CURRENT")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR OR CURRENT'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR OR CURRENT'\n");
            return false;
        }
    }

    public boolean NT_ALT_BASE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("MODIFY")){
            i++;
            if(Object.getElementAt(i).equals("NAME")){
                i++;
                if(Object.getElementAt(i).equals("=")){
                    i++;
                    if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
                        return false;
                    }
                }else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'='\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'='\n");
                    return false;
                }
            }else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NAME'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NAME'\n");
                return false;
            }
        }else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'MODIFY'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'MODIFY'\n");
            return false;
        }
    }

    public boolean NT_INDEX(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_NAME_INDEX(Object, Line, TokenType, i)){
            i++;
            if(Object.getElementAt(i).equals("ON")){
                i++;
                if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
                    i++;
                    if(NT_ACTION_INDEX(Object, Line, TokenType, i)){
                        return true;
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ON'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ON'\n");
                return false;
            }
        }
        return false;
    }

    public boolean NT_NAME_INDEX(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            return true;
        } else if(Object.getElementAt(i).equals("ALL")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR OR ALL'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR OR ALL'\n");
            return false;
        }
    }

    public boolean NT_OBJECT_NAME(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            i++;
            if(NT_OBJECT_NAME2(Object, Line, TokenType, i)){
                return true;
            }
        } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
                return false;
            }
        return false;
    }

    public boolean NT_OBJECT_NAME2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(".")){
            i++;
            if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                i++;
                if(NT_OBJECT_NAME3(Object, Line, TokenType, i)){
                    return true;
                }
            } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    return false;
                }
        } else {return true;}
        return false;
    }

    public boolean NT_OBJECT_NAME3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(".")){
            i++;
            if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                return true;
            } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    return false;
                }
        } else {return true;}
    }

    public boolean NT_ACTION_INDEX(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("DISABLE")){
            return true;
        } else if(Object.getElementAt(i).equals("REBUILD")){
            return true;
        } else if(Object.getElementAt(i).equals("UNUSABLE")){
            return true;
        } else if(Object.getElementAt(i).equals("DISABLE")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t''\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t''\n");
            return false;
        }
    }

    public boolean NT_TABLE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
            i++;
            if(NT_ACTION_ALTER(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_ACTION_ALTER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("ALTER")){
            i++;
            if(Object.getElementAt(i).equals("COLUMN")){
                i++;
                if(NT_CL_ADD3(Object, Line, TokenType, i)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'COLUMN'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'COLUMN'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("ADD")){
            i++;
            if(NT_TYPE_ADD(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals("DROP")){
            i++;
            if(NT_CL_DROP(Object, Line, TokenType, i)){
                return true;
            }
        } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ALTER, COLUMN, ADD OR DROP'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ALTER, COLUMN, ADD OR DROP'\n");
                return false;
            }
        return false;
    }

    public boolean NT_TYPE_ADD(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("CONSTRAINT")){
            i++;
            if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
                i++;
                if(NT_CONSTRAINT_TYPE(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else if(NT_CL_ADD(Object, Line, TokenType, i)){
            return true;
        } else if(Object.getElementAt(i).equals("FOREIGN")){
            i++;
            if(Object.getElementAt(i).equals("KEY")){
                i++;
                if(Object.getElementAt(i).equals("(")){
                    i++;
                    if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
                        i++;
                        if(Object.getElementAt(i).equals(")")){
                            i++;
                            if(Object.getElementAt(i).equals("REFERENCES")){
                                i++;
                                if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
                                    i++;
                                    if(Object.getElementAt(i).equals("(")){
                                        i++;
                                        if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
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
                                }
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'REFERENCES'\n");
                                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'REFERENCES'\n");
                                return false;
                            }
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
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'KEY'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'KEY'\n");
                return false;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'CONSTRAINT OR FOREIGN'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'CONSTRAINT OR FOREIGN'\n");
            return false;
        }
        return false;
    }

    public boolean NT_CL_ADD(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_CL_ADD3(Object, Line, TokenType, i)){
            i++;
            if(NT_CL_ADD2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_CL_ADD2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_CL_ADD3(Object, Line, TokenType, i)){
            i++;
            if(Object.getElementAt(i).equals(",")){
                i++;
                if(NT_CL_ADD(Object, Line, TokenType, i)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t','\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t','\n");
                return false;
            }
        } else {return true;}
        return false;
    }

    public boolean NT_CL_ADD3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
            i++;
            if(NT_DATA_TYPE(Object, Line, TokenType, i)){
                i++;
                if(NT_CONSTRAINT_TYPE(Object, Line, TokenType, i)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean NT_DATA_TYPE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("CHAR")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NT_NUM(Object, Line, TokenType, i)){
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
        } else if(Object.getElementAt(i).equals("VARCHAR")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NT_NUM(Object, Line, TokenType, i)){
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
        } else if(Object.getElementAt(i).equals("DECIMAL")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NT_NUM(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(",")){
                        i++;
                        if(NT_NUM(Object, Line, TokenType, i)){
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
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("NUMERIC")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NT_NUM(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(",")){
                        i++;
                        if(NT_NUM(Object, Line, TokenType, i)){
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
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("FLOAT")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NT_NUM(Object, Line, TokenType, i)){
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
        } else if(Object.getElementAt(i).equals("VARBINARY")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(Object.getElementAt(i).equals("MAX")){
                    i++;
                    if(Object.getElementAt(i).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'MAX'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'MAX'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("TEXT")){
                return true;
        }  else if(Object.getElementAt(i).equals("IMAGE")){
                return true;
        } else if(Object.getElementAt(i).equals("IMAGE")){
                return true;
        } else if(Object.getElementAt(i).equals("VARBINARY")){
                return true;
        } else if(Object.getElementAt(i).equals("DATE")){
                return true;
        } else if(Object.getElementAt(i).equals("DATETIME")){
                return true;
        } else if(Object.getElementAt(i).equals("DATETIME")){
                return true;
        } else if(Object.getElementAt(i).equals("SMALLDATETIME")){
                return true;
        }  else if(Object.getElementAt(i).equals("TIME")){
                return true;
        } else if(Object.getElementAt(i).equals("DATETIMEOFFSET")){
                return true;
        } else if(Object.getElementAt(i).equals("TIMESTAMP")){
                return true;
        } else if(Object.getElementAt(i).equals("MONEY")){
                return true;
        } else if(Object.getElementAt(i).equals("REAL")){
                return true;
        } else if(Object.getElementAt(i).equals("BIT")){
                return true;
        } else if(Object.getElementAt(i).equals("INT")){
                return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DATA TYPE'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DATA TYPE'\n");
            return false;
        }
        return false;
    }

    public boolean NT_CONSTRAINT(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_CONSTRAINT3(Object, Line, TokenType, i)){
            i++;
            if(NT_CONSTRAINT2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_CONSTRAINT2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_CONSTRAINT3(Object, Line, TokenType, i)){
            i++;
            if(NT_CONSTRAINT(Object, Line, TokenType, i)){
                return true;
            }
        } else {return true;}
        return false;
    }

    public boolean NT_CONSTRAINT3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("NULL")){
            return true;
        } else if(Object.getElementAt(i).equals("UNIQUE")){
            return true;
        } else if(Object.getElementAt(i).equals("PRIMARY")){
            i++;
            if(Object.getElementAt(i).equals("KEY")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'KEY'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'KEY'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("FOREIGN")){
            i++;
            if(Object.getElementAt(i).equals("KEY")){
                i++;
                if(Object.getElementAt(i).equals("(")){
                    i++;
                    if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
                        i++;
                        if(Object.getElementAt(i).equals(")")){
                            i++;
                            if(Object.getElementAt(i).equals("REFERENCES")){
                                i++;
                                if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
                                    i++;
                                    if(Object.getElementAt(i).equals("(")){
                                        i++;
                                        if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
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
                                }
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'REFERENCES'\n");
                                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'REFERENCES'\n");
                                return false;
                            }
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
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'KEY'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'KEY'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("DEFAULT")){
            i++;
            if(NT_DATA_TYPE2(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals("IDENTIFY")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NT_NUM(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(",")){
                        i++;
                        if(NT_NUM(Object, Line, TokenType, i)){
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
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                return false;
            }
        } else {return true;}
        return false;
    }

    public boolean NT_CONSTRAINT_TYPE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("UNIQUE")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
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
        } else if(Object.getElementAt(i).equals("FOREIGN")){
            i++;
            if(Object.getElementAt(i).equals("KEY")){
                i++;
                if(Object.getElementAt(i).equals("(")){
                    i++;
                    if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
                        i++;
                        if(Object.getElementAt(i).equals(")")){
                            i++;
                            if(Object.getElementAt(i).equals("REFERENCES")){
                                i++;
                                if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
                                    i++;
                                    if(Object.getElementAt(i).equals("(")){
                                        i++;
                                        if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
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
                                }
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'REFERENCES'\n");
                                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'REFERENCES'\n");
                                return false;
                            }
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
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'KEY'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'KEY'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("PRIMARY")){
            i++;
            if(Object.getElementAt(i).equals("KEY")){
                i++;
                if(Object.getElementAt(i).equals("(")){
                    i++;
                    if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
                        i++;
                        if(Object.getElementAt(i).equals(")")){
                            return true;
                        }  else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')'\n");
                            return false;
                        }
                    }
                }  else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'('\n");
                    return false;
                }
            }  else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'KEY'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'KEY'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("CHECK")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NT_EXP_LOGIC(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals(")")){
                        return true;
                    }  else {
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
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'UNIQUE, FOREIGN, PRIMARY OR CHECK'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'UNIQUE, FOREIGN, PRIMARY OR CHECK'\n");
            return false;
        }
        return false;
    }

    public boolean NT_EXP_LOGIC(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_EXP_LOGIC3(Object, Line, TokenType, i)){
            i++;
            if(NT_EXP_LOGIC2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_EXP_LOGIC2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_EXP_LOGIC3(Object, Line, TokenType, i)){
            i++;
            if(NT_OP_LOGIC(Object, Line, TokenType, i)){
                i++;
                if(NT_EXP_LOGIC(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }

    public boolean NT_EXP_LOGIC3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
            i++;
            if(NT_OP(Object, Line, TokenType, i)){
                i++;
                if(NT_DATA_TYPE2(Object, Line, TokenType, i)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean NT_DATA_TYPE2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("CADENA")){
            return true;
        } else if(TokenType.getElementAt(i).equals("BOOLEAN")){
            return true;
        } else if(TokenType.getElementAt(i).equals("ENTERO")){
            return true;
        } else if(TokenType.getElementAt(i).equals("DECIMAL")){
            return true;
        }  else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DATA TYPE'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DATA TYPE'\n");
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
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'OPERATION'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'OPERATION'\n");
            return false;
        }
    }

    public boolean NT_OP_LOGIC(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("AND")){
            return true;
        } else if(Object.getElementAt(i).equals("OR")){
            return true;
        } else if(Object.getElementAt(i).equals("&&")){
            return true;
        } else if(Object.getElementAt(i).equals("||")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'LOGICAL OPERATION'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'LOGICAL OPERATION'\n");
            return false;
        }
    }

    public boolean NT_NUM(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("ENTERO")){
            return true;
        } else if(TokenType.getElementAt(i).equals("DECIMAL")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NUMBER'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NUMBER'\n");
            return false;
        }
    }

    public boolean NT_CL_DROP(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("CONSTRAINT")){
            i++;
            if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
                    return true;
            }
        } else if(Object.getElementAt(i).equals("COLUMN")){
            i++;
            if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
                    return true;
            }
        }  else if(Object.getElementAt(i).equals("INDEX")){
            i++;
            if(NT_OBJECT_NAME(Object, Line, TokenType, i)){
                    return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'CONSTRAIN, COLUMN OR INDEX'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'CONSTRAIN, COLUMN OR INDEX'\n");
            return false;
        }
        return false;
    }

    public boolean NT_USER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            i++;
            if(Object.getElementAt(i).equals("WITH")){
                i++;
                if(NT_SET_ITEM(Object, Line, TokenType, i)){
                    return true;
                }
            }  else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'WITH'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'WITH'\n");
                return false;
            }
        }  else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
            return false;
        }
        return false;
    }

    public boolean NT_SET_ITEM(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_SET_ITEM3(Object, Line, TokenType, i)){
            i++;
            if(NT_SET_ITEM2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_SET_ITEM2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(",")){
            i++;
            if(NT_SET_ITEM(Object, Line, TokenType, i)){
                return true;
            }
        } else {return true;}
        return false;
    }

    public boolean NT_SET_ITEM3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("NAME")){
            i++;
            if(Object.getElementAt(i).equals("=")){
                i++;
                if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'='\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'='\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("LOGIN")){
            i++;
            if(Object.getElementAt(i).equals("=")){
                i++;
                if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'='\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'='\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("PASSWORD")){
            i++;
            if(Object.getElementAt(i).equals("=")){
                i++;
                if(TokenType.getElementAt(i).equals("CADENA")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'STRING'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'='\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'='\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("DEFAULT_SCHEMA")){
            i++;
            if(Object.getElementAt(i).equals("=")){
                i++;
                if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'='\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'='\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("NULL")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NAME, LOGIN, PASSWORD, DEFAULT_SCHEMA OR NULL'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NAME, LOGIN, PASSWORD, DEFAULT_SCHEMA OR NULL'\n");
            return false;
        }
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
