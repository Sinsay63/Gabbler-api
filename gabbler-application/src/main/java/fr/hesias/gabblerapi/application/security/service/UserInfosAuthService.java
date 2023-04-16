package fr.hesias.gabblerapi.application.security.service;

import fr.hesias.gabblerapi.application.adapter.AuthAccessorAdapter;
import fr.hesias.gabblerapi.domain.result.DomainUserInfosAuthResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserInfosAuthService implements UserDetailsService {

    @Autowired
    private AuthAccessorAdapter authAccessorAdapter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DomainUserInfosAuthResult user = authAccessorAdapter.getUserCredentialByEmail(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("l'utilisateur n'a pas été trouvé ! -> " + username);
        }
    }

}
