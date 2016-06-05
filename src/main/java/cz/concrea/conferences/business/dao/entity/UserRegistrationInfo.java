package cz.concrea.conferences.business.dao.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class UserRegistrationInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String name;
	@NotNull
	private String value;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "registrationId")
	private UserRegistration registration;


	public UserRegistrationInfo(String name, String value, UserRegistration registration) {
		this.name = name;
		this.value = value;
		this.registration = registration;
	}


	public UserRegistrationInfo() {
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	
	
}