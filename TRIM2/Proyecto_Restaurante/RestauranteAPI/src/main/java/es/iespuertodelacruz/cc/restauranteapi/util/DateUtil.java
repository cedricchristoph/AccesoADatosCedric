package es.iespuertodelacruz.cc.restauranteapi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public enum DateFormat {
		
		DD_MM_YYYY("dd/MM/YYYY"),
		MM_DD_YYYY("MM/dd/YYYY"),
		YYYY_MM_DD("YYYY/MM/DD");
		
		
		private String format;
		
		DateFormat(String format) {
			this.format = format;
		}
		
		public String getFormat() {
			return format;
		}
		
	}
	
	/**
	 * Convierte milisegundos en una fecha String según el formato indicado
	 * @param format Formato que debe tener la fecha
	 * @param millis Milisegundos a convertir a fecha
	 * @return String fecha formateada
	 */
	public static synchronized String millisToStringDate(DateFormat format, long millis) {
		return new SimpleDateFormat(format.getFormat()).format(new Date(millis));
	}
	
	/**
	 * Convierte una fecha en formato string a milisegundos
	 * @param format Formato de la fecha que se envía
	 * @param strDate Fecha en formato string
	 * @return long millisegundos de fecha
	 * @throws ParseException Excepción si la fecha no tiene el formato indicado
	 */
	public static synchronized long stringDateToMillis(DateFormat format, String strDate) throws ParseException {
			return new SimpleDateFormat(format.getFormat()).parse(strDate).getTime();
	}
	
}
