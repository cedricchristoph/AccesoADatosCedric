/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmoprimos;

import es.iespuertodelacruz.cc.controlador.AppController;
import es.iespuertodelacruz.cc.modelo.Gestor;
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
    static es.iespuertodelacruz.cc.modelo.AlgoritmoPrimos algoritmo;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AppController ac = new AppController("resultados_binario");
        ac.iniciar();
    }
    
    
    
}
