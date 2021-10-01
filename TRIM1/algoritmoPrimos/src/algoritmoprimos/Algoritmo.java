/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmoprimos;

import java.util.ArrayList;

/**
 *
 * @author Cedric Christoph
 */
public class Algoritmo {
    
    final int PROGRESS_CHECK = 5000;
    int antiguo;
    int limit;
    ArrayList<Integer> resultadosPrimos;
    
    /**
     * Constructor de la clase Algoritmo
     * @param limit Limite a encontrar numeros primos
     */
    public Algoritmo(int limit) {
        this.limit = limit;
    }

    public ArrayList<Integer> getResultadosPrimos() {
        return resultadosPrimos;
    }
    
    /**
     * Metodo para iniciar el algoritmo
     */
    public void iniciarAlgoritmo() {
        resultadosPrimos = new ArrayList<>();
        int counter = 0;
        antiguo = 2;
        for (int i = 2; i < limit; i++) {
            if ((i - antiguo) == PROGRESS_CHECK) {
                antiguo = i;
                double porcentaje = ((i / (double) limit) * 100);
                System.out.println("Procesando... ( " + i + " / " + limit + " ) " + String.format("%.2f", porcentaje) + "%");
            }
            if (esPrimo(i))
                resultadosPrimos.add(i);
        }
        System.out.println("\nAlgoritmo completado ( " + limit + " / " + limit + " ) 100,00%\n\nResultados:");
        System.out.println("Existen " + resultadosPrimos.size() + " numeros primos en los primeros " + limit + " numeros");
    }
    
    /**
     * Funcion que determina si el numero indicado es o no primo
     * @param numero Numero a comprobar
     * @return devuelve true y solo true si el numero es primo
     */
    private boolean esPrimo(int numero) {
        for (int i = 2; i < numero; i++) {
            if (numero % i == 0) 
                return false;
        }
        return true;
    }
}
