package com.crossover.media.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.crossover.media.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

	public Optional<User> findByEmail(String email);
}
