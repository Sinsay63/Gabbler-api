package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.SubscriptionRepository;

public class SubscriptionDao {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionDao(final SubscriptionRepository subscriptionDao) {

        super();
        this.subscriptionRepository = subscriptionDao;
    }


}
