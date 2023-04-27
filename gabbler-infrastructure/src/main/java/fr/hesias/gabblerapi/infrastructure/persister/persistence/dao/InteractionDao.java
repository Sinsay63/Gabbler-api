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

    public List<Interaction> getInteractionsByGabId(int gabId)
    {

        return interactionRepository.getInteractionByGab_Id(gabId);
    }

    public int countInteractionByActionAndGabId(InteractionTypeEnum action, int gabId)
    {

        return interactionRepository.countInteractionByActionAndGab_Id(action, gabId).orElse(0);
    }

    public List<Interaction> getInteractionsByUserUuid(String userUuid)
    {

        return interactionRepository.getInteractionByUser_Uuid(userUuid);
    }

    public Interaction getInteractionByUserUuidAndGabId(Interaction interaction)
    {

        return interactionRepository.getInteractionByUser_UuidAndGab_Id(interaction.getUser().getUuid(),
                                                                        interaction.getGab().getId()).orElse(null);
    }

    public Interaction addInteraction(Interaction interaction)
    {

        return interactionRepository.save(interaction);
    }


    public Interaction updateInteraction(Interaction interaction)
    {

        return interactionRepository.save(interaction);
    }

    public void removeInteraction(Interaction interaction)
    {

        interactionRepository.delete(interaction);
    }

}
