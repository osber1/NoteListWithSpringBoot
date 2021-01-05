package com.notes.notelist.security;

import com.notes.notelist.auth.ApplicationUserService;
import com.notes.notelist.jwt.JwtConfig;
import com.notes.notelist.jwt.JwtTokenVerifier;
import com.notes.notelist.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

import static com.notes.notelist.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;
    private final SecretKey secretKey;
    private final JwtConfig config;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), config, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, config), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
//                .antMatchers("/").permitAll() // "localhost:8080/" url can access anyone
                .antMatchers("/api/**").hasAnyRole(SIMPLE.name(), ADMIN.name(), NOTES_ALL_USER_READER.name())
                .antMatchers(HttpMethod.DELETE, "/admin/api/**").hasAuthority(ApplicationUserPermission.USER_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/admin/api/**").hasAuthority(ApplicationUserPermission.USER_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/admin/api/**").hasAuthority(ApplicationUserPermission.USER_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/admin/api/**").hasAnyRole(ADMIN.name(), NOTES_ALL_USER_READER.name())
                .anyRequest()
                .authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui/",
                "/webjars/**");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
}
