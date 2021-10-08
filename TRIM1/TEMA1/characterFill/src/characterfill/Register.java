/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characterfill;

/**
 *
 * @author dama
 */
public class Register {
    final static int SIZE_NOMBRE = 50;
    final static int SIZE_APELLIDO = 50;
    final static int SIZE_EDAD = 3;
    final static int SIZE_REGISTRO = SIZE_NOMBRE + SIZE_APELLIDO + SIZE_EDAD;
    
    public static String fixedSizeStringtoString(String input) {
        if (input.contains("\0"))
            return input.split("\0")[0];
        return input;
    }
    
    
}
