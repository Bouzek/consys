package cz.concrea.conferences.business.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.dao.entity.Invoice;
import cz.concrea.conferences.config.InvoiceType;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

	public List<Invoice> findInvoiceByConferenceAndTypeOrderByNumberDesc(Conference conference, InvoiceType type);
	public List<Invoice> findInvoiceByConference(Conference conference);
	
}
