package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.result.DomainSubscriptionResult;

public interface SubscriptionPersister
{

    DomainSubscriptionResult subscribeUser(DomainSubscriptionResult domainSubscriptionResult);

    DomainSubscriptionResult getSubscriptionByUserUuid(String uuid);

}
