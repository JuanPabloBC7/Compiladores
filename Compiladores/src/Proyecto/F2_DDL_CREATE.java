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
public class F2_DDL_CREATE {
    DefaultListModel ll_Sentencia_Hija_Archivo = new DefaultListModel();
    String ls_Sentencia_Hija_Errores = "";
    
    public int NT_CREATE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
	if(Object.getElementAt(i).equals("CREATE")){
            i++;
            if(NT_OP_CREATE(Object, Line, TokenType, i)){
                i++;
                if(NT_FIN(Object, Line, TokenType, i)){
                    ll_Sentencia_Hija_Archivo.addElement("CREATE Leido correctamente.");
                    return i;
                }
            }
        }
        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** CREATE no leido correctamente\n");
        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** CREATE no leido correctamente\n");
        return i;
    }

    public boolean NT_OP_CREATE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("DATABASE")){
            i++;
            if(NT_ID(Object, Line, TokenType, i)){
                return true;
            }
        } else if(NT_OP_INDEX(Object, Line, TokenType, i)){
            i++;
            if(Object.getElementAt(i).equals("INDEX")){
                i++;
                if(NT_INDEX_CREATE(Object, Line, TokenType, i)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'INDEX'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'INDEX'\n");
                return false;
            }
        } else if(Object.getElementAt(i).equals("TABLE")){
            i++;
            if(NT_TBL_CREATE(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals("USER")){
            i++;
            if(NT_USER_CREATE(Object, Line, TokenType, i)){
                return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DATABASE, TALBE OR USER'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DATABASE, TALBE OR USER'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_ID(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            i++;
            if(NT_ID2(Object, Line, TokenType, i)){
                return true;   
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_ID2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(".")){
            i++;
            if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                i++;
                if(NT_ID3(Object, Line, TokenType, i)){
                    return true;
                }
            }
            else if(Object.getElementAt(i).equals(".")){
                i++;
                if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                    return false;
                }
            }else{ return true; }
        }
        return false;
    }
    
    public boolean NT_ID3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals(".")){
            i++;
            if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                return false;
            }
        }else{ return true; }
    }

    public boolean NT_OP_INDEX(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("UNIQUE")){
            return true;
        } else if(Object.getElementAt(i).equals("CLUSTERED")){
            return true;
        } else if(Object.getElementAt(i).equals("NONCLUSTERED")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'UNIQUE, CLUSETERED OR NONCLUSTERED'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'UNIQUE, CLUSETERED OR NONCLUSTERED'\n");
            return false;
        }
    }
    
    public boolean NT_INDEX_CREATE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_ID(Object, Line, TokenType, i)){
            i++;
            if(Object.getElementAt(i).equals("ON")){
                i++;
                if(NT_ID(Object, Line, TokenType, i)){
                    i++;
                    if(Object.getElementAt(i).equals("(")){
                        i++;
                        if(NT_CL_INDEX(Object, Line, TokenType, i)){
                            i++;
                            if(Object.getElementAt(i).equals(")")){
                                i++;
                                if(NT_MAS_INDEX(Object, Line, TokenType, i)){
                                    return true;
                                }
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
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ON'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ON'\n");
                return false;
            }
        }    
        return false;
    }

    public boolean NT_CL_INDEX(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_ID(Object, Line, TokenType, i)){
            i++;
            if(NT_CL_INDEX2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_CL_INDEX2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_ID(Object, Line, TokenType, i)){
            i++;
            if(Object.getElementAt(i).equals(",")){
                i++;
                if(NT_CL_INDEX(Object, Line, TokenType, i)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t','\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t','\n");
                return false;
            }
        }else {return true;}
        return false;
    }

    public boolean NT_MAS_INDEX(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(Object.getElementAt(i).equals("INCLUDE")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NT_CL_INDEX(Object, Line, TokenType, i)){
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
        } else {return true;}
        return false;
    }

    public boolean NT_TBL_CREATE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
         if(NT_ID(Object, Line, TokenType, i)){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NT_ADD_COLUMN(Object, Line, TokenType, i)){
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
        return false;
    }

    public boolean NT_ADD_COLUMN(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_ADD_COLUMN3(Object, Line, TokenType, i)){
            i++;
            if(NT_ADD_COLUMN2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_ADD_COLUMN2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_ADD_COLUMN3(Object, Line, TokenType, i)){
            i++;
            if(Object.getElementAt(i).equals(",")){
                i++;
                if(NT_ADD_COLUMN2(Object, Line, TokenType, i)){
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

    public boolean NT_ADD_COLUMN3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_ID(Object, Line, TokenType, i)){
            i++;
            if(NT_TYPE_DATA(Object, Line, TokenType, i)){
                i++;
                if(NT_CONSTRAINT(Object, Line, TokenType, i)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean NT_TYPE_DATA(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
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
                    if(NT_ID(Object, Line, TokenType, i)){
                        i++;
                        if(Object.getElementAt(i).equals(")")){
                            i++;
                            if(Object.getElementAt(i).equals("REFERENCES")){
                                i++;
                                if(NT_ID(Object, Line, TokenType, i)){
                                    i++;
                                    if(Object.getElementAt(i).equals("(")){
                                        i++;
                                        if(NT_ID(Object, Line, TokenType, i)){
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
            if(NT_DATA_TYPE(Object, Line, TokenType, i)){
                return true;
            }
        } else if(Object.getElementAt(i).equals("CHECK")){
            i++;
            if(Object.getElementAt(i).equals("(")){
                i++;
                if(NT_LOGIC_EXP(Object, Line, TokenType, i)){
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
        } else if(Object.getElementAt(i).equals("NOT")){
            i++;
            if(Object.getElementAt(i).equals("NULL")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NULL'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'NULL'\n");
                return false;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'CONSTRAINT'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'CONSTRAINT'\n");
            return false;
        }
        return false;
    }

    public boolean NT_LOGIC_EXP(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_LOGIC_EXP3(Object, Line, TokenType, i)){
            i++;
            if(NT_LOGIC_EXP2(Object, Line, TokenType, i)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_LOGIC_EXP2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_LOGIC_EXP3(Object, Line, TokenType, i)){
            i++;
            if(NT_OP_LOGIC(Object, Line, TokenType, i)){
                i++;
                if(NT_LOGIC_EXP(Object, Line, TokenType, i)){
                    return true;
                }
            }
        } else {return true;}
        return false;
    }

    public boolean NT_LOGIC_EXP3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(NT_ID(Object, Line, TokenType, i)){
            i++;
            if(NT_OPERADOR(Object, Line, TokenType, i)){
                i++;
                if(NT_DATA_TYPE(Object, Line, TokenType, i)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean NT_DATA_TYPE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("CADENA")){
            return true;
        } else if(TokenType.getElementAt(i).equals("BOOLEAN")){
            return true;
        } else if(TokenType.getElementAt(i).equals("ENTERO")){
            return true;
        } else if(TokenType.getElementAt(i).equals("DECIMAL")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DATA TYPE'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'DATA TYPE'\n");
            return false;
        }
    }

    public boolean NT_OPERADOR(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
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
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t')OPERADOR\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'OPERADOR'\n");
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
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'LOGICAL OPERADOR'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'LOGICAL OPERADOR'\n");
            return false;
        }
    }

    public boolean NT_USER_CREATE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
        if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
            i++;
            if(Object.getElementAt(i).equals("FOR")){
                i++;
                if(Object.getElementAt(i).equals("LOGIN")){
                    i++;
                    if(TokenType.getElementAt(i).equals("IDENTIFICADOR")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                        ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'LOGIN'\n");
                    ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'LOGIN'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'FOR'\n");
                ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'FOR'\n");
                return false;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores = ("*** ERROR LINE " + Line.getElementAt(i) + ". *** Expected a \t'ID'\n");
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
