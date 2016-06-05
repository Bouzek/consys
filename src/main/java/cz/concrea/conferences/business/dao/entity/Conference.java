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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import cz.concrea.conferences.config.ConferenceType;

@Entity
@Table(name = "conferences")
public class Conference {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique=true)
	@NotNull
	private String codeName;

	@NotNull
	private String name;

	@Column
	private Timestamp confdate;
	
	@Enumerated(EnumType.STRING)
	private ConferenceType conferenceType;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "conference")
	private List<User> users;
	

	public Conference() {} //nechat

	public Conference(String codeName, String name, ConferenceType conferenceType, Timestamp confdate) {
		this.codeName = codeName;
		this.name = name;
		this.conferenceType = conferenceType;
		this.confdate = confdate;
	}
	
	public void addUser(User user) {
        this.users.add(user);
        if (user.getConference() != this) {
            user.setConference(this);
        }
    }

	public List<User> getUsers(){
		return users;
	}

	public String getCodeName() {
		return codeName;
	}


	public String getName() {
		return name;
	}

	public ConferenceType getConferenceType() {
		return conferenceType;
	}

	public void setConferenceType(ConferenceType conferenceType) {
		this.conferenceType = conferenceType;
	}

	public long getId() {
		return id;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Timestamp getConfdate() {
		return confdate;
	}
	public void setConfdate(Timestamp confDate) {
		this.confdate = confDate;
	}
	public String getConfDateFormatted() {
		if(confdate == null) return "Unknown";
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(confdate.getTime()));
		String dt = cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH)+1) + "."+cal.get(Calendar.YEAR);
		return dt;
	}

	
	
	
}
