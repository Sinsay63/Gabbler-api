package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainUser;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.GabDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import org.springframework.transaction.annotation.Transactional;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.INTERNAL_ERROR;

public class GabPersisterService {

    private final GabDao gabDao;

    public GabPersisterService(final GabDao gabDao) {
        this.gabDao = gabDao;
    }

    @Transactional(readOnly = true)
    public DomainGabResult getGabById(final int id) {
        DomainAccessStatus domainAccessStatus = null;
        DomainGabResult domainGab = new DomainGabResult(domainAccessStatus);
        try {
            final Gab gab = gabDao.getGabById(id);
            if (gab != null) {
                domainGab.setId(gab.getId());
                domainGab.setContent(gab.getContent());
                domainGab.setDate(gab.getPostDate().toString());

                final DomainUserResult domainUserResult = new DomainUserResult(domainAccessStatus);
                domainUserResult.setDomainUser(new DomainUser(
                        gab.getUser().getId(),
                        gab.getUser().getUsername(),
                        gab.getUser().getFirstname(),
                        gab.getUser().getLastname(),
                        gab.getUser().getBirthday().toString(),
                        gab.getUser().getEmail(),
                        gab.getUser().getBiography()
                ));
                domainGab.setUser(domainUserResult);

            }

        } catch (final Exception e) {
//            log.error("[NA] Erreur survenue lors de la récupération des utilisateurs", e);
            domainAccessStatus = INTERNAL_ERROR;
            domainGab = new DomainGabResult(domainAccessStatus);
        }


        return domainGab;
    }
}
