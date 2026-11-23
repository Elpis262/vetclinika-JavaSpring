package com.vetclinika.vetclinika.config;

import com.vetclinika.vetclinika.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/index", "/login", "/register", "/access-denied",
                    "/api/**",
                    "/**/*.css", "/**/*.js",
                    "/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/**/*.svg", "/**/*.gif", "/**/*.ico",
                    "/css/**", "/js/**", "/images/**"
                ).permitAll()

                .requestMatchers("/users/**", "/roles/**", "/specialities/**", "/species/**", "/breeds/**", "/services/**")
                    .hasRole("ADMIN")

                .requestMatchers("/admin/**", "/vets/**", "/appointments/**", "/prescriptions/**", "/pets/**")
                    .hasAnyRole("ADMIN", "DOCTOR")

                .requestMatchers("/booking/**").authenticated()

                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/access-denied")
            );

        return http.build();
    }
}