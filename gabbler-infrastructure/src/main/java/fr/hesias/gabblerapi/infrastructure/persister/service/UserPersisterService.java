package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.UserDao;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserPersisterService {

    private final UserDao userDao;

    public UserPersisterService(final UserDao userDao) {
        this.userDao = userDao;
    }

//    @Transactional(readOnly = true)
    
}
