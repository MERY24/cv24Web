package fr.univrouen.xmlProject.Configuration.Security;


import fr.univrouen.xmlProject.Filters.JWTAuthentificationFilter;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true , securedEnabled = true , prePostEnabled = true)
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final JWTAuthentificationFilter jwtAuthentificationFilter;


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws  Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestHandler = new CsrfTokenRequestAttributeHandler();
        csrfTokenRequestHandler.setCsrfRequestAttributeName("_csrf");

        http
                .csrf((csrf) -> csrf.disable()).
                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests((requests)->requests
                        .requestMatchers("/api/auth/login","/api/users/register").permitAll()
//                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }



}
