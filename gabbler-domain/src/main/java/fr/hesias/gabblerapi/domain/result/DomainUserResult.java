package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainUser;
import lombok.Getter;

@Getter
public class DomainUserResult extends DomainResultable
{

    private DomainUser domainUser;

    public DomainUserResult(final DomainAccessStatus domainAccessStatus)
    {
        super(domainAccessStatus);
    }

    public DomainUserResult(final DomainAccessStatus domainAccessStatus, final DomainUser domainUser)
    {
        super(domainAccessStatus);
        this.domainUser = domainUser;
    }
}
