package cz.concrea.conferences.business.model.form;

import java.util.ArrayList;
import java.util.List;

import cz.concrea.conferences.business.dao.entity.InvoiceItem;
import cz.concrea.conferences.business.dao.entity.UserRegistration;
import cz.concrea.conferences.business.dao.entity.UserRegistrationInfo;

public class FormSelector extends FormField{
	
	private List<Selection> selections;
	private long selectedId;
	
	
	public FormSelector() {
	}

	public List<Selection> getSelections() {
		return selections;
	}

	public void AddSelection(long id, String text, int price) {
		this.selections.add(new Selection(id, text, price));
	}

	
	public FormSelector(long id, String header, String text, String invoiceName, String desc) {
		super(id, header, text, invoiceName, desc);
		this.selections = new ArrayList<Selection>();
	}
	
	
	public class Selection {
		private long id;
		private String text;
		private int price;

		public Selection(long id, String text, int price) {
			this.id = id;
			this.text = text;
			this.price = price;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		@Override
		public String toString() {
			return "Selection [id=" + id + ", text=" + text + ", price=" + price + "]";
		}

	}

	public void setSelections(List<Selection> selections) {
		this.selections = selections;
	}

	public long getSelectedId() {
		return selectedId;
	}

	public int getSelectedPrice() {
		if (selections == null || selections.size() < 1)
			return 0;
	
		for (Selection selection : selections) {
			if (selection.getId() == selectedId)
				return selection.getPrice();
		}

		return selections.get(0).getPrice();
	}

	public void setSelectedId(long selectedId) {
		this.selectedId = selectedId;
	}
	
	public Selection getSelectedSelection(){
		for (Selection selection : selections) {
			if (selection.getId() == selectedId)
				return selection;
		}
		System.out.println("ZDE89999 - " + getHeader());
		return selections.get(0);
	}

	@Override
	public boolean isBeingPaid() {
		return true;
	}

	@Override
	public InvoiceItem getInvoiceItem( long invoiceId) {
		Selection selected = getSelectedSelection();
		return new InvoiceItem( invoiceId , getInvoiceName() + " - " + selected.getText(), selected.getPrice());
	}

	@Override
	public String getFieldValue() {
		return getSelectedSelection().getText();
	}

	@Override
	public String getType() {
		return "selector";
	}
	
	@Override
	public UserRegistrationInfo getInfo(UserRegistration registration) {
		return new UserRegistrationInfo(getLongDescription(), getFieldValue(), registration);
	}


}
