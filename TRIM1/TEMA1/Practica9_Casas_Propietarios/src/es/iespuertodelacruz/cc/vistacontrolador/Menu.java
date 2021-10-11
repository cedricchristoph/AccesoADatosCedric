/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.vistacontrolador;

import es.iespuertodelacruz.cc.xml.ListaCasasXML;
import es.iespuertodelacruz.cc.entities.Casa;
import es.iespuertodelacruz.cc.entities.ListaCasas;
import es.iespuertodelacruz.cc.entities.ListaPropietarios;
import es.iespuertodelacruz.cc.entities.Propietario;
import es.iespuertodelacruz.cc.entities.TipoCasa;
import es.iespuertodelacruz.cc.utils.FileManager;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author cedric christoph
 */
public class Menu {
    
    // Variables de la clase Menu
    
    ListaCasas listaCasas;
    ListaPropietarios listaPropietarios;
    Scanner scanner;
    
    /**
     * Constructor por defecto.
     */
    public Menu() {
        listaCasas = new ListaCasas();
        listaPropietarios = new ListaPropietarios();
    }
    
    /**
     * Metodo para iniciar el funcionamiento del menu.
     */
    public void iniciar() {
        scanner = new Scanner(System.in);
        int input;
        while (true) {
            printMenuOptions();
            try {
                input = Integer.parseInt(scanner.nextLine());
                validar(input);
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Por favor introduzca solo numeros\n");
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Por favor introduzca solo numeros\n");
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
    
    /**
     * Metodo para ejecutar la fucion que inserto el usuario
     * @param input Integer punto del menu
     */
    private void validar(int input) {
        switch (input) {
            case 1:
                crearPropietario();    
                break;
            case 2:
                mostrarPropietarios();
                break;
            case 3:
                crearCasa();
                break;
            case 4:
                mostrarCasas();
                break;
            case 5:
                addPropietarioACasa();
                break;
            case 6:
                guardarXML();
                break;
            case 7:
                cargarXML();
                break;
            default:
                System.exit(0);
        }
    }
    
    /**
     * Metodo para imprimir las opciones del menu.
     */
    private void printMenuOptions() {
        System.out.println("            MENU\n" + 
                           " (1) -- Añadir propietario\n" +
                           " (2) -- Mostrar propietarios\n" +
                           " (3) -- Añadir una casa\n" +
                           " (4) -- Mostrar casas\n" +
                           " (5) -- Añadir propietario a una casa\n" +
                           " (6) -- Guardar datos en XML\n" +
                           " (7) -- Cargar datos de XML\n" + 
                           " (0) -- Salir\n");     
    }
    
    /**
     * Metodo para crear un nuevo propietario.
     */
    private void crearPropietario() {
        String dni;
        String nombre;
        String apellidos;
        System.out.println("========== AÑADIR PROPIETARIO ==========");
        try {
            scanner = new Scanner(System.in);
            System.out.println("Introducir dni ( Formato: 12345678A ): ");
            dni = scanner.nextLine();
            System.out.println("Introducir nombre: ");
            nombre = scanner.nextLine();
            System.out.println("Introducir apellidos: ");
            apellidos = scanner.nextLine();
            
            listaPropietarios.add(new Propietario(
                    dni, nombre, apellidos, null
            ));
                        
            System.out.println("\n✔ Añadido correctamente ✔\n");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            System.out.println("========================================\n");
        }
    }
    
    /**
     * Metodo para añadir una nueva casa.
     */
    private void crearCasa() {
        Integer idCasa;
        String direccion;
        TipoCasa tipoCasa;
        Propietario propietario;
        System.out.println("========== AÑADIR CASA ==========");
        try {
            scanner = new Scanner(System.in);
            System.out.println("Introducir identificador de casa (numero): ");
            idCasa = Integer.parseInt(scanner.nextLine());
            if (listaCasas.get(idCasa) != null)
                throw new Exception("El identificador de la casa ya esta en uso");
            System.out.println("Introducir direccion de la casa: ");
            direccion = scanner.nextLine();
            System.out.println("Introducir tipo de vivienda: ");
            System.out.println("  1-APARTAMENTO  2-PISO  3-CASA  4-VILLA ");
            tipoCasa = TipoCasa.getTipo(Integer.parseInt(scanner.nextLine()));
            if (tipoCasa == null)
                throw new NullPointerException("El tipo de casa que ha seleccionado no existe.");
            System.out.println("Introducir dni del propietario:");
            propietario = listaPropietarios.get(scanner.nextLine());
            if (propietario == null) 
                throw new NullPointerException("El propietario que ha introducido no se encuentra en la base de datos.");
            
            listaCasas.add(new Casa(
                    idCasa, direccion, tipoCasa, propietario
            ));
            
            System.out.println("\n✔ Añadido correctamente ✔\n");
        } catch (InputMismatchException e) {
            System.out.println("ERROR: No ha introducido un numero");
        } catch (NullPointerException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            System.out.println("=====================================\n");
        }
    } 
    
    /**
     * Metodo para añadir un propietario a una casa.
     */
    private void addPropietarioACasa() {
        Propietario propietario;
        Casa casa;
        try {
            System.out.println("============= AÑADIR PROPIETARIO A CASA =============");
            System.out.println(" Introducir dni del propietario: ");
            propietario = listaPropietarios.get(scanner.nextLine());
            if (propietario == null)
                throw new Exception("No se pudo encontrar este propietario.");
            System.out.println(" Introducir identificador de la casa: ");
            casa = listaCasas.get(Integer.parseInt(scanner.nextLine()));
            if (casa == null)
                throw new Exception("No se puedo encontrar la casa con este identificador.");
            casa.add(propietario);
            propietario.addPropiedad(casa);
            System.out.println("\n✔ Añadido correctamente ✔\n");
        } catch (InputMismatchException e) {
            System.out.println("ERROR: No ha introducido un numero");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            System.out.println("=====================================================");
        }
        
    }
    
    /**
     * Metodo para mostrar la lista de propietarios.
     */
    private void mostrarPropietarios() {
        try {
            System.out.println("=========== MOSTAR PROPIETARIOS ===========");
            listaPropietarios.getPropietarios().stream().forEach(p -> System.out.println(" - " + p.toString()));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            System.out.println("===========================================");
        }
    }
    
    /**
     * Metodo para mostrar las casas y sus propietarios.
     */
    private void mostrarCasas() {
        try {
            System.out.println("=========== MOSTAR CASAS ===========");
            listaCasas.getCasas().stream().forEach(c -> {
                System.out.println(" - " + c.toString()); 
                System.out.println("    Propietarios:");
                c.getPropietarios().stream().forEach(p -> System.out.println("     - " + p.toString()));
            });
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            System.out.println("====================================");
        }
    }
    
    /**
     * Metodo para guardar los datos de la aplicacion en un XML.
     */
    private void guardarXML() {
        String rutaFichero;
        System.out.println("=========== GUARDAR XML ===========");
        try {
            scanner = new Scanner(System.in);
            System.out.println("Introducir ruta del fichero (ej. /home/[usuario]/Escritorio/fichero.xml): ");
            rutaFichero = scanner.nextLine();
            FileManager mf = new FileManager(rutaFichero);
            ListaCasasXML converter = new ListaCasasXML();
            mf.write(converter.objToStringXML(listaCasas));
            System.out.println("\n✔ Guardado correctamente ✔\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println("==================================\n");
        }
    }
    
    /**
     * Metodo para cargar la informacion de un XML.
     */
    private void cargarXML() {
        String rutaFichero;
        System.out.println("=========== CARGAR XML ===========");
        try {
            scanner = new Scanner(System.in);
            System.out.println("Introducir ruta del fichero (ej. /home/[usuario]/Escritorio/fichero.xml): ");
            rutaFichero = scanner.nextLine();
            FileManager mf = new FileManager(rutaFichero);
            ListaCasasXML converter = new ListaCasasXML();
            listaCasas = converter.stringXMLToObj(mf.readAll());
            getPropietariosFromListaCasas();
            System.out.println("\n✔ Cargado correctamente ✔\n");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            System.out.println("==================================\n");
        }
    }
    
    /**
     * Metodo para cargar los propietarios de la ListaCasas tras cargar de un fichero XML.
     */
    private void getPropietariosFromListaCasas() {
        for (Casa casa : listaCasas.getCasas()) {
            for (Propietario propietario : casa.getPropietarios()) {
                if (!(listaPropietarios.exists(propietario)))
                    listaPropietarios.add(propietario);
            }
        }
    }
    
}
