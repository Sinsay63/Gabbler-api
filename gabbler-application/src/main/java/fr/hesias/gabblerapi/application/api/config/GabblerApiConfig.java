package fr.hesias.gabblerapi.application.api.config;

import fr.hesias.gabblerapi.application.adapter.AuthAccessorAdapter;
import fr.hesias.gabblerapi.application.adapter.GabInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.adapter.InteractionInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.adapter.UserInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.api.mapper.*;
import fr.hesias.gabblerapi.application.api.service.*;
import fr.hesias.gabblerapi.application.security.service.JwtService;
import fr.hesias.gabblerapi.domain.port.primary.GabInfosAccessor;
import fr.hesias.gabblerapi.domain.port.primary.UserInfosAccessor;
import fr.hesias.gabblerapi.infrastructure.config.InfrastructureAdapterConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Import({InfrastructureAdapterConfiguration.class})
public class GabblerApiConfig
{


    @Bean
    public GabInfosAccessorAdapter gabInfosAccessorAdapter(final GabInfosAccessor gabInfosAccessor)
    {

        return new GabInfosAccessorAdapter(gabInfosAccessor);
    }

    @Bean
    public AuthAccessorAdapter authAccessorAdapter(final UserInfosAccessor userInfosAccessor)
    {

        return new AuthAccessorAdapter(userInfosAccessor);
    }

    @Bean
    public GabblerApiErrorHandler gabblerApiErrorHandler(final GabblerApiErrorMapper gabblerApiErrorMapper)
    {

        return new GabblerApiErrorHandler(gabblerApiErrorMapper);
    }

    @Bean
    public GabblerApiErrorMapper gabblerApiErrorMapper()
    {

        return new GabblerApiErrorMapper();
    }

    @Bean
    public UserApiMapper gabblerUserApiMapper(PasswordEncoder passwordEncoder)
    {

        return new UserApiMapper(passwordEncoder);
    }

    @Bean
    GabApiMapper gabApiMapper(UserApiMapper userApiMapper)
    {

        return new GabApiMapper(userApiMapper);
    }

    @Bean
    public SearchApiMapper searchApiMapper(GabApiMapper gabApiMapper, UserApiMapper userApiMapper)
    {

        return new SearchApiMapper(gabApiMapper, userApiMapper);
    }

    @Bean
    InteractionApiMapper interactionApiMapper()
    {

        return new InteractionApiMapper();
    }

    @Bean
    public GabblerApiService gabblerApiService()
    {

        return new GabblerApiService();
    }

    @Bean
    public UserApiDelegateImpl userApiDelegateImpl(final UserApiMapper userApiMapper,
                                                   final GabblerApiService gabblerApiService,
                                                   final UserInfosAccessorAdapter userInfosAccessorAdapter)
    {

        return new UserApiDelegateImpl(userApiMapper,
                                       gabblerApiService,
                                       userInfosAccessorAdapter);
    }

    @Bean
    public InteractionApiDelegateImpl interactionApiDelegateImpl(InteractionInfosAccessorAdapter interactionInfosAccessorAdapter,
                                                                 InteractionApiMapper interactionApiMapper,
                                                                 GabblerApiService gabblerApiService)
    {

        return new InteractionApiDelegateImpl(interactionInfosAccessorAdapter,
                                              interactionApiMapper,
                                              gabblerApiService);
    }

    @Bean
    public GabApiDelegateImpl gabApiDelegateImpl(final GabInfosAccessorAdapter gabInfosAccessorAdapter,
                                                 final GabblerApiService gabblerApiService,
                                                 final GabApiMapper gabApiMapper)
    {

        return new GabApiDelegateImpl(gabInfosAccessorAdapter, gabblerApiService, gabApiMapper);
    }

    @Bean
    public DocApiDelegateImpl docApiDelegateImpl()
    {

        return new DocApiDelegateImpl();
    }

    @Bean
    public AuthApiDelegateImpl authApiDelegateImpl(GabblerApiService gabblerApiService,
                                                   UserInfosAccessorAdapter userInfosAccessorAdapter,
                                                   AuthAccessorAdapter authAccessorAdapter,
                                                   UserApiMapper userApiMapper,
                                                   JwtService jwtService,
                                                   AuthenticationManager authenticationManager)
    {

        return new AuthApiDelegateImpl(gabblerApiService,
                                       userInfosAccessorAdapter,
                                       authAccessorAdapter,
                                       userApiMapper,
                                       jwtService,
                                       authenticationManager);
    }

    @Bean
    public SearchApiDelegateImpl searchApiDelegateImpl(GabblerApiService gabblerApiService,
                                                       GabInfosAccessorAdapter gabInfosAccessorAdapter,
                                                       SearchApiMapper searchApiMapper)
    {

        return new SearchApiDelegateImpl(gabblerApiService, gabInfosAccessorAdapter, searchApiMapper);
    }

}
