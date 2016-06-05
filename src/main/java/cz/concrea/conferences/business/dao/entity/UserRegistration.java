package cz.concrea.conferences.business.dao.entity;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class UserRegistration {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long registrationId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "invoice")
	private Invoice invoice;
	
	@OneToMany
	@JoinColumn(name = "registrationId", referencedColumnName = "registrationId")
	private List<UserRegistrationInfo> infos;
	
	@Column(name="registered", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp registered;

	public UserRegistration(User user, Invoice invoice, List<UserRegistrationInfo> infos) {
		this.user = user;
		this.invoice = invoice;
		this.infos = infos;
	}

	public UserRegistration() {
	}

	public List<UserRegistrationInfo> getInfos() {
		return infos;
	}

	public void setInfos(List<UserRegistrationInfo> infos) {
		this.infos = infos;
	}

	public long getRegistrationId() {
		return registrationId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Timestamp getRegistered() {
		return registered;
	}

	public void setRegistered(Timestamp registered) {
		this.registered = registered;
	}

	
	
	
	
}
