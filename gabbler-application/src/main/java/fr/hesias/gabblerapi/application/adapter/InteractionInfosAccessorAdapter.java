package fr.hesias.gabblerapi.application.adapter;

import fr.hesias.gabblerapi.domain.port.primary.InteractionInfosAccessor;
import fr.hesias.gabblerapi.domain.result.DomainUserInteractionsResult;

public class InteractionInfosAccessorAdapter
{

    private final InteractionInfosAccessor interactionInfosAccessor;

    public InteractionInfosAccessorAdapter(InteractionInfosAccessor interactionInfosAccessor)
    {

        this.interactionInfosAccessor = interactionInfosAccessor;
    }

    public DomainUserInteractionsResult getInteractionsByUserUuid(String userUuid)
    {

        return this.interactionInfosAccessor.getInteractionsByUserUuid(userUuid);
    }


}
