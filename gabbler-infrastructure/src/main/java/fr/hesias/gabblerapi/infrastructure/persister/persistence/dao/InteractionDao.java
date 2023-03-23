package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.InteractionRepository;

public class InteractionDao {

    private final InteractionRepository interactionRepository;

    public InteractionDao(final InteractionRepository interactionRepository) {

        super();
        this.interactionRepository = interactionRepository;
    }

}
