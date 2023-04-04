package fr.hesias.gabblerapi.domain.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainGab;
import fr.hesias.gabblerapi.domain.port.primary.GabInfosAccessor;
import fr.hesias.gabblerapi.domain.port.secondary.GabPersister;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainGabsResult;

import java.util.List;

public class GabInfosAccessorImpl implements GabInfosAccessor {


    private final GabPersister gabPersister;

    public GabInfosAccessorImpl(final GabPersister gabPersister) {

        super();
        this.gabPersister = gabPersister;
    }

    @Override
    public DomainGabsResult getGabs() {

        List<DomainGabResult> gabs = null;
        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;

        if (gabPersister.getGabs() != null) {

            gabs = gabPersister.getGabs().getGabs();
        }
        else {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }
        return new DomainGabsResult(domainAccessStatus, gabs);
    }

    @Override
    public DomainGabResult getGabById(int id) {

        DomainGab gab = null;
        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;

        if (gabPersister.getGabById(id) != null) {

            gab = gabPersister.getGabById(id).getGab();
        }
        else {
            domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
        }
        return new DomainGabResult(domainAccessStatus, gab);
    }

}
