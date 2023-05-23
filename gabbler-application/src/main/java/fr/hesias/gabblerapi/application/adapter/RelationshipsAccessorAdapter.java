package fr.hesias.gabblerapi.application.adapter;

import fr.hesias.gabblerapi.domain.port.primary.RelationshipsAccessor;
import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;

public class RelationshipsAccessorAdapter
{

    private final RelationshipsAccessor relationshipsAccessor;

    public RelationshipsAccessorAdapter(RelationshipsAccessor relationshipsAccessor)
    {

        this.relationshipsAccessor = relationshipsAccessor;
    }

    public void relationshipsCUD(DomainUserRelationshipsCreationResult domainUserRelationshipsCreationResult)
    {

        relationshipsAccessor.relationshipsCUD(domainUserRelationshipsCreationResult);

    }

    public String getRelationByUserAndUserRelated(String uuidUser, String uuidUserToFollow)
    {

        return relationshipsAccessor.getRelationByUserAndUserRelated(uuidUser, uuidUserToFollow);
    }

}
