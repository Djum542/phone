package com.gdu.nhom1.shopproject.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.gdu.nhom1.shopproject.dto.AuthRequest;
import com.gdu.nhom1.shopproject.dto.AuthResponse;
import com.gdu.nhom1.shopproject.jwt.JwtTokenUtil;
import com.gdu.nhom1.shopproject.models.User;
import com.gdu.nhom1.shopproject.repository.RoleRepositoryJwt;
import com.gdu.nhom1.shopproject.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AuthApi {
	@Autowired AuthenticationManager authManager;
	@Autowired
	JwtTokenUtil jwtUtil;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(), request.getPassword())
			);
			
			User user = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
			
			return ResponseEntity.ok().body(response);
			
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
//	@PostMapping("/json")
//	public ResponseEntity<Model> jsonuser(Model model, HttpSession session, String email){
//		model.addAttribute("role", roleRepositoryJwt);
//		model.addAttribute("user", repositoryJwt.findAll());
//		session.setAttribute("session", session);
//		return ResponseEntity.ok().body(model);
//	}
}
