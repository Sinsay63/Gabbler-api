package fr.hesias.gabblerapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DomainSubscription
{

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String userUuid;

    private int subscriptionOfferId;

    public DomainSubscription(LocalDateTime startDate, LocalDateTime endDate, String userUuid, int subscriptionOfferId)
    {

        this.startDate = startDate;
        this.endDate = endDate;
        this.userUuid = userUuid;
        this.subscriptionOfferId = subscriptionOfferId;
    }

    public DomainSubscription(LocalDateTime startDate, String userUuid, int subscriptionOfferId)
    {

        this.startDate = startDate;
        this.userUuid = userUuid;
        this.subscriptionOfferId = subscriptionOfferId;
    }

    public DomainSubscription(String userUuid, int subscriptionOfferId)
    {

        this.userUuid = userUuid;
        this.subscriptionOfferId = subscriptionOfferId;
    }

}
