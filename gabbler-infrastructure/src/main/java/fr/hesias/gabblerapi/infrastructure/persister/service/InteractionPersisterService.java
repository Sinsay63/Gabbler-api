package fr.hesias.gabblerapi.infrastructure.persister.service;

import fr.hesias.gabblerapi.domain.result.DomainUserInteractionResult;
import fr.hesias.gabblerapi.domain.result.DomainUserInteractionsResult;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.InteractionDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Interaction;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.InteractionTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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

        List<Interaction> interactionList = new ArrayList<>();
        try
        {
            interactionList = interactionDao.getInteractionsByUserUuid(userUuid);

        }
        catch (Exception e)
        {
            log.error("Erreur lors de la récupération des interactions de l'utilisateur {}", userUuid, e);
        }
        return this.gabblerInfraMapper.toInteractionsListToDomainUserInteractionsResult(interactionList);
    }


    public DomainUserInteractionResult interactionCUD(String userUuid, int gabId, String action)
    {

        Interaction interaction = new Interaction();
        Interaction interactionToUpdate = new Interaction();
        interaction.setGab(new Gab(gabId));
        interaction.setUser(new User(userUuid));
        try
        {
            interaction.setAction(InteractionTypeEnum.getInteractionByName(action));
            interactionToUpdate = interactionDao.getInteractionByUserUuidAndGabId(interaction);
            if (interactionToUpdate == null)
            {
                interactionToUpdate = addInteraction(interaction);
            }
            else if (interactionToUpdate.getAction() != interaction.getAction())
            {

                interactionToUpdate.setAction(interaction.getAction());
                updateInteraction(interactionToUpdate);
            }
            else
            {
                removeInteraction(interactionToUpdate);
            }
        }
        catch (Exception e)
        {
            log.error("Erreur lors de l'update ou ajout de l'interaction pour l'utilisateur  {} et le gab {}",
                      interaction.getUser().getUuid(),
                      interaction.getGab().getId(),
                      e);
        }
        return this.gabblerInfraMapper.toInteractionToDomainUserInteractionResult(interactionToUpdate);
    }


    public Interaction addInteraction(Interaction interaction)
    {

        return interactionDao.addInteraction(interaction);
    }

    private void removeInteraction(Interaction interaction)
    {

        interactionDao.removeInteraction(interaction);
    }

    private Interaction updateInteraction(Interaction interaction)
    {

        return interactionDao.updateInteraction(interaction);
    }

}
