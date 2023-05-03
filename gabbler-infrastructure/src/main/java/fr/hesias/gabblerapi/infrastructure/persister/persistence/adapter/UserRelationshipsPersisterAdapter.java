package fr.hesias.gabblerapi.infrastructure.persister.persistence.adapter;

import fr.hesias.gabblerapi.domain.port.secondary.UserRelationshipsPersister;
import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;
import fr.hesias.gabblerapi.infrastructure.persister.service.UserRelationshipsPersisterService;

public class UserRelationshipsPersisterAdapter implements UserRelationshipsPersister
{

    private final UserRelationshipsPersisterService userRelationshipsPersisterService;

    public UserRelationshipsPersisterAdapter(UserRelationshipsPersisterService userRelationshipsPersisterService)
    {

        this.userRelationshipsPersisterService = userRelationshipsPersisterService;
    }

    @Override
    public void insertRelationShips(DomainUserRelationshipsCreationResult domainUserRelationshipsCreationResult)
    {

        userRelationshipsPersisterService.insertRelationShips(domainUserRelationshipsCreationResult);

    }

}
