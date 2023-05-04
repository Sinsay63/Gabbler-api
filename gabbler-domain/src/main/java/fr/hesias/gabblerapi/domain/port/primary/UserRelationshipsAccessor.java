package fr.hesias.gabblerapi.domain.port.primary;

import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;

public interface UserRelationshipsAccessor
{

    void relationshipsCUD(DomainUserRelationshipsCreationResult domainUserRelationshipsCreationResult);

}
