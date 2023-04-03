package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.GabDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.INTERNAL_ERROR;

@Slf4j
public class GabPersisterService {

    private final GabDao gabDao;

    private final GabblerInfraMapper gabblerInfraMapper;

    public GabPersisterService(final GabDao gabDao, final GabblerInfraMapper gabblerInfraMapper) {

        this.gabDao = gabDao;
        this.gabblerInfraMapper = gabblerInfraMapper;
    }

    @Transactional(readOnly = true)
    public DomainGabResult getGabById(final int id) {

        DomainAccessStatus domainAccessStatus = null;
        DomainGabResult domainGab = new DomainGabResult(domainAccessStatus);
        try {
            final Gab gab = gabDao.getGabById(id);
            if (gab != null) {

                final DomainUserResult domainUserResult = new DomainUserResult(domainAccessStatus);

            }

        } catch (final Exception e) {
            log.error("[NA] Erreur survenue lors de la récupération des utilisateurs", e);
            domainAccessStatus = INTERNAL_ERROR;
            domainGab = new DomainGabResult(domainAccessStatus);
        }


        return domainGab;
    }

//    @Transactional(rollbackFor = Exception.class)
//    public DomainGabResult createGab() {
//
//        DomainAccessStatus domainAccessStatus = DomainAccessStatus.OK;
//        try {
//            final Gab gab = new Gab();
//            gab.setContent("test");
//            gabDao.createGab(gab);
//        } catch (final Exception e) {
//            log.error("[NA] Erreur survenue lors de la récupération des utilisateurs", e);
//            domainAccessStatus = INTERNAL_ERROR;
//        }
//
//        return
//    }

}
