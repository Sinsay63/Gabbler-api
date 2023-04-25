package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.desc.api.server.model.Media;
import fr.hesias.gabblerapi.desc.api.server.model.User;
import fr.hesias.gabblerapi.desc.api.server.model.UserAuth;
import fr.hesias.gabblerapi.desc.api.server.model.UserRegister;
import fr.hesias.gabblerapi.domain.model.DomainMedia;
import fr.hesias.gabblerapi.domain.model.DomainUser;
import fr.hesias.gabblerapi.domain.model.DomainUserAuth;
import fr.hesias.gabblerapi.domain.model.DomainUserRegistration;
import fr.hesias.gabblerapi.domain.result.DomainUserInfosAuthResult;
import fr.hesias.gabblerapi.domain.result.DomainUserRegistrationInfosResult;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;
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
            user.setMedias(toDomainMediasListToMediasList(domainUser.getMedias()));
        }
        return user;
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

}
