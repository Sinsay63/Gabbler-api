package fr.hesias.gabblerapi.application.api.service;

import fr.hesias.gabblerapi.application.adapter.AuthAccessorAdapter;
import fr.hesias.gabblerapi.application.adapter.UserInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.api.mapper.UserApiMapper;
import fr.hesias.gabblerapi.application.security.service.JwtService;
import fr.hesias.gabblerapi.desc.api.server.AuthApiDelegate;
import fr.hesias.gabblerapi.desc.api.server.model.UserAuth;
import fr.hesias.gabblerapi.desc.api.server.model.UserRegister;
import fr.hesias.gabblerapi.desc.api.server.model.UserToken;
import fr.hesias.gabblerapi.domain.result.DomainResultable;
import fr.hesias.gabblerapi.domain.result.DomainUserInfosAuthResult;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

import static com.github.jknack.handlebars.internal.lang3.StringUtils.isNotEmpty;
import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.BAD_REQUEST;
import static fr.hesias.gabblerapi.domain.model.DomainAccessStatus.OK;

public class AuthApiDelegateImpl implements AuthApiDelegate
{

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final GabblerApiService gabblerApiService;

    private final UserInfosAccessorAdapter userInfosAccessorAdapter;

    private final AuthAccessorAdapter authAccessorAdapter;

    private final UserApiMapper userApiMapper;


    public AuthApiDelegateImpl(GabblerApiService gabblerApiService,
                               UserInfosAccessorAdapter userInfosAccessorAdapter,
                               AuthAccessorAdapter authAccessorAdapter,
                               UserApiMapper userApiMapper,
                               JwtService jwtService,
                               AuthenticationManager authenticationManager)
    {

        this.gabblerApiService = gabblerApiService;
        this.userInfosAccessorAdapter = userInfosAccessorAdapter;
        this.authAccessorAdapter = authAccessorAdapter;
        this.userApiMapper = userApiMapper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity authenticateAndGetToken(@RequestBody UserAuth userAuth)
    {

        UserToken userToken = getToken(userAuth);
        if (isNotEmpty(userToken.getToken()))
        {
            return gabblerApiService.getResponse(getToken(userAuth), new DomainResultable(OK));
        }
        DomainResultable domainResultable = new DomainResultable(BAD_REQUEST);
        domainResultable.updateMessage("Erreur lors de l'authentification de l'utilisateur !");
        return new ResponseEntity<>(domainResultable, HttpStatusCode.valueOf(400));
    }

    @Override
    public ResponseEntity<UserToken> registerUser(@RequestBody UserRegister userRegister)
    {

        DomainUserResult domainUserResult = this.authAccessorAdapter.register(this.userApiMapper.toUserRegisterToDomainUserRegistrationInfosResult(
                userRegister));
        if (domainUserResult != null)
        {
            DomainUserInfosAuthResult domainUserInfosAuthResult = authAccessorAdapter.getUserCredentialByEmail(
                    domainUserResult.getDomainUser().getEmail());
            if (domainUserInfosAuthResult.getUserAuthInfo() != null)
            {
                domainUserInfosAuthResult.getUserAuthInfo().setPassword(userRegister.getPassword());
                UserToken UserToken = getToken(this.userApiMapper.toDomainUserInfosAuthResultToUserAuth(
                        domainUserInfosAuthResult));
                return gabblerApiService.getResponse(UserToken, domainUserResult);
            }
        }
        throw new UsernameNotFoundException("Erreur lors de l'enregistrement de l'utilisateur !");
    }

    private UserToken getToken(UserAuth userAuth)
    {

        UserToken userToken = new UserToken();
        try
        {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userAuth.getEmail(),
                    userAuth.getPassword()));
            if (authentication.isAuthenticated())
            {
                userToken.setToken(jwtService.generateToken(userAuth.getEmail()));
            }
        }
        catch (Exception e)
        {
            return userToken;
        }
        return userToken;
    }

}
