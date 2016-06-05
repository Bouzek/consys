package cz.concrea.conferences.business.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.dao.entity.User;


public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findByEmailAndConference(String email, Conference string);
	List<User> findByConference(Conference conference);
}
