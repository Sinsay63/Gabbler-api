package fr.hesias.gabblerapi.domain.port.primary;

import fr.hesias.gabblerapi.domain.result.*;

public interface UserInfosAccessor
{

    /**
     * Récupère la liste des utilisateurs
     *
     * @return la liste des utilisateurs
     */
    DomainUsersResult getUsers();

    DomainUserResult getUserByUuid(String userUuid);

    DomainUsersResult getSuggestionsUserNotConnected();

    DomainUsersResult getSuggestionsUserConnected(String userUuid);

    DomainUserResult getUserByEmail(String email);

    DomainUserInfosAuthResult getUserCredentialByEmail(String email);

    DomainUserResult register(final DomainUserRegistrationInfosResult user);


    DomainUserProfileResult getUserProfile(String userUuid);

    void confirmEmailByUserUuid(String userUuid);

}
