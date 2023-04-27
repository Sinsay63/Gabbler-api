package fr.hesias.gabblerapi.domain.port.primary;

import fr.hesias.gabblerapi.domain.result.DomainUserInteractionResult;
import fr.hesias.gabblerapi.domain.result.DomainUserInteractionsResult;

public interface InteractionInfosAccessor
{

    DomainUserInteractionsResult getInteractionsByUserUuid(String userUuid);

    DomainUserInteractionResult interactionCUD(String userUuid, int gabId, String action);

}
