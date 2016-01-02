package com.crossover.media.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crossover.media.model.User;
import com.crossover.media.repository.UserRepository;

@Service
public class DefaultUserService implements UserService {

	@Resource
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}
}
