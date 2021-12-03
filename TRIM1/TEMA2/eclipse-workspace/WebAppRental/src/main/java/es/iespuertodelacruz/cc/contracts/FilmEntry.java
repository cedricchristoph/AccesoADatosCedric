package es.iespuertodelacruz.cc.contracts;

public class FilmEntry {

	/* TABLE DATA */
	public static final String TABLE = "film";
	public static final String ENTITY = "Film";
	public static final String ID = "film_id";
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	public static final String RELEASE_YEAR = "release_year";
	public static final String LANGUAGE_ID = "language_id";
	public static final String ORIGINAL_LANGUAGE_ID = "original_language_id";
	public static final String RENTAL_DURATION = "rental_duration";
	public static final String RENTAL_DATE = "rental_date";
	public static final String LENGTH = "length";
	public static final String REPLACEMENT_COST = "replacement_cost";
	public static final String LAST_UPDATE = "last_update";
	
	
	/* SQL QUERY */
	public static final String FINDALL = "Film.findAll";
	public static final String FINDALL_QUERY = "SELECT f FROM " + ENTITY + " f";
	public static final String FINDBYID = "Film.findById";
	public static final String FINDBYID_QUERY = "SELECT f FROM " + ENTITY + " f WHERE " +
												ID + " = ?1";
	
}
