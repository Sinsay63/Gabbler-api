package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainGab;
import fr.hesias.gabblerapi.domain.model.DomainGabCreation;
import fr.hesias.gabblerapi.domain.result.DomainGabCreationResult;
import fr.hesias.gabblerapi.domain.result.DomainGabResult;
import fr.hesias.gabblerapi.domain.result.DomainGabsResult;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.GabDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.InteractionDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.ActionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.INTERNAL_ERROR;
import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Slf4j
public class GabPersisterService {

    private final GabDao gabDao;

    private final InteractionDao interactionDao;

    private final GabblerInfraMapper gabblerInfraMapper;

    public GabPersisterService(final GabDao gabDao, final InteractionDao interactionDao, final GabblerInfraMapper gabblerInfraMapper) {

        this.gabDao = gabDao;
        this.interactionDao = interactionDao;
        this.gabblerInfraMapper = gabblerInfraMapper;
    }

    @Transactional(readOnly = true)
    public DomainGabResult getGabById(final int id) {

        DomainAccessStatus domainAccessStatus = OK;
        DomainGab domainGab = null;
        try {
            final Gab gab = gabDao.getGabById(id);
            if (gab != null) {

                domainGab = gabblerInfraMapper.toGabToDomainGab(gab);
                final HashMap<String, Integer> interactions = getInteractionCountByGab(gab);
                int nbComments = gabDao.getCommentsByParentGabId(id).size();
                domainGab.setNbLikes(interactions.get("like"));
                domainGab.setNbDislikes(interactions.get("dislike"));
                domainGab.setNbComments(nbComments);

            }

        } catch (final Exception e) {
            log.error("[NA] Erreur survenue lors de la récupération des utilisateurs", e);
            domainAccessStatus = INTERNAL_ERROR;
        }


        return new DomainGabResult(domainAccessStatus, domainGab);
    }

    @Transactional(readOnly = true)
    public DomainGabsResult getGabs() {

        List<DomainGabResult> domainGab = new ArrayList<>();
        DomainAccessStatus domainAccessStatus = OK;
        try {
            final List<Gab> gabs = gabDao.getGabs();
            if (isNotEmpty(gabs)) {
                for (final Gab gab : gabs) {
                    domainGab.add(getGabById(gab.getId()));
                }
            }

        } catch (final Exception e) {
            log.error("[NA] Erreur survenue lors de la récupération de tous les gabs", e);
            domainAccessStatus = INTERNAL_ERROR;
        }
        return new DomainGabsResult(domainAccessStatus, domainGab);
    }

    @Transactional(readOnly = true)
    public DomainGabsResult getCommentsByParentGabId(final int parentGabId) {

        List<DomainGabResult> domainGab = new ArrayList<>();
        DomainAccessStatus domainAccessStatus = OK;
        try {
            final List<Gab> gabs = gabDao.getCommentsByParentGabId(parentGabId);
            if (isNotEmpty(gabs)) {
                for (final Gab gab : gabs) {
                    domainGab.add(getGabById(gab.getId()));
                }
            }

        } catch (final Exception e) {
            log.error("[NA] Erreur survenue lors de la récupération des commentaires", e);
            domainAccessStatus = INTERNAL_ERROR;
        }
        return new DomainGabsResult(domainAccessStatus, domainGab);
    }


    public DomainGabCreationResult createGab(final DomainGabCreationResult domainGabCreationResult) {

        DomainAccessStatus domainAccessStatus = OK;
        DomainGabCreation domainGabCreation = domainGabCreationResult.getDomainGabCreation();
        try {
            gabDao.createGab(gabblerInfraMapper.toDomainGabCreationToGab(domainGabCreation));
        } catch (final IllegalArgumentException e) {
            log.error("[NA] Erreur survenue lors de la création du gab", e);
            domainAccessStatus = INTERNAL_ERROR;
        }
        return new DomainGabCreationResult(domainAccessStatus, null);
    }

    private HashMap<String, Integer> getInteractionCountByGab(Gab gab) {
        HashMap<String, Integer> interactionCountByGab = new HashMap<>();
        interactionCountByGab.put("like", interactionDao.countInteractionByActionAndGabId(ActionEnum.LIKE, gab.getId()));
        interactionCountByGab.put("dislike", interactionDao.countInteractionByActionAndGabId(ActionEnum.DISLIKE, gab.getId()));
        return interactionCountByGab;
    }

}
