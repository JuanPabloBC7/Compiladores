/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author jpbalan
 */
public class F2_Methods {
    ArrayList<String> pl_Errores = new ArrayList<>();
    DefaultListModel Object = new DefaultListModel();
    DefaultListModel Column = new DefaultListModel();
    DefaultListModel Line = new DefaultListModel();
    DefaultListModel TokenType = new DefaultListModel();
    DefaultListModel Value = new DefaultListModel();
    
    DefaultListModel pl_Sentencia_Archivo = new DefaultListModel();
    String ps_Sentencia_Errores;
    
    public void F2_AnalizadorSintactico(File Path) throws FileNotFoundException, IOException{
        File Archivo = new File("Archivo.txt");
        PrintWriter PW;
        try
        {
            PW = new PrintWriter(Archivo);
            
            if(!Path.toString().equals(""))
            {
                boolean archivoExiste = false;
                String Salida;
                Archivo = new File(Path.toString());
                if(Archivo.exists())
                {
                    archivoExiste= true;
                    FileReader FR = new FileReader(Archivo);
                    BufferedReader BR = new BufferedReader(FR);
                    while((Salida = BR.readLine()) != null)
                    {
                        PW.print(Salida + "\r\n");
                    }
                }
                else
                {
                    PW.print(Path.toString());
                }
                PW.close();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "La ruta del archivo esta vacia.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Problemas en la clase F2_Methods", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        Reader Read = new BufferedReader(new FileReader("Archivo.txt"));
        
        DefaultListModel ll_Lista_Archivo = new DefaultListModel();
        String ls_Cadena_Errores = "";
        
        F1_MiniSQL SQL = new F1_MiniSQL(Read);
        String SalidaURL = Archivo.getPath();
        SalidaURL = SalidaURL.split("\\.")[0];
        FileWriter FW = new FileWriter(SalidaURL + "_MiniSQL.out");
        BufferedWriter BW = new BufferedWriter(FW);
        while(true)
        {    
            Token T = SQL.yylex();
            if(T == null)
            {
                SENTENCES();
                for (int i = 0; i < ll_Lista_Archivo.size(); i++) 
                {
                    pl_Errores.add(ls_Cadena_Errores);
                    BW.write(ll_Lista_Archivo.getElementAt(i).toString());
                    BW.newLine();
                }
                BW.close();
                return;
            }
            switch(T)
            {
                case ERROR:                                      //----------------------------------------
                    ll_Lista_Archivo.addElement("*** " + T + " LINE " + SQL.Lineas(Read) + ". *** Unrecognized Character: \t" + SQL.Texto + "\n\n");
                    ls_Cadena_Errores += "*** " + T + " LINE " + SQL.Lineas(Read) + ". *** Unrecognized Character: \t" + SQL.Texto + "\n";
                    break;
                case ERROR_COMENTARIO:
                    ll_Lista_Archivo.addElement("*** ERROR LINE " + SQL.Lineas(Read) + ". *** Comment Error: \t" + SQL.Texto + "\n\n");
                    ls_Cadena_Errores += "*** ERROR LINE " + SQL.Lineas(Read) + ". *** Comment Error: \ts" + SQL.Texto + "\n";
                    break;
                case IDENTIFICADOR:
                    if (SQL.Texto.length() > 31)
                    {
                        ll_Lista_Archivo.addElement("\n*** ERROR LINE " + SQL.Lineas(Read) + ". *** Truncated idntifier: \t" + SQL.Texto.substring(0,31) + "\n\n");
                        ls_Cadena_Errores += "*** ERROR LINE " + SQL.Lineas(Read) + ". *** Truncated idntifier: \t" + SQL.Texto.substring(0,31) + "\n";
                    }
                    else
                    {
                        Object.addElement("" + SQL.Texto);
                        Column.addElement("" + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()));
                        Line.addElement("" + SQL.Lineas(Read));
                        TokenType.addElement("" + T);
                        Value.addElement("");
                        ll_Lista_Archivo.addElement("Linea " + SQL.Lineas(Read) + " Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()) + "     \t " + SQL.Texto + "          \t es " + T + "\n");
                    }
                    break;
                
                default:
                    if(T == T.BOOLEANO || T == T.ENTERO || T == T.DECIMAL || T == T.CADENA)
                    {
                        Object.addElement("" + SQL.Texto);
                        Column.addElement("" + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()));
                        Line.addElement("" + SQL.Lineas(Read));
                        TokenType.addElement("" + T);
                        Value.addElement("" + SQL.Texto);
                        ll_Lista_Archivo.addElement("Linea " + SQL.Lineas(Read) + " Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()) + "     \t " + SQL.Texto + "          \t es " + T + " (value = " + SQL.Texto + ")\n");
                    }
                    else
                    { 
                        Object.addElement("" + SQL.Texto);
                        Column.addElement("" + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()));
                        Line.addElement("" + SQL.Lineas(Read));
                        TokenType.addElement("" + T);
                        Value.addElement("");
                        ll_Lista_Archivo.addElement("Linea " + SQL.Lineas(Read) + " Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()) + "     \t " + SQL.Texto + "          \t es " + T + "\n");
                    }
            }
        }
    }
    
    public void SENTENCES() throws IOException{
        for (int i = 0; i < TokenType.size(); i++) {
            switch(TokenType.getElementAt(i).toString())
            {
                case "PALABRA_RESERVADA":
                    switch (Object.getElementAt(i).toString().trim()) {
                        case " SELECT":
                            F2_DML_SELECT SEL = new F2_DML_SELECT();
                            i = SEL.S1(Object, Line, TokenType, i) - 1;
                            pl_Sentencia_Archivo = SEL.ll_Sentencia_Hija_Archivo;
                            ps_Sentencia_Errores = SEL.ls_Sentencia_Hija_Errores;
                            break;
                        case "INSERT":
                            F2_DML_INSERT INS = new F2_DML_INSERT();
                            i = INS.NT_INSERT(Object, Line, TokenType, i) - 1;
                            pl_Sentencia_Archivo = INS.ll_Sentencia_Hija_Archivo;
                            ps_Sentencia_Errores = INS.ls_Sentencia_Hija_Errores;
                            break;
                        case "UPDATE":
                            F2_DML_UPDATE UPD = new F2_DML_UPDATE();
                            i = UPD.NT_UPDATE(Object, Line, TokenType, i) - 1;
                            pl_Sentencia_Archivo = UPD.ll_Sentencia_Hija_Archivo;
                            ps_Sentencia_Errores = UPD.ls_Sentencia_Hija_Errores;
                            break;
                        case "DELETE":
                            F2_DML_DELETE DEL = new F2_DML_DELETE();
                            i = DEL.S1(Object, Line, TokenType, i) - 1;
                            pl_Sentencia_Archivo = DEL.ll_Sentencia_Hija_Archivo;
                            ps_Sentencia_Errores = DEL.ls_Sentencia_Hija_Errores;
                            break;
                        case "CREATE":
                            F2_DDL_CREATE CRE = new F2_DDL_CREATE();
                            i = CRE.NT_CREATE(Object, Line, TokenType, i) - 1;
                            pl_Sentencia_Archivo = CRE.ll_Sentencia_Hija_Archivo;
                            ps_Sentencia_Errores = CRE.ls_Sentencia_Hija_Errores;
                            break;
                        case "ALTER":
                            F2_DDL_ALTER ALT = new F2_DDL_ALTER();
                            i = ALT.NT_ALTER(Object, Line, TokenType, i) - 1;
                            pl_Sentencia_Archivo = ALT.ll_Sentencia_Hija_Archivo;
                            ps_Sentencia_Errores = ALT.ls_Sentencia_Hija_Errores;
                            break;
                        case "DROP":
                            F2_DDL_DROP DRO = new F2_DDL_DROP();
                            i = DRO.NT_DROP(Object, Line, TokenType, i) - 1;
                            pl_Sentencia_Archivo = DRO.ll_Sentencia_Hija_Archivo;
                            ps_Sentencia_Errores = DRO.ls_Sentencia_Hija_Errores;
                            break;
                        case "TRUNCATE":
                            F2_DDL_TRUNCATE TRU = new F2_DDL_TRUNCATE();
                            i = TRU.NT_TRUNCATE(Object, Line, TokenType, i) - 1;
                            pl_Sentencia_Archivo = TRU.ll_Sentencia_Hija_Archivo;
                            ps_Sentencia_Errores = TRU.ls_Sentencia_Hija_Errores;
                            break;
                        default:
                            pl_Sentencia_Archivo.addElement("\n*** ERROR LINE " + i + ". *** Expected a \t'DDL OR DML SENTENCE'\n");
                            ps_Sentencia_Errores = ("*** ERROR LINE " + i + ". *** Expected a \t'DDL OR DML SENTENCE'");
                            break;
                    }
                    break;
                    
                default:
                    pl_Sentencia_Archivo.addElement("\n*** ERROR LINE " + i + ". *** Expected a \t'RESERVED WORD'\n");
                    ps_Sentencia_Errores = ("*** ERROR LINE " + i + ". *** Expected a \t'RESERVED WORD'");
                    break;
            }
        }
    }
}
