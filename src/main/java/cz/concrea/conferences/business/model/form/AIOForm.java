package cz.concrea.conferences.business.model.form;

import javax.validation.Valid;

public class AIOForm {
	
	@Valid
	private PersonalDataForm personalDataForm;
	@Valid
	private RegistrationForm registrationForm;
	@Valid
	private BillingAddressForm billingAddressForm;

	public PersonalDataForm getPersonalDataForm() {
		return personalDataForm;
	}

	public void setPersonalDataForm(PersonalDataForm personalDataForm) {
		this.personalDataForm = personalDataForm;
	}

	public RegistrationForm getRegistrationForm() {
		return registrationForm;
	}

	public void setRegistrationForm(RegistrationForm registrationForm) {
		this.registrationForm = registrationForm;
	}

	public BillingAddressForm getBillingAddressForm() {
		return billingAddressForm;
	}

	public void setBillingAddressForm(BillingAddressForm billingAddressForm) {
		this.billingAddressForm = billingAddressForm;
	}

	public AIOForm() {
	}

	public AIOForm(PersonalDataForm personalDataForm, RegistrationForm registrationForm,
			BillingAddressForm billingAddressForm) {
		this.personalDataForm = personalDataForm;
		this.registrationForm = registrationForm;
		this.billingAddressForm = billingAddressForm;
	}

	
}
