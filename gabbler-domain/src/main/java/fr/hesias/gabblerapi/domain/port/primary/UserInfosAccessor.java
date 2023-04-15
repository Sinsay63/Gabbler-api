package fr.hesias.gabblerapi.domain.port.primary;

import fr.hesias.gabblerapi.domain.model.DomainUserAuth;
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

    DomainUserResult getUserByEmail(String email);

    DomainUserAuth getUserCredentialByEmail(String email);


}
