package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainEditUserProfile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainEditUserProfileResult extends DomainResultable
{

    private DomainEditUserProfile domainEditUserProfile;

    public DomainEditUserProfileResult(DomainAccessStatus domainAccessStatus)
    {

        super(domainAccessStatus);
    }

    public DomainEditUserProfileResult(DomainAccessStatus domainAccessStatus,
                                       DomainEditUserProfile domainEditUserProfile)
    {

        super(domainAccessStatus);
        this.domainEditUserProfile = domainEditUserProfile;
    }

}
