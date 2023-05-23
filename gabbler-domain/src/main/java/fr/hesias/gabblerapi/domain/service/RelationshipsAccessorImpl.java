package fr.hesias.gabblerapi.domain.service;

import fr.hesias.gabblerapi.domain.port.primary.RelationshipsAccessor;
import fr.hesias.gabblerapi.domain.port.secondary.RelationshipsPersister;
import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;

public class RelationshipsAccessorImpl implements RelationshipsAccessor
{

    private final RelationshipsPersister relationshipsPersister;

    public RelationshipsAccessorImpl(RelationshipsPersister relationshipsPersister)
    {

        this.relationshipsPersister = relationshipsPersister;
    }

    @Override
    public void relationshipsCUD(DomainUserRelationshipsCreationResult domainUserRelationshipsCreationResult)
    {

        relationshipsPersister.relationshipsCUD(domainUserRelationshipsCreationResult);

    }

    @Override
    public boolean doFollowUser(String uuidUser, String uuidUserToFollow)
    {

        return relationshipsPersister.doFollowUser(uuidUser, uuidUserToFollow);
    }

}
