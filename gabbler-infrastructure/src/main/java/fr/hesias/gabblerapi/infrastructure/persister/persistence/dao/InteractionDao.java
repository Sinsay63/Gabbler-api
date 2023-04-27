package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Interaction;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.InteractionTypeEnum;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.InteractionRepository;

import java.util.List;

public class InteractionDao
{

    private final InteractionRepository interactionRepository;

    public InteractionDao(final InteractionRepository interactionRepository)
    {

        super();
        this.interactionRepository = interactionRepository;
    }

    public List<Interaction> getInteractionByGabId(int gabId)
    {

        return interactionRepository.getInteractionByGab_Id(gabId);
    }

    public int countInteractionByActionAndGabId(InteractionTypeEnum action, int gabId)
    {

        return interactionRepository.countInteractionByActionAndGab_Id(action, gabId).orElse(0);
    }

    public Interaction save(Interaction interaction)
    {

        return interactionRepository.save(interaction);
    }

    public List<Interaction> getInteractionsByUserUuid(String userUuid)
    {

        return interactionRepository.getInteractionByUser_Uuid(userUuid);
    }


}
