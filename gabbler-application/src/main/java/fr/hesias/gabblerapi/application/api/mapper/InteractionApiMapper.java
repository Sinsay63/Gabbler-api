package fr.hesias.gabblerapi.application.api.mapper;

import fr.hesias.gabblerapi.desc.api.server.model.InteractionUser;
import fr.hesias.gabblerapi.domain.model.DomainInteraction;
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

    public List<InteractionUser> toDomainInteractionListToInteractionUserList(List<DomainInteraction> domainInteractions)
    {

        final List<InteractionUser> interactionUserList = new ArrayList<>();

        if (domainInteractions != null)
        {
            for (DomainInteraction domainInteraction : domainInteractions)
            {
                interactionUserList.add(toDomainInteractionToInteractionUser(domainInteraction));
            }
        }
        return interactionUserList;

    }

    private InteractionUser toDomainInteractionToInteractionUser(DomainInteraction domainInteraction)
    {

        final InteractionUser interactionUser = new InteractionUser();

        if (domainInteraction != null)
        {
            interactionUser.setInteraction(domainInteraction.getInteractionType());
            interactionUser.setUserUuid(domainInteraction.getUserUuid());
            interactionUser.setGabId(domainInteraction.getGabId());

        }
        return interactionUser;
    }


}
