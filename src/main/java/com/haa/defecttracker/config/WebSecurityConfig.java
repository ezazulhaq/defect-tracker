package com.haa.defecttracker.config;

import com.haa.defecttracker.service.UserDetailsServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception
    // {

    // UserBuilder users = User.withDefaultPasswordEncoder();

    // auth.inMemoryAuthentication()
    // .withUser(users.username("haq.abdul").password("Haa@1234").roles("EMPLOYEE",
    // "MANAGER"))
    // .withUser(users.username("ali.khan").password("Haa@1234").roles("EMPLOYEE",
    // "LIMITED"))
    // .withUser(users.username("hafeez.md").password("Haa@1234").roles("EMPLOYEE",
    // "LIMITED"));
    // }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] staticResources = {
                "/css/**",
                "/images/**",
                "/js/**",
        };

        http.authorizeRequests()
                // .anyRequest()
                // .authenticated()
                .antMatchers(staticResources)
                .permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/").hasRole("EMPLOYEE")// saveDefect
                .antMatchers("/search/**").hasRole("EMPLOYEE")
                .antMatchers("/updateDefect/**").hasRole("EMPLOYEE")
                .antMatchers("/saveDefect").hasRole("EMPLOYEE")
                .antMatchers("/**").hasRole("MANAGER")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authLogin")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }
}
