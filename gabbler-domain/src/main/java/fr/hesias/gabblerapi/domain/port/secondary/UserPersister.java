package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.result.*;

public interface UserPersister {

    /**
     * Récupérer tous les utilisateurs
     *
     * @return une liste de tous les utilisateurs
     */
    DomainUsersInfosResult getUsers();

    /**
     * Récupérer un utilisateur par son id
     *
     * @param uuid l'uuid de l'utilisateur
     * @return l'utilisateur
     */
    DomainUserInfosResult getUserByUuid(final String uuid);

    DomainUserResult getUserByEmail(String password);

    DomainUserInfosAuthResult getUserCredentialByEmail(String email);

    DomainUserResult register(final DomainUserRegistrationInfosResult user);

}
