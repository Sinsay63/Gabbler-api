package fr.hesias.gabblerapi.domain.port.primary;

import fr.hesias.gabblerapi.domain.result.DomainUsersResult;

public interface UserInfosAccessor {

    /**
     * Récupère la liste des utilisateurs
     *
     * @return la liste des utilisateurs
     */
    DomainUsersResult getUsers();


}
