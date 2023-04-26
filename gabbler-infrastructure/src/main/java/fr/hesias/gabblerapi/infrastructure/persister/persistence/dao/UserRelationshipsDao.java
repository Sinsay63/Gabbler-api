package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.UserRelationships;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.RelationshipTypeEnum;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.UserRelationshipsRepository;

import java.util.List;

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

}
