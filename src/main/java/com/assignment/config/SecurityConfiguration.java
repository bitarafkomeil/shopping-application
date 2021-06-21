package com.assignment.config;

import com.assignment.model.enumeration.UserRole;
import com.assignment.security.JwtConfigurer;
import com.assignment.security.PasswordAuthenticationProvider;
import com.assignment.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String ADMIN = UserRole.ADMIN.name();
    private static final String USER = UserRole.USER.name();
    private final TokenProvider tokenProvider;

    private final SecurityProblemSupport problemSupport;

    private final PasswordAuthenticationProvider passwordAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/h2-console/**")
                .antMatchers("/swagger-ui.html");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport)
                .and()
                .headers()
                .contentSecurityPolicy("default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:")
                .and()
                .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
                .and()
                .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; speaker 'none'; fullscreen 'self'; payment 'none'")
                .and()
                .frameOptions()
                .deny()
                .and()
                .apply(jwtConfigurer())
                .and();

        configureAuthorization(http);
    }

    private void configureAuthorization(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Category
                .antMatchers(POST, "/api/categories/**").hasAuthority(ADMIN)
                .antMatchers(PUT, "/api/categories/**").hasAuthority(ADMIN)
                .antMatchers(GET, "/api/categories/**").hasAuthority(ADMIN)
                .antMatchers(DELETE, "/api/categories/**").hasAuthority(ADMIN)
                // Comment
                .antMatchers(POST, "/api/comments/**").hasAnyAuthority(ADMIN, USER)
                .antMatchers(PUT, "/api/comments/**").hasAnyAuthority(ADMIN, USER)
                .antMatchers(GET, "/api/comments/**").permitAll()
                .antMatchers(DELETE, "/api/comments/**").hasAnyAuthority(ADMIN, USER)
                // Product
                .antMatchers(POST, "/api/products/**").hasAuthority(ADMIN)
                .antMatchers(PUT, "/api/products/**").hasAuthority(ADMIN)
                .antMatchers(GET, "/api/products/**").permitAll()
                .antMatchers(DELETE, "/api/products/**").hasAuthority(ADMIN)
                // Rate
                .antMatchers(POST, "/api/rates/**").hasAnyAuthority(ADMIN, USER)
                .antMatchers(PUT, "/api/rates/**").hasAnyAuthority(ADMIN, USER)
                .antMatchers(GET, "/api/comments/**").permitAll()
                .antMatchers(DELETE, "/api/rates/**").hasAnyAuthority(ADMIN, USER)
                // User
                .antMatchers(PUT, "/api/users/**").hasAuthority(ADMIN)
                .antMatchers(POST, "/api/sign-up/**").permitAll()
                .antMatchers("/api/sign-in").permitAll();

    }

    private JwtConfigurer jwtConfigurer() {
        return new JwtConfigurer(tokenProvider);
    }

    @Autowired
    private void configureAuthenticationManagerBuilder(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(passwordAuthenticationProvider);
    }

}