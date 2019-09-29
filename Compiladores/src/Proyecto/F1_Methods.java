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
public class F1_Methods {
    ArrayList<String> pl_Lista_Errores = new ArrayList<>();
    DefaultListModel Object = new DefaultListModel();
    DefaultListModel Column = new DefaultListModel();
    DefaultListModel Line = new DefaultListModel();
    DefaultListModel TokenType = new DefaultListModel();
    DefaultListModel Value = new DefaultListModel();
    
    public void F1_AnalizadorLexico(File Path) throws FileNotFoundException, IOException{
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
            JOptionPane.showMessageDialog(null, "Problemas en la clase Methods", "Warning", JOptionPane.WARNING_MESSAGE);
            //Logger.getLogger(F1_Interface.class.getName()).log(Level.SEVERE, null, ex);
            //Logger.getLogger(Interface.class.getName().log(Level.SEVERE, null, ex));
        }
        Reader Read = new BufferedReader(new FileReader("Archivo.txt"));
        F1_MiniSQL SQL = new F1_MiniSQL(Read);
        DefaultListModel ll_Lista_Archivo = new DefaultListModel();
        String ls_Cadena_Errores = "";
        
        String SalidaURL = Archivo.getPath();
        SalidaURL = SalidaURL.split("\\.")[0];
        FileWriter FW = new FileWriter(SalidaURL + "_MiniSQL.out");
//        FileWriter FW2 = new FileWriter(SalidaURL + "_MiniC_ERRORES.out");
        BufferedWriter BW = new BufferedWriter(FW);
//        BufferedWriter BW2 = new BufferedWriter(FW2);
        while(true)
        {
            Token T = SQL.yylex();
            if(T == null)
            {
                for (int i = 0; i < ll_Lista_Archivo.size(); i++) 
                {
                    pl_Lista_Errores.add(ls_Cadena_Errores);
                    BW.write(ll_Lista_Archivo.getElementAt(i).toString());
                    BW.newLine();
                }
//                JOptionPane.showMessageDialog(null, " " + ls_Cadena_Errores, "FASE_1 COMPILADORES",JOptionPane.OK_OPTION);
//                for (int i = 0; i < TOKENS.size() ; i++) 
//                {
//                    jTextArea1.setText(TOKENS2);
//                    BW.write(TOKENS.getElementAt(i).toString());
//                    BW.newLine();
//                }
//                for (int i = 0; i < ERRORES.size(); i++) 
//                {
//                    jTextArea1.setText(ERRORES2);
//                    BW2.write(ERRORES.getElementAt(i).toString());
//                    BW2.newLine();
//                }
                BW.close();
//                BW2.close();
                return;
            }            
            switch(T)
            {
                case ERROR:                                      //----------------------------------------
                    ll_Lista_Archivo.addElement("*** " + T + " LINE " + SQL.Lineas(Read) + ". *** Unrecognized Character: \t" + SQL.Texto + "\n");
                    ls_Cadena_Errores += "*** " + T + " LINE " + SQL.Lineas(Read) + ". *** Unrecognized Character: \t" + SQL.Texto + "\n";
                    
                    break;
                case ERROR_COMENTARIO:
                    ll_Lista_Archivo.addElement("*** ERROR LINE " + SQL.Lineas(Read) + ". *** Comment Error: \t" + SQL.Texto + "\n");
                    ls_Cadena_Errores += "*** ERROR LINE " + SQL.Lineas(Read) + ". *** Comment Error: \ts" + SQL.Texto + "\n";
                    break;
                /*case OPERADOR:
                    ll_Lista_Archivo.addElement(SQL.Texto + "\t Linea " + SQL.Lineas(Read) + "\t Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()) + " es '" + SQL.Texto + "'\n");
                    ls_Cadena_Errores += SQL.Texto + "\t Linea " + SQL.Lineas(Read) + "\t Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length())  + " es '" + SQL.Texto + "'\n";
                    break;*/
                case IDENTIFICADOR:
                    if (SQL.Texto.length() > 31)
                    {
                        ll_Lista_Archivo.addElement("\n*** ERROR LINE " + SQL.Lineas(Read) + ". *** Truncated idntifier: \t" + SQL.Texto.substring(0,31) + "\n");
                        ls_Cadena_Errores += "*** ERROR LINE " + SQL.Lineas(Read) + ". *** Truncated idntifier: \t" + SQL.Texto.substring(0,31) + "\n";
                    }
                    else
                    {
                        Object.addElement(" " + SQL.Texto);
                        Column.addElement("" + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()));
                        Line.addElement("" + SQL.Lineas(Read));
                        TokenType.addElement("" + T);
                        Value.addElement("");
                        ll_Lista_Archivo.addElement("Linea " + SQL.Lineas(Read) + " Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()) + "     \t " + SQL.Texto + "          \t es " + T);
                        //TODO2 += "Linea " + SQL.Lineas(Read) + " Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()) + "     \t " + SQL.Texto + "          \t es " + T + "\n";
                    }
                    break;
                
                default:
                    //if(T == T.IDENTIFICADOR && Tamaño > 31)
                    if(T == T.BOOLEANO || T == T.ENTERO || T == T.DECIMAL || T == T.CADENA)
                    {
                        Object.addElement(" " + SQL.Texto);
                        Column.addElement("" + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()));
                        Line.addElement("" + SQL.Lineas(Read));
                        TokenType.addElement("" + T);
                        Value.addElement("" + SQL.Texto);
                        ll_Lista_Archivo.addElement("Linea " + SQL.Lineas(Read) + " Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()) + "     \t " + SQL.Texto + "          \t es " + T + " (value = " + SQL.Texto);
                        //TODO2 += "Linea " + SQL.Lineas(Read) + " Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length())  + "     \t " + SQL.Texto + "          \t es " + T + " (value = " + SQL.Texto + ")\n";
                    }
                    else
                    {
                        Object.addElement(" " + SQL.Texto);
                        Column.addElement("" + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()));
                        Line.addElement("" + SQL.Lineas(Read));
                        TokenType.addElement("" + T);
                        Value.addElement("");
                        ll_Lista_Archivo.addElement("Linea " + SQL.Lineas(Read) + " Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()) + "     \t " + SQL.Texto + "          \t es " + T);
                        //TODO2 += "Linea " + SQL.Lineas(Read) + " Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()) + "     \t " + SQL.Texto + "          \t es " + T + "\n";
                    }
            }
        }
    }
}
