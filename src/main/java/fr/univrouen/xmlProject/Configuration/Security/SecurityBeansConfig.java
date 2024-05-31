package fr.univrouen.xmlProject.Configuration.Security;

import fr.univrouen.xmlProject.Repositories.UserRepository;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@RequiredArgsConstructor
@Configuration
public class SecurityBeansConfig {

    private final UserRepository userRepository;


    @Bean
    UserDetailsService userDetailsService() {
        return username -> (UserDetails) userRepository.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    //load env vars, so it can be used in autowire inside Constant class
    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure().load();
    }

    @Bean //Spring is unable to find a bean of type BCryptPasswordEncoder to inject into SecurityConfig, that's why I'm doing it explicitly here
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return authProvider;
    }

}
