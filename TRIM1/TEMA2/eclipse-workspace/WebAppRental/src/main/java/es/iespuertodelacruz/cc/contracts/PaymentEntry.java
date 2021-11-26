package es.iespuertodelacruz.cc.contracts;

public class PaymentEntry {

	/* TABLE DATA */
	public static final String TABLE = "payment";
	public static final String ENTITY = "Payment";
	public static final String PAYMENT_ID = "payment_id";
	public static final String CUSTOMER_ID = "customer_id";
	public static final String STAFF_ID = "staff_id";
	public static final String RENTAL_ID = "rental_id";
	public static final String AMOUNT = "amount";
	public static final String DATE_TIME = "payment_date";
	
	/* SQL */
	public static final String FINDALL = "Payment.findAll";
	public static final String FINDALL_QUERY = "SELECT c FROM " + ENTITY + " c";
	public static final String FINDBYID = "Payment.findById";
	public static final String FINDBYID_QUERY = "SELECT c FROM " + ENTITY + " c WHERE " +
												PAYMENT_ID + " = ?1";
	public static final String FINDBYCUSTOMER = "Payment.findByCustomer";
	public static final String FINBYCUSTOMER = "SELECT c FROM " + ENTITY + " c WHERE " +
												CUSTOMER_ID + " = ?1";
}
