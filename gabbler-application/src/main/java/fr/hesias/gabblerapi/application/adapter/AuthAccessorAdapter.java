package fr.hesias.gabblerapi.application.adapter;

import fr.hesias.gabblerapi.domain.port.primary.UserInfosAccessor;
import fr.hesias.gabblerapi.domain.result.DomainUserInfosAuthResult;
import fr.hesias.gabblerapi.domain.result.DomainUserRegistrationInfosResult;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;

public class AuthAccessorAdapter {

    private final UserInfosAccessor userInfosAccessor;

    public AuthAccessorAdapter(final UserInfosAccessor userInfosAccessor) {

        super();
        this.userInfosAccessor = userInfosAccessor;
    }

    public DomainUserInfosAuthResult getUserCredentialByEmail(String email) {

        return userInfosAccessor.getUserCredentialByEmail(email);
    }

    public DomainUserResult register(final DomainUserRegistrationInfosResult user) {

        return userInfosAccessor.register(user);
    }
}
