package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;

public interface RelationshipsPersister
{

    void relationshipsCUD(DomainUserRelationshipsCreationResult domainUserRelationshipsCreationResult);

    String getRelationByUserAndUserRelated(String uuidUser, String uuidUserToFollow);

}
