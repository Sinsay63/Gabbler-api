package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.desc.api.server.model.UserSubscription;
import fr.hesias.gabblerapi.domain.model.DomainSubscription;
import fr.hesias.gabblerapi.domain.result.DomainSubscriptionResult;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;

public class SubscriptionApiMapper
{

    public UserSubscription toDomainSubscriptionToSubscriptionResult(DomainSubscriptionResult domainSubscriptionResult)
    {

        var domainSubscription = domainSubscriptionResult.getDomainSubscription();
        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setSubscriptionOfferId(domainSubscription.getSubscriptionOfferId());
        userSubscription.setEndDate(domainSubscription.getEndDate().toString());
        userSubscription.setStartDate(domainSubscription.getStartDate().toString());
        return userSubscription;
    }

    public DomainSubscriptionResult toSubscriptionToDomainSubscription(String userUuid,
                                                                       int offerId)
    {

        var domainSubcription = new DomainSubscription(
                userUuid,
                offerId
        );

        return new DomainSubscriptionResult(OK, domainSubcription);
    }


}
