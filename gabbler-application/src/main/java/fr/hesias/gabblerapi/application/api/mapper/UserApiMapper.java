package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.domain.model.DomainUser;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;
import org.openapitools.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserApiMapper {


    public User toUser(final DomainUser domainUser) {

        final User user = new User();

        if (domainUser != null) {
            user.setId(domainUser.getId());
            user.setFirstname(domainUser.getFirstName());
            user.setLastname(domainUser.getLastName());
            user.setEmail(domainUser.getEmail());
            user.setBiography(domainUser.getBiography());
            user.setBirthday(domainUser.getBirthday());
            user.setUsername(domainUser.getUsername());
        }
        return user;
    }

    public DomainUser toDomainUser(final User user) {

        final DomainUser domainUser = new DomainUser();

        if (user != null) {

            domainUser.setId(user.getId());
            domainUser.setFirstName(user.getFirstname());
            domainUser.setLastName(user.getLastname());
            domainUser.setEmail(user.getEmail());
            domainUser.setBiography(user.getBiography());
            domainUser.setBirthday(user.getBirthday());
            domainUser.setUsername(user.getUsername());
        }
        return domainUser;
    }

    public User toDomainUserResultToUser(final DomainUserResult domainUserResult) {

        final User user = new User();

        if (domainUserResult != null) {
            final DomainUser domainUser = domainUserResult.getDomainUser();

            user.setId(domainUser.getId());
            user.setFirstname(domainUser.getFirstName());
            user.setLastname(domainUser.getLastName());
            user.setEmail(domainUser.getEmail());
            user.setBiography(domainUser.getBiography());
            user.setBirthday(domainUser.getBirthday());
            user.setUsername(domainUser.getUsername());
        }

        return user;
    }

    public List<User> toDomainUserResultToUserList(final DomainUsersResult domainUsersResultList) {

        final List<User> userList = new ArrayList<>();

        if (domainUsersResultList != null) {
            for (final DomainUserResult domainUserResult : domainUsersResultList.getUsers()) {

                userList.add(toUser(domainUserResult.getDomainUser()));
            }
        }
        return userList;
    }


}
