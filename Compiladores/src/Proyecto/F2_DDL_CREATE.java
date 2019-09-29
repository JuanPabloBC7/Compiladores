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
    int h;
    
    public int NT_CREATE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType, int i) throws IOException{
	h = i;
        if(Object.getElementAt(h).equals("CREATE")){
            h++;
            if(NT_OP_CREATE(Object, Line, TokenType)){
                h++;
                if(NT_FIN(Object, Line, TokenType)){
                    h++;
                    ls_Sentencia_Hija_Errores += ("CREATE Leido correctamente.\n");
                    return h;
                }
            }
        }
        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** CREATE no leido correctamente\n");
        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** CREATE no leido correctamente\n");
        return h;
    }

    public boolean NT_OP_CREATE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("DATABASE")){
            h++;
            if(NT_ID(Object, Line, TokenType)){
                return true;
            }
        } else if(NT_OP_INDEX(Object, Line, TokenType)){
            h++;
            if(Object.getElementAt(h).equals("INDEX")){
                h++;
                if(NT_INDEX_CREATE(Object, Line, TokenType)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'INDEX'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'INDEX'\n");
                return false;
            }
        } else if(Object.getElementAt(h).equals("TABLE")){
            h++;
            if(NT_TBL_CREATE(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("USER")){
            h++;
            if(NT_USER_CREATE(Object, Line, TokenType)){
                return true;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DATABASE, TALBE OR USER'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DATABASE, TALBE OR USER'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_ID(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            h++;
            if(NT_ID2(Object, Line, TokenType)){
                return true;   
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            return false;
        }
        return false;
    }
    
    public boolean NT_ID2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(".")){
            h++;
            if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                h++;
                if(NT_ID3(Object, Line, TokenType)){
                    return true;
                }
            }
            else if(Object.getElementAt(h).equals(".")){
                h++;
                if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                    return true;
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                    return false;
                }
            }
        }else{h--; return true;}
        return false;
    }
    
    public boolean NT_ID3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals(".")){
            h++;
            if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                return false;
            }
        }else{h--; return true;}
    }

    public boolean NT_OP_INDEX(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("UNIQUE")){
            return true;
        } else if(Object.getElementAt(h).equals("CLUSTERED")){
            return true;
        } else if(Object.getElementAt(h).equals("NONCLUSTERED")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'UNIQUE, CLUSETERED OR NONCLUSTERED'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'UNIQUE, CLUSETERED OR NONCLUSTERED'\n");
            return false;
        }
    }
    
    public boolean NT_INDEX_CREATE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_ID(Object, Line, TokenType)){
            h++;
            if(Object.getElementAt(h).equals("ON")){
                h++;
                if(NT_ID(Object, Line, TokenType)){
                    h++;
                    if(Object.getElementAt(h).equals("(")){
                        h++;
                        if(NT_CL_INDEX(Object, Line, TokenType)){
                            h++;
                            if(Object.getElementAt(h).equals(")")){
                                h++;
                                if(NT_MAS_INDEX(Object, Line, TokenType)){
                                    return true;
                                }
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
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ON'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ON'\n");
                return false;
            }
        }    
        return false;
    }

    public boolean NT_CL_INDEX(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_ID(Object, Line, TokenType)){
            h++;
            if(NT_CL_INDEX2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_CL_INDEX2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_ID(Object, Line, TokenType)){
            h++;
            if(Object.getElementAt(h).equals(",")){
                h++;
                if(NT_CL_INDEX(Object, Line, TokenType)){
                    return true;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t','\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t','\n");
                return false;
            }
        }else {h--; return true;}
        return false;
    }

    public boolean NT_MAS_INDEX(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(Object.getElementAt(h).equals("INCLUDE")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NT_CL_INDEX(Object, Line, TokenType)){
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
        } else {h--; return true;}
        return false;
    }

    public boolean NT_TBL_CREATE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
         if(NT_ID(Object, Line, TokenType)){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NT_ADD_COLUMN(Object, Line, TokenType)){
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
        return false;
    }

    public boolean NT_ADD_COLUMN(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_ADD_COLUMN3(Object, Line, TokenType)){
            h++;
            if(NT_ADD_COLUMN2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_ADD_COLUMN2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_ADD_COLUMN3(Object, Line, TokenType)){
            h++;
            if(Object.getElementAt(h).equals(",")){
                h++;
                if(NT_ADD_COLUMN2(Object, Line, TokenType)){
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

    public boolean NT_ADD_COLUMN3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_ID(Object, Line, TokenType)){
            h++;
            if(NT_TYPE_DATA(Object, Line, TokenType)){
                h++;
                if(NT_CONSTRAINT(Object, Line, TokenType)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean NT_TYPE_DATA(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
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
                    if(NT_ID(Object, Line, TokenType)){
                        h++;
                        if(Object.getElementAt(h).equals(")")){
                            h++;
                            if(Object.getElementAt(h).equals("REFERENCES")){
                                h++;
                                if(NT_ID(Object, Line, TokenType)){
                                    h++;
                                    if(Object.getElementAt(h).equals("(")){
                                        h++;
                                        if(NT_ID(Object, Line, TokenType)){
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
            if(NT_DATA_TYPE(Object, Line, TokenType)){
                return true;
            }
        } else if(Object.getElementAt(h).equals("CHECK")){
            h++;
            if(Object.getElementAt(h).equals("(")){
                h++;
                if(NT_LOGIC_EXP(Object, Line, TokenType)){
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
        } else if(Object.getElementAt(h).equals("NOT")){
            h++;
            if(Object.getElementAt(h).equals("NULL")){
                return true;
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NULL'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'NULL'\n");
                return false;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'CONSTRAINT'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'CONSTRAINT'\n");
            return false;
        }
        return false;
    }

    public boolean NT_LOGIC_EXP(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_LOGIC_EXP3(Object, Line, TokenType)){
            h++;
            if(NT_LOGIC_EXP2(Object, Line, TokenType)){
                return true;
            }
        }
        return false;
    }

    public boolean NT_LOGIC_EXP2(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_LOGIC_EXP3(Object, Line, TokenType)){
            h++;
            if(NT_OP_LOGIC(Object, Line, TokenType)){
                h++;
                if(NT_LOGIC_EXP(Object, Line, TokenType)){
                    return true;
                }
            }
        } else {h--; return true;}
        return false;
    }

    public boolean NT_LOGIC_EXP3(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(NT_ID(Object, Line, TokenType)){
            h++;
            if(NT_OPERADOR(Object, Line, TokenType)){
                h++;
                if(NT_DATA_TYPE(Object, Line, TokenType)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean NT_DATA_TYPE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("CADENA")){
            return true;
        } else if(TokenType.getElementAt(h).equals("BOOLEAN")){
            return true;
        } else if(TokenType.getElementAt(h).equals("ENTERO")){
            return true;
        } else if(TokenType.getElementAt(h).equals("DECIMAL")){
            return true;
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DATA TYPE'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'DATA TYPE'\n");
            return false;
        }
    }

    public boolean NT_OPERADOR(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
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
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t')OPERADOR\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'OPERADOR'\n");
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
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'LOGICAL OPERADOR'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'LOGICAL OPERADOR'\n");
            return false;
        }
    }

    public boolean NT_USER_CREATE(DefaultListModel Object, DefaultListModel Line, DefaultListModel TokenType) throws IOException{
        if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
            h++;
            if(Object.getElementAt(h).equals("FOR")){
                h++;
                if(Object.getElementAt(h).equals("LOGIN")){
                    h++;
                    if(TokenType.getElementAt(h).equals("IDENTIFICADOR")){
                        return true;
                    } else {
                        ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                        ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
                        return false;
                    }
                } else {
                    ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'LOGIN'\n");
                    ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'LOGIN'\n");
                    return false;
                }
            } else {
                ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'FOR'\n");
                ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'FOR'\n");
                return false;
            }
        } else {
            ll_Sentencia_Hija_Archivo.addElement("\n*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
            ls_Sentencia_Hija_Errores += ("*** ERROR LINE " + Line.getElementAt(h) + ". *** Expected a \t'ID'\n");
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
