package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainGab;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainGabResult extends DomainResultable {

    private DomainGab gab;

    public DomainGabResult(final DomainAccessStatus domainAccessStatus) {
        super(domainAccessStatus);
    }

    public DomainGabResult(final DomainAccessStatus domainAccessStatus, final DomainGab gab) {
        super(domainAccessStatus);
        this.gab = gab;
    }
}
