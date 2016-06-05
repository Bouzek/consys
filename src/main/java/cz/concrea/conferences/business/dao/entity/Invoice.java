package cz.concrea.conferences.business.dao.entity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import cz.concrea.conferences.config.InvoiceType;

@Entity
@Table(name = "invoices")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="invoiceId")
	private long id;

	@NotNull
	private int paid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private User user;

	@OneToMany
	@JoinColumn(name = "invoiceId", referencedColumnName = "invoiceId")
	private List<InvoiceItem> items;

	@Column(name="issued", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp issued;
	
	//BILLING ADDRESS
	@Column
	private String institution;
	@Column
	private String name;
	@Column
	private String street;
	@Column
	private String city;
	@Column
	private String zip;
	@Column
	private String country;
	@Column
	private String IDN; //IČ
	@Column
	private String TIDN; //DIČ
	
	//CONFERENCE AND TYPE SPECIFIC NUMBER
	@Column
	private int number;
	@Enumerated(EnumType.STRING)
	private InvoiceType type;
	@ManyToOne(fetch=FetchType.LAZY)
	private Conference conference;
	

	public Invoice(int paid, User user, List<InvoiceItem> items, String name, String street, String city, String zip,
			String country, String iN, String tIN, InvoiceType type, Conference conference, int number, String institution) {
		this.paid = paid;
		this.user = user;
		this.items = items;
		this.name = name;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
		IDN = iN;
		TIDN = tIN;
		this.type = type;
		this.conference = conference;
		this.number = number;
		this.institution = institution;
	}

	public int getTotalPrice(){
		int price = 0;
		for (InvoiceItem item : items){
			price += item.getPrice();
		}
		return price;
	}
	
	public String getFormattedIssued(){
		String fIssued;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(issued.getTime()));
		fIssued = cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH)+1) + "."+cal.get(Calendar.YEAR);
		return fIssued;
	}
	public String getFormattedDue(){
		String fDue;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(issued.getTime()));
		cal.add((Calendar.WEEK_OF_YEAR), 2);
		fDue= cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH)+1) + "."+cal.get(Calendar.YEAR);
		return fDue;
	}
	
	public String getFormattedId(){
		String fId = "" + id;
		while(fId.length() < 5){
			fId = "0" +fId;
		}
		fId = "1" +fId;
		return fId;
	}

	public int getTotal(){
		if(items == null || items.isEmpty()) return 0;
		int price = 0;
		for(InvoiceItem item : items){
			price += item.getPrice();
		}
		return price;
	}
	
	public Invoice() {
	}

	public long getId() {
		return id;
	}



	public int getPaid() {
		return paid;
	}



	public void setPaid(int paid) {
		this.paid = paid;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public List<InvoiceItem> getItems() {
		return items;
	}



	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}



	public Timestamp getIssued() {
		return issued;
	}



	public void setIssued(Timestamp issued) {
		this.issued = issued;
	}



	public int getNumber() {
		return number;
	}

	public InvoiceType getType() {
		return type;
	}

	public String getTypeName() {
		return type.name().toLowerCase();
	}
	
	public Conference getConference() {
		return conference;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getZip() {
		return zip;
	}



	public void setZip(String zip) {
		this.zip = zip;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getIDN() {
		return IDN;
	}



	public void setIDN(String iDN) {
		IDN = iDN;
	}



	public String getTIDN() {
		return TIDN;
	}



	public void setTIDN(String tIDN) {
		TIDN = tIDN;
	}



	public void setId(long id) {
		this.id = id;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setType(InvoiceType type) {
		this.type = type;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}
	
	
	
}
