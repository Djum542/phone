package com.gdu.nhom1.shopproject.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.nhom1.shopproject.models.Role;
import com.gdu.nhom1.shopproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	private JwtTokenUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
				HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!hasAuthorizationBearer(request)) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = getAccessToken(request);

		if (!jwtUtil.validateAccessToken(token)) {
			filterChain.doFilter(request, response);
			return;
		}

		setAuthenticationContext(token, request);
		filterChain.doFilter(request, response);
	}

	private boolean hasAuthorizationBearer(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
			return false;
		}

		return true;
	}

	private String getAccessToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		String token = header.split(" ")[1].trim();
		return token;
	}

	private void setAuthenticationContext(String token, HttpServletRequest request) {
		UserDetails userDetails = getUserDetails(token);

		UsernamePasswordAuthenticationToken 
			authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

		authentication.setDetails(
				new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private UserDetails getUserDetails(String token) {
		User userDetails = new User();
		// jwt vuot qua quyen so huu
		Claims claims = jwtUtil.parseClaims(token);
		// chuyen gia tri chu de ve thanh motj chuoi
		String subject = (String) claims.get(Claims.SUBJECT);
		String roles = (String) claims.get("roles");
		
		System.out.println("SUBJECT: " + subject);
		System.out.println("roles: " + roles);
		roles = roles.replace("[", "").replace("]", "");
		String[] roleNames = roles.split(",");
		
		for (String aRoleName : roleNames) {
			userDetails.addRole(new Role(aRoleName));
		}
		
		String[] jwtSubject = subject.split(",");

		userDetails.setId(Integer.parseInt(jwtSubject[0]));
		userDetails.setEmail(jwtSubject[1]);

		return userDetails;
	}
}
