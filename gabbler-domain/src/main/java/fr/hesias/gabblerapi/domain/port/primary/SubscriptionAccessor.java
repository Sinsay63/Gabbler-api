package fr.hesias.gabblerapi.domain.port.primary;

import fr.hesias.gabblerapi.domain.model.DomainSubscription;
import fr.hesias.gabblerapi.domain.result.DomainSubscriptionResult;

public interface SubscriptionAccessor
{

    DomainSubscriptionResult subscribeUser(DomainSubscription domainSubscription);

    DomainSubscriptionResult getSubscriptionByUserUuid(String uuid);

}
