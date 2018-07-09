/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compilador;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author donadonydonaji
 */
public class Analisis {
      
 Vector<String> v=new Vector<String>();
 Vector<String> e=new Vector<String>(); 
  int tam(){
        return v.size();
    }
    
    public void vaciar(){
        v.clear();
    }
  int tamerror(){
        return e.size();
    }
    
    public void vaciarerror(){
        e.clear();
    }

int contador=0;
    File abre;
    JFileChooser fc;
    public String aux;
    public String texto;
    boolean existe;
    String direccion;

    
    public void abrir(){
    
    aux = "";
		texto = "";
		try {
			// llamamos el metodo que permite cargar la ventana
			fc = new JFileChooser();
			FileNameExtensionFilter filtro = new FileNameExtensionFilter(
					".prac2lfp", "prac2lfp");
			fc.setFileFilter(filtro);
			fc.showOpenDialog(fc);
			abre = fc.getSelectedFile();
			fc.addChoosableFileFilter(filtro);

			// recorremos el archivo, lo leemos para plasmarlo en el area de
			// texto
                        int status = 0;
                        if(abre.getName().endsWith("prac2lfp")){
			if (fc != null) {
				FileReader archivos = new FileReader(abre);

				BufferedReader lee = new BufferedReader(archivos);
				while ((aux = lee.readLine()) != null) {
					texto += aux + "\n";
                                        existe = true;
                                        direccion = abre.getAbsolutePath();
				}
				lee.close();
				archivos.close();
			}
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "ERROR", "Solo se acepta formato prac1lfp", 0);
                        }
                        
                         if(status== JFileChooser.CANCEL_OPTION){
                            JOptionPane.showMessageDialog(null, "No eligio ningun archivo", "Error", 0);
                        }
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex + ""
					+ "\nNo se ha encontrado el archivo", "ADVERTENCIA!!!",
					JOptionPane.WARNING_MESSAGE);
		}
                
                
    
    }
    
    public String devolver(){
        return texto;
    }
    
    public void guardar(JTextArea textArea) throws InterruptedException {
		try {
			FileWriter fw = new FileWriter(abre);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.write(textArea.getText());
			pw.append("");
			pw.close();
			bw.close();
			fw.close();
			JOptionPane.showMessageDialog(fc,
					"El archivo se a guardado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);


		} catch (IOException e) {
			JOptionPane.showMessageDialog(fc,
					"Se encontro un ERROR, no se guardo el archivo",
					"Información", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	} 
    
    public void mostrar(){
      try { 
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "C:\\Users\\Denisse BR\\Documents\\NetBeansProjects\\AnalizadorLexico\\ManualDeUsuario.pdf"); 
        } 
        catch (Exception e){ 
        JOptionPane.showMessageDialog(null, "Error al Abrir el Archivo", "ERROR", JOptionPane.ERROR_MESSAGE); 
        }    
    }
    
    public void generar(){
        try { 
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "C:\\Users\\Denisse BR\\Documents\\NetBeansProjects\\AnalizadorLexico\\ManualTecnico.pdf"); 
        } 
        catch (Exception e){ 
        JOptionPane.showMessageDialog(null, "Error al Abrir el Archivo", "ERROR", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    public void mostrarDatos(){
        try { 
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "C:\\Users\\Denisse BR\\Documents\\NetBeansProjects\\AnalizadorLexico\\acercaDe.pdf"); 
        } 
        catch (Exception e){ 
        JOptionPane.showMessageDialog(null, "Error al Abrir el Archivo", "ERROR", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    public void buscarPalabra(JTextField buscar, JTextArea palabra){
    String texto=buscar.getText();
    
    String palabraTXT = palabra.getText();
    char [] palabratxt=palabraTXT.toCharArray();
    DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
    DefaultHighlighter.DefaultHighlightPainter highlightPainte = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW); 
    Highlighter h = palabra.getHighlighter(); 
    h.removeAllHighlights(); 
    String text = palabra.getText();     
    Pattern p = Pattern.compile(texto); 
    Matcher m= p.matcher(text);
    //int aux =m.start();
    int i;
    while(m.find()){
        for(i=0;i<palabraTXT.length()-1;i++){  
        if(!Character.isLowerCase(palabratxt[m.end(i)])|| !Character.isUpperCase(palabratxt[m.end(i)])&& palabratxt[m.end(i)]==' ' ){
            try {    
                h.addHighlight(m.start(), m.end(), highlightPainter);
                contador++;
            } catch (BadLocationException ex) {
                Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println("prueba"+m.end());
            break;
        }
        else{
            try {    
                h.addHighlight(m.start(), m.end(), highlightPainte);
                contador++;
            } catch (BadLocationException ex) {
                Logger.getLogger(Analisis.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
        }
        }
    }
    }
    
    public void probarLexerFile(JTextPane area) throws IOException{
        int contIDs=0;
        
        File fichero = new File ("fichero.txt");
        PrintWriter writer;
        try {
            writer = new PrintWriter(fichero);
            writer.print(area.getText());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JADA_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader reader = new BufferedReader(new FileReader("fichero.txt"));
        Lexer lexer = new Lexer (reader);
        String resultado="";
        
        int numLinea=1;
        while(true){
            
            Token token = lexer.yylex();
            if(token==null){
                v.add("EOF");
                return;
            }
            switch(token){
               
              

                case COMPARACION: case FUNCION_RESERVADA: case PUNTO: case COMA: case IGUAL: case IDENTIFICADOR: case ENTERO: case CADENA: case PALABRA_RESERVADA: case SIGNO_DIVISION: case SIGNO_MULTIPLICACION: case SIGNO_MAS: case SIGNO_MENOS: case PARENTESIS_ABIERTO: case PARENTESIS_CERRADO: case LLAVE_ABIERTA: case LLAVE_CERRADA: case PUNTO_Y_COMA: case CORCHETE_ABIERTO: case CORCHETE_CERRADO: case NOTE_VALUE: case NOTE_STRING: case PITCH_NOTATION:
                    v.add(token+" "+lexer.lexeme+" "+(lexer.linea+1));

               
                break;
               case CADERROR:
                   e.add("La cadena "+lexer.lexeme+" en la linea "+(lexer.linea+1)+" no se reconoce como una cadena valida ");
                break;
                case ERROR:
                    e.add("La cadena "+lexer.lexeme+" en la linea "+(lexer.linea+1)+" no se reconoce como una cadena valida ");
                default:
                    
            }
            
        }
        
 }

    
}
