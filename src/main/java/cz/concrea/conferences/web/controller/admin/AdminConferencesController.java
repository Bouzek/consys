package cz.concrea.conferences.web.controller.admin;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.service.db.ConferenceService;
import cz.concrea.conferences.business.service.db.InvoiceService;
import cz.concrea.conferences.business.service.db.UserService;

@Controller
@RequestMapping(value = "/admin/")
public class AdminConferencesController {

	@Autowired
	ConferenceService confService;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "conferences")
	public String showConfs(Model model) {
		List<Conference> conferences = confService.findAllRecentConferences();
		Collections.sort(conferences, new ConfComparatorByDateAsc());
		model.addAttribute("conferences", conferences);
		model.addAttribute("users", userService);
		model.addAttribute("invoices", invoiceService);
		return "admin/conferences";
	}

	class ConfComparatorByDateAsc implements Comparator<Conference>{

		@Override
		public int compare(Conference arg0, Conference arg1) {
			if(arg0.getConfdate().after(arg1.getConfdate())) return 1;
			else if(arg1.getConfdate().after(arg0.getConfdate())) return -1;
			return 0;
		}
		
	}
	
}
