package es.iespuertodelacruz.cc.webapprental.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for creating a redirecting URL that contains parameters
 * @author Cedric Christoph
 *
 */
public class ParameterUrl {

	/**
	 * Variables for ParameterUrl
	 */
	private String destination;
	private HashMap<String, String> parameters;
	
	/**
	 * Constructor for ParameterURL
	 * @param destination main destination target without parameters
	 */
	public ParameterUrl(String destination) {
		this.destination = destination;
		parameters = new HashMap<String, String>();
	}

	/**
	 * Adds a parameter to the url
	 * @param name Name of the parameter
	 * @param value Value of the parameter
	 */
	public void addParameter(String name, String value) {
		parameters.put(name, value);
	}
	
	/**
	 * If given parameter name exists, it removes it from the url
	 * @param name Name of the parameter to remove
	 */
	public void removeParameter(String name) {
		parameters.remove(name);
	}
	
	/**
	 * Function that parses the final url as String
	 * @return Url with given parameters
	 */
	public String toUrl() {
		String url = destination;
		if (!parameters.isEmpty()) {
			url += "?";
			int counter = 0;
			for (Map.Entry<String, String> entry : parameters.entrySet()) {
				url += entry.getKey() + "=" + entry.getValue();
				counter++;
				System.out.println(counter + "  " + (parameters.size()));
				if (counter < (parameters.size()))
					url += "&";
			}
		}
		return url;
	}
	
	// Getters & Setters
	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public HashMap<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}
	
	
}
