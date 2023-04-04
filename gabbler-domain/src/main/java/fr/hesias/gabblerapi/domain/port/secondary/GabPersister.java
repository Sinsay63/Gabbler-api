package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainGabsResult;

public interface GabPersister {

    DomainGabResult getGabById(final int id);

    DomainGabsResult getGabs();


}
