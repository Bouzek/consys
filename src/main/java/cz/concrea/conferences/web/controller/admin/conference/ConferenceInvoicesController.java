package cz.concrea.conferences.web.controller.admin.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.service.db.ConferenceService;
import cz.concrea.conferences.business.service.db.InvoiceService;

@Controller
@RequestMapping(value = "/admin/{conferenceCode}/")
public class ConferenceInvoicesController {
	
	@Autowired
	ConferenceService confService;
	@Autowired
	InvoiceService invoiceService;
	
	@RequestMapping(value = "invoices")
	public String showOverview(Model model, @PathVariable(value = "conferenceCode") String conferenceCode) {
		Conference conference = confService.findConferenceByCodename(conferenceCode);
		model.addAttribute("conference", conference);
		model.addAttribute("isInvoices", true);
		model.addAttribute("invoices", invoiceService.getAllInvoices(conference));
		return "admin/conference/conferenceInvoices";
	}
	
	
}
