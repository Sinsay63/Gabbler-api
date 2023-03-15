package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.UserRelationshipsRepository;

public class UserRelationShipsDao {

    private final UserRelationshipsRepository userRelationshipsRepository;

    public UserRelationShipsDao(UserRelationshipsRepository userRelationshipsRepository) {
        this.userRelationshipsRepository = userRelationshipsRepository;
    }
}
