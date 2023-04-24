package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainUserInfosResult extends DomainResultable {

    private DomainUser domainUser;

    private DomainMediasResult domainMediasResult;

    public DomainUserInfosResult(DomainAccessStatus domainAccessStatus) {
        super(domainAccessStatus);
    }

    public DomainUserInfosResult(DomainAccessStatus domainAccessStatus, DomainUser domainUser, DomainMediasResult domainMediasResult) {
        super(domainAccessStatus);
        this.domainUser = domainUser;
        this.domainMediasResult = domainMediasResult;
    }
}
