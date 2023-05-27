package fr.hesias.gabblerapi.infrastructure.persister.persistence.repository;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("subscriptionRepository")
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer>
{


    Subscription getSubscriptionByUser_Uuid(String uuid);

    void deleteByUser_Uuid(String uuid);

}
