package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainGab;
import fr.hesias.gabblerapi.domain.model.DomainGabCreation;
import fr.hesias.gabblerapi.domain.result.*;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.*;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Media;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.UserRelationships;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.InteractionTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.INTERNAL_ERROR;
import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;
import static fr.hesias.gabblerapi.infrastructure.persister.persistence.model.RelationshipTypeEnum.FOLLOWED;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Slf4j
public class GabPersisterService
{

    private final GabDao gabDao;

    private final UserDao userDao;

    private final MediaDao mediaDao;

    private final InteractionDao interactionDao;

    private final RelationshipsDao relationshipsDao;

    private final GabblerInfraMapper gabblerInfraMapper;

    private final UserPersisterService userPersisterService;

    public GabPersisterService(final GabDao gabDao,
                               final UserDao userDao,
                               MediaDao mediaDao,
                               final InteractionDao interactionDao,
                               final RelationshipsDao relationshipsDao,
                               final UserPersisterService userPersisterService,
                               final GabblerInfraMapper gabblerInfraMapper)
    {

        this.gabDao = gabDao;
        this.userDao = userDao;
        this.mediaDao = mediaDao;
        this.interactionDao = interactionDao;
        this.relationshipsDao = relationshipsDao;
        this.userPersisterService = userPersisterService;
        this.gabblerInfraMapper = gabblerInfraMapper;
    }

    @Transactional(readOnly = true)
    public DomainGabResult getGabById(final int id)
    {

        DomainGabResult domainGabResult = null;
        try
        {
            final Gab gab = gabDao.getGabById(id);
            if (gab != null)
            {
                domainGabResult = setDomainGabResultDataByGab(gab);

            }
        }
        catch (final Exception e)
        {
            log.error("[NA] Erreur survenue lors de la récupération des utilisateurs", e);
        }


        return domainGabResult;
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


    @Transactional(rollbackFor = Exception.class)
    public DomainGabResult createGab(final DomainGabCreationResult domainGabCreationResult)
    {

        DomainAccessStatus domainAccessStatus = OK;
        DomainGabCreation domainGabCreation = domainGabCreationResult.getDomainGabCreation();
        DomainGab domainGab = new DomainGab();
        try
        {
            var createdGab = gabDao.createGab(gabblerInfraMapper.toDomainGabCreationToGab(domainGabCreation));
            domainGab = this.gabblerInfraMapper.toGabToDomainGab(createdGab);
            var user = userPersisterService.getUserByUuid(domainGabCreation.getUserUuid());
            domainGab.setUser(user.getDomainUser());
        }
        catch (final IllegalArgumentException e)
        {
            log.error("[NA] Erreur survenue lors de la création du gab", e);
            domainAccessStatus = INTERNAL_ERROR;
        }
        return new DomainGabResult(domainAccessStatus, domainGab, null);
    }

    @Transactional(readOnly = true)
    public DomainGabsResult getFeedUserNotConnected()
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

        DomainGabsResult domainGabsResult = null;
        DomainUsersResult domainUsersResult = null;
        DomainAccessStatus domainAccessStatus = OK;

        try
        {
            domainGabsResult = setDomainGabsResultDataByGabsList(gabDao.getGabsBySearch(researchContent));
            domainUsersResult = this.userPersisterService.setDomainUserResultDataByUsersList(userDao.getUsersBySearch(
                    researchContent));

        }
        catch (final Exception e)
        {
            log.error("[NA] Erreur survenue lors de la récupération des commentaires", e);
            domainAccessStatus = INTERNAL_ERROR;
        }
        return new DomainSearchResult(domainAccessStatus, domainGabsResult, domainUsersResult);
    }


    @Transactional(readOnly = true)
    public DomainGabResult setDomainGabResultDataByGab(Gab gab)
    {

        DomainAccessStatus domainAccessStatus = OK;
        DomainGab domainGab = null;
        DomainMediasResult domainMediasResult = null;
        try
        {
            domainGab = gabblerInfraMapper.toGabToDomainGab(gab);
            final HashMap<String, Integer> interactions = getInteractionCountByGab(gab);
            int nbComments = gabDao.getCommentsByParentGabId(gab.getId()).size();
            domainGab.setNbLikes(interactions.get("like"));
            domainGab.setNbDislikes(interactions.get("dislike"));
            domainGab.setNbComments(nbComments);
            List<Media> gabMediaList = mediaDao.getMediaByGabId(domainGab.getId());
            List<Media> userMediaList = mediaDao.getMediaAvatarAndBannerByUserUuid(domainGab.getUser().getUuid());
            gab.getUser().setMedias(userMediaList);
            domainGab.setUser(gabblerInfraMapper.toUserToDomainUserRelationships(gab.getUser()));
            domainGab.getUser()
                     .setPremium(this.userPersisterService.getPremiumByUserUuid(domainGab.getUser().getUuid()));
            domainMediasResult = this.gabblerInfraMapper.toMediaListToDomainMediasResult(gabMediaList);
        }
        catch (final Exception e)
        {
            log.error("[NA] Erreur survenue lors du set des données pour un gab", e);
            domainAccessStatus = INTERNAL_ERROR;
        }
        return new DomainGabResult(domainAccessStatus, domainGab, domainMediasResult);
    }

    @Transactional(readOnly = true)
    public DomainGabsResult getFeedUserConnected(String userUuid)
    {

        DomainAccessStatus domainAccessStatus = OK;
        List<DomainGabResult> domainGabResultList = new ArrayList<>();
        try
        {
            List<UserRelationships> userRelationshipsList = relationshipsDao.findAllByUser_UuidAndTypeIsOrderByCreationDateDesc(
                    userUuid,
                    FOLLOWED);

            List<User> userFollowed = new ArrayList<>();
            var user = userPersisterService.getUserByUuid(userUuid);
            userFollowed.add(this.gabblerInfraMapper.toDomainUserToUser(user.getDomainUser()));
            for (UserRelationships userRelationships : userRelationshipsList)
            {
                userFollowed.add(userRelationships.getUserRelated());
            }
            List<Gab> gabs = gabDao.getGabsByUsersFollowed(userFollowed);
            if (isNotEmpty(gabs))
            {
                for (final Gab gab : gabs)
                {
                    domainGabResultList.add(setDomainGabResultDataByGab(gab));
                }
            }
            else
            {
                List<Gab> gabList = gabDao.getGabsForUserConnectedFeed(userUuid);
                if (isNotEmpty(gabList))
                {
                    for (final Gab gab : gabList)
                    {
                        domainGabResultList.add(setDomainGabResultDataByGab(gab));
                    }
                }
            }
        }
        catch (final Exception e)
        {
            log.error("[{}] Erreur survenue lors de la récupération du feed pour un utilisateur connecté", userUuid, e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainGabsResult(domainAccessStatus, domainGabResultList);
    }

    @Transactional(readOnly = true)
    public DomainGabsResult setDomainGabsResultDataByGabsList(List<Gab> gabs)
    {

        DomainAccessStatus domainAccessStatus = OK;
        List<DomainGabResult> domainGabResultList = new ArrayList<>();
        for (final Gab gab : gabs)
        {
            domainGabResultList.add(setDomainGabResultDataByGab(gab));
        }


        return new DomainGabsResult(domainAccessStatus, domainGabResultList);
    }

    private HashMap<String, Integer> getInteractionCountByGab(Gab gab)
    {

        HashMap<String, Integer> interactionCountByGab = new HashMap<>();
        interactionCountByGab.put("like",
                                  interactionDao.countInteractionByActionAndGabId(InteractionTypeEnum.LIKE,
                                                                                  gab.getId()));
        interactionCountByGab.put("dislike",
                                  interactionDao.countInteractionByActionAndGabId(InteractionTypeEnum.DISLIKE,
                                                                                  gab.getId()));
        return interactionCountByGab;
    }

}
