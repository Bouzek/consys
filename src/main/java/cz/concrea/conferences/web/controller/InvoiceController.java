package cz.concrea.conferences.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cz.concrea.conferences.business.dao.entity.Invoice;
import cz.concrea.conferences.business.service.CryptLongParameterService;
import cz.concrea.conferences.business.service.InvoicePdfService;
import cz.concrea.conferences.business.service.db.InvoiceService;

@Controller
@RequestMapping(value = "/confs/{conference}/")
public class InvoiceController {

	@Autowired
	InvoicePdfService invoicePdfService;

	@Autowired
	CryptLongParameterService cryptService;

	@Autowired
	InvoiceService invoiceService;

	@RequestMapping(value = "invoice", method = RequestMethod.GET)
	public String showInvoice(Model model, @PathVariable(value = "conference") String conferenceCode,
			@RequestParam String inv) {

		long invoiceId;
		try {
			invoiceId = cryptService.decryptLong(inv);
		} catch (Exception e) {
			e.printStackTrace();
			return "notfound";
		}

		Invoice invoice = invoiceService.getInvoice(invoiceId, conferenceCode);
		if (invoice == null)
			return "notfound";
		model.addAttribute("invoice", invoice);
		return "invoices/invoiceNew";
	}

	@RequestMapping(value = "invoice.pdf", method = RequestMethod.GET)
	public void downloadInvoice(Model model, @PathVariable(value = "conference") String conferenceCode,
			@RequestParam String inv, HttpServletResponse response) {

		try {
			invoicePdfService.createInvoicePdf("invoice.pdf",
					"http://sys.concrea.cz/confs/" + conferenceCode + "/invoice?inv=" + inv, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
