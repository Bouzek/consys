package cz.concrea.conferences.web.controller.admin.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.service.db.ConferenceService;
import cz.concrea.conferences.business.service.db.InvoiceService;
import cz.concrea.conferences.business.service.db.RegistrationService;
import cz.concrea.conferences.business.service.db.UserService;


@Controller
@RequestMapping(value = "/admin/{conferenceCode}/")
public class ConferenceOverviewController {
	
	@Autowired
	ConferenceService confService;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	RegistrationService registrationService;
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "overview")
	public String showOverview(Model model, @PathVariable(value = "conferenceCode") String conferenceCode) {
		Conference conference = confService.findConferenceByCodename(conferenceCode);
		model.addAttribute("conference", conference);
		model.addAttribute("invoices", invoiceService);
		model.addAttribute("registrations", registrationService);
		model.addAttribute("users", userService);
		model.addAttribute("isOverview", true);
		return "admin/conference/conferenceOverview";
	}
	
	
}
