package fr.hesias.gabblerapi.application.application;

import fr.hesias.gabblerapi.application.config.GabblerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"fr.hesias.gabblerapi.desc.api.server"})
@Import({GabblerConfiguration.class})
public class GabblerApplication {

    public static void main(final String[] args) {

        SpringApplication.run(GabblerApplication.class, args);
    }

}
