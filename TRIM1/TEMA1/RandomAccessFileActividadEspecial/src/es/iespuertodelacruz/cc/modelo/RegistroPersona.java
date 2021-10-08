/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cedric
 */
public class RegistroPersona {
    
    public static final int SIZE_NOMBRE = 50;
    public static final int SIZE_APELLIDOS = 50;
    public static final int SIZE_DNI = 10;
    
    public static final int CHARS_TO_BYTES_SIZE = 2;
    
    public static final int CHAR_BLOCK_SIZE = SIZE_NOMBRE + SIZE_APELLIDOS + SIZE_DNI;
    public static final int BLOCK_SIZE = CHAR_BLOCK_SIZE * CHARS_TO_BYTES_SIZE;
    
    
    private String fixedNombre;
    private String fixedApellidos;
    private String fixedDni;
    
    public RegistroPersona(String nombre, String apellidos, String dni) {
        fixedNombre = stringToFixed(nombre, SIZE_NOMBRE);
        fixedApellidos = stringToFixed(apellidos, SIZE_APELLIDOS);
        fixedDni = stringToFixed(dni, SIZE_DNI);
    }
    
    public RegistroPersona(Persona persona) {
        fixedNombre = stringToFixed(persona.getNombre(), SIZE_NOMBRE);
        fixedApellidos = stringToFixed(persona.getApellidos(), SIZE_APELLIDOS);
        fixedDni = stringToFixed(persona.getDni(), SIZE_DNI);
    }
    
    public RegistroPersona(String data) {
        fixedNombre = data.substring(0, SIZE_NOMBRE);
        fixedApellidos = data.substring(SIZE_NOMBRE, (SIZE_NOMBRE + SIZE_APELLIDOS));
        fixedDni = data.substring((SIZE_NOMBRE + SIZE_APELLIDOS), (SIZE_DNI + SIZE_NOMBRE + SIZE_APELLIDOS));
    }
//    public RegistroPersona(byte[] data) {
//        int start = 0;
//        int end = SIZE_NOMBRE * CHARS_TO_BYTES_SIZE;
//        System.out.println("Leyendo desde " + start + " hasta 0" + end);
//        byte[] nombre = Arrays.copyOfRange(data, start, end);
//        
//        start = end;
//        end += (SIZE_APELLIDOS * CHARS_TO_BYTES_SIZE);
//        
//        System.out.println("Leyendo desde " + start + " hasta 0" + end);
//        byte[] apellidos = Arrays.copyOfRange(data, start, end);
//        
//        start = end;
//        end += (SIZE_DNI * CHARS_TO_BYTES_SIZE);
//        
//        System.out.println("Leyendo desde " + start + " hasta 0" + end);
//        byte[] dni = Arrays.copyOfRange(data, start, end);
//        
//        fixedNombre = new String(nombre);
//        fixedApellidos = new String(apellidos);
//        fixedDni = new String(dni);
//    }
    
    public Persona toPersona() {
        return new Persona(
                fixedToString(fixedDni),
                fixedToString(fixedNombre),
                fixedToString(fixedApellidos)
        );
    }
    
    public char[] toCharArray() {
        char[] nombre = fixedNombre.toCharArray();
        char[] apellidos = fixedApellidos.toCharArray();
        char[] dni = fixedDni.toCharArray();
        char[] output = join(nombre, apellidos, dni);
        return output;
    }
    
    private String stringToFixed(String str, int size) {
        str += "\0";
        for (int i = str.length(); i < size; i++) {
            str += " ";
        }
        return str;
    }
    
    private String fixedToString(String str) {
        if (str.contains("\0")) {
            try {
                return str.split("\0")[0];
            } catch (Exception e) {}
        }
        return str;
    }
    
    private char[] join(char[] nombre, char[] apellidos, char[] dni) {
        List<Character> list = new ArrayList<>();
        for (char b : nombre) {
            list.add(b);
        }
        for (char b : apellidos) {
            list.add(b);
        }
        for(char b: dni) {
            list.add(b);
        }
        Object[] joinedList = list.toArray();
        char[] output = new char[joinedList.length];
        for (int i = 0; i < joinedList.length; i++) {
            output[i] = (char) joinedList[i];
        }
        return output;
    }
    
    
    // Getters & Setters

    public String getFixedNombre() {
        return fixedNombre;
    }

    public String getFixedApellidos() {
        return fixedApellidos;
    }

    public String getFixedDni() {
        return fixedDni;
    }

    public void setFixedNombre(String fixedNombre) {
        this.fixedNombre = fixedNombre;
    }

    public void setFixedApellidos(String fixedApellidos) {
        this.fixedApellidos = fixedApellidos;
    }

    public void setFixedDni(String fixedDni) {
        this.fixedDni = fixedDni;
    }
    
}
