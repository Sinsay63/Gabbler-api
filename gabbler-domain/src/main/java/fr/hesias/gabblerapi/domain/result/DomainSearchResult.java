package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;

public class DomainSearchResult extends DomainResultable
{

    private DomainGabsResult gabs;

    private DomainUsersResult usersInfos;

    public DomainSearchResult(final DomainAccessStatus domainAccessStatus)
    {

        super(domainAccessStatus);
    }

    public DomainSearchResult(final DomainAccessStatus domainAccessStatus,
                              final DomainGabsResult gabs,
                              final DomainUsersResult usersInfos)
    {

        super(domainAccessStatus);
        this.gabs = gabs;
        this.usersInfos = usersInfos;
    }

}
