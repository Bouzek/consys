package cz.concrea.conferences.business.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@Column(nullable = true)
	private String degree;
	@Column(nullable = true)
	private String gender;
	@NotNull
	private String university;
	@Column(nullable = true)
	private String division;
	@NotNull
	private String country;
	@NotNull
	private String city;
	@NotNull
	private String street;
	@NotNull
	private String postCode;
	
	@OneToOne
	@JoinColumn(name = "user")
	private User user;

	
	
	public UserInfo() {}

	public UserInfo(String firstName, String lastName, String degree, String gender, String university,
			String division, String country, String city, String street, String postCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.degree = degree;
		this.gender = gender;
		this.university = university;
		this.division = division;
		this.country = country;
		this.city = city;
		this.street = street;
		this.postCode = postCode;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public User getUser() {
		return user;
	}
	
	
}
