package com.crossover.media.service;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crossover.media.model.CurrentUser;
import com.crossover.media.model.User;
import com.crossover.media.repository.UserRepository;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Resource
	private UserRepository userRepository;

	@Override
	public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username).orElseThrow(
				() -> new UsernameNotFoundException(String.format("User with email=%s was not found", username)));
		return new CurrentUser(user);
	}
}
