package fr.hesias.gabblerapi.infrastructure.config;

import fr.hesias.gabblerapi.domain.port.secondary.UserPersister;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.adapter.UserPersisterAdapter;
import fr.hesias.gabblerapi.infrastructure.persister.persistence.config.PersistenceConfiguration;
import fr.hesias.gabblerapi.infrastructure.persister.service.UserPersisterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import({PersistenceConfiguration.class})
public class InfrastructureAdapterConfiguration {

    @Bean
    public UserPersister userPersister(final UserPersisterService userPersisterService) {

        return new UserPersisterAdapter(userPersisterService);
    }

}
