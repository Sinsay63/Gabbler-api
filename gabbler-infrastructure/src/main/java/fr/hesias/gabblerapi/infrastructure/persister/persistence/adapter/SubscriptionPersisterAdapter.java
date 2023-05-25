package fr.hesias.gabblerapi.infrastructure.persister.persistence.adapter;

import fr.hesias.gabblerapi.domain.model.DomainSubscription;
import fr.hesias.gabblerapi.domain.port.secondary.SubscriptionPersister;
import fr.hesias.gabblerapi.domain.result.DomainSubscriptionResult;
import fr.hesias.gabblerapi.infrastructure.persister.service.SubscriptionPersisterService;

public class SubscriptionPersisterAdapter implements SubscriptionPersister
{


    private final SubscriptionPersisterService subscriptionPersisterService;

    public SubscriptionPersisterAdapter(final SubscriptionPersisterService subscriptionPersisterService)
    {

        super();
        this.subscriptionPersisterService = subscriptionPersisterService;
    }

    @Override
    public DomainSubscriptionResult subscribeUser(DomainSubscription domainSubscription)
    {

        return null;
    }

    @Override
    public DomainSubscriptionResult getSubscriptionByUserUuid(String uuid)
    {

        return subscriptionPersisterService.getSubscriptionByUserUuid(uuid);
    }

}
