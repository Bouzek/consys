package cz.concrea.conferences.web.controller.admin.conference;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.model.form.admin.NewFormField;
import cz.concrea.conferences.business.model.form.admin.NewFormSubfield;
import cz.concrea.conferences.business.service.db.ConferenceService;
import cz.concrea.conferences.business.service.db.FormFieldService;
import cz.concrea.conferences.business.service.db.RegistrationService;
import cz.concrea.conferences.config.FormFieldType;

@Controller
@RequestMapping(value = "/admin/{conferenceCode}/")
public class ConferenceRegistrationSettingsController {

	@Autowired
	ConferenceService confService;
	@Autowired
	RegistrationService regService;
	@Autowired
	FormFieldService formFieldService;

	@ModelAttribute("newfield")
	public NewFormField newfield() {
		return new NewFormField();
	}

	@ModelAttribute("newsubfield")
	public NewFormSubfield newsubfield() {
		return new NewFormSubfield();
	}

	@RequestMapping(value = "regsettings")
	public String showRegistrationSettings(Model model, @PathVariable(value = "conferenceCode") String conferenceCode,
			@ModelAttribute("newField") NewFormField newfield,
			@ModelAttribute("newsubfield") NewFormSubfield newsubfield) {
		Conference conference = confService.findConferenceByCodename(conferenceCode);
		model.addAttribute("conference", conference);

		if (!regService.hasRegSettings(conference))
			regService.setActiveRegSettings(confService.findConferenceByCodename(conferenceCode), false);

		boolean hasReg = regService.isRegActive(conference);
		model.addAttribute("isRegistration", hasReg);
		model.addAttribute("regFields", regService.getRegSettings(conference).getFields());
		model.addAttribute("fieldtypes", FormFieldType.values());
		model.addAttribute("isSettings", true);
		return "admin/conference/conferenceRegSettings";
	}

	@RequestMapping(value = "regsettings", params = { "activate" })
	public String activateRegistration(@PathVariable(value = "conferenceCode") String conferenceCode) {
		regService.setActiveRegSettings(confService.findConferenceByCodename(conferenceCode), true);
		return "redirect:regsettings";
	}

	@RequestMapping(value = "regsettings", params = { "deactivate" })
	public String deactivateRegistration(@PathVariable(value = "conferenceCode") String conferenceCode) {
		regService.setActiveRegSettings(confService.findConferenceByCodename(conferenceCode), false);
		return "redirect:regsettings";
	}

	@RequestMapping(value = "regsettings", params = { "addfield" })
	public String addFormField(Model model, @Valid @ModelAttribute("newField") NewFormField newfield,
			@PathVariable(value = "conferenceCode") String conferenceCode, BindingResult bindingResult) {
		Conference conference = confService.findConferenceByCodename(conferenceCode);
		if (bindingResult.hasErrors()) {
			return "admin/conference/conferenceRegSettings";
		}
		regService.addRegFormField(formFieldService.addFormField(newfield), conference);

		return "redirect:regsettings";
	}

	@RequestMapping(value = "regsettings", params = { "addsubfield" })
	public String addFormSubfield(Model model, @Valid @ModelAttribute("newField") NewFormSubfield newsubfield,
			@PathVariable(value = "conferenceCode") String conferenceCode, BindingResult bindingResult,
			@RequestParam int addsubfield) {

		if (bindingResult.hasErrors()) {
			return "redirect:regsettings";
		}
		formFieldService.addSubfield(addsubfield, newsubfield);

		return "redirect:regsettings";
	}

	@RequestMapping(value = "regsettings", params = { "upfield" })
	public String moveUpField(@PathVariable(value = "conferenceCode") String conferenceCode,
			@RequestParam int upfield) {
		regService.moveField(upfield, -1, confService.findConferenceByCodename(conferenceCode));
		return "redirect:regsettings";
	}

	@RequestMapping(value = "regsettings", params = { "downfield" })
	public String moveDownField(@PathVariable(value = "conferenceCode") String conferenceCode,
			@RequestParam int downfield) {
		regService.moveField(downfield, 1, confService.findConferenceByCodename(conferenceCode));
		return "redirect:regsettings";
	}

	@RequestMapping(value = "regsettings", params = { "delfield" })
	public String deleteField(@PathVariable(value = "conferenceCode") String conferenceCode,
			@RequestParam int delfield) {
		regService.removeField(delfield, confService.findConferenceByCodename(conferenceCode));
		return "redirect:regsettings";
	}

	@RequestMapping(value = "regsettings", params = { "upsubfield" })
	public String moveUpSubField(@RequestParam int upsubfield) {
		formFieldService.moveSubField(upsubfield, -1);
		return "redirect:regsettings";
	}

	@RequestMapping(value = "regsettings", params = { "downsubfield" })
	public String moveDownSubField(@RequestParam int downsubfield) {
		formFieldService.moveSubField(downsubfield, 1);
		return "redirect:regsettings";
	}

	@RequestMapping(value = "regsettings", params = { "delsubfield" })
	public String deleteSubField(@RequestParam int delsubfield) {
		formFieldService.deleteSubField(delsubfield);
		return "redirect:regsettings";
	}

}
