package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;

public interface UserRelationshipsPersister
{

    void insertRelationShips(DomainUserRelationshipsCreationResult domainUserRelationshipsCreationResult);

}
