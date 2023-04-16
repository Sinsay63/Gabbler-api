package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainUser;
import fr.hesias.gabblerapi.domain.model.DomainUserAuth;
import fr.hesias.gabblerapi.domain.result.DomainUserInfosAuthResult;
import fr.hesias.gabblerapi.domain.result.DomainUserRegistrationInfosResult;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.UserDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.INTERNAL_ERROR;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Slf4j
public class UserPersisterService {

    private final UserDao userDao;

    private final GabblerInfraMapper gabblerInfraMapper;

    public UserPersisterService(final UserDao userDao, final GabblerInfraMapper gabblerInfraMapper) {

        this.userDao = userDao;
        this.gabblerInfraMapper = gabblerInfraMapper;
    }

    @Transactional(readOnly = true)
    public DomainUsersResult getUsers() {

        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        final List<DomainUserResult> users = new ArrayList<>();
        try {
            final List<User> userList = userDao.getUsers();
            if (isNotEmpty(userList)) {
                for (final User user : userList) {
                    users.add(gabblerInfraMapper.toDomainUserToDomainUserResult(domainAccessStatus,
                            gabblerInfraMapper.toUserToDomainUser(
                                    user)));
                }
            }

        } catch (final Exception e) {
            log.error("[NA] Erreur survenue lors de la récupération des utilisateurs", e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainUsersResult(domainAccessStatus, users);
    }

    @Transactional(readOnly = true)
    public DomainUserResult getUserById(final int id) {

        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        DomainUser user = new DomainUser();

        try {
            final User daoUser = userDao.getUserById(id).orElseThrow(() -> new Exception("Utilisateur non trouvé"));

            user = this.gabblerInfraMapper.toUserToDomainUser(daoUser);
        } catch (final Exception e) {
            log.error("[{}] Erreur survenue lors de la récupération d'un utlisateur à partir de son id", id, e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainUserResult(domainAccessStatus, user);
    }

    @Transactional(rollbackFor = Exception.class)
    public DomainUserResult register(final DomainUserRegistrationInfosResult user) {

        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        User newUser = new User();

        try {
            User usr = this.gabblerInfraMapper.toDomainUserRegistrationInfosResultToUser(user);
            newUser = userDao.addUser(usr);
        } catch (final Exception e) {
            log.error("[NA] Erreur survenue lors de la création de l'utilisateur {}", user.getUserRegistration().getEmail(), e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainUserResult(domainAccessStatus, this.gabblerInfraMapper.toUserToDomainUser(newUser));
    }

    @Transactional(readOnly = true)
    public DomainUserResult getUserByEmail(final String email) {

        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        DomainUser user = new DomainUser();

        try {
            final User daoUser = userDao.getUserByEmail(email)
                    .orElseThrow(() -> new Exception("Utilisateur non trouvé"));

            user = this.gabblerInfraMapper.toUserToDomainUser(daoUser);
        } catch (final Exception e) {
            log.error("[{}] Erreur survenue lors de la récupération d'un utlisateur à partir de son email", email, e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainUserResult(domainAccessStatus, user);
    }

    @Transactional(readOnly = true)
    public DomainUserInfosAuthResult getUserCredentialByEmail(final String email) {

        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        DomainUserAuth user = new DomainUserAuth();

        try {
            final User daoUser = userDao.getUserByEmail(email)
                    .orElseThrow(() -> new Exception("Utilisateur non trouvé"));

            user = this.gabblerInfraMapper.toUserToDomainUserAuth(daoUser);
        } catch (final Exception e) {
            log.error("[{}] Erreur survenue lors de la récupération d'un utlisateur à partir de son email", email, e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainUserInfosAuthResult(domainAccessStatus, user);
    }


}
