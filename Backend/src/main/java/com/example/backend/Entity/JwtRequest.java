package com.example.backend.Entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class JwtRequest {
	
	private String userName;
	private String userpassword;
}

