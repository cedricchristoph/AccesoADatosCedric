package es.iespuertodelacruz.cc.webapprental.entity;

import java.io.Serializable;
import javax.persistence.*;

import es.iespuertodelacruz.cc.contracts.RentalEntry;
import es.iespuertodelacruz.cc.webapprental.utils.Globals;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;


/**
 * The persistent class for the rental database table.
 * 
 */
@Entity
@Table(name=RentalEntry.TABLE)
@NamedQueries({
	@NamedQuery(name=RentalEntry.FINDALL, query=RentalEntry.FINDALL_QUERY),
	@NamedQuery(name=RentalEntry.FINDBYID, query=RentalEntry.FINDBYID_QUERY)
})
public class Rental implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rental_id")
	private int rentalId;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="rental_date")
	private Date rentalDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="return_date")
	private Date returnDate;
	
	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="rental", cascade = CascadeType.PERSIST)
	private List<Payment> payments;
	
	@Transient
	private List<Payment> newPayments;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	//bi-directional many-to-one association to Inventory
	@ManyToOne
	@JoinColumn(name="inventory_id")
	private Inventory inventory;
	
	//bi-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="staff_id")
	private Staff staff;

	public Rental() {
	}

	/**
	 * Calcula el pago pendiente del alquiler
	 * @return
	 */
	public double getPagoPendiente() {
		double pagosRealizadosTotal = 0;
		for (Payment p : getPayments())
			pagosRealizadosTotal += Double.parseDouble(p.getAmount().toString());
		double pendiente = 
					Double.parseDouble(getInventory().getFilm().getRentalRate().toString()) 
					- pagosRealizadosTotal;
		return pendiente;
	}
	
	public String getPagoPendienteToString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getPagoPendiente());
	}
	
	public int getRentalId() {
		return this.rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getRentalDate() {
		return this.rentalDate;
	}
	
	public String getRentalDateString() {
		return Globals.getOnlyDate(rentalDate);
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public String getReturnDateString() {
		return Globals.getOnlyDate(returnDate);
	}
	
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Payment addPayment(Payment payment) {
		payment.setCustomer(getCustomer());
		payment.setRental(this);
		getPayments().add(payment);
		return payment;
	}
	
	public Payment addNewPayment(Payment payment) throws Exception {
		if (newPayments == null)
			newPayments = new ArrayList<Payment>();
		payment.setCustomer(getCustomer());
		payment.setRental(this);
		newPayments.add(payment);
		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		return payment;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public List<Payment> getNewPayments() {
		return newPayments;
	}

	public void setNewPayments(List<Payment> newPayments) {
		this.newPayments = newPayments;
	}
	
	


}