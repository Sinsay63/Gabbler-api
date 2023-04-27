package fr.hesias.gabblerapi.infrastructure.persister.persistence.adapter;

import fr.hesias.gabblerapi.domain.port.secondary.InteractionPersister;
import fr.hesias.gabblerapi.domain.result.DomainUserInteractionsResult;
import fr.hesias.gabblerapi.infrastructure.persister.service.InteractionPersisterService;

public class InteractionPersisterAdapter implements InteractionPersister
{

    private final InteractionPersisterService interactionPersisterService;

    public InteractionPersisterAdapter(final InteractionPersisterService interactionPersisterService)
    {

        super();
        this.interactionPersisterService = interactionPersisterService;
    }

    @Override
    public DomainUserInteractionsResult getInteractionsByUserUuid(String userUuid)
    {

        return this.interactionPersisterService.getInteractionByUserUuid(userUuid);
    }

}
