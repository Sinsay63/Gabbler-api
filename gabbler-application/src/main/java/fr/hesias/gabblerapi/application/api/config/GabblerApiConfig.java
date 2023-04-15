package fr.hesias.gabblerapi.application.api.config;

import fr.hesias.gabblerapi.application.adapter.GabInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.adapter.UserInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.api.mapper.GabApiMapper;
import fr.hesias.gabblerapi.application.api.mapper.GabblerApiErrorMapper;
import fr.hesias.gabblerapi.application.api.mapper.UserApiMapper;
import fr.hesias.gabblerapi.application.api.service.*;
import fr.hesias.gabblerapi.application.security.service.JwtService;
import fr.hesias.gabblerapi.domain.port.primary.GabInfosAccessor;
import fr.hesias.gabblerapi.infrastructure.config.InfrastructureAdapterConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;

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
    public UserApiMapper gabblerUserApiMapper()
    {

        return new UserApiMapper();
    }

    @Bean
    GabApiMapper gabApiMapper(UserApiMapper userApiMapper)
    {

        return new GabApiMapper(userApiMapper);
    }

    @Bean
    public GabblerApiService gabblerApiService()
    {

        return new GabblerApiService();
    }

    @Bean
    public UserApiDelegateImpl userApiDelegateImpl(final UserApiMapper userApiMapper,
                                                   final GabblerApiService gabblerApiService,
                                                   final UserInfosAccessorAdapter userInfosAccessorAdapter,
                                                   final JwtService jwtService,
                                                   final AuthenticationManager authenticationManager)
    {

        return new UserApiDelegateImpl(userApiMapper,
                                       gabblerApiService,
                                       userInfosAccessorAdapter,
                                       jwtService,
                                       authenticationManager);
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

}
