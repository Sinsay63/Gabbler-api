package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.result.DomainUserRelationshipsCreationResult;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.RelationshipsDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.UserRelationships;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.RelationshipTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static fr.hesias.gabblerapi.infrastructure.persister.persistence.model.RelationshipTypeEnum.*;

@Slf4j
public class RelationshipsPersisterService
{

    private final RelationshipsDao relationshipsDao;

    private final GabblerInfraMapper gabblerInfraMapper;

    public RelationshipsPersisterService(final RelationshipsDao relationshipsDao,
                                         final GabblerInfraMapper gabblerInfraMapper)
    {

        super();
        this.relationshipsDao = relationshipsDao;
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
                userRelationshipsToUpdate = relationshipsDao.findByUser_UuidAndUserRelated_UuidAndTypeIs(user.getUuid(),
                                                                                                         userRelated.getUuid(),
                                                                                                         AUTHORIZED);
                if (userRelationshipsToUpdate == null)
                {
                    userRelationshipsToUpdate = relationshipsDao.findByUser_UuidAndUserRelated_UuidAndTypeIs(user.getUuid(),
                                                                                                             userRelated.getUuid(),
                                                                                                             BLOCKED);
                    if (userRelationshipsToUpdate == null)
                    {
                        userRelationshipsToUpdate = relationshipsDao.findByUser_UuidAndUserRelated_UuidAndTypeIs(
                                user.getUuid(),
                                userRelated.getUuid(),
                                FOLLOWED);
                        if (userRelationshipsToUpdate == null)
                        {
                            relationshipsDao.save(userRelationships);
                        }
                        else
                        {
                            relationshipsDao.delete(userRelationshipsToUpdate);
                            relationshipsDao.save(userRelationships);
                        }

                    }
                    else
                    {
                        relationshipsDao.delete(userRelationshipsToUpdate);
                    }
                }
                else
                {
                    userRelationshipsToUpdate.setType(BLOCKED);
                    relationshipsDao.save(userRelationshipsToUpdate);

                    userRelationshipsToUpdate = relationshipsDao.findByUser_UuidAndUserRelated_UuidAndTypeIs(user.getUuid(),
                                                                                                             userRelated.getUuid(),
                                                                                                             FOLLOWED);
                    if (userRelationshipsToUpdate != null)
                    {
                        relationshipsDao.delete(userRelationshipsToUpdate);
                    }
                }

            }
            else if (type == AUTHORIZED)
            {
                userRelationshipsToUpdate = relationshipsDao.findByUser_UuidAndUserRelated_UuidAndTypeIs(user.getUuid(),
                                                                                                         userRelated.getUuid(),
                                                                                                         BLOCKED);
                if (userRelationshipsToUpdate == null)
                {
                    userRelationshipsToUpdate = relationshipsDao.findByUser_UuidAndUserRelated_UuidAndTypeIs(user.getUuid(),
                                                                                                             userRelated.getUuid(),
                                                                                                             BLOCKED);
                    if (userRelationshipsToUpdate == null)
                    {
                        relationshipsDao.save(userRelationships);
                    }
                    else
                    {
                        relationshipsDao.delete(userRelationshipsToUpdate);
                    }
                }
                else
                {
                    userRelationshipsToUpdate.setType(AUTHORIZED);
                    relationshipsDao.save(userRelationshipsToUpdate);
                }
            }
            else if (type == FOLLOWED)
            {
                userRelationshipsToUpdate = relationshipsDao.findByUser_UuidAndUserRelated_UuidAndTypeIs(user.getUuid(),
                                                                                                         userRelated.getUuid(),
                                                                                                         FOLLOWED);
                if (userRelationshipsToUpdate == null)
                {
                    relationshipsDao.save(userRelationships);
                }
                else
                {
                    relationshipsDao.delete(userRelationshipsToUpdate);
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


    public String getRelationByUserAndUserRelated(String uuidUser, String uuidUserToFollow)
    {

        List<UserRelationships> relations = relationshipsDao.getRelationsByUserAndUserRelated(uuidUser,
                                                                                              uuidUserToFollow);
        String relation = "";

        if (relations.isEmpty())
        {
            relation = "";
        }
        else if (relations.size() == 1)
        {
            relation = relations.get(0).getType().getInteractionType();
        }
        else if (relations.size() == 2)
        {
            relation = "authorized_followed";
        }
        return relation;
    }

}
