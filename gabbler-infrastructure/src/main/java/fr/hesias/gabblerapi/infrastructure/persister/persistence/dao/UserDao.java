package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.UserRepository;

import java.util.List;

public class UserDao {

    private final UserRepository userRepository;

    public UserDao(final UserRepository userRepository) {

        super();
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {

        return userRepository.findAll();
    }

    public User getUserById(final int id) {

        return userRepository.findById(id).orElse(null);
    }

}
