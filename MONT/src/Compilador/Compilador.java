/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Denisse
 */
public class Compilador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       JADA_GUI ventana = new JADA_GUI();
       ventana.setVisible(true);
      
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String path = s + "\\src\\Compilador\\Lexer2.flex";
        System.out.println(path);

        generarLexer(path);

    }
    public static void generarLexer(String path){
        File file=new File(path);
        //jflex.Main.generate(file);
    }
    
    
    
    
}
