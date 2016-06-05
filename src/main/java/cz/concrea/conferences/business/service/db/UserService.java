package cz.concrea.conferences.business.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.dao.entity.User;
import cz.concrea.conferences.business.dao.entity.UserInfo;
import cz.concrea.conferences.business.dao.repository.UserInfoRepository;
import cz.concrea.conferences.business.dao.repository.UserRepository;
import cz.concrea.conferences.business.model.form.PersonalDataForm;
import cz.concrea.conferences.business.service.DateService;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository; 
	@Autowired
    private UserInfoRepository userInfoRepository; 
	@Autowired
	private DateService dateService;
	@Autowired
	private ConferenceService confService;

	
	public User findUserByEmail(String email, Conference conference) {
		List<User> user = userRepository.findByEmailAndConference(email, conference);
		if(user == null || user.isEmpty()){
			return null;
		}
		if(user.size() > 1){
			System.out.println("ZDE8926 - "+conference.getCodeName() + " - " + email);
		}
        return user.get(0);
    }
	
	public User CreateUser(PersonalDataForm personalData, Conference conference){
		
		UserInfo userInfo = new UserInfo(personalData.getFirstName(), 
				personalData.getSurname(), 
				personalData.getDegree(), 
				"hybrid", 
				personalData.getUniversity(), 
				personalData.getDivision(), 
				personalData.getCountry(), 
				personalData.getCity(), 
				personalData.getStreet(), 
				personalData.getZip());

		userInfoRepository.save(userInfo);
		
		User user = new User(conference, 
				personalData.getEmail(), 
				"aiopasswordunused", //not really used - na testovaci ucely
				userInfo);
		userInfo.setUser(user);
		userRepository.save(user);
		
		return user;
	}
	
	public boolean isExistingEmail(String email, Conference conference){
		List<User> usersWithThisMail = userRepository.findByEmailAndConference(email, conference);
		if(usersWithThisMail != null && usersWithThisMail.size() > 0)
				return true;
		return false;
	}
	
	public List<User> getAllUsers(Conference conference){
		return userRepository.findByConference(conference);
	}
	
	public int GetUserCount(Conference conference){
		return userRepository.findByConference(conference).size();
	}
	
	public int GetUserCount(int conferenceId){
		return GetUserCount(confService.findConferenceById(conferenceId));
	}
	
	public int GetUserCountToday(Conference conference){
		int count = 0;
		for (User usr : userRepository.findByConference(conference)){
			if(dateService.isToday(usr.getSignedOn())) count++;
		}
		return count;
	}
	public int GetUserCountToday(int conferenceID){
		return GetUserCountToday(confService.findConferenceById(conferenceID));
	}
	
	public User getUser(long id){
		return userRepository.findOne(id);
	}
	
	
}