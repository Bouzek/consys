package cz.concrea.conferences.business.model.form.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewConferenceForm {

	@Size(min = 3, max = 128)
	private String name;
	
	@Size(min = 3, max = 12)
	private String codeName;
	
	@NotNull
	private String confType;
	
	@NotNull
	private String confDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getConfDate() {
		return confDate;
	}

	public void setConfDate(String confDate) {
		this.confDate = confDate;
	}

	public String getConfType() {
		return confType;
	}

	public void setConfType(String confType) {
		this.confType = confType;
	}

	
	
	
	
}
