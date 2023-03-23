package fr.hesias.gabblerapi.desc.config;

import fr.hesias.gabblerapi.desc.api.ApiClient;
import fr.hesias.gabblerapi.desc.api.client.UserApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UserClientApiConfig {

    public UserClientApiConfig() {

    }

    @ConditionalOnMissingBean(name = "userRestTemplate")
    @Bean
    @Qualifier("userRestTemplate")
    public RestTemplate userRestTemplate() {

        return new RestTemplate();
    }

    @Bean
    public UserApi userApi(@Value("/") final String basePath, @Qualifier("userRestTemplate") final RestTemplate restTemplate) {

        return new UserApi(apiClient(basePath, restTemplate));
    }

    @Bean
    public ApiClient apiClient(@Value("/") final String basePath, @Qualifier("userRestTemplate") final RestTemplate restTemplate) {

        final ApiClient apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(basePath);
        return apiClient;
    }

}
