package cz.concrea.conferences.business.dao.repository;

import org.springframework.data.repository.CrudRepository;

import cz.concrea.conferences.business.dao.entity.User;
import cz.concrea.conferences.business.dao.entity.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
	
	UserInfo findByUser(User user);
}