package fr.hesias.gabblerapi.application.api.service;


import fr.hesias.gabblerapi.application.adapter.UserInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.api.mapper.UserApiMapper;
import fr.hesias.gabblerapi.desc.api.server.UserApiDelegate;
import fr.hesias.gabblerapi.desc.api.server.model.User;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
public class UserApiDelegateImpl implements UserApiDelegate
{

    private final UserApiMapper userApiMapper;

    private final GabblerApiService gabblerApiService;


    private final UserInfosAccessorAdapter userInfosAccessorAdapter;

    public UserApiDelegateImpl(UserApiMapper userApiMapper,
                               GabblerApiService gabblerApiService,
                               UserInfosAccessorAdapter userInfosAccessorAdapter)
    {

        this.userApiMapper = userApiMapper;
        this.gabblerApiService = gabblerApiService;
        this.userInfosAccessorAdapter = userInfosAccessorAdapter;
    }

    @Override
    public ResponseEntity<List<User>> getUsers()
    {


        final DomainUsersResult domainUsersResult = userInfosAccessorAdapter.getUsers();

        return gabblerApiService.getResponse(userApiMapper.toDomainUsersResultToUsersList(domainUsersResult),
                                             domainUsersResult);
    }

}
