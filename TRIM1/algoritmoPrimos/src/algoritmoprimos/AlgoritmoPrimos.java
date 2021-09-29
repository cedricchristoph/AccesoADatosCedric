/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmoprimos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author Cedric Christoph
 */
public class AlgoritmoPrimos {

    final static int PROGRESS_CHECK = 10;
    static int actual;
    static int antiguo;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introducir limite a comprobar numeros primos:");
        Integer limit = scanner.nextInt();
        System.out.println("");
        iniciarAlgoritmo(limit);
        
    }
    
    private static void iniciarAlgoritmo(Integer limit) {
        ArrayList<Integer> resultadosPrimos = new ArrayList<>();
        int counter = 0;
        antiguo = 2;
        for (int i = 2; i < limit; i++) {
            if ((i - antiguo) == PROGRESS_CHECK) {
                antiguo = i;
                double porcentaje = ((i / (double) limit) * 100);
                System.out.println("Procesando... ( " + i + " / " + limit + " ) " + porcentaje + "%");
            }
            if (esPrimo(i))
                resultadosPrimos.add(i);
        }
        System.out.println("Existen " + resultadosPrimos.size() + " numeros primos en los primeros " + limit + " numeros");
    }
    
    private static boolean esPrimo(int numero) {
        for (int i = 2; i < numero; i++) {
            if (numero % i == 0) 
                return false;
        }
        return true;
    }
    
    
}
