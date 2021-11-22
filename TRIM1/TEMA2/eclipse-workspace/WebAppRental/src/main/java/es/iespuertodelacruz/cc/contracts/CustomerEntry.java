package es.iespuertodelacruz.cc.contracts;

public class CustomerEntry {

	/* TABLE DATA */
	public static final String TABLE = "customer";
	public static final String ENTITY = "Customer";
	public static final String ID = "customer_id";
	public static final String STORE_ID = "store_id";
	public static final String FIRSTNAME = "first_name";
	public static final String LASTNAME = "last_name";
	public static final String EMAIL = "email";
	public static final String ADDRESS_ID = "address_id";
	public static final String ACTIVE = "active";
	public static final String CREATE_DATE = "create_date";
	public static final String LAST_UPDATE = "last_update";
	
	
	/* SQL QUERY */
	public static final String FINDALL = "Customer.findAll";
	public static final String FINDALL_QUERY = "SELECT c FROM " + ENTITY + " c";
	public static final String FINDBYID = "Customer.findById";
	public static final String FINDBYID_QUERY = "SELECT c FROM " + ENTITY + " c WHERE " +
												ID + " = ?1";
	
}
