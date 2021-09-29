/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmoprimos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Cedric Christoph
 */
public class AlgoritmoPrimos {

    final static int PROGRESS_CHECK = 1000;
    static int actual;
    static int antiguo;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer limit;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introducir limite a comprobar numeros primos: (Introduce 0 para terminar el programa)");
            try {
                limit = scanner.nextInt();
                System.out.println("");
                if (!(limit == 0)) {
                    Algoritmo algoritmo = new Algoritmo(limit);
                    algoritmo.iniciarAlgoritmo();
                } else {
                    System.exit(0);
                }
            } catch (InputMismatchException e) {
                
            }
        }
    }
    
    
    
}
