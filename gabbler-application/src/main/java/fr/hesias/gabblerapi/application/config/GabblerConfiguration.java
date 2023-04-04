package fr.hesias.gabblerapi.application.config;

import fr.hesias.gabblerapi.application.api.config.GabblerApiConfig;
import fr.hesias.gabblerapi.desc.config.UserClientApiConfig;
import fr.hesias.gabblerapi.domain.port.primary.GabInfosAccessor;
import fr.hesias.gabblerapi.domain.port.primary.UserInfosAccessor;
import fr.hesias.gabblerapi.domain.port.secondary.GabPersister;
import fr.hesias.gabblerapi.domain.port.secondary.UserPersister;
import fr.hesias.gabblerapi.domain.service.GabInfosAccessorImpl;
import fr.hesias.gabblerapi.domain.service.UserInfosAccessorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({GabblerApiConfig.class, UserClientApiConfig.class})
@PropertySource("classpath:application.yml")
public class GabblerConfiguration {

    @Bean
    public UserInfosAccessor userInfosAccessor(final UserPersister userPersister) {

        return new UserInfosAccessorImpl(userPersister);
    }

    @Bean
    public GabInfosAccessor gabInfosAccessor(final GabPersister gabPersister) {

        return new GabInfosAccessorImpl(gabPersister);
    }

}
