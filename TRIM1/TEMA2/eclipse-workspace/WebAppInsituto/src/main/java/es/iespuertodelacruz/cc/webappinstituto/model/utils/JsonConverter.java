package es.iespuertodelacruz.cc.webappinstituto.model.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase para convertir objetos java a json
 * @author Cedric Christoph
 *
 * @param <T> Objeto Java a convertir
 */
public class JsonConverter<T> {

	ObjectMapper mapper;
	public JsonConverter() {
		mapper = new ObjectMapper();
	}
	
	public String convertToJson(T entity) {
		try {
			return mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(entity);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String convertToJson(List<T> entities) {
		String output = "";
		for (T entity : entities)
			output += convertToJson(entity) + "\n";
		return output;
	}
}
