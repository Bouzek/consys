package cz.concrea.conferences.business.dao.repository;

import org.springframework.data.repository.CrudRepository;

import cz.concrea.conferences.business.dao.entity.settings.registration.FormFieldEntity;

public interface FormFieldEntityRepository extends CrudRepository<FormFieldEntity, Long> {

}
