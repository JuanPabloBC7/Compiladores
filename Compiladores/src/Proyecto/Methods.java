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
public class Methods {
    ArrayList<String> Lista = new ArrayList<>();
    
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
        DefaultListModel TODO = new DefaultListModel();
        String TODO2 = "";
        
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
                for (int i = 0; i < TODO.size(); i++) 
                {
                    Lista.add(TODO2);
                    BW.write(TODO.getElementAt(i).toString());
                    BW.newLine();
                }
//                JOptionPane.showMessageDialog(null, " " + TODO2, "FASE_1 COMPILADORES",JOptionPane.OK_OPTION);
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
                    TODO.addElement("\n*** " + T + " LINE " + SQL.Lineas(Read) + ". *** Unrecognized Char: '" + SQL.Texto + "'\n\n");
                    TODO2 += "\n*** " + T + " LINE " + SQL.Lineas(Read) + ". *** Unrecognized Char: '" + SQL.Texto + "'\n\n";
                    
                    break;
                /*case OPERADOR:
                    TODO.addElement(SQL.Texto + "\t Linea " + SQL.Lineas(Read) + "\t Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()) + " es '" + SQL.Texto + "'\n");
                    TODO2 += SQL.Texto + "\t Linea " + SQL.Lineas(Read) + "\t Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length())  + " es '" + SQL.Texto + "'\n";
                    break;*/
                case IDENTIFICADOR:
                    if (SQL.Texto.length() > 31)
                    {
                        TODO.addElement("\n*** ERROR LINE " + SQL.Lineas(Read) + ". *** Char: '" + SQL.Texto.substring(0,31) + "'\n\n");
                        TODO2 += "\n*** ERROR LINE " + SQL.Lineas(Read) + ". *** Char: '" + SQL.Texto.substring(0,31) + "'\n\n";
                    }
                    else
                    {
                        TODO.addElement(SQL.Texto + "\t Linea " + SQL.Lineas(Read) + "\t Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()) + "   \t es " + T + "\n");
                        TODO2 += SQL.Texto + "\t Linea " + SQL.Lineas(Read) + "\t Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length())  + "   \t es " + T + "\n";
                    }
                    break;
                
                default:
                    //if(T == T.IDENTIFICADOR && Tamaño > 31)
                    if(T == T.BOOLEANO || T == T.ENTERO || T == T.DECIMAL || T == T.CADENA)
                    {
                        TODO.addElement(SQL.Texto + "\t Linea " + SQL.Lineas(Read) + "\t Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()) + "   \t es " + T + " (value = " + SQL.Texto + ")\n");
                        TODO2 += SQL.Texto + "\t Linea " + SQL.Lineas(Read) + "\t Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length())  + "   \t es " + T + " (value = " + SQL.Texto + ")\n";
                    }
                    else
                    {
                        TODO.addElement(SQL.Texto + "\t Linea " + SQL.Lineas(Read) + "\t Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length()) + "   \t es " + T + "\n");
                        TODO2 += SQL.Texto + "\t Linea " + SQL.Lineas(Read) + "\t Columnas: " + SQL.Columnas(Read) + "-" + (SQL.Columnas(Read)+SQL.Texto.length())  + "   \t es " + T + "\n";
                    }
            }
        }
    }
}
