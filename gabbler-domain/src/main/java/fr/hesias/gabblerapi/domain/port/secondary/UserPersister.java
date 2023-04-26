package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.result.DomainUserInfosAuthResult;
import fr.hesias.gabblerapi.domain.result.DomainUserRegistrationInfosResult;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;

public interface UserPersister
{

    /**
     * Récupérer tous les utilisateurs
     *
     * @return une liste de tous les utilisateurs
     */
    DomainUsersResult getUsers();


    DomainUsersResult getSuggestionsUserNotConnected();

    DomainUsersResult getSuggestionsUserConnected(String userUuid);

    /**
     * Récupérer un utilisateur par son id
     *
     * @param uuid l'uuid de l'utilisateur
     * @return l'utilisateur
     */
    DomainUserResult getUserByUuid(final String uuid);

    DomainUserResult getUserByEmail(String password);

    DomainUserInfosAuthResult getUserCredentialByEmail(String email);

    DomainUserResult register(final DomainUserRegistrationInfosResult user);

}
