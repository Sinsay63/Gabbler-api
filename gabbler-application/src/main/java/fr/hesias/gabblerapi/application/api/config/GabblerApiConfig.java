package fr.hesias.gabblerapi.application.api.config;

import fr.hesias.gabblerapi.application.adapter.GabInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.adapter.UserInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.api.mapper.GabApiMapper;
import fr.hesias.gabblerapi.application.api.mapper.GabblerApiErrorMapper;
import fr.hesias.gabblerapi.application.api.mapper.UserApiMapper;
import fr.hesias.gabblerapi.application.api.service.GabApiDelegateImpl;
import fr.hesias.gabblerapi.application.api.service.GabblerApiErrorHandler;
import fr.hesias.gabblerapi.application.api.service.GabblerApiService;
import fr.hesias.gabblerapi.application.api.service.UserApiDelegateImpl;
import fr.hesias.gabblerapi.domain.port.primary.GabInfosAccessor;
import fr.hesias.gabblerapi.domain.port.primary.UserInfosAccessor;
import fr.hesias.gabblerapi.infrastructure.config.InfrastructureAdapterConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({InfrastructureAdapterConfiguration.class})
public class GabblerApiConfig {


    @Bean
    public UserInfosAccessorAdapter userInfosAccessorAdapter(final UserInfosAccessor userInfosAccessor) {

        return new UserInfosAccessorAdapter(userInfosAccessor);
    }

    @Bean
    public GabInfosAccessorAdapter gabInfosAccessorAdapter(final GabInfosAccessor gabInfosAccessor) {

        return new GabInfosAccessorAdapter(gabInfosAccessor);
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

    @Bean
    GabApiMapper gabApiMapper(UserApiMapper userApiMapper) {

        return new GabApiMapper(userApiMapper);
    }

    @Bean
    public GabblerApiService gabblerApiService() {

        return new GabblerApiService();
    }

    @Bean
    public UserApiDelegateImpl userApiDelegateImpl(final UserApiMapper userApiMapper, final GabblerApiService gabblerApiService, final UserInfosAccessorAdapter userInfosAccessorAdapter) {

        return new UserApiDelegateImpl(userApiMapper, gabblerApiService, userInfosAccessorAdapter);
    }

    @Bean
    public GabApiDelegateImpl gabApiDelegateImpl(final GabInfosAccessorAdapter gabInfosAccessorAdapter, final GabblerApiService gabblerApiService, final GabApiMapper gabApiMapper) {

        return new GabApiDelegateImpl(gabInfosAccessorAdapter, gabblerApiService, gabApiMapper);
    }

}
