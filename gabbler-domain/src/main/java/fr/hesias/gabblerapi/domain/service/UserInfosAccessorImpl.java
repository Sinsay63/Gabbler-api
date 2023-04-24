package fr.hesias.gabblerapi.domain.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainUser;
import fr.hesias.gabblerapi.domain.port.primary.UserInfosAccessor;
import fr.hesias.gabblerapi.domain.port.secondary.UserPersister;
import fr.hesias.gabblerapi.domain.result.*;

import java.util.List;

public class UserInfosAccessorImpl implements UserInfosAccessor {

    private final UserPersister userPersister;

    public UserInfosAccessorImpl(final UserPersister userPersister) {

        super();
        this.userPersister = userPersister;
    }

    @Override
    public DomainUsersInfosResult getUsers() {

        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        List<DomainUserInfosResult> domainUserInfosResults = null;
        DomainUsersInfosResult domainUsersInfosResult = userPersister.getUsers();

        if (domainUsersInfosResult.isOk()) {

            domainUserInfosResults = domainUsersInfosResult.getDomainUserInfosResults();
        } else {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }

        return new DomainUsersInfosResult(domainAccessStatus, domainUserInfosResults);
    }

    @Override
    public DomainUserResult getUserByEmail(String email) {

        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        DomainUser domainUser = null;
        DomainUserResult domainUserResult = userPersister.getUserByEmail(email);

        if (domainUserResult.isOk()) {

            domainUser = domainUserResult.getDomainUser();
        } else {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }

        return new DomainUserResult(domainAccessStatus, domainUser);
    }

    @Override
    public DomainUserInfosAuthResult getUserCredentialByEmail(String email) {

        DomainUserInfosAuthResult domainUserAuth = userPersister.getUserCredentialByEmail(email);

        if (!domainUserAuth.isOk()) {

            domainUserAuth = new DomainUserInfosAuthResult(DomainAccessStatus.BAD_REQUEST, null);
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
