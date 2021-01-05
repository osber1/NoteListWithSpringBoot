package com.notes.notelist.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.notes.notelist.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/**").hasAnyRole(SIMPLE.name(), ADMIN.name(), NOTES_ALL_USER_READER.name())
                .antMatchers(HttpMethod.DELETE, "/admin/api/**").hasAuthority(ApplicationUserPermission.USER_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/admin/api/**").hasAuthority(ApplicationUserPermission.USER_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/admin/api/**").hasAuthority(ApplicationUserPermission.USER_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/admin/api/**").hasAnyRole(ADMIN.name(), NOTES_ALL_USER_READER.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails simpleUser = User.builder()
                .username("simple")
                .password(passwordEncoder.encode("simple"))
//                .roles(SIMPLE.name())
                .authorities(SIMPLE.getGrantedAuthorities())
                .build();

        UserDetails readerUser = User.builder()
                .username("reader")
                .password(passwordEncoder.encode("reader"))
//                .roles(READER.name())
                .authorities(NOTES_ALL_USER_READER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                adminUser,
                simpleUser,
                readerUser
        );
    }
}
