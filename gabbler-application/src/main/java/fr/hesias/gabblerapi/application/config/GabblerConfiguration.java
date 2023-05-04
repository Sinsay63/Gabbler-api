package fr.hesias.gabblerapi.application.config;

import fr.hesias.gabblerapi.application.api.config.GabblerApiConfig;
import fr.hesias.gabblerapi.application.security.config.CorsConfiguration;
import fr.hesias.gabblerapi.application.security.config.WebSecurityConfig;
import fr.hesias.gabblerapi.desc.config.OpenApiConfig;
import fr.hesias.gabblerapi.domain.port.primary.GabInfosAccessor;
import fr.hesias.gabblerapi.domain.port.primary.InteractionInfosAccessor;
import fr.hesias.gabblerapi.domain.port.primary.UserInfosAccessor;
import fr.hesias.gabblerapi.domain.port.primary.UserRelationshipsAccessor;
import fr.hesias.gabblerapi.domain.port.secondary.GabPersister;
import fr.hesias.gabblerapi.domain.port.secondary.InteractionPersister;
import fr.hesias.gabblerapi.domain.port.secondary.UserPersister;
import fr.hesias.gabblerapi.domain.port.secondary.UserRelationshipsPersister;
import fr.hesias.gabblerapi.domain.service.GabInfosAccessorImpl;
import fr.hesias.gabblerapi.domain.service.InteractionInfosAccessorImpl;
import fr.hesias.gabblerapi.domain.service.UserInfosAccessorImpl;
import fr.hesias.gabblerapi.domain.service.UserRelationshipsAccessorImpl;
import org.springframework.context.annotation.*;

@Configuration
@Import({GabblerApiConfig.class, OpenApiConfig.class, WebSecurityConfig.class, CorsConfiguration.class})
@ComponentScan(basePackages = {"fr.hesias.gabblerapi.application.security"})
@PropertySource("classpath:application.yml")
public class GabblerConfiguration
{

    @Bean
    public UserInfosAccessor userInfosAccessor(final UserPersister userPersister)
    {

        return new UserInfosAccessorImpl(userPersister);
    }

    @Bean
    public GabInfosAccessor gabInfosAccessor(final GabPersister gabPersister)
    {

        return new GabInfosAccessorImpl(gabPersister);
    }

    @Bean
    public InteractionInfosAccessor interactionInfosAccessor(final InteractionPersister interactionPersister)
    {

        return new InteractionInfosAccessorImpl(interactionPersister);
    }

    @Bean
    public UserRelationshipsAccessor userRelationshipsAccessor(final UserRelationshipsPersister userRelationshipsPersister)
    {

        return new UserRelationshipsAccessorImpl(userRelationshipsPersister);
    }

}
