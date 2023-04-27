package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainUserInteractionResult extends DomainResultable
{

    private String userUuid;

    private String interactionType;

    private int gabId;


    public DomainUserInteractionResult(DomainAccessStatus domainAccessStatus)
    {

        super(domainAccessStatus);
    }

    public DomainUserInteractionResult(DomainAccessStatus domainAccessStatus,
                                       String userUuid,
                                       String interactionType,
                                       int gabId)
    {

        super(domainAccessStatus);
        this.userUuid = userUuid;
        this.interactionType = interactionType;
        this.gabId = gabId;
    }

}
