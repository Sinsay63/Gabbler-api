package fr.hesias.gabblerapi.application.config;

import fr.hesias.gabblerapi.application.api.config.GabblerApiConfig;
import fr.hesias.gabblerapi.application.security.config.CorsConfiguration;
import fr.hesias.gabblerapi.application.security.config.WebSecurityConfig;
import fr.hesias.gabblerapi.desc.config.OpenApiConfig;
import fr.hesias.gabblerapi.domain.port.primary.*;
import fr.hesias.gabblerapi.domain.port.secondary.*;
import fr.hesias.gabblerapi.domain.service.*;
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
    public SubscriptionAccessor subscriptionAccessor(final SubscriptionPersister subscriptionPersister)
    {

        return new SubscriptionAccessorImpl(subscriptionPersister);
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
    public RelationshipsAccessor userRelationshipsAccessor(final RelationshipsPersister relationshipsPersister)
    {

        return new RelationshipsAccessorImpl(relationshipsPersister);
    }

}
