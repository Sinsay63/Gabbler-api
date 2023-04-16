package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainUserRegistration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainUserRegistrationInfosResult {

    private DomainUserRegistration userRegistration;

    public DomainUserRegistrationInfosResult() {
    }

    public DomainUserRegistrationInfosResult(DomainUserRegistration userRegistration) {
        this.userRegistration = userRegistration;
    }
}
