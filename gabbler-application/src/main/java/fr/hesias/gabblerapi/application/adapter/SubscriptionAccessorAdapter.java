package fr.hesias.gabblerapi.application.adapter;

import fr.hesias.gabblerapi.domain.port.primary.SubscriptionAccessor;
import fr.hesias.gabblerapi.domain.result.DomainSubscriptionResult;

public class SubscriptionAccessorAdapter
{

    private final SubscriptionAccessor subscriptionAccessor;

    public SubscriptionAccessorAdapter(SubscriptionAccessor subscriptionAccessor)
    {

        this.subscriptionAccessor = subscriptionAccessor;
    }

    public DomainSubscriptionResult subscribeUser(DomainSubscriptionResult domainSubscriptionResult)
    {

        return subscriptionAccessor.subscribeUser(domainSubscriptionResult);
    }

    public DomainSubscriptionResult getSubscriptionByUserUuid(String uuid)
    {

        return subscriptionAccessor.getSubscriptionByUserUuid(uuid);
    }

}
