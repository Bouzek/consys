package cz.concrea.conferences.business.model.form.admin;

public class DeleteConferenceForm {
	private String password;
	private long conferenceId;
	
	
	public DeleteConferenceForm() {
		conferenceId = -1;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public long getConferenceId() {
		return conferenceId;
	}


	public void setConferenceId(long conferenceId) {
		this.conferenceId = conferenceId;
	}
	
	
	
}
