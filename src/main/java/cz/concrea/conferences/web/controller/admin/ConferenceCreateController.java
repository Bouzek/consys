package cz.concrea.conferences.web.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.concrea.conferences.business.model.form.admin.NewConferenceForm;
import cz.concrea.conferences.business.service.db.ConferenceService;
import cz.concrea.conferences.config.ConferenceType;

@Controller
@RequestMapping(value = "/admin/")
public class ConferenceCreateController {

	@Autowired
	ConferenceService confService;

	@RequestMapping(value = "newconf", method = RequestMethod.GET)
	public String showNewConf(Model model) {
		NewConferenceForm newConf = new NewConferenceForm();
		model.addAttribute("newConf", newConf);
		model.addAttribute("conftypes", ConferenceType.values());
		return "admin/newConference";
	}

	@RequestMapping(value = "newconf", method = RequestMethod.POST)
	public String createNewConf(Model model, @Valid @ModelAttribute("newConf") NewConferenceForm newConf,
			BindingResult bindingResult) {

		if (confService.isDuplicate(newConf.getCodeName())){
			bindingResult.reject("duplicate");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("conftypes", ConferenceType.values());
			model.addAttribute("createerror", true);
			return "admin/newConference";
		}
		confService.createConference(newConf);

		return "redirect:/admin/conferences";
	}

}
