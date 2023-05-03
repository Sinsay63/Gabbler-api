package fr.hesias.gabblerapi.infrastructure.persister.persistence.repository;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.UserRelationships;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.RelationshipTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRelationshipsRepository")
public interface UserRelationshipsRepository extends JpaRepository<UserRelationships, Integer>
{


    List<UserRelationships> findAllByUser_UuidAndTypeIs(String uuid, RelationshipTypeEnum type);

    List<UserRelationships> findAllByUser_Uuid(String uuid);

    List<UserRelationships> findAllByTypeAndUser_Uuid(RelationshipTypeEnum type, String uuid);

    List<UserRelationships> findAllByTypeAndUserRelated_Uuid(RelationshipTypeEnum type, String uuid);

}
