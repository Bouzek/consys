package cz.concrea.conferences.business.dao.entity.settings.registration;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

import cz.concrea.conferences.config.FormFieldType;

@Entity
public class FormFieldEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long fieldid;

	@NotNull
	private String text;

	@Column
	private String header;

	@NotNull
	private int price;

	@Column
	private String longDescription;

	@Column
	private String invoiceDescription;

	@Column // message rows
	private int size;

	@Enumerated(EnumType.STRING)
	private FormFieldType type;

	@OneToMany(cascade = { CascadeType.ALL })
	@Cascade(org.hibernate.annotations.CascadeType.DELETE)
	@JoinColumn(name = "fieldid", referencedColumnName = "fieldid")
	private List<FormSubFieldEntity> subfields;

	public FormFieldEntity() {
	}

	public FormFieldEntity(String text, String header, int price, String longDescription, String invoiceDescription,
			int size, FormFieldType type) {
		this.text = text;
		this.header = header;
		this.price = price;
		this.longDescription = longDescription;
		this.invoiceDescription = invoiceDescription;
		this.size = size;
		this.type = type;
	}

	public void copy(FormFieldEntity from) {
		this.text = from.text;
		this.header = from.header;
		this.price = from.price;
		this.longDescription = from.longDescription;
		this.invoiceDescription = from.invoiceDescription;
		this.size = from.size;
		this.type = from.type;
		this.subfields = from.subfields;
	}

	public long getFieldid() {
		return fieldid;
	}

	public void setFieldid(long fieldid) {
		this.fieldid = fieldid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public FormFieldType getType() {
		return type;
	}

	public void setType(FormFieldType type) {
		this.type = type;
	}

	public List<FormSubFieldEntity> getSubfields() {
		return subfields;
	}

	public void setSubfields(List<FormSubFieldEntity> subfields) {
		this.subfields = subfields;
	}

}
