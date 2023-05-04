package fr.hesias.gabblerapi.domain.service;

import fr.hesias.gabblerapi.domain.port.primary.UserRelationshipsAccessor;
import fr.hesias.gabblerapi.domain.port.secondary.UserRelationshipsPersister;
import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;

public class UserRelationshipsAccessorImpl implements UserRelationshipsAccessor
{

    private final UserRelationshipsPersister userRelationshipsPersister;

    public UserRelationshipsAccessorImpl(UserRelationshipsPersister userRelationshipsPersister)
    {

        this.userRelationshipsPersister = userRelationshipsPersister;
    }

    @Override
    public void relationshipsCUD(DomainUserRelationshipsCreationResult domainUserRelationshipsCreationResult)
    {

        userRelationshipsPersister.relationshipsCUD(domainUserRelationshipsCreationResult);

    }

}
