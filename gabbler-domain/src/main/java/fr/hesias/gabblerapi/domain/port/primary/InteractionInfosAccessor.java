package fr.hesias.gabblerapi.domain.port.primary;

import fr.hesias.gabblerapi.domain.result.DomainUserInteractionsResult;

public interface InteractionInfosAccessor
{

    DomainUserInteractionsResult getInteractionsByUserUuid(String userUuid);

}
