package es.iespuertodelacruz.cc.pruebat3.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;

public class MyDatabase {

	private final String className = "com.mysql.cj.jdbc.Driver";
	private final String dbLocation;
	private final String dbConnection;
	
	BasicDataSource basicDataSource;
	
	// USER: webapp PWD: UrcZCb7qizCbeW2
	
	public MyDatabase() {
		dbLocation = Globals.CONST_APP_DBLOCATION;
		dbConnection = "jdbc:mysql://localhost:3306/" + dbLocation + "?serverTimezone=UTC&characterEncoding=utf-8";
		basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(className);
	    basicDataSource.setUrl(dbConnection);
	    basicDataSource.setUsername(Globals.CONST_APP_USER);
	    basicDataSource.setPassword(Globals.CONST_APP_PWD);
	}
	
	public MyDatabase(String databaseLocation) {
		dbLocation = databaseLocation;
		dbConnection = "jdbc:mysql://localhost:3306/" + dbLocation + "?serverTimezone=UTC&characterEncoding=utf-8";
		basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(className);
	    basicDataSource.setUrl(dbConnection);
	    basicDataSource.setUsername(Globals.CONST_APP_USER);
	    basicDataSource.setPassword(Globals.CONST_APP_PWD);
	}
	
	public MyDatabase(String databaseLocation, String username, String pwd) {
		dbLocation = databaseLocation;
		dbConnection = "jdbc:mysql://localhost:3306/" + dbLocation + "?serverTimezone=UTC&characterEncoding=utf-8";
		basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(className);
	    basicDataSource.setUrl(dbConnection);
	    basicDataSource.setUsername(username);
	    basicDataSource.setPassword(pwd);
	}
	
	public Connection getConnection() {
		Connection con = null;
		
        try {
            con = basicDataSource.getConnection();
        } catch (SQLException ex) {
        	System.out.println(ex.getMessage());
            System.exit(1);
        }        
        return con;
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
