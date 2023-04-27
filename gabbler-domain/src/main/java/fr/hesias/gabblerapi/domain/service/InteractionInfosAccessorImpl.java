package fr.hesias.gabblerapi.domain.service;

import fr.hesias.gabblerapi.domain.port.primary.InteractionInfosAccessor;
import fr.hesias.gabblerapi.domain.port.secondary.InteractionPersister;
import fr.hesias.gabblerapi.domain.result.DomainUserInteractionResult;
import fr.hesias.gabblerapi.domain.result.DomainUserInteractionsResult;

public class InteractionInfosAccessorImpl implements InteractionInfosAccessor
{

    private final InteractionPersister interactionPersister;

    public InteractionInfosAccessorImpl(InteractionPersister interactionPersister)
    {

        this.interactionPersister = interactionPersister;
    }

    @Override
    public DomainUserInteractionsResult getInteractionsByUserUuid(String userUuid)
    {

        return this.interactionPersister.getInteractionsByUserUuid(userUuid);
    }

    @Override
    public DomainUserInteractionResult interactionCUD(String userUuid, int gabId, String action)
    {

        return this.interactionPersister.interactionCUD(userUuid, gabId, action);
    }

}
