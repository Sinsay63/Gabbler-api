package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainGab;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainGabsResult;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.GabDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.INTERNAL_ERROR;
import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Slf4j
public class GabPersisterService
{

    private final GabDao gabDao;

    private final GabblerInfraMapper gabblerInfraMapper;

    public GabPersisterService(final GabDao gabDao, final GabblerInfraMapper gabblerInfraMapper)
    {

        this.gabDao = gabDao;
        this.gabblerInfraMapper = gabblerInfraMapper;
    }

    @Transactional(readOnly = true)
    public DomainGabResult getGabById(final int id)
    {

        DomainAccessStatus domainAccessStatus = OK;
        DomainGab domainGab = null;
        try
        {
            final Gab gab = gabDao.getGabById(id);
            if (gab != null)
            {

                domainGab = gabblerInfraMapper.toGabToDomainGab(gab);

            }

        }
        catch (final Exception e)
        {
            log.error("[NA] Erreur survenue lors de la récupération des utilisateurs", e);
            domainAccessStatus = INTERNAL_ERROR;
        }


        return new DomainGabResult(domainAccessStatus, domainGab);
    }

    @Transactional(readOnly = true)
    public DomainGabsResult getGabs()
    {

        List<DomainGabResult> domainGab = new ArrayList<>();
        DomainAccessStatus domainAccessStatus = OK;
        try
        {
            final List<Gab> gabs = gabDao.getGabs();
            if (isNotEmpty(gabs))
            {

                for (final Gab gab : gabs)
                {
                    domainGab.add(gabblerInfraMapper.toDomainGabToDomainGabResult(domainAccessStatus,
                                                                                  gabblerInfraMapper.toGabToDomainGab(
                                                                                          gab)));
                }
            }

        }
        catch (final Exception e)
        {
            log.error("[NA] Erreur survenue lors de la récupération de tous les gabs", e);
            domainAccessStatus = INTERNAL_ERROR;
        }
        return new DomainGabsResult(domainAccessStatus, domainGab);
    }

}
