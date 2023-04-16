package fr.hesias.gabblerapi.application.adapter;

import fr.hesias.gabblerapi.domain.port.primary.GabInfosAccessor;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainGabsResult;

public class GabInfosAccessorAdapter {

    private final GabInfosAccessor gabInfosAccessor;

    public GabInfosAccessorAdapter(final GabInfosAccessor gabInfosAccessor) {

        super();
        this.gabInfosAccessor = gabInfosAccessor;
    }

    public DomainGabsResult getGabs() {

        return gabInfosAccessor.getGabs();
    }

    public DomainGabResult getGabsById(int id) {

        return gabInfosAccessor.getGabById(id);
    }

    public DomainGabsResult getCommentsByParentGabId(int parentGabId) {
        return gabInfosAccessor.getCommentsByParentGabId(parentGabId);
    }

}
