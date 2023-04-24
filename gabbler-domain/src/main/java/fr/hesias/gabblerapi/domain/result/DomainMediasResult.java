package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DomainMediasResult extends DomainResultable {

    private List<DomainMediaResult> medias;


    public DomainMediasResult(DomainAccessStatus domainAccessStatus) {
        super(domainAccessStatus);
    }

    public DomainMediasResult(DomainAccessStatus domainAccessStatus, List<DomainMediaResult> medias) {
        super(domainAccessStatus);
        this.medias = medias;
    }
}
