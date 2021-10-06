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
    
    /**
     * Constantes
     */
    private final String[] LETRAS_CORRESPONDIENTES = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
    
    /**
     * Variables
     */
    String numero;
    String letra;
    boolean valido = false;
    boolean isNie = false;
    
    /**
     * Constructor de la clase Dni
     * @param input El Dni a guardar
     */
    public Dni (String input) {
        if (input.length() == 9) {
            char c = input.charAt(0);
            if (c >= 'A' && c <= 'Z') { 
                isNie = true;
                input = convertirNie(input);
            }
            numero = input.substring(0, 7);
            letra = input.charAt(input.length()-1) + "";
            valido = comprobarDni();
        }
    }

    // Getters & Setters
    public String getNumero() {
        return numero;
    }

    public String getLetra() {
        return letra;
    }

    public boolean isValido() {
        return valido;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    /**
     * Funcion que devuelve la letra inicial de un NIE
     * @param numeroInicial Numero inicial del DNI
     * @return Letra inicial de Nie
     */
    private String getNieLetraPrincipal(String numeroInicial) {
        switch (numeroInicial) {
            case "0":
                return "X";
            case "1":
                return "Y";
            case "2":
                return "Z";
        }
        return null;
    }
    
    /**
     * Funcion que devuelve un Dni a partir de un Nie
     * @param nie Nie a convertir
     * @return Devuelve dni
     */
    private String convertirNie(String nie) {
        // X 0
        // Y 1
        // Z 2
        nie = nie.toUpperCase();
        String dni = "";
        switch (nie.charAt(0)) {
            case 'X':
                dni = "0";
                break;
            case 'Y':
                dni = "1";
                break;
            case 'Z':
                dni = "2";
        }
        dni += nie.substring(1, nie.length());
        return dni;
    }
    
    /**
     * Funcion que determina si un dni es valido
     * @return Devuelve true y solo true si el dni es valido
     */
    private boolean comprobarDni() {
        if (letra.equals(LETRAS_CORRESPONDIENTES[Integer.parseInt(numero)%23]))
            return true;
        return false;
    }
    
    /**
     * Metodo para conseguir Dni/Nie en su formato debido como String para mostrar
     * @return Dni/Nie completo
     */
    @Override
    public String toString() {
        String strDni = "";
        if (isNie)
            strDni = getNieLetraPrincipal(numero.substring(0, 1));
        return strDni + numero.substring(1) + letra;
    }
    
}
