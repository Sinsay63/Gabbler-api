package fr.hesias.gabblerapi.domain.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.port.primary.UserInfosAccessor;
import fr.hesias.gabblerapi.domain.port.secondary.UserPersister;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;

import java.util.List;

public class UserInfosAccessorImpl implements UserInfosAccessor {


    private UserPersister userPersister;

    @Override
    public DomainUsersResult getUsers() {

        final DomainAccessStatus domainAccessStatus;
        List<DomainUserResult> domainUsersResults = null;

        if (userPersister.getUsers() != null) {
            domainAccessStatus = DomainAccessStatus.OK;
            domainUsersResults = userPersister.getUsers().getUsers();
        }
        else {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }

        return new DomainUsersResult(domainAccessStatus, domainUsersResults);
    }

}
