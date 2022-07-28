package tn.esprit.spring.Entity;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	
	private User user;
	private String token;
	private String role;
	
	

}
