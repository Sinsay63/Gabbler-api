package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainGab;
import fr.hesias.gabblerapi.domain.model.DomainGabCreation;
import fr.hesias.gabblerapi.domain.result.*;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.GabDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.InteractionDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.MediaDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.UserDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Media;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.ActionTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.INTERNAL_ERROR;
import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Slf4j
public class GabPersisterService
{

    private final GabDao gabDao;

    private final UserDao userDao;

    private final MediaDao mediaDao;

    private final InteractionDao interactionDao;

    private final GabblerInfraMapper gabblerInfraMapper;

    public GabPersisterService(final GabDao gabDao,
                               final UserDao userDao,
                               MediaDao mediaDao,
                               final InteractionDao interactionDao,
                               final GabblerInfraMapper gabblerInfraMapper)
    {

        this.gabDao = gabDao;
        this.userDao = userDao;
        this.mediaDao = mediaDao;
        this.interactionDao = interactionDao;
        this.gabblerInfraMapper = gabblerInfraMapper;
    }

    @Transactional(readOnly = true)
    public DomainGabResult getGabById(final int id)
    {

        DomainAccessStatus domainAccessStatus = OK;
        DomainGab domainGab = null;
        DomainMediasResult domainMediasResult = null;
        try
        {
            final Gab gab = gabDao.getGabById(id);
            if (gab != null)
            {

                domainGab = gabblerInfraMapper.toGabToDomainGab(gab);
                final HashMap<String, Integer> interactions = getInteractionCountByGab(gab);
                int nbComments = gabDao.getCommentsByParentGabId(id).size();
                domainGab.setNbLikes(interactions.get("like"));
                domainGab.setNbDislikes(interactions.get("dislike"));
                domainGab.setNbComments(nbComments);
                List<Media> gabMediaList = mediaDao.getMediaByGabId(domainGab.getId());
                List<Media> userMediaList = mediaDao.getMediaAvatarAndBannerByUserUuid(domainGab.getUser().getUuid());
                domainGab.getUser().setMedias(this.gabblerInfraMapper.toMediaListToDomainMediaList(userMediaList));
                domainMediasResult = this.gabblerInfraMapper.toMediaListToDomainMediasResult(gabMediaList);

            }

        }
        catch (final Exception e)
        {
            log.error("[NA] Erreur survenue lors de la récupération des utilisateurs", e);
            domainAccessStatus = INTERNAL_ERROR;
        }


        return new DomainGabResult(domainAccessStatus, domainGab, domainMediasResult);
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
                    domainGab.add(getGabById(gab.getId()));
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

    @Transactional(readOnly = true)
    public DomainGabsResult getCommentsByParentGabId(final int parentGabId)
    {

        List<DomainGabResult> domainGab = new ArrayList<>();
        DomainAccessStatus domainAccessStatus = OK;
        try
        {
            final List<Gab> gabs = gabDao.getCommentsByParentGabId(parentGabId);
            if (isNotEmpty(gabs))
            {
                for (final Gab gab : gabs)
                {
                    domainGab.add(getGabById(gab.getId()));
                }
            }

        }
        catch (final Exception e)
        {
            log.error("[NA] Erreur survenue lors de la récupération des commentaires", e);
            domainAccessStatus = INTERNAL_ERROR;
        }
        return new DomainGabsResult(domainAccessStatus, domainGab);
    }


    public DomainGabCreationResult createGab(final DomainGabCreationResult domainGabCreationResult)
    {

        DomainAccessStatus domainAccessStatus = OK;
        DomainGabCreation domainGabCreation = domainGabCreationResult.getDomainGabCreation();
        try
        {
            gabDao.createGab(gabblerInfraMapper.toDomainGabCreationToGab(domainGabCreation));
        }
        catch (final IllegalArgumentException e)
        {
            log.error("[NA] Erreur survenue lors de la création du gab", e);
            domainAccessStatus = INTERNAL_ERROR;
        }
        return new DomainGabCreationResult(domainAccessStatus, null);
    }

    @Transactional(readOnly = true)
    public DomainGabsResult getFeed()
    {

        List<DomainGabResult> domainGab = new ArrayList<>();
        DomainAccessStatus domainAccessStatus = OK;
        try
        {
            final List<Gab> gabs = gabDao.getFeed();
            if (isNotEmpty(gabs))
            {
                for (final Gab gab : gabs)
                {
                    domainGab.add(getGabById(gab.getId()));
                }
            }

        }
        catch (final Exception e)
        {
            log.error("[NA] Erreur survenue lors de la récupération des commentaires", e);
            domainAccessStatus = INTERNAL_ERROR;
        }
        return new DomainGabsResult(domainAccessStatus, domainGab);

    }

    @Transactional(readOnly = true)
    public DomainSearchResult getResultForSearch(String researchContent)
    {

        List<Gab> gabList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        DomainGabsResult domainGabsResult = null;

        try
        {
            gabList = gabDao.getGabsBySearch(researchContent);
            userList = userDao.getUsersBySearch(researchContent);
        }
        catch (final Exception e)
        {
            log.error("[NA] Erreur survenue lors de la récupération des commentaires", e);
        }
        return new DomainSearchResult(OK);
    }


    private HashMap<String, Integer> getInteractionCountByGab(Gab gab)
    {

        HashMap<String, Integer> interactionCountByGab = new HashMap<>();
        interactionCountByGab.put("like",
                                  interactionDao.countInteractionByActionAndGabId(ActionTypeEnum.LIKE, gab.getId()));
        interactionCountByGab.put("dislike",
                                  interactionDao.countInteractionByActionAndGabId(ActionTypeEnum.DISLIKE, gab.getId()));
        return interactionCountByGab;
    }

}
