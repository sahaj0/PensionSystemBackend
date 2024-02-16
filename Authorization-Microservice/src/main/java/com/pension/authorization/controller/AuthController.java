package com.pension.authorization.controller;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pension.authorization.exception.AuthenticationException;
import com.pension.authorization.model.UserRequest;
import com.pension.authorization.service.MyUserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author POD-4
 *
 */

@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin("*")
public class AuthController {
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	/**
	* The method at this end point generates token for valid login credentials
	*
	* @param UserData
	* @return AuthResponse, HttpStatus
	*/
	
	@PostMapping("/authenticate")
	public ResponseEntity<Map<String, String>> getAuthenticationToken(@Valid @RequestBody UserRequest userRequest) throws AuthenticationException{
		log.info("Authenticating the user");
		Map<String,String> authToken= new HashMap<>();
		UserDetails user= myUserService.loadUserByUsername(userRequest.getUsername());
		authToken.put("token", generateJwt(user.getUsername()));
		if(! passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
			log.error("Invalid credentials");
			throw new AuthenticationException("Invalid Credentials.");
		}
		log.info("authentication successful");
		return ResponseEntity.status(HttpStatus.OK).body(authToken);
	}
	
	/**
	* The method at this end point validates token
	*
	* @param String token
	* @return AuthResponse, HttpStatus
	*/
	
	@PostMapping(value = "/authorize")
	public boolean authorizeAdmin(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)  {
		log.info("validating token");
		String jwtToken = null;
		String userName = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				userName = getUsernameFromToken(jwtToken);
			}

			catch(SignatureException|MalformedJwtException|IllegalArgumentException | ExpiredJwtException e) {
				log.error("invalid token");
				return false;
			}

		}
		log.info("token is valid");
		return userName != null;

	}
	
	/*
	 *Generates jwt token 
	 */
	private String generateJwt(String user) {
		log.info("generating token");
		JwtBuilder builder=Jwts.builder();
		builder.setSubject(user);
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date(new Date().getTime()+(30*60*1000)));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");
		log.info("token generation successful");
		return builder.compact();
	}
	
	/**
	 * Extracts username from token
	 */
	private String getUsernameFromToken(String token) {
		log.info("extracting username from token");
		Jws<Claims> jws=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token.replace("Bearer ", ""));
		log.info("username extracted from token sucessfully");	
		return jws.getBody().getSubject();
		}

	}
