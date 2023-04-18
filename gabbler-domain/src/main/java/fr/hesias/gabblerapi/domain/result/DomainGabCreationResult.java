package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainGabCreation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainGabCreationResult extends DomainResultable {


    private DomainGabCreation domainGabCreation;

    public DomainGabCreationResult(DomainAccessStatus domainAccessStatus) {
        super(domainAccessStatus);
    }

    public DomainGabCreationResult(DomainAccessStatus domainAccessStatus, DomainGabCreation domainGabCreation) {
        super(domainAccessStatus);
        this.domainGabCreation = domainGabCreation;
    }


}
