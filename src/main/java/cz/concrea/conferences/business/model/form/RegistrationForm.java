package cz.concrea.conferences.business.model.form;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.dao.entity.settings.registration.RegistrationSettings;

public class RegistrationForm extends PaidForm {

	public RegistrationForm() {
		super();
	}

	public RegistrationForm(Conference conference, RegistrationSettings regSettings) {
		super(conference, regSettings.getFields());
	}

}
