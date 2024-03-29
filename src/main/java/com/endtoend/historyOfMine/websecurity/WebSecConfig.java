package com.endtoend.historyOfMine.websecurity;

import com.endtoend.historyOfMine.service.UserService;
import com.endtoend.historyOfMine.websecurity.securityutils.BCryptEncodingUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecConfig {
    private final InternalFilter internalFilter;
    private final UserService userService;

    public WebSecConfig(InternalFilter internalFilter, UserService userService) {
        this.internalFilter = internalFilter;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/",
                        "/home",
                        "/docs",
                        "/swagger-ui.html"
                )
                .permitAll()
                .requestMatchers(
                        "/user",
                                "/login"
                )
                .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                            .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(internalFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/logout");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return BCryptEncodingUtils.getEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

}
