package fr.hesias.gabblerapi.infrastructure.persister.persistence.config;

import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.*;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.repository.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "fr.hesias.gabblerapi.infrastructure.persister.persistence.repository")
@EntityScan(basePackages = "fr.hesias.gabblerapi.infrastructure.persister.persistence.entity")
@ComponentScan(basePackages = "fr.hesias.gabblerapi.infrastructure.persister")
@PropertySource("classpath:application.properties")
public class PersistenceConfiguration {

    public PersistenceConfiguration() {

        super();

    }

    @Bean
    public UserDao userDao(final UserRepository userRepository) {

        return new UserDao(userRepository);
    }

    @Bean
    public GabDao gabDao(final GabRepository gabRepository) {

        return new GabDao(gabRepository);
    }

    @Bean
    public SubscriptionDao subscriptionDao(final SubscriptionRepository subscriptionRepository) {

        return new SubscriptionDao(subscriptionRepository);
    }

    @Bean
    public SubscriptionOfferDao subscriptionOfferDao(final SubscriptionOfferRepository subscriptionOfferRepository) {

        return new SubscriptionOfferDao(subscriptionOfferRepository);
    }

    @Bean
    public InteractionDao interactionDao(final InteractionRepository interactionRepository) {

        return new InteractionDao(interactionRepository);
    }

    @Bean
    public UserRelationshipsDao userRelationshipsDao(final UserRelationshipsRepository userRelationshipsRepository) {

        return new UserRelationshipsDao(userRelationshipsRepository);
    }

}
