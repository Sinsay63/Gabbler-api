package fr.hesias.gabblerapi.infrastructure.persister.persistence.repository;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, String>
{
    // C'est la où on va déclarer nos fonctions pour nos requêtes custom


    Optional<User> findByEmail(String email);

    List<User> findAllByUsernameContainingIgnoreCase(String researchContent);

    @Query(value = "SELECT * FROM user ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<User> getRandomUsersForSuggestionsUserNotConnected();


    @Query(value = "SELECT * FROM user WHERE uuid NOT IN :usersUuid ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<User> getRandomUsersForSuggestionsUserConnected(List<String> usersUuid);


    @Modifying
    @Query(value = " UPDATE User u SET u.is_validated = 1 WHERE u.uuid = :userUuid", nativeQuery = true)
    User validateUserByUserUuuid(String userUuid);

}
