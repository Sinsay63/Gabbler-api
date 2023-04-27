package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.result.DomainUserInteractionResult;
import fr.hesias.gabblerapi.domain.result.DomainUserInteractionsResult;

public interface InteractionPersister
{

    DomainUserInteractionsResult getInteractionsByUserUuid(String userUuid);

    DomainUserInteractionResult interactionCUD(String userUuid, int gabId, String action);

    void deleteInteraction(String userUuid, int gabId);

}
