package cz.concrea.conferences.business.dao.repository;

import org.springframework.data.repository.CrudRepository;

import cz.concrea.conferences.business.dao.entity.InvoiceItem;

public interface InvoiceItemRepository extends CrudRepository<InvoiceItem, Long> {

}
