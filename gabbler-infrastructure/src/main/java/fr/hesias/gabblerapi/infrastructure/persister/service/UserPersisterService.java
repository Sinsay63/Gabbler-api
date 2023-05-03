package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainUser;
import fr.hesias.gabblerapi.domain.model.DomainUserAuth;
import fr.hesias.gabblerapi.domain.model.DomainUserProfile;
import fr.hesias.gabblerapi.domain.result.*;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.*;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.*;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.INTERNAL_ERROR;
import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Slf4j
public class UserPersisterService
{

    private final UserDao userDao;

    private final GabDao gabDao;

    private final MediaDao mediaDao;

    private final UserRelationshipsDao userRelationshipsDao;

    private final InteractionDao interactionDao;

    private final GabblerInfraMapper gabblerInfraMapper;

    public UserPersisterService(final UserDao userDao,
                                final GabDao gabDao,
                                final MediaDao mediaDao,
                                final UserRelationshipsDao userRelationshipsDao,
                                final InteractionDao interactionDao,
                                final GabblerInfraMapper gabblerInfraMapper)
    {

        this.userDao = userDao;
        this.gabDao = gabDao;
        this.mediaDao = mediaDao;
        this.userRelationshipsDao = userRelationshipsDao;
        this.interactionDao = interactionDao;
        this.gabblerInfraMapper = gabblerInfraMapper;
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
                    List<Media> mediaList = mediaDao.getMediaAvatarAndBannerByUserUuid(user.getUuid());
                    users.add(toUserToDomainUserResult(user, mediaList));
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
            List<Media> mediaList = mediaDao.getMediaAvatarAndBannerByUserUuid(user.getUuid());

            domainUserResult = toUserToDomainUserResult(user, mediaList);

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
                    List<Media> mediaList = mediaDao.getMediaAvatarAndBannerByUserUuid(user.getUuid());
                    users.add(toUserToDomainUserResult(user, mediaList));
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
            List<UserRelationships> userRelationshipsList = userRelationshipsDao.findAllByUser_Uuid(userUuid);

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
                    List<Media> mediaList = mediaDao.getMediaAvatarAndBannerByUserUuid(user.getUuid());
                    users.add(toUserToDomainUserResult(user, mediaList));
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
                domainAccessStatus = DomainAccessStatus.BAD_REQUEST;
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
                    List<Media> mediaList = mediaDao.getMediaAvatarAndBannerByUserUuid(user.getUuid());
                    domainUserResultList.add(toUserToDomainUserResult(user, mediaList));
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

    private DomainUserResult toUserToDomainUserResult(User user, List<Media> mediaList)
    {

        DomainUser domainUser = new DomainUser();
        user.setMedias(mediaList);
        domainUser = gabblerInfraMapper.toUserToDomainUserRelationships(user);

        return new DomainUserResult(OK, domainUser);
    }

    @Transactional(readOnly = true)
    public DomainUserProfileResult getUserProfile(String userUuid)
    {

        DomainAccessStatus domainAccessStatus = OK;
        DomainUserProfile domainUserProfile = null;
        try
        {
            User daoUser = userDao.getUserByUuid(userUuid).orElse(null);
            if (daoUser != null)
            {
                List<Media> mediaList = mediaDao.getMediaAvatarAndBannerByUserUuid(userUuid);
                List<Interaction> interactions = interactionDao.getInteractionsByUserUuid(userUuid);
                List<Gab> gabs = gabDao.getGabsByUserUuid(userUuid);
                List<UserRelationships> userFollowers = userRelationshipsDao.findAllFollowersByUserUuid(userUuid);
                List<UserRelationships> userFollows = userRelationshipsDao.findAllFollowsByUserUuid(userUuid);

                for (Gab gab : gabs)
                {
                    List<Media> gabMediasList = mediaDao.getMediaByGabId(gab.getId());
                    gab.setMedias(gabMediasList);
                }
                for (UserRelationships userRelationships : userFollowers)
                {
                    List<Media> gabMediasList = mediaDao.getMediaAvatarAndBannerByUserUuid(userRelationships.getUserRelated()
                                                                                                            .getUuid());
                    userRelationships.getUser().setMedias(gabMediasList);
                }
                for (UserRelationships userRelationships : userFollows)
                {
                    List<Media> gabMediasList = mediaDao.getMediaAvatarAndBannerByUserUuid(userRelationships.getUser()
                                                                                                            .getUuid());
                    userRelationships.getUser().setMedias(gabMediasList);
                }
                domainUserProfile = this.gabblerInfraMapper.toUserToDomainUserProfile(daoUser,
                                                                                      mediaList,
                                                                                      interactions,
                                                                                      gabs,
                                                                                      userFollowers,
                                                                                      userFollows);
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

}
