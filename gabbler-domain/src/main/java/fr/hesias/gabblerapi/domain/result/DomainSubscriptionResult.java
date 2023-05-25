package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainSubscription;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainSubscriptionResult extends DomainResultable
{

    private DomainSubscription domainSubscription;

    public DomainSubscriptionResult(DomainAccessStatus domainAccessStatus)
    {

        super(domainAccessStatus);
    }

    public DomainSubscriptionResult(DomainAccessStatus domainAccessStatus, DomainSubscription domainSubscription)
    {

        super(domainAccessStatus);
        this.domainSubscription = domainSubscription;
    }

}
