package fr.hesias.gabblerapi.infrastructure.persister.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionOffer extends JpaRepository<SubscriptionOffer, Integer> {
}
