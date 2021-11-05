package es.iespuertodelacruz.cc.webappinstituto.model.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDatabase {

	private final String className = "com.mysql.cj.jdbc.Driver";
	private final String dbLocation;
	private final String dbConnection;
	
	private final String username;
	private final String pwd;
	
	private String command;
	Connection conexion = null;
	
	public MyDatabase(String databaseLocation, String username, String pwd) {
		dbLocation = databaseLocation;
		this.username = username;
		this.pwd = pwd;
		dbConnection = "jdbc:mysql://localhost:3306/" + dbLocation + "?serverTimezone=UTC";
	}
	
	public Connection getConnection() {
		if (username == null || pwd == null)
			return null;
		if ((!(username.isEmpty()) && !(pwd.isEmpty()))) {
			try {
				return DriverManager.getConnection(dbConnection, username, pwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static void cargarDriverMysql() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.err.println("no carga el driver");
			System.exit(1);
		}
	}
	
}
