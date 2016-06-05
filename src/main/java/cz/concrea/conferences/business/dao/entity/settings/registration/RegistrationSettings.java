package cz.concrea.conferences.business.dao.entity.settings.registration;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import cz.concrea.conferences.business.dao.entity.Conference;

@Entity
public class RegistrationSettings {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name = "REGISTRATIONS_AND_FIELDS", joinColumns = @JoinColumn(name = "REG_ID", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "FIELD_ID", referencedColumnName = "fieldid"))
	private List<FormFieldEntity> fields;

	@OneToOne
	@JoinColumn(name = "conference")
	private Conference conference;
	
	@NotNull
	private boolean active;

	public List<FormFieldEntity> getFields() {
		if(fields == null) return new ArrayList<FormFieldEntity>();
		return fields;
	}

	public Conference getConference() {
		return conference;
	}

	public RegistrationSettings() {
	}

	public RegistrationSettings(List<FormFieldEntity> fields, Conference conference, boolean active) {
		this.active = active;
		this.fields = fields;
		this.conference = conference;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setFields(List<FormFieldEntity> fields) {
		this.fields = fields;
	}
	public void addField(FormFieldEntity field){
		this.fields.add(field);
	}

	

}
