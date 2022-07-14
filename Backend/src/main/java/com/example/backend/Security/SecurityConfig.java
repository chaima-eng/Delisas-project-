package com.example.backend.Security;

import com.example.backend.Filter.CustomAuthentificationFilter;
import com.example.backend.Filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService  userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*
       CustomAuthentificationFilter customAuthentificationFilter =new CustomAuthentificationFilter(authenticationManagerBean());
       customAuthentificationFilter.setFilterProcessesUrl("/Rest/login");


         */
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       http.authorizeRequests().antMatchers("/Rest/login/**","/Rest/token/refresh/**").permitAll();

        //Give an authority for each role
        /*
        http.authorizeRequests().antMatchers(GET,"/Rest/personnels/**").hasAnyAuthority("Admin");
        http.authorizeRequests().antMatchers(POST,"/Rest/personnel/**").hasAnyAuthority("Admin");
        http.authorizeRequests().antMatchers(GET,"/Rest/personnels/**").hasAnyAuthority("RH");


         */

        //permit all requests
        http.authorizeRequests().anyRequest().permitAll();
        //http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthentificationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

















}
