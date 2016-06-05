package cz.concrea.conferences.business.model.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class PersonalDataForm {

	@Size(min = 2, max = 100)
	private String surname;

	@Size(min = 2, max = 100)
	private String firstName;

	@Size(max = 100)
	private String degree;

	@Size(min = 2, max = 100)
	private String university;

	@Size(max = 100)
	private String division;

	@Size(min = 2, max = 100)
	private String street;

	@Size(min = 2, max = 100)
	private String city;

	@Size(min = 3, max = 14)
	private String zip;

	@Size(min = 1, max = 30)
	private String country;
	
	@Size(max = 10)
	private String gender;

	@Size(min = 3, max = 100)
	@NotNull
	@Email
	private String email;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PersonalDataForm() {
	}

}
