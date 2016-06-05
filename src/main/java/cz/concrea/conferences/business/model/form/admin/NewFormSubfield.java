package cz.concrea.conferences.business.model.form.admin;

import javax.validation.constraints.NotNull;

public class NewFormSubfield {
	
	private int price;
	
	@NotNull
	private String text;
	
	@NotNull
	private String description;
	
	private String invoiceDescription;
	
	
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getInvoiceDescription() {
		return invoiceDescription;
	}
	public void setInvoiceDescription(String invoiceDescription) {
		this.invoiceDescription = invoiceDescription;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
