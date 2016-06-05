package cz.concrea.conferences.business.model.form.admin;

import javax.validation.constraints.Size;

import cz.concrea.conferences.config.FormFieldType;

public class NewFormField {
	
	private String text;
	@Size(max = 256)
	private String header;
	@Size(min = 2, max = 1024)
	private String longDescription;
	@Size(max = 128)
	private String invoiceDescription;
	
	private FormFieldType type;
	
	private int price;
	private int size;
	
	
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
	public String getInvoiceDescription() {
		return invoiceDescription;
	}
	public void setInvoiceDescription(String invoiceDescription) {
		this.invoiceDescription = invoiceDescription;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public FormFieldType getType() {
		return type;
	}
	public void setType(FormFieldType type) {
		this.type = type;
	}
	
}
