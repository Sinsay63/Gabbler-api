package fr.hesias.gabblerapi.domain.port.primary;

import fr.hesias.gabblerapi.domain.result.DomainGabCreationResult;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainGabsResult;

public interface GabInfosAccessor {

    DomainGabsResult getGabs();

    DomainGabResult getGabById(int id);

    DomainGabsResult getCommentsByParentGabId(int parentGabId);

    DomainGabCreationResult createGab(DomainGabCreationResult domainGabCreationResult);

    DomainGabsResult getFeed();
}
