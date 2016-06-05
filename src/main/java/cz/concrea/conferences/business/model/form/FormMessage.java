package cz.concrea.conferences.business.model.form;

import cz.concrea.conferences.business.dao.entity.InvoiceItem;
import cz.concrea.conferences.business.dao.entity.UserRegistration;
import cz.concrea.conferences.business.dao.entity.UserRegistrationInfo;

public class FormMessage extends FormField{
	private int rows;
	private String message;
	
	
	public FormMessage(long id, String header, String text, String invoiceName, String desc, int rows) {
		super(id, header, text, invoiceName, desc);
		this.rows = rows;
	}
	
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public boolean isBeingPaid() {
		return false;
	}


	@Override
	public InvoiceItem getInvoiceItem( long invoiceId) {
		return null;
	}


	@Override
	public String getFieldValue() {
		return message;
	}
	
	@Override
	public String getType() {
		return "message";
	}
	@Override
	public UserRegistrationInfo getInfo(UserRegistration registration) {
		return new UserRegistrationInfo(getLongDescription(), getFieldValue(), registration);
	}
	
}
