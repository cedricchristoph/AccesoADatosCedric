package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


import models.enitities.Lapiz;
import utils.MyDatabase;

public class Main {

	public static void main(String[] args) {

		MyDatabase.cargarDriverMysql();
		MyDatabase db = null;
		Connection conn = null; 
		ArrayList<Lapiz> lapices = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Presione CTRL+C para terminar la ejecucion del programa.");
		while (conn == null) {
			try {
				System.out.println("Introduzca usuario:");
				String user = scanner.nextLine();
				System.out.println("Introduzca contraseña:");
				String pwd = scanner.nextLine();
				db = new MyDatabase("oficina", user, pwd);
				conn = db.getConnection();
				System.out.println("¡Conectado con el servidor!");
			} catch (SQLException e) {
				System.out.println("No se pudo conectar al servidor. Causa:");
				System.out.println(e.getMessage());
				conn = null;
			}
		}

		String marca = "";
		System.out.println("Introduzca exit para terminar el programa.");
		while (!marca.toLowerCase().equals("exit")) {
			lapices = new ArrayList<Lapiz>();
			System.out.println("Introduce una marca de lapices:");
			marca = scanner.nextLine();
			Statement stmt;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select idlapiz,marca,numero from lapices where marca=" + marca);
				while (rs.next()) {
					lapices.add(new Lapiz(rs.getInt("idlapiz"), rs.getString("marca"), rs.getInt("numero")));
				}
				lapices.forEach(l -> System.out.println(l.getId() + " " + l.getMarca() + " " + l.getNumero()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
