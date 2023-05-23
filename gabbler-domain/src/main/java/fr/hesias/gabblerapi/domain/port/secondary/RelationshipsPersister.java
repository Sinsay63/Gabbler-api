package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;

public interface RelationshipsPersister
{

    void relationshipsCUD(DomainUserRelationshipsCreationResult domainUserRelationshipsCreationResult);

    boolean doFollowUser(String uuidUser, String uuidUserToFollow);

}
