package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainUserProfile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainUserProfileResult extends DomainResultable
{

    private DomainUserProfile domainUserProfile;


    public DomainUserProfileResult(final DomainAccessStatus domainAccessStatus)
    {

        super(domainAccessStatus);
    }

    public DomainUserProfileResult(final DomainAccessStatus domainAccessStatus,
                                   final DomainUserProfile domainUserProfile)
    {

        super(domainAccessStatus);
        this.domainUserProfile = domainUserProfile;
    }

}
