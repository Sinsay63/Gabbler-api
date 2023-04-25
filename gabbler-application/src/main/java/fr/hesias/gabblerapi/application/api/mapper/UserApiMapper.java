package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.desc.api.server.model.*;
import fr.hesias.gabblerapi.domain.model.DomainMedia;
import fr.hesias.gabblerapi.domain.model.DomainUser;
import fr.hesias.gabblerapi.domain.model.DomainUserAuth;
import fr.hesias.gabblerapi.domain.model.DomainUserRegistration;
import fr.hesias.gabblerapi.domain.result.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserApiMapper
{

    private final PasswordEncoder passwordEncoder;

    public UserApiMapper(PasswordEncoder passwordEncoder)
    {

        this.passwordEncoder = passwordEncoder;
    }


    public Users toDomainUsersInfosResultToUsers(final DomainUsersInfosResult domainUsersInfosResult)
    {

        final Users users = new Users();

        if (domainUsersInfosResult.isOk())
        {
            final List<User> userList = new ArrayList<>();

            for (final DomainUserInfosResult domainUserInfosResult : domainUsersInfosResult.getDomainUserInfosResults())
            {
                userList.add(toDomainUserInfosResultToUser(domainUserInfosResult));
            }
            users.setUsers(userList);
        }
        return users;
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
            user.setMedias(toDomainMediasListToMediasList(domainUser.getMedias()));
        }
        return user;
    }

    public User toDomainUserInfosResultToUser(final DomainUserInfosResult domainUserInfosResult)
    {

        User user = new User();

        if (domainUserInfosResult.isOk())
        {
            final DomainUser domainUser = domainUserInfosResult.getDomainUser();
            final DomainMediasResult domainMediasResult = domainUserInfosResult.getDomainMediasResult();

            user = toDomainUserAndDomainMediasResultToUser(domainUser, domainMediasResult);
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

    public User toDomainUserAndDomainMediasResultToUser(final DomainUser domainUser,
                                                        final DomainMediasResult domainMediasResult)
    {

        final User user = new User();

        if (domainUser != null && domainMediasResult != null)
        {
            user.setUuid(domainUser.getUuid());
            user.setFirstname(domainUser.getFirstName());
            user.setLastname(domainUser.getLastName());
            user.setUsername(domainUser.getUsername());
            user.setMedias(toDomainMediasResultToMediasList(domainMediasResult));

        }
        return user;
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

}
