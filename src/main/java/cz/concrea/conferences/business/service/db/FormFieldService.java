package cz.concrea.conferences.business.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.dao.entity.settings.registration.FormFieldEntity;
import cz.concrea.conferences.business.dao.entity.settings.registration.FormSubFieldEntity;
import cz.concrea.conferences.business.dao.repository.FormFieldEntityRepository;
import cz.concrea.conferences.business.dao.repository.FormSubFieldEntityRepository;
import cz.concrea.conferences.business.model.form.admin.NewFormField;
import cz.concrea.conferences.business.model.form.admin.NewFormSubfield;

@Service
public class FormFieldService {

	@Autowired
	FormFieldEntityRepository fieldRepo;
	@Autowired
	FormSubFieldEntityRepository subfieldRepo;

	public FormFieldEntity addFormField(NewFormField field) {
		FormFieldEntity fieldEntity = new FormFieldEntity(field.getText(), field.getHeader(), field.getPrice(),
				field.getLongDescription(), field.getInvoiceDescription(), field.getSize(), field.getType());
		fieldRepo.save(fieldEntity);
		return fieldEntity;
	}

	public void switchFields(long id1, long id2, Conference conference) {
		FormFieldEntity temp1 = fieldRepo.findOne(id1);
		FormFieldEntity temp2 = fieldRepo.findOne(id2);

		FormFieldEntity temp3 = new FormFieldEntity();
		temp3.copy(temp1);
		temp1.copy(temp2);
		temp2.copy(temp3);

		fieldRepo.save(temp1);
		fieldRepo.save(temp2);
	}

	public void deleteField(long id) {
		fieldRepo.delete(id);
	}

	public void addSubfield(long fieldId, NewFormSubfield newsub) {
		FormFieldEntity field = fieldRepo.findOne(fieldId);
		field.getSubfields().add(new FormSubFieldEntity(fieldId, newsub.getPrice(), newsub.getText(),
				newsub.getInvoiceDescription(), newsub.getDescription()));
		fieldRepo.save(field);
	}
	
	public void moveSubField(long subfieldId,int shift){
		long fieldId = subfieldRepo.findOne(subfieldId).getFieldId();
		FormFieldEntity field = fieldRepo.findOne(fieldId);
		
		int index = -1;
		int i = 0;
		for ( FormSubFieldEntity subfield : field.getSubfields()){
			if(subfield.getId() == subfieldId){
				index = i;
				break;
			}
			++i;
		}
		if(index == -1) throw new IllegalArgumentException("Invalid subfieldid");
		
		FormSubFieldEntity temp = new FormSubFieldEntity();
		temp.copy(field.getSubfields().get(index));
		field.getSubfields().get(index).copy(field.getSubfields().get(index + shift));
		field.getSubfields().get(index + shift).copy(temp);
		fieldRepo.save(field);
	}
	
	public void deleteSubField(long id){
		subfieldRepo.delete(id);
	}
}
