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
	
	public boolean checkConnection() {
		try {
			Connection conn = db.getConnection();
			if (conn == null)
				return false;
			return true;
		} catch (Exception e) {
			return false;
		}
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
	
	public boolean update(Lapiz lapiz) {
		try (Connection conn = db.getConnection()){
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("update lapices SET marca=" + lapiz.getMarca() + ",numero=" + lapiz.getNumero() + "  where idLapiz=" + lapiz.getId());
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	public boolean remove(int id) {
		try (Connection conn = db.getConnection()){
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("delete from lapices where idLapiz="+id);
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
}
