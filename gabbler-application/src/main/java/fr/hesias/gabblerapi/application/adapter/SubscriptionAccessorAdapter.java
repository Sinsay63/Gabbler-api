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

    public void subscribeUser(String uuidUser, String uuidUserToFollow)
    {

//        subscriptionAccessor.subscribeUser(uuidUser, uuidUserToFollow);
    }

    public DomainSubscriptionResult getSubscriptionByUserUuid(String uuid)
    {

        return subscriptionAccessor.getSubscriptionByUserUuid(uuid);
    }

}
