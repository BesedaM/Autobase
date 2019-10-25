package by.epam.javatraining.beseda.webproject.config;

import by.epam.javatraining.beseda.webproject.controller.security.AuthSuccessHandler;
import by.epam.javatraining.beseda.webproject.controller.security.SuccessLogoutHandler;
import by.epam.javatraining.beseda.webproject.service.PasswordHash;
import by.epam.javatraining.beseda.webproject.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationSuccessHandler getSuccessHandler() {
        return new AuthSuccessHandler();
    }

    @Bean
    public UserDetailsService getSecurityService(){ return new SecurityService();}

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LogoutHandler getLogoutHandler() {
        return new SuccessLogoutHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getSecurityService()).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll();

        http.formLogin()//
                // Submit URL of login page.
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .failureUrl("/login?error")
                .successHandler(getSuccessHandler())/// Submit URL
                .usernameParameter("username")//
                .passwordParameter("password");

        http.logout().logoutUrl("/logout").invalidateHttpSession(true).addLogoutHandler(getLogoutHandler());
        http.sessionManagement().maximumSessions(1).expiredUrl("/");
    }


}
