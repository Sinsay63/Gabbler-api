package fr.hesias.gabblerapi.infrastructure.persister.persistence.adapter;

import fr.hesias.gabblerapi.domain.port.secondary.UserPersister;
import fr.hesias.gabblerapi.domain.result.DomainUserInfosAuthResult;
import fr.hesias.gabblerapi.domain.result.DomainUserRegistrationInfosResult;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;
import fr.hesias.gabblerapi.infrastructure.persister.service.UserPersisterService;

public class UserPersisterAdapter implements UserPersister
{

    private final UserPersisterService userPersisterService;

    public UserPersisterAdapter(final UserPersisterService userPersisterService)
    {

        this.userPersisterService = userPersisterService;
    }

    @Override
    public DomainUsersResult getUsers()
    {

        return userPersisterService.getUsers();
    }

    @Override
    public DomainUserResult getUserByUuid(final String uuid)
    {

        return userPersisterService.getUserByUuid(uuid);
    }

    @Override
    public DomainUserResult getUserByEmail(String email)
    {

        return userPersisterService.getUserByEmail(email);
    }

    @Override
    public DomainUserInfosAuthResult getUserCredentialByEmail(String email)
    {

        return userPersisterService.getUserCredentialByEmail(email);
    }

    @Override
    public DomainUserResult register(DomainUserRegistrationInfosResult user)
    {

        return userPersisterService.register(user);
    }

    @Override
    public DomainUsersResult getSuggestionsUserNotConnected()
    {

        return userPersisterService.getSuggestionsUserNotConnected();
    }

    @Override
    public DomainUsersResult getSuggestionsUserConnected(String userUuid)
    {

        return userPersisterService.getSuggestionsUserConnected(userUuid);
    }

}
