package fr.hesias.gabblerapi.application.config;

import fr.hesias.gabblerapi.application.api.mapper.JwtAuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig
{

    public static final String ADMIN = "admin";

    public static final String USER = "user";

    public static String CONTEXT_PATH = "/api";


    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {

        http.authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, CONTEXT_PATH + "/users", CONTEXT_PATH + "/users/**").permitAll()
            .requestMatchers(HttpMethod.GET, CONTEXT_PATH + "/gabs", CONTEXT_PATH + "/gabs/**").hasRole(USER)
            .requestMatchers(HttpMethod.GET, "/actuator", "/actuator/**").hasRole(ADMIN)
            .requestMatchers(HttpMethod.GET, CONTEXT_PATH + "/doc/json", CONTEXT_PATH + "/doc/json/**").permitAll();
//            .anyRequest().authenticated();
        http.oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().and().csrf().disable();
        return http.build();
    }

}
