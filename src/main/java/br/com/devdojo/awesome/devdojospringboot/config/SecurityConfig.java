package br.com.devdojo.awesome.devdojospringboot.config;

import br.com.devdojo.awesome.devdojospringboot.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//        @Bean
//        public InMemoryUserDetailsManager userDetailsService() {
//            UserDetails user = User.withDefaultPasswordEncoder()
//                    .username("admin")
//                    .password("admin")
//                    .roles("USER", "ADMIN")
//                    .build();
//            UserDetails user2 = User.withDefaultPasswordEncoder()
//                    .username("bruna")
//                    .password("bruna")
//                    .roles("USER")
//                    .build();
//            return new InMemoryUserDetailsManager(user, user2);
//        }
//
//        @Bean
//        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//            http
//                    .csrf(csrf -> csrf.disable())
//                    .authorizeHttpRequests((auth) ->
//                        auth
//                            .anyRequest().authenticated()
//                    )
//                    .sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                    .and()
//                    .httpBasic(withDefaults());
//            return http.build();
//        }

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                   .csrf().disable()
                    .authorizeHttpRequests((auth) ->
                        auth.antMatchers("/user").permitAll()
                            .anyRequest().authenticated()
                    )
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .httpBasic(withDefaults());

    }
}



