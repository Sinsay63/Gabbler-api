package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.desc.api.server.model.User;
import fr.hesias.gabblerapi.desc.api.server.model.UserAuth;
import fr.hesias.gabblerapi.desc.api.server.model.UserRegister;
import fr.hesias.gabblerapi.desc.api.server.model.Users;
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

public class UserApiMapper {

    private final PasswordEncoder passwordEncoder;

    public UserApiMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toUser(final DomainUser domainUser) {

        final User user = new User();

        if (domainUser != null) {
            user.setUuid(domainUser.getUuid());
            user.setFirstname(domainUser.getFirstName());
            user.setLastname(domainUser.getLastName());
            user.setEmail(domainUser.getEmail());
            user.setBiography(domainUser.getBiography());
            user.setBirthday(domainUser.getBirthday().toString());
            user.setUsername(domainUser.getUsername());
        }
        return user;
    }

    public Users toUsers(final DomainUsersResult domainUsersResult) {

        final Users users = new Users();

        if (domainUsersResult != null) {
            final List<User> userList = new ArrayList<>();

            for (final DomainUserResult domainUserResult : domainUsersResult.getUsers()) {
                final DomainUser domainUser = domainUserResult.getDomainUser();
                userList.add(toUser(domainUser));
            }
            users.setUsers(userList);
        }
        return users;
    }

    public DomainUser toDomainUser(final User user) {

        final DomainUser domainUser = new DomainUser();

        if (user != null) {

            domainUser.setUuid(user.getUuid());
            domainUser.setFirstName(user.getFirstname());
            domainUser.setLastName(user.getLastname());
            domainUser.setEmail(user.getEmail());
            domainUser.setBiography(user.getBiography());
            domainUser.setBirthday(LocalDate.parse(user.getBirthday()));
            domainUser.setUsername(user.getUsername());
        }
        return domainUser;
    }

    public User toDomainUserResultToUser(final DomainUserResult domainUserResult) {

        final User user = new User();

        if (domainUserResult != null) {
            final DomainUser domainUser = domainUserResult.getDomainUser();

            user.setUuid(domainUser.getUuid());
            user.setFirstname(domainUser.getFirstName());
            user.setLastname(domainUser.getLastName());
            user.setEmail(domainUser.getEmail());
            user.setBiography(domainUser.getBiography());
            user.setBirthday(domainUser.getBirthday().toString());
            user.setUsername(domainUser.getUsername());
        }

        return user;
    }

    public Users toDomainUsersResultToUsers(final DomainUsersResult domainUsersResultList) {

        final Users users = new Users();

        if (domainUsersResultList != null) {
            final List<User> userList = new ArrayList<>();
            for (final DomainUserResult domainUserResult : domainUsersResultList.getUsers()) {

                userList.add(toUser(domainUserResult.getDomainUser()));
            }
            users.setUsers(userList);
        }
        return users;
    }

    public UserAuth toDomainUserInfosAuthResultToUserAuth(final DomainUserInfosAuthResult domainUserInfosAuthResult) {

        final UserAuth userAuth = new UserAuth();

        if (domainUserInfosAuthResult != null) {
            final DomainUserAuth domainUserAuth = domainUserInfosAuthResult.getUserAuthInfo();

            userAuth.setEmail(domainUserAuth.getUser().getEmail());
            userAuth.setPassword(domainUserAuth.getPassword());

        }
        return userAuth;
    }

    public DomainUserRegistrationInfosResult toUserRegisterToDomainUserRegistrationInfosResult(final UserRegister userRegister) {

        final DomainUserRegistration domainUserRegistration = new DomainUserRegistration();

        if (userRegister != null) {
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
