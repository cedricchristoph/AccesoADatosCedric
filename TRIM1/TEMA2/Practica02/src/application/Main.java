package application;

import java.util.ArrayList;

import models.GestorLapices;
import models.enitities.Lapiz;
import utils.MyDatabase;

public class Main {

	public static void main(String[] args) {

		MyDatabase.cargarDriverMysql();
		
		GestorLapices gl = new GestorLapices("oficina", "root", "1q2w3e4r");
		ArrayList<Lapiz> lapices = gl.obtenerLapicesPorMarca("staedler");
		lapices.stream().forEach(l -> System.out.println(l.getId() + " "+ l.getMarca() + " " + l.getNumero()));
		
	}

}
