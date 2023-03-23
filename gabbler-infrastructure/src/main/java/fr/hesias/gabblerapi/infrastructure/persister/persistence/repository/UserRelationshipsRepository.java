package fr.hesias.gabblerapi.infrastructure.persister.persistence.repository;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.UserRelationships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRelationshipsRepository")
public interface UserRelationshipsRepository extends JpaRepository<UserRelationships, Integer> {

}
