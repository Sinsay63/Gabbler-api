package fr.hesias.gabblerapi.application.api.service;

import fr.hesias.gabblerapi.application.adapter.InteractionInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.api.mapper.InteractionApiMapper;
import fr.hesias.gabblerapi.desc.api.server.InteractionApiDelegate;
import fr.hesias.gabblerapi.desc.api.server.model.InteractionUser;
import fr.hesias.gabblerapi.domain.result.DomainUserInteractionResult;
import fr.hesias.gabblerapi.domain.result.DomainUserInteractionsResult;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class InteractionApiDelegateImpl implements InteractionApiDelegate
{

    private final InteractionInfosAccessorAdapter interactionInfosAccessorAdapter;

    private final InteractionApiMapper interactionApiMapper;

    private final GabblerApiService gabblerApiService;


    public InteractionApiDelegateImpl(InteractionInfosAccessorAdapter interactionInfosAccessorAdapter,
                                      InteractionApiMapper interactionApiMapper,
                                      GabblerApiService gabblerApiService)
    {

        this.interactionInfosAccessorAdapter = interactionInfosAccessorAdapter;
        this.interactionApiMapper = interactionApiMapper;
        this.gabblerApiService = gabblerApiService;
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

    @Override
    public ResponseEntity<InteractionUser> interactionCUD(Integer gabId, String userUuid, String interaction)
    {

        final DomainUserInteractionResult domainUserInteractionResult = interactionInfosAccessorAdapter.interactionCUD(
                userUuid,
                gabId,
                interaction);
        return gabblerApiService.getResponse(interactionApiMapper.toDomainUserInteractionResultToInteractionUser(
                                                     domainUserInteractionResult),
                                             domainUserInteractionResult);
    }

}
