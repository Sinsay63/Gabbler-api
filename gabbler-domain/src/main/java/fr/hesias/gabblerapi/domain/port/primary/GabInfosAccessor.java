package fr.hesias.gabblerapi.domain.port.primary;

import fr.hesias.gabblerapi.domain.result.DomainGabCreationResult;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainGabsResult;
import fr.hesias.gabblerapi.domain.result.DomainSearchResult;

public interface GabInfosAccessor
{

    DomainGabsResult getGabs();

    DomainGabResult getGabById(int id);

    DomainGabsResult getCommentsByParentGabId(int parentGabId);

    DomainGabResult createGab(DomainGabCreationResult domainGabCreationResult);

    DomainGabsResult getFeedUserNotConnected();

    DomainGabsResult getFeedUserConnected(String userUuid);

    DomainSearchResult getResultForSearch(String content);

}
