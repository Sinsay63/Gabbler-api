package fr.hesias.gabblerapi.infrastructure.persister.persistence.config;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.UserDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.UserRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "fr.hesias.gabblerapi.infrastructure.persister.persistence.repository")
@EntityScan(basePackages = "fr.hesias.gabblerapi.infrastructure.persister.persistence.entity")
@PropertySource("classpath:application.properties")

public class PersistenceConfiguration {

    public PersistenceConfiguration() {

        super();

    }

    @Bean
    public UserDao userDao(final UserRepository userRepository) {

        return new UserDao(userRepository);
    }

}
