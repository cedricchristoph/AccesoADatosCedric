package es.iespuertodelacruz.cc.entities;

/**
 * Clase que contiene la informacion de un Numero
 * @author Cedric Christoph
 *
 */
public class Numero {

	private long milis;
	public int number;
	
	public Numero(long milis, int number) {
		this.milis = milis;
		this.number = number;
	}

	public long getMilis() {
		return milis;
	}

	public void setMilis(long milis) {
		this.milis = milis;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}	
	
	@Override
	public String toString() {
		return "" + number;
	}
}
