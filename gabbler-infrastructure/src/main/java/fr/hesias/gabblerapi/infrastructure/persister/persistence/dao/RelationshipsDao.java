package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.UserRelationships;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.RelationshipTypeEnum;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.RelationshipsRepository;

import java.util.List;

import static fr.hesias.gabblerapi.infrastructure.persister.persistence.model.RelationshipTypeEnum.FOLLOWED;

public class RelationshipsDao
{

    private final RelationshipsRepository relationshipsRepository;

    public RelationshipsDao(final RelationshipsRepository relationshipsRepository)
    {

        super();
        this.relationshipsRepository = relationshipsRepository;
    }

    public List<UserRelationships> findAllByUser_UuidAndTypeIs(String user_uuid, RelationshipTypeEnum type)
    {

        return relationshipsRepository.findAllByUser_UuidAndTypeIs(user_uuid, type);
    }

    public List<UserRelationships> findAllByUser_Uuid(String user_uuid)
    {

        return relationshipsRepository.findAllByUser_Uuid(user_uuid);
    }

    public void save(UserRelationships userRelationships)
    {

        relationshipsRepository.save(userRelationships);
    }

    public List<UserRelationships> findAllFollowersByUserUuid(String userUuid)
    {

        return relationshipsRepository.findAllByTypeAndUser_Uuid(FOLLOWED, userUuid);
    }

    public List<UserRelationships> findAllFollowsByUserUuid(String userUuid)
    {

        return relationshipsRepository.findAllByTypeAndUserRelated_Uuid(FOLLOWED, userUuid);
    }

    public UserRelationships findByUser_UuidAndUserRelated_UuidAndTypeIs(String userUuid,
                                                                         String userRelatedUuid,
                                                                         RelationshipTypeEnum type)
    {

        return relationshipsRepository.findByUser_UuidAndUserRelated_UuidAndTypeIs(userUuid, userRelatedUuid, type);
    }

    public void delete(UserRelationships userRelationships)
    {

        relationshipsRepository.delete(userRelationships);
    }

    public boolean doFollowUser(String userUuid, String userRelatedUuid)
    {

        return relationshipsRepository.existsByUser_UuidAndUserRelated_UuidAndTypeIs(userUuid,
                                                                                     userRelatedUuid,
                                                                                     FOLLOWED);
    }

}
