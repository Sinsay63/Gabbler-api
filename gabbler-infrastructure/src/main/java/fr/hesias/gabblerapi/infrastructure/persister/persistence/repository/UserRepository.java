package fr.hesias.gabblerapi.infrastructure.persister.persistence.repository;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
    // C'est la où on va déclarer nos fonctions pour nos requêtes custom

    @Query("SELECT User FROM User WHERE User.username = 'Sinsay' OR User.username = 'LorisTrr'")
    List<User> getUsers();
}
