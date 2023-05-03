package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.UserRelationshipsDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;

public class UserRelationshipsPersisterService
{

    private final UserRelationshipsDao userRelationshipsDao;

    private final GabblerInfraMapper gabblerInfraMapper;

    public UserRelationshipsPersisterService(final UserRelationshipsDao userRelationshipsDao,
                                             final GabblerInfraMapper gabblerInfraMapper)
    {

        super();
        this.userRelationshipsDao = userRelationshipsDao;
        this.gabblerInfraMapper = gabblerInfraMapper;
    }

    public void insertRelationShips(DomainUserRelationshipsCreationResult domainUserRelationshipsCreationResult)
    {

        userRelationshipsDao.save(this.gabblerInfraMapper.toDomainUserRelationshipsCreationResultToUserRelationships(
                domainUserRelationshipsCreationResult));
    }


}
