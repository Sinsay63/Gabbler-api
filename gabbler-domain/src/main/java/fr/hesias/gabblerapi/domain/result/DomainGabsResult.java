package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DomainGabsResult extends DomainResultable
{

    private List<DomainGabResult> gabs;

    public DomainGabsResult(final DomainAccessStatus domainAccessStatus)
    {

        super(domainAccessStatus);
    }

    public DomainGabsResult(final DomainAccessStatus domainAccessStatus, final List<DomainGabResult> gabs)
    {

        super(domainAccessStatus);
        this.gabs = gabs;
    }

}
