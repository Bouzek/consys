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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "conference")
	private Conference conference;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private UserInfo userInfo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Invoice> invoices;
	
	@Column(name="signedOn", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp signedOn;
	
	
	public Conference getConference() {
		return conference;
	}
	
	public void setConference(Conference conference) {
        this.conference = conference;
        
        if (!conference.getUsers().contains(this)) { // O(n) slozitost, bacha
        	conference.getUsers().add(this);
        }
        
    }

	public User() {}

	public User(Conference conference, String email, String password, UserInfo userInfo) {
		this.conference = conference;
		this.email = email;
		this.password = password;
		this.userInfo = userInfo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Timestamp getSignedOn() {
		return signedOn;
	}

	public void setSignedOn(Timestamp signedOn) {
		this.signedOn = signedOn;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getSignedTime(){
		return signedOn.toString();
	}
	
	
}
