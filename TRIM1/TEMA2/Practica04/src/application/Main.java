package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import models.GestorLapices;
import models.enitities.Lapiz;
import utils.MyDatabase;

public class Main {

	static Scanner scanner;
	static GestorLapices gl = null;
	
	public static void main(String[] args) {

		MyDatabase.cargarDriverMysql();
		scanner = new Scanner(System.in);
		
		while (gl == null) {
			login();
		}
		
		while (true) {
			printMenu();
			int input = Integer.parseInt(scanner.nextLine());
			switch (input) {
				case 1:
					verTodos();
					break;
				case 2:
					verMarca();
					break;
				case 3:
					actualizarLapiz();
					break;
				case 4:
					eliminarLapiz();
					break;
				case 5:
					addLapiz();
					break;
				case 0:
					System.exit(0);
			}
		}
	}

	private static void printMenu() {
		System.out.println("MENU");
		System.out.println("1 - Ver todos los lapices");
		System.out.println("2 - Ver lapices de una marca");
		System.out.println("3 - Actualizar un lapiz");
		System.out.println("4 - Eliminar un lapiz");
		System.out.println("5 - Añadir un lapiz");
		System.out.println("0 - Salir");
	}
	
	private static boolean login() {
		System.out.println("Introduzca usuario:");
		String user = scanner.nextLine();
		System.out.println("Introduzca contraseña:");
		String pwd = scanner.nextLine();
		gl = new GestorLapices("oficina", user, pwd);
		if (gl.checkConnection())
			return true;
		System.out.println("No se pudo conectar al servidor.");
		gl = null;
		return false;
	}
	
	private static void verTodos() {
		gl.getAllLapices().stream().forEach(l -> 
			System.out.println(l.getId() + " " + l.getMarca() + " " + l.getNumero())
		);
	}
	
	private static void verMarca() {
		System.out.println("Introduce marca a buscar:");
		String marca = scanner.nextLine();
		gl.obtenerLapicesPorMarca(marca).forEach(l -> 
			System.out.println(l.getId() + " " + l.getMarca() + " " + l.getNumero())
		);
	}
	
	private static void actualizarLapiz() {
		System.out.println("Introduce ID de lapiz a actualizar:");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.println("Introduce marca de lapiz:");
		String marca = scanner.nextLine();
		System.out.println("Introduce numero de lapiz:");
		int numero = Integer.parseInt(scanner.nextLine());
		Lapiz lapiz = new Lapiz(id, marca, numero);
		gl.update(lapiz);
		System.out.println("Actualizado");
	}
	
	private static void eliminarLapiz() {
		System.out.println("Introduce ID de lapiz a borrar:");
		int id = Integer.parseInt(scanner.nextLine());
		gl.remove(id);
		System.out.println("Borrado");
	}
	
	private static void addLapiz() {
		System.out.println("Introduce marca de lapiz:");
		String marca = scanner.nextLine();
		System.out.println("Introduce numero de lapiz:");
		int numero = Integer.parseInt(scanner.nextLine());
		Lapiz lapiz = new Lapiz(marca, numero);
		try {
			lapiz = gl.insert(lapiz);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("El lapiz se guardó bajo el ID: " + lapiz.getId());
	}
	
}
