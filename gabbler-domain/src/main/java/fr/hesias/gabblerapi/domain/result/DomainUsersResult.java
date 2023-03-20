package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DomainUsersResult extends DomainResultable {

    private List<DomainUserResult> users;


    public DomainUsersResult(final DomainAccessStatus domainAccessStatus) {
        super(domainAccessStatus);
    }

    public DomainUsersResult(final DomainAccessStatus domainAccessStatus,
                             final List<DomainUserResult> users) {
        super(domainAccessStatus);
        this.users = users;
    }

    @Override
    public String toString() {
        return "DomainUsersResult{" +
                "users=" + users +
                '}';
    }
}
