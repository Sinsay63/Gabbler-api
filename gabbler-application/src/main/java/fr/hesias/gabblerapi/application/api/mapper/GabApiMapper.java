package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.desc.api.server.model.Gab;
import fr.hesias.gabblerapi.desc.api.server.model.Gabs;
import fr.hesias.gabblerapi.domain.model.DomainGab;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainGabsResult;

import java.util.ArrayList;
import java.util.List;

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

}
