package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DomainUsersInfosResult extends DomainResultable {

    private List<DomainUserInfosResult> domainUserInfosResults;

    public DomainUsersInfosResult(DomainAccessStatus domainAccessStatus) {
        super(domainAccessStatus);
    }

    public DomainUsersInfosResult(DomainAccessStatus domainAccessStatus, List<DomainUserInfosResult> domainUserInfosResults) {
        super(domainAccessStatus);
        this.domainUserInfosResults = domainUserInfosResults;
    }
}
