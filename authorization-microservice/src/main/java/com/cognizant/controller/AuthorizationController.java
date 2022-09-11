package com.cognizant.controller;

import com.cognizant.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.cognizant.model.AuthRequest;
import com.cognizant.service.AdminDetailService;
import com.cognizant.util.JwtUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorizationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationController.class);

	private JwtUtil jwtUtil;

	private AdminDetailService userDetailService;

	private AuthenticationManager authenticationManager;

	
	@Autowired
	public AuthorizationController(JwtUtil jwtUtil, AdminDetailService userDetailService,
			AuthenticationManager authenticationManager) {

		this.jwtUtil = jwtUtil;
		this.userDetailService = userDetailService;
		this.authenticationManager = authenticationManager;
	}

	//starting message
	@GetMapping("/message")
	public ResponseEntity<String> welcome() {
		LOGGER.info("STARTED authorization microservice welcome");
		LOGGER.info("END - authorization microservice welcome");
		return ResponseEntity.ok("Welcome to Security Application");
	}

	//jwt token authentication using userName and password
	@PostMapping("/authenticate")
	public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		LOGGER.info("STARTED - generateToken");
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

		} catch (Exception e) {
			LOGGER.error("EXCEPTION - generateToken");
			throw new ResourceNotFoundException("Admin Not Found");
		}

		LOGGER.info("END - generateToken");
		return ResponseEntity.ok(jwtUtil.generateToken(authRequest.getUserName()));
	}
	
	
	//Validation of the generated jwt token to access '/authorize' endpoint
	@GetMapping("/validate")
	public ResponseEntity<?> authorization(@RequestHeader("Authorization") String token1) {

		LOGGER.info("STARTED - authorization");
		String token = token1.substring(7);

		UserDetails user = userDetailService.loadUserByUsername(jwtUtil.extractUsername(token));

		if (jwtUtil.validateToken(token, user)) {
			LOGGER.info("END - authorization");
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			LOGGER.info("END - authorization");
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}
	}
}
