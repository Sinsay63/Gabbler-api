package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainMedia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainMediaResult extends DomainResultable {

    private DomainMedia domainMedia;

    public DomainMediaResult(DomainAccessStatus domainAccessStatus) {
        super(domainAccessStatus);
    }

    public DomainMediaResult(DomainAccessStatus domainAccessStatus, DomainMedia domainMedia) {
        super(domainAccessStatus);
        this.domainMedia = domainMedia;
    }

}
