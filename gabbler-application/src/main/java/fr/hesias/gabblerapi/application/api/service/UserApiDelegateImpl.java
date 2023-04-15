package fr.hesias.gabblerapi.application.api.service;


import fr.hesias.gabblerapi.application.adapter.UserInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.api.mapper.UserApiMapper;
import fr.hesias.gabblerapi.application.security.service.JwtService;
import fr.hesias.gabblerapi.desc.api.server.UserApiDelegate;
import fr.hesias.gabblerapi.desc.api.server.model.User;
import fr.hesias.gabblerapi.desc.api.server.model.UserAndToken;
import fr.hesias.gabblerapi.desc.api.server.model.UserAuth;
import fr.hesias.gabblerapi.desc.api.server.model.Users;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
public class UserApiDelegateImpl implements UserApiDelegate
{

    private final UserApiMapper userApiMapper;

    private final GabblerApiService gabblerApiService;

    private final UserInfosAccessorAdapter userInfosAccessorAdapter;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public UserApiDelegateImpl(UserApiMapper userApiMapper,
                               GabblerApiService gabblerApiService,
                               UserInfosAccessorAdapter userInfosAccessorAdapter,
                               JwtService jwtService,
                               AuthenticationManager authenticationManager)
    {

        this.userApiMapper = userApiMapper;
        this.gabblerApiService = gabblerApiService;
        this.userInfosAccessorAdapter = userInfosAccessorAdapter;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<Users> getUsers()
    {


        final DomainUsersResult domainUsersResult = userInfosAccessorAdapter.getUsers();

        return gabblerApiService.getResponse(userApiMapper.toUsers(domainUsersResult), domainUsersResult);
    }

    @Override
    public ResponseEntity<UserAndToken> authenticateAndGetToken(@RequestBody UserAuth userAuth)
    {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userAuth.getEmail(),
                userAuth.getPassword()));
        if (authentication.isAuthenticated())
        {
            DomainUserResult domainUserResult = userInfosAccessorAdapter.getUserByEmail(userAuth.getEmail());
            User user = userApiMapper.toUser(domainUserResult.getDomainUser());
            UserAndToken userAndToken = new UserAndToken();
            userAndToken.setToken(jwtService.generateToken(userAuth.getEmail()));
            userAndToken.setUser(user);
            return gabblerApiService.getResponse(userAndToken, domainUserResult);
        }
        else
        {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}
