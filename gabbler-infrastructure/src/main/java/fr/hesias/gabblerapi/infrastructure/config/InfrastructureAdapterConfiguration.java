package fr.hesias.gabblerapi.infrastructure.config;

import fr.hesias.gabblerapi.domain.port.secondary.*;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.adapter.*;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.config.PersistenceConfiguration;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.*;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import fr.hesias.gabblerapi.infrastructure.persister.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

@Import({PersistenceConfiguration.class})
@Configuration
public class InfrastructureAdapterConfiguration
{

    @Bean
    UserPersister userPersister(final UserPersisterService userPersisterService)
    {

        return new UserPersisterAdapter(userPersisterService);
    }

    @Bean
    SubscriptionPersister subscriptionPersister(final SubscriptionPersisterService subscriptionPersisterService)
    {

        return new SubscriptionPersisterAdapter(subscriptionPersisterService);
    }

    @Bean
    GabPersister gabPersister(final GabPersisterService gabPersisterService)
    {

        return new GabPersisterAdapter(gabPersisterService);
    }

    @Bean
    InteractionPersister interactionPersister(final InteractionPersisterService interactionPersisterService)
    {

        return new InteractionPersisterAdapter(interactionPersisterService);
    }


    @Bean
    RelationshipsPersister userRelationshipsPersister(final RelationshipsPersisterService relationshipsPersisterService)
    {

        return new RelationshipsPersisterAdapter(relationshipsPersisterService);
    }

    @Bean
    UserPersisterService userPersisterService(final UserDao userDao,
                                              final GabDao gabDao,
                                              final MediaDao mediaDao,
                                              final RelationshipsDao relationshipsDao,
                                              final InteractionDao interactionDao,
                                              final GabblerInfraMapper gabblerInfraMapper,
                                              @Lazy final GabPersisterService gabPersisterService,
                                              final SubscriptionDao subscriptionDao)
    {

        return new UserPersisterService(userDao,
                                        gabDao,
                                        mediaDao,
                                        relationshipsDao,
                                        interactionDao,
                                        gabblerInfraMapper,
                                        gabPersisterService, subscriptionDao);
    }

    @Bean
    SubscriptionPersisterService subscriptionPersisterService(final SubscriptionDao subscriptionDao,
                                                              final UserDao userDao,
                                                              final GabblerInfraMapper gabblerInfraMapper)
    {

        return new SubscriptionPersisterService(subscriptionDao, userDao, gabblerInfraMapper);
    }

    @Bean
    GabPersisterService gabPersisterService(final GabDao gabDao,
                                            final UserDao userDao,
                                            MediaDao mediaDao,
                                            final InteractionDao interactionDao,
                                            final RelationshipsDao relationshipsDao,
                                            @Lazy final UserPersisterService userPersisterService,
                                            final GabblerInfraMapper gabblerInfraMapper)
    {

        return new GabPersisterService(gabDao,
                                       userDao,
                                       mediaDao,
                                       interactionDao,
                                       relationshipsDao,
                                       userPersisterService,
                                       gabblerInfraMapper);
    }

    @Bean
    InteractionPersisterService interactionPersisterService(final InteractionDao interactionDao,
                                                            final GabblerInfraMapper gabblerInfraMapper)
    {

        return new InteractionPersisterService(interactionDao, gabblerInfraMapper);
    }

    @Bean
    RelationshipsPersisterService userRelationshipsPersisterService(final RelationshipsDao relationshipsDao,
                                                                    final GabblerInfraMapper gabblerInfraMapper)
    {

        return new RelationshipsPersisterService(relationshipsDao, gabblerInfraMapper);
    }

    @Bean
    GabblerInfraMapper gabblerInfraMapper()
    {

        return new GabblerInfraMapper();
    }


}
