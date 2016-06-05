package cz.concrea.conferences.business.dao.repository;

import org.springframework.data.repository.CrudRepository;

import cz.concrea.conferences.business.dao.entity.User;
import cz.concrea.conferences.business.dao.entity.UserRegistration;

public interface UserRegistrationRepository extends CrudRepository<UserRegistration, Long> {
	
	public UserRegistration findByUser(User user);

}
