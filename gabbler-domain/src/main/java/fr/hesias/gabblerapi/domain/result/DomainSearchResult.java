package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainSearchResult extends DomainResultable
{

    private DomainGabsResult domainGabsResult;

    private DomainUsersResult domainUsersResult;

    public DomainSearchResult(final DomainAccessStatus domainAccessStatus)
    {

        super(domainAccessStatus);
    }

    public DomainSearchResult(final DomainAccessStatus domainAccessStatus,
                              final DomainGabsResult domainGabsResult,
                              final DomainUsersResult domainUsersResult)
    {

        super(domainAccessStatus);
        this.domainGabsResult = domainGabsResult;
        this.domainUsersResult = domainUsersResult;
    }

}
