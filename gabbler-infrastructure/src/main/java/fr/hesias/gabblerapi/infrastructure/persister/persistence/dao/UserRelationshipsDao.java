package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.UserRelationships;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.RelationshipTypeEnum;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.UserRelationshipsRepository;

import java.util.List;

import static fr.hesias.gabblerapi.infrastructure.persister.persistence.model.RelationshipTypeEnum.FOLLOWED;

public class UserRelationshipsDao
{

    private final UserRelationshipsRepository userRelationshipsRepository;

    public UserRelationshipsDao(final UserRelationshipsRepository userRelationshipsRepository)
    {

        super();
        this.userRelationshipsRepository = userRelationshipsRepository;
    }

    public List<UserRelationships> findAllByUser_UuidAndTypeIs(String user_uuid, RelationshipTypeEnum type)
    {

        return userRelationshipsRepository.findAllByUser_UuidAndTypeIs(user_uuid, type);
    }

    public List<UserRelationships> findAllByUser_Uuid(String user_uuid)
    {

        return userRelationshipsRepository.findAllByUser_Uuid(user_uuid);
    }

    public void save(UserRelationships userRelationships)
    {

        userRelationshipsRepository.save(userRelationships);
    }

    public List<UserRelationships> findAllFollowersByUserUuid(String userUuid)
    {

        return userRelationshipsRepository.findAllByTypeAndUser_Uuid(FOLLOWED, userUuid);
    }

    public List<UserRelationships> findAllFollowsByUserUuid(String userUuid)
    {

        return userRelationshipsRepository.findAllByTypeAndUserRelated_Uuid(FOLLOWED, userUuid);
    }

}
