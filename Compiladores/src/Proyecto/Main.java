/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jpbalan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GetUIManager();
        
        //Definir el Path de salida de mi archivo flex que sera el encargado de almacenar todos los tokens y de  mas
        String Path = "D:/Users/jpbalan/Documents/NetBeansProjects/Compiladores/Compiladores/src/Proyecto/F1_MiniSQL.flex";
        MiniSQL(Path);
        Interface IF = new Interface();
        IF.show();
    }
    
    public static void MiniSQL(String Path){
        File F = new File(Path);
        jflex.Main.generate(F);       
    }
    
    //No tomar en cuenta, codigo utilizado para parte grafica en el frame.
    public static void GetUIManager() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
