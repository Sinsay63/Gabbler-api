package fr.hesias.gabblerapi.domain.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainGab;
import fr.hesias.gabblerapi.domain.port.primary.GabInfosAccessor;
import fr.hesias.gabblerapi.domain.port.secondary.GabPersister;
import fr.hesias.gabblerapi.domain.result.*;

import java.util.ArrayList;
import java.util.List;

public class GabInfosAccessorImpl implements GabInfosAccessor
{


    private final GabPersister gabPersister;

    public GabInfosAccessorImpl(final GabPersister gabPersister)
    {

        super();
        this.gabPersister = gabPersister;
    }

    @Override
    public DomainGabsResult getGabs()
    {

        List<DomainGabResult> gabs = new ArrayList<>();
        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;

        DomainGabsResult domainGabsResult = gabPersister.getGabs();

        if (domainGabsResult.isOk())
        {

            gabs = domainGabsResult.getGabs();
        }
        else
        {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }
        return new DomainGabsResult(domainAccessStatus, gabs);
    }

    @Override
    public DomainGabResult getGabById(int id)
    {

        DomainGab gab = null;
        DomainMediasResult medias = null;
        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
        DomainGabResult gabResult = gabPersister.getGabById(id);

        if (gabResult != null && gabResult.isOk())
        {
            gab = gabResult.getGab();
            medias = gabResult.getMedias();
        }
        else
        {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }
        return new DomainGabResult(domainAccessStatus, gab, medias);
    }

    @Override
    public DomainGabsResult getCommentsByParentGabId(int parentGabId)
    {

        List<DomainGabResult> gabs = new ArrayList<>();
        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;

        DomainGabsResult domainGabsResult = gabPersister.getCommentsByParentGabId(parentGabId);
        if (domainGabsResult.isOk())
        {

            gabs = domainGabsResult.getGabs();
        }
        else
        {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }
        return new DomainGabsResult(domainAccessStatus, gabs);
    }

    @Override
    public DomainGabCreationResult createGab(DomainGabCreationResult domainGabCreationResult)
    {

        DomainAccessStatus domainAccessStatus;

        if (gabPersister.createGab(domainGabCreationResult).isOk())
        {
            domainAccessStatus = DomainAccessStatus.OK;

        }
        else
        {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }
        return new DomainGabCreationResult(domainAccessStatus, null);
    }

    @Override
    public DomainGabsResult getFeed()
    {

        List<DomainGabResult> gabs = new ArrayList<>();
        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;

        DomainGabsResult domainGabsResult = gabPersister.getFeed();

        if (domainGabsResult.isOk())
        {

            gabs = domainGabsResult.getGabs();
        }
        else
        {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }
        return new DomainGabsResult(domainAccessStatus, gabs);
    }

    /**
     * @param content la recherche de l'utilisateur
     * @return l'objet DomainSearchResult qui contient la liste des gabs et users trouvés
     */
    @Override
    public DomainSearchResult getResultForSearch(String content)
    {

        DomainGabsResult gabs = null;
        DomainUsersResult users = null;
        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;

        DomainSearchResult domainSearchResult = gabPersister.getResultForSearch(content);

        if (domainSearchResult.isOk())
        {

            gabs = domainSearchResult.getDomainGabsResult();
            users = domainSearchResult.getDomainUsersResult();
        }
        else
        {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }
        return new DomainSearchResult(domainAccessStatus, gabs, users);
    }

}
