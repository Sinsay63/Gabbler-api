package fr.hesias.gabblerapi.infrastructure.persister.persistence.adapter;

import fr.hesias.gabblerapi.domain.port.secondary.GabPersister;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.infrastructure.persister.service.GabPersisterService;

public class GabPersisterAdapter implements GabPersister {


    private GabPersisterService gabPersisterService;

    @Override
    public DomainGabResult getGabById(final int id) {
        return this.gabPersisterService.getGabById(id);
    }
}
