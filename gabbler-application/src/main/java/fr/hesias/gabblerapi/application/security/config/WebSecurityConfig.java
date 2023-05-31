package fr.hesias.gabblerapi.application.security.config;

import fr.hesias.gabblerapi.application.adapter.InteractionInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.adapter.RelationshipsAccessorAdapter;
import fr.hesias.gabblerapi.application.adapter.SubscriptionAccessorAdapter;
import fr.hesias.gabblerapi.application.adapter.UserInfosAccessorAdapter;
import fr.hesias.gabblerapi.application.security.filter.JwtAuthFilter;
import fr.hesias.gabblerapi.application.security.service.JwtService;
import fr.hesias.gabblerapi.application.security.service.UserInfosAuthService;
import fr.hesias.gabblerapi.domain.port.primary.InteractionInfosAccessor;
import fr.hesias.gabblerapi.domain.port.primary.RelationshipsAccessor;
import fr.hesias.gabblerapi.domain.port.primary.SubscriptionAccessor;
import fr.hesias.gabblerapi.domain.port.primary.UserInfosAccessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class WebSecurityConfig {

    private final JwtAuthFilter authFilter;

    public WebSecurityConfig(JwtAuthFilter authFilter) {

        this.authFilter = authFilter;
    }

    @Bean
    public UserDetailsService userDetailsService() {

        return new UserInfosAuthService();
    }

    @Bean
    public UserInfosAccessorAdapter userInfosAccessorAdapter(UserInfosAccessor userInfosAccessor) {

        return new UserInfosAccessorAdapter(userInfosAccessor);
    }

    @Bean
    public SubscriptionAccessorAdapter subscriptionAccessorAdapter(SubscriptionAccessor subscriptionAccessor) {

        return new SubscriptionAccessorAdapter(subscriptionAccessor);
    }

    @Bean
    public RelationshipsAccessorAdapter userRelationshipsAccessorAdapter(RelationshipsAccessor relationshipsAccessor) {

        return new RelationshipsAccessorAdapter(relationshipsAccessor);
    }

    @Bean
    public InteractionInfosAccessorAdapter interactionInfosAccessorAdapter(InteractionInfosAccessor interactionInfosAccessor) {

        return new InteractionInfosAccessorAdapter(interactionInfosAccessor);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        return http.csrf().disable()
//                .cors().disable()
//                .authorizeHttpRequests().requestMatchers("/api/**").permitAll()
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().authenticationProvider(authenticationProvider()).addFilterBefore(authFilter,
//                        UsernamePasswordAuthenticationFilter.class)
//                .build();
        return http.csrf().disable()
                .cors().disable()
                .authorizeHttpRequests().requestMatchers("/api/**").permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authenticationProvider(authenticationProvider()).addFilterBefore(authFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public JwtService jwtService(UserInfosAccessorAdapter userInfosAccessorAdapter) {

        return new JwtService(userInfosAccessorAdapter);
    }

}
