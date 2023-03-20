package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.result.DomainUserResult;

public interface GabPersister {

    DomainUserResult getGabById(final int id);
}
