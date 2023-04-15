package fr.hesias.gabblerapi.application.security.service;

import fr.hesias.gabblerapi.application.adapter.UserInfosAccessorAdapter;
import fr.hesias.gabblerapi.domain.model.DomainUserAuth;
import fr.hesias.gabblerapi.domain.result.DomainUserInfosAuthResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;

@Component
public class UserInfosAuthService implements UserDetailsService
{

    @Autowired
    private UserInfosAccessorAdapter userInfosAccessorAdapter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        DomainUserAuth user = userInfosAccessorAdapter.getUserCredentialByEmail(username);
        if (user != null)
        {
            return new DomainUserInfosAuthResult(OK, user);
        }
        else
        {
            throw new UsernameNotFoundException("l'utilisateur n'a pas été trouvé ! -> " + username);
        }
    }

}
