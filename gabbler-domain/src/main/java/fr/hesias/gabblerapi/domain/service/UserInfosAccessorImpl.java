package fr.hesias.gabblerapi.domain.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainUser;
import fr.hesias.gabblerapi.domain.model.DomainUserProfile;
import fr.hesias.gabblerapi.domain.port.primary.UserInfosAccessor;
import fr.hesias.gabblerapi.domain.port.secondary.UserPersister;
import fr.hesias.gabblerapi.domain.result.*;

import java.util.List;

public class UserInfosAccessorImpl implements UserInfosAccessor
{

    private final UserPersister userPersister;

    public UserInfosAccessorImpl(final UserPersister userPersister)
    {

        super();
        this.userPersister = userPersister;
    }

    @Override
    public DomainUsersResult getUsers()
    {

        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        List<DomainUserResult> domainUserResults = null;
        DomainUsersResult domainUsersResult = userPersister.getUsers();

        if (domainUsersResult.isOk())
        {

            domainUserResults = domainUsersResult.getUsers();
        }
        else
        {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }

        return new DomainUsersResult(domainAccessStatus, domainUserResults);
    }

    @Override
    public DomainUserResult getUserByUuid(String userUuid)
    {

        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        DomainUser domainUser = null;
        DomainUserResult domainUserResult = userPersister.getUserByUuid(userUuid);

        if (domainUserResult.isOk())
        {

            domainUser = domainUserResult.getDomainUser();
        }
        else
        {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }

        return new DomainUserResult(domainAccessStatus, domainUser);
    }


    @Override
    public DomainUserResult getUserByEmail(String email)
    {

        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        DomainUser domainUser = null;
        DomainUserResult domainUserResult = userPersister.getUserByEmail(email);

        if (domainUserResult.isOk())
        {

            domainUser = domainUserResult.getDomainUser();
        }
        else
        {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }

        return new DomainUserResult(domainAccessStatus, domainUser);
    }

    @Override
    public DomainUserInfosAuthResult getUserCredentialByEmail(String email)
    {

        DomainUserInfosAuthResult domainUserAuth = userPersister.getUserCredentialByEmail(email);

        if (!domainUserAuth.isOk())
        {

            domainUserAuth.updateAccessStatus(DomainAccessStatus.BAD_REQUEST);
        }

        return domainUserAuth;
    }

    @Override
    public DomainUserResult register(DomainUserRegistrationInfosResult user)
    {

        DomainUserResult domainUserResult = userPersister.register(user);

        if (domainUserResult.getDomainAccessStatus().isOk())
        {

            return domainUserResult;
        }
        else
        {
            return new DomainUserResult(DomainAccessStatus.BAD_REQUEST);
        }

    }

    @Override
    public DomainUsersResult getSuggestionsUserNotConnected()
    {

        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        List<DomainUserResult> domainUserResults = null;
        DomainUsersResult domainUsersResult = userPersister.getSuggestionsUserNotConnected();

        if (domainUsersResult.isOk())
        {

            domainUserResults = domainUsersResult.getUsers();
        }
        else
        {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }

        return new DomainUsersResult(domainAccessStatus, domainUserResults);

    }

    @Override
    public DomainUsersResult getSuggestionsUserConnected(String userUuid)
    {

        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        List<DomainUserResult> domainUserResults = null;
        DomainUsersResult domainUsersResult = userPersister.getSuggestionsUserConnected(userUuid);

        if (domainUsersResult.isOk())
        {

            domainUserResults = domainUsersResult.getUsers();
        }
        else
        {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }

        return new DomainUsersResult(domainAccessStatus, domainUserResults);
    }

    @Override
    public DomainUserProfileResult getUserProfile(String userUuid)
    {

        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        DomainUserProfile domainUserProfile = null;
        DomainUserProfileResult domainUserProfileResult = userPersister.getUserProfile(userUuid);

        if (domainUserProfileResult.isOk())
        {

            domainUserProfile = domainUserProfileResult.getDomainUserProfile();
        }
        else
        {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }
        return new DomainUserProfileResult(domainAccessStatus, domainUserProfile);
    }

}
