package models.enitities;

public class Lapiz {

	/*
	 * Variables
	 */
	private int id;
	private String marca;
	private int numero;
	
	/**
	 * Constructor por defecto
	 */
	public Lapiz() {
		
	}
	
	/**
	 * Constructor
	 * @param marca Marca del lapiz
	 * @param numero Numero de lapices
	 */
	public Lapiz(String marca, int numero) {
		this.marca = marca;
		this.numero = numero;
	}
	
	/**
	 * Construcotr
	 * @param id Identificador del lapiz 
	 * @param marca Marca del lapiz
	 * @param numero Numero de lapices
	 */
	public Lapiz(int id, String marca, int numero) {
		this.id = id;
		this.marca = marca;
		this.numero = numero;
	}

	
	// Getters and setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	
}
