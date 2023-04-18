package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.Gab;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.GabRepository;

import java.util.List;

public class GabDao {

    private final GabRepository gabRepository;

    public GabDao(final GabRepository gabRepository) {

        super();
        this.gabRepository = gabRepository;
    }

    public Gab getGabById(final int id) {

        return gabRepository.findById(id).orElse(null);
    }

    public List<Gab> getGabs() {

        return gabRepository.findAll();
    }

    public List<Gab> getCommentsByParentGabId(final int parentGabId) {

        return gabRepository.findAllByParentGabIdOrderByPostDateAsc(parentGabId);
    }

    public Gab createGab(final Gab gab) {

        return gabRepository.save(gab);
    }
}
