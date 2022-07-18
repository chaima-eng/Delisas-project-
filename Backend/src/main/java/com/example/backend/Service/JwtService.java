package com.example.backend.Service;


import com.example.backend.Entity.*;
import com.example.backend.Repository.IntPersonnelRepo;
import com.example.backend.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Primary
@Service
public class JwtService implements UserDetailsService {

	@Autowired
	private IntPersonnelRepo ur;
	
	@Autowired
	private JwtUtil jwtu;
	
	@Autowired
	private AuthenticationManager am;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest)throws Exception{
		System.out.print(jwtRequest);
		String userName=jwtRequest.getUserName();
		System.out.print(userName);
		String userPassword=jwtRequest.getUserpassword();
		authenticate(userName, userPassword);
		
	final	UserDetails userDetails = loadUserByUsername(userName);
		
		String newGeneratedToken=jwtu.generateToken(userDetails);
		Personnel user = ur.findByUserName(userName);
		String role = "";
		for (Roles value : user.getRoles()){
			 role =value.getName();
		}
			  
		
	
		return new JwtResponse(user, newGeneratedToken,role);
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = ur.findByUserName(username);
		
		if(user!=null){
			return new org.springframework.security.core.userdetails.User(
					user.getUserName(),
					user.getPassword(), 
					getAuthorities(user));
		}else{
			throw new UsernameNotFoundException("user name is not valid");
		}
		
	}
	
	
	public User getFulluser (String username) throws UsernameNotFoundException{
		return ur.findByUserName(username);
	}
	
	private Set getAuthorities (User user){
		Set authorities = new HashSet();
		
		return authorities;
	}
	
	private void authenticate (String userName,String userPassword) throws Exception{
		try{
			am.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));

		}catch(DisabledException e){
			throw new Exception("User is disabled ");
		}catch(BadCredentialsException e ){
			throw new Exception("bad credentials from User");
		}
	}

}
