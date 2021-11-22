package es.iespuertodelacruz.cc.contracts;

public class RentalEntry {

	/* SQL TABLE DATA */
	public static final String TABLE = "rental";
	public static final String ENTITY = "Rental";
	public static final String ID = "rental_id";
	public static final String DATE = "rental_date";
	public static final String INVENTORY_ID = "inventory_id";
	public static final String CUSTOMER_ID = "customer_id";
	public static final String RETURN_DATE = "return_date";
	public static final String STAFF_ID = "staff_id";
	public static final String LAST_UPDATE = "last_update";
	
	/* SQL QUERY */
	public static final String FINDALL = "Rental.findAll";
	public static final String FINDALL_QUERY = "SELECT r FROM " + ENTITY + " r";
	public static final String FINDBYID = "Rental.findById";
	public static final String FINDBYID_QUERY = "SELECT r FROM " + ENTITY + " r WHERE " + ID + " = ?1";
}
