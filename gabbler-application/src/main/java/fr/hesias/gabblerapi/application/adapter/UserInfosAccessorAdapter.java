package fr.hesias.gabblerapi.application.adapter;

import fr.hesias.gabblerapi.domain.port.primary.UserInfosAccessor;
import fr.hesias.gabblerapi.domain.result.DomainEditUserProfileResult;
import fr.hesias.gabblerapi.domain.result.DomainUserProfileResult;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;


public class UserInfosAccessorAdapter
{

    private final UserInfosAccessor userInfosAccessor;

    public UserInfosAccessorAdapter(final UserInfosAccessor userInfosAccessor)
    {

        super();
        this.userInfosAccessor = userInfosAccessor;
    }

    public DomainUsersResult getUsers()
    {

        return userInfosAccessor.getUsers();
    }

    public DomainUserResult getUserByUuid(String userUuid)
    {

        return userInfosAccessor.getUserByUuid(userUuid);
    }

    public DomainUserResult getUserByEmail(String email)
    {

        return userInfosAccessor.getUserByEmail(email);
    }

    public DomainUsersResult getSuggestionsUserNotConnected()
    {

        return userInfosAccessor.getSuggestionsUserNotConnected();
    }

    public DomainUsersResult getSuggestionsUserConnected(String userUuid)
    {

        return userInfosAccessor.getSuggestionsUserConnected(userUuid);
    }

    public DomainUserProfileResult getUserProfile(String userUuid)
    {

        return userInfosAccessor.getUserProfile(userUuid);
    }

    public void confirmEmailByUserUuid(String userUuid)
    {

        userInfosAccessor.confirmEmailByUserUuid(userUuid);
    }

    public DomainEditUserProfileResult editUserProfile(DomainEditUserProfileResult domainEditUserProfileResult)
    {

        return userInfosAccessor.editUserProfile(domainEditUserProfileResult);
    }

}
