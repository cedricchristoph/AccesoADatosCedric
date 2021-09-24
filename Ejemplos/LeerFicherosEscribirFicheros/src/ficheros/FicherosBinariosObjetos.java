/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheros;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */


class DNI implements Serializable{
    int numero;

    public DNI(int numero) {
        this.numero = numero;
    }
    String letra;

    @Override
    public String toString() {
        return "DNI{" + "numero=" + numero + ", letra=" + letra + '}';
    }
}

class Persona implements Serializable{

    public Persona(String nombre, int edad, int altura, DNI dni) {
        this.nombre = nombre;
        this.edad = edad;
        this.altura = altura;
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", edad=" + edad + ", altura=" + altura + ", dni=" + dni + '}';
    }
    String nombre;
    int edad;
    int altura;
    DNI dni;
}





public class FicherosBinariosObjetos {


    public static Persona generadorPersonaAleatoria() {
        //array de nombres
        String nombres[] = {  "Cruz", "Axel", "Ariel", "Cameron", "Francis", "Noel", "René" , "Jessie", "Jade", "Reyes", "Tyler"};
        Random rnd = new Random();
        int edad = rnd.nextInt(50) + 15;
        int altura = rnd.nextInt(40) + 150;
        int dni = rnd.nextInt(80000000) + 10000000;
        String nombre = nombres[rnd.nextInt(nombres.length)];
        return new Persona(nombre,edad,altura,new DNI(dni));
    }


    public static void main(String[] args) {

        /* Quizás se pudiera desear hacer un acceso aleatorio en el fichero de objetos pero no es una buena idea
        * ya que los objetos pueden tener diferente tamaño cada uno así el acceso aleatorio es problemático
        * Si se quiere acceso aleatorio porque fichero es grande quizás fuera planteable una base de datos
        * 
        */
        
        Persona persona=null;
        try(
                FileOutputStream fos = new FileOutputStream(new File("aquel.txt"));
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                ObjectOutputStream oos = new ObjectOutputStream(bos);
            ){
            
            //Escribimos varias personas en fichero:
            for (int i = 0; i < 5; i++) {
                persona = generadorPersonaAleatoria();
                oos.writeObject(persona);
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FicherosBinariosObjetos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FicherosBinariosObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<Persona> personas = new ArrayList<Persona>();
        try(
                FileInputStream fis = new FileInputStream(new File("aquel.txt"));
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis);
            ){
            
            
            
            boolean finDeFichero = false;
            Persona p;
            do{
                
                try{
                    p = (Persona)ois.readObject();
                    
                    personas.add(p);
                    System.out.println(p);
                }catch(EOFException ex){
                    finDeFichero = true;
                }

            
            }while(!finDeFichero);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FicherosBinariosObjetos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FicherosBinariosObjetos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FicherosBinariosObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
