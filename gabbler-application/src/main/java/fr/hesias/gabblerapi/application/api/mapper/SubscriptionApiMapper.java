package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.desc.api.server.model.UserSubscription;
import fr.hesias.gabblerapi.domain.model.DomainSubscription;
import fr.hesias.gabblerapi.domain.result.DomainSubscriptionResult;

import java.time.LocalDateTime;

public class SubscriptionApiMapper
{

    public UserSubscription toDomainSubscriptionToSubscriptionResult(DomainSubscriptionResult domainSubscriptionResult)
    {

        var domainSubscription = domainSubscriptionResult.getDomainSubscription();
        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setUserUuid(domainSubscription.getUserUuid());
        userSubscription.setSubscriptionOfferId(domainSubscription.getSubscriptionOfferId());
        userSubscription.setEndDate(domainSubscription.getEndDate().toString());
        userSubscription.setStartDate(domainSubscription.getStartDate().toString());
        return userSubscription;
    }

    public DomainSubscription toSubscriptionToDomainSubscription(UserSubscription userSubscription)
    {

        return new DomainSubscription(
                LocalDateTime.parse(userSubscription.getStartDate()),
                LocalDateTime.parse(userSubscription.getEndDate()),
                userSubscription.getUserUuid(),
                userSubscription.getSubscriptionOfferId()
        );

    }


}
