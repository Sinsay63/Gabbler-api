package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.model.DomainSubscription;
import fr.hesias.gabblerapi.domain.result.DomainSubscriptionResult;

public interface SubscriptionPersister
{

    DomainSubscriptionResult subscribeUser(DomainSubscription domainSubscription);

    DomainSubscriptionResult getSubscriptionByUserUuid(String uuid);

}
