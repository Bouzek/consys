package cz.concrea.conferences.web.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.dao.entity.Invoice;
import cz.concrea.conferences.business.dao.entity.User;
import cz.concrea.conferences.business.model.form.AIOForm;
import cz.concrea.conferences.business.model.form.BillingAddressForm;
import cz.concrea.conferences.business.model.form.PersonalDataForm;
import cz.concrea.conferences.business.model.form.RegistrationForm;
import cz.concrea.conferences.business.service.EmailService;
import cz.concrea.conferences.business.service.db.ConferenceService;
import cz.concrea.conferences.business.service.db.RegistrationService;
import cz.concrea.conferences.business.service.db.UserService;
import cz.concrea.conferences.config.ConferenceType;

@Controller
@RequestMapping(value = "/confs/{conference}")
@SessionAttributes("currentConf")
public class AIOFormController {

	@Autowired
	ConferenceService conferenceService;

	@Autowired
	UserService userService;

	@Autowired
	RegistrationService regService;

	@Autowired
	EmailService mailService;

	@ModelAttribute("aioform")
	public AIOForm getAioform(@PathVariable(value = "conference") String conferenceCode) {
		return new AIOForm(new PersonalDataForm(),
				new RegistrationForm(conferenceService.findConferenceByCodename(conferenceCode),
						regService.getRegSettings(conferenceService.findConferenceByCodename(conferenceCode))),
				new BillingAddressForm());
		
	}

	@RequestMapping(value = "/aioform", method = RequestMethod.GET)
	public String showForm(Model model, @PathVariable(value = "conference") String conferenceCode,
			@ModelAttribute("aioform") AIOForm aioform) {
		if (!validateUrl(model, conferenceCode))
			return "redirect:/confs/" + conferenceCode + "/";

		model.addAttribute("aioform", aioform);
		return "AIOForm/aioform";
	}

	@RequestMapping(value = "/aioform", method = RequestMethod.POST)
	public String checkPersonInfo(Model model, @Valid @ModelAttribute("aioform") AIOForm aioform,
			BindingResult bindingResult, @PathVariable(value = "conference") String conferenceCode,
			final Locale locale) {
		if (userService.isExistingEmail(aioform.getPersonalDataForm().getEmail(),
				conferenceService.findConferenceByCodename(conferenceCode)))
			bindingResult.rejectValue("personalDataForm.email", "error.user", "Email je již použit.");

		model.addAttribute("aioform", aioform);
		if (bindingResult.hasErrors()) {
			return "AIOForm/aioform";
		}

		User user = userService.CreateUser(aioform.getPersonalDataForm(),
				conferenceService.findConferenceByCodename(conferenceCode));

		Invoice inv = regService.CreateRegistration(user, conferenceService.findConferenceByCodename(conferenceCode),
				aioform.getRegistrationForm(), aioform.getBillingAddressForm());

		mailService.SendAIOCompleted(aioform, conferenceService.findConferenceByCodename(conferenceCode), inv,
				regService.getUserRegistration(user), locale);
		return "AIOForm/completed";
	}

	boolean validateUrl(Model model, String conferenceCode) {
		try {
			Conference currentConf = (Conference) model.asMap().get("currentConf");
			if (currentConf.getCodeName().equals(conferenceCode)
					&& currentConf.getConferenceType() == ConferenceType.AIO) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
