package fr.hesias.gabblerapi.desc.config;

import fr.hesias.gabblerapi.desc.api.ApiClient;
import fr.hesias.gabblerapi.desc.api.client.DocApi;
import fr.hesias.gabblerapi.desc.api.client.GabApi;
import fr.hesias.gabblerapi.desc.api.client.UserApi;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenApiConfig {

    public OpenApiConfig() {

    }

    @ConditionalOnMissingBean(name = "userRestTemplate")
    @Bean
    @Qualifier("userRestTemplate")
    public RestTemplate userRestTemplate() {

        return new RestTemplate();
    }

    @Bean
    public UserApi userApi(@Value("/") final String basePath,
                           @Qualifier("userRestTemplate") final RestTemplate restTemplate) {

        return new UserApi(apiClient(basePath, restTemplate));
    }

    @Bean
    public GabApi gabApi(@Value("/") final String basePath,
                         @Qualifier("userRestTemplate") final RestTemplate restTemplate) {

        return new GabApi(apiClient(basePath, restTemplate));
    }

    @Bean
    public DocApi docApi(@Value("/") final String basePath,
                         @Qualifier("userRestTemplate") final RestTemplate restTemplate) {

        return new DocApi(apiClient(basePath, restTemplate));
    }

    @Bean
    public ApiClient apiClient(@Value("/") final String basePath,
                               @Qualifier("userRestTemplate") final RestTemplate restTemplate) {

        final ApiClient apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(basePath);
        return apiClient;
    }

    @Bean
    public OpenAPI springCustomOpenAPI() {

        return new OpenAPI()
                .info(new Info().title("Gabbler API")
                        .description("l'api pour Gabbler t'as capté")
                        .version("v1.0.0")
                        .license(new License().name("Gabbler 1.0").url("http://localhost:4200")));
    }

}
