package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.UserRelationshipsDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.UserRelationships;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.RelationshipTypeEnum;
import lombok.extern.slf4j.Slf4j;

import static fr.hesias.gabblerapi.infrastructure.persister.persistence.model.RelationshipTypeEnum.*;

@Slf4j
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

    public void relationshipsCUD(DomainUserRelationshipsCreationResult domainUserRelationshipsCreationResult)
    {

        UserRelationships userRelationships = new UserRelationships();
        UserRelationships userRelationshipsToUpdate = new UserRelationships();
        User user = new User(domainUserRelationshipsCreationResult.getDomainUserRelationshipsCreation().getUserUuid());
        User userRelated = new User(domainUserRelationshipsCreationResult.getDomainUserRelationshipsCreation()
                                                                         .getUserRelatedUuid());

        userRelationships.setUser(user);
        userRelationships.setUserRelated(userRelated);

        try
        {
            RelationshipTypeEnum type = RelationshipTypeEnum.getInteractionByName(domainUserRelationshipsCreationResult.getDomainUserRelationshipsCreation()
                                                                                                                       .getType());
            userRelationships.setType(type);
            if (type == BLOCKED)
            {
                userRelationshipsToUpdate = userRelationshipsDao.findByUser_UuidAndUserRelated_UuidAndTypeIs(user.getUuid(),
                                                                                                             userRelated.getUuid(),
                                                                                                             AUTHORIZED);
                if (userRelationshipsToUpdate == null)
                {
                    userRelationshipsToUpdate = userRelationshipsDao.findByUser_UuidAndUserRelated_UuidAndTypeIs(user.getUuid(),
                                                                                                                 userRelated.getUuid(),
                                                                                                                 BLOCKED);
                    if (userRelationshipsToUpdate == null)
                    {
                        userRelationshipsDao.save(userRelationships);
                    }
                    else
                    {
                        userRelationshipsDao.delete(userRelationshipsToUpdate);
                    }
                }
                else
                {
                    userRelationshipsToUpdate.setType(BLOCKED);
                    userRelationshipsDao.save(userRelationshipsToUpdate);
                }

            }
            else if (type == AUTHORIZED)
            {
                userRelationshipsToUpdate = userRelationshipsDao.findByUser_UuidAndUserRelated_UuidAndTypeIs(user.getUuid(),
                                                                                                             userRelated.getUuid(),
                                                                                                             BLOCKED);
                if (userRelationshipsToUpdate == null)
                {
                    userRelationshipsToUpdate = userRelationshipsDao.findByUser_UuidAndUserRelated_UuidAndTypeIs(user.getUuid(),
                                                                                                                 userRelated.getUuid(),
                                                                                                                 BLOCKED);
                    if (userRelationshipsToUpdate == null)
                    {
                        userRelationshipsDao.save(userRelationships);
                    }
                    else
                    {
                        userRelationshipsDao.delete(userRelationshipsToUpdate);
                    }
                }
                else
                {
                    userRelationshipsToUpdate.setType(AUTHORIZED);
                    userRelationshipsDao.save(userRelationshipsToUpdate);
                }
            }
            else if (type == FOLLOWED)
            {
                userRelationshipsToUpdate = userRelationshipsDao.findByUser_UuidAndUserRelated_UuidAndTypeIs(user.getUuid(),
                                                                                                             userRelated.getUuid(),
                                                                                                             FOLLOWED);
                if (userRelationshipsToUpdate == null)
                {
                    userRelationshipsDao.save(userRelationships);
                }
                else
                {
                    userRelationshipsDao.delete(userRelationshipsToUpdate);
                }
            }
        }
        catch (Exception e)
        {
            log.error(
                    "Erreur lors de l'update ou ajout de la relation pour l'utilisateur  {} et l'utilisateur related {}",
                    userRelationships.getUser().getUuid(),
                    userRelationships.getUserRelated().getUuid(),
                    e);
        }

    }


}
