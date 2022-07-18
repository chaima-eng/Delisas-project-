package com.example.backend.Controller;


import com.example.backend.Entity.JwtRequest;
import com.example.backend.Entity.JwtResponse;
import com.example.backend.Service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/log")
@Slf4j
public class JwtController {
	
	@Autowired
	private JwtService jwts;
	
	@PostMapping("/auth")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		return jwts.createJwtToken(jwtRequest);
	}



}
