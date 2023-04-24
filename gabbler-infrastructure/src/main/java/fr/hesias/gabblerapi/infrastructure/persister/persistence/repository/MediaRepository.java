package fr.hesias.gabblerapi.infrastructure.persister.persistence.repository;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Media;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.MediaTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    List<Media> findAllByUser_UuidAndTypeIsNot(String userUuid, MediaTypeEnum type);

    List<Media> findAllByGab_IdAndTypeIs(Integer gabId, MediaTypeEnum type);
}
