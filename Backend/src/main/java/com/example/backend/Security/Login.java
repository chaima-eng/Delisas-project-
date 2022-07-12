package com.example.backend.Security;



import com.example.backend.Entity.Personnel;
import com.example.backend.Entity.Role;
import com.example.backend.Service.IntPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Configuration
@EnableWebSecurity
public class Login extends WebSecurityConfigurerAdapter {

    @Autowired
    IntPersonnelService personnelService;




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
               Personnel p = personnelService.loadUserByUserName(s);
               Collection<GrantedAuthority> authorities=new ArrayList<>();
                Role[] array= p.getRole().values();
                List<Role> roles= Arrays.asList(array);
                roles.forEach(r -> {
                    authorities.add(new SimpleGrantedAuthority(r.toString()));

                });
                return new User(p.getUserName(),p.getPassword(),authorities);
            }
        });

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.formLogin();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       http.authorizeRequests().anyRequest().permitAll();

    }






}
