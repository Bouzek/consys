package cz.concrea.conferences.business.model.form;

import cz.concrea.conferences.business.dao.entity.InvoiceItem;
import cz.concrea.conferences.business.dao.entity.UserRegistration;
import cz.concrea.conferences.business.dao.entity.UserRegistrationInfo;

public abstract class FormField  {
	private long id;
	private String header;
	private String text;
	private String invoiceName;
	private String longDescription;
	
	public FormField() {
	}

	public FormField(long id, String header, String text, String invoiceName, String longDescription) {
		this.id = id;
		this.header = header;
		this.text = text;
		this.invoiceName = invoiceName;
		this.longDescription = longDescription;
	}
	
	public abstract boolean isBeingPaid();
	public abstract InvoiceItem getInvoiceItem( long invoiceId );
	public abstract String getFieldValue();
	public abstract String getType();
	public abstract UserRegistrationInfo getInfo(UserRegistration registration);
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	
	public boolean isHeader(){
		if (header != null && header.length() > 1) return true;
		return false;
	}
}
