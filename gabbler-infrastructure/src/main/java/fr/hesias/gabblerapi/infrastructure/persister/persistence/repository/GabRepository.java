package fr.hesias.gabblerapi.infrastructure.persister.persistence.repository;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GabRepository extends JpaRepository<Gab, Integer> {
}
