/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


/**
 *
 * @author carlos
 */
public class LeerEscribirFicherosDeTexto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        

        Path path = Paths.get(System.getProperty("user.home"), "prueba.txt");
        
        
        boolean finalizar = false;
        Scanner sc = new Scanner(System.in);
        
        
        
        //para ficheros de texto
        //sistema viejo sencillo pero nada protegido PrintWriter no debe acceder
        //directamente porque no controla los fallos de i/o
        //ni siquiera mostramos como se escribiría. Se usaría si se quisiera por ejemplo:  pw.println("texto");
         try {
            PrintWriter pw = new PrintWriter(new File("oooo.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeerEscribirFicherosDeTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         
         
        //para ficheros de texto escritura
        //una alternativa sencilla pero no tan eficiente al no usar buffer
        //válido para algo muy sencillo. Observar que nos hace la apertura
        //escritura y finalmente cierre del fichero en cada sentencia Files.write
        
        System.out.println("Introducir el texto a escribir");
        System.out.println("una línea con espacios por favor. para que sea parseada a un array de string");

        try{
                //Ahora vemos como usar directamente Files.write con texto
                // cogemos una frase adicional la convertimos en un array de string con una palabra en cada string
                //y escribimos una por linea
                System.out.println("Probando Files.write. Escribe una frase: ");
                String texto = sc.nextLine();
                Files.write(path, Arrays.asList(texto.split(" ")) );
        } catch (IOException ex) {
            Logger.getLogger(LeerEscribirFicherosDeTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        

        //para ficheros de texto escritura
        //Acceso eficiente mediante buffer escribiendo con buffer y si se quiere formateado con printwriter
        System.out.println("Introducir el texto a escribir");
        System.out.println("Se finalizará introduciendo una línea: Fin");

        try(BufferedWriter bw =  Files.newBufferedWriter(path,StandardOpenOption.APPEND)){
            
            PrintWriter pw = new PrintWriter(bw); //linea opcional si queremos texto formateado
            do{
                String texto = sc.nextLine();
                if(texto.toLowerCase().equals("fin")){
                    finalizar = true;
                }else{
                    bw.write(texto);
                    bw.newLine();
                    
                    pw.printf("El número pi con 3 decimales: %.3f junto con el texto de usuario: %s \n", Math.PI,texto);
                    //printwriter nos escribe formateado y nos pone el salto de línea 
                    //este método no te lanza excepción si hay error de entrada/salida
                    //así que es buena idea en general usarlo sobre un BufferedWriter
                }
            }while(!finalizar);
            
        } catch (IOException ex) {
            Logger.getLogger(LeerEscribirFicherosDeTexto.class.getName()).log(Level.SEVERE, null, ex);
        }


        
        
        
        //La lectura de fichero se puede hacer de varias formas.
        //si es un fichero pequeño se puede cargar todo en memoria de una sola sentencia y trabajar directamente
        //cuidado!! un fichero de 300MB podría generar una lectura imposible en un ordenador de 4GB de RAM
        //pero si vamos a por un fichero de unos pocos MB se puede hacer sin problemas
        System.out.println("\n\nvamos a leer el fichero: " + path.getFileName() + "\n");
        
        
        //array para almacenar el contenido del fichero
        ArrayList<String> array=null;
        System.out.println("Haciendo uso de readalllines:");
        try{
            array = new ArrayList(Files.readAllLines(path));
            //vamos a mostrarlo en pantalla
            array.stream()
                    .forEach(System.out::println);
        }catch(IOException ex){
            Logger.getLogger(LeerEscribirFicherosDeTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println("\n\n\n");
        
        
        
        
        //Ahora leer el fichero si se quiere, directamente con BufferedReader aprovechando las ventajas de stream
        //con bufferedreader ya podemos despreocuparnos del problema de desbordamiento por cargar todo
        //el fichero en memoria y adicionalmente eficiente por usar un buffer
        
        try(BufferedReader br= Files.newBufferedReader(path)){


            System.out.println("leer directamente mediante bufferedreader con las ventajas de stream:");
            array = (ArrayList)br.lines()
                    .peek(System.out::println)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            Logger.getLogger(LeerEscribirFicherosDeTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        System.out.println("\n\n\n lectura del fichero nuevamente. Ahora con Scanner\n");
        //Si se prefiere se puede usar una forma más tradicional mediante Scanner
        //Tiene por ventaja que nos hace el parser directamente al soportar nextInt(),...
        try(BufferedReader br= Files.newBufferedReader(path)){
            Scanner scFile = new Scanner(br);

            System.out.println("Comenzamos la lectura con Scanner:");
            while(scFile.hasNext()){
                String linea = scFile.nextLine(); //observar que aquí se podría haber elegido por ejemplo:  scFile.nextDouble() 
                System.out.println(linea);
                if(array == null )
                    array = new ArrayList<String>();
                array.add(linea);
            }
        } catch (IOException ex) {
            Logger.getLogger(LeerEscribirFicherosDeTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
