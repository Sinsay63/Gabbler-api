package fr.hesias.gabblerapi.infrastructure.persister.persistence.repository;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("gabRepository")
public interface GabRepository extends JpaRepository<Gab, Integer>
{


    List<Gab> findAllByParentGabIdOrderByPostDateAsc(int parentGabId);

    List<Gab> findAllByOrderByPostDateDesc();

    List<Gab> findAllByContentContainingIgnoreCaseAndParentGabNull(String researchContent);

    List<Gab> findAllByUserInAndParentGabNullOrderByPostDateDesc(List<User> users);

    List<Gab> findAllByUserUuidAndParentGabNullOrderByPostDateDesc(String userUuid);

    List<Gab> findAllByUser_UuidNotAndParentGabNull(String userUuid);

}
