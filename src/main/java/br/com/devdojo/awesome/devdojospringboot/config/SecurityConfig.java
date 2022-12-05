package br.com.devdojo.awesome.devdojospringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

        @Bean
        public InMemoryUserDetailsManager userDetailsService() {
            UserDetails user = User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password("admin")
                    .roles("USER", "ADMIN")
                    .build();
            UserDetails user2 = User.withDefaultPasswordEncoder()
                    .username("bruna")
                    .password("bruna")
                    .roles("USER")
                    .build();
            return new InMemoryUserDetailsManager(user, user2);
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests((auth) ->
                        auth
                            .anyRequest().authenticated()
                    )
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .httpBasic(withDefaults());
            return http.build();
        }

    }



