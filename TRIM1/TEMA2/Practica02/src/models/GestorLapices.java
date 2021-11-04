package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.enitities.Lapiz;
import utils.MyDatabase;

public class GestorLapices {
	
	MyDatabase db;
	
	public GestorLapices(String ddbb, String user, String pwd) {
		db = new MyDatabase(ddbb, user, pwd);
	}
	
	public GestorLapices(MyDatabase db) {
		this.db = db;
	}
	
	public ArrayList<Lapiz> getAllLapices() {
		ArrayList<Lapiz> lapices = new ArrayList<Lapiz>();
		try (Connection conn = db.getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select idlapiz,marca,numero from lapices");
			while (rs.next()) {
				lapices.add(new Lapiz(
							rs.getInt("idlapiz"),
							rs.getString("marca"),
							rs.getInt("numero")
						)
				);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			return lapices;
		}
	}
	
	public ArrayList<Lapiz> obtenerLapicesPorMarca(String marca) {
		ArrayList<Lapiz> lapices = new ArrayList<Lapiz>();
		try (Connection conn = db.getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select idlapiz,marca,numero from lapices where marca=" + marca);
			while (rs.next()) {
				lapices.add(new Lapiz(
							rs.getInt("idlapiz"),
							rs.getString("marca"),
							rs.getInt("numero")
						)
				);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			return lapices;
		}
	}
	
}
