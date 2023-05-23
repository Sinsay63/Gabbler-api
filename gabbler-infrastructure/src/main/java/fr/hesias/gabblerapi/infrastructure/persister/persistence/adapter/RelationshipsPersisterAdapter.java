package fr.hesias.gabblerapi.infrastructure.persister.persistence.adapter;

import fr.hesias.gabblerapi.domain.port.secondary.RelationshipsPersister;
import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;
import fr.hesias.gabblerapi.infrastructure.persister.service.RelationshipsPersisterService;

public class RelationshipsPersisterAdapter implements RelationshipsPersister
{

    private final RelationshipsPersisterService relationshipsPersisterService;

    public RelationshipsPersisterAdapter(RelationshipsPersisterService relationshipsPersisterService)
    {

        this.relationshipsPersisterService = relationshipsPersisterService;
    }

    @Override
    public void relationshipsCUD(DomainUserRelationshipsCreationResult domainUserRelationshipsCreationResult)
    {

        relationshipsPersisterService.relationshipsCUD(domainUserRelationshipsCreationResult);

    }

    @Override
    public String getRelationByUserAndUserRelated(String uuidUser, String uuidUserToFollow)
    {

        return relationshipsPersisterService.getRelationByUserAndUserRelated(uuidUser, uuidUserToFollow);
    }

}
