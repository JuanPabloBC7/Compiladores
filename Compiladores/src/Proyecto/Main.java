/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        GetUIManager();
        
        //Definir el Path de salida de mi archivo flex que sera el encargado de almacenar todos los tokens y de  mas
        String Path = "D:/Users/jpbalan/Documents/NetBeansProjects/Compiladores/Compiladores/src/Proyecto/F1_MiniSQL.flex";
        MiniSQL(Path);
        
        String Path2 = "D:/Users/jpbalan/Documents/NetBeansProjects/Compiladores/Compiladores/src/Proyecto/F3_MiniSQL_F.flex";
        String[] Path3 = {"-parser", "F3_MiniSQL_C", "-expect", "100000", "D:/Users/jpbalan/Documents/NetBeansProjects/Compiladores/Compiladores/src/Proyecto/F3_MiniSQL_C.cup"};
        GenerarLexer(Path2, Path3);
        
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
    
    public static void GenerarLexer(String Path2, String[] Path3) throws IOException, Exception{
        File archivo;
        archivo = new File(Path2);
        jflex.Main.generate(archivo);
        java_cup.Main.main(Path3);

        Path rutaSym = Paths.get("D:/Users/jpbalan/Documents/NetBeansProjects/Compiladores/Compiladores/src/Proyecto/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
        Paths.get("D:/Users/jpbalan/Documents/NetBeansProjects/Compiladores/Compiladores/sym.java"),
        Paths.get("D:/Users/jpbalan/Documents/NetBeansProjects/Compiladores/Compiladores/src/Proyecto/sym.java"));

        Path rutaSym2 = Paths.get("D:/Users/jpbalan/Documents/NetBeansProjects/Compiladores/Compiladores/src/Proyecto/F3_MiniSQL_C.java");
        if (Files.exists(rutaSym2)) {
            Files.delete(rutaSym2);
        }
        Files.move(
            Paths.get("D:/Users/jpbalan/Documents/NetBeansProjects/Compiladores/Compiladores/F3_MiniSQL_C.java"),
            Paths.get("D:/Users/jpbalan/Documents/NetBeansProjects/Compiladores/Compiladores/src/Proyecto/F3_MiniSQL_C.java")
        );
    }
}
