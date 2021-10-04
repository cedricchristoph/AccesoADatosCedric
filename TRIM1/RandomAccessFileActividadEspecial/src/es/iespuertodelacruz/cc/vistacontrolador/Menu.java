/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.vistacontrolador;

import es.iespuertodelacruz.cc.modelo.GestorPersonas;
import es.iespuertodelacruz.cc.modelo.Persona;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author cedric christoph
 */
public class Menu {
    
    private GestorPersonas gestor;
    private Scanner scanner;
    
    /**
     * Constructor de la clase Menu
     */
    public Menu() {
        gestor = new GestorPersonas();
    }
    
    public Menu(String rutaFichero, String rutaIndex) {
        gestor = new GestorPersonas(rutaFichero, rutaIndex);
    }
    
    /**
     * Metodo para iniciar el funcionamiento del menu
     */
    public void start() {
        System.out.println(
            "RANDOM ACCESS FILE APPLICATION EXAMPLE\n" +
            "Cedric Christoph\n" +
            "=================================================\n\n"
        );
        Integer input;
        do {
            scanner = new Scanner(System.in);
            try {
                showOptions();
                input = scanner.nextInt();
                switch (input) {
                    case 1:
                        crearPersona();
                        break;
                    case 2:
                        encontrarPersona();
                        break;
                    default:
                        System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor introduzca solo numeros");
            }
        } while (true);
    }
    
    /**
     * Metodo para mostrar al usuario las opciones del menu
     */
    private void showOptions() {
        System.out.println(
                "                     MENU\n" +
                "=================================================\n" +
                "  ( 1 ) -- Crear nueva persona\n" +
                "  ( 2 ) -- Encontrar una persona en el fichero\n" +
                "  ( 0 ) -- Salir\n\n"
        );
    }
    
    /**
     * Metodo para iniciar la creacion de una persona recibiendo entradas del usuario
     */
    private void crearPersona() {
        String dni;
        String nombre;
        String apellidos;
        scanner = new Scanner(System.in);
        System.out.println("Introduzca DNI:");
        dni = scanner.nextLine();
        System.out.println("Introduzca nombre:");
        nombre = scanner.nextLine();
        System.out.println("Introduzca apellidos:");
        apellidos = scanner.nextLine();
        if (gestor.get(dni) == null) {
            gestor.addPersona(new Persona (dni, nombre, apellidos));
            System.out.println("✔ Guardado e indexado\n");
        } else {
            System.out.println("Este DNI ya existe\n");
        }
    }
    
    /**
     * Metodo para iniciar la busqueda de una persona recibiendo entradas del usuario
     */
    private void encontrarPersona() {
        scanner = new Scanner(System.in);
        System.out.println("Introduzca dni a buscar:");
        String dni = scanner.nextLine();
        Persona persona;
        if ((persona = gestor.get(dni)) != null) {
            System.out.println("\n============================");
            System.out.println(persona.getDni() + " " + persona.getNombre() + " " + persona.getApellidos());
            System.out.println("============================\n");
        } else {
            System.out.println("No se pudo encontrar la persona");
        }
    }
}
