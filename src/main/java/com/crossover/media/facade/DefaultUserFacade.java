package com.crossover.media.facade;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.crossover.media.converter.Converter;
import com.crossover.media.model.User;
import com.crossover.media.resource.UserResource;
import com.crossover.media.service.UserService;

@Component
public class DefaultUserFacade implements UserFacade {

	@Resource
	private UserService userService;

	@Resource(name = "userResourceConverter")
	private Converter<UserResource, User> userResourceConverter;
	
	@Resource(name = "userConverter")
	private Converter<User, UserResource> userConverter;

	@Override
	public UserResource createUser(UserResource userResource) {
		User user = userResourceConverter.convert(userResource);
		User createdUser = userService.createUser(user);
		return userConverter.convert(createdUser);
	}
}
