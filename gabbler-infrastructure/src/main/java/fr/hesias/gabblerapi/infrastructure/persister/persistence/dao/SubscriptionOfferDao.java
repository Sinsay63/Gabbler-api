package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.SubscriptionOfferRepository;

public class SubscriptionOfferDao {

    private final SubscriptionOfferRepository subscriptionOfferRepository;

    public SubscriptionOfferDao(final SubscriptionOfferRepository subscriptionOfferRepository) {

        super();
        this.subscriptionOfferRepository = subscriptionOfferRepository;
    }


}
