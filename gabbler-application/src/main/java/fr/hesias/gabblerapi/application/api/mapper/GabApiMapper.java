package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.desc.api.server.model.*;
import fr.hesias.gabblerapi.domain.model.DomainGab;
import fr.hesias.gabblerapi.domain.model.DomainGabCreation;
import fr.hesias.gabblerapi.domain.model.DomainMedia;
import fr.hesias.gabblerapi.domain.result.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;

public class GabApiMapper {

    private final UserApiMapper userApiMapper;

    public GabApiMapper(final UserApiMapper userApiMapper) {
        super();
        this.userApiMapper = userApiMapper;
    }

    public Gab toGab(final DomainGab domainGab, final DomainMediasResult domainMediasResult) {

        final Gab gab = new Gab();

        if (domainGab != null) {
            gab.setId(domainGab.getId());
            gab.setContent(domainGab.getContent());
            gab.setPostDate(domainGab.getPostDate().toString());
            gab.setNbLikes(domainGab.getNbLikes());
            gab.setNbDislikes(domainGab.getNbDislikes());
            gab.setNbComments(domainGab.getNbComments());
            gab.setMedias(toDomainMediasResultToMedias(domainMediasResult));
            gab.setUser(userApiMapper.toDomainUserToUser(domainGab.getUser()));
        }
        return gab;
    }

    public Gabs toGabs(final DomainGabsResult domainGabsResult) {
        final Gabs gabs = new Gabs();
        if (domainGabsResult != null) {
            final List<Gab> gabsList = new ArrayList<>();

            for (DomainGabResult domainGabResult : domainGabsResult.getGabs()) {
                gabsList.add(toGab(domainGabResult.getGab(), domainGabResult.getMedias()));
            }
            gabs.setGabs(gabsList);
        }
        return gabs;
    }

    public Media toDomainMediaToMedia(final DomainMedia domainMedia) {

        final Media media = new Media();

        if (domainMedia != null) {
            media.setId(domainMedia.getId());
            media.setUrl(domainMedia.getUrl());
            media.setType(domainMedia.getType());
            media.setDate(domainMedia.getDate().toString());
        }
        return media;
    }

    public Medias toDomainMediasResultToMedias(DomainMediasResult domainMediasResult) {
        Medias medias = new Medias();
        List<Media> mediaList = new ArrayList<>();

        if (domainMediasResult.isOk()) {
            for (DomainMediaResult domainMediaResult : domainMediasResult.getMedias()) {
                mediaList.add(toDomainMediaToMedia(domainMediaResult.getDomainMedia()));
            }
            medias.setMedias(mediaList);
        }


        return medias;
    }

    public DomainGabCreationResult toGabCreationToDomainGabCreationResult(final GabCreation gab) {
        final DomainGabCreation domainGabCreation = new DomainGabCreation();

        domainGabCreation.setContent(gab.getContent());
        domainGabCreation.setPostDate(LocalDateTime.parse(gab.getPostDate()));
        domainGabCreation.setParentId(gab.getParentGabId() == null ? 0 : gab.getParentGabId());
        domainGabCreation.setUserUuid(gab.getUserUuid());
        return new DomainGabCreationResult(OK, domainGabCreation);
    }

}
