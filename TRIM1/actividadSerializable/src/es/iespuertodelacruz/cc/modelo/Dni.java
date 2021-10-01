/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.modelo;

import java.io.Serializable;

/**
 *
 * @author Cedric Christoph
 */
public class Dni implements Serializable {
    
    private final String[] LETRAS_CORRESPONDIENTES = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
    
    int numero;
    String letra;
    boolean valido;
    
    public Dni (String input) {
        if (input.length() == 9) {
            char c = input.charAt(0);
            if (c >= 'A' && c <= 'Z') { 
                input = convertirNie(input);
            }
            numero = Integer.parseInt(input.substring(0, 7));
            letra = input.charAt(7) + "";
            valido = comprobarDni();
        }
    }

    public int getNumero() {
        return numero;
    }

    public String getLetra() {
        return letra;
    }

    public boolean isValido() {
        return valido;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    private String convertirNie(String nie) {
        // X 0
        // Y 1
        // Z 2
        nie = nie.toUpperCase();
        String dni = "";
        switch (nie.charAt(0)) {
            case 'X':
                dni = "1";
                break;
            case 'Y':
                dni = "2";
                break;
            case 'Z':
                dni = "3";
        }
        dni = nie.substring(1, nie.length());
        return dni;
    }
    
    private boolean comprobarDni() {
        if (letra.equals(LETRAS_CORRESPONDIENTES[numero%23]))
            return true;
        return false;
    }
    
}
