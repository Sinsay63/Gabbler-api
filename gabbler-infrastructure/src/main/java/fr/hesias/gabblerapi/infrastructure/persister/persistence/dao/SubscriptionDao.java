package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Subscription;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.SubscriptionRepository;

public class SubscriptionDao
{

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionDao(final SubscriptionRepository subscriptionDao)
    {

        super();
        this.subscriptionRepository = subscriptionDao;
    }

    public Subscription addSubscription(Subscription subscription)
    {

        return subscriptionRepository.save(subscription);
    }

    public Subscription getSubscriptionByUserUuid(String uuid)
    {

        return subscriptionRepository.getSubscriptionByUser_Uuid(uuid);
    }

    public void deleteSubscriptionByUserUuid(String userUuid)
    {

        subscriptionRepository.deleteByUser_Uuid(userUuid);

    }

}
