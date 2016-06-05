package cz.concrea.conferences.business.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.dao.entity.Invoice;
import cz.concrea.conferences.business.dao.entity.InvoiceItem;
import cz.concrea.conferences.business.dao.entity.User;
import cz.concrea.conferences.business.dao.repository.ConferenceRepository;
import cz.concrea.conferences.business.dao.repository.InvoiceItemRepository;
import cz.concrea.conferences.business.dao.repository.InvoiceRepository;
import cz.concrea.conferences.business.model.form.BillingAddressForm;
import cz.concrea.conferences.business.model.form.PaidForm;
import cz.concrea.conferences.config.InvoiceType;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRepository invoiceRep;
	
	@Autowired
	ConferenceRepository confRep;

	@Autowired
	InvoiceItemRepository invoiceItemRep;

	public Invoice createInvoice(User user, PaidForm paidForm, BillingAddressForm billingAddress, InvoiceType type,
			Conference conference) {

		Invoice invoice = new Invoice(0, user, null, billingAddress.getName(), billingAddress.getStreet(),
				billingAddress.getCity(), billingAddress.getZip(), billingAddress.getCountry(), billingAddress.getIN(),
				billingAddress.getTIN(), type, conference, generateInvoiceNumber(conference, type), billingAddress.getInstitution());

		invoiceRep.save(invoice);

		List<InvoiceItem> items = paidForm.getPaidItems(invoice.getId());

		invoiceItemRep.save(items);

		invoice.setItems(items);

		return invoice;
	}

	public Invoice getInvoice(long id, String conf) {
		Invoice inv = invoiceRep.findOne(id);
		if (inv == null)
			return inv;
		if (!inv.getUser().getConference().getCodeName().equals(conf)) {
			throw new IllegalArgumentException("Hledani invoice - id: " + id + ", conf: " + conf);
		}

		return inv;
	}
	
	public List<Invoice> getAllInvoices(Conference conference){
		return invoiceRep.findInvoiceByConference(conference);
	}

	private int generateInvoiceNumber(Conference conference, InvoiceType type) {

		List<Invoice> invoices = invoiceRep.findInvoiceByConferenceAndTypeOrderByNumberDesc(conference, type);

		if (invoices == null || invoices.isEmpty())
			return 1;

		return invoices.get(0).getNumber() + 1;
	}
	
	public List<Invoice> GetAllConferenceInvoices(Conference conference){
		return invoiceRep.findInvoiceByConference(conference);
	}
	
	public List<Invoice> GetAllConferenceInvoices(long conferenceId){
		return invoiceRep.findInvoiceByConference(confRep.findOne(conferenceId));
	}
	
	public int GetConferenceInvoiceCount(long conferenceId){
		return GetAllConferenceInvoices(conferenceId).size();
	}
	
	
	
	
}
