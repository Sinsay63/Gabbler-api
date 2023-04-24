package fr.hesias.gabblerapi.infrastructure.persister.persistence.adapter;

import fr.hesias.gabblerapi.domain.port.secondary.UserPersister;
import fr.hesias.gabblerapi.domain.result.*;
import fr.hesias.gabblerapi.infrastructure.persister.service.UserPersisterService;

public class UserPersisterAdapter implements UserPersister {

    private final UserPersisterService userPersisterService;

    public UserPersisterAdapter(final UserPersisterService userPersisterService) {

        this.userPersisterService = userPersisterService;
    }

    @Override
    public DomainUsersInfosResult getUsers() {

        return userPersisterService.getUsers();
    }

    @Override
    public DomainUserInfosResult getUserByUuid(final String uuid) {

        return userPersisterService.getUserByUuid(uuid);
    }

    @Override
    public DomainUserResult getUserByEmail(String email) {

        return userPersisterService.getUserByEmail(email);
    }

    @Override
    public DomainUserInfosAuthResult getUserCredentialByEmail(String email) {

        return userPersisterService.getUserCredentialByEmail(email);
    }

    @Override
    public DomainUserResult register(DomainUserRegistrationInfosResult user) {
        return userPersisterService.register(user);
    }
}
