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
    int h;
    
    public int NT_ALTER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        h = i;
        if(Object.getElementAt(h).equals("ALTER")){
            h++;
            if(NT_OPTIONS(Object, Line, TokenType)){
                h++;
                if(NT_FIN(Object, Line, TokenType)){
                    ls_Sentencia_Hija_Errores += ("ALTER Leido correctamente.\n");
                    return h;
                }
            }
        }
        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** ALTER no leido correctamente\n");
        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** ALTER no leido correctamente\n");
        return h;
    }

    public boolean NT_OPTIONS(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("DATABASE")){
            h++;
            if(NT_DATABASE(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("INDEX")){
            h++;
            if(NT_INDEX(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("TABLE")){
            h++;
            if(NT_TABLE(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("USER")){
            h++;
            if(NT_USER(Object, Line, TokenType)){
                return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DATABASE, INDEX, TALBE OR USER'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DATABASE, INDEX, TALBE OR USER'\n");
            return false;
        }
        return false;
    }

    public boolean NT_DATABASE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_ID(Object, Line, TokenType)){
            h++;
            if(NT_ALT_BASE(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_ID(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            return true;
        } else if(Object.getElementAt(h).equals("CURRENT")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR OR CURRENT'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR OR CURRENT'\n");
            return false;
        }
    }

    public boolean NT_ALT_BASE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("MODIFY")){
            h++;
            if(Object.getElementAt(h).equals("NAME")){
                h++;
                if(Object.getElementAt(h).equals("=")){
                    h++;
                    if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
                        return false;
                    }
                }else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'='\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'='\n");
                    return false;
                }
            }else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NAME'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NAME'\n");
                return false;
            }
        }else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'MODIFY'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'MODIFY'\n");
            return false;
        }
    }

    public boolean NT_INDEX(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_NAME_INDEX(Object, Line, TokenType)){
            h++;
            if(Object.getElementAt(h).equals("ON")){
                h++;
                if(NT_OBJECT_NAME(Object, Line, TokenType)){
                    h++;
                    if(NT_ACTION_INDEX(Object, Line, TokenType)){
                        return true;
                    }
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ON'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ON'\n");
                return false;
            }
        }
        return false;
    }

    public boolean NT_NAME_INDEX(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            return true;
        } else if(Object.getElementAt(h).equals("ALL")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR OR ALL'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR OR ALL'\n");
            return false;
        }
    }

    public boolean NT_OBJECT_NAME(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            h++;
            if(NT_OBJECT_NAME2(Object, Line, TokenType)){
                return true;
            }
        } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
                return false;
            }
        return false;
    }

    public boolean NT_OBJECT_NAME2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(".")){
            h++;
            if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                h++;
                if(NT_OBJECT_NAME3(Object, Line, TokenType)){
                    return true;
                }
            } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    return false;
                }
        } else {h--; return true;}
        return false;
    }

    public boolean NT_OBJECT_NAME3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(".")){
            h++;
            if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                return true;
            } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    return false;
                }
        } else {h--; return true;}
    }

    public boolean NT_ACTION_INDEX(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("DISABLE")){
            return true;
        } else if(Object.getElementAt(h).equals("REBUILD")){
            return true;
        } else if(Object.getElementAt(h).equals("UNUSABLE")){
            return true;
        } else if(Object.getElementAt(h).equals("DISABLE")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t''\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t''\n");
            return false;
        }
    }

    public boolean NT_TABLE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_OBJECT_NAME(Object, Line, TokenType)){
            h++;
            if(NT_ACTION_ALTER(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_ACTION_ALTER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("ALTER")){
            h++;
            if(Object.getElementAt(h).equals("COLUMN")){
                h++;
                if(NT_CL_ADD3(Object, Line, TokenType)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'COLUMN'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'COLUMN'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("ADD")){
            h++;
            if(NT_TYPE_ADD(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("DROP")){
            h++;
            if(NT_CL_DROP(Object, Line, TokenType)){
                return true;
            }
        } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ALTER, COLUMN, ADD OR DROP'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ALTER, COLUMN, ADD OR DROP'\n");
                return false;
            }
        return false;
    }

    public boolean NT_TYPE_ADD(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("CONSTRAINT")){
            h++;
            if(NT_OBJECT_NAME(Object, Line, TokenType)){
                h++;
                if(NT_CONSTRAINT_TYPE(Object, Line, TokenType)){
                    return true;
                }
            }
        } else if(NT_CL_ADD(Object, Line, TokenType)){
            return true;
        } else if(Object.getElementAt(h).equals("FOREIGN")){
            h++;
            if(Object.getElementAt(h).equals("KEY")){
                h++;
                if(Object.getElementAt(h).equals("(")){
                    h++;
                    if(NT_OBJECT_NAME(Object, Line, TokenType)){
                        h++;
                        if(Object.getElementAt(h).equals(")")){
                            h++;
                            if(Object.getElementAt(h).equals("REFERENCES")){
                                h++;
                                if(NT_OBJECT_NAME(Object, Line, TokenType)){
                                    h++;
                                    if(Object.getElementAt(h).equals("(")){
                                        h++;
                                        if(NT_OBJECT_NAME(Object, Line, TokenType)){
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
                                }
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'REFERENCES'\n");
                                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'REFERENCES'\n");
                                return false;
                            }
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
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'KEY'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'KEY'\n");
                return false;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'CONSTRAINT OR FOREIGN'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'CONSTRAINT OR FOREIGN'\n");
            return false;
        }
        return false;
    }

    public boolean NT_CL_ADD(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_CL_ADD3(Object, Line, TokenType)){
            h++;
            if(NT_CL_ADD2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_CL_ADD2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_CL_ADD3(Object, Line, TokenType)){
            h++;
            if(Object.getElementAt(h).equals(",")){
                h++;
                if(NT_CL_ADD(Object, Line, TokenType)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t','\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t','\n");
                return false;
            }
        } else {h--; return true;}
        return false;
    }

    public boolean NT_CL_ADD3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_OBJECT_NAME(Object, Line, TokenType)){
            h++;
            if(NT_DATA_TYPE(Object, Line, TokenType)){
                h++;
                if(NT_CONSTRAINT_TYPE(Object, Line, TokenType)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean NT_DATA_TYPE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("CHAR")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NT_NUM(Object, Line, TokenType)){
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
        } else if(Object.getElementAt(h).equals("VARCHAR")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NT_NUM(Object, Line, TokenType)){
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
        } else if(Object.getElementAt(h).equals("DECIMAL")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NT_NUM(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(",")){
                        h++;
                        if(NT_NUM(Object, Line, TokenType)){
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
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("NUMERIC")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NT_NUM(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(",")){
                        h++;
                        if(NT_NUM(Object, Line, TokenType)){
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
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("FLOAT")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NT_NUM(Object, Line, TokenType)){
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
        } else if(Object.getElementAt(h).equals("VARBINARY")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(Object.getElementAt(h).equals("MAX")){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'MAX'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'MAX'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("TEXT")){
                return true;
        }  else if(Object.getElementAt(h).equals("IMAGE")){
                return true;
        } else if(Object.getElementAt(h).equals("IMAGE")){
                return true;
        } else if(Object.getElementAt(h).equals("VARBINARY")){
                return true;
        } else if(Object.getElementAt(h).equals("DATE")){
                return true;
        } else if(Object.getElementAt(h).equals("DATETIME")){
                return true;
        } else if(Object.getElementAt(h).equals("DATETIME")){
                return true;
        } else if(Object.getElementAt(h).equals("SMALLDATETIME")){
                return true;
        }  else if(Object.getElementAt(h).equals("TIME")){
                return true;
        } else if(Object.getElementAt(h).equals("DATETIMEOFFSET")){
                return true;
        } else if(Object.getElementAt(h).equals("TIMESTAMP")){
                return true;
        } else if(Object.getElementAt(h).equals("MONEY")){
                return true;
        } else if(Object.getElementAt(h).equals("REAL")){
                return true;
        } else if(Object.getElementAt(h).equals("BIT")){
                return true;
        } else if(Object.getElementAt(h).equals("INT")){
                return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DATA TYPE'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DATA TYPE'\n");
            return false;
        }
        return false;
    }

    public boolean NT_CONSTRAINT(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_CONSTRAINT3(Object, Line, TokenType)){
            h++;
            if(NT_CONSTRAINT2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_CONSTRAINT2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_CONSTRAINT3(Object, Line, TokenType)){
            h++;
            if(NT_CONSTRAINT(Object, Line, TokenType)){
                return true;
            }
        } else {h--; return true;}
        return false;
    }

    public boolean NT_CONSTRAINT3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("NULL")){
            return true;
        } else if(Object.getElementAt(h).equals("UNIQUE")){
            return true;
        } else if(Object.getElementAt(h).equals("PRIMARY")){
            h++;
            if(Object.getElementAt(h).equals("KEY")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'KEY'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'KEY'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("FOREIGN")){
            h++;
            if(Object.getElementAt(h).equals("KEY")){
                h++;
                if(Object.getElementAt(h).equals("(")){
                    h++;
                    if(NT_OBJECT_NAME(Object, Line, TokenType)){
                        h++;
                        if(Object.getElementAt(h).equals(")")){
                            h++;
                            if(Object.getElementAt(h).equals("REFERENCES")){
                                h++;
                                if(NT_OBJECT_NAME(Object, Line, TokenType)){
                                    h++;
                                    if(Object.getElementAt(h).equals("(")){
                                        h++;
                                        if(NT_OBJECT_NAME(Object, Line, TokenType)){
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
                                }
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'REFERENCES'\n");
                                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'REFERENCES'\n");
                                return false;
                            }
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
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'KEY'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'KEY'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("DEFAULT")){
            h++;
            if(NT_DATA_TYPE2(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("IDENTIFY")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NT_NUM(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(",")){
                        h++;
                        if(NT_NUM(Object, Line, TokenType)){
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
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                return false;
            }
        } else {h--; return true;}
        return false;
    }

    public boolean NT_CONSTRAINT_TYPE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("UNIQUE")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NT_OBJECT_NAME(Object, Line, TokenType)){
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
        } else if(Object.getElementAt(h).equals("FOREIGN")){
            h++;
            if(Object.getElementAt(h).equals("KEY")){
                h++;
                if(Object.getElementAt(h).equals("(")){
                    h++;
                    if(NT_OBJECT_NAME(Object, Line, TokenType)){
                        h++;
                        if(Object.getElementAt(h).equals(")")){
                            h++;
                            if(Object.getElementAt(h).equals("REFERENCES")){
                                h++;
                                if(NT_OBJECT_NAME(Object, Line, TokenType)){
                                    h++;
                                    if(Object.getElementAt(h).equals("(")){
                                        h++;
                                        if(NT_OBJECT_NAME(Object, Line, TokenType)){
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
                                }
                            } else {
                                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'REFERENCES'\n");
                                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'REFERENCES'\n");
                                return false;
                            }
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
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'KEY'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'KEY'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("PRIMARY")){
            h++;
            if(Object.getElementAt(h).equals("KEY")){
                h++;
                if(Object.getElementAt(h).equals("(")){
                    h++;
                    if(NT_OBJECT_NAME(Object, Line, TokenType)){
                        h++;
                        if(Object.getElementAt(h).equals(")")){
                            return true;
                        }  else {
                            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')'\n");
                            return false;
                        }
                    }
                }  else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'('\n");
                    return false;
                }
            }  else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'KEY'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'KEY'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("CHECK")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NT_EXP_LOGIC(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals(")")){
                        return true;
                    }  else {
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
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'UNIQUE, FOREIGN, PRIMARY OR CHECK'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'UNIQUE, FOREIGN, PRIMARY OR CHECK'\n");
            return false;
        }
        return false;
    }

    public boolean NT_EXP_LOGIC(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_EXP_LOGIC3(Object, Line, TokenType)){
            h++;
            if(NT_EXP_LOGIC2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_EXP_LOGIC2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_EXP_LOGIC3(Object, Line, TokenType)){
            h++;
            if(NT_OP_LOGIC(Object, Line, TokenType)){
                h++;
                if(NT_EXP_LOGIC(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }

    public boolean NT_EXP_LOGIC3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_OBJECT_NAME(Object, Line, TokenType)){
            h++;
            if(NT_OP(Object, Line, TokenType)){
                h++;
                if(NT_DATA_TYPE2(Object, Line, TokenType)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean NT_DATA_TYPE2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("CADENA")){
            return true;
        } else if(TokenType.getElementAt(h).equals("BOOLEAN")){
            return true;
        } else if(TokenType.getElementAt(h).equals("ENTERO")){
            return true;
        } else if(TokenType.getElementAt(h).equals("DECIMAL")){
            return true;
        }  else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DATA TYPE'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DATA TYPE'\n");
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
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'OPERATION'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'OPERATION'\n");
            return false;
        }
    }

    public boolean NT_OP_LOGIC(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("AND")){
            return true;
        } else if(Object.getElementAt(h).equals("OR")){
            return true;
        } else if(Object.getElementAt(h).equals("&&")){
            return true;
        } else if(Object.getElementAt(h).equals("||")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'LOGICAL OPERATION'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'LOGICAL OPERATION'\n");
            return false;
        }
    }

    public boolean NT_NUM(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("ENTERO")){
            return true;
        } else if(TokenType.getElementAt(h).equals("DECIMAL")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NUMBER'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NUMBER'\n");
            return false;
        }
    }

    public boolean NT_CL_DROP(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("CONSTRAINT")){
            h++;
            if(NT_OBJECT_NAME(Object, Line, TokenType)){
                    return true;
            }
        } else if(Object.getElementAt(h).equals("COLUMN")){
            h++;
            if(NT_OBJECT_NAME(Object, Line, TokenType)){
                    return true;
            }
        }  else if(Object.getElementAt(h).equals("INDEX")){
            h++;
            if(NT_OBJECT_NAME(Object, Line, TokenType)){
                    return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'CONSTRAIN, COLUMN OR INDEX'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'CONSTRAIN, COLUMN OR INDEX'\n");
            return false;
        }
        return false;
    }

    public boolean NT_USER(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            h++;
            if(Object.getElementAt(h).equals("WITH")){
                h++;
                if(NT_SET_ITEM(Object, Line, TokenType)){
                    return true;
                }
            }  else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'WITH'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'WITH'\n");
                return false;
            }
        }  else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
            return false;
        }
        return false;
    }

    public boolean NT_SET_ITEM(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_SET_ITEM3(Object, Line, TokenType)){
            h++;
            if(NT_SET_ITEM2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_SET_ITEM2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(",")){
            h++;
            if(NT_SET_ITEM(Object, Line, TokenType)){
                return true;
            }
        } else {h--; return true;}
        return false;
    }

    public boolean NT_SET_ITEM3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("NAME")){
            h++;
            if(Object.getElementAt(h).equals("=")){
                h++;
                if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'='\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'='\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("LOGIN")){
            h++;
            if(Object.getElementAt(h).equals("=")){
                h++;
                if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'='\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'='\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("PASSWORD")){
            h++;
            if(Object.getElementAt(h).equals("=")){
                h++;
                if(TokenType.getElementAt(h).equals("CADENA")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'STRING'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'='\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'='\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("DEFAULT_SCHEMA")){
            h++;
            if(Object.getElementAt(h).equals("=")){
                h++;
                if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'IDENTIFICADOR'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'='\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'='\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("NULL")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NAME, LOGIN, PASSWORD, DEFAULT_SCHEMA OR NULL'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NAME, LOGIN, PASSWORD, DEFAULT_SCHEMA OR NULL'\n");
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
