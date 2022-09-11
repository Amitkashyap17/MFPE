package com.cognizant.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

	private String userName;
	private String password;

}
