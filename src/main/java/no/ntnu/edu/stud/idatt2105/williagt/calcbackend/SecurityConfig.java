/*
package no.ntnu.edu.stud.idatt2105.williagt.calcbackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //configure(http);
        http.requiresChannel().anyRequest().requiresSecure()
        .and()
        .authorizeRequests().antMatchers("/calculate/**").permitAll().anyRequest().fullyAuthenticated();
    }
}
*/
