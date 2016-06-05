package cz.concrea.conferences.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.concrea.conferences.business.model.form.PersonalDataForm;

@Controller
@RequestMapping(value = "/confs/{ConferenceName}")
public class SignUpController {
	
	
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String showForm(Model model) {
		model.addAttribute("personForm", new PersonalDataForm());
		return "normal/persDataForm";
	}

	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public String checkPersonInfo(Model model, @Valid @ModelAttribute("personForm") PersonalDataForm personForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("personForm", personForm);
			return "normal/persDataForm";
		}
		return "normal/persDataForm";
	}
}
