package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainGabResult extends DomainResultable {

    private int id;

    private String content;

    private String date;

    private DomainUserResult user;

    public DomainGabResult(final DomainAccessStatus domainAccessStatus) {
        super(domainAccessStatus);
    }

    public DomainGabResult(final DomainAccessStatus domainAccessStatus, final int id, final String content, final String date, final DomainUserResult user) {
        super(domainAccessStatus);
        this.id = id;
        this.content = content;
        this.date = date;
        this.user = user;
    }
}
