package fr.hesias.gabblerapi.infrastructure.persister.persistence.repository;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.SubscriptionOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("subscriptionOfferRepository")
public interface SubscriptionOfferRepository extends JpaRepository<SubscriptionOffer, Integer> {

}
