package fr.hesias.gabblerapi.application.adapter;

import fr.hesias.gabblerapi.domain.port.primary.GabInfosAccessor;
import fr.hesias.gabblerapi.domain.result.DomainGabCreationResult;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainGabsResult;
import fr.hesias.gabblerapi.domain.result.DomainSearchResult;

public class GabInfosAccessorAdapter
{

    private final GabInfosAccessor gabInfosAccessor;

    public GabInfosAccessorAdapter(final GabInfosAccessor gabInfosAccessor)
    {

        super();
        this.gabInfosAccessor = gabInfosAccessor;
    }

    public DomainGabsResult getGabs()
    {

        return gabInfosAccessor.getGabs();
    }

    public DomainGabResult getGabById(int id)
    {

        return gabInfosAccessor.getGabById(id);
    }

    public DomainGabsResult getCommentsByParentGabId(int parentGabId)
    {

        return gabInfosAccessor.getCommentsByParentGabId(parentGabId);
    }

    public DomainGabCreationResult createGab(DomainGabCreationResult domainGabCreationResult)
    {

        return gabInfosAccessor.createGab(domainGabCreationResult);
    }

    public DomainGabsResult getFeedUserNotConnected()
    {

        return gabInfosAccessor.getFeedUserNotConnected();
    }

    public DomainGabsResult getFeedUserConnected(String userUuid)
    {

        return gabInfosAccessor.getFeedUserConnected(userUuid);
    }

    public DomainSearchResult getResultForSearch(String content)
    {

        return gabInfosAccessor.getResultForSearch(content);
    }

}
