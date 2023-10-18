package com.example.mainservice.config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final String ADMIN_NAME = System.getenv("ADMIN_NAME");
    private final String ADMIN_PASSWORD = System.getenv("ADMIN_PASSWORD");

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        UserDetails adminDetails = User
                .withUsername(ADMIN_NAME)
                .password(passwordEncoder().encode(ADMIN_PASSWORD))
                .roles("ADMIN")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setCreateUserSql("insert into access_data (username, password, enabled) values (?,?,?)");
        users.createUser(adminDetails);
        return users;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder, DataSource dataSource) throws Exception {
        authenticationManagerBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from access_data where username=?");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/access").access("permitAll()")
                .antMatchers(HttpMethod.GET, "/info").access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.GET, "/info/**").access("hasRole('USER')")
                .antMatchers(HttpMethod.POST, "/info").access("hasRole('USER')")
                .antMatchers(HttpMethod.PATCH, "/info/**").access("hasRole('USER')")
                .antMatchers(HttpMethod.DELETE, "/info/**").access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.GET, "/users").access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.GET, "/users/**").access("hasRole('USER')")
                .antMatchers(HttpMethod.POST, "/users").access("hasRole('USER')")
                .antMatchers(HttpMethod.PATCH, "/users/**").access("hasRole('USER')")
                .antMatchers(HttpMethod.DELETE, "/users/**").access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.GET, "/images").access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.GET, "/images/**").access("hasRole('USER')")
                .antMatchers(HttpMethod.POST, "/images").access("hasRole('USER')")
                .antMatchers(HttpMethod.PATCH, "/images/**").access("hasRole('USER')")
                .antMatchers(HttpMethod.DELETE, "/images/**").access("hasRole('ADMIN')")
                .and()
                .csrf().disable()
                .formLogin().disable();
        return http.build();
    }
}
