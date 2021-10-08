/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.controlador;

import es.iespuertodelacruz.cc.modelo.AlgoritmoPrimos;
import es.iespuertodelacruz.cc.modelo.Gestor;
import java.util.Scanner;

/**
 *
 * @author cedric christoph
 */
public class AppController {
   
    AlgoritmoPrimos algoritmo;
    Gestor gestor;
    
    public AppController() {}
    
    public void iniciar() {
        Integer number;
        while ((number = getUserLimit()) != 0) {
            algoritmo = new AlgoritmoPrimos(number);
            algoritmo.iniciarAlgoritmo();
        }
        
        gestor = new Gestor();
        gestor.guardar(algoritmo.getResultadosPrimos());
        System.out.println("\nResultados guardados :)");
    }
    
    /**
     * Funcion que devuelve el numero introducido por el usuario
     * @return Devuelve un numero en todo caso
     */
    private Integer getUserLimit() {
        Scanner scanner = new Scanner(System.in);
        Integer number = null;
        while (number == null)
            try {
                System.out.println("\nIntroduzca el limite: ");
                number = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("ERROR: No ha introducido un numero");
            }
        return number;
    }
}
