package cz.concrea.conferences.business.service.db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.dao.repository.ConferenceRepository;
import cz.concrea.conferences.business.model.form.admin.NewConferenceForm;
import cz.concrea.conferences.business.service.DateService;
import cz.concrea.conferences.config.ConferenceType;

@Service
public class ConferenceService {
	@Autowired
	private ConferenceRepository conferenceRepository;
	@Autowired
	private DateService dateService;

	public Conference findConferenceByCodename(String codename) {
		return conferenceRepository.findByCodeName(codename);
	}

	public Conference findConferenceById(long id) {
		return conferenceRepository.findOne(id);
	}

	public Iterable<Conference> findAllConferences() {
		return conferenceRepository.findAll();
	}

	public List<Conference> findAllRecentConferences() {
		List<Conference> conferences = new ArrayList<Conference>();
		for (Conference conf : conferenceRepository.findAll()) {
			if (conf.getConfdate() != null && conf.getConfdate().after(Calendar.getInstance().getTime())) {
				conferences.add(conf);
			}
		}

		return conferences;
	}

	public List<Conference> findAllLegacyConferences() {
		List<Conference> conferences = new ArrayList<Conference>();
		for (Conference conf : conferenceRepository.findAll()) {
			if (conf.getConfdate() == null || !conf.getConfdate().after(Calendar.getInstance().getTime())) {
				conferences.add(conf);
			}
		}
		return conferences;
	}

	public void createConference(NewConferenceForm newConf) {
		ConferenceType type = ConferenceType.NORMAL;

		for (ConferenceType tp : ConferenceType.values()) {
			if (tp.name().equals(newConf.getConfType()))
				type = tp;
		}
		Conference conference = new Conference(newConf.getCodeName(), newConf.getName(), type,
				new Timestamp(dateService.dateStringToDate(newConf.getConfDate()).getTimeInMillis()));
		conferenceRepository.save(conference);
	}

	public void deleteConference(long id) {
		conferenceRepository.delete(id);
	}

	public boolean isDuplicate(String codename) {
		if (findConferenceByCodename(codename) != null)
			return true;
		return false;
	}

	public void updateConference(Conference conference) {
		conferenceRepository.save(conference);
	}

}