package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.desc.api.server.model.Gab;
import fr.hesias.gabblerapi.desc.api.server.model.GabCreation;
import fr.hesias.gabblerapi.desc.api.server.model.Gabs;
import fr.hesias.gabblerapi.domain.model.DomainGab;
import fr.hesias.gabblerapi.domain.model.DomainGabCreation;
import fr.hesias.gabblerapi.domain.result.DomainGabCreationResult;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainGabsResult;

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

    public Gab toGab(final DomainGab domainGab) {

        final Gab gab = new Gab();

        if (domainGab != null) {
            gab.setId(domainGab.getId());
            gab.setContent(domainGab.getContent());
            gab.setPostDate(domainGab.getPostDate().toString());
            gab.setNbLikes(domainGab.getNbLikes());
            gab.setNbDislikes(domainGab.getNbDislikes());
            gab.setNbComments(domainGab.getNbComments());
            gab.setUser(userApiMapper.toUser(domainGab.getUser()));
        }
        return gab;
    }

    public Gabs toGabs(final DomainGabsResult domainGabsResult) {
        final Gabs gabs = new Gabs();
        if (domainGabsResult != null) {
            final List<Gab> gabsList = new ArrayList<>();

            for (DomainGabResult domainGabResult : domainGabsResult.getGabs()) {
                final DomainGab domainGab = domainGabResult.getGab();
                gabsList.add(toGab(domainGab));
            }
            gabs.setGabs(gabsList);
        }
        return gabs;
    }

    public DomainGabResult toGabToDomainGabResult(final Gab gab) {
        final DomainGab domainGab = new DomainGab();
        domainGab.setId(gab.getId());
        domainGab.setContent(gab.getContent());
        domainGab.setPostDate(LocalDateTime.parse(gab.getPostDate()));
        domainGab.setNbLikes(gab.getNbLikes());
        domainGab.setNbDislikes(gab.getNbDislikes());
        domainGab.setNbComments(gab.getNbComments());
        domainGab.setUser(userApiMapper.toDomainUser(gab.getUser()));
        return new DomainGabResult(OK, domainGab);
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
