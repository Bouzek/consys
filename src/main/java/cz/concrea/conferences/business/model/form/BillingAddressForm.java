package cz.concrea.conferences.business.model.form;

import javax.validation.constraints.Size;

public class BillingAddressForm {
	@Size(max = 200)
	private String institution;
	
	@Size(min = 2, max = 200)
	private String name;

	@Size(min = 2, max = 100)
	private String street;

	@Size(min = 2, max = 100)
	private String city;

	@Size(min = 3, max = 14)
	private String zip;

	@Size(min = 3, max = 30)
	private String country;
	
	@Size(max = 30)
	private String IN; //IČ
	
	@Size(max = 30)
	private String TIN; //DIČ

	
	
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

	public String getIN() {
		return IN;
	}

	public void setIN(String iN) {
		IN = iN;
	}

	public String getTIN() {
		return TIN;
	}

	public void setTIN(String tIN) {
		TIN = tIN;
	}

	public BillingAddressForm() {
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}
	
	
	

}
