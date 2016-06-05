package cz.concrea.conferences.web.controller.admin.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.service.db.ConferenceService;
import cz.concrea.conferences.business.service.db.UserService;

@Controller
@RequestMapping(value = "/admin/{conferenceCode}/")
public class ConferenceUsersController {

	@Autowired
	ConferenceService confService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "users")
	public String showUsers(Model model, @PathVariable(value = "conferenceCode") String conferenceCode) {
		Conference conference = confService.findConferenceByCodename(conferenceCode);
		model.addAttribute("conference", conference);
		model.addAttribute("users", userService.getAllUsers(conference));
		model.addAttribute("isUsers", true);
		return "admin/conference/conferenceUsers";
	}

	@RequestMapping(value = "user", params = { "userid" })
	public String showUser(Model model, @PathVariable(value = "conferenceCode") String conferenceCode,
			@RequestParam int userid) {
		Conference conference = confService.findConferenceByCodename(conferenceCode);
		model.addAttribute("conference", conference);
		model.addAttribute("user", userService.getUser(userid));
		model.addAttribute("isUsers", true);
		return "admin/conference/conferenceUser";
	}

}
