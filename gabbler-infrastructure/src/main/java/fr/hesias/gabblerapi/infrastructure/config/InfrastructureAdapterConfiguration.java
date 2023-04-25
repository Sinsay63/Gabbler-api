package fr.hesias.gabblerapi.infrastructure.config;

import fr.hesias.gabblerapi.domain.port.secondary.GabPersister;
import fr.hesias.gabblerapi.domain.port.secondary.UserPersister;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.adapter.GabPersisterAdapter;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.adapter.UserPersisterAdapter;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.config.PersistenceConfiguration;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.GabDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.InteractionDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.MediaDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.dao.UserDao;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.mapper.GabblerInfraMapper;
import fr.hesias.gabblerapi.infrastructure.persister.service.GabPersisterService;
import fr.hesias.gabblerapi.infrastructure.persister.service.UserPersisterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
    GabPersister gabPersister(final GabPersisterService gabPersisterService)
    {

        return new GabPersisterAdapter(gabPersisterService);
    }

    @Bean
    UserPersisterService userPersisterService(final UserDao userDao,
                                              final MediaDao mediaDao,
                                              final GabblerInfraMapper gabblerInfraMapper)
    {

        return new UserPersisterService(userDao, mediaDao, gabblerInfraMapper);
    }

    @Bean
    GabPersisterService gabPersisterService(final GabDao gabDao,
                                            final UserDao userDao,
                                            MediaDao mediaDao,
                                            final InteractionDao interactionDao,
                                            final GabblerInfraMapper gabblerInfraMapper)
    {

        return new GabPersisterService(gabDao, userDao, mediaDao, interactionDao, gabblerInfraMapper);
    }

    @Bean
    GabblerInfraMapper gabblerInfraMapper()
    {

        return new GabblerInfraMapper();
    }


}
