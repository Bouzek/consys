package cz.concrea.conferences.web.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.concrea.conferences.business.model.form.admin.DeleteConferenceForm;
import cz.concrea.conferences.business.service.db.ConferenceService;

@Controller
@RequestMapping(value = "/admin/")
public class ConferenceDeleteController {
	@Autowired
	ConferenceService confService;

	@RequestMapping(value = "deleteconf", method = RequestMethod.GET)
	public String showDeleteConf(Model model) {
		model.addAttribute("conferences", confService.findAllConferences());
		model.addAttribute("delForm", new DeleteConferenceForm());
		return "admin/deleteConference";
	}

	@RequestMapping(value = "deleteconf", method = RequestMethod.POST)
	public String deleteConf(Model model, @Valid @ModelAttribute("delForm") DeleteConferenceForm delForm,
			BindingResult bindingResult) {

		if (!delForm.getPassword().equals("delpassword")) {
			bindingResult.reject("passerror");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("passerror", true);
			model.addAttribute("conferences", confService.findAllConferences());
			return "admin/deleteConference";
		}
		confService.deleteConference(delForm.getConferenceId());
		model.addAttribute("conferences", confService.findAllConferences());
		model.addAttribute("delsuccess", true);
		return "admin/deleteConference";
	}

}
