package fr.hesias.gabblerapi.infrastructure.persister.persistence.dao;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserDao
{

    private final UserRepository userRepository;

    public UserDao(final UserRepository userRepository)
    {

        super();
        this.userRepository = userRepository;
    }

    public List<User> getUsers()
    {

        return userRepository.findAll();
    }

    public Optional<User> getUserById(final int id)
    {

        return userRepository.findById(id);
    }

    public User addUser(final User user)
    {

        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(final String email)
    {

        return userRepository.findByEmail(email);
    }

}
