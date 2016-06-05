package cz.concrea.conferences.business.dao.entity.settings.registration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class FormSubFieldEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "fieldid")
	private long fieldId;
	
	@NotNull
	private int price;
	
	@NotNull
	private String text;
	
	@Column
	private String invoiceDescription;
	
	@Column
	private String longDescription;

	
	
	
	
	
	public FormSubFieldEntity() {
	}

	public FormSubFieldEntity(long fieldId, int price, String text, String invoiceDescription, String longDescription) {
		this.fieldId = fieldId;
		this.price = price;
		this.text = text;
		this.invoiceDescription = invoiceDescription;
		this.longDescription = longDescription;
	}
	
	public void copy(FormSubFieldEntity from){
		this.fieldId = from.fieldId;
		this.price = from.price;
		this.text = from.text;
		this.invoiceDescription = from.invoiceDescription;
		this.longDescription = from.longDescription;
	}

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

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFieldId() {
		return fieldId;
	}

	public void setFieldId(long fieldId) {
		this.fieldId = fieldId;
	}
	
	
	
	
}
