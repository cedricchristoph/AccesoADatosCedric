package es.iespuertodelacruz.cc.entities;

public class NumberController {

	private Numero secreto;
	
	public NumberController(Numero secreto) {
		this.secreto = secreto;
	}

	/**
	 * Funcion que devuelve un valor byte que indica una comparacion con el secreto
	 * 
	 * @param toCompare Numero a comparar con el secreto
	 * @return Byte:
	 * - (-1) : El numero secreto ha caducado
	 * - ( 0) : El numero enviado es menor que el secreto
	 * - ( 1) : El numero enviado es mayor que el secreto
	 * - ( 2) : El numero enviado y el secreto son identicos
	 */
	public synchronized byte check(Numero toCompare) {
		if (toCompare.getMilis() > secreto.getMilis()) {
			if (toCompare.getNumber() < secreto.getNumber())
				return 0;
			else if (toCompare.getNumber() > secreto.getNumber())
				return 1;
			return 2;
		} else {
			return -1;
		}
	}
	
	public synchronized Numero getSecreto() {
		return secreto;
	}

	public void setSecreto(Numero secreto) {
		this.secreto = secreto;
	}
	
}
