package fr.hesias.gabblerapi.application.application;

import fr.hesias.gabblerapi.application.api.config.GabblerApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({GabblerApiConfig.class})
public class GabblerApplication {

    public static void main(final String[] args) {

        SpringApplication.run(GabblerApplication.class, args);
    }

}
