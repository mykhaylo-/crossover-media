package com.crossover.media.converter;

import javax.annotation.Resource;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.crossover.media.model.User;
import com.crossover.media.resource.UserResource;

@Component("userConverter")
public class UserConverter implements Converter<User, UserResource> {

	@Resource
	private PasswordEncoder passwordEncoder;

	@Override
	public UserResource convert(User source) {
		UserResource user = new UserResource();
		user.setEmail(source.getEmail());
		user.setRole(source.getRole());
		user.setId(source.getId());
		return user;
	}
}
