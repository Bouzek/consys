package cz.concrea.conferences.business.model.form;

import cz.concrea.conferences.business.dao.entity.InvoiceItem;
import cz.concrea.conferences.business.dao.entity.UserRegistration;
import cz.concrea.conferences.business.dao.entity.UserRegistrationInfo;

public class FormCheckBox extends FormField{
	
	private boolean checked;
	private int price;
	
	
	public FormCheckBox(long id, String header, String text, String invoiceName, String desc, int price, boolean checked) {
		super(id, header, text, invoiceName, desc);
		this.checked = checked;
		this.price = price;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSelectedPrice(){
		if(checked) return price;
		return 0;
	}
	
	@Override
	public boolean isBeingPaid() {
		if( price > 0 && checked ) return true;
		return false;
	}

	@Override
	public InvoiceItem getInvoiceItem( long invoiceId ) {
		return new InvoiceItem( invoiceId, getInvoiceName(), price);
	}

	@Override
	public String getFieldValue() {
		if(checked) return "Yes";
		return "No";
	}

	@Override
	public String getType() {
		return "checkbox";
	}

	@Override
	public UserRegistrationInfo getInfo(UserRegistration registration) {
		return new UserRegistrationInfo(getLongDescription(), getFieldValue(), registration);
	}
	
	
	
}
