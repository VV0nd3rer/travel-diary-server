package com.kverchi.diary.config;


import com.google.common.collect.ImmutableList;
import com.kverchi.diary.security.CrossDomainCsrfTokenRepository;
import com.kverchi.diary.security.XSRFTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Created by Liudmyla Melnychuk on 12.12.2018.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /*.authorizeRequests()
                .anyRequest().authenticated()
                .and()*/
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .csrf()
                .disable();
                //.csrfTokenRepository(CrossDomainCsrfTokenRepository.withHttpOnlyFalse());
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("http://localhost:4200"));
        configuration.setAllowedMethods(ImmutableList.of("*"));
        configuration.setAllowedHeaders(
                ImmutableList.of("X-XSRF-TOKEN", "Content-Type"));
        configuration.setExposedHeaders(ImmutableList.of(
                "Content-Location", "X-XSRF-TOKEN"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
