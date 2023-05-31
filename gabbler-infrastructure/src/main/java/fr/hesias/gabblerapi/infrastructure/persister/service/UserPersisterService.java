package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.model.*;
import fr.hesias.gabblerapi.domain.result.*;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.*;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Media;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.UserRelationships;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.*;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Slf4j
public class UserPersisterService
{

    private final UserDao userDao;

    private final GabDao gabDao;

    private final MediaDao mediaDao;

    private final RelationshipsDao relationshipsDao;

    private final InteractionDao interactionDao;

    private final GabblerInfraMapper gabblerInfraMapper;

    private final GabPersisterService gabPersisterService;

    private final SubscriptionDao subscriptionDao;

    public UserPersisterService(final UserDao userDao,
                                final GabDao gabDao,
                                final MediaDao mediaDao,
                                final RelationshipsDao relationshipsDao,
                                final InteractionDao interactionDao,
                                final GabblerInfraMapper gabblerInfraMapper,
                                final GabPersisterService gabPersisterService, SubscriptionDao subscriptionDao)
    {

        this.userDao = userDao;
        this.gabDao = gabDao;
        this.mediaDao = mediaDao;
        this.relationshipsDao = relationshipsDao;
        this.interactionDao = interactionDao;
        this.gabblerInfraMapper = gabblerInfraMapper;
        this.gabPersisterService = gabPersisterService;
        this.subscriptionDao = subscriptionDao;
    }

    @Transactional(readOnly = true)
    public DomainUsersResult getUsers()
    {

        DomainAccessStatus domainAccessStatus = OK;
        final List<DomainUserResult> users = new ArrayList<>();
        try
        {
            final List<User> userList = userDao.getUsers();
            if (isNotEmpty(userList))
            {
                for (final User user : userList)
                {

                    users.add(toUserToDomainUserResult(user, getPremiumByUserUuid(user.getUuid())));
                }
            }

        }
        catch (final Exception e)
        {
            log.error("[NA] Erreur survenue lors de la récupération des utilisateurs", e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainUsersResult(domainAccessStatus, users);
    }

    @Transactional(readOnly = true)
    public DomainUserResult getUserByUuid(final String uuid)
    {

        DomainUserResult domainUserResult = null;

        try
        {
            final User user = userDao.getUserByUuid(uuid).orElseThrow(() -> new Exception("Utilisateur non trouvé"));

            domainUserResult = toUserToDomainUserResult(user, getPremiumByUserUuid(user.getUuid()));

        }
        catch (final Exception e)
        {
            log.error("[{}] Erreur survenue lors de la récupération d'un utlisateur à partir de son uuid", uuid, e);
        }

        return domainUserResult;
    }


    @Transactional(readOnly = true)
    public DomainUsersResult getSuggestionsUserNotConnected()
    {

        DomainAccessStatus domainAccessStatus = OK;
        final List<DomainUserResult> users = new ArrayList<>();
        try
        {
            final List<User> userList = userDao.getRandomUsersForSuggestionsUserNotConnected();
            if (isNotEmpty(userList))
            {
                for (final User user : userList)
                {
                    users.add(toUserToDomainUserResult(user, getPremiumByUserUuid(user.getUuid())));
                }
            }

        }
        catch (final Exception e)
        {
            log.error("[NA] Erreur survenue lors de la récupération des utilisateurs", e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainUsersResult(domainAccessStatus, users);

    }

    @Transactional(readOnly = true)
    public DomainUsersResult getSuggestionsUserConnected(String userUuid)
    {

        DomainAccessStatus domainAccessStatus = OK;
        final List<DomainUserResult> users = new ArrayList<>();
        try
        {
            List<UserRelationships> userRelationshipsList = relationshipsDao.findAllByUser_Uuid(userUuid);

            List<String> usersFollowedOrBlockedUuid = new ArrayList<>();
            for (UserRelationships userRelationships : userRelationshipsList)
            {
                usersFollowedOrBlockedUuid.add(userRelationships.getUserRelated().getUuid());
            }
            usersFollowedOrBlockedUuid.add(userUuid);

            final List<User> userList = userDao.getRandomUsersForSuggestionsUserConnected(usersFollowedOrBlockedUuid);

            if (isNotEmpty(userList))
            {
                for (final User user : userList)
                {
                    users.add(toUserToDomainUserResult(user, getPremiumByUserUuid(user.getUuid())));
                }
            }

        }
        catch (final Exception e)
        {
            log.error("[NA] Erreur survenue lors de la récupération des utilisateurs", e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainUsersResult(domainAccessStatus, users);

    }


    @Transactional(rollbackFor = Exception.class)
    public DomainUserResult register(final DomainUserRegistrationInfosResult user)
    {

        DomainAccessStatus domainAccessStatus = OK;
        User newUser = new User();

        try
        {
            User usr = this.gabblerInfraMapper.toDomainUserRegistrationInfosResultToUser(user);

            newUser = userDao.addUser(usr);
            mediaDao.addDefaultUserMedias(newUser.getUuid());

        }
        catch (final Exception e)
        {
            log.error("[NA] Erreur survenue lors de la création de l'utilisateur {}",
                      user.getUserRegistration().getEmail(),
                      e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainUserResult(domainAccessStatus, this.gabblerInfraMapper.toUserToDomainUser(newUser));
    }

    @Transactional(readOnly = true)
    public DomainUserResult getUserByEmail(final String email)
    {

        DomainAccessStatus domainAccessStatus = OK;
        DomainUser user = new DomainUser();

        try
        {
            final User daoUser = userDao.getUserByEmail(email)
                                        .orElseThrow(() -> new Exception("Utilisateur non trouvé"));

            user = this.gabblerInfraMapper.toUserToDomainUser(daoUser);
        }
        catch (final Exception e)
        {
            log.error("[{}] Erreur survenue lors de la récupération d'un utlisateur à partir de son email", email, e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainUserResult(domainAccessStatus, user);
    }

    @Transactional(readOnly = true)
    public DomainUserInfosAuthResult getUserCredentialByEmail(final String email)
    {

        DomainAccessStatus domainAccessStatus = OK;
        DomainUserAuth user = new DomainUserAuth();

        try
        {
            final User daoUser = userDao.getUserByEmail(email)
                                        .orElse(null);
            if (daoUser != null)
            {
                user = this.gabblerInfraMapper.toUserToDomainUserAuth(daoUser);
            }
            else
            {
                domainAccessStatus = BAD_REQUEST;
            }
        }
        catch (final Exception e)
        {
            log.error("[{}] Erreur survenue lors de la récupération d'un utlisateur à partir de son email", email, e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainUserInfosAuthResult(domainAccessStatus, user);
    }

    @Transactional(readOnly = true)
    public DomainUsersResult setDomainUserResultDataByUsersList(List<User> users)
    {

        DomainAccessStatus domainAccessStatus = OK;
        final List<DomainUserResult> domainUserResultList = new ArrayList<>();
        try
        {
            if (isNotEmpty(users))
            {
                for (final User user : users)
                {
                    domainUserResultList.add(toUserToDomainUserResult(user,
                                                                      getPremiumByUserUuid(user.getUuid())));
                }
            }

        }
        catch (final Exception e)
        {
            log.error("[NA] Erreur survenue lors de la récupération des utilisateurs", e);
            domainAccessStatus = INTERNAL_ERROR;
        }

        return new DomainUsersResult(domainAccessStatus, domainUserResultList);
    }

    private DomainUserResult toUserToDomainUserResult(User user, boolean isPremium)
    {

        DomainUser domainUser = gabblerInfraMapper.toUserToDomainUserRelationships(user);
        domainUser.setPremium(isPremium);
        return new DomainUserResult(OK, domainUser);
    }

    @Transactional(readOnly = true)
    public DomainUserProfileResult getUserProfile(String userUuid)
    {

        DomainAccessStatus domainAccessStatus = OK;
        DomainUserProfile domainUserProfile = null;
        List<DomainGabResult> domainGabResultList = new ArrayList<>();
        List<DomainGab> domainGabList = new ArrayList<>();
        try
        {
            User daoUser = userDao.getUserByUuid(userUuid).orElse(null);
            if (daoUser != null)
            {
                List<UserRelationships> userFollowers = relationshipsDao.findAllFollowersByUserUuid(userUuid);
                List<UserRelationships> userFollows = relationshipsDao.findAllFollowsByUserUuid(userUuid);

                for (Gab gab : daoUser.getGabs())
                {
                    domainGabResultList.add(this.gabPersisterService.setDomainGabResultDataByGab(gab));
                }
                for (DomainGabResult domainGabResult : domainGabResultList)
                {
                    domainGabList.add(domainGabResult.getGab());
                }
                if (isNotEmpty(userFollowers))
                {
                    for (UserRelationships userRelationships : userFollowers)
                    {
                        List<Media> gabMediasList = mediaDao.getMediaAvatarAndBannerByUserUuid(userRelationships.getUserRelated()
                                                                                                                .getUuid());
                        userRelationships.getUser().setMedias(gabMediasList);
                    }
                }
                if (isNotEmpty(userFollows))
                {
                    for (UserRelationships userRelationships : userFollows)
                    {
                        List<Media> gabMediasList = mediaDao.getMediaAvatarAndBannerByUserUuid(userRelationships.getUser()
                                                                                                                .getUuid());
                        userRelationships.getUser().setMedias(gabMediasList);
                    }
                }
                boolean isPremium = isNotEmpty(subscriptionDao.getSubscriptionByUserUuid(userUuid));
                domainUserProfile = this.gabblerInfraMapper.toUserToDomainUserProfile(daoUser,
                                                                                      daoUser.getMedias(),
                                                                                      daoUser.getInteractions(),
                                                                                      daoUser.getGabs(),
                                                                                      userFollowers,
                                                                                      userFollows);
                domainUserProfile.setGabs(domainGabList);
                domainUserProfile.setPremium(isPremium);
            }
        }
        catch (final Exception e)
        {
            log.error(
                    "[{}] Erreur survenue lors de la récupération des données du profile d'un utilisateur à partir de son uuid",
                    userUuid,
                    e);
            domainAccessStatus = INTERNAL_ERROR;
        }
        return new DomainUserProfileResult(domainAccessStatus, domainUserProfile);
    }


    @Transactional(rollbackFor = Exception.class)
    public void confirmEmailByUserUuid(String userUuid)
    {

        try
        {
            userDao.validateUserByUserUuuid(userUuid);
        }
        catch (final Exception e)
        {
            log.error("[{}] Erreur survenue lors de la confirmation de l'email d'un utilisateur à partir de son uuid",
                      userUuid,
                      e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean getPremiumByUserUuid(String userUuid)
    {

        var subscription = subscriptionDao.getSubscriptionByUserUuid(userUuid);
        var isPremium = false;
        if (isNotEmpty(subscription))
        {
            if (subscription.getEndDate().isAfter(LocalDateTime.now()))
            {
                isPremium = true;
            }
            else
            {
                try
                {
                    subscriptionDao.deleteSubscriptionByUserUuid(userUuid);
                    userDao.removePremiumRoleByUserUuid(userUuid);
                }
                catch (final Exception e)
                {
                    log.error(
                            "[{}] Erreur survenue lors de la suppression de l'abonnement d'un utilisateur à partir de son uuid",
                            userUuid,
                            e);
                }
            }
        }
        return isPremium;
    }

    @Transactional
    public DomainEditUserProfileResult editUserProfile(DomainEditUserProfileResult domainEditUserProfileResult)
    {

        var userUuid = domainEditUserProfileResult.getDomainEditUserProfile().getUserUuid();
        var type = domainEditUserProfileResult.getDomainEditUserProfile().getEditType();
        var value = domainEditUserProfileResult.getDomainEditUserProfile().getValue();
        var user = userDao.getUserByUuid(userUuid).orElse(null);
        if (user != null)
        {
            if (type.equals("username") && getPremiumByUserUuid(userUuid))
            {
                user.setUsername(value);
                userDao.updateUser(user);
            }
            else if (type.equals("biography"))
            {
                user.setBiography(value);
                userDao.updateUser(user);

            }
            else
            {
                return new DomainEditUserProfileResult(BAD_REQUEST,
                                                       domainEditUserProfileResult.getDomainEditUserProfile());
            }
            return new DomainEditUserProfileResult(OK, domainEditUserProfileResult.getDomainEditUserProfile());
        }
        return new DomainEditUserProfileResult(INTERNAL_ERROR, domainEditUserProfileResult.getDomainEditUserProfile());
    }

}
