package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.GabRepository;

public class GabDao {

    private final GabRepository gabRepository;

    public GabDao(final GabRepository gabRepository) {
        this.gabRepository = gabRepository;
    }

    public Gab getGabById(final int id) {
        return gabRepository.findById(id).orElse(null);
    }
}
