package fr.hesias.gabblerapi.application.api.service;


import fr.hesias.gabblerapi.application.adapter.InteractionInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.adapter.UserInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.api.mapper.InteractionApiMapper;
import fr.hesias.gabblerapi.application.api.mapper.UserApiMapper;
import fr.hesias.gabblerapi.desc.api.server.UserApiDelegate;
import fr.hesias.gabblerapi.desc.api.server.model.InteractionUser;
import fr.hesias.gabblerapi.desc.api.server.model.User;
import fr.hesias.gabblerapi.domain.result.DomainUserInteractionsResult;
import fr.hesias.gabblerapi.domain.result.DomainUserResult;
import fr.hesias.gabblerapi.domain.result.DomainUsersResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
public class UserApiDelegateImpl implements UserApiDelegate
{

    private final UserApiMapper userApiMapper;

    private final InteractionApiMapper interactionApiMapper;

    private final GabblerApiService gabblerApiService;


    private final UserInfosAccessorAdapter userInfosAccessorAdapter;

    private final InteractionInfosAccessorAdapter interactionInfosAccessorAdapter;

    public UserApiDelegateImpl(UserApiMapper userApiMapper,
                               InteractionApiMapper interactionApiMapper,
                               GabblerApiService gabblerApiService,
                               UserInfosAccessorAdapter userInfosAccessorAdapter,
                               InteractionInfosAccessorAdapter interactionInfosAccessorAdapter)
    {

        this.userApiMapper = userApiMapper;
        this.interactionApiMapper = interactionApiMapper;
        this.gabblerApiService = gabblerApiService;
        this.userInfosAccessorAdapter = userInfosAccessorAdapter;
        this.interactionInfosAccessorAdapter = interactionInfosAccessorAdapter;
    }

    @Override
    public ResponseEntity<List<User>> getUsers()
    {


        final DomainUsersResult domainUsersResult = userInfosAccessorAdapter.getUsers();

        return gabblerApiService.getResponse(userApiMapper.toDomainUsersResultToUsersList(domainUsersResult),
                                             domainUsersResult);
    }

    @Override
    public ResponseEntity<User> getUserByUuid(String userUuid)
    {


        final DomainUserResult domainUserResult = userInfosAccessorAdapter.getUserByUuid(userUuid);

        return gabblerApiService.getResponse(userApiMapper.toDomainUserResultToUser(domainUserResult),
                                             domainUserResult);
    }

    @Override
    public ResponseEntity<List<User>> getSuggestionUserConnected(String userUuid)
    {

        final DomainUsersResult domainUsersResult = userInfosAccessorAdapter.getSuggestionsUserConnected(userUuid);

        return gabblerApiService.getResponse(userApiMapper.toDomainUsersResultToUsersList(domainUsersResult),
                                             domainUsersResult);
    }

    @Override
    public ResponseEntity<List<User>> getSuggestionUserNotConnected()
    {

        final DomainUsersResult domainUsersResult = userInfosAccessorAdapter.getSuggestionsUserNotConnected();

        return gabblerApiService.getResponse(userApiMapper.toDomainUsersResultToUsersList(domainUsersResult),
                                             domainUsersResult);
    }

    @Override
    public ResponseEntity<List<InteractionUser>> getInteractionsByUserUuid(String userUuid)
    {

        final DomainUserInteractionsResult domainUserInteractionsResult = interactionInfosAccessorAdapter.getInteractionsByUserUuid(
                userUuid);

        return gabblerApiService.getResponse(interactionApiMapper.toDomainUserInteractionsResultToUsersList(
                                                     domainUserInteractionsResult),
                                             domainUserInteractionsResult);
    }

}
