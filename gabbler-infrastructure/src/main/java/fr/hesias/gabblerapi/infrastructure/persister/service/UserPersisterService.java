package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainUser;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.UserDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.INTERNAL_ERROR;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Slf4j
public class UserPersisterService
{

    private final UserDao userDao;

    public UserPersisterService(final UserDao userDao)
    {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    public DomainUsersResult getUsers()
    {
        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        final List<DomainUserResult> users = new ArrayList<>();
        try
        {
            final List<User> userList = userDao.getUsers();
            if(isNotEmpty(userList))
            {
                for(final User user : userList)
                {

                    users.add(new DomainUserResult(
                                      domainAccessStatus,
                                      new DomainUser(user.getId(),
                                                     user.getFirstname(),
                                                     user.getLastname(),
                                                     user.getUsername(),
                                                     user.getEmail(),
                                                     user.getBiography(),
                                                     user.getBirthday().toString())
                              )
                             );
                }
            }

        }
        catch(final Exception e)
        {
            log.error("[NA] Erreur survenue lors de la récupération des utilisateurs", e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainUsersResult(domainAccessStatus, users);
    }

    @Transactional(readOnly = true)
    public DomainUserResult getUserById(final int id)
    {
        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        DomainUser user = new DomainUser();

        try
        {
            final User daoUser = userDao.getUserById(id);

            user = new DomainUser(daoUser.getId(),
                                  daoUser.getFirstname(),
                                  daoUser.getLastname(),
                                  daoUser.getUsername(),
                                  daoUser.getEmail(),
                                  daoUser.getBiography(),
                                  daoUser.getBirthday().toString());

        }
        catch(final Exception e)
        {
            log.error("[{}] Erreur survenue lors de la récupération d'un utlisateur à partir d'un id", id, e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainUserResult(domainAccessStatus, user);
    }

}
