package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.result.DomainGabCreationResult;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainGabsResult;

public interface GabPersister {

    DomainGabResult getGabById(final int id);

    DomainGabsResult getGabs();

    DomainGabsResult getCommentsByParentGabId(final int parentGabId);

    DomainGabCreationResult createGab(final DomainGabCreationResult domainGabCreationResult);

    DomainGabsResult getFeed();


}
