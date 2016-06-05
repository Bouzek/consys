package cz.concrea.conferences.business.dao.repository;

import org.springframework.data.repository.CrudRepository;

import cz.concrea.conferences.business.dao.entity.UserRegistrationInfo;

public interface UserRegistrationInfoRepository extends CrudRepository<UserRegistrationInfo, Long> {

}
