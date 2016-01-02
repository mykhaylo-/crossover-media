package com.crossover.media.converter;

import javax.annotation.Resource;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.crossover.media.model.User;
import com.crossover.media.resource.UserResource;

@Component("userResourceConverter")
public class UserResourceConverter implements Converter<UserResource, User> {

	@Resource
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User convert(UserResource source) {
		User user = new User();
		user.setEmail(source.getEmail());
		user.setPassword(passwordEncoder.encode(source.getPassword()));
		user.setRole(source.getRole());
		return user;
	}
}
