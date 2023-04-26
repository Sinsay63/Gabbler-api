package fr.hesias.gabblerapi.domain.port.primary;

import fr.hesias.gabblerapi.domain.result.DomainUserInfosAuthResult;
import fr.hesias.gabblerapi.domain.result.DomainUserRegistrationInfosResult;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;

public interface UserInfosAccessor
{

    /**
     * Récupère la liste des utilisateurs
     *
     * @return la liste des utilisateurs
     */
    DomainUsersResult getUsers();

    DomainUsersResult getSuggestionsUserNotConnected();

    DomainUsersResult getSuggestionsUserConnected(String userUuid);

    DomainUserResult getUserByEmail(String email);

    DomainUserInfosAuthResult getUserCredentialByEmail(String email);

    DomainUserResult register(final DomainUserRegistrationInfosResult user);


}
