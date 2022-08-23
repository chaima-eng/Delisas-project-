package com.example.backend.Controller;


import com.example.backend.Entity.*;
import com.example.backend.Repository.PasswordTokenRepository;
import com.example.backend.Service.IntPersonnelService;
import com.example.backend.Service.JwtService;
import com.example.backend.Service.PasswordResetService;
import com.example.backend.config.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin
@RequestMapping("/log")
@Slf4j
public class JwtController {


	private static final String SECRET_KEY = "secretkey";
	private static final int TOKEN_VALIDITY = 3600*5;





	@Autowired
	private JwtUtil jwtUtil;


	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	PasswordResetService passwordResetService;

	@Autowired
	PasswordTokenRepository passwordTokenRepository;

	@Autowired
	private JwtService jwts;

	@Autowired
	IntPersonnelService Myservice;



	@PostMapping("/authLivreur")
	public JwtResponse createJwtTokenLivreur(JwtRequestLiv jwtRequest)throws Exception
	{
		return jwts.createJwtTokenLivreur(jwtRequest);
	}











	@PostMapping("/auth")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		return jwts.createJwtToken(jwtRequest);
	}

	@PostMapping("/forget-password")
	public ResponseEntity<?> ForgetPass(@RequestParam("email") String email) {
		passwordResetService.forgetPassword(email);
		return ResponseEntity.ok("reset password sent successfully");
	}

	@PostMapping("/reset-password")
	public ResponseEntity<?> changePassword(@RequestBody PasswordReset passwordDto, @RequestParam String token)
	{
		String result = passwordResetService.validatePasswordResetToken(token);
		if (result != null) {
			return ResponseEntity
					.badRequest()
					.body("Error Token is : " + result);
		}

		User user =  passwordTokenRepository.findByToken(token).getUser();
		if (user.getEmail() != null) {
			passwordResetService.changeUserPassword(user, passwordDto.getNewPassword());
			passwordTokenRepository.delete(passwordTokenRepository.findByToken(token));
			return ResponseEntity.ok("Password changed successfully!");

		} else {
			return ResponseEntity
					.badRequest()
					.body("Error Token is : " + result);
		}
	}





}
