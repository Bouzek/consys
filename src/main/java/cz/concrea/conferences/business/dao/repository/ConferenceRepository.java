package cz.concrea.conferences.business.dao.repository;

import org.springframework.data.repository.CrudRepository;

import cz.concrea.conferences.business.dao.entity.Conference;

public interface ConferenceRepository extends CrudRepository<Conference, Long> {	
	Conference findByCodeName(String codeName);
}