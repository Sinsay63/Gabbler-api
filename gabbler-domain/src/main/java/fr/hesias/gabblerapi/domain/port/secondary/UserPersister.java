package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;

import java.util.UUID;

public interface UserPersister {

    /**
     * Récupérer tous les utilisateurs
     *
     * @return une liste de tous les utilisateurs
     */
    DomainUsersResult getUsers();

    /**
     * Récupérer un utilisateur par son id
     *
     * @param uuid l'uuid de l'utilisateur
     * @return l'utilisateur
     */
    DomainUserResult getUserByUuid(final UUID uuid);
}
