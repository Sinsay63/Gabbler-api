package fr.hesias.gabblerapi.application.adapter;

import fr.hesias.gabblerapi.domain.port.primary.UserInfosAccessor;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;


public class UserInfosAccessorAdapter {

    private final UserInfosAccessor userInfosAccessor;

    public UserInfosAccessorAdapter(final UserInfosAccessor userInfosAccessor) {

        super();
        this.userInfosAccessor = userInfosAccessor;
    }

    public DomainUsersResult getUsers() {

        return userInfosAccessor.getUsers();
    }

    public DomainUserResult getUserByEmail(String email) {

        return userInfosAccessor.getUserByEmail(email);
    }
}
