package com.wealthpark.purchasehistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wealthpark.purchasehistory.security.SecurityUserDetailsService;
import com.wealthpark.purchasehistory.security.model.AuthenticationRequest;
import com.wealthpark.purchasehistory.security.model.AuthenticationResponse;
import com.wealthpark.purchasehistory.util.JwtUtil;

@RestController
public class SecurityController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SecurityUserDetailsService userDetailService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping(value="/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		}catch(BadCredentialsException e) {
			throw new Exception("Incorrect Username and Password", e);
		}
		
		final UserDetails userDetails = userDetailService.
				loadUserByUsername(authenticationRequest.getUserName());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
