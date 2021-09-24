/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheros;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class EntradaSalidaFicheroValidaAnteriorJava8 {


    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {

        BufferedWriter out = null;
        BufferedWriter nbw = null;
        BufferedWriter bwFile = null;
        PrintWriter pwFile = null;
        RandomAccessFile rafFichero = null;
        
        //          ACCESO A FICHERO 1
        try {
            //Usando un printwriter para texto ( sin buffer )
            //<--- Desventaja: no tiene un buffer y Se come las excepciones
            // Observar que es autónomo. No precisa de otro método. Incluso disponemos de encoding

            pwFile = new PrintWriter("C:/tmp/cuba.txt", "UTF-8");
            pwFile.println("Cuba es una isla caribeña");
            pwFile.println("a la que hubo una importante migración");
            pwFile.println("de Canarios durante el siglo XX");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(EntradaSalidaFicheroValidaAnteriorJava8.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EntradaSalidaFicheroValidaAnteriorJava8.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                pwFile.close();
               
            }catch(Exception ex){
                Logger.getLogger(EntradaSalidaFicheroValidaAnteriorJava8.class.getName()).log(Level.SEVERE, null, ex);    
            }
        }
        
        
        
        
        //          ACCESO A FICHERO 2
        try {
            
//        File fich = new File("fichero.txt");
//        FileChannel channel = null;
//        FileLock flFichero = null;
        

            //Observar que basta con cerrar el buffer. usa FILEWRITER  *******************************************
            //no pone encoding NO LO SOPORTA FileWriter. Observar que hay que enviar un newLine()
        
            bwFile = new BufferedWriter( new FileWriter("C:/tmp/cuba.txt"));
            bwFile.write("Se puede observar que reescribe el fichero");
            bwFile.newLine();
            bwFile.close();

        } catch (IOException ex) {
            Logger.getLogger(EntradaSalidaFicheroValidaAnteriorJava8.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bwFile.close();
            } catch (IOException ex) {
                Logger.getLogger(EntradaSalidaFicheroValidaAnteriorJava8.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
        
		//          ACCESO A FICHERO 3  
        try {

            //La opción con encoding...
            out = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("C:/tmp/cuba.txt"), "UTF-8"));
            out.write("Esto se ha realizado con tres instrucciones");
            out.newLine();
            out.write("BufferedWriter, sobre el OutputStreamWriter para encoding\n\n");
            out.write("y el FileOutputStream que es el que se encarga del nombre de fichero");
            out.newLine();
            out.close();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EntradaSalidaFicheroValidaAnteriorJava8.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EntradaSalidaFicheroValidaAnteriorJava8.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(EntradaSalidaFicheroValidaAnteriorJava8.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

            
            
        //          ACCESO A FICHERO 4    
        try {
            //Eliminando la necesidad de OutputStreamWriter mediante NIO y su newBufferedWriter
            //Observar que ha sobreescrito el fichero
            //observar que NO USAMOS el comando new sino un método factoria
            Charset ch =  Charset.forName("UTF-8");
            Path filePath = Paths.get("C:/tmp/cuba.txt");
            nbw = Files.newBufferedWriter(filePath, ch);
            nbw.append("newBufferedWriter blablabla");
            
        } catch (IOException ex) {
            Logger.getLogger(EntradaSalidaFicheroValidaAnteriorJava8.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                nbw.close();
            } catch (IOException ex) {
                Logger.getLogger(EntradaSalidaFicheroValidaAnteriorJava8.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
        
       //          ACCESO A FICHERO 5 
        try {
            rafFichero = new RandomAccessFile("C:/tmp/cuba.txt", "rwd");
            //FileChannel fcFichero = rafFichero.getChannel();
            //flFichero = fcFichero.lock(0, rafFichero.length(), false);
            if( rafFichero.length() > 1 )
                rafFichero.seek(rafFichero.length());
            rafFichero.writeUTF("Estamos añadiendo texto al final del fichero");

            //flFichero.release();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EntradaSalidaFicheroValidaAnteriorJava8.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EntradaSalidaFicheroValidaAnteriorJava8.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                rafFichero.close();
            } catch (IOException ex) {
                Logger.getLogger(EntradaSalidaFicheroValidaAnteriorJava8.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
