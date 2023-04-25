package fr.hesias.gabblerapi.infrastructure.persister.persistence.repository;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, String>
{
    // C'est la où on va déclarer nos fonctions pour nos requêtes custom


    Optional<User> findByEmail(String email);

    List<User> findAllByUsernameContainingIgnoreCase(String researchContent);

}
