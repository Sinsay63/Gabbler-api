package fr.hesias.gabblerapi.application.config;

import fr.hesias.gabblerapi.application.api.config.GabblerApiConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({GabblerApiConfig.class})
public class GabblerConfiguration {

}
