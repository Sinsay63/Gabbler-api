package fr.hesias.gabblerapi.application.api.service;

import fr.hesias.gabblerapi.application.adapter.AuthAccessorAdapter;
import fr.hesias.gabblerapi.application.adapter.UserInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.api.mapper.UserApiMapper;
import fr.hesias.gabblerapi.application.security.service.JwtService;
import fr.hesias.gabblerapi.desc.api.server.AuthApiDelegate;
import fr.hesias.gabblerapi.desc.api.server.model.User;
import fr.hesias.gabblerapi.desc.api.server.model.UserAndToken;
import fr.hesias.gabblerapi.desc.api.server.model.UserAuth;
import fr.hesias.gabblerapi.desc.api.server.model.UserRegister;
import fr.hesias.gabblerapi.domain.result.DomainUserInfosAuthResult;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthApiDelegateImpl implements AuthApiDelegate {

    private GabblerApiService gabblerApiService;
    private UserInfosAccessorAdapter userInfosAccessorAdapter;

    private AuthAccessorAdapter authAccessorAdapter;

    private UserApiMapper userApiMapper;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public AuthApiDelegateImpl(GabblerApiService gabblerApiService, UserInfosAccessorAdapter userInfosAccessorAdapter, AuthAccessorAdapter authAccessorAdapter, UserApiMapper userApiMapper, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.gabblerApiService = gabblerApiService;
        this.userInfosAccessorAdapter = userInfosAccessorAdapter;
        this.authAccessorAdapter = authAccessorAdapter;
        this.userApiMapper = userApiMapper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<UserAndToken> authenticateAndGetToken(@RequestBody UserAuth userAuth) {
        if (getToken(userAuth) != null) {
            UserAndToken userAndToken = getToken(userAuth);
            DomainUserResult domainUserResult = userInfosAccessorAdapter.getUserByEmail(userAuth.getEmail());
            return gabblerApiService.getResponse(userAndToken, domainUserResult);
        } else {
            throw new UsernameNotFoundException("Erreur lors de l'authentification de l'utilisateur !");
        }
    }

    @Override
    public ResponseEntity<UserAndToken> registerUser(@RequestBody UserRegister userRegister) {
        DomainUserResult domainUserResult = this.authAccessorAdapter.register(this.userApiMapper.toUserRegisterToDomainUserRegistrationInfosResult(userRegister));
        if (domainUserResult != null) {
            DomainUserInfosAuthResult domainUserInfosAuthResult = authAccessorAdapter.getUserCredentialByEmail(domainUserResult.getDomainUser().getEmail());
            if (domainUserInfosAuthResult.getUserAuthInfo() != null) {
                domainUserInfosAuthResult.getUserAuthInfo().setPassword(userRegister.getPassword());
                UserAndToken userAndToken = getToken(this.userApiMapper.toDomainUserInfosAuthResultToUserAuth(domainUserInfosAuthResult));
                return gabblerApiService.getResponse(userAndToken, domainUserResult);
            }
        }
        throw new UsernameNotFoundException("Erreur lors de l'enregistrement de l'utilisateur !");
    }

    private UserAndToken getToken(UserAuth userAuth) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userAuth.getEmail(),
                userAuth.getPassword()));
        if (authentication.isAuthenticated()) {
            DomainUserResult domainUserResult = userInfosAccessorAdapter.getUserByEmail(userAuth.getEmail());
            User user = userApiMapper.toDomainUserToUser(domainUserResult.getDomainUser());
            UserAndToken userAndToken = new UserAndToken();
            userAndToken.setToken(jwtService.generateToken(userAuth.getEmail()));
            userAndToken.setUser(user);
            return userAndToken;
        } else {
            return null;
        }
    }
}
