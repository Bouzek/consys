package cz.concrea.conferences.business.model.form;

import java.util.ArrayList;
import java.util.List;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.dao.entity.InvoiceItem;
import cz.concrea.conferences.business.dao.entity.settings.registration.FormFieldEntity;
import cz.concrea.conferences.business.dao.entity.settings.registration.FormSubFieldEntity;

public class PaidForm {

	
	private List<FormField> fields;
	


	public PaidForm() {
	}

	public PaidForm(Conference conference, List<FormFieldEntity> fieldEntities) {
		this.fields = new ArrayList<FormField>();
		
		
		for ( FormFieldEntity fieldEntity : fieldEntities){
			//potreba predelat
			selectors.add(null);
			checkboxes.add(null);
			messages.add(null);
			//konec
			switch ( fieldEntity.getType() ){
			
				case CHECKBOX: addCheckbox(fieldEntity);break;
				
				case MESSAGE: addMessage(fieldEntity);break;
				
				case SELECTOR: addSelector(fieldEntity); break;
						
				default: throw new IllegalArgumentException("Unknown field type O.o - " + fieldEntity.getType());
			
			}
		}
	}

	
	private void addSelector(FormFieldEntity fieldEntity){
		FormSelector selector = new FormSelector(fieldEntity.getFieldid(), fieldEntity.getHeader(), fieldEntity.getText(), fieldEntity.getInvoiceDescription(), fieldEntity.getLongDescription());
		
		for(FormSubFieldEntity subField : fieldEntity.getSubfields()){
			selector.AddSelection(subField.getId(), subField.getText(), subField.getPrice());
		}
		
		fields.add(selector);
		selectors.set(selectors.size()-1, selector);
	}
	
	private void addCheckbox (FormFieldEntity fieldEntity){
		FormCheckBox box =  new FormCheckBox(fieldEntity.getFieldid(), fieldEntity.getHeader(), fieldEntity.getText(), fieldEntity.getInvoiceDescription(), fieldEntity.getLongDescription(), fieldEntity.getPrice(), false);
		fields.add(box);
		checkboxes.set(checkboxes.size()-1, box);
	}
	
	private void addMessage (FormFieldEntity fieldEntity){
		FormMessage msg =  new FormMessage(fieldEntity.getFieldid(), fieldEntity.getHeader(), fieldEntity.getText(), fieldEntity.getInvoiceDescription(), fieldEntity.getLongDescription(), fieldEntity.getSize());
		fields.add(msg);
		messages.set(messages.size()-1, msg);
	}

	public List<InvoiceItem> getPaidItems( long invoiceId ){
		List<InvoiceItem> items = new ArrayList<InvoiceItem>();
		
		for ( FormField field : fields ){
			if( field.isBeingPaid() ){
				items.add( field.getInvoiceItem( invoiceId ) );
			}
		}
		return items;
	}

	public List<FormField> getFields() {
		return fields;
	}
	
	
	
	//potreba predelat
	private List<FormSelector> selectors = new ArrayList<FormSelector>();
	private List<FormCheckBox> checkboxes = new ArrayList<FormCheckBox>();
	private List<FormMessage> messages = new ArrayList<FormMessage>();

	public List<FormSelector> getSelectors() {
		return selectors;
	}

	public void setSelectors(List<FormSelector> selectors) {
		this.selectors = selectors;
	}
	

	public List<FormCheckBox> getCheckboxes() {
		return checkboxes;
	}

	public void setCheckboxes(List<FormCheckBox> checkboxes) {
		this.checkboxes = checkboxes;
	}

	public List<FormMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<FormMessage> messages) {
		this.messages = messages;
	}
	
	//konec

	public void setFields(List<FormField> fields) {
		this.fields = fields;
	}
	
	
}
