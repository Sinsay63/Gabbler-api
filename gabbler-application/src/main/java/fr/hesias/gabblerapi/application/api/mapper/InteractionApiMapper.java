package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.desc.api.server.model.InteractionUser;
import fr.hesias.gabblerapi.domain.result.DomainUserInteractionResult;
import fr.hesias.gabblerapi.domain.result.DomainUserInteractionsResult;

import java.util.ArrayList;
import java.util.List;

public class InteractionApiMapper
{

    public List<InteractionUser> toDomainUserInteractionsResultToUsersList(final DomainUserInteractionsResult domainUserInteractionResult)
    {

        final List<InteractionUser> userList = new ArrayList<>();

        if (domainUserInteractionResult.isOk())
        {
            for (DomainUserInteractionResult domainUserInteraction : domainUserInteractionResult.getDomainUserInteractionsResult())
            {
                userList.add(toDomainUserInteractionResultToInteractionUser(domainUserInteraction));
            }
        }
        return userList;
    }

    public InteractionUser toDomainUserInteractionResultToInteractionUser(DomainUserInteractionResult domainUserInteractionsResult)
    {

        final InteractionUser interactionUser = new InteractionUser();

        if (domainUserInteractionsResult.isOk())
        {
            interactionUser.setInteraction(domainUserInteractionsResult.getInteractionType());
            interactionUser.setUserUuid(domainUserInteractionsResult.getUserUuid());
            interactionUser.setGabId(domainUserInteractionsResult.getGabId());

        }
        return interactionUser;
    }


}
