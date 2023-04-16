package fr.hesias.gabblerapi.domain.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainUser;
import fr.hesias.gabblerapi.domain.port.primary.UserInfosAccessor;
import fr.hesias.gabblerapi.domain.port.secondary.UserPersister;
import fr.hesias.gabblerapi.domain.result.DomainUserInfosAuthResult;
import fr.hesias.gabblerapi.domain.result.DomainUserRegistrationInfosResult;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;

import java.util.List;

public class UserInfosAccessorImpl implements UserInfosAccessor {

    private final UserPersister userPersister;

    public UserInfosAccessorImpl(final UserPersister userPersister) {

        super();
        this.userPersister = userPersister;
    }

    @Override
    public DomainUsersResult getUsers() {

        List<DomainUserResult> domainUsersResults = null;
        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;

        if (userPersister.getUsers().isOk()) {

            domainUsersResults = userPersister.getUsers().getUsers();
        } else {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }

        return new DomainUsersResult(domainAccessStatus, domainUsersResults);
    }

    @Override
    public DomainUserResult getUserByEmail(String email) {

        DomainUser domainUser = null;
        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;

        if (userPersister.getUsers().isOk()) {

            domainUser = userPersister.getUserByEmail(email).getDomainUser();
        } else {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }

        return new DomainUserResult(domainAccessStatus, domainUser);
    }

    @Override
    public DomainUserInfosAuthResult getUserCredentialByEmail(String email) {

        DomainUserInfosAuthResult domainUserAuth = null;

        if (userPersister.getUserCredentialByEmail(email).isOk()) {

            domainUserAuth = userPersister.getUserCredentialByEmail(email);
        }

        return domainUserAuth;
    }

    @Override
    public DomainUserResult register(DomainUserRegistrationInfosResult user) {

        DomainUserResult domainUserResult = userPersister.register(user);
        String test = "test";

        if (domainUserResult.getDomainAccessStatus().isOk()) {

            return domainUserResult;
        } else {
            return new DomainUserResult(DomainAccessStatus.BAD_REQUEST);
        }

    }
}
