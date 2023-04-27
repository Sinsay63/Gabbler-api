package fr.hesias.gabblerapi.infrastructure.persister.persistence.repository;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Interaction;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.model.InteractionTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("interactionRepository")
public interface InteractionRepository extends JpaRepository<Interaction, Integer>
{

    List<Interaction> getInteractionByGab_Id(int gabId);

    Optional<Integer> countInteractionByActionAndGab_Id(InteractionTypeEnum action, int gabId);

    List<Interaction> getInteractionByUser_Uuid(String userUuid);

    Optional<Interaction> getInteractionByUser_UuidAndGab_Id(String userUuid, int gabId);

}
