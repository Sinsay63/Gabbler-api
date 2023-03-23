package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.UserRelationshipsRepository;

public class UserRelationshipsDao {

    private final UserRelationshipsRepository userRelationshipsRepository;

    public UserRelationshipsDao(final UserRelationshipsRepository userRelationshipsRepository) {

        super();
        this.userRelationshipsRepository = userRelationshipsRepository;
    }

}
