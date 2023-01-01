package com.saurav.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
	
	  private String token;
	  private String type = "Bearer";
	  private String email;
	  private String role;

}
