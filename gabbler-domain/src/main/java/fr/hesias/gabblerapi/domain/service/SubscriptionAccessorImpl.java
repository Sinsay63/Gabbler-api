package fr.hesias.gabblerapi.domain.service;

import fr.hesias.gabblerapi.domain.model.DomainSubscription;
import fr.hesias.gabblerapi.domain.port.primary.SubscriptionAccessor;
import fr.hesias.gabblerapi.domain.port.secondary.SubscriptionPersister;
import fr.hesias.gabblerapi.domain.result.DomainSubscriptionResult;

public class SubscriptionAccessorImpl implements SubscriptionAccessor
{

    private final SubscriptionPersister subscriptionPersister;

    public SubscriptionAccessorImpl(SubscriptionPersister subscriptionPersister)
    {

        this.subscriptionPersister = subscriptionPersister;
    }

    @Override
    public DomainSubscriptionResult subscribeUser(DomainSubscription domainSubscription)
    {

//        return this.subscriptionPersister;
        return null;
    }

    @Override
    public DomainSubscriptionResult getSubscriptionByUserUuid(String uuid)
    {

        return subscriptionPersister.getSubscriptionByUserUuid(uuid);
    }

}
