package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainGab;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainGabResult extends DomainResultable {

    private DomainGab gab;

    private DomainMediasResult medias;

    public DomainGabResult(final DomainAccessStatus domainAccessStatus) {

        super(domainAccessStatus);
    }

    public DomainGabResult(final DomainAccessStatus domainAccessStatus, final DomainGab gab, final DomainMediasResult medias) {

        super(domainAccessStatus);
        this.gab = gab;
        this.medias = medias;
    }

}
