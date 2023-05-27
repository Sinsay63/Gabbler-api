package fr.hesias.gabblerapi.domain.port.primary;

import fr.hesias.gabblerapi.domain.result.DomainSubscriptionResult;

public interface SubscriptionAccessor
{

    DomainSubscriptionResult subscribeUser(DomainSubscriptionResult domainSubscriptionResult);

    DomainSubscriptionResult getSubscriptionByUserUuid(String uuid);

}
