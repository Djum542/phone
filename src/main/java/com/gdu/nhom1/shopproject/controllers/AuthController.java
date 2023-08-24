package com.gdu.nhom1.shopproject.controllers;



import com.gdu.nhom1.shopproject.dto.AuthRequest;
import com.gdu.nhom1.shopproject.dto.AuthResponse;
import com.gdu.nhom1.shopproject.jwt.JwtTokenUtil;
import com.gdu.nhom1.shopproject.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
//@RequestMapping("/login")
public class AuthController {
//    AuthenticationManager authManager;
//    JwtTokenUtil jwtUtil;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @PostMapping
//    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
//        try {
//            Authentication authentication = authManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getEmail(), request.getPassword())
//            );
//
//            User user = (User) authentication.getPrincipal();
//            String accessToken = jwtUtil.generateAccessToken(user);
//            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
//
//            return ResponseEntity.ok().body(response);
//
//        } catch (BadCredentialsException ex) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }

    @GetMapping("/error")
    public String errorPage() {
        return "There was an unexpected error (type=Unauthorized, status=401).";
    }

}
