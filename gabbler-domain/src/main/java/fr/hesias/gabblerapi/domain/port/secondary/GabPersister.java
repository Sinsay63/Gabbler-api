package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.result.DomainGabResult;

public interface GabPersister {

    DomainGabResult getGabById(final int id);


}
