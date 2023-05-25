package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.desc.api.server.model.Gab;
import fr.hesias.gabblerapi.desc.api.server.model.GabCreation;
import fr.hesias.gabblerapi.desc.api.server.model.Media;
import fr.hesias.gabblerapi.domain.model.DomainGab;
import fr.hesias.gabblerapi.domain.model.DomainGabCreation;
import fr.hesias.gabblerapi.domain.model.DomainMedia;
import fr.hesias.gabblerapi.domain.result.*;

import java.util.ArrayList;
import java.util.List;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;

public class GabApiMapper
{

    private final UserApiMapper userApiMapper;

    public GabApiMapper(final UserApiMapper userApiMapper)
    {

        super();
        this.userApiMapper = userApiMapper;
    }

    public Gab toGab(final DomainGab domainGab, final DomainMediasResult domainMediasResult)
    {

        final Gab gab = new Gab();

        if (domainGab != null)
        {
            gab.setId(domainGab.getId());
            gab.setContent(domainGab.getContent());
            gab.setPostDate(domainGab.getPostDate().toString());
            gab.setNbLikes(domainGab.getNbLikes());
            gab.setNbDislikes(domainGab.getNbDislikes());
            gab.setNbComments(domainGab.getNbComments());
            gab.setMedias(toDomainMediasResultToMediasList(domainMediasResult));
            gab.setUser(userApiMapper.toDomainUserToUser(domainGab.getUser()));
            gab.getUser().setIsPremium(domainGab.getUser().isPremium());
        }
        return gab;
    }

    public Media toDomainMediaToMedia(final DomainMedia domainMedia)
    {

        final Media media = new Media();

        if (domainMedia != null)
        {
            media.setId(domainMedia.getId());
            media.setUrl(domainMedia.getUrl());
            media.setType(domainMedia.getType());
            media.setDate(domainMedia.getDate().toString());
        }
        return media;
    }

    public List<Media> toDomainMediasResultToMediasList(DomainMediasResult domainMediasResult)
    {

        List<Media> mediaList = new ArrayList<>();

        if (domainMediasResult.isOk())
        {
            for (DomainMediaResult domainMediaResult : domainMediasResult.getMedias())
            {
                mediaList.add(toDomainMediaToMedia(domainMediaResult.getDomainMedia()));
            }
        }
        return mediaList;
    }

    public DomainGabCreationResult toGabCreationToDomainGabCreationResult(final GabCreation gab)
    {

        final DomainGabCreation domainGabCreation = new DomainGabCreation();

        domainGabCreation.setContent(gab.getContent());
        domainGabCreation.setParentId(gab.getParentGabId() == null ? 0 : gab.getParentGabId());
        domainGabCreation.setUserUuid(gab.getUserUuid());
        return new DomainGabCreationResult(OK, domainGabCreation);
    }

    public List<Gab> toDomainGabsResultToGabsList(DomainGabsResult domainGabsResult)
    {

        final List<Gab> gabsList = new ArrayList<>();

        if (domainGabsResult != null)
        {

            for (DomainGabResult domainGabResult : domainGabsResult.getGabs())
            {
                gabsList.add(toGab(domainGabResult.getGab(), domainGabResult.getMedias()));
            }
        }
        return gabsList;
    }

    public List<Gab> toDomainGabListToGabsList(List<DomainGab> gabs)
    {

        final List<Gab> gabsList = new ArrayList<>();

        if (gabs != null)
        {

            for (DomainGab domainGab : gabs)
            {
                gabsList.add(toGab(domainGab, null));
            }
        }
        return gabsList;
    }

}
