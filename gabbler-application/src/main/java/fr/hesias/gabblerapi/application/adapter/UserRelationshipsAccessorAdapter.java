package fr.hesias.gabblerapi.application.adapter;

import fr.hesias.gabblerapi.domain.port.primary.UserRelationshipsAccessor;
import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;

public class UserRelationshipsAccessorAdapter
{

    private final UserRelationshipsAccessor userRelationshipsAccessor;

    public UserRelationshipsAccessorAdapter(UserRelationshipsAccessor userRelationshipsAccessor)
    {

        this.userRelationshipsAccessor = userRelationshipsAccessor;
    }

    public void relationshipsCUD(DomainUserRelationshipsCreationResult domainUserRelationshipsCreationResult)
    {

        userRelationshipsAccessor.relationshipsCUD(domainUserRelationshipsCreationResult);

    }

}
