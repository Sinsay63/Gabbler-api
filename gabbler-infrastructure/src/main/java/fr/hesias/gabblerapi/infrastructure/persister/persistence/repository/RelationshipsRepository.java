package fr.hesias.gabblerapi.infrastructure.persister.persistence.repository;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.UserRelationships;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.RelationshipTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRelationshipsRepository")
public interface RelationshipsRepository extends JpaRepository<UserRelationships, Integer>
{


    List<UserRelationships> findAllByUser_UuidAndTypeIs(String uuid, RelationshipTypeEnum type);

    List<UserRelationships> findAllByUser_Uuid(String uuid);

    List<UserRelationships> findAllByUser_UuidAndUserRelated_Uuid(String uuidUser, String uuidUserRelated);

    List<UserRelationships> findAllByTypeAndUser_Uuid(RelationshipTypeEnum type, String uuid);

    List<UserRelationships> findAllByTypeAndUserRelated_Uuid(RelationshipTypeEnum type, String uuid);

    UserRelationships findByUser_UuidAndUserRelated_UuidAndTypeIs(String uuid,
                                                                  String uuidRelated,
                                                                  RelationshipTypeEnum type);

}
