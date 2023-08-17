package com.gdu.nhom1.shopproject.services;

import javax.transaction.Transactional;

import com.gdu.nhom1.shopproject.models.User;
import com.gdu.nhom1.shopproject.repository.UserRepositoryJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class UserServiceJwt {
	@Autowired private UserRepositoryJwt repo;
	@Autowired private PasswordEncoder passwordEncoder;
	
	public User save(User user) {
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		
		return repo.save(user);
	}
}
