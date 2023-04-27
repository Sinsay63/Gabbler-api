package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.result.DomainUserInteractionsResult;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.InteractionDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Interaction;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;

import java.util.List;

public class InteractionPersisterService
{

    private final InteractionDao interactionDao;

    private final GabblerInfraMapper gabblerInfraMapper;

    public InteractionPersisterService(final InteractionDao interactionDao,
                                       final GabblerInfraMapper gabblerInfraMapper)
    {

        super();
        this.interactionDao = interactionDao;
        this.gabblerInfraMapper = gabblerInfraMapper;
    }

    public DomainUserInteractionsResult getInteractionByUserUuid(String userUuid)
    {

        List<Interaction> interactionList = interactionDao.getInteractionsByUserUuid(userUuid);
        return this.gabblerInfraMapper.toInteractionsListToDomainUserInteractionsResult(interactionList);
    }

}
