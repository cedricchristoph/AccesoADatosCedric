/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characterfill;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author Cedric Christoph
 */
public class CharacterFill {

    final static int REGISTER_SIZE = 50;
    
    final static File fichero = new File ("ficheroPersonas.txt");
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = fillRegister(scan.nextLine());
        System.out.println("Tamaño nuevo: " + input.length());
        System.out.println(input);
    }
    
    public static String fillRegister(String content) {
        content += "\0";
        while (content.length() < REGISTER_SIZE) {
            content += " ";
        }
        return content;
    }
    
    private String readString(long comienzo, int cantidad) throws FileNotFoundException, IOException {
        RandomAccessFile raf = new RandomAccessFile(fichero, "r");
        raf.seek(comienzo);
        char campo[] = new char[cantidad];
        for (int i=0; i<cantidad; i++) {
            campo[i] = raf.readChar();
        }
        raf.close();
        return new String(campo);
    }
    
}
