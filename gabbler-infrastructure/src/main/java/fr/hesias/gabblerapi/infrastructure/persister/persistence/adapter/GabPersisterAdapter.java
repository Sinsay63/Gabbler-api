package fr.hesias.gabblerapi.infrastructure.persister.persistence.adapter;

import fr.hesias.gabblerapi.domain.port.secondary.GabPersister;
import fr.hesias.gabblerapi.domain.result.DomainGabCreationResult;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainGabsResult;
import fr.hesias.gabblerapi.domain.result.DomainSearchResult;
import fr.hesias.gabblerapi.infrastructure.persister.service.GabPersisterService;

public class GabPersisterAdapter implements GabPersister
{


    private final GabPersisterService gabPersisterService;

    public GabPersisterAdapter(final GabPersisterService gabPersisterService)
    {

        super();
        this.gabPersisterService = gabPersisterService;
    }

    @Override
    public DomainGabResult getGabById(final int id)
    {

        return this.gabPersisterService.getGabById(id);
    }

    /**
     * @return
     */
    @Override
    public DomainGabsResult getGabs()
    {

        return this.gabPersisterService.getGabs();
    }

    @Override
    public DomainGabsResult getCommentsByParentGabId(int parentGabId)
    {

        return this.gabPersisterService.getCommentsByParentGabId(parentGabId);
    }

    @Override
    public DomainGabCreationResult createGab(final DomainGabCreationResult domainGabCreationResult)
    {

        return this.gabPersisterService.createGab(domainGabCreationResult);
    }

    @Override
    public DomainGabsResult getFeedUserNotConnected()
    {

        return this.gabPersisterService.getFeedUserNotConnected();
    }

    @Override
    public DomainGabsResult getFeedUserConnected(String userUuid)
    {

        return this.gabPersisterService.getFeedUserConnected(userUuid);
    }

    @Override
    public DomainSearchResult getResultForSearch(String content)
    {

        return this.gabPersisterService.getResultForSearch(content);
    }

}
