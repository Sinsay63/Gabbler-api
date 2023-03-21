package fr.hesias.gabblerapi.application.api.config;

import fr.hesias.gabblerapi.application.api.mapper.GabblerApiErrorMapper;
import fr.hesias.gabblerapi.application.api.mapper.UserApiMapper;
import fr.hesias.gabblerapi.application.api.service.GabblerApiDelegateImpl;
import fr.hesias.gabblerapi.application.api.service.GabblerApiErrorHandler;
import fr.hesias.gabblerapi.domain.port.secondary.UserPersister;
import fr.hesias.gabblerapi.infrastructure.config.InfrastructureAdapterConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({InfrastructureAdapterConfiguration.class})
public class GabblerApiConfig {


    @Bean
    public GabblerApiDelegateImpl gabblerApiDelegateImpl(final UserPersister userPersister) {

        return new GabblerApiDelegateImpl(userPersister);
    }

    @Bean
    public GabblerApiErrorHandler gabblerApiErrorHandler(final GabblerApiErrorMapper gabblerApiErrorMapper) {

        return new GabblerApiErrorHandler(gabblerApiErrorMapper);
    }

    @Bean
    public GabblerApiErrorMapper gabblerApiErrorMapper() {

        return new GabblerApiErrorMapper();
    }

    @Bean
    public UserApiMapper gabblerUserApiMapper() {

        return new UserApiMapper();
    }

}
