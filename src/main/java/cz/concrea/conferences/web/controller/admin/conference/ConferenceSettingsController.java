package cz.concrea.conferences.web.controller.admin.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.service.db.ConferenceService;

@Controller
@RequestMapping(value = "/admin/{conferenceCode}/")
public class ConferenceSettingsController {
	
	@Autowired
	ConferenceService confService;
	
	@RequestMapping(value = "settings")
	public String showSettings(Model model, @PathVariable(value = "conferenceCode") String conferenceCode) {
		Conference conference = confService.findConferenceByCodename(conferenceCode);
		model.addAttribute("conference", conference);
		model.addAttribute("isSettings", true);
		return "admin/conference/conferenceSettings";
	}
	
}
