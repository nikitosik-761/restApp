package restApplication.rest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import restApplication.rest.utils.MyAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

private final MyAuthenticationSuccessHandler successHandler;




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2Login( login -> login.successHandler(successHandler));



        return http.build();
    }

}
