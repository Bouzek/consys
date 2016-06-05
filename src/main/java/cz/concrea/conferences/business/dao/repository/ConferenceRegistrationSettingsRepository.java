package cz.concrea.conferences.business.dao.repository;

import org.springframework.data.repository.CrudRepository;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.dao.entity.settings.registration.RegistrationSettings;

public interface ConferenceRegistrationSettingsRepository extends CrudRepository<RegistrationSettings, Long>{
	public RegistrationSettings findByConference(Conference conference);
}
