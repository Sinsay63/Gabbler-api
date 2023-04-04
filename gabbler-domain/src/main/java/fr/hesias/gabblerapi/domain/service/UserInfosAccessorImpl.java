package fr.hesias.gabblerapi.domain.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainUser;
import fr.hesias.gabblerapi.domain.port.primary.UserInfosAccessor;
import fr.hesias.gabblerapi.domain.port.secondary.UserPersister;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;

import java.util.List;
import java.util.UUID;

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

        if (userPersister.getUsers() != null) {

            domainUsersResults = userPersister.getUsers().getUsers();
        }
        else {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }

        return new DomainUsersResult(domainAccessStatus, domainUsersResults);
    }


}
