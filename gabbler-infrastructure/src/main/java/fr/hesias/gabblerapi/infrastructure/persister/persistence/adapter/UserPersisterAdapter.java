package fr.hesias.gabblerapi.infrastructure.persister.persistence.adapter;

import fr.hesias.gabblerapi.domain.port.secondary.UserPersister;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;
import fr.hesias.gabblerapi.infrastructure.persister.service.UserPersisterService;

import java.util.UUID;

public class UserPersisterAdapter implements UserPersister {

    private final UserPersisterService userPersisterService;

    public UserPersisterAdapter(final UserPersisterService userPersisterService) {
        this.userPersisterService = userPersisterService;
    }

    @Override
    public DomainUsersResult getUsers() {
        return userPersisterService.getUsers();
    }

    @Override
    public DomainUserResult getUserByUuid(final UUID uuid) {
        return userPersisterService.getUserByUuid(uuid);
    }
}
