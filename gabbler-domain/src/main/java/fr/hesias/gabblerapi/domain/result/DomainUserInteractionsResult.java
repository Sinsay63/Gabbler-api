package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DomainUserInteractionsResult extends DomainResultable
{

    private List<DomainUserInteractionResult> domainUserInteractionsResult;


    public DomainUserInteractionsResult(DomainAccessStatus domainAccessStatus)
    {

        super(domainAccessStatus);
    }

    public DomainUserInteractionsResult(DomainAccessStatus domainAccessStatus,
                                        List<DomainUserInteractionResult> domainUserInteractionsResult)
    {

        super(domainAccessStatus);
        this.domainUserInteractionsResult = domainUserInteractionsResult;
    }

}
