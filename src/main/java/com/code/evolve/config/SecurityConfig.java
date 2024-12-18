package com.code.evolve.config;

import com.code.evolve.util.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;


    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails admin= User.builder()
                .username("Yugesh")
                .password(passwordEncoder().encode("yugesh123"))
                .roles("ADMIN")
                .build();

        UserDetails user= User.builder()
                .username("selena")
                .password(passwordEncoder().encode("selena123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin,user);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(csrf->csrf.disable())
//                .authorizeHttpRequests(authorize->authorize.
//                        requestMatchers("/api/users/addUser").hasAnyRole("ADMIN")
//                        .requestMatchers("/api/users/getUsers").hasAnyRole("USER","ADMIN")
//                        .anyRequest().permitAll()).httpBasic(Customizer.withDefaults());
//        return httpSecurity.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize->authorize
                        .requestMatchers("/api/users/getToken").permitAll()
                        .anyRequest().authenticated());
        return httpSecurity.build();
    }
}
