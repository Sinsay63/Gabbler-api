package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.desc.api.server.model.*;
import fr.hesias.gabblerapi.domain.model.*;
import fr.hesias.gabblerapi.domain.result.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;


public class UserApiMapper
{

    private final InteractionApiMapper interactionApiMapper;

    private final PasswordEncoder passwordEncoder;

    public UserApiMapper(PasswordEncoder passwordEncoder, InteractionApiMapper interactionApiMapper)
    {

        this.passwordEncoder = passwordEncoder;
        this.interactionApiMapper = interactionApiMapper;
    }


    public List<User> toDomainUsersResultToUsersList(final DomainUsersResult domainUsersResult)
    {

        final List<User> userList = new ArrayList<>();

        if (domainUsersResult.isOk())
        {

            for (final DomainUserResult domainUserResult : domainUsersResult.getUsers())
            {
                userList.add(toDomainUserResultToUser(domainUserResult));
            }
        }
        return userList;
    }

    public Media toDomainMediaToMedia(final DomainMedia domainMedia)
    {

        final Media media = new Media();

        if (domainMedia != null)
        {
            media.setId(domainMedia.getId());
            media.setUrl(domainMedia.getUrl());
            media.setType(domainMedia.getType());
            media.setDate(domainMedia.getDate().toString());
        }
        return media;
    }

    public DomainUser toUserToDomainUser(final User user)
    {

        final DomainUser domainUser = new DomainUser();

        if (user != null)
        {

            domainUser.setUuid(user.getUuid());
            domainUser.setUsername(user.getUsername());
            domainUser.setFirstName(user.getFirstname());
            domainUser.setLastName(user.getLastname());
        }
        return domainUser;
    }

    public User toDomainUserToUser(DomainUser domainUser)
    {

        User user = new User();
        if (domainUser != null)
        {
            user.setUuid(domainUser.getUuid());
            user.setUsername(domainUser.getUsername());
            user.setFirstname(domainUser.getFirstName());
            user.setLastname(domainUser.getLastName());
            if (isNotEmpty(domainUser.getMedias()))
            {
                HashMap<String, Media> hashMap = getUserMediaMap(domainUser.getMedias());
                if (!hashMap.isEmpty())
                {
                    user.setAvatar(hashMap.get("avatar"));
                    user.setBanner(hashMap.get("banner"));
                }
            }
        }
        return user;
    }

    public List<User> toDomainUserListToUserList(List<DomainUser> domainUserList)
    {

        List<User> userList = new ArrayList<>();

        if (domainUserList != null)
        {
            for (DomainUser domainUser : domainUserList)
            {
                userList.add(toDomainUserToUser(domainUser));
            }
        }

        return userList;

    }

    public User toDomainUserResultToUser(final DomainUserResult domainUserResult)
    {

        User user = new User();

        if (domainUserResult.isOk())
        {
            final DomainUser domainUser = domainUserResult.getDomainUser();
            user = toDomainUserToUser(domainUser);
        }
        return user;
    }

    public List<Media> toDomainMediasListToMediasList(List<DomainMedia> domainMediaList)
    {

        List<Media> mediaList = new ArrayList<>();

        if (domainMediaList != null)
        {
            for (DomainMedia domainMedia : domainMediaList)
            {
                mediaList.add(toDomainMediaToMedia(domainMedia));
            }
        }

        return mediaList;

    }

    public UserAuth toDomainUserInfosAuthResultToUserAuth(final DomainUserInfosAuthResult domainUserInfosAuthResult)
    {

        final UserAuth userAuth = new UserAuth();

        if (domainUserInfosAuthResult != null)
        {
            final DomainUserAuth domainUserAuth = domainUserInfosAuthResult.getUserAuthInfo();

            userAuth.setEmail(domainUserAuth.getUser().getEmail());
            userAuth.setPassword(domainUserAuth.getPassword());

        }
        return userAuth;
    }

    public DomainUserRegistrationInfosResult toUserRegisterToDomainUserRegistrationInfosResult(final UserRegister userRegister)
    {

        final DomainUserRegistration domainUserRegistration = new DomainUserRegistration();

        if (userRegister != null)
        {
            domainUserRegistration.setEmail(userRegister.getEmail());
            //On chiffre le mot de passe
            domainUserRegistration.setPassword(passwordEncoder.encode(userRegister.getPassword()));
            domainUserRegistration.setUsername(userRegister.getUsername());
            domainUserRegistration.setFirstName(userRegister.getFirstname());
            domainUserRegistration.setLastName(userRegister.getLastname());
            domainUserRegistration.setBirthday(LocalDate.parse(userRegister.getBirthday()));
        }
        return new DomainUserRegistrationInfosResult(domainUserRegistration);
    }

    public HashMap<String, Media> getUserMediaMap(List<DomainMedia> mediaList)
    {

        HashMap<String, Media> mediaMap = new HashMap<>();
        for (DomainMedia domainMedia : mediaList)
        {
            mediaMap.put(domainMedia.getType(), toDomainMediaToMedia(domainMedia));
        }
        return mediaMap;
    }

    public UserInfosProfile toDomainUserProfileResultToUserInfosProfile(DomainUserProfileResult domainUserProfileResult)
    {

        UserInfosProfile userInfosProfile = new UserInfosProfile();

        if (domainUserProfileResult.isOk())
        {
            final DomainUserProfile domainUserProfile = domainUserProfileResult.getDomainUserProfile();
            userInfosProfile = toDomainUserProfileToUserInfosProfile(domainUserProfile);
        }
        return userInfosProfile;
    }

    public UserInfosProfile toDomainUserProfileToUserInfosProfile(DomainUserProfile domainUserProfile)
    {

        UserInfosProfile userInfosProfile = new UserInfosProfile();

        if (domainUserProfile != null)
        {
            userInfosProfile.setUuid(domainUserProfile.getUuid());
            userInfosProfile.setUsername(domainUserProfile.getUsername());
            userInfosProfile.setFirstname(domainUserProfile.getFirstName());
            userInfosProfile.setLastname(domainUserProfile.getLastName());
            userInfosProfile.setBirthday(domainUserProfile.getBirthday().toString());
            userInfosProfile.setBiography(domainUserProfile.getBiography());
            userInfosProfile.setAvatar(toDomainMediaToMedia(domainUserProfile.getAvatar()));
            userInfosProfile.setBanner(toDomainMediaToMedia(domainUserProfile.getBanner()));
            userInfosProfile.setGabs(toDomainGabListToGabsList(domainUserProfile.getGabs()));
            userInfosProfile.setInteractions(this.interactionApiMapper.toDomainInteractionListToInteractionUserList(
                    domainUserProfile.getInteractions()));
            userInfosProfile.setFollowers(toDomainUserListToUserList(domainUserProfile.getFollowers()));
            userInfosProfile.setFollows(toDomainUserListToUserList(domainUserProfile.getFollows()));
        }
        return userInfosProfile;
    }

    public Gab toGab(final DomainGab domainGab)
    {

        final Gab gab = new Gab();

        if (domainGab != null)
        {
            gab.setId(domainGab.getId());
            gab.setContent(domainGab.getContent());
            gab.setPostDate(domainGab.getPostDate().toString());
            gab.setNbLikes(domainGab.getNbLikes());
            gab.setNbDislikes(domainGab.getNbDislikes());
            gab.setNbComments(domainGab.getNbComments());
            gab.setMedias(toDomainMediasListToMediasList(domainGab.getMedias()));
            gab.setUser(toDomainUserToUser(domainGab.getUser()));
        }
        return gab;
    }

    public List<Media> toDomainMediasResultToMediasList(DomainMediasResult domainMediasResult)
    {

        List<Media> mediaList = new ArrayList<>();

        if (domainMediasResult.isOk())
        {
            for (DomainMediaResult domainMediaResult : domainMediasResult.getMedias())
            {
                mediaList.add(toDomainMediaToMedia(domainMediaResult.getDomainMedia()));
            }
        }
        return mediaList;
    }

    public List<Gab> toDomainGabListToGabsList(List<DomainGab> gabs)
    {

        final List<Gab> gabsList = new ArrayList<>();

        if (gabs != null)
        {

            for (DomainGab domainGab : gabs)
            {
                gabsList.add(toGab(domainGab));
            }
        }
        return gabsList;
    }

}
