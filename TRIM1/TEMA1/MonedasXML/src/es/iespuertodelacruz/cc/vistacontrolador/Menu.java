/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.vistacontrolador;

import es.iespuertodelacruz.jc.monedasxml.entities.Almacen;
import es.iespuertodelacruz.jc.monedasxml.entities.Historico;
import es.iespuertodelacruz.jc.monedasxml.entities.Moneda;
import es.iespuertodelacruz.jc.monedasxml.utils.ManejoFichero;
import es.iespuertodelacruz.jc.monedasxml.xml.AlmacenXML;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author cedric
 */
public class Menu {
    
    Almacen almacen;
    Scanner scanner;
    
    public Menu() {
        almacen = new Almacen();
    }
    
    public void iniciar() {
        scanner = new Scanner(System.in);
        int input = 0;
        while (true) {
            printMenuOptions();
            try {
                input = Integer.parseInt(scanner.nextLine());
                validar(input);
            } catch (InputMismatchException e) {
                System.out.println("Por favor introduzca solo numeros\n");
            }
        }
    }
    
    private void validar(int input) {
        switch (input) {
            case 1:
                crearMoneda();
                break;
            case 2:
                crearHistorico();
                break;
            case 3:
                guardarXML();
                break;
            case 4:
                cargarXML();
                break;
            default:
                System.exit(0);
        }
    }
    
    private void printMenuOptions() {
        System.out.println("            MENU\n" + 
                           " (1) -- Crear moneda\n" +
                           " (2) -- Añadir historico a moneda\n" +
                           " (3) -- Guardar datos en XML\n" +
                           " (4) -- Cargar datos de XML\n" + 
                           " (0) -- Salir\n");     
    }
    
    private void crearMoneda() {
        Integer id;
        String nombre;
        String pais;
        System.out.println("========== CREAR MONEDA ==========");
        try {
            scanner = new Scanner(System.in);
            System.out.println("Introducir identificador (numero): ");
            id = Integer.parseInt(scanner.nextLine());
            System.out.println("Introducir nombre de moneda: ");
            nombre = scanner.nextLine();
            System.out.println("Introducir pais de la moneda: ");
            pais = scanner.nextLine();
            almacen.add(new Moneda(
                    id, nombre, pais
            ));
            System.out.println("✔ Añadido correctamente ✔\n");
        } catch (InputMismatchException e) {
            System.out.println("ERROR: No ha introducido un numero");
        } finally {
            System.out.println("==================================\n");
        }
    }
    
    private void crearHistorico() {
        Moneda moneda;
        Integer idMoneda;
        Date fecha;
        double valor;
        System.out.println("========== CREAR HISTORICO ==========");
        try {
            scanner = new Scanner(System.in);
            System.out.println("Introducir identificador de moneda (numero): ");
            idMoneda = Integer.parseInt(scanner.nextLine());
            System.out.println("Introducir fecha del historico (dd-MM-YYYY): ");
            fecha = new Date(scanner.nextLine());
            System.out.println("Introducir pais de la moneda: ");
            valor = Double.parseDouble(scanner.nextLine());
            
            moneda = almacen.getMoneda(idMoneda);
            if (moneda == null)
                throw new NullPointerException("No se pudo encontrar la moneda con id " + idMoneda);
            moneda.addHistorico(
                    new Historico(
                            moneda.getHistoricos().size(),
                            moneda,
                            fecha,
                            valor
                    )
            );
            System.out.println("✔ Añadido correctamente ✔\n");
        } catch (InputMismatchException e) {
            System.out.println("ERROR: No ha introducido un numero");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("=====================================\n");
        }
    } 
    
    private void guardarXML() {
        String rutaFichero;
        System.out.println("=========== GUARDAR XML ===========");
        try {
            scanner = new Scanner(System.in);
            System.out.println("Introducir ruta del fichero (ej. /home/[usuario]/Escritorio/fichero.xml): ");
            rutaFichero = scanner.nextLine();
            ManejoFichero mf = new ManejoFichero(rutaFichero);
            AlmacenXML converter = new AlmacenXML();
            mf.write(converter.objToStringXML(almacen));
            System.out.println("✔ Guardado correctamente ✔\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println("==================================\n");
        }
    }
    
    private void cargarXML() {
        String rutaFichero;
        System.out.println("=========== CARGAR XML ===========");
        try {
            scanner = new Scanner(System.in);
            System.out.println("Introducir ruta del fichero (ej. /home/[usuario]/Escritorio/fichero.xml): ");
            rutaFichero = scanner.nextLine();
            ManejoFichero mf = new ManejoFichero(rutaFichero);
            AlmacenXML converter = new AlmacenXML();
            almacen = converter.stringXMLToObj(mf.readAll());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            System.out.println("==================================\n");
        }
    }
}

