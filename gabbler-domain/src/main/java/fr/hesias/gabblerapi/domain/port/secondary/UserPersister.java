package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.model.DomainUserAuth;
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

    /**
     * Récupérer un utilisateur par son id
     *
     * @param id l'id de l'utilisateur
     * @return l'utilisateur
     */
    DomainUserResult getUserById(final int id);

    DomainUserResult getUserByEmail(String password);

    DomainUserAuth getUserCredentialByEmail(String email);

}
