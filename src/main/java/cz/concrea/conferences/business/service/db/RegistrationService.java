package cz.concrea.conferences.business.service.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.dao.entity.Invoice;
import cz.concrea.conferences.business.dao.entity.User;
import cz.concrea.conferences.business.dao.entity.UserRegistration;
import cz.concrea.conferences.business.dao.entity.UserRegistrationInfo;
import cz.concrea.conferences.business.dao.entity.settings.registration.FormFieldEntity;
import cz.concrea.conferences.business.dao.entity.settings.registration.RegistrationSettings;
import cz.concrea.conferences.business.dao.repository.ConferenceRegistrationSettingsRepository;
import cz.concrea.conferences.business.dao.repository.UserRegistrationInfoRepository;
import cz.concrea.conferences.business.dao.repository.UserRegistrationRepository;
import cz.concrea.conferences.business.dao.repository.UserRepository;
import cz.concrea.conferences.business.model.form.BillingAddressForm;
import cz.concrea.conferences.business.model.form.FormField;
import cz.concrea.conferences.business.model.form.RegistrationForm;
import cz.concrea.conferences.business.service.DateService;
import cz.concrea.conferences.config.InvoiceType;

@Service
public class RegistrationService {

	@Autowired
	UserRegistrationRepository regRepo;
	@Autowired
	ConferenceRegistrationSettingsRepository regSegRepo;
	@Autowired
	UserRegistrationInfoRepository regInfoRepo;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	ConferenceService confService;
	@Autowired
	UserRepository userRepo;
	@Autowired
	DateService dateService;
	@Autowired
	FormFieldService fieldService;
	
	
	public Invoice CreateRegistration(User user, Conference conference, RegistrationForm regForm, BillingAddressForm billingAddress){	
		Invoice invoice = invoiceService.createInvoice(user, regForm, billingAddress, InvoiceType.REGISTRATION, conference);
		
		List<UserRegistrationInfo> infos = new ArrayList<UserRegistrationInfo>();
		
		UserRegistration registration = new UserRegistration(user, invoice, infos);
		
		regRepo.save(registration);
		
		for ( FormField field : regForm.getFields() )
		{
			infos.add(field.getInfo(registration));
		}
		regInfoRepo.save(infos);	
		return invoice;
	}
	
	public List<UserRegistration> getRegistrations(Conference conference){
		List<User> users = userRepo.findByConference(conference);
		List<UserRegistration> regs = new ArrayList<UserRegistration>();
		for(User usr : users){
			UserRegistration reg = regRepo.findByUser(usr);
			if(reg != null) regs.add(reg);
		}
		return regs;
	}
	public int getRegistrationsCount(Conference conference){
		return getRegistrations(conference).size();
	}
	public int getRegistrationsCount(int conferenceId){
		return getRegistrationsCount(confService.findConferenceById(conferenceId));
	}
	
	public List<UserRegistration> getRegistrationsToday(Conference conference){
		List<User> users = userRepo.findByConference(conference);
		List<UserRegistration> regs = new ArrayList<UserRegistration>();
		for(User usr : users){
			UserRegistration reg = regRepo.findByUser(usr);
			if(reg != null && dateService.isToday(reg.getRegistered())) regs.add(reg);
		}
		return regs;
	}
	public int getRegistrationsCountToday(Conference conference){
		return getRegistrationsToday(conference).size();
	}
	public int getRegistrationsCountToday(int conferenceId){
		return getRegistrationsCountToday(confService.findConferenceById(conferenceId));
	}
	
	public UserRegistration getUserRegistration(User user){
		return regRepo.findByUser(user);
	}

	public boolean hasRegSettings(Conference conference){
		if (regSegRepo.findByConference(conference) == null) return false;
		return true;
	}
	public boolean isRegActive(Conference conference){
		if(!hasRegSettings(conference)) return false;
		return regSegRepo.findByConference(conference).isActive();
	}
	
	public RegistrationSettings getRegSettings(Conference conference){
		return regSegRepo.findByConference(conference);
	}
	
	public void setActiveRegSettings(Conference conference, boolean active){
		if(regSegRepo.findByConference(conference) == null){
			RegistrationSettings regSeg = new RegistrationSettings(new ArrayList<FormFieldEntity>(), conference, true);
			regSegRepo.save(regSeg);
		}
		RegistrationSettings regSeg = regSegRepo.findByConference(conference);
		regSeg.setActive(active);
		regSegRepo.save(regSeg);
	}
	
	public void addRegFormField(FormFieldEntity field, Conference conference){
		RegistrationSettings regSeg = regSegRepo.findByConference(conference);
		regSeg.addField(field);
		regSegRepo.save(regSeg);
	}
	
	public void moveField(int index, int shift, Conference conference){
		RegistrationSettings regSeg = regSegRepo.findByConference(conference);
		List<FormFieldEntity> fields = regSeg.getFields();
		long id1 = fields.get(index).getFieldid();
		long id2 = fields.get(index + shift).getFieldid();
		fieldService.switchFields(id1, id2, conference);
	}
	public void removeField(long id, Conference conference){
		RegistrationSettings regSeg = regSegRepo.findByConference(conference);
		for (FormFieldEntity field : regSeg.getFields()){
			if(field.getFieldid() == id){
				regSeg.getFields().remove(field);
				break;
			}
		}
		regSegRepo.save(regSeg);
		fieldService.deleteField(id);
	}

}
