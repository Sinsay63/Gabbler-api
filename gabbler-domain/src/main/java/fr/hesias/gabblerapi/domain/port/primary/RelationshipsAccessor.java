package fr.hesias.gabblerapi.domain.port.primary;

import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;

public interface RelationshipsAccessor
{

    void relationshipsCUD(DomainUserRelationshipsCreationResult domainUserRelationshipsCreationResult);

    String getRelationByUserAndUserRelated(String uuidUser, String uuidUserToFollow);

}
