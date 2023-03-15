package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;

public class DomainUserResult extends DomainResultable {

    public DomainUserResult(final DomainAccessStatus domainAccessStatus) {
        super(domainAccessStatus);
    }
}
