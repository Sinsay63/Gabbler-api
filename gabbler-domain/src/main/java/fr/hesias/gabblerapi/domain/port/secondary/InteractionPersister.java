package fr.hesias.gabblerapi.domain.port.secondary;

import fr.hesias.gabblerapi.domain.result.DomainUserInteractionsResult;

public interface InteractionPersister
{

    DomainUserInteractionsResult getInteractionsByUserUuid(String userUuid);

}
