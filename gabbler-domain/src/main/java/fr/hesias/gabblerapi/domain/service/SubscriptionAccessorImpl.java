package fr.hesias.gabblerapi.domain.service;

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
    public DomainSubscriptionResult subscribeUser(DomainSubscriptionResult domainSubscriptionResult)
    {

        return subscriptionPersister.subscribeUser(domainSubscriptionResult);
    }

    @Override
    public DomainSubscriptionResult getSubscriptionByUserUuid(String uuid)
    {

        return subscriptionPersister.getSubscriptionByUserUuid(uuid);
    }

}
