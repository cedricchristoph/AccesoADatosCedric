package es.iespuertodelacruz.cc.contracts;

public class StaffEntry {

	/* SQL TABLE DATA */
	public static final String TABLE = "staff";
	public static final String ENTITY = "Staff";
	public static final String ID = "staff_id";
	public static final String NAME = "first_name";
	public static final String LASTNAME = "last_name";
	public static final String ADDRESS_ID = "address_id";
	public static final String EMAIL = "email";
	public static final String STORE_ID = "store_id";
	public static final String ACTIVE = "active";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	
	/* SQL QUERY */
	public static final String FINDALL = "Staff.findAll";
	public static final String FINDALL_QUERY = "SELECT s FROM " + ENTITY + " s";
	public static final String FINDUSER = "Staff.findByUser";
	public static final String FINDUSER_QUERY = "SELECT s FROM " + ENTITY + " s WHERE s." 
																 + USERNAME + " = ?1";
	
}
